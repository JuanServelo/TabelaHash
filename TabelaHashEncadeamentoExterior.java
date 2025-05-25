public class TabelaHashEncadeamentoExterior extends TabelaHashAbstrata {
    private NoHashEncadeamentoExterior[] tabela;

    public TabelaHashEncadeamentoExterior() {
        tabela = new NoHashEncadeamentoExterior[capacidade];
    }

    private int funcaoHash(String chave) {
        int hash = 0;
        for (char c : chave.toCharArray()) {
            hash += c;
        }
        return hash % capacidade;
    }

    @Override
    public void inserir(String chave) {
        int indice = funcaoHash(chave);
        NoHashEncadeamentoExterior novo = new NoHashEncadeamentoExterior(chave);
        if (tabela[indice] == null) {
            tabela[indice] = novo;
        } else {
            colisoes++;
            NoHashEncadeamentoExterior atual = tabela[indice];
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novo;
        }
    }

    @Override
    public boolean buscar(String chave) {
        int indice = funcaoHash(chave);
        NoHashEncadeamentoExterior atual = tabela[indice];
        while (atual != null) {
            if (atual.chave.equals(chave)) return true;
            atual = atual.proximo;
        }
        return false;
    }

    @Override
    public int[] getDistribuicao() {
        int[] dist = new int[capacidade];
        for (int i = 0; i < capacidade; i++) {
            NoHashEncadeamentoExterior atual = tabela[i];
            while (atual != null) {
                dist[i]++;
                atual = atual.proximo;
            }
        }
        return dist;
    }
}
