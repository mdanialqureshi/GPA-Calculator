import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

public class GPA implements ActionListener {

	public String name;
	public Double scale;
	public List<Double> grades = new ArrayList<Double>();
	public List<Double> credits = new ArrayList<Double>();
	public Double gradePointAverage;
	public JFrame mainFrame;
	private JPanel mainPanel = new JPanel();
	private JLabel greeting = new JLabel("Welcome to the GPA Calculator!");
	private JTextField input1;
	private JTextField creditInput1;
	private JTextField gpaScale;
	private JLabel outputLabel;
	private JButton done;
	private JButton reset;
	private JLabel errorLabel;
	private JButton instructions; 
	private JLabel instructionLabel;
	private JButton hide;

	GPA() {
		mainFrame = new JFrame("GPA Calculator");
		greeting.setFont(new Font("Arial", Font.BOLD, 20));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setSize(500, 500);
		mainFrame.setResizable(true);
		mainFrame.setLocationRelativeTo(null);
		greeting.setBounds(100, 10, 400, 40);
		greeting.setForeground(Color.blue);
		mainPanel.add(greeting);
		mainFrame.add(mainPanel);
		mainPanel.setLayout(null);
		buildGUI(); // go build rest of GUI after main panels are constructed
	}

	public void setName(String name) {
		if (name != null) {
			this.name = name;
		} else {
			throw new NullPointerException();
		}
	}

	public String getName() {
		if (name != null) {
			return this.name;
		} else {
			throw new NullPointerException();
		}
	}

	public void setGPAScale(Double scale) {
		if (scale != null) {
			this.scale = scale;
		} else {
			throw new NullPointerException();
		}
	}

	public Double getGPAScale() {
		if (scale != null) {
			return scale;
		} else {
			return 0.0;
		}
	}

