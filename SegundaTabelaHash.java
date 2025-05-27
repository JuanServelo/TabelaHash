public class SegundaTabelaHash extends TabelaHashAbstrata {
    @Override
    protected int funcaoHash(String chave) {
        return Math.abs(chave.hashCode()) % capacidade;
    }
}
