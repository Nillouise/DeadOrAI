package doa;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

public class View extends Stage {
    private Canvas canvas = new Canvas();

    public View(){
        Group root = new Group();
        root.getChildren().add(canvas);
        canvas.widthProperty().bind(widthProperty());
        canvas.heightProperty().bind(heightProperty());
        this.setScene(new Scene(root));

        this.setWidth(960);//设置宽度为960
        this.setHeight(540);//设置高度为540
        this.show();//显示出来啦
    }

    public void update(){
        canvas.getGraphicsContext2D().clearRect(0,0,canvas.getWidth(),canvas.getHeight());
//        canvas.getGraphicsContext2D().drawImage(...);//这里开始绘制图形
    }
}
