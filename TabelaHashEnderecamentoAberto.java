public class TabelaHashEnderecamentoAberto extends TabelaHashAbstrata {
    private NoHash[] tabela;
    private int tamanho;
    private static final double FATOR_DE_CARGA = 0.75;

    public TabelaHashEnderecamentoAberto() {
        tabela = new NoHash[capacidade];
        tamanho = 0;
    }

    @Override
    protected int funcaoHash(String chave) {
        return Math.abs(chave.hashCode()) % capacidade;
    }

    @Override
    public void inserir(String chave) {
        if (tamanho + 1 >= capacidade * FATOR_DE_CARGA) {
            rehash();
        }

        int indice = funcaoHash(chave);

        while (tabela[indice] != null && !tabela[indice].removido) {
            if (tabela[indice].chave.equals(chave)) {
                return;
            }
            colisoes++;
            indice = (indice + 1) % capacidade;
        }

        tabela[indice] = new NoHash(chave);
        tamanho++;
    }

    @Override
    public boolean buscar(String chave) {
        int indice = funcaoHash(chave);
        int tentativas = 0;

        while (tabela[indice] != null && tentativas < capacidade) {
            if (!tabela[indice].removido && tabela[indice].chave.equals(chave)) {
                return true;
            }
            indice = (indice + 1) % capacidade;
            tentativas++;
        }

        return false;
    }

    @Override
    public void rehash() {
        int novaCapacidade = capacidade + (capacidade / 2);
        NoHash[] tabelaAntiga = tabela;

        tabela = new NoHash[novaCapacidade];
        capacidade = novaCapacidade;
        tamanho = 0;

        int colisoesAnteriores = colisoes;
        colisoes = 0;

        for (NoHash no : tabelaAntiga) {
            if (no != null && !no.removido) {
                inserir(no.chave);
            }
        }

        colisoes += colisoesAnteriores;
    }

    @Override
    public int[] getDistribuicao() {
        int[] dist = new int[capacidade];
        for (int i = 0; i < capacidade; i++) {
            if (tabela[i] != null && !tabela[i].removido) {
                dist[i] = 1;
            }
        }
        return dist;
    }
}