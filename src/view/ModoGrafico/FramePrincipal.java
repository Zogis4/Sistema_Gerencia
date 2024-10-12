package view.ModoGrafico;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import javax.swing.*;
import java.awt.*;

public class FramePrincipal {
    private JFrame frame;
    private TelaLogin painelLogin;
    private TelaPrograma painelPrograma;
    private JLayeredPane painelPrincipal;

    public FramePrincipal(){

        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
            UIManager.put("TabbedPane.background", new Color(74, 143, 211));
            UIManager.put("Panel.background", new Color(124, 173, 223));

        } catch (Exception e) {
            e.printStackTrace();
        }

        frame = new JFrame("Sistema de Gerenciamento de Inventário");


        JMenuBar menuBar = criarMenuBar();
        frame.setJMenuBar(menuBar);


        painelLogin = new TelaLogin();
        painelLogin.setBounds(0,0,700,600);


        painelPrograma = new TelaPrograma();
        painelPrograma.setBounds(0,0,700,600);


        painelPrincipal = new JLayeredPane();


        painelPrincipal.add(painelPrograma, JLayeredPane.DEFAULT_LAYER);
        painelPrincipal.setBounds(0,0,700,600);
        painelPrincipal.setVisible(true);
        painelPrincipal.add(painelLogin, JLayeredPane.PALETTE_LAYER);

        frame.setSize(700, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.add(painelPrincipal, BorderLayout.CENTER);
    }

    public JMenuBar criarMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Criando menus principais
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenu menuConfig = new JMenu("Configurações");
        JMenu menuAjuda = new JMenu("Ajuda");

        // Adicionando os menus à barra
        menuBar.add(menuArquivo);
        menuBar.add(menuConfig);
        menuBar.add(menuAjuda);

        // Criando itens de menu
        JMenuItem novoUsuario = new JMenuItem("Novo Usuario");
        JMenuItem options = new JMenuItem("Opções");
        JMenuItem ajuda = new JMenuItem("Sobre");

        menuArquivo.add(novoUsuario);
        menuConfig.add(options);
        menuAjuda.add(ajuda);

        configurarEventosMenu(novoUsuario, options, ajuda);

        return menuBar;
    }

    private void configurarEventosMenu(JMenuItem novoUsuario, JMenuItem options, JMenuItem ajuda) {
        // Listener para o menu Arquivo
        novoUsuario.addActionListener(e ->
                JOptionPane.showMessageDialog(null, "O botão Novo Usuário não está implementado.",
                        "Informação", JOptionPane.INFORMATION_MESSAGE));

        // Listener para o menu Configurações
        options.addActionListener(e ->
                JOptionPane.showMessageDialog(null, "O botão Opções não está implementado.",
                        "Informação", JOptionPane.INFORMATION_MESSAGE));

        // Listener para o menu Ajuda
        ajuda.addActionListener(e ->
                JOptionPane.showMessageDialog(null, "Sistema de Gerenciamento de Inventário\n" +
                        "Versão 1.0", "Sobre", JOptionPane.INFORMATION_MESSAGE));
    }

    //getters
    public JFrame getFrame() {
        return frame;
    }

    public JPanel getPainelLogin() {
        return painelLogin;
    }

    public JPanel getPainelPrograma() {
        return painelPrograma;
    }

    public JLayeredPane getPainelPrincipal() {
        return painelPrincipal;
    }

    public JButton[] getBotoes(){
        return new JButton[]{
            painelLogin.getButtonEntrar(),painelLogin.getButtonSair()
        };
    }
}
