package browser.view;

import browser.model.model;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.web.WebView;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.events.Event;
import org.w3c.dom.events.EventListener;
import org.w3c.dom.events.EventTarget;
import java.net.URL;

public class display extends view {
    // Display given message as an error in the GUI
    public static void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Browser Error");
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Typical code to create HTML page display
    protected Node makePageDisplay() {
        myPage = new WebView();
        // catch "browsing" events within web page
        myPage.getEngine().getLoadWorker().stateProperty().addListener(new display.LinkListener());
        return myPage;
    }

    // Update just the view to display given URL
    public static void update (URL url) {
        String urlText = url.toString();
        myPage.getEngine().load(urlText);
        myURLDisplay.setText(urlText);
    }

    // This display method prompts a user to enter a URL to be added to myFavorites
    public static String promptForFavorite () {
        TextInputDialog input = new TextInputDialog();
        input.setTitle("Add to Favorites");
        input.setContentText("URL:");
        input.setHeaderText("Please enter a URL to add to Favorites");
        input.showAndWait();
        return input.getResult();
    }

    // Inner class to deal with link-clicks and mouse-overs Mostly taken from
    //   http://blogs.kiyut.com/tonny/2013/07/30/javafx-webview-addhyperlinklistener/
    private static class LinkListener implements ChangeListener<Worker.State> {
        public static final String ANCHOR = "a";
        public static final String HTML_LINK = "href";
        public static final String EVENT_CLICK = "click";
        public static final String EVENT_MOUSEOVER = "mouseover";
        public static final String EVENT_MOUSEOUT = "mouseout";

        @Override
        public void changed(ObservableValue<? extends Worker.State> ov, Worker.State oldState, Worker.State newState) {
            if (newState == Worker.State.SUCCEEDED) {
                EventListener listener = this::handleMouse;
                Document doc = myPage.getEngine().getDocument();
                NodeList nodes = doc.getElementsByTagName(ANCHOR);
                for (int k = 0; k < nodes.getLength(); k += 1) {
                    EventTarget node = (EventTarget) nodes.item(k);
                    node.addEventListener(EVENT_CLICK, listener, false);
                    node.addEventListener(EVENT_MOUSEOVER, listener, false);
                    node.addEventListener(EVENT_MOUSEOUT, listener, false);
                }
            }
        }

        // Give user feedback as expected by modern browsers
        private void handleMouse(Event event) {
            final String href = ((Element) event.getTarget()).getAttribute(HTML_LINK);
            if (href != null) {
                switch (event.getType()) {
                    case EVENT_CLICK -> model.showPage(href);
                    case EVENT_MOUSEOVER -> panel.showStatus(href);
                    case EVENT_MOUSEOUT -> panel.showStatus(BLANK);
                }
            }
        }
    }
}
