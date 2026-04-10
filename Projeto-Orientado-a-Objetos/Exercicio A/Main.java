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






class teste {
    public static teste create() {
        System.out.println("Criando teste");
        return new teste();
    }
}

public class Main {
    public static void main(String[] args) {
        Notificação email = EnviarNotificação.createEmail();
        Notificação sms = EnviarNotificação.createSMS();
        Notificação push = EnviarNotificação.createPush();
        email.enviar();
        sms.enviar();
        push.enviar();
    }
}

    



