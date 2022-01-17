package browser.model;

import browser.view.display;

public class navigation extends model {

    // Move to previous URL in the history
    public static void back() {
        myCurrentIndex -= 1;
        display.update(myHistory.get(myCurrentIndex));
    }

    // Set current URL to homepage
    public static void setHome() {
        myHome = myCurrentURL;
    }

    // Display home URL
    public static void getHome() throws IllegalAccessException {
        if (myHome == null) {
            throw new IllegalAccessException("Bad URL");
        }
        display.update(myHome);
    }

    // Move to next URL in the history
    public static void next () {
        myCurrentIndex += 1;
        display.update(myHistory.get(myCurrentIndex));
    }

    // Returns true if there is a next URL available
    public static boolean hasNext () {
        return myCurrentIndex < (myHistory.size() - 1);
    }
}
