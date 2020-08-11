package doa;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class MouseController implements EventHandler<MouseEvent> {
    Model model;

    public MouseController(Model model) {
        this.model = model;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
            model.click((int) event.getX(), (int) event.getY());
//            model...
        }
    }
}