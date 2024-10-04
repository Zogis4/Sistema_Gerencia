package model.interfaces;

import model.identificadores.Produto;

public interface IProdutos {
    public void adicionarProduto(String nomeProduto, String idProduto, double preco, String categoria, String descricao);
    public void alterarEstoque(int retirada);
    public void alterarPreco(double novoPreco);


}
