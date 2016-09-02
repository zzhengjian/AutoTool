package com.gd.loginhelper;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gd.autofill.AutoFillPanel;
import com.gd.converter.POConverter;

public class TestFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6152760563187565895L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame("LoginPanel");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public static void startLoginPanel() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame("LoginPanel");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void startAutoFillPanel() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame("AutoFillPanel");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void startConverterPanel() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestFrame frame = new TestFrame("POConverter");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public TestFrame(String panel) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(TestFrame.class.getResource("/com/gd/resources/login.jpg")));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 545, 528);
		if(panel.equals("LoginPanel"))
			contentPane = new LoginPanel();
		else if(panel.equals("AutoFillPanel"))		
			contentPane = new AutoFillPanel();
		else if(panel.equals("POConverter"))	
			contentPane = new POConverter();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

	}

}
