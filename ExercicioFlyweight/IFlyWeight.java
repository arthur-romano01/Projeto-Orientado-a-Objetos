import java.util.HashMap;

interface flyweight {
    void desenhar_arvore(int x, int y, double escala);
}

class fabricaDeArvores {
    private static HashMap<String, flyweight> cache = new HashMap<>();

    public static flyweight getArvore(String tipo, String textura) {
        if (cache.containsKey(tipo)) {
            return cache.get(tipo);
        }
        flyweight arvore = new ArvoreImutavel(tipo, textura);
        cache.put(tipo, arvore);
        return arvore;
    }
}

class SemFlyWeight {
    private String tipo;
    private String textura;
    private int x;
    private int y;
    private double escala;

    public SemFlyWeight(String tipo, String textura, int x, int y, double escala) {
        this.tipo = tipo;
        this.textura = textura;
        this.x = x;
        this.y = y;
        this.escala = escala;
    }

    public void desenhar_arvore(int x, int y, double escala) {
        System.out.println("Desenhando arvore: " + this.tipo + ", com textura" + this.textura + "na posicao (" + this.x
                + ", " + this.y
                + ") com escala: " + this.escala);
    }
}

class ArvoreImutavel implements flyweight {
    private String tipo;
    private String textura;

    public ArvoreImutavel(String tipo, String textura) {
        this.tipo = tipo;
        this.textura = textura;
    }

    @Override
    public void desenhar_arvore(int x, int y, double escala) {
        System.out.println("Desenhando arvore: " + tipo + ", com textura" + textura + "na posicao (" + x + ", " + y
                + ") com escala: " + escala);
    }

}

class ArvoreMutavel implements flyweight {
    private int x;
    private int y;
    private double escala;
    private flyweight tipoArvore;

    public ArvoreMutavel(int x, int y, double escala, flyweight tipoArvore) {
        this.x = x;
        this.y = y;
        this.escala = escala;
        this.tipoArvore = tipoArvore;
    }

    @Override
    public void desenhar_arvore(int x, int y, double escala) {
        tipoArvore.desenhar_arvore(this.x, this.y, this.escala);
    }

}

public class IFlyWeight {
    public static void main(String[] args) {
        System.out.println("--- 1. Obtendo os tipos de arvore imutaveis (Flyweight) ---");

        flyweight carvalho = fabricaDeArvores.getArvore("Carvalho", "textura_carvalho.png");
        flyweight pinheiro = fabricaDeArvores.getArvore("Pinheiro", "textura_pinheiro.png");

        System.out.println("--- 2. Criando 100.000 arvores COM Flyweight ---");
        
        long memAntesFlyweight = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        flyweight[] florestaFlyweight = new flyweight[100000];
        for (int i = 0; i < 100000; i++) {
            florestaFlyweight[i] = new ArvoreMutavel(i, i, 1.0, i % 2 == 0 ? carvalho : pinheiro);
        }
        long memDepoisFlyweight = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println("--- 3. Desenhando algumas arvores no mapa (Flyweight) ---");
        florestaFlyweight[0].desenhar_arvore(0, 0, 0);
        florestaFlyweight[1].desenhar_arvore(0, 0, 0);

        System.out.println("\nMemoria gasta no bloco Flyweight: " + (memDepoisFlyweight - memAntesFlyweight) + " bytes");

        florestaFlyweight = null;
        System.gc();

        System.out.println("\n--- 4. Criando 100.000 arvores SEM Flyweight ---");
        
        long memAntesSemFlyweight = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        SemFlyWeight[] florestaSemFlyweight = new SemFlyWeight[100000];
        for (int i = 0; i < 100000; i++) {
            florestaSemFlyweight[i] = new SemFlyWeight("Carvalho", "textura_carvalho.png", i, i, 1.0);
        }
        long memDepoisSemFlyweight = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();

        System.out.println("--- 5. Desenhando algumas arvores no mapa (Sem Flyweight) ---");
        florestaSemFlyweight[0].desenhar_arvore(0, 0, 0);
        florestaSemFlyweight[1].desenhar_arvore(0, 0, 0);

        System.out.println("\nMemoria gasta no bloco Sem Flyweight: " + (memDepoisSemFlyweight - memAntesSemFlyweight) + " bytes");
    }
}
