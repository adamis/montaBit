package br.com.adamis.montabit.view.montatag;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import br.com.adamis.montabit.interfaces.ShowHideInterface;
import br.com.adamis.montabit.resources.ResourcesImages;
import br.com.adamis.montabit.util.Utils;
import br.com.adamis.montabit.view.desmontabit.ClickBotaoDireitoRemove;

public class MontaBit extends JInternalFrame implements ShowHideInterface{

	private static final long serialVersionUID = 2391523257677462640L;
	private JTextField tagsInputText;
	private JTextField tamanhoInputText;
	private JTextField valorInputText;
	private JTable table;
	private boolean controlAuto = false;
	private String mensagem = "<HTML><BODY> O valor ira ser cortado de acordo com o campo <b>TAMANHO</b> </BODY></HTML>";
	private JTextField caracterInputText;	
	private JTextArea textArea;
	private ClickBotaoDireitoRemove botaoDireitoRemove;

	/**
	 * Create the frame.
	 */
	public MontaBit() {
		
		botaoDireitoRemove = new ClickBotaoDireitoRemove();
		setTitle("Monta Tags");
		this.setFrameIcon(ResourcesImages.monta());
		setClosable(true);		
		setBounds(100, 100, 860, 693);
		getContentPane().setLayout(null);

		textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setBounds(5, 464, 834, 152);
		getContentPane().add(textArea);

		tagsInputText = new JTextField();
		Utils.numberOnly(tagsInputText,getToolkit());
		tagsInputText.setHorizontalAlignment(SwingConstants.CENTER);
		tagsInputText.setBounds(10, 55, 133, 30);
		getContentPane().add(tagsInputText);
		tagsInputText.setColumns(10);

		tamanhoInputText = new JTextField();
		Utils.numberOnly(tamanhoInputText,getToolkit());
		tamanhoInputText.setHorizontalAlignment(SwingConstants.CENTER);
		tamanhoInputText.setBounds(150, 55, 108, 30);
		getContentPane().add(tamanhoInputText);
		tamanhoInputText.setColumns(10);

		valorInputText = new JTextField();		
		valorInputText.setBounds(265, 55, 521, 30);
		getContentPane().add(valorInputText);
		valorInputText.setColumns(10);

		JLabel lblNewLabel = new JLabel("Tag*");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 35, 133, 14);
		getContentPane().add(lblNewLabel);

		JLabel lblTamanho = new JLabel("Tamanho");
		lblTamanho.setHorizontalAlignment(SwingConstants.CENTER);
		lblTamanho.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTamanho.setBounds(150, 36, 108, 14);
		getContentPane().add(lblTamanho);

