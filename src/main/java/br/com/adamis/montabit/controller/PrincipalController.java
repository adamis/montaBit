/**
 * 
 */
package br.com.adamis.montabit.controller;

import br.com.adamis.montabit.util.Utils;

/**
 * @author F5K5WQI
 *
 */
public class PrincipalController implements Runnable {
	
	StringBuilder stringBuilder = new StringBuilder();

	public void addParam(String tagID, int size, String value) {
		if(size > 0) {
			stringBuilder.append(tagID);
			stringBuilder.append(Utils.format(size, 3, 'L', '0'));
			stringBuilder.append(Utils.format(value, size, 'L', '0'));
		}else {
			stringBuilder.append(tagID);
			stringBuilder.append(Utils.format(value.length(), 3, 'L', '0'));
			stringBuilder.append(value);
		}
	}
	
	@Override
	public void run() {
		
		
		
		
		//CONTEUDO
		
		System.err.println(""+stringBuilder.toString());
		//System.err.println(""+Utils.format("1000120013001800", 24, 'L', '0'));
	}

}
