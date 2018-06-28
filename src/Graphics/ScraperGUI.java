package Graphics;
import Utils.Article;
import Utils.Scraper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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
        window.add(new MainPanel(articles, 10, this));
        window.setSize(1280, 800);
        window.setTitle(title);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}

class MainPanel extends JPanel {
    ArrayList<Article> articles;
    ReferenceBox refBox;
    public MainPanel(ArrayList<Article> articles_temp, int radius, ScraperGUI display) {
        this.articles = articles_temp;
        refBox = new ReferenceBox(this, articles.get(0));
        refBox.setVisible(false);
        this.add(refBox);
        for (Article article : articles) {
            Ellipse2D.Double ellipse = new Ellipse2D.Double(article.x, article.y, radius*2, radius*2);
            article.body = ellipse;
        }
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                for (Article ref : articles) {
                    int x1 = ref.x, y1 = ref.y, x2 = e.getPoint().x-radius, y2 = e.getPoint().y-radius;
                    if (insideArticle(x1, y1, x2, y2, radius)) {
                        refBox.updateArticle(ref);
                        refBox.setVisible(true);
                        ref.isClicked = true;
                        display.window.revalidate();
                        display.window.repaint();
                    }
                }
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                for (Article ref : articles) {
                    if (insideArticle(ref.x, ref.y, e.getPoint().x-radius, e.getPoint().y-radius, radius)) {
                        ref.isHover = true;
                        display.window.revalidate();
                        display.window.repaint();
                    } else ref.isHover = false;
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
            if (article.isClicked) {
                g_2D.setColor(Color.RED);
                //article.isClicked = false;
            } else if (article.isHover) {
                g_2D.setColor(Color.WHITE);
            }
            g_2D.fill(article.body);
        }
    }

    boolean insideArticle (int x1, int y1, int x2, int y2, int radius) {
        double dist = Math.sqrt(Math.pow(Math.abs(x1-x2), 2) +
                Math.pow(Math.abs(y1-y2), 2));
        if (dist < radius) {
            return true;
        }
        return false;
    }

    public void closeArticleWindow() {
        refBox.setVisible(false);
    }

}

class ReferenceBox extends JPanel {
    JButton closeButton = new JButton("CLOSE"),
        openArticle = new JButton("To Wikipedia"),
        jumpToArticle = new JButton("Change Article");
    public Article article;
    public MainPanel mainPanel;
    private JLabel articleName;
    private String address;

    public ReferenceBox(MainPanel mainPanel_temp, Article article_temp) {
        this.article = article_temp;
        this.address = "https://en.wikipedia.org" + article.link;
        this.articleName = new JLabel(article.name);
        this.mainPanel = mainPanel_temp;
        this.setLayout(new GridLayout(5,5, 10, 10));
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.closeArticleWindow();
            }
        });
        openArticle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(address);
                //open new chrome tab to this address
            }
        });
        jumpToArticle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // scrape new page
            }
        });
        this.add(closeButton).setLocation(1, 1);
        this.add(openArticle).setLocation(1, 2);
        this.add(jumpToArticle).setLocation(1, 3);
        this.add(articleName);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setOpaque(true);
        setBackground(Color.white);
        this.setBounds(0, 0, 250, 250);
    }

    public void updateArticle(Article newArticle) {
        this.remove(articleName);
        this.article = newArticle;
        this.address = "https://en.wikipedia.org" + article.link;
        this.articleName = new JLabel(article.name);
        this.add(articleName);
    }
}
