package model.interfaces;

import model.identificadores.Produto;

public interface IPedidos {
    public void criarPedido(String nomePedido, String idPedido, String dataPedido, String dataEntrega, String produtos, String descricao);
    public void verificarDisponibilidade();
    public void encerrarPedido();
    public void alterarPedido();
}
