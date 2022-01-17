package browser.view;

import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class button extends view {
    // create a button
    protected static Button makeButton(String label, EventHandler<ActionEvent> handler) {
        Button result = new Button();
        result.setText(label);
        result.setOnAction(handler);
        return result;
    }
}
