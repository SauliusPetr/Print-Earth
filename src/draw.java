package src;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;


public class draw extends  Plot {

    Earth d;
    int blocksize = 3;
    double resolution = 1;

    public draw(String filename) throws IOException {
        d = new Earth();
        d.readDataArray(filename);

        setScaleX(0, 360);
        setScaleY(-90, 90);
    }


    @Override
    public void paintComponent(Graphics g) {
        List<double[]> points;
        Graphics2D g2d = (Graphics2D) g;

        this.width = getWidth();
        this.height = getHeight();

        for(int i = 0; i<d.arrayOfEarth.length; i++){
            double x = d.arrayOfEarth[i][0];
            double y = d.arrayOfEarth[i][1];
            double colour = d.arrayOfEarth[i][2];
            if(colour < -3500 )
                g2d.setColor(new Color(25,25,112));
            else if(colour >=-3500 &&  colour <-2500)
                g2d.setColor(new Color(0,0,128));
            else if(colour >=-2500 && colour <-1500)
                g2d.setColor(new Color(0,0,205));
            else if(colour >=-1500 && colour <-500)
                g2d.setColor(new Color(65,105,225));
            else if(colour >=-500 && colour<-150)
                g2d.setColor(new Color(100,149,237));
            else if(colour >=-150 && colour <0)
                g2d.setColor(new Color( 135,206,250));
            else if(colour >= 0 && colour <500 )
                g2d.setColor(new Color(102,141,60));
            else if (colour >=500 && colour <1000)
                g2d.setColor(new Color(218,202,105));
            else if (colour >=1000 && colour <2000)
                g2d.setColor(new Color(213,117,0));
            else if (colour >= 2000 && colour <3000)
                g2d.setColor(new Color(143,59,27));
            else if (colour >= 3000 && colour <4000)
                g2d.setColor(new Color(185,156,107));
            else if (colour >=4000)
                g2d.setColor(new Color(133,57,35));
            else
                g2d.setColor(new Color(255,255,255));
            x += 180;

            if(x > 360){x -= 360;}
            g.fillRect(scaleX(x), scaleY(y), blocksize, blocksize);
        }
    }
    public interface MouseListener{
        public void mouseClicked(MouseEvent ev);
    }
}
