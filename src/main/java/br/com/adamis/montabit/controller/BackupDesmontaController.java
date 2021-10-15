package br.com.adamis.montabit.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableModel;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.adamis.montabit.dto.BackupDesmontaDTO;
import br.com.adamis.montabit.dto.BitsDTO;
import br.com.adamis.montabit.resources.ResourcesImages;
import br.com.adamis.montabit.util.Utils;
import br.com.adamis.montabit.view.desmontabit.DesmontaBit;

public class BackupDesmontaController implements Runnable {

	private JTextArea jTextArea;
	private JTable jTableBits;
	private String filePath;
		
	public BackupDesmontaController(JTextArea jTextArea,JTable jTableBits, String filePath) {
		this.jTextArea = jTextArea;
		this.jTableBits = jTableBits;
		this.filePath = filePath;
	}
	
	@Override
	public void run() {
		DesmontaBit.pbarProcess.setValue(0);
		
		List<BitsDTO> listBits = new ArrayList<BitsDTO>();
		
		BackupDesmontaDTO backupDesmontaDTO = new BackupDesmontaDTO();
		backupDesmontaDTO.setBitString(jTextArea.getText());
				
		DesmontaBit.pbarProcess.setValue(10);
		
		TableModel model = jTableBits.getModel();
		for (int count = 0; count < model.getRowCount(); count++){
			DesmontaBit.pbarProcess.setValue(DesmontaBit.pbarProcess.getValue()+1);
			
			BitsDTO bitsDTO = new BitsDTO();
			
			bitsDTO.setBit(model.getValueAt(count, 0)+"");
			bitsDTO.setLength(Integer.valueOf(model.getValueAt(count, 1)+""));					
			listBits.add(bitsDTO);
			
		}		
		
		backupDesmontaDTO.setListBits(listBits);
		
		ObjectMapper mapper = new ObjectMapper();
		  String json;
		try {
			json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(backupDesmontaDTO);
			//System.out.println("ResultingJSONstring = " + json);
			Utils.writeTxt(this.filePath, json, true);
		
			JOptionPane.showMessageDialog(null, "Arquivo gerado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE, ResourcesImages.success());
			
		} catch (Exception e) {			
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE, ResourcesImages.fail());
		}		  
		
		
		DesmontaBit.pbarProcess.setValue(100);
	}

}
