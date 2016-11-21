package com.gd.converter;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gd.common.Configuration;
import com.gd.common.ConverterSettings;
import com.gd.pages.serializer.Page;
import com.gd.pages.serializer.PageParser;
import com.gd.steps.doc.Helper;
import com.gd.steps.serializer.StatementParser;
import com.google.gson.JsonElement;

public class POConverter extends JPanel {
	private static final Logger logger = LoggerFactory.getLogger(POConverter.class);
	private JButton btnOpen;
	private JList<String> fileList;
	private JScrollPane fileScrollPane;
	private DefaultListModel<String> fileListModel;
	private JButton btnConvert;

	private ArrayList<File> filelist = new ArrayList<File>();
	private JButton btnSelectFolder;
	
	private JTextField selectedField;
	private JTextField EndPointTextField;
	private JComboBox<String> projectComboBox;
	
	private DefaultComboBoxModel<String> projectModel;
	private JTextField wokspaceField;
	private JTabbedPane tabbedPane;
	private JPanel statementPanel;
	private JPanel pagePanel;
	private JComboBox<String> skinComboBox;
	private DefaultComboBoxModel<String> skinModel;
	
	private JsonElement projects;
	private JsonElement skins;
	
	private boolean isOnPage = false;
	private Thread converting;
	private JButton btnStop;
	
	/**
	 * Create the panel.
	 */
	public POConverter() {		
		
		setProperEndPoint();
		initComponents();
		CreateEvents();

	}

	private void setProperEndPoint() {
		String endpoint =  JOptionPane.showInputDialog("set your endpoint", ConverterSettings.EndPoint);
		if(endpoint!=null && !endpoint.equals("")){
			ConverterSettings.EndPoint = endpoint;
		}
		ConverterSettings.saveEndPoint();
	}

