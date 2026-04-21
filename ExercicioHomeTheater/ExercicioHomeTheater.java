package Exercicios.ExercicioHomeTheater;

class TV {
    public void LigarTv()       { System.out.println("Ligando a TV"); }
    public void DesligarTv()    { System.out.println("Desligando a TV"); }
    public void MudarCanal()    { System.out.println("Mudando o canal"); }
    public void AumentarVolume(){ System.out.println("Aumentando o volume da TV"); }
    public void DiminuirVolume(){ System.out.println("Diminuindo o volume da TV"); }
}

class Projetor {
    public void LigarProjetor()   { System.out.println("Ligando o projetor"); }
    public void DesligarProjetor(){ System.out.println("Desligando o projetor"); }
}

class Receiver {
    public void LigarReceiver()   { System.out.println("Ligando o receiver"); }
    public void DesligarReceiver(){ System.out.println("Desligando o receiver"); }
}

class PlayerdeMidia {
    public void LigarPlayerdeMidia()   { System.out.println("Ligando o player de midia"); }
    public void DesligarPlayerdeMidia(){ System.out.println("Desligando o player de midia"); }
    public void AumentarVolume()       { System.out.println("Aumentando o volume do player"); }
    public void DiminuirVolume()       { System.out.println("Diminuindo o volume do player"); }
}

class Som {
    public void LigarSom()    { System.out.println("Ligando o som"); }
    public void DesligarSom() { System.out.println("Desligando o som"); }
    public void AumentarVolume(){ System.out.println("Aumentando o volume do som"); }
    public void DiminuirVolume(){ System.out.println("Diminuindo o volume do som"); }
}

class Luz {
    public void LigarLuz()  { System.out.println("Ligando a luz"); }
    public void DesligarLuz(){ System.out.println("Desligando a luz"); }
    public void MudaCor()   { System.out.println("Mudando de cor"); }
}

interface facade {
    void AssistirFilme();
    void Sair();
    void LigaLuz();
    void DesligaLuz();
}

class HomeTheater implements facade {

    private TV tv;
    private Projetor projetor;
    private Receiver receiver;
    private PlayerdeMidia player;
    private Som som;
    private Luz luz;

    public HomeTheater() {
        this.tv       = new TV();
        this.projetor = new Projetor();
        this.receiver = new Receiver();
        this.player   = new PlayerdeMidia();
        this.som      = new Som();
        this.luz      = new Luz();
    }

    @Override
    public void AssistirFilme() {
        System.out.println("\n=== Preparando o Home Theater para assistir filme ===");
        tv.DesligarTv();
        luz.DesligarLuz();     
        projetor.LigarProjetor();
        receiver.LigarReceiver();
        player.LigarPlayerdeMidia();
        som.LigarSom();
        som.AumentarVolume();
    }

    @Override
    public void Sair() {
        System.out.println("\n=== Desligando o Home Theater ===");
        player.DesligarPlayerdeMidia();
        som.DesligarSom();
        receiver.DesligarReceiver();
        projetor.DesligarProjetor();
        luz.LigarLuz();           
    }

    @Override
    public void LigaLuz() {
        System.out.println("Ligando a luz");
        luz.LigarLuz();
    }

    @Override
    public void DesligaLuz() {
        System.out.println("Desligando a luz");
        luz.DesligarLuz();
    }
}


public class ExercicioHomeTheater {
    public static void main(String[] args) {
        facade homeTheater = new HomeTheater();
        homeTheater.AssistirFilme();
        homeTheater.Sair();
    }
}