	private void buildGUI() {
		mainPanel.setBackground(Color.LIGHT_GRAY);
		input1 = new JTextField();
		input1.setBounds(50, 150, 50, 40);
		input1.addActionListener(this);
		mainPanel.add(input1);
		
		// creating GPA scale field
		gpaScale = new JTextField("Enter GPA Scale");
		gpaScale.addFocusListener(new FocusListener() { // input hint functionality for gpaScale input
			public void focusGained(FocusEvent e) {
				gpaScale.setText("");
			}

			public void focusLost(FocusEvent e) {
			}
		});
		
		gpaScale.setBounds(350, 75, 110, 50);
		mainPanel.add(gpaScale);
		gpaScale.addActionListener(this);
		
		creditInput1 = new JTextField();
		creditInput1.setBounds(125, 150, 50, 40);
		creditInput1.addActionListener(this);
		mainPanel.add(creditInput1);
		
		JLabel gradeLabel = new JLabel();
		gradeLabel.setText("Number Grade");
		gradeLabel.setBounds(10, 125, 150, 30);
		gradeLabel.setFont(new Font("Arial", Font.BOLD, 13));
		mainPanel.add(gradeLabel);

		JLabel creditLabel = new JLabel();
		creditLabel.setText("Credits");
		creditLabel.setFont(new Font("Arial", Font.BOLD, 13));
		creditLabel.setBounds(125, 125, 100, 30);
		mainPanel.add(creditLabel);
		
		outputLabel = new JLabel();
		outputLabel.setText("");
		outputLabel.setFont(new Font("Arial", Font.BOLD, 17));
		outputLabel.setForeground(Color.red);
		outputLabel.setBounds(200, 150, 250, 40);
		mainPanel.add(outputLabel);

		done = new JButton("DONE");
		done.setBounds(50, 200, 100, 40);
		done.setForeground(Color.RED);
		done.addActionListener(this);
		mainPanel.add(done);
		
		reset = new JButton("RESET");
		reset.setBounds(150,200,100,40);
		reset.setForeground(Color.red);
		reset.addActionListener(this);
		mainPanel.add(reset);
		
		errorLabel = new JLabel();
		errorLabel.setText("Please fill in all provided boxes above.");
		errorLabel.setFont(new Font("Arial", Font.BOLD, 17));
		errorLabel.setForeground(Color.red);
		errorLabel.setBounds(100, 250, 350, 50);
		errorLabel.setVisible(false);
		mainPanel.add(errorLabel);
		
		JLabel scaleLabel = new JLabel();
		scaleLabel.setText("GPA Scale:");
		scaleLabel.setFont(new Font("Arial",Font.BOLD,16));
		scaleLabel.setBounds(250,80,200,40);
		mainPanel.add(scaleLabel);
		
		instructions = new JButton("HELP");
		instructions.setForeground(Color.red);
		instructions.setBounds(250,200,100,40);
		instructions.addActionListener(this);
		mainPanel.add(instructions);
		
		instructionLabel = new JLabel();
		instructionLabel.setText("<html>1.) Fill in GPA Scale." + "<br/>2.) Fill in Grades and corresponding " +
		"Credits one by one and press enter." + "<br/>3.) Click done once all grades have been entered." + 
				"<br/>4.) Click Reset if you wish to start over.</html>");
		instructionLabel.setFont(new Font("Arial",Font.BOLD,14));
		instructionLabel.setBounds(50,300,300,100);
		instructionLabel.setVisible(false);
		mainPanel.add(instructionLabel);
		
		hide = new JButton();
		hide.setText("Hide Instructions");
		hide.setBounds(50, 410, 150, 40);
		hide.setForeground(Color.red);
		hide.addActionListener(this);
		hide.setVisible(false);
		mainPanel.add(hide);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		boolean holder = false;// for gpaScale filled check
		Double credit1 = 0.0;

		if (source == done) { //done button functionality 
			outputLabel.setText("Your GPA is " + gpaCalculation() + ", on a " + getGPAScale() + " scale.");
			holder = true;
		}
		
		if(source == reset) { //reset button functionality
			this.grades.clear();
			this.credits.clear();
			outputLabel.setText("");
			errorLabel.setText("");
		}
		
		if(source == instructions) { //help button functionality
			instructionLabel.setVisible(true);
			hide.setVisible(true);
		}
		if(source == hide) { //hide button functionality
			hide.setVisible(false);
			instructionLabel.setVisible(false);
		}
		
		
		if (gpaScale.getText() != null && gpaScale.getText().length() > 0 && gpaScale.getText().matches("[0-9]+")
				|| gpaScale.getText().matches("^-?\\d*\\.\\d+$")) {
			holder = true;
			setGPAScale(Double.parseDouble(gpaScale.getText()));
			gpaScale.setText("" + this.scale);
			gpaScale.setEnabled(false);
			gpaScale.setEditable(false);
		} else {
			errorLabel.setText("Please fill in all provided boxes above.");
			errorLabel.setVisible(true);
		}
			
			if (holder && creditInput1.getText() != null && creditInput1.getText().length() > 0
					&& input1.getText() != null && input1.getText().length() > 0) { // check if all required inputs are
																					// filled in or not
				errorLabel.setText("");
				credit1 = Double.parseDouble(creditInput1.getText());
				creditInput1.setText("");

				Double firstGrade = Double.parseDouble(input1.getText());
				this.grades.add(firstGrade);
				this.credits.add(credit1);
				input1.setText("");
			} else {
				errorLabel.setText("Please fill in all provided boxes above.");
				errorLabel.setVisible(true);
			}

	}

	public Double gpaCalculation() {
		double gcSum = 0;
		double creditSum = 0;
		int counter = 0;
		for (double j : credits) {
			creditSum = creditSum + j;
			gcSum = gcSum + grades.get(counter) * j;
			counter++;
		}
		this.gradePointAverage = gcSum / creditSum;
		return (double) Math.round(gradePointAverage * 100) / 100;
	}

}
