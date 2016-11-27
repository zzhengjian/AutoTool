package com.gd.pogen;

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.openqa.selenium.WebDriver;

import com.gd.common.Property;
import com.gd.common.Utils;
import com.gd.driver.Driver;

import java.awt.Toolkit;

import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class POGen {

	private JFrame frame;
	private JTextField filePathTextField;
	private JTextField fileNameTextField;
	private JLabel lblFilename;

	private JButton btnRun;
	private JLabel lblPageName;
	private JTextField tfPageName;
	private JButton btnGetPageName;
	private JTextField parentField;
	private JLabel lblParentnode;
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
				
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(POGen.class.getResource("/com/gd/resources/se.png")));
		frame.setBounds(100, 100, 450, 277);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		btnRun = new JButton("Run");
		btnRun.setToolTipText("Hit me to generate Page file for whole webpage");
		btnRun.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Property.SaveToPath = filePathTextField.getText();
				Property.url = Driver.getWebDriver().getCurrentUrl();
				Property.pageFileName = tfPageName.getText();
				new ElementGenerator().GeneratePageObject(Driver.getWebDriver());
			}
		});
		
		filePathTextField = new JTextField();
		filePathTextField.setColumns(10);
		filePathTextField.setText(Paths.get(Property.DefaultPath, "/pages/").toString());
		
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
				String pageName = PageHelper.generatePageNameWithUrl(Driver.getWebDriver().getCurrentUrl());
				tfPageName.setText(pageName);
				String fileName = Utils.getPageNameFromUrl(Driver.getWebDriver().getCurrentUrl());
				fileNameTextField.setText(fileName);
			}
		});
		btnGetPageName.setToolTipText("Generate Page Name from current URL");
		
		lblParentnode = new JLabel("ParentNode");
		
		parentField = new JTextField();
		parentField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Property.parentNodeLocator = parentField.getText();
			}
		});
		parentField.setColumns(10);
		parentField.setText(Property.parentNodeLocator);
		
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(btnRun, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(10)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblSaveto, GroupLayout.PREFERRED_SIZE, 46, GroupLayout.PREFERRED_SIZE)
										.addGap(16))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblFilename, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED)))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(filePathTextField, GroupLayout.PREFERRED_SIZE, 188, GroupLayout.PREFERRED_SIZE)
									.addComponent(fileNameTextField, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)))
							.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
								.addContainerGap()
								.addComponent(lblPageName)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(tfPageName, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnGetPageName)))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblParentnode)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(parentField, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))
					.addGap(368))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(46)
							.addComponent(lblSaveto))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(39)
							.addComponent(filePathTextField, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPageName)
						.addComponent(tfPageName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnGetPageName))
					.addGap(16)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFilename)
						.addComponent(fileNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblParentnode)
						.addComponent(parentField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(45)
					.addComponent(btnRun)
					.addContainerGap())
		);
		frame.getContentPane().setLayout(groupLayout);
	}
}
