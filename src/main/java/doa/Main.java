package doa;

import game.entity.World;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        final World world = new World();
        final Model model = new Model(world);
        final View view = new View(model, world);
        final MouseController controller = new MouseController(model);//新增

        new AnimationTimer() {

            @Override
            public void handle(long now) {
                long b = System.currentTimeMillis();
                model.update();
                view.update();
//                System.out.print(String.format("\033[2J"));
//                System.out.println(System.currentTimeMillis() - b);
//                System.out.print(String.format("\033[%dA",1)); // Move up
//                System.out.print("\033[2K");
            }
        }.start();

        view.getScene().addEventHandler(MouseEvent.MOUSE_PRESSED, controller);//新增
    }

    public static void main(String[] args) {
        launch(args);
    }
}
