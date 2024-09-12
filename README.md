# T3-Construcao-Compiladores

## T3 - Analisador Semântico ##
Neste repositório, encontra-se o terceiro trabalho realizado na disciplina de Construção de Compiladores ofertada no 1º semestre de 2024 ministrada pelo Prof. Dr. Daniel Lucrédio.
O objetivo do mesmo é desenvolver um analisador semântico. Abaixo, encontra-se as instruções do que é necessário ter instalado na máquina pessoal para executar o analisador, assim como, compilar e executá-lo. Ademais, conterá a explicação da gramática criada mediante regras para atender aos casos de teste fornecidos pelo professor (foi reaproveitada a gramática do analisador léxico e a gramática do analisador semântico, respectivamente, do T1 e T2).
Espera-se que este analisador semântico funcione nos sistemas operacionais: Linux, Windows e macOS.

## Integrantes ##
Este trabalho foi desenvolvido pelas alunas:
- Lara Santiago Rodrigues, RA 769701 - Turma A
- Letícia Bossatto Marchezi, RA 791003 - Turma A

## Requisitos ##
Para a execução do analisador, é necessário ter instalado Java (aceitável qualquer versão a partir da *8*), Maven (*versão 3.6.3*) e ANTLR (*versão 4.13.1*).

- A dupla usou, especificadamente, a versão mais atual do Java, que no atual momento do trabalho é a *19.0.2*.

Verifique se o Java e Maven estão definidos nas variáveis de ambiente de seu sistema, dessa forma, será possível invocar os comandos em qualquer diretório.

## Instruções de uso ##
O trabalho foi desenvolvido usando a IDE Visual Studio Code e a execução ocorreu em dois sistemas operacionais diferentes: Linux (Ubuntu) e Windows 10.

