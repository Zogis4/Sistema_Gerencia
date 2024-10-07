
import controler.ControleModoTexto;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import view.ModoGrafico.TelaLogin;
import view.ModoGrafico.TelaPrograma;

import javax.swing.*;
import java.awt.*;

public class App {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new FlatMacLightLaf());
			UIManager.put("TabbedPane.background", new Color(74, 143, 211));
			UIManager.put("Panel.background", new Color(124, 173, 223));

		} catch (Exception e) {
			e.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame frame = new JFrame("Sistema de Gerenciamento de Inventário");
				TelaLogin telaLogin = new TelaLogin();
				frame.setContentPane(telaLogin.renderizarPainelLogin(frame));
				telaLogin.configurarAcoes(frame, new TelaLogin(), new TelaPrograma());

				frame.setSize(700, 600);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});

		ControleModoTexto start = new ControleModoTexto();
		start.start();
		System.exit(0);
	}
}


