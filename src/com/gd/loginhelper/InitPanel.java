package com.gd.loginhelper;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.gd.common.Configuration;
import com.gd.common.Property;
import com.gd.pogen.POGen;
import com.gd.steps.serializer.DocEditor;

public class InitPanel extends JPanel {

	private JMenuBar menuBar;
	private JMenu mnPlugins;
	private JMenuItem mntmCustomeHelper;
	private JMenuItem mntmAutoFill;
	private JMenuItem mntmPoGen;
	private JMenuItem mntmPageConverter;
	private JMenuItem mntmDocEditor;
	private JMenuItem mntmAutotool;

	/**
	 * Create the panel.
	 */
	public InitPanel() {
		setLayout(new BorderLayout(0, 0));
		Property.SetUp();
		loadCucumberWorkspace();
		createMenuComponents();
		createMenuEvents();

	}
	

	private void loadCucumberWorkspace() {

		String workspace =  JOptionPane.showInputDialog("your cucumber location",Configuration.CucumberWorkspace);
		Configuration.CucumberWorkspace = workspace;	
		Configuration.saveWorkspace();
	}
	
	private void createMenuComponents() {
		
		menuBar = new JMenuBar();
		add(menuBar, BorderLayout.NORTH);
		
		mnPlugins = new JMenu("Plug-ins");
		menuBar.add(mnPlugins);
		
		mntmCustomeHelper = new JMenuItem("CustomerHelper");
		
		mnPlugins.add(mntmCustomeHelper);
		
		mntmAutotool = new JMenuItem("AutoTool");
		
		mnPlugins.add(mntmAutotool);
		
		mntmAutoFill = new JMenuItem("Auto Fill");
		
		mnPlugins.add(mntmAutoFill);
		
		mntmPoGen = new JMenuItem("POGen");
				
		mnPlugins.add(mntmPoGen);
		
		mntmPageConverter = new JMenuItem("PageConverter");
						
		mnPlugins.add(mntmPageConverter);
		
		mntmDocEditor = new JMenuItem("Doc Editor");
		
		mnPlugins.add(mntmDocEditor);		
		
	}

	private void createMenuEvents() {
		mntmCustomeHelper.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent paramMouseEvent) {
				TestFrame.startLoginPanel();
			}
		});
		
		mntmAutoFill.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent paramMouseEvent) {
				TestFrame.startAutoFillPanel();
			}
		});
		
		mntmPoGen.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				POGen.startPOGen();
			}
		});
		
		mntmPageConverter.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				TestFrame.startConverterPanel();
			}
		});
		
		mntmAutotool.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				TestFrame.startAutoTool();
			}
		});
		
		mntmDocEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DocEditor();
			}
		});
		
	}
}
