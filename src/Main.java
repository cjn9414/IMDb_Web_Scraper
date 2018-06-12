import Utils.Scraper;
import org.jsoup.nodes.Document;
import java.io.InputStreamReader;

public class Main {
    
    private static Document site;

    public static void main(String[] args) {
        Scraper scraper = new Scraper(new InputStreamReader(System.in));
        scraper.scrape();
    }
}
