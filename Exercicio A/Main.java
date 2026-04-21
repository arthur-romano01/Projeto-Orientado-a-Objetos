interface Notificação {
    void enviar();
}
class EmailNotificacao implements Notificação {
    public void enviar() { System.out.println("Enviando por Email"); }
}
class SMSNotificacao implements Notificação {
    public void enviar() { System.out.println("Enviando por SMS"); }
}
class PushNotificacao implements Notificação {
    public void enviar() { System.out.println("Enviando por Push"); }
}

class APIExternaSMS {
    public void enviarMensagemSMS() {
        System.out.println("Enviando por API Externa SMS");
    }
}

class SMSAdapter implements Notificação {
    private APIExternaSMS smsAdapter;
    public SMSAdapter(APIExternaSMS smsAdapter) {
        this.smsAdapter = smsAdapter;
    }

    @Override
    public void enviar() {
        smsAdapter.enviarMensagemSMS();
    }
}

class EnvioNotificacaoProxy implements Notificação {
    private Notificação notificacao;
    public EnvioNotificacaoProxy(Notificação notificacao) {
        this.notificacao = notificacao;
    }
    @Override
    public void enviar() {
        System.out.println("[LOG] Iniciando envio..."); 
        notificacao.enviar();                       
        System.out.println("[LOG] Envio concluído.");
    }
}

class ConfiguraçãoGlobal {
    private ConfiguraçãoGlobal() {}
    private static ConfiguraçãoGlobal instancia;

    public static ConfiguraçãoGlobal getInstancia() {
        if (instancia == null) {
            instancia = new ConfiguraçãoGlobal();
        }
        return instancia;
    }

    public void GuardaNome(String nome) {
        System.out.println(nome);
    }

    public void GuardaServidor(String servidor) {
        System.out.println(servidor);
    }

    public void GuardaTentativas(int tentativas) {
        System.out.println(tentativas);
    }

}

class EnviarNotificação {
    public static EmailNotificacao createEmail() {
        System.out.println("Criando Notificação Email");
        return new EmailNotificacao();
    }

    public static SMSNotificacao createSMS() {
        System.out.println("Criando Notificação SMS");
        return new SMSNotificacao();
    }

    public static PushNotificacao createPush() {
        System.out.println("Criando Notificação Push");
        return new PushNotificacao();
    }

}

public class Main {
    public static void main(String[] args) {
        Notificação email = new EnvioNotificacaoProxy(EnviarNotificação.createEmail());
        Notificação sms = new EnvioNotificacaoProxy(EnviarNotificação.createSMS());
        Notificação push = new EnvioNotificacaoProxy(EnviarNotificação.createPush());
        APIExternaSMS apiExterna = new APIExternaSMS();
        Notificação smsViaAdapter = new EnvioNotificacaoProxy(new SMSAdapter(apiExterna));
        smsViaAdapter.enviar();
        email.enviar();
        sms.enviar();
        push.enviar();
    }
}

    



