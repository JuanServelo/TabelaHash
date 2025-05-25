public class NoHashEncadeamentoExterior {
    String chave;
    NoHashEncadeamentoExterior proximo;

    public NoHashEncadeamentoExterior(String chave) {
        this.chave = chave;
        this.proximo = null;
    }
}