		JLabel lblValor = new JLabel("Valor*");
		lblValor.setHorizontalAlignment(SwingConstants.CENTER);
		lblValor.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblValor.setBounds(265, 35, 521, 14);
		getContentPane().add(lblValor);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 197, 829, 202);
		getContentPane().add(scrollPane);

		table = new JTable();
		botaoDireitoRemove.clickSelectTable(table);
		table.setModel(new DefaultTableModel(new Object[][] {},new String[] {"Tag", "Tamanho", "Valor"}));		
		table.getColumnModel().getColumn(0).setPreferredWidth(133);
		table.getColumnModel().getColumn(0).setMaxWidth(150);
		table.getColumnModel().getColumn(1).setPreferredWidth(133);
		table.getColumnModel().getColumn(1).setMaxWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(100);
		table.getColumnModel().getColumn(2).setMinWidth(100);
		scrollPane.setViewportView(table);

		JButton btnNewButton = new JButton("Montar Tags");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				StringBuilder sb = new StringBuilder();
				DefaultTableModel model = (DefaultTableModel) table.getModel();

				for (int i = 0; i < model.getRowCount(); i++) {
					sb.append(getValueforCellTable(model, i, 0));
					sb.append(getValueforCellTable(model, i, 1));
					sb.append(getValueforCellTable(model, i, 2));
				}
				textArea.setText(sb.toString());

			}
		});
		btnNewButton.setBounds(368, 412, 89, 30);
		getContentPane().add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Adicionar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel model = (DefaultTableModel) table.getModel();

				try {
					boolean valid = true;
					valid = validate(valid, tagsInputText.getText());
					valid = validate(valid, valorInputText.getText());

					if(valid) {
						String tamanhoini = tamanhoInputText.getText();
						String tagsMontado = valorInputText.getText();

						if(controlAuto) {
							tamanhoini = checkSize(""+tagsMontado.length());
						}else {
							int tamanhoFixo = Integer.valueOf(tamanhoInputText.getText());
							tamanhoini = checkSize(tamanhoini);
							tagsMontado = Utils.montaString(tagsMontado, caracterInputText.getText(), tamanhoFixo);
						}

						model.addRow(new Object[]{tagsInputText.getText(),tamanhoini,tagsMontado });					
						clearForm();
					}else {
						JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
					}

				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}


		});
		btnNewButton_1.setBounds(368, 155, 108, 30);
		getContentPane().add(btnNewButton_1);

		JLabel lblBitsMontados = new JLabel("Tags Montados:");
		lblBitsMontados.setHorizontalAlignment(SwingConstants.LEFT);
		lblBitsMontados.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblBitsMontados.setBounds(10, 439, 133, 14);
		getContentPane().add(lblBitsMontados);

		JCheckBox chkTamanhoAuto = new JCheckBox("Auto Tamanho");
		chkTamanhoAuto.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {

				if(e.getStateChange() == ItemEvent.SELECTED) {
					tamanhoInputText.setEnabled(false);
					tamanhoInputText.setText("");
					controlAuto = true;	
				}else {
					tamanhoInputText.setEnabled(true);
					controlAuto = false;
				}

			}
		});
		chkTamanhoAuto.setBounds(150, 92, 108, 23);
		getContentPane().add(chkTamanhoAuto);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {


			@Override
			public void mouseClicked(MouseEvent e) {
				JOptionPane.showMessageDialog(null, mensagem , "Question",JOptionPane.INFORMATION_MESSAGE);				
			}
		});
		lblNewLabel_1.setToolTipText(mensagem);
		lblNewLabel_1.setIcon(ResourcesImages.questao());
		lblNewLabel_1.setBounds(793, 35, 46, 50);
		getContentPane().add(lblNewLabel_1);

		caracterInputText = new JTextField();
		maxChar(caracterInputText, 1);
		setSublinhado(caracterInputText);
		caracterInputText.setHorizontalAlignment(SwingConstants.CENTER);
		caracterInputText.setColumns(1);
		caracterInputText.setBounds(736, 96, 50, 30);
		getContentPane().add(caracterInputText);

		JLabel lblPreencherCom = new JLabel("Preencher com:");
		lblPreencherCom.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPreencherCom.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPreencherCom.setBounds(593, 104, 133, 14);
		getContentPane().add(lblPreencherCom);
		
		JButton btnCopiar = new JButton("Copiar");
		btnCopiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StringSelection stringSelection = new StringSelection(textArea.getText());
				Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
				clpbrd.setContents(stringSelection, null);
			}
		});
		btnCopiar.setBounds(750, 627, 89, 30);
		getContentPane().add(btnCopiar);

	}



	public String getValueforCellTable(DefaultTableModel model , int row, int col){				
		return (String) model.getValueAt(row, col).toString();
	}


	private void clearForm() {
		tagsInputText.setText("");
		tamanhoInputText.setText("");
		valorInputText.setText("");
	}

	private String checkSize(String text) {		
		if(text.length() <= 3) {
			return Utils.montaString(text,"0",3);
		}else {
			return Utils.montaString(text,"0",text.length());
		}		
	}


	private void maxChar(final JTextField jTextField, final int size) {
		jTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) { 
				if (jTextField.getText().length() >= size ) // limit textfield to 3 characters
					e.consume(); 
			}  
		});
		
		
		
	}

	@SuppressWarnings("unchecked")
	private void setSublinhado(JTextField jTextField) {
		Font font = jTextField.getFont();
		@SuppressWarnings("rawtypes")
		Map attributes = font.getAttributes();
		attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
		jTextField.setFont(font.deriveFont(attributes));
	}
	
	private boolean validate(boolean valid, String text) {
		if(valid) {
			if(text != null && text.length() > 0) {
				return true;
			}else {
				return false;
			}
		}else {
			return valid;
		}
	}


	@Override
	public void showHide(boolean state) {
		setVisible(state);
	}
}
