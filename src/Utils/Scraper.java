package Utils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Scraper {
    BufferedReader reader;
    private static Document site;

    public Scraper(InputStreamReader website) {
        this.reader = new BufferedReader(website);
    }

    public void scrape() {
        try {
            site = Jsoup.connect(reader.readLine()).timeout(0).get();

            Elements tags = site.select("tr");
            for (int index = 0; index < tags.size(); index++) {
                System.out.println(tags.get(index).text());
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}