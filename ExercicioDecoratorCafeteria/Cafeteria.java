package Exercicios.ExercicioDecoratorCafeteria;
interface Pedido {
    void PedirCafe();
    void PedirCappucino();
    void PedirCha();
}

class Pedir implements Pedido {
    public void PedirCafe() { System.out.println("Pedindo Cafe"); }
    public void PedirCappucino() { System.out.println("Pedindo Cappucino"); }
    public void PedirCha() { System.out.println("Pedindo Cha"); }
}

class Decorado implements Pedido {
    private Pedido pedido;
    public Decorado(Pedido pedido) { this.pedido = pedido; }
    public void PedirCafe() { pedido.PedirCafe(); }
    public void PedirCappucino() { pedido.PedirCappucino(); }
    public void PedirCha() { pedido.PedirCha(); }
}

class AdicionarLeite extends Decorado {
    public AdicionarLeite(Pedido pedido) { super(pedido); }
    public void PedirCafe() { super.PedirCafe(); System.out.println("Adicionando leite"); }
    public void PedirCappucino() { super.PedirCappucino(); System.out.println("Adicionando leite"); }
    public void PedirCha() { super.PedirCha(); System.out.println("Adicionando leite"); }
}

class AdicionarChantilly extends Decorado {
    public AdicionarChantilly(Pedido pedido) { super(pedido); }
    public void PedirCafe() { super.PedirCafe(); System.out.println("Adicionando chantilly"); }
    public void PedirCappucino() { super.PedirCappucino(); System.out.println("Adicionando chantilly"); }
    public void PedirCha() { super.PedirCha(); System.out.println("Adicionando chantilly"); }
}

class AdicionarCanela extends Decorado {
    public AdicionarCanela(Pedido pedido) { super(pedido); }
    public void PedirCafe() { super.PedirCafe(); System.out.println("Adicionando canela"); }
    public void PedirCappucino() { super.PedirCappucino(); System.out.println("Adicionando canela"); }
    public void PedirCha() { super.PedirCha(); System.out.println("Adicionando canela"); }
}

class AdicionarCaldaChocolate extends Decorado {
    public AdicionarCaldaChocolate(Pedido pedido) { super(pedido); }
    public void PedirCafe() { super.PedirCafe(); System.out.println("Adicionando calda de chocolate"); }
    public void PedirCappucino() { super.PedirCappucino(); System.out.println("Adicionando calda de chocolate"); }
    public void PedirCha() { super.PedirCha(); System.out.println("Adicionando calda de chocolate"); }
}


public class Cafeteria {
    public static void main(String args[]) {
        // Café com leite e canela
        Pedido pedido1 = new AdicionarCanela(new AdicionarLeite(new Pedir()));
        System.out.println("=== Café com leite e canela ===");
        pedido1.PedirCafe();

        // Cappuccino com chantilly e calda de chocolate
        Pedido pedido2 = new AdicionarCaldaChocolate(new AdicionarChantilly(new Pedir()));
        System.out.println("\n=== Cappuccino com chantilly e calda de chocolate ===");
        pedido2.PedirCappucino();

        // Chá simples, sem complemento
        Pedido pedido3 = new Pedir();
        System.out.println("\n=== Chá simples ===");
        pedido3.PedirCha();
    }
}
