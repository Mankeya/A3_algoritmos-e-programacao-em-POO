# Sistema de Folha de Pagamento

Projeto acadêmico desenvolvido em **Java puro** para a disciplina de Programação
Orientada a Objetos. É uma aplicação de terminal que cadastra colaboradores de
uma empresa hipotética e calcula a folha de pagamento de acordo com diferentes
modalidades de remuneração.

Todos os dados ficam **em memória** durante a execução: não há banco de dados,
frameworks, interface gráfica ou persistência em arquivos.

## Objetivos

- Cadastar colaboradores dos tipos **Padrão**, **Comissionado** e **Produção**.
- Calcular o salário final de cada colaborador conforme sua modalidade.
- Gerar a folha de pagamento completa e um resumo com totais e média salarial.
- Demonstrar os conceitos fundamentais de Programação Orientada a Objetos.

## Estrutura dos arquivos

```text
SistemaFolhaPagamento/
│
├── src/
│   ├── Colaborador.java              # Classe base
│   ├── ColaboradorPadrao.java        # Herda de Colaborador
│   ├── ColaboradorComissionado.java  # Herda de Colaborador
│   ├── ColaboradorProducao.java      # Herda de Colaborador
│   └── Principal.java                # Menu e fluxo principal
│
├── README.md
└── docs/
    └── fluxogramas.md                # Fluxogramas em Mermaid
```

## Aplicação dos Conceitos de POO

| Conceito | Classe ou método em que foi aplicado | Explicação |
|---|---|---|
| **Classe** | `Colaborador`, `ColaboradorPadrao`, `ColaboradorComissionado`, `ColaboradorProducao`, `Principal` | Cada classe modela um conceito do sistema: o colaborador genérico, suas três modalidades e o menu principal. |
| **Objeto** | `new ColaboradorPadrao(...)`, `new ColaboradorComissionado(...)`, `new ColaboradorProducao(...)` no `Principal` | Cada colaborador cadastrado é instanciado com `new` e seu respectivo construtor, sendo guardado no `ArrayList<Colaborador>`. |
| **Método** | `calcularSalarioFinal()`, `calcularComissao()`, `calcularProdutividade()`, `exibirDados()`, `exibirResumo()`, `calcularTotalFolha()` | Cada método tem uma responsabilidade bem definida, evitando repetição de código. |
| **Herança** | `extends Colaborador` nas três subclasses e uso de `super()` nos construtores | As subclasses reaproveitam os atributos, getters e comportamentos da superclasse, acrescentando apenas o que é específico de cada modalidade. |
| **Encapsulamento** | Atributos `private` em todas as classes, com getters e setters validados (ex.: `setPercentualComissao()` só aceita valores entre 0 e 100) | Os atributos só são acessados ou alterados pelos métodos da classe, que garantem valores válidos. |
| **Constante** | `public static final double SALARIO_BASE = 2000.00` em `Colaborador` | O salário base é o mesmo para todos os colaboradores e não pode ser alterado (não possui setter). |
| **Polimorfismo** | `ArrayList<Colaborador>` no `Principal` e o laço `for` da opção "Gerar Folha de Pagamento" | A lista guarda os três tipos de colaboradores por referência da superclasse; cada objeto executa seu próprio `calcularSalarioFinal()` e `exibirDados()` em tempo de execução, sem uso de `instanceof`. |
| **Sobrescrita** | `@Override` em `calcularSalarioFinal()`, `getTipo()` e `exibirDetalhes()` nas subclasses | Cada modalidade especializa o cálculo salarial e a apresentação dos dados. |

## Explicação das classes

### `Colaborador` (classe base)

Representa os dados e comportamentos comuns a todos os funcionários.

- **Atributos privados:** `matricula` (int), `nome` (String), `salarioBase` (double).
- **Constante:** `SALARIO_BASE = 2000.00` (`static final`).
- **Construtor:** recebe matrícula e nome e atribui o salário base constante.
- **Métodos:** `calcularSalarioFinal()`, `exibirDados()`, `getTipo()` e os
  getters/setters (os setters validam os valores recebidos).

