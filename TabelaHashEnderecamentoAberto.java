public class TabelaHashEnderecamentoAberto extends TabelaHashAbstrata {
    private NoHashEnderecamentoAberto[] tabela;
    private int tamanho;

    public TabelaHashEnderecamentoAberto() {
        tabela = new NoHashEnderecamentoAberto[capacidade];
        tamanho = 0;
    }

    private int funcaoHash(String chave) {
        int hash = 7;
        for (char c : chave.toCharArray()) {
            hash = hash * 31 + c;
        }
        return Math.abs(hash % capacidade);
    }

    @Override
    public void inserir(String chave) {
        int indice = funcaoHash(chave);
        int tentativas = 0;

        while (tabela[indice] != null && !tabela[indice].removido) {
            colisoes++;
            tentativas++;
            if (tentativas >= capacidade) return; // tabela cheia
            indice = (indice + 1) % capacidade; // teste linear
        }

        tabela[indice] = new NoHashEnderecamentoAberto(chave);
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
    public int[] getDistribuicao() {
        int[] dist = new int[capacidade];
        for (int i = 0; i < capacidade; i++) {
            if (tabela[i] != null && !tabela[i].removido) {
                dist[i]++;
            }
        }
        return dist;
    }
}
