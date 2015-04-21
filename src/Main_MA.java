import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.ButtonGroup;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

//	Main_MA.java by Kyle Wolff and Brandon VanderMey on 4/21/2015 

public class Main_MA extends JApplet implements ActionListener
{
	public static JRadioButton rdoFull;
	public static JRadioButton rdoTwo;
	public static JRadioButton rdoWeek;
	public static JTextField txtLength;
	public static JTextField txtWidth;
	
	public void init()
	{
		setSize(500, 180);
		setLayout(null);
		
		JLabel lblLength = new JLabel("Lawn Length:");
		lblLength.setBounds(new Rectangle(new Point(20, 20), lblLength.getPreferredSize()));
		
		JLabel lblWidth = new JLabel("Lawn Width:");
		lblWidth.setBounds(new Rectangle(new Point(20, 60), lblWidth.getPreferredSize()));
		
		txtLength = new JTextField(5);
		txtLength.setBounds(new Rectangle(new Point(120, 20), txtLength.getPreferredSize()));
		
		txtWidth = new JTextField(5);
		txtWidth.setBounds(new Rectangle(new Point(120, 60), txtWidth.getPreferredSize()));
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(new Rectangle(new Point(200, 100), btnSubmit.getPreferredSize()));
		
		ButtonGroup btnGp = new ButtonGroup();
		
		rdoFull = new JRadioButton("Full payment ($0 Service Charge)");
		rdoFull.setBounds(new Rectangle(new Point(200, 20), rdoFull.getPreferredSize()));
		
		rdoTwo = new JRadioButton("Two payments ($5 Service Charge)");
		rdoTwo.setBounds(new Rectangle(new Point(200, 40), rdoTwo.getPreferredSize()));
		
		rdoWeek = new JRadioButton("Weekly payments ($3 Service Charge");
		rdoWeek.setBounds(new Rectangle(new Point(200, 60), rdoWeek.getPreferredSize()));
		
		btnGp.add(rdoFull);
		btnGp.add(rdoTwo);
		btnGp.add(rdoWeek);
		
		btnSubmit.addActionListener(this);
		
		add(lblLength);
		add(lblWidth);
		add(txtLength);
		add(txtWidth);
		add(btnSubmit);
		add(rdoFull);
		add(rdoTwo);
		add(rdoWeek);
	}
	
	public void actionPerformed(ActionEvent e)
	{
		CalculatePrice(txtLength.getText(), txtWidth.getText());
	}
	
	public static void CalculatePrice(String length, String width)
	{
		DecimalFormat currencyFormat = new DecimalFormat("'$'#,###,###.00");
		
		float squareFeet = Float.parseFloat(length) * Float.parseFloat(width);
		float mowingFee = 0.0f;
		float payment = 0.0f;
		int numberPayments = 1;
		
		
		if (squareFeet < 4000.0f)
			mowingFee = 25.0f;
		
		else if (squareFeet >= 4000.0f && squareFeet < 6000.0f)
			mowingFee = 35.0f;
		
		else if (squareFeet >= 6000.0f)
			mowingFee = 50.0f;
		
		//	Can't use '==' operator because it doesn't check the value stored in the variable.  This returns true or false if 'paymentPlan' equals '1'.
		if (rdoFull.isSelected())
		{
			payment = mowingFee * 20;
			numberPayments = 1;
		}
		
		else if (rdoTwo.isSelected())
		{
			payment = mowingFee * 20 / 2 + 5.0f;
			numberPayments = 2;
		}
		else if (rdoWeek.isSelected())
		{
			payment = mowingFee + 3.0f;
			numberPayments = 20;
		}
		
		JOptionPane.showMessageDialog(null, "Number of payments: " + numberPayments + "\nCost per payment: " + currencyFormat.format(payment) + "\nSeason total: " + currencyFormat.format(payment * numberPayments), "Totals", JOptionPane.PLAIN_MESSAGE);
	}
}
