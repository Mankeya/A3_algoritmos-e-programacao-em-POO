/**
 * Colaborador que recebe valores adicionais pela produção de peças.
 * Herda os dados básicos de Colaborador e adiciona
 * a quantidade de peças e o valor recebido por peça.
 *
 * Regra: salário final = salário base + produtividade
 */
public class ColaboradorProducao extends Colaborador {

    // Atributos privados adicionais (encapsulamento)
    private int quantidadePecas;
    private double valorPorPeca;

    // Construtor: super() inicializa matrícula, nome e salário base
    public ColaboradorProducao(int matricula, String nome,
                               int quantidadePecas, double valorPorPeca) {
        super(matricula, nome);
        setQuantidadePecas(quantidadePecas);
        setValorPorPeca(valorPorPeca);
    }

    // Getters
    public int getQuantidadePecas() {
        return quantidadePecas;
    }

    public double getValorPorPeca() {
        return valorPorPeca;
    }

    // Setters com validação
    public void setQuantidadePecas(int quantidadePecas) {
        if (quantidadePecas >= 0) {
            this.quantidadePecas = quantidadePecas;
        }
    }

    public void setValorPorPeca(double valorPorPeca) {
        if (valorPorPeca >= 0) {
            this.valorPorPeca = valorPorPeca;
        }
    }

    // Calcula o valor da produtividade
    public double calcularProdutividade() {
        return quantidadePecas * valorPorPeca;
    }

    // Sobrescrita: considera o salário base + a produtividade
    @Override
    public double calcularSalarioFinal() {
        return getSalarioBase() + calcularProdutividade();
    }

    // Sobrescrita do tipo apresentado na folha de pagamento
    @Override
    public String getTipo() {
        return "Produção";
    }

    // Sobrescrita: mostra a produtividade junto com os dados
    @Override
    protected void exibirDetalhes() {
        System.out.println("Produtividade: " + formatarMoeda(calcularProdutividade()));
    }
}
