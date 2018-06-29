import Graphics.ScraperGUI;
import Utils.*;
import javax.swing.*;
import java.util.ArrayList;

public class Main {

    public static Scraper scraper;
    private static ArrayList<Article> articles;
    public static void main(String[] args) {
        String page = "https://www.wikipedia.org/wiki/Special:Random"; //Random webpage on wikipedia
        scraper = new Scraper(page);
        String pageTitle = scraper.configureScraper();
        articles = scraper.getArticles();
        System.out.println(articles.size());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ScraperGUI(articles, pageTitle, scraper);
            }
        });
    }
}
