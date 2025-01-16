# Sistema de Cadastro de Corridas 🚗🚖

## Descrição
Este sistema realiza o cadastro de corridas em uma central, funcionando de maneira semelhante a um sistema de táxi. Foi desenvolvido como parte do meu Curso Técnico no Senai, sendo o meu último trabalho. Cada aluno recebeu um tema sorteado e teve que desenvolver sua aplicação do zero, apresentando-a para uma banca de avaliadores.

Linguagens e tecnologias utilizadas:

* Java
* HTML
* CSS
* JavaScript
* JPA/Hibernate
* Thymeleaf
* Spring Boot MVC
* MySQL
* Draw.IO
* iTextPDF

<hr>

## Fotos do Sistema em Funcionamento

### 1. Menu Inicial
![image](https://github.com/user-attachments/assets/5eaa15dd-a936-44fe-a6bb-9086afe79832)
<br>
Este é o menu inicial do sistema, navegável através da aba à esquerda.

<hr>

### 2. Tela Principal - Cadastro de Corridas
![image](https://github.com/user-attachments/assets/4300febf-e450-458c-b612-bfdb1a3d7efc)
<br>
Aqui é onde as corridas são cadastradas.

<hr>

### 3. Exemplo de Cadastro de Corrida
![image](https://github.com/user-attachments/assets/1ddf211d-b9ad-4827-8082-02e7a49730f2)
<br>
Tela de exemplo mostrando o processo de cadastro de uma corrida.

<hr>

### 4. Tela de Listagem de Corridas
![image](https://github.com/user-attachments/assets/a566dc4b-6d7f-4e30-a239-8c0d70f55ad2)
<br>
Depois que uma corrida é cadastrada, ela aparece nesta tela, com opções de deletar e editar.

<hr>

### 5. Busca de Corridas por Motorista
![image](https://github.com/user-attachments/assets/e5023459-7ed0-4448-b6a8-19fb3a110d09)
<br>
Esta tela permite realizar buscas para visualizar as corridas específicas de cada motorista.

<hr>

### 6. Total Arrecadado por Motorista
![image](https://github.com/user-attachments/assets/836b6a98-1fdd-4def-8e73-f5de6a8a23bd)
<br>
Ao pesquisar um motorista, é possível visualizar o total arrecadado com as corridas realizadas.

<hr>

### 7. Geração de PDF
![image](https://github.com/user-attachments/assets/dcbf2925-a15d-4dc4-8a58-03a0cbfec5c6)
<br>
O sistema conta com uma função que permite gerar um PDF com a lista de corridas cadastradas, disponível para download.

<hr>

### 8. Cadastro de Aplicativos
![image](https://github.com/user-attachments/assets/bad1a12a-7e71-4233-9be9-e877fb31eb7d)
<br>
Nesta tela, é possível cadastrar os aplicativos utilizados para realizar as corridas.

<hr>

### 9. Listagem de Aplicativos
![image](https://github.com/user-attachments/assets/763a2630-79d0-4c46-aec7-f17d94979d22)
<br>
Aqui são listados todos os aplicativos cadastrados no sistema.

<hr>

### 10. Cadastro de Motoristas
![image](https://github.com/user-attachments/assets/bfad6737-a67f-4d8c-8a89-cbf5c25beee8)
<br>
Nesta tela, são cadastrados os motoristas que utilizam o sistema. Cada corrida precisa de um motorista para ser registrada.

<hr>

### 11. Listagem de Motoristas
![image](https://github.com/user-attachments/assets/002b1bb4-87fb-47b9-afb9-ddd2d5e9f0be)
<br>
Esta tela exibe os motoristas cadastrados no sistema.

<hr>

### 12. Confirmação de Deleção
![image](https://github.com/user-attachments/assets/a2b671a9-f9e3-4c4f-a261-9ac56ed1d58f)
<br>
Ao clicar no botão de deletar, um alerta de confirmação é exibido para garantir a ação.

<hr>

### 13. Deleção de Motoristas com Corridas Associadas
![image](https://github.com/user-attachments/assets/764239cf-a91f-4f96-80a7-6fab92678bcc)
<br>
Este alerta obedece à regra de banco de dados: não é possível excluir um motorista que tenha corridas associadas. Para excluir, é necessário primeiro excluir as corridas associadas, uma regra que também se aplica aos aplicativos.

<hr>

### 14. Validação de Idade para Motoristas
![image](https://github.com/user-attachments/assets/5f8aaec6-89f4-407e-bd7a-c6986f47327f)
<br>
O sistema verifica os dados inseridos, como no caso da idade do motorista, que não pode ser inferior a 18 anos.

<hr>

### 15. Validação do Número da Placa
![image](https://github.com/user-attachments/assets/9c6296dd-7030-47b9-9064-fff4471cd967)
<br>
O sistema também valida campos como o número da placa, que precisa seguir um padrão específico.

<hr>

### 16. Script de Banco de Dados
![image](https://github.com/user-attachments/assets/2a7974fc-e89c-42a4-8347-c919f55d4fbc)
<br>
Para quem deseja testar o sistema, existe um script do banco de dados na pasta "static", que contém as instruções para criação das tabelas.
<hr>
