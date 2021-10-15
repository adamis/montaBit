package br.com.adamis.montabit.view.desmontabit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ClickBotaoDireitoRemove {
	
	
	public JPopupMenu popupmenu;
	

	public void clickSelectTable(final JTable jTable) {
		jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		jTable.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
	        public void valueChanged(ListSelectionEvent event) {
	            clickBotaoDireito(jTable);
	        }
	    });
	}
	
	public void clickBotaoDireito(final JTable table) {
		popupmenu = new JPopupMenu("Edit");
				
		JMenuItem remover = new JMenuItem("Remover");  
		
		remover.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				if(DesmontaBit.pbarProcess != null) {					
					DesmontaBit.pbarProcess.setValue(0);
				}
				
				int row = table.getSelectedRow();
				DefaultTableModel model = (DefaultTableModel) table.getModel();				
				model.removeRow(table.convertRowIndexToModel(row));				
			}
			
		});
				
		popupmenu.add(remover); 
		table.setComponentPopupMenu(popupmenu);		
		
		table.addMouseListener(new MouseAdapter() { 
			
            public void mouseClicked(MouseEvent e) {
            	
            	int row = table.getSelectedRow();            	
            	
            	if( (e.getButton() == MouseEvent.BUTTON2) && (row >= 0) ) {
            		popupmenu.show(table , e.getX(), e.getY());
            	}
            	
            }
            
         });  
		
	}

}