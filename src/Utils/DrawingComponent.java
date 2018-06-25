package Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class DrawingComponent extends JComponent {
    public ArrayList<Article> articles;
    @Override
    public void paintComponent(Graphics g) { ;
        super.paintComponent(g);
        Graphics2D g_2D = (Graphics2D) g;
        for (Article article : articles) {
            Ellipse2D.Double ellipse = new Ellipse2D.Double(article.x, article.y, 10, 10);
            g_2D.setColor(Color.LIGHT_GRAY);
            g_2D.fill(ellipse);
        }
    }

    public void getArticles(ArrayList<Article> articles_temp) {
        this.articles = articles_temp;
    }

}
