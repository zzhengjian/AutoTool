package com.gd.pages.serializer;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class PageUI extends JPanel {
	private JScrollPane scrollPane;
	private JTree tree;
	private JComboBox<String> projectComboBox;
	private JTable metaTable;
	private JLabel lblUrl;
	private JTextField urlTf;
	private JLabel lblElementMetas;

	/**
	 * Create the panel.
	 */
	public PageUI() {
		
		initComponents();
		
		metaTable = new JTable();
		
		JSeparator separator = new JSeparator();
		
		lblUrl = new JLabel("Url");
		
		urlTf = new JTextField();
		urlTf.setColumns(10);
		
		lblElementMetas = new JLabel("Element Metas");
		
		
		
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(46)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(projectComboBox, GroupLayout.PREFERRED_SIZE, 117, GroupLayout.PREFERRED_SIZE)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE)
							.addGap(44)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblElementMetas)
								.addComponent(metaTable, GroupLayout.PREFERRED_SIZE, 510, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createSequentialGroup()
									.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(separator, GroupLayout.PREFERRED_SIZE, 1, GroupLayout.PREFERRED_SIZE)
											.addGap(58))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(lblUrl)
											.addGap(18)))
									.addComponent(urlTf, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(93, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(42)
							.addComponent(projectComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 423, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(86)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
									.addComponent(urlTf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lblUrl)))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(lblElementMetas)
							.addGap(18)
							.addComponent(metaTable, GroupLayout.PREFERRED_SIZE, 285, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(29, Short.MAX_VALUE))
		);
		setLayout(groupLayout);

	}

	private void initComponents() {
		tree = new JTree();		
		scrollPane = new JScrollPane(tree);
		
		projectComboBox = new JComboBox<String>();
		
	}
}
