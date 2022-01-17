package browser.view;

import browser.model.model;
import browser.model.navigation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import java.net.MalformedURLException;

public class panel extends view {
    private static Label myStatus;

    protected static void showStatus (String message) {
        myStatus.setText(message);
    }

    // Make panel where "would-be" clicked URL is displayed
    protected Node makeInformationPanel() {
        // BLANK must be non-empty or status label will not be displayed in GUI
        myStatus = new Label(BLANK);
        return myStatus;
    }

    // Make user-entered URL/text field and back/next buttons
    protected Node makeInputPanel() {
        HBox result = new HBox();
        // create buttons, with their associated actions

        // HOMEPAGE BUTTON
        Button getHome = button.makeButton("Home", event -> {
            try {
                navigation.getHome();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });

        result.getChildren().add(getHome);
        Button backButton = button.makeButton("Back", event -> navigation.back());
        result.getChildren().add(backButton);
        Button nextButton = button.makeButton("Next", event -> navigation.next());
        result.getChildren().add(nextButton);
        Button setHome = button.makeButton("Set Home", event -> navigation.setHome());
        result.getChildren().add(setHome);

        // SET FAVORITE BUTTON
        Button favorite = button.makeButton("Set Favorite", event -> {
            try {
                model.setFavorite();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });

        result.getChildren().add(favorite);
        // if user presses button or enter in text field, load/show the URL
        EventHandler<ActionEvent> showHandler = event -> model.showPage(myURLDisplay.getText());
        result.getChildren().add(button.makeButton("Go", showHandler));
        myURLDisplay = makeInputField(40, showHandler);
        result.getChildren().add(myURLDisplay);
        return result;
    }

    // Typical code to create text field for input
    private TextField makeInputField(int width, EventHandler<ActionEvent> handler) {
        TextField result = new TextField();
        result.setPrefColumnCount(width);
        result.setOnAction(handler);
        return result;
    }
}
