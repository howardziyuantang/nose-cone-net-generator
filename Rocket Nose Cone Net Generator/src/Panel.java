import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {

    private double[][] points;

    public Panel(double[][] transformed) {
        super();
        this.setBackground(Color.black);
        this.setVisible(true);
        this.points = transformed;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.white);
        for(int i = 1; i < points.length/2; i++) {
            g.drawLine((int)points[i*2][0], (int)points[i*2][1], (int)points[i*2+1][0], (int)points[i*2+1][1]);
            g.drawLine((int)points[i*2][0], (int)points[i*2][1], (int)points[(i-1)*2][0], (int)points[(i-1)*2][1]);
            g.drawLine((int)points[i*2+1][0], (int)points[i*2+1][1], (int)points[(i-1)*2+1][0], (int)points[(i-1)*2+1][1]);
        }
    }

}
