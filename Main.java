import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> nomes = new ArrayList<>();
        try (BufferedReader leitor = new BufferedReader(new FileReader("female_names.txt"))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                nomes.add(linha.trim());
            }
        }

        TabelaHashAbstrata HashSomaASCII = new PrimeiraTabelaHash();
        TabelaHashAbstrata HashCode = new SegundaTabelaHash();

        long inicioEnc = System.nanoTime();
        for (String nome : nomes) {
            HashSomaASCII.inserir(nome);
        }
        long tempoEnc = System.nanoTime() - inicioEnc;

        long inicioAberta = System.nanoTime();
        for (String nome : nomes) {
            HashCode.inserir(nome);
        }
        long tempoAberta = System.nanoTime() - inicioAberta;

        Collections.shuffle(nomes);
        List<String> amostra = nomes.subList(0, 1000);

        long inicioBuscaEnc = System.nanoTime();
        for (String nome : amostra) {
            HashSomaASCII.buscar(nome);
        }
        long tempoBuscaEnc = System.nanoTime() - inicioBuscaEnc;

        long inicioBuscaAberta = System.nanoTime();
        for (String nome : amostra) {
            HashCode.buscar(nome);
        }
        long tempoBuscaAberta = System.nanoTime() - inicioBuscaAberta;

        System.out.println("=== RELATÓRIO FINAL ===");

        System.out.println("\nTabela com Hash (soma de chars):");
        System.out.println("Colisões: " + HashSomaASCII.getColisoes());
        System.out.println("Tempo de inserção: " + tempoEnc / 1e6 + " ms");
        System.out.println("Tempo de busca: " + tempoBuscaEnc / 1e6 + " ms");
        imprimirDistribuicao(HashSomaASCII.getDistribuicao());

        System.out.println("\nTabela com Hash (hashCode):");
        System.out.println("Colisões: " + HashCode.getColisoes());
        System.out.println("Tempo de inserção: " + tempoAberta / 1e6 + " ms");
        System.out.println("Tempo de busca: " + tempoBuscaAberta / 1e6 + " ms");
        imprimirDistribuicao(HashCode.getDistribuicao());
    }

    public static void imprimirDistribuicao(int[] dist) {
        System.out.println("Distribuição de chaves:");
        for (int i = 0; i < dist.length; i++) {
            System.out.println("Índice " + i + ": " + dist[i] + " entradas");
        }
    }
}
