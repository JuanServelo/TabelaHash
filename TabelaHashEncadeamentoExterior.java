public class TabelaHashEncadeamentoExterior extends TabelaHashAbstrata {
    private NoHash[] tabela;
    private int tamanho;
    private static final double FATOR_DE_CARGA = 0.75;

    public TabelaHashEncadeamentoExterior() {
        tabela = new NoHash[capacidade];
        tamanho = 0;
    }

    @Override
    protected int funcaoHash(String chave) {
        int hash = 0;
        for (char c : chave.toCharArray()) {
            hash += c;
        }
        return hash % capacidade;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
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
}