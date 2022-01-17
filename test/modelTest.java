
import org.junit.jupiter.api.Test;
import browser.model.model;

/**
 * Tests for model class
 *
 * @author Cate Schick
 */
class modelTest extends Main {
    // TEST SHOWPAGE
    /**
     * NOTE: my showPage tests aren't functioning properly due to my view being null
     */
    @Test
    void showPageTestOne() {
        // should show google.com
        String url = "https://google.com";
        model.showPage(url);
    }
    @Test
    void showPageTestTwo() {
        // should show youtube.com
        String url = "https://youtube.com";
        model.showPage(url);
    }
}
