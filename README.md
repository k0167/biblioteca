## Projeto Biblioteca em JAVA

Bom dia meu nobre, Esse projeto representa uma tentativa de utilização do JDBC especificamente para o Postgres de forma a aprender como criar conexões DAOs Models e tudo mais no padrão MVC do JAVA

## Estrutura de arquivos

O projeot possui dois principais diretorios

- `src`: onde está todo o source do projeto
- `lib`: onde está as depências (embora não possua nenhuma kkkkkk)

De qualquer forma, siga as instruções abaixo para executar:

## Configurando Execução
1 - Primeiramente você vai precisar possuir o JDBC do Postgres e o JavaFX, então dê um jeito de baixar eles :)

Utilizado nesse projeto foi o JDBC `postgresql-42.3.4.jar`
e o JavaFX foi o `javafx-sdk-18.0.1`

Ambos foram baixados no site oficial então é só dar uma googleada aí

2 - Tenha certeza que você importou as dependências, em sua pasta .vscode verifique as seguintes configurações:

seu arquivo launch.json deve estar assim:

```
{
    "version": "0.2.0",
    "configurations": [
        {
            "type": "java",
            "name": "Launch App",
            "request": "launch",
            "mainClass": "App",
            "vmArgs": "--module-path D:/Java_projects/javafx-sdk-18.0.1/lib --add-modules javafx.controls,javafx.fxml",
        }
    ]
}
```
mude o caminho  para onde seu JavaFX estã salvo "vmArgs": "--module-path `D:/Java_projects/javafx-sdk-18.0.1/lib` --add-modules javafx.controls,javafx.

Seu arquivo settings.json precisa estar assim

```
{
    "java.project.sourcePaths": ["src"],
    "java.project.outputPath": "bin",
    "java.project.referencedLibraries": [
        "lib/**/*.jar",
        "D:/Java_projects/javafx-sdk-18.0.1/lib/**/*.jar",
        "D:/Java_projects/postgresql-42.3.4.jar"
    ]
}
```
Mude os caminhos `"D:/Java_projects/javafx-sdk-18.0.1/lib/**/*.jar"`,
                 `"D:/Java_projects/postgresql-42.3.4.jar"`
                 para onde suas dependências estão salvas

3 - Bom agora tu precisa configurar o banco. 
    Todo o banco de dados foi feito por meio do PgMyAdmin, então eu recomento que seja feito pelo mesmo lugar.
    Basta abrir o banco de dados e executar o arquivo `banco.sql` (voce pode so copiar e colar ele dentro do query editor e rodar)

4 Pronto, feito isso na teoria seu programa deve rodar :)
Só de precaução irei deixar o zip do arquivo junto do repositorio, para caso queira ter certeza de todas configurações.


Como ustilizar o programa:

Não tem muito segredo os botôes são bem intuitivos onde

Inserir -> insere novo registro

Deleter -> Deleta registro selecionado ( o ultimo clicado na tabela)

Procurar -> Busca por um registro e popula a tabela com o resultado

Alterar -> Altera o registro atual

Para realizar uma alteração primeiro deve importar um registro para a area de edição, para fazer isso, basta selecionar ele na tabela e apertar ENTER
