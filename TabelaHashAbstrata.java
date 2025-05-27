public abstract class TabelaHashAbstrata {
    protected int capacidade = 32;
    protected int colisoes = 0;
    protected int tamanho = 0;
    protected NoHash[] tabela;
    protected final double FATOR_DE_CARGA = 0.75;

    public TabelaHashAbstrata() {
        tabela = new NoHash[capacidade];
    }

    protected abstract int funcaoHash(String chave);

    public void inserir(String chave) {
        if ((double) tamanho / capacidade > FATOR_DE_CARGA) {
            rehash();
        }

        int indice = funcaoHash(chave);
        NoHash novo = new NoHash(chave);

        if (tabela[indice] == null) {
            tabela[indice] = novo;
        } else {
            colisoes++;
            NoHash atual = tabela[indice];
            while (atual.proximo != null) {
                atual = atual.proximo;
            }
            atual.proximo = novo;
        }

        tamanho++;
    }

    public boolean buscar(String chave) {
        int indice = funcaoHash(chave);
        NoHash atual = tabela[indice];

        while (atual != null) {
            if (atual.chave.equals(chave)) {
                return true;
            }
            atual = atual.proximo;
        }

        return false;
    }

    public void rehash() {
        int novaCapacidade = capacidade + (capacidade / 2);
        NoHash[] novaTabela = new NoHash[novaCapacidade];
        NoHash[] antigaTabela = tabela;

        tabela = novaTabela;
        capacidade = novaCapacidade;
        tamanho = 0;

        int colisoesAnteriores = colisoes;
        colisoes = 0;

        for (NoHash no : antigaTabela) {
            while (no != null) {
                inserir(no.chave);
                no = no.proximo;
            }
        }

        colisoes += colisoesAnteriores;
    }

    public int[] getDistribuicao() {
        int[] dist = new int[capacidade];

        for (int i = 0; i < capacidade; i++) {
            NoHash atual = tabela[i];
            while (atual != null) {
                dist[i]++;
                atual = atual.proximo;
            }
        }

        return dist;
    }

    public int getColisoes() {
        return colisoes;
    }
}
