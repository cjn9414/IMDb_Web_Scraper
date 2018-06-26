package Graphics;
import Utils.Article;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class ScraperGUI {
    JFrame window;
    ArrayList<Article> articles;
    String title;
    public ScraperGUI(ArrayList<Article> articles_temp, String title_temp) {
        this.articles = articles_temp;
        this.title = title_temp;
        window = new JFrame();
        window.add(new MainPanel(articles, 10));
        window.setSize(1280, 800);
        window.setTitle(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}

class MainPanel extends JPanel {
    ArrayList<Article> articles;
    public MainPanel(ArrayList<Article> articles_temp, int radius) {
        this.articles = articles_temp;

        for (Article article : articles) {
            Ellipse2D.Double ellipse = new Ellipse2D.Double(article.x, article.y, radius*2, radius*2);
            article.body = ellipse;
        }
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                for (Article ref : articles) {
                    double dist = Math.sqrt(Math.pow(Math.abs(ref.x-e.getPoint().x+radius), 2) +
                            Math.pow(Math.abs(ref.y-e.getPoint().y+radius), 2));
                    if (dist < radius) {
                        System.out.println(ref.name);
                    }
                }
            }
        });
    }
    @Override
    protected void paintComponent(Graphics g) { ;
        super.paintComponent(g);
        setOpaque(true);
        setBackground(Color.BLACK);
        Graphics2D g_2D = (Graphics2D) g;
        for (Article article : articles) {
            g_2D.setColor(Color.LIGHT_GRAY);
            g_2D.fill(article.body);
        }
    }
}
