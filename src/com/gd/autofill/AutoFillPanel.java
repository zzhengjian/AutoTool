package com.gd.autofill;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map.Entry;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.browser.debug.Customer;
import com.browser.debug.Driver;

public class AutoFillPanel extends JPanel {
	private JComboBox<String> cbxCustomerType;
	private JComboBox<String> cbxAddressType;
	private JLabel lblCustomerType;
	private JLabel lblAddressType;
	private JButton btnFill;

	/**
	 * Create the panel.
	 */
	public AutoFillPanel() {
		
		initComponents();
		CreateEvents();
		
		

	}



	private void initComponents() {
		lblCustomerType = new JLabel("Customer Type");
		
		cbxCustomerType = new JComboBox<String>();
		DefaultComboBoxModel<String> custTypes = new DefaultComboBoxModel<String>();
		for(Entry<String, String> ssnEntry : Customer.ssnMaps.entrySet())
		{
			custTypes.addElement(ssnEntry.getKey());			
		}		
		cbxCustomerType.setModel(custTypes);
		
		lblAddressType = new JLabel("Address Type");
		
		cbxAddressType = new JComboBox<String>();
		DefaultComboBoxModel<String> addrTypes = new DefaultComboBoxModel<String>();
		for (Entry<String, String[]> entry : Customer.addressMap.entrySet())
		{			
			addrTypes.addElement(entry.getKey());
		}
		cbxAddressType.setModel(addrTypes);
		
		btnFill = new JButton("Fill");

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnFill)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblCustomerType)
								.addComponent(lblAddressType))
							.addGap(36)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(cbxAddressType, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(cbxCustomerType, 0, 90, Short.MAX_VALUE))))
					.addContainerGap(255, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(33)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblCustomerType)
						.addComponent(cbxCustomerType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddressType)
						.addComponent(cbxAddressType, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(57)
					.addComponent(btnFill)
					.addContainerGap(125, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	
	private void CreateEvents() {
		btnFill.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String registerCustomerType = (String) cbxCustomerType.getSelectedItem();
				String addressType = (String) cbxAddressType.getSelectedItem();
				AutoFillHelper.fill(Driver.oWebDriver, registerCustomerType, addressType);
			}
		});
		
	}
}
