package game.entity

import javafx.geometry.Point2D

const val bodyLen = 40.0;
const val handLen = 15.0;
const val armLen = 15.0;
const val armStart = 15.0;
const val footLen = 20.0;
const val kneeLen = 20.0;
const val kneeStart = 0.0;
const val initX = 200.0;
const val initY = 100.0;
const val reginX1 = 100.0;
const val reginY1 = 100.0;

const val grave_time_limit = 40;
const val grave_range_y = initY + 3500;
const val grave_range_foot_y = initY + 500;

data class Grave(
    var jumpTime: Int = 0,
    var jumpPoint: Point2D = Point2D.ZERO
)


data class Role(

    var head: Point2D = Point2D(initX, initY + bodyLen + footLen + kneeLen),
    var tail: Point2D = Point2D(initX, initY + footLen + kneeLen),
    var leftHand: Point2D = Point2D(initX, initY + bodyLen + footLen + kneeLen - armStart - armLen - handLen),
    var leftArm: Point2D = Point2D(initX, initY + bodyLen + footLen + kneeLen - armStart - armLen),
    var rightHand: Point2D = Point2D(initX, initY + bodyLen + footLen + kneeLen - armStart - armLen - handLen),
    var rightArm: Point2D = Point2D(initX, initY + bodyLen + footLen + kneeLen - armStart - armLen),
    var leftFoot: Point2D = Point2D(initX, initY),
    var leftKnee: Point2D = Point2D(initX, initY + kneeLen),
    var rightFoot: Point2D = Point2D(initX, initY),
    var rightKnee: Point2D = Point2D(initX, initY + kneeLen),
    var grave: Grave = Grave()

) {
    fun calArmPoint(): Point2D {
        val distance = head.distance(tail)
        val sx = (tail.x - head.x) / head.distance(tail);
        val sy = (tail.y - head.y) / head.distance(tail);

        val nx = armStart * sx + head.x;
        val ny = armStart * sy + head.y;
        return Point2D(nx, ny);
    }
}