## MHIFES

Esse projeto consiste em uma aplicação desenvolvida para o Ifes Campus Colatina envolvendo o gerenciamento de turmas, alunos, professores, coordenadores, aulas, disciplinas, equipamentos de sala, eventos, horários dee aula, coordenadorias, matrizes-curriculares e alocações utilizando Spring Boot para o backend, Angular para o frontend, PostgreSQL para o banco de dados e MYSQL para a importação de migrations e gerenciamento de relatórios.
A Aplicação também possui suporte a leitores de RFID, código de barras, relatórios via Jasper Report, sistemas de Log e login de diferentes tipos de usuários utilizando autenticação JWT.

Esse trabalho foi desenvolvido em seis meses para a disciplina de Laboratório de Engenharia de Software, obtendo como pontuação final 76 pontos de 100.

##Como rodar o projeto

Clone esse repositório: git clone https://github.com/voidGustavoNunes/MHIFES---Backend

Após clonar, entre na pasta /src e instale as dependências: mvn clean install

##Importante
Certifique-se que esteja com o PostGreSQL e MySQL instalados e seus respectivos bancos configurados de acordo com o arquivo aplication.proprieties que esta na pasta raiz.

O projeto possui um frontend: https://github.com/voidGustavoNunes/MHIFES-Frontend
