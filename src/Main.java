import Utils.*;
import java.util.Map.Entry;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    static Random rand = new Random();

    private ArrayList<Article> articles = new ArrayList<Article>();
    public static void main(String[] args) {
        String view = "https://www.wikipedia.org/wiki/Special:Random"; //Random webpage on wikipedia
        Scraper scraper = new Scraper(view);
        HashMap<String, String> pageReferences = scraper.collectReferences(); //Find all links on given page
        ArrayList<Article> articles = new ArrayList<>();
        for (Entry<String, String> pair : pageReferences.entrySet()) { //Every link
            Article new_article = new Article(pair.getKey(), pair.getValue(), rand.nextInt(640), rand.nextInt(400));
            articles.add(new_article);
        }
        ScraperGUI display = new ScraperGUI();
        display.displayArticles(articles);
    }
}
