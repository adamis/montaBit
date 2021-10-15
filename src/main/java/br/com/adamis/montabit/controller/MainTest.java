package br.com.adamis.montabit.controller;

public class MainTest {

	public static void main(String[] args) {
		DesmontaController controller = new DesmontaController(null,null);
		controller.setTexto("04010120000004503880228003BIN02290142019100916454602300064100000231035FD002500-00 - POS MOVE2500 WIFI PGW0232004OPEN0905014201910100900000250011Andre Rocha0251025011998290368|0119982903680252038AVENIDA ANDRE RODRIGUES DE FREITAS 8710253007SICREDI0254016ITAPEMA DO NORTE02550038710256008892490000257009ITAPOA/SC0258002BR0259000026000002610000262015TESTE UAT POS06090000279000060500006060000607000");

		controller.setBit("0401",3);
		controller.setBit("0228",3);
		controller.setBit("0229",3);
		controller.setBit("0230",3);
		controller.setBit("0231",3);
		controller.setBit("0232",3);
		controller.setBit("0905",3);
		//controller.setBit("0250",3);
		controller.setBit("0251",3);
		controller.setBit("0252",3);
		controller.setBit("0253",3);
		controller.setBit("0254",3);
		controller.setBit("0255",3);
		controller.setBit("0256",3);
		controller.setBit("0257",3);		
		controller.setBit("0258",3);
		controller.setBit("0259",3);
		
		controller.setBit("0260",3);
		controller.setBit("0261",3);
		controller.setBit("0262",3);		
		controller.setBit("0605",3);
		controller.setBit("0606",3);
		controller.setBit("0607",3);
		controller.setBit("0609",3);
		controller.setBit("0279",3);
		
		controller.run();
	}	
	
}

