package model.interfaces;

public interface IProdutos {
    public void adicionarProduto(String nomeProduto, String idProduto, double preco, String categoria, String descricao, double desdconto);
    public void alterarEstoque(int retirada);
    public void alterarPreco(double novoPreco);


}
