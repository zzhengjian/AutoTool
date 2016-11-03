package com.gd.driver;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.gd.common.Configuration;
import com.gd.common.Property;
import com.gd.common.Utils;
import com.gd.loginhelper.TestFrame;
import com.gd.pagetree.ElementBean;
import com.gd.pagetree.PageBean;
import com.gd.pagetree.PageTree;
import com.gd.pogen.POGen;
import com.gd.steps.serializer.DocEditor;


public class AutoTool {

	static AutoTool window;
	private JFrame frmAutotool;
	private JTextField elementTag;
	private JTextField url;
	private JTextField sParam;
	
	private JTextPane logTextPane;
	
	private WebDriver oWebDriver;
	private String sBrowserType;
	private String sCommand;	

	private HashMap<String, PageNode> pageNodeMap  = new LinkedHashMap<String, PageNode>();
	private File file = null;	
	//public static String CucumberDirectoryPath = loadCucumberWorkspace();
	public static String tempPath;
	public JScrollPane elementsScrollPane;
	
	private JComboBox<String> browserType;	
	private JComboBox<String> commandName;	
	
	//Jtree
    protected DefaultMutableTreeNode rootNode;
    protected DefaultTreeModel treeModel;	
    
    public static String PageName = "";
    private JMenuBar menuBar;
    private JMenuItem mntmPoGen;
    private JMenuItem mntmLoginHelper;
    private JMenuItem mntmAutoFill;
    private JMenu mnPlugins;
    private JLabel lblUrl;
    private JMenuItem mntmPageConverter;
    private JButton btnStart;
    private JMenuItem mntmDocEditor;
    private JButton btnInspect;
    private JButton btnGoto;
    private JButton btnHighlight;
    private JLabel lblCommand;
    private JButton btnSend;
    private JScrollPane logScrollPane;
    private JButton btnAddButton;
    private JButton btnRemove;
    private JButton btnRefreshButton;
    
    private final JTree tree =  new JTree();
    
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					window = new AutoTool();
					window.frmAutotool.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AutoTool() {
		
		Property.SetUp();
		loadCucumberWorkspace();
		initialize();
	}

	private void loadCucumberWorkspace() {

		String workspace =  JOptionPane.showInputDialog("your cucumber location",Configuration.CucumberWorkspace);
		Configuration.CucumberWorkspace = workspace;	
		Configuration.saveWorkspace();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initialize() {
		
		initComponents();
		createMenuComponents();
		createEvents();		
		createMenuEvents();	

	}

	private void createMenuComponents() {
		mntmLoginHelper = new JMenuItem("Login Helper");

		mnPlugins.add(mntmLoginHelper);
		
		mntmAutoFill = new JMenuItem("Auto Fill");

		mnPlugins.add(mntmAutoFill);
		
		mntmPoGen = new JMenuItem("POGen");

		mnPlugins.add(mntmPoGen);
		
		mntmPageConverter = new JMenuItem("PageConverter");

		mnPlugins.add(mntmPageConverter);
		
		mntmDocEditor = new JMenuItem("Doc Editor");

		mnPlugins.add(mntmDocEditor);
		
		lblUrl = new JLabel("URL");
		lblUrl.setBounds(5, 94, 46, 14);
		frmAutotool.getContentPane().add(lblUrl);
	}

	private void createMenuEvents() {
		mntmLoginHelper.addMouseListener(new MouseAdapter() {
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
		
		mntmDocEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DocEditor();
			}
		});
		
	}

