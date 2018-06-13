import Utils.Scraper;
import org.jsoup.nodes.Document;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {
    static final int exampleCount = 1; // number of scrapes
    private static Document site;

    public static void main(String[] args) {
        String view = "https://www.linkedin.com/";
        for (int i = 0; i < exampleCount; i++) {
            Scraper scraper = new Scraper(new InputStreamReader(new ByteArrayInputStream(view.getBytes(StandardCharsets.UTF_8))));
            //scraper.scrapeData();
            //view = scraper.getNextPage();
            try {
                scraper.executeLogin();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
