package pattern;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by Apipol on 16/03/15.
 */
public class PieChartObserver extends JPanel implements SubjectObserver {

    private CourseRecord[] courseData;

    private static int xOffset = 100;
    private static int yOffset = 100;
    private int radius = 80;

    private Color[] color = {Color.CYAN, Color.blue, Color.BLACK, Color.green, Color.red};

    public PieChartObserver(CourseData data) {
        courseData = data.getUpdate();
        data.attach(this);

        this.setPreferredSize(new Dimension(300, 300));
    }

    public void paint(Graphics g) {

        super.paint(g);

        int total = 0;

        for(CourseRecord course : courseData)
            total += course.getNumOfStudents();

        if(total == 0)
            return;

        double startAngle = 0.0;
        for(int i = 0; i < courseData.length; ++i) {
            double ratio = (courseData[i].getNumOfStudents() / (double)total) * 360.0;

            //draw the arc
            g.setColor(color[i]);
            g.fillArc(xOffset, yOffset, 2 * radius, 2 * radius, (int) startAngle, (int) ratio);
            startAngle += ratio;
        }
    }

    @Override
    public void stateChange(CourseRecord newData) {
        for(CourseRecord rec : courseData)
            if(rec.getSubject().equals(newData.getSubject()))
                rec = newData;
        this.repaint();
    }
}
