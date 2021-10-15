package br.com.adamis.montabit.resources;


import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;

/**
 * @author Adamis.Rocha
 * @since 1.0, 31 de ago de 2017
 */
public class ResourcesImages {

	public static ImageIcon questao() {
		return toolkitIcon("questao.png");
	}

	public static Image questaoImg() {
		return toolkit("questao.png");
	}

	public static ImageIcon success() {
		return toolkitIcon("success.png");
	}

	public static Image successImg() {
		return toolkit("success.png");
	}	

	public static ImageIcon fail() {
		return toolkitIcon("fail.png");
	}

	public static Image failImg() {
		return toolkit("fail.png");
	}	

	public static ImageIcon sistema() {
		return toolkitIcon("sistema.png");
	}

	public static Image sistemaImg() {
		return toolkit("sistema.png");
	}
	
	public static ImageIcon monta() {
		return toolkitIcon("montaBit.png");
	}

	public static Image montaImg() {
		return toolkit("montaBit.png");
	}
	
	public static ImageIcon desmonta() {
		return toolkitIcon("desmontaBit.png");
	}

	public static Image desmontaImg() {
		return toolkit("desmontaBit.png");
	}
	
	
	private static Image toolkit(String img) {

		Class<?> clazz = ResourcesImages.class;
		ClassLoader classLoader = clazz.getClassLoader();
		return Toolkit.getDefaultToolkit().getImage(classLoader.getResource(img));
	}

	private static ImageIcon toolkitIcon(String img) {

		Class<?> clazz = ResourcesImages.class;
		ClassLoader classLoader = clazz.getClassLoader();
		return new ImageIcon(classLoader.getResource(img));

		// return new ImageIcon(ResourcesImages.class.getResource(img));
	}

}