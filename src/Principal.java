import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe principal: contém o menu interativo e controla
 * o cadastro de colaboradores e a geração da folha de pagamento.
 *
 * Os dados ficam apenas em memória, em um ArrayList<Colaborador>.
 * Utilizando referências do tipo Colaborador, conseguimos guardar os
 * três tipos de colaboradores na mesma lista (polimorfismo).
 */
public class Principal {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ArrayList<Colaborador> colaboradores = new ArrayList<>();

        int opcao;

        // Laço do menu: repete até o usuário escolher a opção 0
        do {
            exibirMenu();
            opcao = lerInteiro(teclado, "Escolha uma opção: ", 0);

            switch (opcao) {
                case 1:
                    cadastrarPadrao(teclado, colaboradores);
                    break;
                case 2:
                    cadastrarComissionado(teclado, colaboradores);
                    break;
                case 3:
                    cadastrarProducao(teclado, colaboradores);
                    break;
                case 4:
                    gerarFolha(colaboradores);
                    break;
                case 5:
                    exibirResumo(colaboradores);
                    break;
                case 0:
                    System.out.println("Encerrando o Sistema de Folha de Pagamento. Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Escolha um número entre 0 e 5.");
            }

            System.out.println();

        } while (opcao != 0);

        teclado.close();
    }

    // Mostra o menu principal na tela
    public static void exibirMenu() {
        System.out.println("========================================");
        System.out.println("       SISTEMA DE FOLHA DE PAGAMENTO");
        System.out.println("========================================");
        System.out.println("1 - Cadastrar Colaborador Padrão");
        System.out.println("2 - Cadastrar Colaborador Comissionado");
        System.out.println("3 - Cadastrar Colaborador Produção");
        System.out.println("4 - Gerar Folha de Pagamento");
        System.out.println("5 - Exibir Resumo da Folha");
        System.out.println("0 - Sair do Programa");
        System.out.println("========================================");
    }

    // ----------------------------------------
    // Cadastros
    // ----------------------------------------

    public static void cadastrarPadrao(Scanner teclado, ArrayList<Colaborador> colaboradores) {
        System.out.println("--- Cadastro de Colaborador Padrão ---");
        int matricula = lerMatricula(teclado, colaboradores);
        String nome = lerNome(teclado);

        // Criamos o objeto e guardamos na lista como Colaborador (polimorfismo)
        ColaboradorPadrao novo = new ColaboradorPadrao(matricula, nome);
        colaboradores.add(novo);

        System.out.println("Colaborador Padrão cadastrado com sucesso!");
    }

    public static void cadastrarComissionado(Scanner teclado, ArrayList<Colaborador> colaboradores) {
        System.out.println("--- Cadastro de Colaborador Comissionado ---");
        int matricula = lerMatricula(teclado, colaboradores);
        String nome = lerNome(teclado);
        double valorVendas = lerDecimal(teclado, "Valor total das vendas: ", 0);
        double percentual = lerPercentual(teclado);

        ColaboradorComissionado novo = new ColaboradorComissionado(matricula, nome, valorVendas, percentual);
        colaboradores.add(novo);

        System.out.println("Colaborador Comissionado cadastrado com sucesso!");
    }

    public static void cadastrarProducao(Scanner teclado, ArrayList<Colaborador> colaboradores) {
        System.out.println("--- Cadastro de Colaborador Produção ---");
        int matricula = lerMatricula(teclado, colaboradores);
        String nome = lerNome(teclado);
        int quantidadePecas = lerInteiro(teclado, "Quantidade de peças produzidas: ", 0);
        double valorPorPeca = lerDecimal(teclado, "Valor recebido por peça: ", 0);

        ColaboradorProducao novo = new ColaboradorProducao(matricula, nome, quantidadePecas, valorPorPeca);
        colaboradores.add(novo);

        System.out.println("Colaborador Produção cadastrado com sucesso!");
    }

    // ----------------------------------------
    // Folha de pagamento e resumo
    // ----------------------------------------

    public static void gerarFolha(ArrayList<Colaborador> colaboradores) {
        System.out.println("========================================");
        System.out.println("          FOLHA DE PAGAMENTO");
        System.out.println("========================================");

        if (colaboradores.isEmpty()) {
            System.out.println("A folha está vazia. Nenhum colaborador cadastrado.");
            return;
        }

        // Percorre a lista com polimorfismo: cada objeto sabe como
        // exibir seus dados e calcular seu próprio salário final.
        for (Colaborador colaborador : colaboradores) {
            colaborador.exibirDados();
            System.out.println("----------------------------------------");
        }
    }

    public static void exibirResumo(ArrayList<Colaborador> colaboradores) {
        int totalColaboradores = colaboradores.size();
        double totalFolha = calcularTotalFolha(colaboradores);
        double mediaSalarial = calcularMediaSalarial(totalFolha, totalColaboradores);

        System.out.println("========================================");
        System.out.println("          RESUMO DA FOLHA");
        System.out.println("========================================");
        System.out.println("Total de colaboradores: " + totalColaboradores);
        System.out.println("Total da folha: " + Colaborador.formatarMoeda(totalFolha));
        System.out.println("Média salarial: " + Colaborador.formatarMoeda(mediaSalarial));
        System.out.println("========================================");
    }

    // Soma o salário final de todos os colaboradores (cálculo polimórfico)
    public static double calcularTotalFolha(ArrayList<Colaborador> colaboradores) {
        double total = 0;
        for (Colaborador colaborador : colaboradores) {
            total = total + colaborador.calcularSalarioFinal();
        }
        return total;
    }

    // Calcula a média salarial, evitando divisão por zero
    public static double calcularMediaSalarial(double totalFolha, int totalColaboradores) {
        if (totalColaboradores == 0) {
            return 0;
        }
        return totalFolha / totalColaboradores;
    }

    // ----------------------------------------
    // Leitura e validação de dados
    // Todas as entradas são lidas como texto e convertidas manualmente,
    // evitando problemas com nextInt()/nextDouble().
    // ----------------------------------------

    // Lê um número inteiro repetindo até o usuário digitar um valor válido
    public static int lerInteiro(Scanner teclado, String mensagem, int minimo) {
        while (true) {
            System.out.print(mensagem);
            String texto = teclado.nextLine().trim();

            // Verifica se o texto contém apenas dígitos
            if (texto.matches("\\d+")) {
                int valor = Integer.parseInt(texto);
                if (valor >= minimo) {
                    return valor;
                }
            }

            System.out.println("Entrada inválida! Digite um número inteiro maior ou igual a " + minimo + ".");
        }
    }

    // Lê um número decimal repetindo até o usuário digitar um valor válido
    // (aceita vírgula ou ponto como separador decimal)
    public static double lerDecimal(Scanner teclado, String mensagem, double minimo) {
        while (true) {
            System.out.print(mensagem);
            String texto = teclado.nextLine().trim().replace(',', '.');

            if (texto.matches("\\d+(\\.\\d+)?")) {
                double valor = Double.parseDouble(texto);
                if (valor >= minimo) {
                    return valor;
                }
            }

            System.out.println("Entrada inválida! Digite um número maior ou igual a " + minimo + ".");
        }
    }

    // Lê o percentual de comissão, que deve estar entre 0 e 100
    public static double lerPercentual(Scanner teclado) {
        while (true) {
            double percentual = lerDecimal(teclado, "Percentual de comissão (0 a 100): ", 0);
            if (percentual <= 100) {
                return percentual;
            }
            System.out.println("Percentual inválido! Informe um valor entre 0 e 100.");
        }
    }

    // Lê a matrícula: deve ser inteira positiva e não pode estar duplicada
    public static int lerMatricula(Scanner teclado, ArrayList<Colaborador> colaboradores) {
        while (true) {
            int matricula = lerInteiro(teclado, "Matrícula: ", 1);

            boolean duplicada = false;
            for (Colaborador colaborador : colaboradores) {
                if (colaborador.getMatricula() == matricula) {
                    duplicada = true;
                }
            }

            if (!duplicada) {
                return matricula;
            }

            System.out.println("Matrícula já cadastrada! Informe outra matrícula.");
        }
    }

    // Lê o nome completo, que não pode ficar vazio
    public static String lerNome(Scanner teclado) {
        while (true) {
            System.out.print("Nome completo: ");
            String nome = teclado.nextLine().trim();

            if (!nome.isEmpty()) {
                return nome;
            }

            System.out.println("Nome inválido! O nome não pode ficar vazio.");
        }
    }
}
