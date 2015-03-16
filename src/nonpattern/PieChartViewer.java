package nonpattern;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.util.HashMap;

/**
 * Created by Apipol on 16/03/15.
 */
public class PieChartViewer extends CourseViewer {

    private static int xOffset = 250;
    private static int yOffset = 100;
    private int radius = 80;

    public void paint(Graphics g) {

        super.paint(g);

        int total = 0;
        for(JSlider slider: sliders)
            total += slider.getValue();

        if(total == 0)
            return;

        double startAngle = 0.0;
        for(JSlider slider: sliders) {
            double ratio = (slider.getValue() / (double)total) * 360.0;

            //draw the arc
            g.fillArc(xOffset, yOffset, 2 * radius, 2 * radius, (int) startAngle, (int) ratio);
            startAngle += ratio;
        }
    }

    public static void main(String[] args) {
        CourseViewer viewer = new PieChartViewer();
        viewer.addSubject("Physics");
        viewer.addSubject("Chemistry");
        viewer.addSubject("Biology");
    }
}
