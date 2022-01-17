package browser.model;

import browser.view.display;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class model {
    protected static URL myCurrentURL;
    protected static int myCurrentIndex;
    protected static List<URL> myFavorites;
    protected static List<URL> myHistory;
    protected static URL myHome;

    public model() {
        myCurrentURL = null;
        myCurrentIndex = -1;
        myHistory = new ArrayList<>();
        myFavorites = new ArrayList<>();
        myHome = null;
    }

    // Sets URL in myFavorites
    public static void setFavorite() throws MalformedURLException {
        String favorite = display.promptForFavorite();
        showPage(favorite);
        myFavorites.add(new URL(favorite));

        // Pseudo-frontend until I figure out how to display favorites
        System.out.println("CURRENT FAVORITES:" + myFavorites);
    }

    // Deal with a potentially incomplete URL
    private static URL completeURL (String possible) {
        final String PROTOCOL_PREFIX = "http://";
        try {
            // try it as is
            return new URL(possible);
        }
        catch (MalformedURLException e) {
            try {
                // e.g., let user leave off initial protocol
                return new URL(PROTOCOL_PREFIX + possible);
            }
            catch (MalformedURLException ee) {
                try {
                    return new URL(myCurrentURL.toString() + "/" + possible);
                }
                catch (MalformedURLException eee) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    /**
     * Display given URL.
     */
    public static void showPage(String url) {
        try {
            URL tmp = completeURL(url);
            if (tmp != null) {
                // unfortunately, completeURL may not have returned a valid URL, so test it
                tmp.openStream();
                // if successful, remember this URL
                myCurrentURL = tmp;
                if (navigation.hasNext()) {
                    myHistory = myHistory.subList(0, myCurrentIndex + 1);
                }
                myHistory.add(myCurrentURL);
                myCurrentIndex += 1;
                display.update(myCurrentURL);
            }
        } catch (Exception e) {
            display.showError(String.format("Could not load %s", url));
        }
    }
}