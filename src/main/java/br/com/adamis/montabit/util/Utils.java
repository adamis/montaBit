package br.com.adamis.montabit.util;

import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;

public class Utils {

	public static void main(String[] args) {
		//System.err.println(""+format("00:00|00:00|00:00|00:00|", 16, 'L', '0'));
		String montaString = montaString("123456", "0", 2);
		System.err.println("MontaString: "+montaString);
	}

	public static String format(Object arg0, int length, char position, char complement) {

		if (arg0 == null) {
			arg0 = "";
		}

		int tamanhoOriginal = arg0.toString().length();
		StringBuffer sb = new StringBuffer(arg0.toString());

		if (tamanhoOriginal > length) {
			if (position == 'D' || position == 'R') {
				sb.setLength(length);
			} else if (position == 'E' || position == 'L') {
				sb.delete(0, tamanhoOriginal - length);
			}
		} else {
			if (position == 'D' || position == 'R') {
				for (int i = tamanhoOriginal; i < length; i++) {
					sb.append(complement);
				}
			} else if (position == 'E' || position == 'L') {
				for (int i = tamanhoOriginal; i < length; i++) {
					sb.insert(0, complement);
				}
			}
		}

		return sb.toString();
	}

	public static String montaString(String text,String caracter, int size) {

		int verif = size - text.length();		
		String textTemp = "";

		if(verif > 0) {			
			textTemp = text;
			for (int i = 0; i < verif; i++) {
				textTemp = caracter+textTemp;
			}

		}else if(verif == 0) {
			textTemp = text;

		}else if(verif < 0) {

			String[] montado = text.split("");

			for (int i = 0; i < size; i++) {
				textTemp += montado[i];
			}

		}

		return textTemp;
	}

	public static void numberOnly(JTextField jTextField,final Toolkit toolkit) {
		jTextField.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if (!((c >= '0') && (c <= '9') ||
						(c == KeyEvent.VK_BACK_SPACE) ||
						(c == KeyEvent.VK_DELETE))) {					
					toolkit.beep();
					e.consume();
				}
			}
		});
	}

	public static String readTxt(String url) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(url));
		String text = "";
		while (br.ready()) {
			text += br.readLine();
		}
		br.close();
		return text;
	}

	public static List<String> readTxtList(String url) throws IOException {
		List<String> lista = new ArrayList<String>();

		final BufferedReader br = new BufferedReader(new FileReader(url));

		while (br.ready()) {
			lista.add(br.readLine());
		}
		br.close();
		return lista;
	}

	public static void writeTxt(String file, String text, boolean replaceFile) throws Exception {

		file = file.trim();

//		System.err.println("File: " + file);
//		System.err.println("text: " + text);
//		System.err.println("Replace: " + replaceFile);

		new File(file).mkdirs();

		if (replaceFile && new File(file).exists()) {
			new File(file).delete();
		}

		// FileWriter arq = new FileWriter(file);
		PrintWriter gravarArq = new PrintWriter(new File(file), "UTF-8");

		// System.err.println(""+text);

		// gravarArq.printf(text);
		gravarArq.write(text);
		gravarArq.close();
	}

	public static void writeTxtList(String file, List<String> textList, boolean replaceFile) throws Exception {
		if (replaceFile && new File(file).exists()) {
			new File(file).delete();
		}
		FileWriter arq = new FileWriter(file);
		PrintWriter gravarArq = new PrintWriter(arq);

		for (int i = 0; i < textList.size(); i++) {
			gravarArq.print(textList.get(i) + "\r\n");
		}
		arq.close();
	}
	
}
