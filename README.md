# Desafio Cadastro de Pets

![Java](https://img.shields.io/badge/Java-17-blue)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow)
![Contributions](https://img.shields.io/badge/Contribuições-Bem%20vindas-orange)

Aplicacao Java em linha de comando para cadastro e gerenciamento de pets resgatados. O projeto foi desenvolvido como solucao para um desafio de programacao com foco em orientacao a objetos, tratamento de excecoes e manipulacao de arquivos.

## Objetivo

Permitir que um abrigo ou responsavel registre pets encontrados e gerencie esses dados pelo terminal.

O sistema permite:

- cadastrar um novo pet
- alterar dados de um pet ja cadastrado
- deletar um pet
- listar todos os pets
- buscar pets por criterios como tipo, nome, sexo, endereco, idade, peso e raca

## Tecnologias e conceitos

- Java
- Programacao orientada a objetos
- `enum`
- Excecoes customizadas
- Leitura e escrita de arquivos `.txt`
- Persistencia local em disco

## Estrutura do projeto

```text
desafioCadastro/
|- fomulario.txt
|- petsCadastrados/
|- src/
|  |- src/
|  |  |- br/com/desafio/app/
|  |  |- br/com/desafio/domain/
|  |  |- br/com/desafio/service/
|  |  |- br/com/desafio/Exception/
|- README_DESAFIO.md
```

### Principais classes

- `App`: fluxo principal da aplicacao e menu CLI
- `MenuInicial`: impressao das opcoes iniciais
- `Pet`: entidade com os dados do animal
- `PetManager`: regras de negocio, validacoes e buscas
- `PetRepositorio`: persistencia dos pets em arquivos `.txt`

## Como funciona

### Cadastro

Ao cadastrar um pet, o sistema:

1. le o formulario base do arquivo `fomulario.txt`
2. coleta os dados no terminal
3. valida nome, idade e peso
4. cria um objeto `Pet`
5. salva o pet em um arquivo dentro da pasta `petsCadastrados`

### Persistencia

Cada pet e salvo em um arquivo `.txt` individual.

O nome do arquivo segue a ideia:

```text
yyyyMMdd'T'HHmm + NOMESEMESPACOS + .txt
```

Exemplo:

```text
20260330T1450REXDASILVA.txt
```

O conteudo do arquivo contem os dados do pet, incluindo a data de cadastro.

### Busca

A busca sempre exige o tipo do animal primeiro:

- gato
- cachorro

Depois disso, o usuario pode informar um segundo criterio opcional, como:

- nome
- sexo
- endereco
- idade
- peso
- raca

## Regras implementadas

- nome deve conter nome e sobrenome
- nome nao aceita caracteres especiais
- tipo e sexo usam `enum`
- idade aceita anos ou conversao de meses
- idade deve ser maior que `0` e menor ou igual a `20`
- peso deve ficar entre `0.5kg` e `60kg`
- campos vazios podem ser preenchidos com `NAO INFORMADO`
- busca por nome e endereco ignora diferenca entre maiusculas, minusculas e acentos

## Como executar

### Opcao 1: IntelliJ IDEA

1. abra a pasta do projeto
2. localize a classe `App`
3. execute o metodo `main`

### Opcao 2: terminal

Se for executar pelo terminal, compile os arquivos da pasta `src\src`.

Exemplo no Windows:

```powershell
javac -d out src\src\br\com\desafio\app\*.java src\src\br\com\desafio\domain\*.java src\src\br\com\desafio\service\*.java src\src\br\com\desafio\Exception\*.java
java -cp out br.com.desafio.app.App
```

## Dados salvos

Os arquivos dos pets ficam em:

```text
petsCadastrados/
```

O formulario lido no cadastro fica em:

```text
fomulario.txt
```
