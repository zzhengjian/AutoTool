package com.gd.loginhelper;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import com.gd.common.Configuration;
import com.gd.driver.Customer;
import com.gd.driver.Driver;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;

public class LoginPanel extends JPanel {
	/**
	 * Login Helper UI
	 */
	private static final long serialVersionUID = -1807850528422559367L;
	private JLabel lblProductcode;
	private JLabel lblAccountage;
	private JLabel lblAmount;
	private JTextField tfProductCode;
	private JTextField tfAccountAge;
	private JTextField tfAccountBalance;
	private JButton btnGet;
	private JList<String> ltCustomerType;
	private JLabel lblCustomerType;
	private JButton btnAdd;
	private JButton btnClear;
	private JScrollPane scrpCustType;
	private JComboBox<String>  cbxProject;
	private JLabel lblProject;	
	private JList<String> ltCustomerType2;

	List<String> defaultCustlists;
	private DefaultListModel<String> defaultCustomerModel;
	private DefaultListModel<String> ltCustomerType2Model;
	
	private JButton btnRemove;
	private JTextField tfSearchBox;
	private JTextPane customerTextPane;
	private JScrollPane customerInfoPanel;
	private JCheckBox chckbxLogin;
	private JTextField email;
	private JComboBox<String> envComboBox;
	private JComboBox<String> processorBox;
	private JLabel lblProcessor;
	
	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		
		initComponents();
		createEvents();
	}


	private void initComponents() {
		
		lblProductcode = new JLabel("Product Code");
		
		lblAccountage = new JLabel("Account Age(days)");
		lblAccountage.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		lblAmount = new JLabel("Account Balance($)");
		
		tfProductCode = new JTextField();
		tfProductCode.setColumns(10);
		
		tfAccountAge = new JTextField();
		tfAccountAge.setText("<=180");
		tfAccountAge.setColumns(10);
		
		tfAccountBalance = new JTextField();
		tfAccountBalance.setText(">=0");
		tfAccountBalance.setColumns(10);
		
		btnGet = new JButton("Get Customer");
		
		JScrollPane scrollPane = new JScrollPane();
		
		lblCustomerType = new JLabel("Customer Type");
		
		btnAdd = new JButton("Add");
		
		btnClear = new JButton("Clear");
		
		scrpCustType = new JScrollPane();
		
		cbxProject = new JComboBox<String>();

		int size = Customer.projectList.size();
		String[] projects = new String[size];
		DefaultComboBoxModel<String> projectModel = new DefaultComboBoxModel<String>(Customer.projectList.toArray(projects));
		cbxProject.setModel(projectModel);
		
		lblProject = new JLabel("Project");
		
		btnRemove = new JButton("Remove");
		
		tfSearchBox = new JTextField();
		tfSearchBox.setToolTipText("input box to search customer types");
		tfSearchBox.setColumns(10);
		
		customerInfoPanel = new JScrollPane();
		
		chckbxLogin = new JCheckBox("Need Login");
		
		email = new JTextField();
		email.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		
		JLabel lblEnv = new JLabel("Environment");
		
		envComboBox = new JComboBox<String>();
		envComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"QA4", "QA3"}));
		envComboBox.setEditable(true);
		
		processorBox = new JComboBox<String>();
		processorBox.setModel(new DefaultComboBoxModel<String>(new String[] {"TSYS", "IPS"}));
		
		lblProcessor = new JLabel("Processor");
				
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(customerInfoPanel, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProcessor, GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE)
								.addComponent(lblEnv))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(processorBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(envComboBox, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblProject, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblProductcode)
								.addComponent(lblAccountage)
								.addComponent(lblAmount))
							.addPreferredGap(ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(tfProductCode, Alignment.TRAILING, 109, 109, Short.MAX_VALUE)
								.addComponent(cbxProject, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfAccountAge, Alignment.TRAILING)
								.addComponent(tfAccountBalance)))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblEmail)
							.addPreferredGap(ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
							.addComponent(email, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(chckbxLogin)
							.addPreferredGap(ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
							.addComponent(btnGet))
						.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnRemove, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnClear, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(lblCustomerType, Alignment.LEADING)
						.addComponent(tfSearchBox, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrpCustType, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 209, Short.MAX_VALUE))
					.addGap(52))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCustomerType)
							.addGap(2)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(tfSearchBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(cbxProject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblProject))
							.addGap(17)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup()
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(tfProductCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblProductcode))
											.addGap(10)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(tfAccountAge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblAccountage))
											.addGap(11)
											.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
												.addComponent(tfAccountBalance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblAmount))
											.addGap(15)
											.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
												.addComponent(email, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(lblEmail))))
									.addGap(8)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnClear)
										.addComponent(btnRemove)
										.addComponent(btnAdd))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
										.addComponent(customerInfoPanel)
										.addComponent(scrpCustType, GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(lblEnv)
									.addGap(3)
									.addComponent(lblProcessor)
									.addGap(230)))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnGet)
								.addComponent(chckbxLogin))
							.addGap(42))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(envComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(3)
							.addComponent(processorBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(302))))
		);
		
		customerTextPane = new JTextPane();
		customerInfoPanel.setViewportView(customerTextPane);
			
		ltCustomerType = new JList<String>();
		
		defaultCustomerModel = new DefaultListModel<String>();
		defaultCustlists = CucumberHelper.loadCustomerTypes();
		for(String cust : defaultCustlists)
		{
			defaultCustomerModel.addElement(cust);
		}
		ltCustomerType.setModel(defaultCustomerModel);

		scrollPane.setViewportView(ltCustomerType);
		
		ltCustomerType2 = new JList<String>();
		ltCustomerType2Model = new DefaultListModel<String>();
		ltCustomerType2.setModel(ltCustomerType2Model);
		
		scrpCustType.setViewportView(ltCustomerType2);
		
		setLayout(groupLayout);	
	}
	
	private void createEvents() {

		btnAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent paramMouseEvent) {
				
				List<String> custlists = ltCustomerType.getSelectedValuesList();
				
				for(String custType : custlists)
				{
					ltCustomerType2Model.addElement(custType);
				}
				
				
			}
		});
		
		btnRemove.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent paramMouseEvent) {
				
				List<String> custlists = ltCustomerType2.getSelectedValuesList();
				
				for(String custType : custlists)
				{
					ltCustomerType2Model.removeElement(custType);
				}
			}
		});
		
		
		btnClear.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent paramMouseEvent) {
				
				ltCustomerType2Model.clear();
			}
		});
		
		btnGet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent paramMouseEvent) {
							
				int size = ltCustomerType2.getModel().getSize();
				
				String custTypes = "";
			    // Get all item objects
			    for (int i = 0; i < size; i++) {			    	
			     
			      Object item = ltCustomerType2.getModel().getElementAt(i);		      
			      
			      if(i == (size-1)) 
			    	  custTypes += item;
			      else			    	  
			    	  custTypes += item + "|";			      
			      
			    }
								
				CucumberHelper.getCustTypeArgs().put("ProductCode", tfProductCode.getText());
				CucumberHelper.getCustTypeArgs().put("CustomerType", custTypes);
				CucumberHelper.getCustTypeArgs().put("accountage", tfAccountAge.getText());
				CucumberHelper.getCustTypeArgs().put("acctbalance", tfAccountBalance.getText());
				CucumberHelper.getCustTypeArgs().put("email", email.getText());
				CucumberHelper.getCustTypeArgs().put("environment", envComboBox.getSelectedItem().toString());
				CucumberHelper.getCustTypeArgs().put("processor", processorBox.getSelectedItem().toString());
				CucumberHelper.getCustTypeArgs().put("project", cbxProject.getSelectedItem().toString());
				
				CucumberHelper.getSpecificCustomerFromRest();
				
				customerTextPane.setText(CucumberHelper.custInfo.toString());
				
				if(Customer.UserId.equals(Customer.Customer_Not_Exist) || Customer.UserId.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Customer not found");
				}
				else
				{
					if(chckbxLogin.isSelected()){
						boolean isLogin = LoginSkins.login(Driver.getWebDriver(), (String) cbxProject.getSelectedItem());
						if(!isLogin)
						{
							JOptionPane.showMessageDialog(null, "UserId/Password is incorrect, Please make sure you inputs in correct");
						}
					}
					
				}
				

			}
		});
		
		tfSearchBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//Filter search results
				List<String> tempCustList = new ArrayList<String>();
				String type = tfSearchBox.getText();
				if(!type.equals(""))
				{
					for(String temptype : defaultCustlists)
					{
						if(temptype.toLowerCase().contains(type.toLowerCase()))
						{
							tempCustList.add(temptype);
						}
					}
					DefaultListModel<String> tempCustomerModel = new DefaultListModel<String>();
					for(String cust : tempCustList)
					{
						tempCustomerModel.addElement(cust);
					}
					
					ltCustomerType.setModel(tempCustomerModel);
				}
				else
				{
					ltCustomerType.setModel(defaultCustomerModel);
				}

			}
		});
		
		cbxProject.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbxProject.getSelectedItem().equals("Walmart"))
				{
					CucumberHelper.runPath = Paths.get(Configuration.CucumberWorkspace, "Projects/Walmart");
				}
				else
				{
					CucumberHelper.runPath = Paths.get(Configuration.CucumberWorkspace, "Projects/GreenDot");
				}
			}
		});

	}
}
