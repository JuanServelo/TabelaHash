public class PrimeiraTabelaHash extends TabelaHashAbstrata {
    @Override
    protected int funcaoHash(String chave) {
        int hash = 0;
        for (char c : chave.toCharArray()) {
            hash += c;
        }
        return hash % capacidade;
    }
}
