package Utils;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Article extends JPanel {
    public String name, link;
    public int x, y;
    public Ellipse2D body;
    public Article(String name_temp, String link_temp, int x_temp, int y_temp) {
        this.name = name_temp;
        this.link = link_temp;
        this.x = x_temp;
        this.y = y_temp;
    }

}
