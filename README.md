# ES-2023-2Sem-Grupo-B

Inês Ferreira Ribeiro - 98283 - nokinhas2002

João Diogo Figueirinha Rodrigues  - 99793 - Joao-Diogo-Figueirinha-Rodrigues

Jorge Miguel Melo Poço - 96130 - jmmpo3

Lourenço Soares Veríssimo - 98784 - lourencosoaress

Paulo Manuel Fernandes Pires - 98132 - pmfpiscte

Vasco Alexandre dos Santos Dionísio Domingos - 98493 - vasdd

# Calendar App

Esta aplicação Java serve como backend para a aplicação calendário da cadeira de Engenharia de Software

# Getting Started
## Compilar e correr a aplicação

### Como correr
1. Instalar maven (one time only)
> [Instalar Maven](https://maven.apache.org/install.html)
2. Instalar docker (one time only)
> [Instalar Docker](https://docs.docker.com/desktop/install/windows-install/)
3. Arrancar a aplicação (JaCoCo report gerado para target/site/jacoco/index.html):
> `mvn clean install && docker-compose -f docker/docker-compose.yml up --build`
4. Para correr apenas os testes e gerar o report JaCoCo usar:
> `mvn test jacoco:report`

### Documentação de referência
Para mais informação sobre a stack utilizada neste projecto por favor consultar o documento de markdown HELP.md
