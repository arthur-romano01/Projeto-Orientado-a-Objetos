import java.util.ArrayList;
import java.util.List;

abstract class Subject {
    private List<Observer> observadores = new ArrayList<>();

    public void addObserver(Observer o) {
        observadores.add(o);
    }

    public void removeObserver(Observer o) {
        observadores.remove(o);
    }

    public void notifyObservers() {
        for (Observer o : observadores) {
            o.update(this);
        }
    }

    public abstract String getNome();
    public abstract String getValorFormatado();
}

class Temperatura extends Subject {
    private double temp;

    public double getTemp() {
        return temp;
    }

    @Override
    public String getNome() {
        return "Temperatura";
    }

    @Override
    public String getValorFormatado() {
        return temp + "°C";
    }

    public void setTemp(double temp) {
        this.temp = temp;
        notifyObservers();
    }
}

class PH extends Subject {
    private double ph;

    public double getPh() {
        return ph;
    }

    @Override
    public String getNome() {
        return "PH";
    }

    @Override
    public String getValorFormatado() {
        return String.valueOf(ph);
    }

    public void setPh(double ph) {
        this.ph = ph;
        notifyObservers();
    }
}

class UmidadeRelativa extends Subject {
    private double umidadeRelativa;

    public double getUmidadeRelativa() {
        return umidadeRelativa;
    }

    @Override
    public String getNome() {
        return "Umidade Relativa";
    }

    @Override
    public String getValorFormatado() {
        return umidadeRelativa + "%";
    }

    public void setUmidadeRelativa(double umidadeRelativa) {
        this.umidadeRelativa = umidadeRelativa;
        notifyObservers();
    }
}
interface Observer {
    void update(Subject s);
}
// Inversão de controle e dependência
class Unifesp implements Observer {
    @Override
    public void update(Subject s) {
        System.out.println("[UNIFESP] " + s.getNome() + " atualizado: " + s.getValorFormatado());
    }
}
// Inversão de controle e dependência
class USP implements Observer {
    @Override
    public void update(Subject s) {
        System.out.println("[USP] " + s.getNome() + " atualizado: " + s.getValorFormatado());
    }
}
// Inversão de controle e dependência
class Unicamp implements Observer {
    @Override
    public void update(Subject s) {
        System.out.println("[UNICAMP] " + s.getNome() + " atualizado: " + s.getValorFormatado());
    }
}

public class ExercicioInversao {
    public static void main(String[] args) {
        // Temperatura compartilhada (mesmo valor para todos)
        Temperatura temperatura = new Temperatura();

        // PH separado por universidade
        PH phUnifesp = new PH();
        PH phUsp = new PH();
        PH phUnicamp = new PH();

        // Umidade separada por universidade
        UmidadeRelativa umidadeUnifesp = new UmidadeRelativa();
        UmidadeRelativa umidadeUsp = new UmidadeRelativa();
        UmidadeRelativa umidadeUnicamp = new UmidadeRelativa();

        Observer unifesp = new Unifesp();
        Observer usp = new USP();
        Observer unicamp = new Unicamp();

        temperatura.addObserver(unifesp);
        temperatura.addObserver(usp);
        temperatura.addObserver(unicamp);
        
        phUnifesp.addObserver(unifesp);
        phUsp.addObserver(usp);
        phUnicamp.addObserver(unicamp);

        umidadeUnifesp.addObserver(unifesp);
        umidadeUsp.addObserver(usp);
        umidadeUnicamp.addObserver(unicamp);

        System.out.println("=== Atualizando Temperatura ===");
        temperatura.setTemp(32.5);

        System.out.println("\n=== Atualizando PH ===");
        phUnifesp.setPh(6.8);
        phUsp.setPh(7.2);
        phUnicamp.setPh(5.9);

        System.out.println("\n=== Atualizando Umidade Relativa ===");
        umidadeUnifesp.setUmidadeRelativa(85.0);
        umidadeUsp.setUmidadeRelativa(72.3);
        umidadeUnicamp.setUmidadeRelativa(91.5);
    }
}
