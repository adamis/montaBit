package br.com.adamis.montabit.view.defaultModel;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableBitData {

	private DefaultTableModel defaultTableModel;
	private JTable jTable = null;

	public TableBitData() {
		defaultTableModel = new DefaultTableModel( new Object[][] {},
												   new String[] {"Bit", "Length", "Valor"}
												 ) 
		{
			private static final long serialVersionUID = -3200992902496721592L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] {
					String.class, String.class, String.class
			};
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};

	}

	
	public DefaultTableModel getDefaultTableModel() {		
		return defaultTableModel;
	}

	public void setDefaultTableModel(DefaultTableModel defaultTableModel) {
		this.defaultTableModel = defaultTableModel;
	}


	public JTable getjTable() {
		return jTable;
	}

	public void setjTable(JTable jTable) {
		if(jTable != null && defaultTableModel != null && jTable.getColumnModel() != null) {
			jTable.setAutoCreateRowSorter(true);
			jTable.getColumnModel().getColumn(0).setPreferredWidth(133);
			jTable.getColumnModel().getColumn(0).setMaxWidth(150);
			jTable.getColumnModel().getColumn(1).setPreferredWidth(50);
			jTable.getColumnModel().getColumn(2).setPreferredWidth(440);
		}
		this.jTable = jTable;
	}
}