package nonpattern;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Presents a barchart view of a set of subjects and their marks. No pattern is used in 
 * this program.
 */
public class CourseViewer extends JFrame implements ActionListener,
		ChangeListener {

	public CourseViewer() {
		this.setTitle("Observer Pattern -- Non Pattern Version");
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		sliders = new Vector<JSlider>();
		sliderPanel = new JPanel();
		sliderPanel.setLayout(new GridBagLayout());
		sliderPanel.setBackground(Color.white);
		this.getContentPane().setLayout(new GridBagLayout());
		coursePanel = new JPanel();
		coursePanel.setBorder(new TitledBorder("Courses"));
		coursePanel.setLayout(new GridLayout(0, 1));
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.BOTH;

		scrollPane = new JScrollPane(coursePanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//Layout code
		constraints.weightx = 0.0;
		constraints.weighty = 1.0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		sliderPanel.add(scrollPane, constraints);

		button = new JButton("New Course");
		button.addActionListener(this);
		
		//Layout code
		constraints.weightx = 0.0;
		constraints.weighty = 0.0;
		constraints.gridy = 1;
		sliderPanel.add(button, constraints);

		//Layout code
		constraints.weightx = 0.0;
		constraints.weighty = 1.0;
		constraints.gridx = 0;
		constraints.gridy = 0;
		this.getContentPane().add(sliderPanel, constraints);

		//Layout code
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.gridx = 1;
		constraints.gridy = 0;
		//the bar chart will be drawn over this panel
		this.getContentPane().add(new JPanel(), constraints);
		this.setVisible(true);
	}

	public void addSubject(String record) {
		boolean alreadyExists = false;
		for (int i = 0; i < sliders.size(); i++) {
			JSlider slider = sliders.elementAt(i);
			if (slider.getName().equals(record)) {
				alreadyExists = true;
				JOptionPane
						.showMessageDialog(
								null,
								"Warning: Attempt to add new subject with existing name",
								"alert", JOptionPane.ERROR_MESSAGE);
				i = sliders.size(); //exit the loop
			}
		}
		if (!alreadyExists) { //add the slider
			JSlider slider = new JSlider();
			slider.setBackground(Color.white);
			slider.setName(record);
			slider.setValue(50);
			slider.setMinimum(0);
			slider.setMaximum(100);
			slider.setMajorTickSpacing(25);
			slider.setMinorTickSpacing(5);
			slider.setPaintTicks(true);
			slider.setPaintLabels(true);
			slider.setBorder(new TitledBorder(record));
			slider.addChangeListener(this);
			coursePanel.add(slider);
			coursePanel.revalidate();
			sliders.addElement(slider);
			this.setSize(this.xOffset + 50 + this.sliders.size()
					* (this.barWidth + this.barSpacing), (sliders.size() + 1)
					* 70 + this.button.getHeight());
			this.sliderPanel.revalidate();
			this.coursePanel.revalidate();
			this.repaint();
			this.setVisible(true);
		}
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.black);
		g.drawString("Num of Students", this.xOffset - 100, 150);
		g.drawLine(xOffset, yOffset, xOffset, graphHeight + yOffset);
		g.drawLine(xOffset, graphHeight + yOffset, xOffset
				+ (this.barSpacing + barWidth) * this.sliders.size(),
				graphHeight + yOffset);
		for (int i = 0; i < sliders.size(); i++) {
			JSlider record = sliders.elementAt(i);
			g.setColor(Color.blue);
			g.fillRect(this.xOffset + (i + 1) * this.barSpacing + i
					* this.barWidth, this.yOffset + this.graphHeight
					- this.barHeight + 2 * (this.maxValue - record.getValue()),
					this.barWidth, 2 * record.getValue());
			g.setColor(Color.red);
			g.drawString(record.getName(), this.xOffset + (i + 1)
					* this.barSpacing + i * this.barWidth, this.yOffset
					+ this.graphHeight + 20);
		}
	}

	// Called when "New Course" button is pressed
	public void actionPerformed(ActionEvent arg0) {
		String input = JOptionPane
				.showInputDialog("Please enter the new subject name:");
		if (input != null)
			this.addSubject(input);
	}

	// Called when the value of a slider changes
	public void stateChanged(ChangeEvent arg0) {
		this.repaint();
	}

	// Frame contents
	private JPanel sliderPanel;
	private JPanel coursePanel;
	protected Vector<JSlider> sliders;
	private JScrollPane scrollPane;
	private JButton button;

	// Layout Variables 
	private final int xOffset = 350;
	private final int yOffset = 30;
	private final int graphHeight = 210;
	private final int maxValue = 100;
	private final int barHeight = 200;
	private final int barWidth = 50;
	private final int barSpacing = 20;

	public static void main(String[] args) {
		CourseViewer viewer = new CourseViewer();
		viewer.addSubject("Physics");
		viewer.addSubject("Chemistry");
		viewer.addSubject("Biology");
	}
}