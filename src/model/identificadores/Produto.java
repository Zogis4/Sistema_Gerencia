package model.identificadores;

import model.interfaces.IProdutos;

public class Produto implements IProdutos {
    private String nome = "";
    private String id = "";
    private double preco;
    private String categoria = "";
    private String descricao = "";

    @Override
    public void adicionarProduto(String nomeProduto, String idProduto, double preco, String categoria, String descricao) {
        this.nome = nome;
        this.id = id;
        this.preco = preco;
        this.categoria = categoria;
        this.descricao = descricao;
    }

    @Override
    public void alterarEstoque(int retirada) {

    }

    @Override
    public void alterarPreco(double novoPreco) {

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    //public void adicionarProduto();
	//public void alterarFuncionario();
	//public void desligarFuncionario();
}
