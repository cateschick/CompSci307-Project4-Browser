import browser.model.navigation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class navigationTest extends navigation {

    // TEST SET HOME BUTTON
    @Test
    void setHomeTestOne() throws MalformedURLException {
        // When home is null
        myHome = null;
        myCurrentURL = new URL("https://courses.cs.duke.edu/compsci307d/fall21/");
        navigation.setHome();
        Assertions.assertEquals(myHome, myCurrentURL);
    }
    @Test
    void setHomeTestTwo() throws MalformedURLException {
        // When home is another URL
        myHome = new URL("https://google.com");
        myCurrentURL = new URL("https://courses.cs.duke.edu/compsci307d/fall21/");
        navigation.setHome();
        Assertions.assertEquals(myHome, myCurrentURL);
    }

    // TEST HAS NEXT METHOD
    @Test
    void hasNextTestOne() throws MalformedURLException {
        URL one = new URL("https://google.com");
        URL two = new URL("https://twitter.com");
        URL three = new URL("https://youtube.com");
        URL four = new URL("https://facebook.com");
        myHistory = Arrays.asList(one, two, three, four);
        myCurrentIndex = 3;
        Assertions.assertFalse(navigation.hasNext());
    }
    @Test
    void hasNextTestTwo() throws MalformedURLException {
        URL one = new URL("https://google.com");
        URL two = new URL("https://twitter.com");
        URL three = new URL("https://youtube.com");
        URL four = new URL("https://facebook.com");
        myHistory = Arrays.asList(one, two, three, four);
        myCurrentIndex = 0;
        Assertions.assertTrue(navigation.hasNext());
    }

    // TEST GET HOME BUTTON
    @Test
    void getHomeTestOne() throws MalformedURLException, IllegalAccessException {
        // When home is null
        myHome = null;
        myCurrentURL = new URL("https://courses.cs.duke.edu/compsci307d/fall21/");
        navigation.getHome();
        // Nothing should happen and myHome should still be null
        Assertions.assertNull(myHome);
    }
    @Test
    void getHomeTestTwo() throws MalformedURLException, IllegalAccessException {
        // When home is another URL
        myHome = new URL("https://google.com");
        myCurrentURL = new URL("https://courses.cs.duke.edu/compsci307d/fall21/");
        navigation.getHome();
        // myHome should be updated
        Assertions.assertEquals(myHome, myCurrentURL);
    }

    // TEST BACK BUTTON
    @Test
    void backTestOne() throws MalformedURLException {
        myCurrentURL = new URL("https://www.google.com");
        URL previousURL = new URL("https://sites.duke.edu/arabic408/");
        myHistory = Arrays.asList(previousURL, myCurrentURL);
        navigation.back();
        Assertions.assertEquals(myCurrentURL, previousURL);
    }
    @Test
    void backTestTwo() throws MalformedURLException {
        myCurrentURL = new URL("https://www.google.com");
        URL previousURL = new URL("https://sites.duke.edu/arabic408/");
        URL otherURL = new URL("https://youtube.com");
        URL finalURL = new URL("https://courses.cs.duke.edu/compsci307d/fall21");
        myHistory = Arrays.asList(otherURL, previousURL, myCurrentURL, finalURL);
        navigation.back();
        Assertions.assertEquals(myCurrentURL, previousURL);
    }

    // TEST NEXT BUTTON
    @Test
    void nextTestOne() throws MalformedURLException {
        myCurrentURL = new URL("https://www.google.com");
        URL otherURL = new URL("https://facebook.com");
        URL goalURL = new URL("https://sites.duke.edu/arabic408/");
        myHistory = Arrays.asList(otherURL, myCurrentURL, goalURL);
        navigation.next();
        Assertions.assertEquals(myCurrentURL, goalURL);
    }
    @Test
    void nextTestTwo() throws MalformedURLException {
        myCurrentURL = new URL("https://www.google.com");
        URL goalURL = new URL("https://sites.duke.edu/arabic408/");
        myHistory = Arrays.asList(myCurrentURL, goalURL);
        navigation.next();
        Assertions.assertEquals(myCurrentURL, goalURL);
    }

}
