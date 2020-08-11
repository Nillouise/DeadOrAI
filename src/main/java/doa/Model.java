package doa;

import game.entity.World;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static game.system.GravityKt.input;

public class Model {
    Random rand = new Random();
    List<Point2D> pointList = new ArrayList<>();
    World world;

    public Model(World world) {
        this.world = world;
    }

    void click(int x, int y) {
        pointList.add(new Point2D(x, y));
    }

    void update() {
        input(world);
    }
}
