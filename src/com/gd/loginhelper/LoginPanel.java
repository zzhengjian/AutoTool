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

import com.gd.driver.AutoTool;
import com.gd.driver.Customer;
import com.gd.driver.Driver;

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
	private JButton btnLogin;
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
	
	/**
	 * Create the panel.
	 */
	public LoginPanel() {
		
		initComponents();
		createEvents();
	}


	private void initComponents() {
		
		lblProductcode = new JLabel("Product Code");
		
		lblAccountage = new JLabel("Account Age");
		lblAccountage.setAlignmentY(Component.BOTTOM_ALIGNMENT);
		
		lblAmount = new JLabel("Account Balance");
		
		tfProductCode = new JTextField();
		tfProductCode.setColumns(10);
		
		tfAccountAge = new JTextField();
		tfAccountAge.setColumns(10);
		
		tfAccountBalance = new JTextField();
		tfAccountBalance.setColumns(10);
		
		btnLogin = new JButton("Login");
		
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
		tfSearchBox.setColumns(10);
				
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblAccountage)
								.addComponent(lblAmount)
								.addComponent(lblProductcode)
								.addComponent(lblProject, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(tfProductCode, 109, 109, 109)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(tfAccountBalance, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
									.addComponent(tfAccountAge, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
								.addComponent(cbxProject, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
							.addGap(45)
							.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(btnAdd, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnRemove, GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(btnClear)
									.addGap(4))
								.addComponent(lblCustomerType, Alignment.LEADING)
								.addComponent(tfSearchBox, Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 137, GroupLayout.PREFERRED_SIZE)
								.addComponent(scrpCustType, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE))
							.addGap(52))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnLogin)
							.addGap(26))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblCustomerType)
					.addGap(2)
					.addComponent(tfSearchBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 143, GroupLayout.PREFERRED_SIZE)
									.addGap(13))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(32)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblProject)
										.addComponent(cbxProject, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblProductcode)
										.addComponent(tfProductCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblAccountage)
										.addComponent(tfAccountAge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGap(14)
									.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblAmount)
										.addComponent(tfAccountBalance, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addGap(29)
							.addComponent(scrpCustType, GroupLayout.PREFERRED_SIZE, 208, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(157)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnAdd)
								.addComponent(btnRemove)
								.addComponent(btnClear))
							.addGap(230)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(btnLogin)
					.addGap(49))
		);
			
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
		
		btnLogin.addMouseListener(new MouseAdapter() {
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
				CucumberHelper.getCustTypeArgs().put("AccountAge", tfAccountAge.getText());
				CucumberHelper.getCustTypeArgs().put("AccountBalance", tfAccountBalance.getText());
				CucumberHelper.getCustTypeArgs().put("Email", "");
				
				CucumberHelper.getUserId();
				if(Customer.UserId.equals(Customer.Customer_Not_Exist))
				{
					JOptionPane.showMessageDialog(null, "Customer not found");
				}
				else
				{
					boolean isLogin = LoginSkins.login(Driver.oWebDriver, (String) cbxProject.getSelectedItem());
					if(!isLogin)
					{
						JOptionPane.showMessageDialog(null, "UserId/Password is incorrect, Please make sure you inputs in correct");
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
					CucumberHelper.runPath = Paths.get(AutoTool.CucumberDirectoryPath, "Projects/Walmart");
				}
				else
				{
					CucumberHelper.runPath = Paths.get(AutoTool.CucumberDirectoryPath, "Projects/GreenDot");
				}
			}
		});

	}
}
