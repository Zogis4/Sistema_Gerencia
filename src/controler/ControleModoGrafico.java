package controler;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import model.identificadores.Funcionario;
import model.identificadores.Login;
import model.identificadores.Pedido;
import model.identificadores.Produto;
import view.ModoGrafico.TelaLogin;
import view.ModoGrafico.TelaPrograma;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ControleModoGrafico {
    private ArrayList<Funcionario> funcionarios = new ArrayList<>();
    private ArrayList<Pedido> pedidos = new ArrayList<>();
    private ArrayList<Produto> produtos = new ArrayList<>();
    private ArrayList<Login> usuarios = new ArrayList<>();
    private JFrame frame;

    public ControleModoGrafico(){
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
                frame = new JFrame("Sistema de Gerenciamento de Inventário");
                TelaLogin telaLogin = new TelaLogin();
                frame.setContentPane(telaLogin.renderizarPainelLogin(frame));
                telaLogin.configurarAcoes(frame, new TelaLogin(), new TelaPrograma());



                frame.setSize(700, 600);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
