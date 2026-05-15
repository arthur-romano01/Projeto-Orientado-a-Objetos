public abstract class Colega {
    protected Mediator mediator;

    public Colega(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract void enviar(String mensagem);
    public abstract void receber(String mensagem);
}
