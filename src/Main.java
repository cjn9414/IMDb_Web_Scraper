import Utils.*;
import org.jsoup.nodes.Document;
import java.util.HashMap;
import java.util.Set;

public class Main {
    static final int exampleCount = 1; // number of scrapes
    private static Document site;
    public static void main(String[] args) {

        String view = "https://www.wikipedia.org/wiki/Special:Random";
        for (int i = 0; i < exampleCount; i++) {
            Scraper scraper = new Scraper(view);
            HashMap<String, String> pageReferences = scraper.collectReferences();
            //scraper.scrapeData();
        }
    }
}
