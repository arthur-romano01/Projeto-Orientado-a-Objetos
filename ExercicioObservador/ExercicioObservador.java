package Exercicios.ExercicioObservador;

import java.util.ArrayList;
import java.util.List;

class Subject {
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
}

class Temperatura extends Subject {
    private double temp;

    public double getTemp() {
        return temp;
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

    public void setUmidadeRelativa(double umidadeRelativa) {
        this.umidadeRelativa = umidadeRelativa;
        notifyObservers();
    }
}
interface Observer {
    void update(Subject s);
}

class Unifesp implements Observer {
    @Override
    public void update(Subject s) {
        if (s instanceof Temperatura) {
            System.out.println("[UNIFESP] Temperatura atualizada: " + ((Temperatura) s).getTemp() + "°C");
        } else if (s instanceof PH) {
            System.out.println("[UNIFESP] PH atualizado: " + ((PH) s).getPh());
        } else if (s instanceof UmidadeRelativa) {
            System.out.println("[UNIFESP] Umidade Relativa atualizada: " + ((UmidadeRelativa) s).getUmidadeRelativa() + "%");
        }
    }
}

class USP implements Observer {
    @Override
    public void update(Subject s) {
        if (s instanceof Temperatura) {
            System.out.println("[USP] Temperatura atualizada: " + ((Temperatura) s).getTemp() + "°C");
        } else if (s instanceof PH) {
            System.out.println("[USP] PH atualizado: " + ((PH) s).getPh());
        } else if (s instanceof UmidadeRelativa) {
            System.out.println("[USP] Umidade Relativa atualizada: " + ((UmidadeRelativa) s).getUmidadeRelativa() + "%");
        }
    }
}

class Unicamp implements Observer {
    @Override
    public void update(Subject s) {
        if (s instanceof Temperatura) {
            System.out.println("[UNICAMP] Temperatura atualizada: " + ((Temperatura) s).getTemp() + "°C");
        } else if (s instanceof PH) {
            System.out.println("[UNICAMP] PH atualizado: " + ((PH) s).getPh());
        } else if (s instanceof UmidadeRelativa) {
            System.out.println("[UNICAMP] Umidade Relativa atualizada: " + ((UmidadeRelativa) s).getUmidadeRelativa() + "%");
        }
    }
}

public class ExercicioObservador {
    public static void main(String[] args) {

        Temperatura temperatura = new Temperatura();
        PH ph = new PH();
        UmidadeRelativa umidade = new UmidadeRelativa();

        Observer unifesp = new Unifesp();
        Observer usp = new USP();
        Observer unicamp = new Unicamp();
        temperatura.addObserver(unifesp);
        temperatura.addObserver(usp);
        temperatura.addObserver(unicamp);

        ph.addObserver(unifesp);
        ph.addObserver(usp);
        ph.addObserver(unicamp);

        umidade.addObserver(unifesp);
        umidade.addObserver(usp);
        umidade.addObserver(unicamp);

        System.out.println("=== Atualizando Temperatura ===");
        temperatura.setTemp(32.5);

        System.out.println("\n=== Atualizando PH ===");
        ph.setPh(6.8);

        System.out.println("\n=== Atualizando Umidade Relativa ===");
        umidade.setUmidadeRelativa(89.3);
        
        System.out.println("\n=== Removendo UNICAMP da Temperatura ===");
        temperatura.removeObserver(unicamp);

        System.out.println("\n=== Atualizando Temperatura novamente ===");
        temperatura.setTemp(34.1);
    }
}