	private void initComponents() {		
		
		fileScrollPane = new JScrollPane();
		
		btnOpen = new JButton("Select Files");
		
		btnConvert = new JButton("Convert");				
		fileList = new JList<String>();
		fileListModel = new DefaultListModel<String>();
		fileList.setModel(fileListModel);
		fileScrollPane.setViewportView(fileList);
		
		btnSelectFolder = new JButton("Select Folder");
		
		setLayout(new BorderLayout(0, 0));
		
		JPanel topPanel = new JPanel();
		add(topPanel, BorderLayout.NORTH);
		
		projectComboBox = new JComboBox<String>();
		
		projectModel = new DefaultComboBoxModel<String>();
		projects = Helper.getProjects();
		for(JsonElement project : projects.getAsJsonArray())
		{
			projectModel.addElement(project.getAsJsonObject().get("projectName").getAsString());
		}
				
		projectComboBox.setModel(projectModel);
		
		JLabel lblProject = new JLabel("project");
		
		JLabel label = new JLabel("");
		
		JLabel lblEndPoint = new JLabel("EndPoint");
		
		EndPointTextField = new JTextField();
		EndPointTextField.setText(ConverterSettings.EndPoint);
		EndPointTextField.setColumns(10);
		
		wokspaceField = new JTextField();
		wokspaceField.setText(Configuration.CucumberWorkspace);
		wokspaceField.setColumns(10);
		
		JLabel lblWorkspace = new JLabel("workspace");
		GroupLayout gl_topPanel = new GroupLayout(topPanel);
		gl_topPanel.setHorizontalGroup(
			gl_topPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblProject, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWorkspace)
						.addComponent(lblEndPoint))
					.addGap(28)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(EndPointTextField)
						.addComponent(projectComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(wokspaceField, GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE))
					.addGap(42))
		);
		gl_topPanel.setVerticalGroup(
			gl_topPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_topPanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblWorkspace)
						.addComponent(wokspaceField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(projectComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblProject, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_topPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblEndPoint, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addComponent(EndPointTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
		);
		topPanel.setLayout(gl_topPanel);
		
		JPanel bottomPanel = new JPanel();
		add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new BorderLayout(0, 0));
		bottomPanel.add(btnConvert,BorderLayout.EAST);
		
		JLabel label_2 = new JLabel("");
		bottomPanel.add(label_2, BorderLayout.NORTH);
		
		btnStop = new JButton("Stop");

		bottomPanel.add(btnStop, BorderLayout.WEST);
		
		JPanel centralPanel = new JPanel();
		add(centralPanel, BorderLayout.CENTER);
		centralPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel centralTopPanel = new JPanel();
		centralPanel.add(centralTopPanel, BorderLayout.NORTH);
		centralTopPanel.setLayout(new BorderLayout(0, 0));
		centralTopPanel.add(btnSelectFolder, BorderLayout.EAST);
		centralTopPanel.add(btnOpen, BorderLayout.WEST);
		JLabel label_1 = new JLabel();
		label_1.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		label_1.setBounds(new Rectangle(0, 0, 0, 4));
		centralTopPanel.add(label_1, BorderLayout.NORTH);
		
		selectedField = new JTextField();
		centralTopPanel.add(selectedField, BorderLayout.CENTER);
		selectedField.setColumns(10);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		
		centralPanel.add(tabbedPane, BorderLayout.CENTER);
		
		statementPanel = new JPanel();
		tabbedPane.addTab("Statement", null, statementPanel, null);
		
		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_statementPanel = new GroupLayout(statementPanel);
		gl_statementPanel.setHorizontalGroup(
			gl_statementPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_statementPanel.createSequentialGroup()
					.addGap(24)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 471, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(29, Short.MAX_VALUE))
		);
		gl_statementPanel.setVerticalGroup(
			gl_statementPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_statementPanel.createSequentialGroup()
					.addContainerGap(80, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 186, GroupLayout.PREFERRED_SIZE)
					.addGap(29))
		);
		
		JTextPane pageLogTextPane = new JTextPane();
		scrollPane.setViewportView(pageLogTextPane);
		statementPanel.setLayout(gl_statementPanel);
		
		pagePanel = new JPanel();
		tabbedPane.addTab("Page", null, pagePanel, null);

		JLabel lblSkin_1 = new JLabel("Skin");
		
		skinComboBox = new JComboBox<String>();
		skinModel = new DefaultComboBoxModel<String>();
		skinComboBox.setModel(skinModel);
				
		skinComboBox.setEditable(true);
		
		JScrollPane logScrollPane = new JScrollPane();
		GroupLayout gl_pagePanel = new GroupLayout(pagePanel);
		gl_pagePanel.setHorizontalGroup(
			gl_pagePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pagePanel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_pagePanel.createParallelGroup(Alignment.LEADING)
						.addComponent(logScrollPane, GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
						.addGroup(gl_pagePanel.createSequentialGroup()
							.addComponent(lblSkin_1)
							.addGap(18)
							.addComponent(skinComboBox, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_pagePanel.setVerticalGroup(
			gl_pagePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pagePanel.createSequentialGroup()
					.addGap(30)
					.addGroup(gl_pagePanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(skinComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSkin_1))
					.addGap(53)
					.addComponent(logScrollPane, GroupLayout.PREFERRED_SIZE, 194, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JTextPane statementLogTextPane = new JTextPane();
		logScrollPane.setViewportView(statementLogTextPane);
		pagePanel.setLayout(gl_pagePanel);
		
		//refactor this layout
//		add(lblSkin);
//		add(tfSkin);
//		add(fileScrollPane);
//		add(btnSelectFolder);
//		add(btnOpen);
//		add(rdbtnPages);
//		add(rdbtnStatements);
//		add(btnConvert);
		
	}

	
	private void CreateEvents() {
		btnOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				JFileChooser fc;
				File[] files;
				fileListModel.clear();
				filelist.clear();
				fc = new JFileChooser(Configuration.CucumberWorkspace);
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
				
				
				converting = new Thread(){
					private ArrayList<String> invalidFiles = new ArrayList<String>();
					@Override
					public void run(){
						
						Stack<File> fileStack = new Stack<File>();
						
						if(isOnPage){
							for(File f : filelist)
							{
								if(!f.getName().contains(Page.SharedElements)){
									fileStack.push(f);
								}
							}
							for(File f : filelist)
							{
								if(f.getName().contains(Page.SharedElements)){
									fileStack.push(f);
								}
							}	
							String skinName = skinComboBox.getSelectedItem().toString();
							String skinid = null;
							
							for(JsonElement skin : skins.getAsJsonArray())
							{
								if(skin.getAsJsonObject().get("skinName").getAsString().equals(skinName))
								{
									skinid = skin.getAsJsonObject().get("id").getAsString();
									break;
								}
							}
							PageParser.Skin = skinid;
							
						}
						else{
							
							for(File f : filelist)
							{
								fileStack.push(f);
							}	
							
							String projectname = projectComboBox.getSelectedItem().toString();
							String projectid = null;
							
							for(JsonElement project : projects.getAsJsonArray())
							{
								if(project.getAsJsonObject().get("projectName").getAsString().equals(projectname))
								{
									projectid = project.getAsJsonObject().get("id").getAsString();
									break;
								}
							}
							StatementParser.project = projectid;
							
						}
						btnConvert.setText("Converting");
						while(!Thread.currentThread().isInterrupted() && !fileStack.isEmpty())
						{
							
							File f = fileStack.pop();
							if(isOnPage){									
								try {
									new PageParser().parse(f.getAbsolutePath());
								} catch (Exception e) {
									invalidFiles.add(f.getAbsolutePath());
									logger.error("parsing pages error: {}", e);
								}
							}
							else{
								try {
									StatementParser parser = new StatementParser(f.getAbsolutePath());									
									parser.processSteps();
									parser.convertCategory();
								} catch (Exception e) {
									invalidFiles.add(f.getAbsolutePath());
									logger.error("parsing statements error: {}", e);
								}
							}
							
						}
						
//						if(isOnPage)
//						{							
//							String skinName = skinComboBox.getSelectedItem().toString();
//							String skinid = null;
//							
//							for(JsonElement skin : skins.getAsJsonArray())
//							{
//								if(skin.getAsJsonObject().get("skinName").getAsString().equals(skinName))
//								{
//									skinid = skin.getAsJsonObject().get("id").getAsString();
//									break;
//								}
//							}
//							PageParser.Skin = skinid;
//							
//							for(File f : filelist)
//							{
//								if(f.getName().contains(Page.SharedElements))
//								{
//									try {
//										new PageParser().parse(f.getAbsolutePath());
//									} catch (Exception e) {
//										invalidFiles.add(f.getAbsolutePath());
//										logger.error("parsing shared elements error: {}", e);
//									}
//								}
//							}
//							for(File f : filelist)
//							{
//								
//								if(f.getName().contains(Page.SharedElements))
//								{
//									continue;
//								}
//								
//								try {
//									new PageParser().parse(f.getAbsolutePath());
//								} catch (Exception e) {
//									invalidFiles.add(f.getAbsolutePath());
//									logger.error("parsing pages error: {}", e);
//								}
//							}
//						}
//						else
//						{
//							for(File f : filelist)
//							{
//								
//								try {
//									
//									String projectname = projectComboBox.getSelectedItem().toString();
//									String projectid = null;
//									
//									for(JsonElement project : projects.getAsJsonArray())
//									{
//										if(project.getAsJsonObject().get("projectName").getAsString().equals(projectname))
//										{
//											projectid = project.getAsJsonObject().get("id").getAsString();
//											break;
//										}
//									}
//									StatementParser.project = projectid;
//									StatementParser parser = new StatementParser(f.getAbsolutePath());									
//									parser.processSteps();
//									parser.convertCategory();
//								} catch (Exception e) {
//									invalidFiles.add(f.getAbsolutePath());
//									logger.error("parsing statements error: {}", e);
//								}
//
//							}
//						}
						
						
						for(String file : invalidFiles)
						{
							logger.info("Error in Files: {}", file);
						}
						btnConvert.setText("Convert");
					}
					
					
				};
				converting.start();
				
				
			}

		});
		
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent paramActionEvent) {
				converting.interrupt();
			}
		});
		
		projectComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!isOnPage){
					return;
				}
				
				skinModel.removeAllElements();
				String projectname = projectComboBox.getSelectedItem().toString();
				String projectid = null;
				
				for(JsonElement project : projects.getAsJsonArray())
				{
					if(project.getAsJsonObject().get("projectName").getAsString().equals(projectname))
					{
						projectid = project.getAsJsonObject().get("id").getAsString();
						break;
					}
					//projectModel.addElement(project.getAsJsonObject().get("projectName").getAsString());
				}
				skins = Helper.getSkinsByProject(projectid);
				for(JsonElement skin : skins.getAsJsonArray())
				{
					skinModel.addElement(skin.getAsJsonObject().get("skinName").getAsString());
				}
				
			}
		});
		
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent paramChangeEvent) {
				if(tabbedPane.getSelectedIndex() == 1)
				{
					isOnPage = true;
				}
				else{
					isOnPage = false;
				}
				
				if(isOnPage)
				{
					skinModel.removeAllElements();
					String projectname = projectComboBox.getSelectedItem().toString();
					String projectid = null;
					
					for(JsonElement project : projects.getAsJsonArray())
					{
						if(project.getAsJsonObject().get("projectName").getAsString().equals(projectname))
						{
							projectid = project.getAsJsonObject().get("id").getAsString();
							break;
						}
					}
					skins = Helper.getSkinsByProject(projectid);
					for(JsonElement skin : skins.getAsJsonArray())
					{
						skinModel.addElement(skin.getAsJsonObject().get("skinName").getAsString());
					}
					
					
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
				fc = new JFileChooser(Configuration.CucumberWorkspace);
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				fc.setVisible(true);				
				int returnVal = fc.showOpenDialog(null);
				if (returnVal == JFileChooser.APPROVE_OPTION) 	
				{
					fileOrDir = fc.getSelectedFile();
					
					if(fileOrDir.isDirectory())
					{
						selectedField.setText(fileOrDir.getAbsolutePath());
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
			            if (file.isFile() && file.getName().endsWith(".rb") && !file.getName().endsWith("-ErrorFormatred.rb")){
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
