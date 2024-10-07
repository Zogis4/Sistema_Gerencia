package view.ModoGrafico;

import model.identificadores.Funcionario;
import model.identificadores.Pedido;
import model.identificadores.Produto;
import javax.swing.*;
import java.awt.*;

public class TelaPrograma {

    private ImageIcon logoIcon;
    private JPanel gradientPanel;
    private JTabbedPane tabbedPane;
    private JPanel arquivoPanel;
    private JPanel funcionariosPanel;
    private JPanel produtosPanel;
    private JPanel pedidosPanel;
    private JButton buttonVoltar;
    private JButton buttonSair;
    private JButton buttonCadastrar;
    private JButton buttonAlterar;
    private JButton buttonDesligar;
    private JButton buttonAdicionar;
    private JButton buttonAlterarEstoque;
    private JButton buttonAlterarPreco;
    private JButton buttonVerificar;
    private JButton buttonRealizar;
    private JButton buttonEncerrar;
    private JButton buttonAlterarPedidos;

    public void renderizarPainelPrograma(JFrame frame, TelaLogin login, TelaPrograma telaPrograma) {
        logoIcon = new ImageIcon("logoEmpresa.png");

        gradientPanel = new JPanel();
        gradientPanel.setLayout(new BorderLayout());

        tabbedPane = new JTabbedPane();
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);

        arquivoPanel = createArquivoPanel(frame, login, telaPrograma, logoIcon);
        funcionariosPanel = createFuncionariosPanel(frame, logoIcon);
        produtosPanel = createProdutosPanel(frame, logoIcon);
        pedidosPanel = createPedidosPanel(frame, logoIcon);

        tabbedPane.addTab("Arquivos", arquivoPanel);
        tabbedPane.addTab("Funcionários", funcionariosPanel);
        tabbedPane.addTab("Produtos", produtosPanel);
        tabbedPane.addTab("Pedidos", pedidosPanel);

        gradientPanel.add(tabbedPane, BorderLayout.CENTER);

        frame.getContentPane().removeAll();
        frame.add(gradientPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    private JPanel createArquivoPanel(JFrame frame, TelaLogin login, TelaPrograma telaPrograma, ImageIcon logoIcon) {
        WatermarkPanel panel = new WatermarkPanel(logoIcon); // Marca d'água no fundo
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalGlue());

        buttonVoltar = new JButton("Voltar ao Menu");
        buttonSair = new JButton("Sair");

        buttonVoltar.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonSair.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(buttonVoltar);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(buttonSair);

        panel.add(Box.createVerticalGlue());

        configurarEventosArquivo(frame, login, telaPrograma);

        return panel;
    }

