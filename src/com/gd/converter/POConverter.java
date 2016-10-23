package com.gd.converter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;


import com.gd.driver.AutoTool;
import com.gd.pages.serializer.PageParser;
import com.gd.steps.serializer.StatementParser;
import com.gd.pages.serializer.Page;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class POConverter extends JPanel {
	private JButton btnOpen;
	private JList<String> fileList;
	private JScrollPane fileScrollPane;
	private DefaultListModel<String> fileListModel;
	private JButton btnConvert;

	private ArrayList<File> filelist = new ArrayList<File>();
	private JButton btnSelectFolder;
	private JTextField tfSkin;
	private JLabel lblSkin;
	
	private boolean isPageSelected = true;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnPages;
	private JRadioButton rdbtnStatements;
	/**
	 * Create the panel.
	 */
	public POConverter() {		

		initComponents();
		CreateEvents();

	}

	private void initComponents() {
		fileScrollPane = new JScrollPane();
		
		btnOpen = new JButton("Open Files");
		
		btnConvert = new JButton("Convert");				
		fileList = new JList<String>();
		fileListModel = new DefaultListModel<String>();
		fileList.setModel(fileListModel);
		fileScrollPane.setViewportView(fileList);
		
		btnSelectFolder = new JButton("Open Folder");
		
		tfSkin = new JTextField();
		tfSkin.setText(PageParser.Skin);
		tfSkin.setColumns(10);
		
		lblSkin = new JLabel("Skin");
		
		rdbtnPages = new JRadioButton("pages");
		buttonGroup.add(rdbtnPages);
		
		rdbtnStatements = new JRadioButton("statements");

		buttonGroup.add(rdbtnStatements);

		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addGap(10)
							.addComponent(lblSkin, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(tfSkin, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
						.addComponent(fileScrollPane, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(btnSelectFolder, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(btnOpen, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnPages)
								.addComponent(rdbtnStatements)))
						.addComponent(btnConvert))
					.addGap(25))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(19)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSelectFolder)
								.addComponent(rdbtnPages))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(btnOpen)
								.addComponent(rdbtnStatements))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(btnConvert))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(fileScrollPane, GroupLayout.PREFERRED_SIZE, 262, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED, 33, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfSkin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSkin))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		
	}

	
	private void CreateEvents() {
		btnOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser fc;
				File[] files;
				fileListModel.clear();
				filelist.clear();
				fc = new JFileChooser(AutoTool.tempPath);
				fc.setMultiSelectionEnabled(true);
				fc.setVisible(true);				
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) 	
				{
					files = fc.getSelectedFiles();
					
					for(File f : files)
					{									
						fileListModel.addElement(f.getName());
						filelist.add(f);
					}
				}				
				
			}
		});
		
		btnConvert.addActionListener(new ActionListener() {			

			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> invalidFiles = new ArrayList<String>();
				PageParser.Skin = tfSkin.getText();
				
				if(isPageSelected)
				{
					for(File f : filelist)
					{
						if(f.getName().equals(Page.SharedElements))
						{
							try {
								new PageParser().parse(f.getAbsolutePath());
							} catch (Exception e) {
								invalidFiles.add(f.getAbsolutePath());
								e.printStackTrace();
							}
						}
					}
					for(File f : filelist)
					{
						if(f.getName().equals(Page.SharedElements))
						{
							continue;
						}
						try {
							new PageParser().parse(f.getAbsolutePath());
						} catch (Exception e) {
							invalidFiles.add(f.getAbsolutePath());
							e.printStackTrace();
						}
					}
				}
				else
				{
					for(File f : filelist)
					{
						try {
							StatementParser parser = new StatementParser(f.getAbsolutePath());
							StatementParser.project = tfSkin.getText();
							parser.processSteps();
							parser.convertCategory();
						} catch (Exception e) {
							invalidFiles.add(f.getAbsolutePath());
							e.printStackTrace();
						}

					}
				}
				
				
				for(String file : invalidFiles)
				{
					System.out.println(file);
				}
			}

		});
		
		rdbtnStatements.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				isPageSelected = false;
				lblSkin.setText("Project");
			}
		});
		
		rdbtnPages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				isPageSelected = true;
				lblSkin.setText("Skin");
			}
		});
		
		btnSelectFolder.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fc;
				File fileOrDir;
				fileListModel.clear();
				filelist.clear();
				fc = new JFileChooser(AutoTool.tempPath);
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fc.setVisible(true);				
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) 	
				{
					fileOrDir = fc.getSelectedFile();
					if(fileOrDir.isDirectory())
					{
						listFilesAndFilesSubDirectories(fileOrDir);
					}
					for(File f : filelist)
					{									
						fileListModel.addElement(f.getName());
					}
				}		
			}		
			

			private void listFilesAndFilesSubDirectories(File fileOrDir) {
		
			        //get all the files from a directory
			        File[] fList = fileOrDir.listFiles();
			        for (File file : fList){
			            if (file.isFile()){
			            	filelist.add(file);
			            } 
			            else if (file.isDirectory()){
			                listFilesAndFilesSubDirectories(file);
			            }
			        }
				
			}
		});
		
	}
}
