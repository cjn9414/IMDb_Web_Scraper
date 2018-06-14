import Utils.*;
import org.javatuples.Pair;
import org.jsoup.nodes.Document;

import java.nio.charset.StandardCharsets;
import java.io.*;

public class Main {
    static final int exampleCount = 1; // number of scrapes
    private static Document site;
    private static Pair <String, String> creds;
    public static void main(String[] args) {

        try {
            creds = Credentials.getCredentials();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String view = "https://www.linkedin.com/";
        for (int i = 0; i < exampleCount; i++) {
            Scraper scraper = new Scraper(new InputStreamReader(new ByteArrayInputStream(view
                    .getBytes(StandardCharsets.UTF_8))), creds.getValue0(), creds.getValue1());

            //scraper.scrapeData();

            try {
               Document page =  scraper.executeLogin();
               view = scraper.getNextPage(page);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