Para facilitar o *download* do trabalho, acesse a pasta: ![T3](https://github.com/letMarchezi/T3-Construcao-Compiladores/tree/main/alguma-grammar).

Após realizar o *download*, o projeto pode ser aberto no VS (visual studio). Veja abaixo como fazer:
![](https://github.com/letMarchezi/T1/assets/110498717/c3dd0f2a-c792-4519-96a6-775352a1e38e)


* Avisos: coloque a pasta baixada em um diretório curto e sem espaços em branco para evitar erros.

## Compilação ##

Para compilar o projeto é necessário executar o segundo comando no diretório **alguma-grammar**:

	mvn clean package

Assim, será gerado o arquivo do analisador semântico empacotado com as dependências dentro da pasta _alguma\_grammar/target_. Este arquivo será utilizado nos passos futuros para a execução.

## Como executar? ##

### Execução manual ###
Para executar é necessário localizar o analisador semântico, que foi empacotado no arquivo **compiladores-corretor-automatico-1.0-SNAPSHOT-jar-with-dependencies.jar** na pasta _alguma\_grammar/target_. 

O analisador semântico recebe dois parâmetros, sendo o primeiro o caminho do arquivo de entrada que será analisado, e o segundo o caminho do arquivo de saída. Ambos os caminhos dos arquivos devem ser completos (*absolute path*). 

Após localizar o analisador semântico, a execução poderá ser realizada da seguinte forma (independente do sistema operacional).

* Abra um _prompt_ de comando e digite o seguinte comando:

		java -jar (caminho do analisador .jar) (caminho do arquivo de teste) (caminho do arquivo de saída)

Onde:
- O caminho do analisador .jar é a localização do arquivo compiladores-corretor-automatico-1.0-SNAPSHOT-jar-with-dependencies.jar;
- O caminho do arquivo de teste é o caminho absoluto do arquivo .txt que contém um algoritmo que será analisado pelo programa;
- O caminho do arquivo de saída é o caminho absoluto do arquivo .txt onde será armazenado o resultado da análise. Caso o diretório final ou o arquivo não existam, eles serão criados.

Um exemplo de execução é a seguinte:
- Linux

  		java -jar /home/lara/Documentos/T3-Construcao-Compiladores/alguma-grammar/target/alguma-grammar-1.0-SNAPSHOT-jar-with-dependencies.jar /home/lara/Documentos/T3-Construcao-Compiladores/alguma-grammar/casos-de-teste/3.casos_teste_t3/entrada/4.algoritmo_3-2_apostila_LA.txt /home/lara/Documentos/T3-Construcao-Compiladores/alguma-grammar/resultado

- Windows

		java -jar D:\Documentos\UFSCar\Compiladores-2024.1\T3-Construcao-Compiladores\alguma-grammar\target\alguma-grammar-1.0-SNAPSHOT-jar-with-dependencies.jar D:\Documentos\UFSCar\Compiladores-2024.1\casos-de-teste/3.casos_teste_t3/entrada/4.algoritmo_3-2_apostila_LA.txt D:\Documentos\UFSCar\Compiladores-2024.1\T3-Construcao-Compiladores\saida.txt


### Correção automática ###

A análise semântica também pode ser realizada por meio do ![corretor automatico](https://github.com/dlucredio/compiladores-corretor-automatico/tree/master), executando o analisador em lotes em conjunto com a correção dos resultados, comparando a saída gerada com o resultado esperado. 

Há 9 casos a serem testados para este trabalho. Acesse a pasta completa em: ![casos-de-teste](https://github.com/letMarchezi/T3-Construcao-Compiladores/tree/main/alguma-grammar/casos-de-teste/3.casos_teste_t3). 

A execução do corretor automático é feita com o seguinte comando:


		java -jar (caminho do corretor) "java -jar (caminho analisador .jar)" (caminho gcc) (diretorio de saída) (diretorio dos casos de teste) (RA dos alunos) (nome do trabalho)


Onde:
- O caminho corretor é a localização do arquivo compiladores-corretor-automatico-1.0-SNAPSHOT-jar-with-dependencies.jar;
- O caminho do analisador .jar é a localização do arquivo compiladores-corretor-automatico-1.0-SNAPSHOT-jar-with-dependencies.jar;
- O caminho gcc é a localização do executável do compilador gcc em seu sistema. Caso o comando *gcc* seja reconhecido, este poderá ser utilizado;
- O diretorio de saída é o caminho absoluto para armazenamento dos resultados do analisador;
- O diretorio dos casos de teste é o caminho absoluto da pasta **casos-de-teste**;
- RA dos alunos é uma string com o RA dos membros separados por vírgula
- Nome do trabalho é uma das opções entre t1, t2, t3, t4 ou t5. 

Exemplos:

- Linux

		java -jar /home/lara/Documentos/T3-Construcao-Compiladores/alguma-grammar/compiladores-corretor-automatico-1.0-SNAPSHOT-jar-with-dependencies.jar "java -jar /home/lara/Documentos/T3-Construcao-Compiladores/alguma-grammar/target/alguma-grammar-1.0-SNAPSHOT-jar-with-dependencies.jar" gcc /home/lara/Documentos/T3-Construcao-Compiladores/alguma-grammar/temp /home/lara/Documentos/T3-Construcao-Compiladores/alguma-grammar/casos-de-teste "769701, 791003" t3

- Windows

		java -jar D:\Documentos\UFSCar\Compiladores-2024.1\T3-Construcao-Compiladores\alguma-grammar\compiladores-corretor-automatico-1.0-SNAPSHOT-jar-with-dependencies.jar "java -jar D:\Documentos\UFSCar\Compiladores-2024.1\T3-Construcao-Compiladores\alguma-grammar\target\alguma-grammar-1.0-SNAPSHOT-jar-with-dependencies.jar" gcc temp D:\Documentos\UFSCar\Compiladores-2024.1\casos-de-teste "769701, 791003" t3


## Gramática ##
A gramática utilizada no desenvolvimento do trabalho foi a especificada nos casos de teste apresentados acima e vista em sala de aula. Para conferi-lá em mais detalhes, recomenda-se a visualização do arquivo **AlgumaGrammar.g4**. Como é a mesma utilizada no T2 (referente a análise sintática), veja a documentação completa realizada em ![T2-README.md](https://github.com/letMarchezi/T2-Construcao-Compiladores/blob/main/README.md) na sessão GRAMÁTICA.

## Novos arquivos ##
Para o desenvolvimento deste trabalho, foi necessário criar novos arquivos, cada um com seu significado de implementação, dado que nos trabalhos anteriores não tinha sido necessário. De tal modo, temos:
- AlgumaSemantico.java: define o comportamento do analisador semântico, isto é, funções que são necessárias para verificar as entradas com base na linguagem criada
- AlgumaSemanticoUtils.java: define funções utilitárias que devem ser utilizadas como auxílio as funções do arquivo AlgumaSemantico.java;
- Escopos.java: define o escopo da linguagem e funções genéricas de escopo;
- Principal.java: arquivo de execução principal do trabalho no qual realiza a inicialização do programa, leitura e escrita das informações a partir de um arquivo;
- TabelaDeSimbolos.java: define a tabela de símbolos, assim como funções genéricas para a mesma;