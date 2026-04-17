package Exercicios.ExercicioObservador;

import java.util.ArrayList;
import java.util.List;



interface Observer {
    void update();
}

class Unifesp implements Observer {
    @Override
    public void update() {
        System.out.println("Unifesp atualizada");
    }
}

class USP implements Observer {
    @Override
    public void update() {
        System.out.println("USP atualizada");
    }
}

class Unicamp implements Observer {
    @Override
    public void update() {
        System.out.println("Unicamp atualizada");
    }
}

public class ExercicioObservador {
    public static void main(String[] args) {
        Observer o1 = new Unifesp();
        Observer o2 = new USP();
        Observer o3 = new Unicamp();

        List<Observer> observadores = new ArrayList<>();
        observadores.add(o1);
        observadores.add(o2);
        observadores.add(o3);

        for (Observer o : observadores) {
            o.update();
        }
    }
}
