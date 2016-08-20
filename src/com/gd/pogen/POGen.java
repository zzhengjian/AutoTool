package com.gd.pogen;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.openqa.selenium.WebDriver;

import com.browser.debug.Driver;
import com.browser.debug.Property;

public class POGen {

	private JFrame frame;
	private JTextField filePathTextField;
	private JTextField fileNameTextField;
	private JLabel lblFilename;

	
	
	public WebDriver oWebDriver;
	private JButton btnRun;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POGen window = new POGen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void startPOGen() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POGen window = new POGen();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the application.
	 */
	public POGen() {
		initialize();
		Property.SetUp();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 277);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		btnRun = new JButton("Run");
		btnRun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				WebDriver driver = Driver.oWebDriver;
				Property.SaveToPath = filePathTextField.getText();
				Property.url = driver.getCurrentUrl();
				Property.pageFileName = fileNameTextField.getText();
				new ElementGenerator().GeneratePageObject(driver);
			}
		});
		
		filePathTextField = new JTextField();
		filePathTextField.setColumns(10);
		
		JLabel lblSaveto = new JLabel("SaveTo");
		
		fileNameTextField = new JTextField();
		fileNameTextField.setColumns(10);
		
		lblFilename = new JLabel("FileName");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(10)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblSaveto, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
							.addGap(16)
							.addComponent(filePathTextField, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblFilename, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(fileNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnRun, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(39)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(7)
							.addComponent(lblSaveto))
						.addComponent(filePathTextField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
					.addGap(11)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(6)
							.addComponent(lblFilename))
						.addComponent(fileNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(70)
					.addComponent(btnRun))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
