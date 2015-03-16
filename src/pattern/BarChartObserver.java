package pattern;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

/*
 *	Presents a Bar Chart view of Course Data 
 */

public class BarChartObserver extends JPanel implements SubjectObserver {

	public BarChartObserver(CourseData data) {
		data.attach(this);
		this.courseData = data.getSubjectData();
		this.setPreferredSize(new Dimension(2 * xOffset
				+ (this.barSpacing + barWidth) * this.courseData.size(),
				graphHeight + 2 * yOffset));
		this.setBackground(Color.white);
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.drawString("Num of Students", 5, 150);
		g.drawLine(xOffset, yOffset, xOffset, graphHeight + yOffset);
		g.drawLine(xOffset, graphHeight + yOffset, xOffset
				+ (this.barSpacing + barWidth) * this.courseData.size(),
				graphHeight + yOffset);
		for (int i = 0; i < courseData.size(); i++) {
			CourseRecord record = (CourseRecord) courseData.elementAt(i);
			g.setColor(Color.blue);
			g.fillRect(this.xOffset + (i + 1) * this.barSpacing + i
					* this.barWidth, this.yOffset + this.graphHeight
					- this.barHeight + 2
					* (this.maxValue - record.getNumOfStudents()),
					this.barWidth, 2 * record.getNumOfStudents());
			g.setColor(Color.red);
			g.drawString(record.getSubject(), this.xOffset + (i + 1)
					* this.barSpacing + i * this.barWidth, this.yOffset
					+ this.graphHeight + 20);
		}
	}

	public void update(CourseData data) {
		this.courseData = data.getSubjectData();
		this.setPreferredSize(new Dimension(2 * xOffset
				+ (this.barSpacing + barWidth) * this.courseData.size(),
				graphHeight + 2 * yOffset));
		this.revalidate();
		this.repaint();
	}

	private Vector courseData;

	private final int xOffset = 100;

	private final int yOffset = 30;

	private final int graphHeight = 210;

	private final int maxValue = 100;

	private final int barHeight = 200;

	private final int barWidth = 50;

	private final int barSpacing = 20;
}
