

import model.ConectorDB;
import view.ModoGrafico.FramePrincipal;


import javax.swing.*;


public class App {
	public static void main(String[] args) {

		FramePrincipal teste = new FramePrincipal();

		JButton[] botoes = teste.getBotoes();

		JButton botao = botoes[0];
		JButton bsair = botoes[1];

		botao.addActionListener(e ->{
			teste.getPainelPrincipal().setLayer(teste.getPainelLogin(), JLayeredPane.DEFAULT_LAYER);
			teste.getPainelPrincipal().setLayer(teste.getPainelPrograma(), JLayeredPane.PALETTE_LAYER);
			teste.getPainelPrincipal().revalidate();
			teste.getPainelPrincipal().repaint();

		});

		ConectorDB conector = new ConectorDB();
		conector.getConnection();
		teste.testeTabela(conector.testeTabela());

		bsair.addActionListener(e -> System.exit(0));

//		ControleModoTexto start = new ControleModoTexto();
//		start.start();
//		System.exit(0);
	}
}


