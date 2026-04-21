import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

class MainTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        // Redireciona stdout para capturar saídas de println
        System.setOut(new PrintStream(outContent));

        // Reseta o Singleton entre testes via reflection
        try {
            Field instancia = ConfiguraçãoGlobal.class.getDeclaredField("instancia");
            instancia.setAccessible(true);
            instancia.set(null, null);
        } catch (Exception e) {
            // Se o campo não existir, ignora
        }
    }

    void tearDown() {
        System.setOut(originalOut);
    }

    // -----------------------------------------------------------------------
    // Testes do padrão Factory
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("Factory cria EmailNotificacao e retorna instância não nula")
    void testFactoryCriaEmail() {
        Notificação n = EnviarNotificação.createEmail();
        assertNotNull(n, "createEmail() não deve retornar null");
        assertInstanceOf(EmailNotificacao.class, n);
    }

    @Test
    @DisplayName("Factory cria SMSNotificacao e retorna instância não nula")
    void testFactoryCriaSMS() {
        Notificação n = EnviarNotificação.createSMS();
        assertNotNull(n, "createSMS() não deve retornar null");
        assertInstanceOf(SMSNotificacao.class, n);
    }

    @Test
    @DisplayName("Factory cria PushNotificacao e retorna instância não nula")
    void testFactoryCriaPush() {
        Notificação n = EnviarNotificação.createPush();
        assertNotNull(n, "createPush() não deve retornar null");
        assertInstanceOf(PushNotificacao.class, n);
    }

    @Test
    @DisplayName("Factory imprime mensagem ao criar Email")
    void testFactoryMensagemEmail() {
        EnviarNotificação.createEmail();
        assertTrue(outContent.toString().contains("Email"),
                "Deve imprimir mensagem ao criar notificação de Email");
    }

    @Test
    @DisplayName("Factory imprime mensagem ao criar SMS")
    void testFactoryMensagemSMS() {
        EnviarNotificação.createSMS();
        assertTrue(outContent.toString().contains("SMS"),
                "Deve imprimir mensagem ao criar notificação de SMS");
    }

    @Test
    @DisplayName("Factory imprime mensagem ao criar Push")
    void testFactoryMensagemPush() {
        EnviarNotificação.createPush();
        assertTrue(outContent.toString().contains("Push"),
                "Deve imprimir mensagem ao criar notificação de Push");
    }

    // -----------------------------------------------------------------------
    // Testes dos tipos de notificação (interface Notificação)
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("EmailNotificacao.enviar() imprime 'Email'")
    void testEmailEnviar() {
        new EmailNotificacao().enviar();
        assertTrue(outContent.toString().contains("Email"));
    }

    @Test
    @DisplayName("SMSNotificacao.enviar() imprime 'SMS'")
    void testSMSEnviar() {
        new SMSNotificacao().enviar();
        assertTrue(outContent.toString().contains("SMS"));
    }

    @Test
    @DisplayName("PushNotificacao.enviar() imprime 'Push'")
    void testPushEnviar() {
        new PushNotificacao().enviar();
        assertTrue(outContent.toString().contains("Push"));
    }

    @Test
    @DisplayName("Todas as implementações seguem a interface Notificação")
    void testImplementamInterface() {
        assertTrue(new EmailNotificacao() instanceof Notificação);
        assertTrue(new SMSNotificacao() instanceof Notificação);
        assertTrue(new PushNotificacao() instanceof Notificação);
    }

    // -----------------------------------------------------------------------
    // Testes do padrão Singleton
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("Singleton: getInstancia() nunca retorna null")
    void testSingletonNaoNulo() {
        assertNotNull(ConfiguraçãoGlobal.getInstancia());
    }

    @Test
    @DisplayName("Singleton: duas chamadas retornam a mesma instância")
    void testSingletonMesmaInstancia() {
        ConfiguraçãoGlobal a = ConfiguraçãoGlobal.getInstancia();
        ConfiguraçãoGlobal b = ConfiguraçãoGlobal.getInstancia();
        assertSame(a, b, "Singleton deve retornar sempre a mesma instância");
    }

    @Test
    @DisplayName("Singleton: construtor privado (não pode ser instanciado externamente)")
    void testSingletonConstrutorPrivado() throws NoSuchMethodException {
        Constructor<ConfiguraçãoGlobal> ctor =
                ConfiguraçãoGlobal.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(ctor.getModifiers()),
                "O construtor de ConfiguraçãoGlobal deve ser privado");
    }

    // -----------------------------------------------------------------------
    // Testes dos métodos de ConfiguraçãoGlobal
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("GuardaNome imprime o nome fornecido")
    void testGuardaNome() {
        ConfiguraçãoGlobal.getInstancia().GuardaNome("MeuApp");
        assertTrue(outContent.toString().contains("MeuApp"));
    }

    @Test
    @DisplayName("GuardaServidor imprime o servidor fornecido")
    void testGuardaServidor() {
        ConfiguraçãoGlobal.getInstancia().GuardaServidor("smtp.exemplo.com");
        assertTrue(outContent.toString().contains("smtp.exemplo.com"));
    }

    @Test
    @DisplayName("GuardaTentativas imprime a quantidade fornecida")
    void testGuardaTentativas() {
        ConfiguraçãoGlobal.getInstancia().GuardaTentativas(5);
        assertTrue(outContent.toString().contains("5"));
    }
}
