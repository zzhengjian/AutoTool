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

import com.gd.driver.Driver;
import com.gd.driver.Property;
import com.gd.driver.Utils;

import java.awt.Toolkit;

import javax.swing.LayoutStyle.ComponentPlacement;

public class POGen {

	private JFrame frame;
	private JTextField filePathTextField;
	private JTextField fileNameTextField;
	private JLabel lblFilename;

	
	
	private WebDriver driver;
	private JButton btnRun;
	private JLabel lblPageName;
	private JTextField tfPageName;
	private JButton btnGetPageName;
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		driver = Driver.oWebDriver;
		
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(POGen.class.getResource("/com/gd/resources/se.png")));
		frame.setBounds(100, 100, 450, 277);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		btnRun = new JButton("Run");
		btnRun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Property.SaveToPath = filePathTextField.getText();
				Property.url = driver.getCurrentUrl();
				Property.pageFileName = tfPageName.getText();
				new ElementGenerator().GeneratePageObject(driver);
			}
		});
		
		filePathTextField = new JTextField();
		filePathTextField.setColumns(10);
		
		JLabel lblSaveto = new JLabel("SaveTo");
		
		fileNameTextField = new JTextField();
		fileNameTextField.setColumns(10);
		
		lblFilename = new JLabel("FileName");
		
		lblPageName = new JLabel("PageName");
		
		tfPageName = new JTextField();
		tfPageName.setColumns(10);
		
		btnGetPageName = new JButton("Get");
		btnGetPageName.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String pageName = PageHelper.generatePageNameWithUrl(driver.getCurrentUrl());
				tfPageName.setText(pageName);
				String fileName = Utils.getPageNameFromUrl(driver.getCurrentUrl());
				fileNameTextField.setText(fileName);
			}
		});
		btnGetPageName.setToolTipText("Generate Page Name from current URL");
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(10)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnRun, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblSaveto, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
											.addGap(16))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblFilename, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
											.addPreferredGap(ComponentPlacement.RELATED)))
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(filePathTextField, GroupLayout.PREFERRED_SIZE, 278, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addPreferredGap(ComponentPlacement.UNRELATED)
											.addComponent(fileNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblPageName)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(tfPageName, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(btnGetPageName)))
					.addContainerGap(92, Short.MAX_VALUE))
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
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPageName)
						.addComponent(tfPageName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGetPageName))
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFilename)
						.addComponent(fileNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(35)
					.addComponent(btnRun))
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
