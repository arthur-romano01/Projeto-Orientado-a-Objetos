public interface Mediator {
    void enviar(String mensagem, Colega origem);
    void receber(String mensagem, Colega destino);
}

class ConcreteMediator implements Mediator{
    private Colega colega1;
    private Colega colega2;

    public ConcreteMediator() {
        this.colega1 = new ColegaConcreta1(this);
        this.colega2 = new ColegaConcreta2(this);
    }
    @Override
    public void enviar(String mensagem, Colega origem) {
        if (origem == colega1) {
            colega2.receber(mensagem);
        } else if (origem == colega2) {
            colega1.receber(mensagem);
        }
    }

    @Override
    public void receber(String mensagem, Colega destino) {
        destino.receber(mensagem);
    }

     public Colega getColega1() { return colega1; }
    public Colega getColega2() { return colega2; }
}

class ColegaConcreta1 extends Colega {
    public ColegaConcreta1(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void enviar(String mensagem) {
        mediator.enviar(mensagem, this);
    }

    @Override
    public void receber(String mensagem) {
        System.out.println("ColegaConcreta1 recebeu: " + mensagem);
    }
}

class ColegaConcreta2 extends Colega {
    public ColegaConcreta2(Mediator mediator) {
        super(mediator);
    }

    @Override
    public void enviar(String mensagem) {
        mediator.enviar(mensagem, this);
    }

    @Override
    public void receber(String mensagem) {
        System.out.println("ColegaConcreta2 recebeu: " + mensagem);
    }
}