### `ColaboradorPadrao`

Hereda diretamente da classe `Colaborador` (usa `extends`). Não possui atributos adicionais: o salário
final é simplesmente o salário base. O tipo apresentado é "Padrão".

### `ColaboradorComissionado`

Hereda de `Colaborador` e acrescenta `valorVendas` e `percentualComissao`.
Calcula a comissão com `calcularComissao()` e sobrescreve
`calcularSalarioFinal()` para somar salário base + comissão. O tipo
apresentado é "Comissionado".

### `ColaboradorProducao`

Hereda de `Colaborador` e acrescenta `quantidadePecas` e `valorPorPeca`.
Calcula a produtividade com `calcularProdutividade()` e sobrescreve
`calcularSalarioFinal()` para somar salário base + produtividade. O tipo
apresentado é "Produção".

### `Principal`

Contém o `main()`, o menu interativo (`do-while` + `switch`) e os métodos de
cadastro, geração da folha, resumo e leitura/validação dos dados digitados.

## Fórmulas de cálculo

```text
Salário base (constante)           = R$ 2.000,00

Colaborador Padrão:
salarioFinal = salarioBase

Colaborador Comissionado:
comissao     = valorVendas * percentualComissao / 100
salarioFinal = salarioBase + comissao

Colaborador Produção:
produtividade = quantidadePecas * valorPorPeca
salarioFinal  = salarioBase + produtividade

Resumo:
totalColaboradores = colaboradores.size()
totalFolha         = soma de todos os salários finais
mediaSalarial      = totalFolha / totalColaboradores   (0 quando a lista é vazia)
```

## Como compilar e executar

Pré-requisito: **JDK 17 ou superior** instalado (`java -version` para
verificar).

A partir da pasta do projeto:

```bash
# Compilar (gera os arquivos .class na pasta out)
javac -d out src/*.java

# Executar
java -cp out Principal
```

No Windows (PowerShell), se o glob `src/*.java` não funcionar:

```powershell
javac -d out (Get-ChildItem src\*.java).FullName
java -cp out Principal
```

## Exemplos de utilização

### Menu principal

```text
========================================
       SISTEMA DE FOLHA DE PAGAMENTO
========================================
1 - Cadastrar Colaborador Padrão
2 - Cadastrar Colaborador Comissionado
3 - Cadastrar Colaborador Produção
4 - Gerar Folha de Pagamento
5 - Exibir Resumo da Folha
0 - Sair do Programa
========================================
Escolha uma opção:
```

### Cadastro de um colaborador comissionado (opção 2)

```text
--- Cadastro de Colaborador Comissionado ---
Matrícula: 234
Nome completo: Maria Silva
Valor total das vendas: 5000
Percentual de comissão (0 a 100): 10
Colaborador Comissionado cadastrado com sucesso!
```

### Folha de pagamento (opção 4)

```text
========================================
          FOLHA DE PAGAMENTO
========================================
Matrícula: 234
Nome: Maria Silva
Tipo: Comissionado
Salário Base: R$ 2.000,00
Comissão: R$ 500,00
Salário Final: R$ 2.500,00
----------------------------------------
```

### Resumo da folha (opção 5)

```text
========================================
          RESUMO DA FOLHA
========================================
Total de colaboradores: 3
Total da folha: R$ 7.000,00
Média salarial: R$ 2.333,33
========================================
```

### Validações

O sistema pede a informação novamente em caso de entrada inválida, por
exemplo:

- Matrícula negativa, igual a zero ou já cadastrada.
- Nome vazio.
- Valor de vendas, quantidade de peças ou valor por peça negativos.
- Percentual de comissão fora da faixa de 0 a 100.
- Letras ou caracteres especiais em campos numéricos (ex.: `abc`).
- Opção inválida no menu.
