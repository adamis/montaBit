package br.com.adamis.montabit.controller;

import java.io.IOException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.adamis.montabit.dto.BackupDesmontaDTO;
import br.com.adamis.montabit.dto.BitsDTO;
import br.com.adamis.montabit.resources.ResourcesImages;
import br.com.adamis.montabit.util.Utils;
import br.com.adamis.montabit.view.defaultModel.TableBit;
import br.com.adamis.montabit.view.desmontabit.DesmontaBit;

public class CarregarBackupController implements Runnable{
	
	private String absolutePath;
	private JTextArea textArea;
	private JTable table;
	
	public CarregarBackupController(String absolutePath, JTextArea textArea, JTable table) {
		this.absolutePath = absolutePath;
		this.textArea = textArea;
		this.table = table;
	}

	@Override
	public void run() {
		try {
			String readTxt = Utils.readTxt(absolutePath);			
			System.err.println(readTxt);
			
			DesmontaBit.pbarProcess.setValue(10);
			
			ObjectMapper mapper = new ObjectMapper();
			BackupDesmontaDTO readValue = mapper.readValue(readTxt, BackupDesmontaDTO.class);
						
			System.err.println("BIT> "+readValue.getBitString());			
			textArea.setText(readValue.getBitString());
			TableBit tableBit = new TableBit();
			DefaultTableModel model = tableBit.getDefaultTableModel();
			
			List<BitsDTO> listBits = readValue.getListBits();
			
			for (BitsDTO bitsDTO : listBits) {
				DesmontaBit.pbarProcess.setValue(DesmontaBit.pbarProcess.getValue()+1);
				model.addRow(new Object[]{ bitsDTO.getBit() , bitsDTO.getLength() });	
			}			
			
			table.setModel(model);
			tableBit.setjTable(table);
			
			DesmontaBit.pbarProcess.setValue(100);
			
			JOptionPane.showMessageDialog(null, "Arquivo lido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE, ResourcesImages.success());
		} catch (IOException e) {
			
			e.printStackTrace();
			DesmontaBit.pbarProcess.setValue(0);
			JOptionPane.showMessageDialog(null, "Erro: "+e.getMessage(), "Sucesso", JOptionPane.ERROR_MESSAGE, ResourcesImages.fail());
		}
		
		
	}
	

}
