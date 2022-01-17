package browser.view;

import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;

public class view {
    protected static TextField myURLDisplay;
    protected static final String BLANK = " ";
    protected static WebView myPage;

    /**
     * Returns scene for the browser, so it can be added to stage.
     */
    public Scene makeScene (int width, int height) {
        // Create a panel and a display
        panel panel = new panel();
        display display = new display();

        BorderPane root = new BorderPane();
        // must be first since other panels may refer to page
        root.setCenter(display.makePageDisplay());
        root.setTop(panel.makeInputPanel());
        root.setBottom(panel.makeInformationPanel());
        // create scene to hold UI
        return new Scene(root, width, height);
    }
}