package br.com.adamis.montabit.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import br.com.adamis.montabit.interfaces.ShowHideInterface;
import br.com.adamis.montabit.resources.ResourcesImages;
import br.com.adamis.montabit.util.Styles;
import br.com.adamis.montabit.view.desmontabit.DesmontaBit;
import br.com.adamis.montabit.view.montatag.MontaBit;

public class Principal extends JFrame implements ShowHideInterface{

	private static final long serialVersionUID = 8801973136200119064L;
	private JPanel contentPane;
	JDesktopPane conteudo;

	/**
	 * Create the frame.
	 */
	public Principal() {
		
		setTitle("Bit Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 909, 609);
		setIconImage(ResourcesImages.sistemaImg());
		
		try {
			UIManager.setLookAndFeel(Styles.style);
			setDefaultLookAndFeelDecorated(true);			
			//this.setIconImage(ResourcesImages.icon2());
		} catch (Exception whoJackedMyIcon) {
			System.out.println("Could not load program icon.");
		}

		setExtendedState(Frame.MAXIMIZED_BOTH);	
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Segoe UI Historic", Font.PLAIN, 14));
		setJMenuBar(menuBar);
		
		JLabel lblNewLabel = new JLabel("  ");
		menuBar.add(lblNewLabel);
		
		JMenu menuArquivo = new JMenu("Arquivo");
		menuBar.add(menuArquivo);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Exit");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(EXIT_ON_CLOSE);
			}
		});
		menuArquivo.add(mntmNewMenuItem);
		
		JMenu menuExecutar = new JMenu("Executar");
		menuBar.add(menuExecutar);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Montar Bit");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MontaBit montaBit = new MontaBit();
				conteudo.add(montaBit);
				montaBit.showHide(true);				
				//conteudo.revalidate();
				//conteudo.repaint();
				montaBit.moveToFront();
			}
		});
		menuExecutar.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Encontrar Bit");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Encontrar Bit
				DesmontaBit desmontaBit = new DesmontaBit();
				conteudo.add(desmontaBit);
				desmontaBit.showHide(true);				
				//conteudo.revalidate();
				//conteudo.repaint();
				desmontaBit.moveToFront();
			}
		});
		menuExecutar.add(mntmNewMenuItem_2);
		
//		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Auto Bit");
//		mntmNewMenuItem_3.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				//Auto Bit
//			}
//		});
//		menuExecutar.add(mntmNewMenuItem_3);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		conteudo = new JDesktopPane(){            
            //Image newimage = image.getScaledInstance(scalx, scaly, Image.SCALE_SMOOTH);
			//Image newimage = ResourcesImages.
						
			private static final long serialVersionUID = -2805003907297471914L;

			@Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.GRAY);
                g.fillRect(0, 0, getWidth(), getHeight());
                //g.drawImage(newimage, 0, 0, this);
            }
        };

		contentPane.add(conteudo, BorderLayout.CENTER);
		
	}

	@Override
	public void showHide(boolean state) {
		setVisible(state);
	}
}
