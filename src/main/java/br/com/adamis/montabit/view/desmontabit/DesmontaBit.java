package br.com.adamis.montabit.view.desmontabit;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import br.com.adamis.montabit.controller.BackupDesmontaController;
import br.com.adamis.montabit.controller.CarregarBackupController;
import br.com.adamis.montabit.controller.DesmontaController;
import br.com.adamis.montabit.interfaces.ShowHideInterface;
import br.com.adamis.montabit.resources.ResourcesImages;
import br.com.adamis.montabit.util.Utils;
import br.com.adamis.montabit.view.defaultModel.TableBit;
import br.com.adamis.montabit.view.defaultModel.TableBitData;

public class DesmontaBit extends JInternalFrame implements ShowHideInterface{

	private static final long serialVersionUID = -6337340778493337552L;
	private JTable table;
	private JTable tableResult = new JTable();
	private JTextField tx_bit;
	private JTextField tx_length;
	private JTextArea textArea;
	public static JProgressBar pbarProcess;
	private ClickBotaoDireitoRemove botaoDireitoRemove;
	private JButton btn_carregar;
	

	/**
	 * Create the frame.
	 */
	public DesmontaBit() {
		botaoDireitoRemove = new ClickBotaoDireitoRemove();
		//setIconImage(ResourcesImages.fail());
		this.setFrameIcon(ResourcesImages.desmonta());
		setClosable(true);	
		setTitle("Desmontar Bits");		
		setBounds(100, 100, 868, 606);
		getContentPane().setLayout(null);
		
		JLabel lblBitsMontados = new JLabel("Bits Montados:");
		lblBitsMontados.setHorizontalAlignment(SwingConstants.LEFT);
		lblBitsMontados.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBitsMontados.setBounds(15, 11, 133, 14);
		getContentPane().add(lblBitsMontados);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(9, 36, 834, 152);
		getContentPane().add(textArea);

		JButton btnProcessar = new JButton("Processar");
		btnProcessar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				pbarProcess.setValue(0);
				DesmontaController desmontaController = new DesmontaController(tableResult,pbarProcess);
				desmontaController.setTexto(textArea.getText());

				TableModel model = table.getModel();
				for (int count = 0; count < model.getRowCount(); count++){					
					desmontaController.setBit(model.getValueAt(count, 0)+"",Integer.valueOf(model.getValueAt(count, 1)+""));
				}

				desmontaController.run();
			}
		});
		btnProcessar.setBounds(357, 381, 108, 30);
		getContentPane().add(btnProcessar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(9, 240, 834, 130);
		getContentPane().add(scrollPane);

		table = new JTable();
		botaoDireitoRemove.clickSelectTable(table);		
		TableBit tableBit = new TableBit();
		table.setModel(tableBit.getDefaultTableModel());		
//		table.setAutoCreateRowSorter(true);
		table.getColumnModel().getColumn(0).setPreferredWidth(133);
		table.getColumnModel().getColumn(0).setMaxWidth(333);
		tableBit.setjTable(table);
		

		//		table.getColumnModel().getColumn(1).setPreferredWidth(133);
		//		table.getColumnModel().getColumn(1).setMaxWidth(150);

		scrollPane.setViewportView(table);

		JButton btn_adicionar = new JButton("Adicionar");
		btn_adicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pbarProcess.setValue(0);
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				if(tx_length.getText().length()>0) {
					model.addRow(new Object[]{tx_bit.getText(), tx_length.getText()});	
				}else {
					model.addRow(new Object[]{tx_bit.getText(), 3});
				}
									
				clearForm();
			}			
		});

		btn_adicionar.setBounds(357, 199, 108, 30);
		getContentPane().add(btn_adicionar);

		tx_bit = new JTextField();
		tx_bit.setBounds(88, 199, 86, 30);
		getContentPane().add(tx_bit);
		tx_bit.setColumns(10);

		JLabel lblNewLabel = new JLabel("Bit:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 199, 68, 30);
		getContentPane().add(lblNewLabel);

		JLabel lblLength = new JLabel("Length:");
		lblLength.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLength.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLength.setBounds(177, 199, 68, 30);
		getContentPane().add(lblLength);

		tx_length = new JTextField();
		tx_length.setText("3");
		Utils.numberOnly(tx_length, getToolkit());		
		tx_length.setColumns(10);
		tx_length.setBounds(255, 199, 86, 30);
		getContentPane().add(tx_length);

		JScrollPane scListaBit = new JScrollPane();
		scListaBit.setBounds(9, 422, 834, 143);

		TableBitData tableBitData = new TableBitData();		
		tableResult.setModel(tableBitData.getDefaultTableModel());
		tableBitData.setjTable(tableResult);
		
//		tableResult.getColumnModel().getColumn(0).setPreferredWidth(133);
//		tableResult.getColumnModel().getColumn(0).setMaxWidth(150);
//		tableResult.getColumnModel().getColumn(1).setPreferredWidth(70);
//		tableResult.getColumnModel().getColumn(2).setPreferredWidth(420);

		//		table.getColumnModel().getColumn(1).setPreferredWidth(133);
		//		table.getColumnModel().getColumn(1).setMaxWidth(150);

		scListaBit.setViewportView(tableResult);

		getContentPane().add(scListaBit);

		btn_carregar = new JButton("Carregar");
		btn_carregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//CARREGAR DADOS
				JFileChooser jFileChooser=new JFileChooser();				   
				int result= jFileChooser.showOpenDialog(btn_carregar);
				if(result==JFileChooser.APPROVE_OPTION)
				{
					File file = jFileChooser.getSelectedFile();		            
		            System.err.println("Opening: " + file.getAbsolutePath());
		            
		            CarregarBackupController carregarBackupController = new CarregarBackupController(file.getAbsolutePath(),textArea,table);
		            carregarBackupController.run();
		            
				}
				
			}
		});
		btn_carregar.setBounds(617, 199, 108, 30);
		getContentPane().add(btn_carregar);

		JButton btn_adicionar_2 = new JButton("Backup");
		btn_adicionar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				//String filename = JOptionPane.showInputDialog("Name this file");
				JFileChooser chooser = new JFileChooser();
				chooser.setAcceptAllFileFilterUsed(false);
				chooser.setCurrentDirectory(new File("/home/me/Documents"));

				chooser.setFileFilter(new FileFilter() {

					@Override
					public String getDescription() {

						return "JSON File(*.json)";
					}

					@Override
					public boolean accept(File f) {
						if (f.isDirectory()) {
							return true;
						} else {
							String filename = f.getName().toLowerCase();
							return filename.endsWith(".json") ;
						}
					}
				});


				int retrival = chooser.showSaveDialog(null);
				if (retrival == JFileChooser.APPROVE_OPTION) {
					System.err.println("FILE> "+chooser.getSelectedFile());

					if(chooser.getSelectedFile().getName().endsWith(".json") || chooser.getSelectedFile().getName().endsWith(".JSON")) {
						//COM .JSON

						BackupDesmontaController backupDesmontaController = new BackupDesmontaController(textArea, table, chooser.getSelectedFile().getAbsolutePath());
						backupDesmontaController.run();

					}else {
						//COM .JSON						
						BackupDesmontaController backupDesmontaController = new BackupDesmontaController(textArea, table, chooser.getSelectedFile().getAbsolutePath()+".json");
						backupDesmontaController.run();
					}

				}
			}
		});
		btn_adicionar_2.setBounds(735, 199, 108, 30);
		getContentPane().add(btn_adicionar_2);

		pbarProcess = new JProgressBar();
		pbarProcess.setStringPainted(true);
		pbarProcess.setBounds(169, 8, 674, 20);
		getContentPane().add(pbarProcess);

	}

	private void clearForm() {
		tx_bit.setText("");
		tx_length.setText("3");		
	}



	@Override
	public void showHide(boolean state) {
		setVisible(state);		
	}
}
