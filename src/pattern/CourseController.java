package pattern;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 *	Updates the Course Data when the user moves a Slider
 */
public class CourseController extends JPanel implements SubjectObserver,
		ChangeListener, ActionListener {

	public CourseController(CourseData subjectData) {
		this.subjectData = subjectData;
		this.sliders = new Vector();
		this.setLayout(new GridBagLayout());
		this.setBackground(Color.white);
		subjectPanel = new JPanel();
		subjectPanel.setBorder(new TitledBorder("Courses"));
		subjectPanel.setLayout(new GridLayout(0, 1));
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		subjectData.attach(this);
		Vector state = subjectData.getSubjectData();
		for (int i = 0; i < state.size(); i++)
			this.addSubject((CourseRecord) state.elementAt(i));
		JScrollPane scrollPane = new JScrollPane(subjectPanel,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JButton button = new JButton("New Courses");
		button.addActionListener(this);
		c.weightx = 0.5;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		this.add(scrollPane, c);
		c.weightx = 0.5;
		c.weighty = 0;
		c.gridy = 1;
		this.add(button, c);
	}

	public void addSubject(CourseRecord record) {
		JSlider slider = new JSlider();
		slider.setBackground(Color.white);
		slider.setName(record.getSubject());
		slider.setValue(record.getNumOfStudents());
		slider.setMinimum(0);
		slider.setMaximum(100);
		slider.setMajorTickSpacing(25);
		slider.setMinorTickSpacing(5);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.setBorder(new TitledBorder(record.getSubject()));
		slider.addChangeListener(this);
		subjectPanel.add(slider);
		subjectPanel.revalidate();
		sliders.addElement(slider);
	}

	public void update(Object o) {
		CourseData data = (CourseData) o;
		Vector state = data.getSubjectData();
		for (int i = sliders.size(); i < state.size(); i++) {
			this.addSubject((CourseRecord) state.elementAt(i));
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		String input = JOptionPane
				.showInputDialog("Please enter the new course name:");
		if (input != null)
			subjectData.addSubjectRecord(new CourseRecord(input, 50));
	}

	public void stateChanged(ChangeEvent arg0) {
		JSlider slider = (JSlider) arg0.getSource();
		subjectData.changeSubjectRecord(slider.getName(), slider.getValue());
	}

	public static void main(String[] args) {
		CourseData data = new CourseData();
		data.addSubjectRecord(new CourseRecord("Physics", 50));
		data.addSubjectRecord(new CourseRecord("Chemistry", 50));
		data.addSubjectRecord(new CourseRecord("Biology", 50));

		CourseController controller = new CourseController(data);

		BarChartObserver bar = new BarChartObserver(data);
		JScrollPane scrollPane = new JScrollPane(bar,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		JFrame frame = new JFrame("Observer Pattern");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridBagLayout());
		frame.setResizable(false);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.25;
		c.weighty = 1.0;
		c.gridx = 0;
		c.gridy = 0;
		frame.getContentPane().add(controller, c);
		c.weightx = 0.5;
		c.weighty = 1.0;
		c.gridx = 1;
		c.gridy = 0;
		frame.getContentPane().add(scrollPane, c);
		frame.pack();
		frame.show();
	}

	private CourseData subjectData;
	private Vector sliders;
	private JPanel subjectPanel;
}
