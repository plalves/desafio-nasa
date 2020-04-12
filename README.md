**Qual o objetivo do comando cache em spark ?**

O objetivo do comando cache é armazenar o resultado de um processamento em memória.
 
A utilização deste comando evita que toda vez que o resultado do processamento é invocado o processamento seja realizado novamente.

A utilização do cache é de grande importância para processamentos pesados, pois a cada utilização da variável a aplicação spark faria novamente o calculo causando um grande decréscimo da performance da aplicação

**O mesmo código implementado em spark é normalmente mais rápido que a implementação equivalente em MapReduce. Por que ?**

A diferença de performance pode ser devido aos paradigmas "Lazy evaluation" e o processamento "in-memory" disponíveis no spark. 

O "Lazy evoluation" permite que o spark agrege operações semelhantes do mesmo dado juntas sem a necessidade do processamento do dado diversas vezes, isto faz processamentos grandes e que utilizem o mesmo dado mais de uma vez tenha uma grande eficiente frente ao MapReduce
Este paradigma permite que processo que é utilizado mais de uma vez no código seja executado apenas uma vez, e o resultado de um processamento pode ainda ser armazenado em memória.

**Qual a função do sparkContext ?**

Permitir a conexão entre o Driver Program e spark cluster. O sparkContext 


**Explique com suas palavras o que é Resilient Distributed Datasets(RDD).**

É o dado distribuído em diferentes partições para permitir do processamento paralelo do dado em diferentes nós do cluster. 


**GroupByKey é menos eficiente que reduceByKey em grandes dataset. Por que ?**

Pelo tráfego de dados entre os nós pela rede. 
Com o GroupByKey todo o dado é trafegado pela rede, reduceByKey trafega apenas o resultado da operação.
GroupByKey irá transferir todo o conjunto de dados(Dataset) atráves da rede para os outros workers para que ele realize a operação. 
Enquanto o reduceByKey irá realizar a operação em cada partição e após realizar o calculo transmitirá o resultado da operação para os outros workers. 

**Explique o que o código scala abaixo faz**

O código abaixo abre um determinado arquivo de texto informado, contabiliza quantas vezes cada palavra é referenciada no texto e salva o resultado em um arquivo de texto

**Instruções de execução:**
- Colocar os dois arquivos em um único diretório
- Informar como parâmetro o diretório do arquivo
