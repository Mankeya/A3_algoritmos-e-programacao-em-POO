import java.text.NumberFormat;
import java.util.Locale;

/**
 * Classe base que representa um colaborador da empresa.
 * Possui os dados comuns (matrícula, nome e salário base)
 * e é herdada pelas classes ColaboradorPadrao,
 * ColaboradorComissionado e ColaboradorProducao.
 */
public class Colaborador {

    // Constante: o salário base é o mesmo para todos os colaboradores.
    // static final = compartilhada por todas as instâncias e imutável (não possui setter).
    public static final double SALARIO_BASE = 2000.00;

    // Atributos privados: só podem ser acessados pelos métodos da própria classe (encapsulamento)
    private int matricula;
    private String nome;
    private double salarioBase;

    // Construtor: inicializa matrícula e nome e atribui o salário base padrão
    public Colaborador(int matricula, String nome) {
        this.matricula = matricula;
        this.nome = nome;
        this.salarioBase = SALARIO_BASE;
    }

    // Getters
    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    // Setters com validação (acesso controlado aos atributos)
    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        }
    }

    public void setSalarioBase(double salarioBase) {
        if (salarioBase >= 0) {
            this.salarioBase = salarioBase;
        }
    }

    // Retorna o tipo do colaborador. É sobrescrito pelas subclasses.
    public String getTipo() {
        return "Padrão";
    }

    // Calcula o salário final. É sobrescrito pelas subclasses
    // que recebem valores adicionais (comissão ou produtividade).
    public double calcularSalarioFinal() {
        return salarioBase;
    }

    // Exibe os dados do colaborador (também pode ser sobrescrito)
    public void exibirDados() {
        System.out.println("Matrícula: " + matricula);
        System.out.println("Nome: " + nome);
        System.out.println("Tipo: " + getTipo());
        System.out.println("Salário Base: " + formatarMoeda(salarioBase));
        // Ponto de extensão: subclasses imprimem aqui a comissão ou a produtividade
        exibirDetalhes();
        System.out.println("Salário Final: " + formatarMoeda(calcularSalarioFinal()));
    }

    // Método auxiliar sobrescrito apenas pelas subclasses com valores adicionais
    protected void exibirDetalhes() {
    }

    // Formata um valor em reais (R$ 2.000,00) usando NumberFormat
    public static String formatarMoeda(double valor) {
        NumberFormat formato = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
        return formato.format(valor);
    }
}
