# UC3 - Registar Categoria da Tarefa


## 1. Engenharia de Requisitos

### Formato Breve

O administrativo inicia a definição de uma nova categoria de tarefa. O sistema solicita os dados necessários (i.e. descrição, área de atividade e lista de competências técnicas requeridas e respetiva caracterização). O administrativo introduz os dados solicitados. O sistema valida e apresenta os dados ao administrativo, pedindo que os confirme. O administrativo confirma. O sistema regista os dados e informa o administrativo do sucesso da operação.

### SSD
![UC3_SSD.png](UC3_SSD.png)

### Formato Completo

#### Ator principal

Administrativo

#### Partes interessadas e seus interesses
* **Administrativo:** quer registar categorias das tarefas para mais tarde associar às tarefas.
* **T4J:** pretende que a plataforma esteja bem estruturada para facilitar a sua utilização pelas organizações.

#### Pré-condições
* Existência de pelo menos uma área de atividade. 
* Lista de competências técnicas.

#### Pós-condições
* Uma nova categoria da tarefa é registada no sistema.

### Cenário de sucesso principal (ou fluxo básico)

1.  Inicia definição de nova categoria de tarefa
2.  Mostra a lista de áreas de atividade e solicita a descrição da categoria de tarefa e a escolha de uma área de atividade
3.  Introduz a descrição e seleciona a área de atividade pretendida
4.  Mostra a lista de competências técnicas associadas à área de atividade especificada e solicita a escolha de uma delas
5.  Seleciona competência técnica solicitada
6.  Solicita indicação do seu caráter (i.e. obrigatória ou desejável)
7.  Introduz a informação pedida
8.  Apresenta os dados e solicita confirmação
9.  Confirma
10. Informa operação bem sucedida

#### Extensões (ou fluxos alternativos)

*a. O administrativo pede o cancelamento do registo da categoria de tarefa.  
> O caso de uso termina.
*b. A qualquer momento o sistema falha: Apresenta mensagem de erro e pede para repetir o processo novamente.

5a. Não existem áreas de atividade no sistema (ainda não foram introduzidas).  
>1. O sistema informa o administrativo do problema.  
>2. O sistema possibilita o registo de uma área de atividade (UC2).  
> 2a. O administrativo não regista uma área de atividade. O caso de uso termina.

7a. Não existem competências técnicas no sistema (ainda não foram introduzidas).
>	1. O sistema informa o administrativo do problema.
>	2. O sistema possibilita o registo de competências técnicas (UC4).  
> 2a. O administrativo não define competências técnicas. O UC termina.

#### Requisitos especiais
\-

#### Tecnologia e Lista de Variações Dados
\-

#### Frequência de Ocorrência
\- Sempre que se verificar a necessidade de criar uma nova categoria de tarefa. Tendencialmente não será necessário.

#### Questões em aberto

* Existem requisitos especiais para este caso de uso?


## 2. Análise OO

### Excerto do Modelo de Domínio Relevante para o UC

![UC3_MD.png](UC3_MD.png)

## 3. Design - Realização do Caso de Uso

###	Diagrama de Sequência

![UC3_SD.png](UC3_SD.png)


###	Diagrama de Classes

![UC3_DC.png](UC3_DC.png)
