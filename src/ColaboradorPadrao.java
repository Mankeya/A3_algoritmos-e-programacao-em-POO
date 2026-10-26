/**
 * Colaborador comum, sem valores adicionais.
 * Herda tudo da classe Colaborador.
 * Regra: salário final = salário base.
 */
public class ColaboradorPadrao extends Colaborador {

    // Construtor: reaproveita o construtor da superclasse com super()
    public ColaboradorPadrao(int matricula, String nome) {
        super(matricula, nome);
    }

    // Não possui atributos adicionais nem sobrescritas:
    // getTipo() já retorna "Padrão" e calcularSalarioFinal()
    // já retorna o salário base, herdados da superclasse.
}
