package br.com.adamis.montabit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.adamis.montabit.view.defaultModel.TableBitData;


public class DesmontaController implements Runnable {

	private List<HashMap<String, Object>> bits = new ArrayList<HashMap<String,Object>>();
	private String texto;
	private JTable jtable;
	private JProgressBar pbarProcess;

	public DesmontaController(JTable jtable, JProgressBar pbarProcess) {
		this.jtable = jtable;
		this.pbarProcess = pbarProcess;
	}

	public void setBit(String bit, int size) {
		HashMap<String, Object> hm = new HashMap<String, Object>();
		hm.put("bit", bit);
		hm.put("size", size);		
		bits.add(hm); 		
	}

	@Override
	public void run() {		
		
		pbarProcess.setValue(10);
		TableBitData tableBitData = new TableBitData();
		
		DefaultTableModel model = tableBitData.getDefaultTableModel();
		
		for (HashMap<String, Object> bit : bits) {
			
			pbarProcess.setValue(pbarProcess.getValue()+1);
			
			if(texto.indexOf(bit.get("bit").toString()) > -1){		
				try {
					int start = texto.indexOf(bit.get("bit").toString())+4;				
					String size = texto.substring(start, start+Integer.valueOf(bit.get("size").toString()));				
					String value = texto.substring(start+3,start+3+Integer.parseInt(size));

					model.addRow(new Object[]{bit.get("bit").toString(), size, value});
					texto = texto.replace(bit.get("bit").toString()+size+value, "");
				} catch (Exception e) {
					model.addRow(new Object[]{bit.get("bit").toString(), 0, "Impossivel Ler!"});
				}
				
				

			}else {
				model.addRow(new Object[]{bit.get("bit").toString(), 0, "BIT NÃO ENCONTRADO"});
				//System.err.println("BIT NÃO ENCONTRADO: "+bit.toString());	
			}			

		}
		
		pbarProcess.setValue(100);

		jtable.setModel(model);
		tableBitData.setjTable(jtable);
	}


	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
