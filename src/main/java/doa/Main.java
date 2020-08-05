package doa;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        final Model model = new Model();

        final View view = new View();//新增
        new AnimationTimer() {
//          long time = System.currentTimeMillis();

            @Override
            public void handle(long now) {
/**
 if (System.currentTimeMillis() - time < 1000 / 60) {
 try {
 Thread.sleep(1000 / 60 - (System.currentTimeMillis() - time));
 } catch (Exception e) {
 e.printStackTrace();
 }
 }
 */
                view.update();//新增
//                model.update();
//              time = System.currentTimeMillis();
            }
        }.start();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