    private void configurarEventosArquivo(JFrame frame, TelaLogin login, TelaPrograma telaPrograma) {
        // Listener para o botão "Voltar ao Menu"
        buttonVoltar.addActionListener(e -> {
            // Mensagem de feedback ao usuário
            JOptionPane.showMessageDialog(frame, "Voltando ao menu principal...");

            // Remove todos os componentes da tela atual
            frame.getContentPane().removeAll();

            // Renderiza a tela de login novamente
            JPanel painelLogin = login.renderizarPainelLogin(frame);
            frame.setContentPane(painelLogin);
            login.configurarAcoes(frame, login, telaPrograma); // Configura as ações da tela de login

            // Ajusta o tamanho, a posição e o comportamento da janela
            frame.setSize(700, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            // Revalida e repinta o frame para garantir que a nova tela seja exibida corretamente
            frame.revalidate();
            frame.repaint();
        });

        // Listener para o botão "Sair"
        buttonSair.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Tem certeza de que deseja sair?",
                    "Confirmação", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                frame.dispose(); // Fecha a janela atual
                System.exit(0);  // Fecha o programa
            }
        });
    }

    private JPanel createFuncionariosPanel(JFrame frame, ImageIcon logoIcon) {
        WatermarkPanel panel = new WatermarkPanel(logoIcon); // Marca d'água no fundo

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(Box.createVerticalGlue());

        buttonCadastrar = new JButton("Cadastrar Funcionário");
        buttonAlterar = new JButton("Alterar Funcionário");
        buttonDesligar = new JButton("Desligar Funcionário");

        buttonCadastrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonAlterar.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonDesligar.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(buttonCadastrar);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(buttonAlterar);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(buttonDesligar);

        panel.add(Box.createVerticalGlue());

        configurarEventosFuncionario(frame);

        return panel;
    }

    @Deprecated
    private void configurarEventosFuncionario(JFrame frame) {
        // Listener para o botao "Cadastrar Funcionário"
        buttonCadastrar.addActionListener(e -> {
            // Exibe diálogos para capturar as informações do funcionário
            String nomeFuncionario = JOptionPane.showInputDialog(frame, "Digite o nome do funcionário:");
            String cargoFuncionario = JOptionPane.showInputDialog(frame, "Digite o cargo do funcionário:");
            String idFuncionario = JOptionPane.showInputDialog(frame, "Digite o ID do funcionário:");

            // Verifica se todos os campos foram preenchidos corretamente
            if (nomeFuncionario != null && cargoFuncionario != null && idFuncionario != null &&
                    !nomeFuncionario.trim().isEmpty() && !cargoFuncionario.trim().isEmpty() && !idFuncionario.trim().isEmpty()) {

                // Cria um novo objeto Funcionario e cadastra as informações
                Funcionario funcionario = new Funcionario();
                funcionario.cadastrarFuncionario(nomeFuncionario, cargoFuncionario, "DataAtual", idFuncionario);

                // Exibe mensagem de sucesso
                JOptionPane.showMessageDialog(frame, "Funcionário cadastrado com sucesso!");
            } else {
                // Exibe mensagem de erro caso algum campo não esteja preenchido
                JOptionPane.showMessageDialog(frame, "Dados inválidos. Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Listener para o botão "Alterar Funcionário"
        buttonAlterar.addActionListener(e -> {
            String idFuncionario = JOptionPane.showInputDialog(frame, "Digite o ID do funcionário a ser alterado:");

            if (idFuncionario != null && !idFuncionario.trim().isEmpty()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(idFuncionario);

                // Solicita novos dados para alteração
                String novoNome = JOptionPane.showInputDialog(frame, "Digite o novo nome do funcionário:");
                String novoCargo = JOptionPane.showInputDialog(frame, "Digite o novo cargo do funcionário:");

                if (novoNome != null && novoCargo != null && !novoNome.trim().isEmpty() && !novoCargo.trim().isEmpty()) {
                    funcionario.setNomeFuncionario(novoNome);
                    funcionario.setCargo(novoCargo);
                    funcionario.alterarFuncionario();

                    JOptionPane.showMessageDialog(frame, "Funcionário alterado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Dados inválidos. Preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "ID do funcionário não pode ser vazio.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Listener para o botão "Desligar Funcionário"
        buttonDesligar.addActionListener(e -> {
            String idFuncionario = JOptionPane.showInputDialog(frame, "Digite o ID do funcionário a ser desligado:");

            if (idFuncionario != null && !idFuncionario.trim().isEmpty()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setIdFuncionario(idFuncionario);

                int confirm = JOptionPane.showConfirmDialog(frame, "Deseja realmente desligar o funcionário ID: "
                        + idFuncionario + "?", "Confirmação", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    funcionario.desligarFuncionario();
                    JOptionPane.showMessageDialog(frame, "Funcionário desligado com sucesso!");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "ID do funcionário não pode ser vazio.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private JPanel createProdutosPanel(JFrame frame, ImageIcon logoIcon) {
        WatermarkPanel panel = new WatermarkPanel(logoIcon); // Marca d'água no fundo

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalGlue());

        buttonAdicionar = new JButton("Adicionar Produto");
        buttonAlterarEstoque = new JButton("Alterar Estoque");
        buttonAlterarPreco = new JButton("Alterar Preço");

        buttonAdicionar.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonAlterarEstoque.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonAlterarPreco.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(buttonAdicionar);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(buttonAlterarEstoque);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(buttonAlterarPreco);

        panel.add(Box.createVerticalGlue());

        configurarEventosProduto(frame, buttonAdicionar, buttonAlterarEstoque, buttonAlterarPreco);

        return panel;
    }

    @Deprecated
    private void configurarEventosProduto(JFrame frame, JButton buttonAdicionar, JButton buttonAlterarEstoque, JButton buttonAlterarPreco) {
        // Listener para o botão "Adicionar Produto"
        buttonAdicionar.addActionListener(e -> {
            // Exibe diálogos para capturar as informações do produto
            String nomeProduto = JOptionPane.showInputDialog(frame, "Digite o nome do produto:");
            String idProduto = JOptionPane.showInputDialog(frame, "Digite o ID do produto:");
            String precoProdutoStr = JOptionPane.showInputDialog(frame, "Digite o preço do produto:");
            String categoriaProduto = JOptionPane.showInputDialog(frame, "Digite a categoria do produto:");
            String descricaoProduto = JOptionPane.showInputDialog(frame, "Digite a descrição do produto:");

            // Validação e conversão do preço
            try {
                double precoProduto = Double.parseDouble(precoProdutoStr);

                if (!nomeProduto.trim().isEmpty() && !idProduto.trim().isEmpty() && precoProduto >= 0
                        && !categoriaProduto.trim().isEmpty() && !descricaoProduto.trim().isEmpty()) {

                    // Cria e adiciona o produto
                    Produto produto = new Produto();
                    produto.adicionarProduto(nomeProduto, idProduto, precoProduto, categoriaProduto, descricaoProduto);

                    JOptionPane.showMessageDialog(frame, "Produto adicionado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Preencha todos os campos corretamente.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Preço inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Listener para o botão "Alterar Estoque"
        buttonAlterarEstoque.addActionListener(e -> {
            String idProduto = JOptionPane.showInputDialog(frame, "Digite o ID do produto para alterar o estoque:");
            String quantidadeStr = JOptionPane.showInputDialog(frame, "Digite a quantidade a ser removida do estoque:");

            try {
                int quantidade = Integer.parseInt(quantidadeStr);

                if (quantidade >= 0 && !idProduto.trim().isEmpty()) {
                    // Aqui você pode buscar o produto pelo ID e alterar o estoque
                    Produto produto = new Produto(); // Exemplo de instanciamento
                    produto.alterarEstoque(quantidade);

                    JOptionPane.showMessageDialog(frame, "Estoque alterado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Preencha os campos corretamente.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Quantidade inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Listener para o botão "Alterar Preço"
        buttonAlterarPreco.addActionListener(e -> {
            String idProduto = JOptionPane.showInputDialog(frame, "Digite o ID do produto para alterar o preço:");
            String novoPrecoStr = JOptionPane.showInputDialog(frame, "Digite o novo preço:");

            try {
                double novoPreco = Double.parseDouble(novoPrecoStr);

                if (novoPreco >= 0 && !idProduto.trim().isEmpty()) {
                    // Buscar o produto pelo ID e alterar o preço
                    Produto produto = new Produto(); // Instanciamento
                    produto.alterarPreco(novoPreco);

                    JOptionPane.showMessageDialog(frame, "Preço alterado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Preencha os campos corretamente.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Preço inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private JPanel createPedidosPanel(JFrame frame, ImageIcon logoIcon) {
        WatermarkPanel panel = new WatermarkPanel(logoIcon); // Marca d'água no fundo

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createVerticalGlue());

        buttonVerificar = new JButton("Verificar Disponibilidade");
        buttonRealizar = new JButton("Realizar Pedido");
        buttonEncerrar = new JButton("Encerrar Pedido");
        buttonAlterar = new JButton("Alterar Pedido");

        buttonVerificar.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonRealizar.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonEncerrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        buttonAlterarPedidos.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.add(buttonVerificar);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(buttonRealizar);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(buttonEncerrar);
        panel.add(Box.createRigidArea(new Dimension(0, 50)));
        panel.add(buttonAlterarPedidos);

        panel.add(Box.createVerticalGlue());

        configurarEventosPedidos(buttonVerificar, buttonRealizar, buttonEncerrar, buttonAlterar);

        return panel;
    }

    @Deprecated
    private void configurarEventosPedidos(JButton buttonVerificar, JButton buttonRealizar, JButton buttonEncerrar, JButton buttonAlterar) {

        /*
        // Ação para o botão "Verificar Disponibilidade"
        buttonVerificar.addActionListener(e -> {
            String nomeProduto = JOptionPane.showInputDialog(null, "Informe o nome do produto que deseja verificar:");

            if (nomeProduto != null && !nomeProduto.trim().isEmpty()) {
                // Verificar a disponibilidade do produto com base no nome informado
                String resultadoDisponibilidade = pedido.verificarDisponibilidade(nomeProduto.trim());

                // Exibir o resultado da verificação
                JOptionPane.showMessageDialog(null, resultadoDisponibilidade,
                        "Disponibilidade", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Nenhum nome de produto foi informado.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });*/

        // Ação para o botão "Realizar/Criar Pedido"
        buttonRealizar.addActionListener(e -> {
            /*
            String nomePedido = JOptionPane.showInputDialog("Digite o nome do pedido:");
            String idPedido = JOptionPane.showInputDialog("Digite o ID do pedido:");
            String dataPedido = JOptionPane.showInputDialog("Digite a data do pedido:");
            String dataEntrega = JOptionPane.showInputDialog("Digite a data de entrega:");
            String descricao = JOptionPane.showInputDialog("Digite a descrição do pedido:");

            if (nomePedido != null && idPedido != null && dataPedido != null && dataEntrega != null && descricao != null &&
                    !nomePedido.trim().isEmpty() && !idPedido.trim().isEmpty() && !dataPedido.trim().isEmpty() &&
                    !dataEntrega.trim().isEmpty() && !descricao.trim().isEmpty()) {

                pedido.criarPedido(nomePedido, idPedido, dataPedido, dataEntrega, "produtos", descricao);
                JOptionPane.showMessageDialog(null, "Pedido realizado com sucesso!",
                        "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }*/
        });

        // Ação para o botão "Encerrar Pedido"
        buttonEncerrar.addActionListener(e -> {
            /*
            String IdPedido = JOptionPane.showInputDialog("Digite o id do pedido a ser encerrado:");
            if (IdPedido != null && !IdPedido.trim().isEmpty()) {
                pedido.encerrarPedido();
                JOptionPane.showMessageDialog(null, "Pedido encerrado com sucesso!",
                        "Encerrar Pedido", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }*/
        });

        // Ação para o botão "Alterar Pedido"
        buttonAlterar.addActionListener(e -> {
            /*
            String IdPedido = JOptionPane.showInputDialog("Digite o id do pedido a ser alterado:");
            String novoId = JOptionPane.showInputDialog("Digite o novo id do pedido:");
            String novaDescricao = JOptionPane.showInputDialog("Digite a nova descrição do pedido:");

            if (IdPedido != null && novoId != null && novaDescricao != null && !IdPedido.trim().isEmpty() &&
                    !novoId.trim().isEmpty() && !novaDescricao.trim().isEmpty()) {

                pedido.setIdPedido(novoId);
                pedido.setDescricao(novaDescricao);
                pedido.alterarPedido();
                JOptionPane.showMessageDialog(null, "Pedido alterado com sucesso!",
                        "Alterar Pedido", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos corretamente.",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }*/
        });
    }

    //getters e setters


    public JButton getButtonVoltar() {
        return buttonVoltar;
    }

    public void setButtonVoltar(JButton buttonVoltar) {
        this.buttonVoltar = buttonVoltar;
    }

    public JButton getButtonSair() {
        return buttonSair;
    }

    public void setButtonSair(JButton buttonSair) {
        this.buttonSair = buttonSair;
    }

    public JButton getButtonCadastrar() {
        return buttonCadastrar;
    }

    public void setButtonCadastrar(JButton buttonCadastrar) {
        this.buttonCadastrar = buttonCadastrar;
    }

    public JButton getButtonAlterar() {
        return buttonAlterar;
    }

    public void setButtonAlterar(JButton buttonAlterar) {
        this.buttonAlterar = buttonAlterar;
    }

    public JButton getButtonDesligar() {
        return buttonDesligar;
    }

    public void setButtonDesligar(JButton buttonDesligar) {
        this.buttonDesligar = buttonDesligar;
    }

    public JButton getButtonAdicionar() {
        return buttonAdicionar;
    }

    public void setButtonAdicionar(JButton buttonAdicionar) {
        this.buttonAdicionar = buttonAdicionar;
    }

    public JButton getButtonAlterarEstoque() {
        return buttonAlterarEstoque;
    }

    public void setButtonAlterarEstoque(JButton buttonAlterarEstoque) {
        this.buttonAlterarEstoque = buttonAlterarEstoque;
    }

    public JButton getButtonAlterarPreco() {
        return buttonAlterarPreco;
    }

    public void setButtonAlterarPreco(JButton buttonAlterarPreco) {
        this.buttonAlterarPreco = buttonAlterarPreco;
    }

    public JButton getButtonVerificar() {
        return buttonVerificar;
    }

    public void setButtonVerificar(JButton buttonVerificar) {
        this.buttonVerificar = buttonVerificar;
    }

    public JButton getButtonRealizar() {
        return buttonRealizar;
    }

    public void setButtonRealizar(JButton buttonRealizar) {
        this.buttonRealizar = buttonRealizar;
    }

    public JButton getButtonEncerrar() {
        return buttonEncerrar;
    }

    public void setButtonEncerrar(JButton buttonEncerrar) {
        this.buttonEncerrar = buttonEncerrar;
    }

    public JButton getButtonAlterarPedidos() {
        return buttonAlterarPedidos;
    }

    public void setButtonAlterarPedidos(JButton buttonAlterarPedidos) {
        this.buttonAlterarPedidos = buttonAlterarPedidos;
    }

    // Implementação do painel com marca d'água
    class WatermarkPanel extends JPanel {
        private final ImageIcon watermark;
        private final int margin = 40; // Margem entre as imagens

        public WatermarkPanel(ImageIcon watermark) {
            this.watermark = watermark;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (watermark != null) {
                Graphics2D g2d = (Graphics2D) g.create();
                AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
                g2d.setComposite(alpha);

                int width = getWidth();
                int height = getHeight();
                int imageWidth = watermark.getIconWidth();
                int imageHeight = watermark.getIconHeight();

                // Ângulo de rotação em radianos (45 graus)
                double angle = Math.toRadians(45);

                // Preenche o fundo com a marca d'água repetida na diagonal e rotacionada
                for (int y = -imageHeight * 2; y < height + imageHeight * 2; y += imageHeight + margin) {
                    for (int x = -imageWidth * 2; x < width + imageWidth * 2; x += imageWidth + margin) {
                        // Criar uma nova instância do Graphics2D para cada imagem
                        Graphics2D g2dImage = (Graphics2D) g2d.create();

                        // Move o ponto de origem para a posição da imagem
                        g2dImage.translate(x + imageWidth, y + imageHeight);
                        // Rotaciona a imagem em torno do seu centro
                        g2dImage.rotate(angle);

                        // Desenha a imagem rotacionada
                        g2dImage.drawImage(watermark.getImage(), -imageWidth / 2, -imageHeight / 2, this);

                        // Libera o contexto gráfico temporário
                        g2dImage.dispose();
                    }
                }
                g2d.dispose(); // Libera o contexto gráfico original
            }
        }
    }
}

