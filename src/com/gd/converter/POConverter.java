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
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.gd.driver.AutoTool;
import com.gd.serializer.PageParser;
import javax.swing.JTextField;
import javax.swing.JLabel;

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
	/**
	 * Create the panel.
	 */
	public POConverter() {		

		initComponents();
		CreateEvents();

	}

	private void initComponents() {
		fileScrollPane = new JScrollPane();
		
		btnOpen = new JButton("Open");
		
		btnConvert = new JButton("Convert");				
		fileList = new JList<String>();
		fileListModel = new DefaultListModel<String>();
		fileList.setModel(fileListModel);
		fileScrollPane.setViewportView(fileList);
		
		btnSelectFolder = new JButton("select folder");
		
		tfSkin = new JTextField();
		tfSkin.setText(PageParser.Skin);
		tfSkin.setColumns(10);
		
		lblSkin = new JLabel("Skin");

		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(24)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSelectFolder)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(10)
								.addComponent(lblSkin)
								.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(tfSkin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addComponent(fileScrollPane, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(btnOpen)
						.addComponent(btnConvert))
					.addGap(162))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(14)
					.addComponent(btnSelectFolder)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnOpen)
							.addGap(58)
							.addComponent(btnConvert))
						.addComponent(fileScrollPane, GroupLayout.PREFERRED_SIZE, 204, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(tfSkin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSkin))
					.addContainerGap(87, Short.MAX_VALUE))
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
				for(File f : filelist)
				{						
					try {
						new PageParser().parse(f.getAbsolutePath());
					} catch (Exception e) {
						//System.err.println(f.getAbsolutePath());
						invalidFiles.add(f.getAbsolutePath());
						e.printStackTrace();
					}
				}
				for(String file : invalidFiles)
				{
					System.out.println(file);
				}
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
