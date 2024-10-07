package model.interfaces;

public interface IPedidos {
    public void criarPedido(String nomePedido, String idPedido, String dataPedido, String dataEntrega, String produtos, String descricao);
    public String verificarDisponibilidade(String nomeProduto);  //MODIFICADO POR CAIO, VERIFICAR SE VAI SER USADO!!!!!!!!!!!
    public void encerrarPedido();
    public void alterarPedido();
}
