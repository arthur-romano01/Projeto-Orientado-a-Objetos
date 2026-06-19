import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[] vetor = { "Neymar", "Brasil", "Endrick", "Alisson", "Bagre", "Marquinhos", "Vini Jr", "Raphinha" };

        Template sort_por_letra = new Sort_Por_letra();
        Arrays.sort(vetor, (a,b) -> sort_por_letra.compare(a,b));
        System.out.println("Ordenado por letra: " + Arrays.toString(vetor));

        Template sort_por_tamanho = new Sort_Por_Tamanho();
        Arrays.sort(vetor, (a,b) -> sort_por_tamanho.compare(a,b));
        System.out.println("Ordenado por tamanho: " + Arrays.toString(vetor));
    }
}
