package edu.ubb.cs.idde.client;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.table.DefaultTableModel;

import org.slf4j.LoggerFactory;

import edu.ubb.cs.idde.inter.UserItemDAO;
import edu.ubb.cs.idde.inter.Person;

public class WindowController {

	Locale currentLocale;
	ArrayList<String> cols;
	Field[] fields;
	ClientServices serv;
	ArrayList<Object> data;
	ClientWindow gui;
	ArrayList<String> columnNames;
	//private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(JdbcUserItemDAO.class);
	
	public WindowController(ClientWindow cw,UserItemDAO ud){
		gui = cw;
		currentLocale = Locale.getDefault();
		serv = new ClientServices(ud);
		data = new ArrayList<Object>();
		Person u = new Person();
		@SuppressWarnings("rawtypes")
		Class cls = u.getClass();
		fields = cls.getDeclaredFields();
		
	}
	
	void resetTable() {
		DefaultTableModel dataModel = (DefaultTableModel) gui.table.getModel();
		dataModel.setRowCount(0);
		dataModel.setColumnCount(0);
		gui.table.setModel(dataModel);
	}
	
	void fillTable() {
		try {
			data.clear();
			data.addAll(serv.FindAllUsers());
		} catch (RuntimeException e) {
			//LOG.error(e.getClass().getName() + ": " + e.getMessage());
		}
		resetTable();
		DefaultTableModel model = (DefaultTableModel) gui.table.getModel();
		

		for (int i = 0; i < cols.size()-1; i++)
			model.addColumn(cols.get(i));
		//Class supObj;
		//Field[] supFields;
		
		Object[] tabledata = new Object[cols.size()];
		
		for (int i = 0; i < data.size(); i++) {
			for (int j= 0; j < fields.length ; j++)
			{
				try {
					tabledata[j] = fields[j].get(data.get(i));
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			model.addRow(tabledata);
		}
		gui.table.setModel(model);
	}
	
	public void setLocale(String lang, String region) {

		ResourceBundle res;
		try {
			currentLocale = new Locale(lang, region);
		} catch (Exception e) {
			currentLocale = Locale.getDefault();
		}
		res = ResourceBundle.getBundle("LanguageBundle", currentLocale);
		gui.btnLoadData.setText(res.getString("loadbtn"));
		cols = new ArrayList<String>();
		for (int i = 0; i < fields.length; i++) {
			cols.add(res.getString(fields[i].getName()));;
		}
		fillTable();
	}
}
