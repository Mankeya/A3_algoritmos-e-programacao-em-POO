/**
 * Colaborador que recebe comissão sobre suas vendas.
 * Herda os dados básicos de Colaborador e adiciona
 * vendas e percentual de comissão.
 *
 * Regra: salário final = salário base + comissão
 */
public class ColaboradorComissionado extends Colaborador {

    // Atributos privados adicionais (encapsulamento)
    private double valorVendas;
    private double percentualComissao;

    // Construtor: super() inicializa matrícula, nome e salário base
    public ColaboradorComissionado(int matricula, String nome,
                                   double valorVendas, double percentualComissao) {
        super(matricula, nome);
        setValorVendas(valorVendas);
        setPercentualComissao(percentualComissao);
    }

    // Getters
    public double getValorVendas() {
        return valorVendas;
    }

    public double getPercentualComissao() {
        return percentualComissao;
    }

    // Setters com validação
    public void setValorVendas(double valorVendas) {
        if (valorVendas >= 0) {
            this.valorVendas = valorVendas;
        }
    }

    public void setPercentualComissao(double percentualComissao) {
        if (percentualComissao >= 0 && percentualComissao <= 100) {
            this.percentualComissao = percentualComissao;
        }
    }

    // Calcula o valor da comissão
    public double calcularComissao() {
        return valorVendas * percentualComissao / 100;
    }

    // Sobrescrita: considera o salário base + a comissão
    @Override
    public double calcularSalarioFinal() {
        return getSalarioBase() + calcularComissao();
    }

    // Sobrescrita do tipo apresentado na folha de pagamento
    @Override
    public String getTipo() {
        return "Comissionado";
    }

    // Sobrescrita: mostra a comissão junto com os dados
    @Override
    protected void exibirDetalhes() {
        System.out.println("Comissão: " + formatarMoeda(calcularComissao()));
    }
}