	private void createEvents() {
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {

					if(btnStart.getText().equals("Start"))
					{
						sBrowserType = browserType.getSelectedItem().toString();
						System.out.println(sBrowserType);
						oWebDriver = new Driver().StartWebDriver(sBrowserType);		
						oWebDriver.manage().timeouts().pageLoadTimeout(50000, TimeUnit.MILLISECONDS);
						btnStart.setText("Stop");
					}
					
					else if(btnStart.getText().equals("Stop"))
					{
						try {
								if(oWebDriver!=null)
								{
									oWebDriver.quit();
								}
						} catch (Exception e1) {							
							logTextPane.setText(logTextPane.getText() + "Browser may already be closed" + "\n");
						}	
						
						btnStart.setText("Start");						
					}
					else
					{
						
					}
					
					
				} catch (Exception e1) {
					e1.printStackTrace();
					logTextPane.setText(logTextPane.getText() + "faile to start webdriver" + "\n");
					logTextPane.setText(logTextPane.getText() + e1.toString() + "\n");
				}
			}
		});
		
		
		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String responseText;
				sCommand = commandName.getSelectedItem().toString();
				String param = sParam.getText();
				System.out.println(sCommand);
				System.out.println(param);
				
				if("".equalsIgnoreCase(elementTag.getText()))
				{
					//responseText = new DebugRemoteDriver(remoteAddress,sessionid).sendCommand(sCommand, param);	
					responseText = new DebugRemoteDriver(oWebDriver).sendCommand(sCommand, param);
					System.out.println(responseText);
					logTextPane.setText(logTextPane.getText()+responseText+"\n");
					
				}
				else
				{
					//responseText = new DebugWebElement(elementTag.getText(),new DebugRemoteDriver(remoteAddress,sessionid)).sendCommand(sCommand, param);				
					responseText = new DebugWebElement(elementTag.getText(),oWebDriver).sendCommand(sCommand, param);
					System.out.println(responseText);
					logTextPane.setText(logTextPane.getText()+responseText+"\n");
				}
					System.gc();
			}
		});
		
		btnHighlight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(oWebDriver!=null)
				{
					try {
						window.frmAutotool.setVisible(false);
						DebugWebElement debugelement = new DebugWebElement(elementTag.getText(),oWebDriver);						
						if(!debugelement.oWebElement.isDisplayed())
							logTextPane.setText(logTextPane.getText() + "Element is hidden" + "\n");
						else
							debugelement.highlightMe();
					} catch (NoSuchElementException e1) {						
						logTextPane.setText(logTextPane.getText() + "Element not Found" + "\n");
						e1.printStackTrace();						
					} catch (Exception e1) {						
						logTextPane.setText(logTextPane.getText() + "Unknow error" + "\n");
						e1.printStackTrace();						
					} finally{
						window.frmAutotool.setVisible(true);
					}
				}
			}
		});
		
		
		url.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					URL sUrl = new URL(url.getText());
					System.out.println(sUrl);
					oWebDriver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
					oWebDriver.get(sUrl.toString());
				} catch (MalformedURLException e) {	
					logTextPane.setText(logTextPane.getText() + "Wrong url form" + "\n");
					e.printStackTrace();
				} catch (Exception e) {	
					e.printStackTrace();
				}						
				
			}
		});
		
		btnGoto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					URL sUrl = new URL(url.getText());
					System.out.println(sUrl);
					oWebDriver.manage().timeouts().pageLoadTimeout(90, TimeUnit.SECONDS);
					oWebDriver.get(sUrl.toString());
				} catch (MalformedURLException e) {	
					logTextPane.setText(logTextPane.getText() + "Wrong url form" + "\n");
					e.printStackTrace();
				} catch (Exception e) {	
					e.printStackTrace();
				}						
				
			}
		});
		
		tree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				String pageurl = "";
				//new Added
				if(selectionNode!=null)
				{
					
					if(selectionNode.getUserObject() instanceof PageBean)
					{
						pageurl = ((PageBean)selectionNode.getUserObject()).getUrl();

						elementTag.setText("");
						
					}
					else if(selectionNode.getUserObject() instanceof ElementBean)
					{
						pageurl = ((PageBean)((DefaultMutableTreeNode) selectionNode.getParent()).getUserObject()).getUrl();

						elementTag.setText(((ElementBean)selectionNode.getUserObject()).getSelector());
					}
					url.setText(pageurl);
				}

			}
		});		
		
		
		btnRefreshButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultMutableTreeNode selectionNode = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				
				if(selectionNode!=null&&selectionNode.isLeaf())
				{		
					tree.setModel(treeModel);
					DefaultMutableTreeNode node = null;
					file = pageNodeMap.get(selectionNode.getParent().toString()).pageFile;
					
					PageNode pageNode = new PageNode();						 
					pageNodeMap.put(pageNode.pageName, pageNode);	

					node = new DefaultMutableTreeNode(pageNode.pageName);

					
					Iterator<Entry<String, String>> key = pageNode.elementsMap.entrySet().iterator();
					
					while(key.hasNext())
					{
						java.util.Map.Entry entry = (java.util.Map.Entry)key.next();
						node.add(new DefaultMutableTreeNode(entry.getKey()));
					 
					}

					treeModel.reload(node);
					tree.revalidate();
					tree.repaint();
			        //Make sure the user can see the lovely new node.
			        tree.scrollPathToVisible(new TreePath(node.getPath()));
				}
				else if(selectionNode!=null&&!selectionNode.equals(rootNode))
				{
					tree.setModel(treeModel);
					DefaultMutableTreeNode node = null;
					file = pageNodeMap.get(selectionNode.toString()).pageFile;
					
					PageNode pageNode = new PageNode();						 
					pageNodeMap.put(pageNode.pageName, pageNode);	

					node = new DefaultMutableTreeNode(pageNode.pageName);

					
					Iterator<Entry<String, String>> key = pageNode.elementsMap.entrySet().iterator();
					
					while(key.hasNext())
					{
						java.util.Map.Entry entry = (java.util.Map.Entry)key.next();
						node.add(new DefaultMutableTreeNode(entry.getKey()));
					 
					}

					treeModel.reload(node);
					tree.revalidate();
					tree.repaint();
			        //Make sure the user can see the lovely new node.
			        tree.scrollPathToVisible(new TreePath(node.getPath()));
				}
				

			}
		});
		
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				 TreePath currentSelection = tree.getSelectionPath();
			        if (currentSelection != null) {
			        	
			            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode)
			                         (currentSelection.getLastPathComponent());
			            MutableTreeNode parent = (MutableTreeNode)(currentNode.getParent());
			            if (parent != null && parent.equals(rootNode)) {
			                treeModel.removeNodeFromParent(currentNode);
			                return;
			            }
			            else if (parent != null &&!parent.equals(rootNode))
			            {
			                treeModel.removeNodeFromParent(parent);
			                return;
			            }
			        } 

			}
		});
		
		btnAddButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				JFileChooser fc;
				
				fc = new JFileChooser(Paths.get(Configuration.CucumberWorkspace, Customer.projectPath.get("GreenDot")).toString());
				fc.setVisible(true);				
				int returnVal = fc.showOpenDialog(frmAutotool);
				if (returnVal == JFileChooser.APPROVE_OPTION) 	
				{
					file = fc.getSelectedFile();
					tempPath = file.getAbsolutePath();
				}
				
				if(file!=null&&returnVal == JFileChooser.APPROVE_OPTION)
				{
					tree.setModel(treeModel);
					DefaultMutableTreeNode node = null;	
					
					String path = file.getAbsolutePath();
					PageTree pageTree = new PageTree(path);
					node = pageTree.getPageNode();
					

					treeModel.insertNodeInto(node, rootNode, rootNode.getChildCount());
					
					tree.revalidate();
					tree.repaint();
			        //Make sure the user can see the lovely new node.
			        tree.scrollPathToVisible(new TreePath(node.getPath()));
				}
				
			}
		});
		
		btnInspect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(!(oWebDriver instanceof FirefoxDriver))
				{
					logTextPane.setText(logTextPane.getText() + "Inspect only works for firefox" + "\n");
					return;
				}				

				String selector = null;
				try {
					selector = inspectOnElement(oWebDriver);					
				} catch (NoSuchElementException e) {
					e.printStackTrace();
					if(e.toString().contains("Please make sure firepath is open"))		
						logTextPane.setText(logTextPane.getText() + "Please make sure firepath is open" + "\n");
				}
				elementTag.setText(selector);
			}

			private String inspectOnElement(WebDriver driver) {
				//this line to trigger firepath inspect mode					
				oWebDriver.manage().ime().deactivate();
				Utils.sleepFor(1);
				//this line to get selector once element is inspected
				return oWebDriver.manage().ime().getActiveEngine();
			}
		});
	}

	private void initComponents() {
		frmAutotool = new JFrame();
		frmAutotool.setTitle("AutoHelper");
		frmAutotool.setIconImage(Toolkit.getDefaultToolkit().getImage(AutoTool.class.getResource("/org/openqa/grid/images/selenium.png")));
		frmAutotool.setBounds(100, 100, 779, 733);
		frmAutotool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAutotool.getContentPane().setLayout(null);
		
		browserType = new JComboBox<String>();
		browserType.setModel(new DefaultComboBoxModel<String>(new String[] { "Chrome","Firefox", "IE"}));
		browserType.setBounds(5, 61, 71, 22);
		frmAutotool.getContentPane().add(browserType);
		
		btnStart = new JButton("Start");		
		btnStart.setBounds(94, 61, 71, 23);
		frmAutotool.getContentPane().add(btnStart);
		
		elementTag = new JTextField();
		elementTag.setBounds(5, 164, 316, 21);
		frmAutotool.getContentPane().add(elementTag);
		elementTag.setColumns(10);
		
		btnSend = new JButton("Send");		
		btnSend.setBounds(336, 196, 86, 23);
		frmAutotool.getContentPane().add(btnSend);
		
		commandName = new JComboBox<String>();
		DefaultComboBoxModel<String> commandsModel = new DefaultComboBoxModel<String>();
		Field[] fields = Commands.class.getFields();
		for(Field field : fields)
		{
			commandsModel.addElement(field.getName());			
		}
		commandName.setModel(commandsModel);
		commandName.setBounds(66, 196, 87, 22);
		frmAutotool.getContentPane().add(commandName);
		
		btnHighlight = new JButton("Highlight");
		
		btnHighlight.setBounds(336, 163, 86, 23);
		frmAutotool.getContentPane().add(btnHighlight);
		
		JLabel lblElementTag = new JLabel("Element Tag");
		lblElementTag.setBounds(5, 147, 91, 14);
		frmAutotool.getContentPane().add(lblElementTag);
		
		lblCommand = new JLabel("Command");
		lblCommand.setBounds(10, 196, 59, 22);
		frmAutotool.getContentPane().add(lblCommand);
		
		sParam = new JTextField();
		sParam.setBounds(161, 196, 164, 21);
		frmAutotool.getContentPane().add(sParam);
		sParam.setColumns(10);
		
		
		url = new JTextField();

		url.setBounds(5, 116, 387, 20);
		frmAutotool.getContentPane().add(url);
		url.setColumns(10);
		
		//nav to desired url
		btnGoto = new JButton("");
		btnGoto.setIcon(new ImageIcon(AutoTool.class.getResource("/com/gd/resources/rightArrow.png")));
		
		btnGoto.setBounds(402, 116, 20, 20);
		frmAutotool.getContentPane().add(btnGoto);
		

		rootNode = new DefaultMutableTreeNode("Web Elements");
		treeModel = new DefaultTreeModel(rootNode);
		tree.setModel(treeModel);
		
		elementsScrollPane = new JScrollPane(tree);
		elementsScrollPane.setBounds(453, 69, 250, 369);
		frmAutotool.getContentPane().add(elementsScrollPane);
		
		logTextPane = new JTextPane();
		logTextPane.setBounds(66, 386, 6, 20);
		
		logScrollPane = new JScrollPane(logTextPane);
		logScrollPane.setBounds(5, 230, 430, 180);
		frmAutotool.getContentPane().add(logScrollPane);
		
		btnRefreshButton = new JButton("");
		btnRefreshButton.setIcon(new ImageIcon(AutoTool.class.getResource("/com/gd/resources/refresh.png")));
		
		btnRefreshButton.setBounds(504, 41, 20, 20);
		frmAutotool.getContentPane().add(btnRefreshButton);
		
		btnAddButton = new JButton("");
		btnAddButton.setToolTipText("Add A Page to Tree Node");
		btnAddButton.setIcon(new ImageIcon(AutoTool.class.getResource("/com/gd/resources/plus.png")));


		
		btnAddButton.setBounds(453, 41, 20, 20);
		frmAutotool.getContentPane().add(btnAddButton);
		
		btnRemove = new JButton("");
		btnRemove.setToolTipText("Remove a Page");
		btnRemove.setIcon(new ImageIcon(AutoTool.class.getResource("/com/gd/resources/cancel.png")));
		
		btnRemove.setBounds(478, 41, 20, 20);
		frmAutotool.getContentPane().add(btnRemove);
		
		btnInspect = new JButton("inspect");
		
		btnInspect.setDisabledIcon(new ImageIcon(AutoTool.class.getResource("/com/gd/resources/arrow.png")));

		
		btnInspect.setBounds(220, 61, 77, 23);
		frmAutotool.getContentPane().add(btnInspect);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 756, 21);
		frmAutotool.getContentPane().add(menuBar);
		
		mnPlugins = new JMenu("Plug-ins");
		menuBar.add(mnPlugins);
	}
}

