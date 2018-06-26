import Graphics.ScraperGUI;
import Utils.*;

import javax.swing.*;
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
        String pageTitle = scraper.getTitle();
        ArrayList<Article> articles = new ArrayList<>();

        for (Entry<String, String> pair : pageReferences.entrySet()) { //Every link
            Article new_article = new Article(pair.getKey(), pair.getValue(), rand.nextInt(1280), rand.nextInt(800));
            articles.add(new_article);
        }
        System.out.println(articles.size());
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ScraperGUI(articles, pageTitle);
            }
        });
    }
}
