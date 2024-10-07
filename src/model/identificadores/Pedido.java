package model.identificadores;

import model.interfaces.IPedidos;

import java.util.HashMap;
import java.util.Map;

public class Pedido  implements IPedidos {
    private String nomePedido = "";
    private String idPedido = "";
    private String dataPedido = "";
    private String dataEntrega = "";
    private String descricao = "";


    @Override
    public void criarPedido(String nomePedido, String idPedido, String dataPedido, String dataEntrega, String produtos, String descricao) {
        this.nomePedido = nomePedido;
        this.idPedido = idPedido;
        this.dataPedido = dataPedido;
        this.dataEntrega = dataEntrega;
        this.descricao = descricao;
    }

    //MODIFICADO POR CAIO, VERIFICAR SE VAI SER USADO!!!!!!!!!!!
    private Map<String, Integer> estoqueProdutos;  // Simulação de estoque com nome e quantidade

    public Pedido() {
        // Simulando um estoque inicial de produtos
        estoqueProdutos = new HashMap<>();
        estoqueProdutos.put("Produto A", 10);  // Produto A com 10 unidades
        estoqueProdutos.put("Produto B", 0);   // Produto B esgotado
    }
    @Override
    public String verificarDisponibilidade(String nomeProduto) { //MODIFICADO POR CAIO, VERIFICAR SE VAI SER USADO!!!!!!!!!!!
        if (estoqueProdutos.containsKey(nomeProduto)) {
            int quantidade = estoqueProdutos.get(nomeProduto);

            if (quantidade > 0) {
                return "O produto '" + nomeProduto + "' está disponível em estoque. Quantidade: " + quantidade;
            } else {
                return "O produto '" + nomeProduto + "' está fora de estoque.";
            }
        } else {
            return "O produto '" + nomeProduto + "' não foi encontrado no sistema.";
        }
    }

    @Override
    public void encerrarPedido() {

    }

    @Override
    public void alterarPedido() {

    }

    public String getNomePedido() {
        return nomePedido;
    }

    public void setNomePedido(String nomePedido) {
        this.nomePedido = nomePedido;
    }

    public String getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(String idPedido) {
        this.idPedido = idPedido;
    }

    public String getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(String dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(String dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    //public void verificarDisponibilidade();
	//public void realizarPedido();
	//public void encerrarPedido();
	//public void alterarPedido();
}
