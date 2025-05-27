public class NoHash {
    String chave;
    boolean removido = false;
    NoHash proximo = null;

    public NoHash(String chave) {
        this.chave = chave;
    }
}