import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        double[][] orig = new double[20][3], transformed = new double[20][2]; //#, {x,y(,z)}
        for(int i = 0; i < orig.length; i++) {
            double r = (i/2)*4.62/9, theta = (i%2==0)?0d:Math.PI/4;
            orig[i] = new double[]{r*Math.cos(theta), r*Math.sin(theta), 11.83*Math.sqrt(1-r*r/(4.62*4.62))};
//            System.out.print(i + ": " + orig[i][0]+", " + orig[i][1]+", "+orig[i][2]+"     ");
//            if(i%2==1) System.out.println();
        }

        double prevS = 0;
        for(int i = 1; i < transformed.length/2; i++) {
            double[] p0 = orig[(i-1)*2], p1 = orig[(i-1)*2+1], p2 = orig[2*i], p3 = orig[2*i+1];
            double currS = Math.sqrt(Math.pow(p2[0]-p3[0],2)+Math.pow(p2[1]-p3[1],2)),
                    d = Math.sqrt(Math.pow(p2[0]-p0[0],2)+Math.pow(p2[2]-p0[2],2)),
                    h = Math.sqrt(d*d-Math.pow((currS-prevS)/2,2));
//            System.out.println(h);
//            System.out.println(i + " " + p2[0] + ", " + p0[0] + "    " + p2[2] + ", " + p0[2]);
            transformed[2*i]=new double[]{transformed[2*(i-1)][0]+h,-currS/2};
            transformed[2*i+1]=new double[]{transformed[2*(i-1)][0]+h,currS/2};
//            System.out.println("CurrS: " + currS + ", d: " + d + ", h: " + h);
//            System.out.println("Pt1: " + transformed[2*i][0] + ", " + transformed[2*i][1]);
//            System.out.println("Pt2: " + transformed[2*i+1][0] + ", " + transformed[2*i+1][1]);
            prevS = currS;
        }
        System.out.println(prevS);

        int[] origin = {200,Toolkit.getDefaultToolkit().getScreenSize().height/2};
        double scaleVal = 44;
        for(int i = 0; i < transformed.length; i++) {
            transformed[i][0] *= scaleVal;
            transformed[i][1] *= scaleVal;
            transformed[i][0] += origin[0];
            transformed[i][1] += origin[1];
            //System.out.print(transformed[i][0] + ", " + transformed[i][1] + "        ");
            //if(i%2==1) System.out.println();
        }

//        System.out.println(Arrays.deepToString(transformed));

        JFrame frame = new JFrame();
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        Panel panel = new Panel(transformed);
        panel.repaint();
        frame.add(panel);

    }

}
