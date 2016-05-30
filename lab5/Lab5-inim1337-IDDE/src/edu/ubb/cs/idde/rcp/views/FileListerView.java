package edu.ubb.cs.idde.rcp.views;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.part.ViewPart;

import edu.ubb.cs.idde.rcp.model.UserModelProvider;
import edu.ubb.cs.server.model.Person;

/**
 * Ez a view lekéri az összes mappát a felhasználó home könyvtárából,
 * s kilistázza ezeket.
 */
public class FileListerView extends ViewPart {
	private Composite parent;
	private TableViewer tViewer;
	private ArrayList<String> localColNames;
	private Locale currentLocale;

	public FileListerView() {
	}

	@Override
	public void createPartControl(Composite parent) {
		// createViewer(parent);
		RowLayout rowLayout = new RowLayout();
		rowLayout.wrap = true;
		rowLayout.pack = true;
		rowLayout.justify = true;
		rowLayout.type = SWT.HORIZONTAL;
		parent.setLayout(rowLayout);
		this.parent = parent;

	}

	@Override
	public void setFocus() {
		// TODO Auto-generated method stub

	}

	public void createViewer() {

		//MessageDialog.openInformation(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), "Info",
		//		"Betoltom egy ListViewerbe az aktualis konyvtar osszes allomanyanak nevet es meretet");

		if (tViewer != null){
			removeCols();
			createColumns(tViewer);
			return;
		}
		
		
		String items[] = { "English", "Magyar",};
		final Combo c = new Combo(parent, SWT.READ_ONLY);
		c.setItems(items);
		c.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				if (c.getText().equals("English")) {
					setLocale("", "");
				} else if (c.getText().equals("Magyar")) {
					setLocale("hu", "hun");

				} else {
					setLocale("", "");
				}
			}
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
			}
		});

		// define the TableViewer
		tViewer = new TableViewer(parent, SWT.FILL | SWT.MULTI 
				| SWT.FULL_SELECTION | SWT.BORDER );
		tViewer.setContentProvider(ArrayContentProvider.getInstance());
		setLocale("","");
		
		final Table table = tViewer.getTable();
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true); 

		parent.layout();

	}

	private void createColumns(TableViewer tViewer2) {
		// create a column for the first name


		TableViewerColumn colName = new TableViewerColumn(tViewer, SWT.NONE);
		colName.getColumn().setWidth(200);
		colName.getColumn().setText(localColNames.get(0));
		colName.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person u = (Person) element;
				return u.Name;
			}
		});
		TableViewerColumn colAge = new TableViewerColumn(tViewer, SWT.NONE);
		colAge.getColumn().setWidth(200);
		colAge.getColumn().setText(localColNames.get(1));
		colAge.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person u = (Person) element;
				return u.Age+"";  // no string representation, we only want to display the image
			}
		});
		TableViewerColumn colLoc = new TableViewerColumn(tViewer, SWT.NONE);
		colLoc.getColumn().setWidth(200);
		colLoc.getColumn().setText(localColNames.get(2));
		colLoc.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person u = (Person) element;
				return u.Address;
			}
		});
		TableViewerColumn colPhone = new TableViewerColumn(tViewer, SWT.NONE);
		colPhone.getColumn().setWidth(200);
		colPhone.getColumn().setText(localColNames.get(3));
		colPhone.setLabelProvider(new ColumnLabelProvider() {
			@Override
			public String getText(Object element) {
				Person u = (Person) element;
				return u.PhoneNumber;
			}
		});
	/*	final Table table = tViewer.getTable();
		TableColumn lastColumn = table.getColumn(table.getColumnCount());
		lastColumn.setWidth(0);*/
		ArrayList<Person> us = UserModelProvider.INSTANCE.getUsers();
		tViewer.setInput(us);
	}

	private void removeCols(){
		final Table table = tViewer.getTable();
		while ( table.getColumnCount() > 0 ) {
			table.getColumns()[0].dispose();
		}
	}
	public void setLocale(String lang, String region) {

		ArrayList<String> newColNames = new ArrayList<String>();
		ResourceBundle res;
		try {
			currentLocale = new Locale(lang, region);
		} catch (Exception e) {
			currentLocale = Locale.getDefault();
		}
		res = ResourceBundle.getBundle("LanguageBundle", currentLocale);
		Field[] f = UserModelProvider.INSTANCE.getFields();
		for (int i = 0; i < f.length; i++) {
			newColNames.add(res.getString(f[i].getName()));;
		}
		this.localColNames = newColNames;

		removeCols();
		createColumns(tViewer);
	}
}
