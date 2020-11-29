package src;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File dir = new File("C:\\earth.xyz");
        Earth op =new Earth();
        MapCoordinate co = new MapCoordinate(32.9697, -96.80322,500);
        op.readDataArray(dir.toString());
        op.coordinatesAbove(50);
        op.coordinatesBelow(20);
        op.percentageAbove(400);
        op.percentageBelow(-500);
        op.userPercentageAbove();
        op.readDataMap(dir.toString());
        op.getAltitude(346.166666667,-12.6666666667);
        op.findAltD();
        JFrame jf = new JFrame("Map");
        jf.getContentPane().setPreferredSize(new Dimension(1300,800));
        draw pd = new draw(dir.toString());
        jf.add(pd);
        jf.pack();
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        co.distanceTo( 29, -98);
    }




}
