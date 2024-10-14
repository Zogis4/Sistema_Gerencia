package view.ModoGrafico;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import model.identificadores.RetornoDescribe;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Vector;

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

            UIManager.put("Table.rowHeight", 25); // Altura das linhas
            UIManager.put("Table.intercellSpacing", new Dimension(3, 3)); // Espaço entre células
            UIManager.put("Table.selectionInsets", new Insets(1, 1, 1, 1)); // Margens da seleção
            UIManager.put("Table.selectionArc", 10); // Arredondamento da seleção
            UIManager.put("Table.cellMargins", new Insets(5, 5, 5, 5));


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

    public void testeTabela(RetornoDescribe retorno){
        JTabbedPane tabbedPane = painelPrograma.getTabbedPane();
        JTable tabela = painelPrograma.atualizarTabelaFuncionarios(retorno.vectorDados,retorno.vectorNomeColunas);

        tabela.putClientProperty("JTable.cellRenderer", true);
        tabela.putClientProperty("JTable.alternateRowColor", true);

        tabela.putClientProperty(FlatClientProperties.COMPONENT_ROUND_RECT, true); // Bordas arredondadas
        tabela.putClientProperty(FlatClientProperties.SCROLL_PANE_SMOOTH_SCROLLING, true); // Habilita rolagem suave
        tabela.putClientProperty(FlatClientProperties.SCROLL_BAR_SHOW_BUTTONS, true); // Botões visíveis nas barras de rolagem
        tabela.putClientProperty(FlatClientProperties.COMPONENT_ROUND_RECT, true); // Habilita bordas arredondadas
        //tabela.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS); // Redimensionamento automático

        // Ajuste de largura e redimensionamento automático
        //tabela.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        //tabela.getColumnModel().getColumn(0).setPreferredWidth(100);  // Exemplo de largura inicial

        // Estilo de seleção
        tabela.setSelectionBackground(new Color(100, 149, 237));
        tabela.setSelectionForeground(Color.BLACK);

        tabela.setCursor(new Cursor(Cursor.HAND_CURSOR));  // Muda para cursor de "mão"

        // Ajustando fonte e altura das células
        tabela.setFont(new Font("Arial", Font.PLAIN, 12));

        tabela.setShowGrid(true);
        tabela.setGridColor(Color.GRAY);
        tabela.setShowHorizontalLines(true);
        tabela.setShowVerticalLines(true);

        // Customização do JScrollPane
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(192, 192, 192), 1));
        scrollPane.setViewportBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        JTableHeader header = tabela.getTableHeader();
        header.setFont(new Font("Arial", Font.PLAIN, 12));
        header.setBackground(Color.LIGHT_GRAY);
        header.setForeground(Color.BLACK);

        // Configura o layout corretamente para evitar deslocamentos
        JPanel painelTeste = new JPanel(new BorderLayout());
        painelTeste.add(scrollPane, BorderLayout.CENTER);

        tabbedPane.addTab("Teste", painelTeste);
        tabbedPane.revalidate();
        tabbedPane.repaint();

        // Alinhamento e renderização com bordas suaves
        tabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(
                    JTable table, Object value, boolean isSelected, boolean hasFocus,
                    int row, int column) {

                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
                label.setHorizontalAlignment(SwingConstants.CENTER);

                if (!isSelected) {
                    if (row % 2 == 0) label.setBackground(new Color(245, 245, 245));
                    else label.setBackground(Color.lightGray);
                }
                return label;
            }
        });

        // Tooltip nas células
        tabela.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                int row = tabela.rowAtPoint(e.getPoint());
                int col = tabela.columnAtPoint(e.getPoint());
                if (row > -1 && col > -1) {
                    Object value = tabela.getValueAt(row, col);
                    if (value != null) {
                        tabela.setToolTipText(value.toString());
                    }
                }
            }
        });
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
