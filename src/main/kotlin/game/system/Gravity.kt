package game.system

import game.entity.*
import javafx.geometry.Point2D
import kotlin.math.abs
import kotlin.math.min
import kotlin.random.Random
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.memberProperties


fun gravity() {

}

const val speed = 3.0;
const val elasticityRange = 3.0;
fun checkmove(world: World, newRole: Role): Boolean {
    val oldRole = world.role;
    for (prop in Role::class.memberProperties) {
        if (prop.get(oldRole) is Point2D) {
            val newPoint = prop.get(newRole) as Point2D
            if ((prop.get(oldRole) as Point2D).distance(newPoint) > speed) {
                return false;
            }
            if (newPoint.x < reginX1 || newPoint.y < reginY1) {
                return false;
            }
        }
    }

    //check body
    if (abs(newRole.head.distance(newRole.tail) - bodyLen) > elasticityRange) {
        return false;
    }
    //check hand
    if (abs(newRole.leftHand.distance(newRole.leftArm) - handLen) > elasticityRange) {
        return false;
    }
    if (abs(newRole.rightHand.distance(newRole.rightArm) - handLen) > elasticityRange) {
        return false;
    }

    //check arm
    val armPoint: Point2D = newRole.calArmPoint()
    if (abs(armPoint.distance(newRole.leftArm) - armLen) > elasticityRange) {
        return false;
    }
    if (abs(armPoint.distance(newRole.rightArm) - armLen) > elasticityRange) {
        return false;
    }

    //check knee
    if (abs(newRole.leftKnee.distance(newRole.tail) - kneeLen) > elasticityRange) {
        return false;
    }
    if (abs(newRole.rightKnee.distance(newRole.tail) - kneeLen) > elasticityRange) {
        return false;
    }

    //check foot
    if (abs(newRole.leftFoot.distance(newRole.leftKnee) - kneeLen) > elasticityRange) {
        return false;
    }
    if (abs(newRole.rightFoot.distance(newRole.rightKnee) - kneeLen) > elasticityRange) {
        return false;
    }


    //check gravity
    val scene: Scene = world.scene;
    if (abs(newRole.rightFoot.y - scene.groundY) > 1 && abs(newRole.leftFoot.y - scene.groundY) > 1) {
        if (newRole.grave.jumpTime == 0) {
            when {
                abs(oldRole.rightFoot.y - scene.groundY) < 1 -> {
                    newRole.grave.jumpPoint = oldRole.rightFoot;
                    newRole.grave.jumpTime = 1;
                }
                abs(oldRole.leftFoot.y - scene.groundY) < 1 -> {
                    newRole.grave.jumpPoint = oldRole.leftFoot
                    newRole.grave.jumpTime = 1;
                }
                else -> {
                    println("error gravity$newRole");
                }
            }
        } else {
            newRole.grave.jumpTime = oldRole.grave.jumpTime + 1
        }
    } else {
        newRole.grave.jumpTime = 0
    }

    var totalY = 0.0;
    Role::class.memberProperties.forEach {
        if (it.get(newRole) is Point2D) {
            val newPoint = it.get(newRole) as Point2D
            totalY += newPoint.y;
        }
    }
    totalY += newRole.rightFoot.y + newRole.leftFoot.y;
    if (newRole.grave.jumpTime >= grave_time_limit) {
        if (totalY > grave_range_y - newRole.grave.jumpTime ) {
            return false;
        }
        val lowerFoot = min(newRole.leftFoot.y,newRole.rightFoot.y);
        if(lowerFoot > grave_range_foot_y - newRole.grave.jumpTime*0.5 ){
            return false;
        }
    }


    //check foot change
    if (abs(newRole.rightFoot.y - scene.groundY) < 1
        && abs(oldRole.rightFoot.y - scene.groundY) < 1
        && abs(newRole.rightFoot.x - oldRole.rightFoot.x) > 1
    ) {
        return false;
    }
    if (abs(newRole.leftFoot.y - scene.groundY) < 1
        && abs(oldRole.leftFoot.y - scene.groundY) < 1
        && abs(newRole.leftFoot.x - oldRole.leftFoot.x) > 1
    ) {
        return false;
    }

    return true;
}

fun collise() {

}

fun score() {

}

fun ranMove(a: Point2D): Point2D {
    var t = speed - 1;
    return Point2D(a.x + Random.nextDouble(-t+0.2, t), a.y + Random.nextDouble(-t+0.1, t) );
}

fun input(world: World) {
    val role = world.role.copy()
    role.grave = world.role.grave.copy()
    do {
        for (prop in Role::class.memberProperties) {
            if (prop is KMutableProperty<*>) {
                try {
                    prop.setter.call(role, ranMove(prop.get(world.role) as Point2D));
                } catch (ignore: Exception) {
                }
            }
        }
    } while (!checkmove(world, role))
    world.role = role
}
