package com.gd.steps.serializer;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.swing.AbstractAction;
import javax.swing.Action;
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
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DefaultStyledDocument;

import com.gd.driver.AutoTool;
import com.gd.steps.doc.Helper;
import javax.swing.JScrollPane;

//简单的文本编辑器

public class DocEditor extends JFrame {
 JTextPane textPane = new JTextPane(); //文本窗格，编辑窗口 
 JLabel statusBar = new JLabel(); //状态栏
 JFileChooser filechooser = new JFileChooser(); //文件选择器
 JComboBox<String> statementCbx = new JComboBox<String>();
 private JPanel panel;
 private JButton btnGenerateDoc;
 private JScrollPane editorScrollPane;
 
 private File tempFolder;

 public DocEditor() { //构造函数
  super("Cucumber Doc Editor");  //调用父类构造函数
  setTitle("Cucumber Doc Editor");
  ((AbstractDocument) textPane.getDocument()).setDocumentFilter(new HighlightDocumentFilter(textPane));
  
  Action[] actions =  //Action数组,各种操作命令
   {
    new NewAction(),
    new OpenAction(),
    new SaveAction(),
    new CutAction(),
    new CopyAction(),
    new PasteAction(),
    new AboutAction(),
    new ExitAction()};

  setJMenuBar(createJMenuBar(actions));  //设置菜单栏
  Container container = getContentPane(); //得到容器

  textPane.setMargin(new Insets(1, 3, 3, 3));
  //container.add(createJToolBar(actions), BorderLayout.NORTH); //增加工具栏
  //container.add(statementCbx, BorderLayout.NORTH);
  //container.add(textPane, BorderLayout.CENTER); //增加文本窗格
  container.add(statusBar, BorderLayout.SOUTH); //增加状态栏
  
  panel = new JPanel();
  getContentPane().add(panel, BorderLayout.NORTH);
  container.add(panel, BorderLayout.NORTH);
  
  btnGenerateDoc = new JButton("");
  btnGenerateDoc.addActionListener(new ActionListener() {
  	public void actionPerformed(ActionEvent e) {
  		String statement = statementCbx.getSelectedItem().toString();
  		String textall = textPane.getText();
  		textPane.setText(textall.replace(statement, Helper.getDocTemplate(statement, "azheng")));
  	}
  });
  btnGenerateDoc.setPreferredSize(new Dimension(30, 9));
  btnGenerateDoc.setIcon(new ImageIcon(DocEditor.class.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Indent-Black.png")));
  btnGenerateDoc.setToolTipText("Generate Doc for then Statement");
  btnGenerateDoc.setVerticalAlignment(SwingConstants.TOP);
  panel.setLayout(new BorderLayout(0, 0));
  panel.add(statementCbx, BorderLayout.CENTER);
  panel.add(btnGenerateDoc,BorderLayout.EAST);
  
  editorScrollPane = new JScrollPane(textPane);
  getContentPane().add(editorScrollPane, BorderLayout.CENTER);
  
  setSize(487, 420); //设置窗口尺寸
  setVisible(true);  //设置窗口可视
  setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);  //关闭窗口时退出程序
 }

 private JMenuBar createJMenuBar(Action[] actions) {  //创建菜单栏
  JMenuBar menubar = new JMenuBar(); //实例化菜单栏
  JMenu menuFile = new JMenu("File"); //实例化菜单
  JMenu menuEdit = new JMenu("Edit");
  JMenu menuAbout = new JMenu("Help");
  JMenuItem menuItem = new JMenuItem(actions[0]);
  menuItem.setText("New");
  menuFile.add(menuItem); //增加新菜单项
  JMenuItem menuItem_1 = new JMenuItem(actions[1]);
  menuItem_1.setText("Open");
  menuItem_1.setAccelerator(KeyStroke.getKeyStroke("F3"));
  menuFile.add(menuItem_1);
  JMenuItem menuItem_2 = new JMenuItem(actions[2]);
  menuItem_2.setText("Save");
  menuItem_2.setAccelerator(KeyStroke.getKeyStroke("F5"));
  menuFile.add(menuItem_2);
  JMenuItem menuItem_3 = new JMenuItem(actions[7]);
  menuItem_3.setText("Quit");
  menuFile.add(menuItem_3);
  JMenuItem menuItem_4 = new JMenuItem(actions[3]);
  menuItem_4.setText("Cut");
  menuEdit.add(menuItem_4);
  JMenuItem menuItem_5 = new JMenuItem(actions[4]);
  menuItem_5.setText("Copy");
  menuEdit.add(menuItem_5);
  JMenuItem menuItem_6 = new JMenuItem(actions[5]);
  menuItem_6.setText("Paste");
  menuEdit.add(menuItem_6);
  JMenuItem menuItem_7 = new JMenuItem(actions[6]);
  menuItem_7.setText("About");
  menuAbout.add(menuItem_7);
  menubar.add(menuFile); //增加菜单
  menubar.add(menuEdit);
  menubar.add(menuAbout);
  return menubar; //返回菜单栏
 }

 private JToolBar createJToolBar(Action[] actions) { //创建工具条
  JToolBar toolBar = new JToolBar(); //实例化工具条
  for (int i = 0; i < actions.length; i++) {
   JButton bt = new JButton(actions[i]); //实例化新的按钮
   bt.setRequestFocusEnabled(false); //设置不需要焦点
   toolBar.add(bt); //增加按钮到工具栏
  }
  return toolBar;  //返回工具栏
 }

 class NewAction extends AbstractAction { //新建文件命令
  public NewAction() {
   super("New");
  }
  public void actionPerformed(ActionEvent e) {
   textPane.setDocument(new DefaultStyledDocument()); //清空文档
  }
 }

 class OpenAction extends AbstractAction { //打开文件命令
  public OpenAction() {
   super("Open");
  }
  public void actionPerformed(ActionEvent e) {
	  tempFolder = new File(AutoTool.CucumberDirectoryPath, "General/step_definitions");
	  filechooser.setCurrentDirectory(tempFolder);
	   int i = filechooser.showOpenDialog(DocEditor.this); //显示打开文件对话框
	   if (i == JFileChooser.APPROVE_OPTION) { //点击对话框中打开选项
	    File f = filechooser.getSelectedFile(); //得到选择的文件
	    try {
	     InputStream is = new FileInputStream(f); //得到文件输入流
	     textPane.read(is, "d"); //读入文件到文本窗格
	     StatementParser sParser = new StatementParser(f.getAbsolutePath());
	     sParser.processSteps();
	     DefaultComboBoxModel<String> cbxmodel = new DefaultComboBoxModel<String>();
	     for(Statement statement : sParser.getStatements())
	     {
	    	 
	    	 cbxmodel.addElement(statement.getContent());
	     }
	     statementCbx.setModel(cbxmodel);
	    } catch (Exception ex) {
	     ex.printStackTrace();  //输出出错信息
	    }
   }
  }
 }

 class SaveAction extends AbstractAction {  //保存命令
  public SaveAction() {
   super("Save");
  }
  public void actionPerformed(ActionEvent e) {
   int i = filechooser.showSaveDialog(DocEditor.this); //显示保存文件对话框
   if (i == JFileChooser.APPROVE_OPTION) {  //点击对话框中保存按钮
    File f = filechooser.getSelectedFile(); //得到选择的文件
    try {
    	 f.setWritable(true);
	     FileOutputStream out = new FileOutputStream(f);  //得到文件输出流
	     out.write(textPane.getText().getBytes()); //写出文件    
	     
    } catch (Exception ex) {
     ex.printStackTrace(); //输出出错信息
    }

    StatementParser sParser = new StatementParser(f.getAbsolutePath());
    sParser.processSteps();
    DefaultComboBoxModel<String> cbxmodel = new DefaultComboBoxModel<String>();
    for(Statement statement : sParser.getStatements())
    {
   	 
   	 cbxmodel.addElement(statement.getContent());
    }
    statementCbx.setModel(cbxmodel);
    
   }
  }
 }

 class ExitAction extends AbstractAction { //退出命令
  public ExitAction() {
   super("Exit");
  }
  public void actionPerformed(ActionEvent e) {
   System.exit(0);  //退出程序
  }
 }

 class CutAction extends AbstractAction {  //剪切命令
  public CutAction() {
   super("Cut");
  }
  public void actionPerformed(ActionEvent e) {
   textPane.cut();  //调用文本窗格的剪切命令
  }
 }

 class CopyAction extends AbstractAction {  //拷贝命令
  public CopyAction() {
   super("Copy");
  }
  public void actionPerformed(ActionEvent e) {
   textPane.copy();  //调用文本窗格的拷贝命令
  }
 }

 class PasteAction extends AbstractAction {  //粘贴命令
  public PasteAction() {
   super("Paste");
  }
  public void actionPerformed(ActionEvent e) {
   textPane.paste();  //调用文本窗格的粘贴命令
  }
 }

 class AboutAction extends AbstractAction { //关于选项命令
  public AboutAction() {
   super("about");
  }
  public void actionPerformed(ActionEvent e) {
   JOptionPane.showMessageDialog(DocEditor.this, "cucumber doc editor"); //显示软件信息
  }
 }

 public static void main(String[] args) {
  new DocEditor();
 }
 
 
}
