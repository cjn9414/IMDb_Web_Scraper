package Utils;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ScraperGUI {
    JFrame window;
    public ScraperGUI() {
        window = new JFrame();
        window.setSize(640, 400);
        window.getContentPane().setBackground(Color.BLACK);
        window.setTitle("temporary title");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
    public void displayArticles(ArrayList<Article> articles) {
        DrawingComponent DC = new DrawingComponent();
        DC.getArticles(articles);
        window.add(DC);
    }
}
