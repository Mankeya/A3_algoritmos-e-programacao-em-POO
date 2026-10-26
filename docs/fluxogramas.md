# Fluxogramas — Sistema de Folha de Pagamento

Este documento apresenta os fluxogramas do funcionamento do sistema, escritos
na sintaxe [Mermaid](https://mermaid.js.org/). Para visualizá-los, basta abrir
o arquivo no GitHub ou em qualquer visualizador de Markdown compatível com
Mermaid (por exemplo, a extensão *Markdown Preview* do VS Code).

---

## 1. Menu principal

O menu é exibido em laço (`do-while`) e só encerra quando o usuário escolhe
a opção `0`. Opções inválidas mostram mensagem de erro e o menu aparece
novamente.

```mermaid
flowchart TD
    A([Início]) --> B[Exibir menu de opções]
    B --> C[Ler opção do usuário]
    C --> D{Opção escolhida}
    D -->|1| E[Cadastrar Colaborador Padrão]
    D -->|2| F[Cadastrar Colaborador Comissionado]
    D -->|3| G[Cadastrar Colaborador Produção]
    D -->|4| H[Gerar Folha de Pagamento]
    D -->|5| I[Exibir Resumo da Folha]
    D -->|0| J[Exibir mensagem de encerramento]
    D -->|Outra| K[Exibir mensagem de erro]
    E --> B
    F --> B
    G --> B
    H --> B
    I --> B
    K --> B
    J --> FIM([Fim])
```

---

## 2. Cadastro de colaboradores

Os três cadastros seguem o mesmo fluxo: leem a matrícula (verificando se ela
é válida e não está duplicada), leem o nome (que não pode ser vazio) e, nos
tipos comissionado e de produção, leem também os valores adicionais. No final,
o objeto é criado com `new` e adicionado ao `ArrayList<Colaborador>`.

```mermaid
flowchart TD
    A([Início do cadastro]) --> B[Ler matrícula]
    B --> C{Matrícula positiva
    e não duplicada?}
    C -->|Não| D[Exibir erro] --> B
    C -->|Sim| E[Ler nome completo]
    E --> F{Nome vazio?}
    F -->|Sim| G[Exibir erro] --> E
    F -->|Não| H{Tipo de colaborador}
    H -->|Comissionado| I[Ler valor das vendas e percentual]
    I --> J{Valores válidos?}
    J -->|Não| K[Exibir erro] --> I
    J -->|Sim| L[Criar objeto com new e
    adicionar ao ArrayList]
    H -->|Produção| M[Ler quantidade de peças e valor por peça]
    M --> N{Valores válidos?}
    N -->|Não| O[Exibir erro] --> M
    N -->|Sim| L
    H -->|Padrão| L
    L --> P[Exibir confirmação do cadastro]
    P --> FIM([Retorna ao menu])
```

---

## 3. Geração da folha de pagamento

A lista é percorrida com um laço de repetição. Para cada objeto, o programa
chama `exibirDados()` e `calcularSalarioFinal()`, que são executados de forma
diferente para cada tipo de colaborador graças ao **polimorfismo** (sem
precisar usar `instanceof`).

```mermaid
flowchart TD
    A([Início]) --> B{Lista de colaboradores
    está vazia?}
    B -->|Sim| C[Exibir aviso:
    folha vazia] --> FIM([Fim])
    B -->|Não| D[Percorrer a lista com laço for]
    D --> E[Chamar exibirDados e
    calcularSalarioFinal do objeto]
    E --> F{Existe próximo
    colaborador?}
    F -->|Sim| E
    F -->|Não| FIM
```

---

## 4. Cálculo do resumo salarial

O resumo reutiliza métodos auxiliares (`calcularTotalFolha` e
`calcularMediaSalarial`) para não repetir código. A média é calculada apenas
se houver colaboradores, evitando divisão por zero.

```mermaid
flowchart TD
    A([Início]) --> B[Contar total de colaboradores:
    lista.size]
    B --> C[Percorrer a lista somando
    os salários finais]
    C --> D[totalFolha = soma dos salários]
    D --> E{Total de colaboradores
    é zero?}
    E -->|Sim| F[médiaSalarial = 0]
    E -->|Não| G[médiaSalarial = totalFolha / total]
    F --> H[Exibir resumo da folha]
    G --> H
    H --> FIM([Fim])
```
