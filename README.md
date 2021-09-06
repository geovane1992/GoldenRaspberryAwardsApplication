Projeto Golden Raspberry Awards

Linguagem utilizada:
Java + Spring Boot

Especificação do Teste 

Desenvolva uma API RESTful para possibilitar a leitura da lista de indicados e vencedores da categoria Pior Filme do Golden Raspberry Awards. 
Requisito do sistema:
 1. Ler o arquivo CSV dos filmes e inserir os dados em uma base de dados ao iniciar a aplicação. 
Requisitos da API: 
1. Obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido, seguindo a especificação de formato definida na página 2; 

Requisitos não funcionais do sistema: 

1. O web service RESTful deve ser implementado com base no nível 2 de maturidade de Richardson; 
2. Devem ser implementados somente testes de integração. Eles devem garantir que os dados obtidos estão de acordo com os dados fornecidos na proposta; 
3. O banco de dados deve estar em memória utilizando um SGBD embarcado (por exemplo, H2). Nenhuma instalação externa deve ser necessária; 
4. A aplicação deve conter um readme com instruções para rodar o projeto e os testes de integração. O código-fonte deve ser disponibilizado em um repositório git (Github, Gitlab, Bitbucket, etc).


Rodar projeto:

1º - Realizar um clone do repositório 
2º - Importar o projeto no eclipse, de preferência na versão 2021-06 (4.20.0)
3º - Verificar se a versão do Spring Tools 4 está instalada
4º - Executar projeto pelo Boot Dashboard
5º - Ao iniciar a aplicação será realizada a carga dos dados com base na planilha que está em “src/main/java/file/movielist.csv”.
6º - Os dados serão carregados no banco H2 conforme especificações: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:./data
7º - A consulta dos produtores premiados podem ser consultado por meio do endpoint http://localhost:8080/producers/intervalPrizes.

Rodar testes:

1º - Navegue até o pacote “/goldenRaspberryAwards/src/test/java/com/restful/repositor”
2º - O pacote acima contém a classe “MovieRepositoryTest”, a qual possui 4 testes de integração, a descrição de cada um está em comentário.
3º - Ao clicar em Run para a classe “MovieRepositoryTest” os testes serão executados realizando a tarefa comentada no código.

