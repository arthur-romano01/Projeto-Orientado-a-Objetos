abstract class Relatorio {
    private String tipo;

    public Relatorio(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public abstract void accept(Visitor visitor);
}

class RelatorioVendas extends Relatorio {
    public RelatorioVendas() {
        super("Vendas");
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class RelatorioFinanceiro extends Relatorio {
    public RelatorioFinanceiro() {
        super("Financeiro");
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class RelatorioContasPagas extends Relatorio {
    public RelatorioContasPagas() {
        super("Contas Pagas");
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class RelatorioContasReceber extends Relatorio {
    public RelatorioContasReceber() {
        super("Contas Receber");
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

interface Visitor {
    void visit(RelatorioVendas relatorioVendas);
    void visit(RelatorioFinanceiro relatorioFinanceiro);
    void visit(RelatorioContasPagas relatorioContasPagas);
    void visit(RelatorioContasReceber relatorioContasReceber);
}

class GeradorRelatorioPdf implements Visitor {
    public void visit(RelatorioVendas relatorioVendas) {
        System.out.println("Gerando RelatorioVendas em PDF");
    }

    public void visit(RelatorioFinanceiro relatorioFinanceiro) {
        System.out.println("Gerando RelatorioFinanceiro em PDF");
    }

    public void visit(RelatorioContasPagas relatorioContasPagas) {
        System.out.println("Gerando RelatorioContasPagas em PDF");
    }

    public void visit(RelatorioContasReceber relatorioContasReceber) {
        System.out.println("Gerando RelatorioContasReceber em PDF");
    }
}

class GeradorRelatorioXml implements Visitor {
    public void visit(RelatorioVendas relatorioVendas) {
        System.out.println("Gerando RelatorioVendas em XML");
    }

    public void visit(RelatorioFinanceiro relatorioFinanceiro) {
        System.out.println("Gerando RelatorioFinanceiro em XML");
    }

    public void visit(RelatorioContasPagas relatorioContasPagas) {
        System.out.println("Gerando RelatorioContasPagas em XML");
    }

    public void visit(RelatorioContasReceber relatorioContasReceber) {
        System.out.println("Gerando RelatorioContasReceber em XML");
    }
}

class GeradorRelatorioHtml implements Visitor {
    public void visit(RelatorioVendas relatorioVendas) {
        System.out.println("Gerando RelatorioVendas em HTML");
    }

    public void visit(RelatorioFinanceiro relatorioFinanceiro) {
        System.out.println("Gerando RelatorioFinanceiro em HTML");
    }

    public void visit(RelatorioContasPagas relatorioContasPagas) {
        System.out.println("Gerando RelatorioContasPagas em HTML");
    }

    public void visit(RelatorioContasReceber relatorioContasReceber) {
        System.out.println("Gerando RelatorioContasReceber em HTML");
    }
}

class GeradorRelatorioCsv implements Visitor {
    public void visit(RelatorioVendas relatorioVendas) {
        System.out.println("Gerando RelatorioVendas em CSV");
    }

    public void visit(RelatorioFinanceiro relatorioFinanceiro) {
        System.out.println("Gerando RelatorioFinanceiro em CSV");
    }

    public void visit(RelatorioContasPagas relatorioContasPagas) {
        System.out.println("Gerando RelatorioContasPagas em CSV");
    }

    public void visit(RelatorioContasReceber relatorioContasReceber) {
        System.out.println("Gerando RelatorioContasReceber em CSV");
    }
}






public class ExercicioVisitor {
    public static void main(String[] args) {
        Relatorio vendas = new RelatorioVendas();
        Relatorio financeiro = new RelatorioFinanceiro();
        Relatorio contasPagas = new RelatorioContasPagas();
        Relatorio contasReceber = new RelatorioContasReceber();

        Relatorio[] relatorios = { vendas, financeiro, contasPagas, contasReceber };

        Visitor geradorPdf = new GeradorRelatorioPdf();
        Visitor geradorXml = new GeradorRelatorioXml();
        Visitor geradorHtml = new GeradorRelatorioHtml();
        Visitor geradorCsv = new GeradorRelatorioCsv();

        System.out.println("=== Gerando em PDF ===");
        for (Relatorio r : relatorios) {
            r.accept(geradorPdf);
        }

        System.out.println("\n=== Gerando em XML ===");
        for (Relatorio r : relatorios) {
            r.accept(geradorXml);
        }

        System.out.println("\n=== Gerando em HTML ===");
        for (Relatorio r : relatorios) {
            r.accept(geradorHtml);
        }

        System.out.println("\n=== Gerando em CSV ===");
        for (Relatorio r : relatorios) {
            r.accept(geradorCsv);
        }
    }
}
