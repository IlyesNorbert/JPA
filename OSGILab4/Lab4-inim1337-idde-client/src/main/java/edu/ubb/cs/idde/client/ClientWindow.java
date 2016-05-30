package edu.ubb.cs.idde.client;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import edu.ubb.cs.idde.inter.UserItemDAO;

import javax.swing.JButton;
import javax.swing.JTable;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Locale;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;

public class ClientWindow extends JFrame {

	private JPanel contentPane;
	JTable table;
	private WindowController wc;
	private JScrollPane scrollPane;
	JButton btnLoadData;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public ClientWindow(UserItemDAO ud) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 579, 306);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		wc = new WindowController(this,ud);
		
		btnLoadData = new JButton("Load Data");
		btnLoadData.setBounds(5, 233, 424, 23);
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				wc.fillTable();
			}
		});
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 5, 424, 223);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPane.add(btnLoadData);
		
		String[] cbItems= { "English" , "Magyar" };
		JComboBox comboBox = new JComboBox(cbItems);
		comboBox.setBounds(449, 22, 84, 20);
		comboBox.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				JComboBox cb = (JComboBox)e.getSource();
		        String lang = (String)cb.getSelectedItem();
		        if (lang.equals("Magyar")){
		        	wc.setLocale("hu", "hun");
		        }else{
		        	wc.setLocale("", "");
		        }
			}
		});
		contentPane.add(comboBox);
		wc.setLocale(null, null);
	}
}
