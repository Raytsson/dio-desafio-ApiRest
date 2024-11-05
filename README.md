# Desafio DIO: Financial Organization Application

Este projeto visa organizar receitas e despesas, fornecendo controle financeiro e relatórios mensais. O sistema permite gerenciar categorias de gastos, acompanhar transações e obter uma visão clara da saúde financeira mensal.

## Índice
- [Instalação](#instalação)
- [Configuração](#configuração)
- [Endpoints da API](#endpoints-da-api)
- [Contribuindo](#contribuindo)
- [Licença](#licença)

## Funcionalidades
- Registro de receitas e despesas
- Classificação de transações por categoria
- Relatórios mensais com filtragem por data
- Suporte a múltiplos usuários (futuramente com autenticação)

## Diagrama de Entidades

| Entidade  | Descrição |
|-----------|-----------|
| **Usuário**    | Responsável por gerenciar suas transações e acessar os relatórios |
| **Categoria**  | Define tipos de despesas (como alimentação, transporte, etc.) |
| **Receita**    | Registra entradas financeiras, categorizadas como 'Pagamento' |
| **Despesa**    | Registra saídas financeiras, associadas a uma categoria |
| **Transação**  | Agrupa receitas e despesas, ligadas a um usuário específico |
| **Relatório**  | Gera um resumo financeiro com total de receitas, despesas e saldo |

## Instalação

### Requisitos
- Java 17
- Maven 3.6+
- PostgreSQL configurado (localmente ou via Railway)

### Configuração do Banco de Dados

Se estiver utilizando o Railway, certifique-se de copiar as configurações de conexão conforme sua instância. Exemplo de configuração para o arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://autorack.proxy.rlwy.net:35383/railway
spring.datasource.username=postgres
spring.datasource.password=swgbzUZLXcbhNTSRuSipqUGNWxvElQSO
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Passos para Clonar e Executar
Clone o repositório:

bash
Copiar código
git clone https://github.com/seu-usuario/seu-projeto-financeiro.git
cd seu-projeto-financeiro
Compile e execute:

bash
Copiar código
mvn clean install
mvn spring-boot:run
Certifique-se de que o banco de dados PostgreSQL está ativo e acessível.

Endpoints da API
Aqui estão os principais endpoints disponíveis:

Método	Endpoint	Descrição
POST	/api/receitas	Cria uma nova receita
POST	/api/despesas	Cria uma nova despesa
GET	/api/relatorios?mes={mes}	Retorna o relatório do mês especificado
GET	/api/categorias	Lista todas as categorias
GET	/api/usuarios/{id}	Retorna as informações do usuário específico
Contribuindo
Se você deseja contribuir com o projeto:

Faça um fork do repositório.
Crie uma branch para sua nova feature (git checkout -b feature/nova-feature).
Faça commit das suas alterações (git commit -m 'Adiciona nova feature').
Envie para o seu fork (git push origin feature/nova-feature).
Abra um Pull Request para revisão.
Licença
Este projeto está licenciado sob os termos da licença MIT. Para mais detalhes, veja o arquivo LICENSE.
