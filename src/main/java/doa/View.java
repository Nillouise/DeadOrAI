package doa;

import game.entity.Role;
import game.entity.World;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import static game.entity.RoleKt.*;


public class View extends Stage {
    private Canvas canvas = new Canvas();
    private Model model;
    private World world;

    public View(Model model, World world) {
        Group root = new Group();
        root.getChildren().add(canvas);
        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());
        this.world = world;
        this.setScene(new Scene(root));
        this.model = model;
        this.setWidth(960);
        this.setHeight(540);
        this.show();
    }

    void strokeLine(GraphicsContext graphicsContext2D, Point2D a, Point2D b) {
        graphicsContext2D.strokeLine(a.getX(), canvas.getHeight() - a.getY(),
                b.getX(), canvas.getHeight() - b.getY());
    }

    public void update() {

        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

        Role role = world.getRole();
        graphicsContext2D.fillOval(role.getHead().getX(),canvas.getHeight() - role.getHead().getY(),5,5);

        strokeLine(graphicsContext2D, role.getHead(),role.getTail());
        strokeLine(graphicsContext2D, role.calArmPoint(),role.getLeftArm());
        strokeLine(graphicsContext2D, role.calArmPoint(),role.getRightArm());
        strokeLine(graphicsContext2D, role.getLeftArm(),role.getLeftHand());
        strokeLine(graphicsContext2D, role.getRightArm(),role.getRightHand());
        strokeLine(graphicsContext2D, role.getTail(),role.getLeftKnee());
        strokeLine(graphicsContext2D, role.getTail(),role.getRightKnee());
        strokeLine(graphicsContext2D, role.getLeftKnee(),role.getLeftFoot());
        strokeLine(graphicsContext2D, role.getRightKnee(),role.getRightFoot());

        strokeLine(graphicsContext2D, new Point2D(reginX1,0.0),new Point2D(reginX1,canvas.getHeight()));
        strokeLine(graphicsContext2D, new Point2D(0.0,reginY1),new Point2D(canvas.getWidth(),reginY1));

    }
}