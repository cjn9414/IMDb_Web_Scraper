package Utils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.HashMap;

public class Scraper {
    private Document site;
    private String username, password, website;
    public Scraper(String website) {
        this.website = website;
        try {
            this.site = Jsoup.connect(website).execute().parse();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*
    This function will compile all necessary data from a given webpage. This data we are collecting consists
    of any given feature we feel will be advantageous to analyze.
     */


    public String getTitle() {
        String title;
        try {
            title = site.getElementById("firstHeading").text();
            return title;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return site.getElementsByAttribute("a").first().text();
    }

    /*
    This function will collect potential pages that we can scrape. It will do so by finding the links to
    new pages, based on the page it is currently on.
     */

    public HashMap<String, String> collectReferences() {
        HashMap<String, String> relatedArticles = new HashMap<String, String>();
        String[] blackList = {"Special", "Wikipedia", ".php",
                "Portal", "Help", "shop", "https"};
        try {
            Elements articles = site.getElementsByAttribute("title");
            for (Element article : articles) {
                String link = article.attr("href");
                int count;
                for (count = 0; count < blackList.length; count++) {
                    String ignoredWord = blackList[count];
                    if (link.contains(ignoredWord)) break;
                }
                if (count != blackList.length) continue;
                relatedArticles.put(article.text(), article.attr("href"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return relatedArticles;
    }
}
