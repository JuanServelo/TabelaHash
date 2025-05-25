public abstract class TabelaHashAbstrata {
    protected int capacidade = 32;
    protected int colisoes = 0;

    public abstract void inserir(String chave);
    public abstract boolean buscar(String chave);
    public abstract int[] getDistribuicao();
    public int getColisoes() {
        return colisoes;
    }
}
