package src;

import javax.swing.*;

public class Plot extends JComponent {
    int width = 1300, height = 800;
    double xmin=0,  xmax=1, ymin=0, ymax=1;

    public  int scaleX(double x) {
        return (int) (width * (x - xmin) / (xmax - xmin));
    }
    public  int scaleY(double y) {
        return (int) (height * (ymin - y)/(ymax - ymin)+height);
    }
    public  void setScaleX(double min, double max) {
        xmin = min;   xmax = max;   }
    public  void setScaleY(double min, double max) {
        ymin = min;   ymax = max; }
}
