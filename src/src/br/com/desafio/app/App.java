package br.com.desafio.app;

import br.com.desafio.Exception.*;
import br.com.desafio.domain.Pet;
import br.com.desafio.domain.SexoPet;
import br.com.desafio.domain.TipoPet;
import br.com.desafio.service.PetManager;
import br.com.desafio.service.PetRepositorio;

import java.io.*;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        PetRepositorio repositorio = new PetRepositorio();
        PetManager pm = new PetManager();
        Scanner sc = new Scanner(System.in);
        MenuInicial menu = new MenuInicial();

        repositorio.carregarPets();
        List<Pet> petsSalvos = repositorio.carregarPets();
        pm.carregarPets(petsSalvos);

        int opcao = 0;

        do {
            menu.imprimirMenu();
            try {
                opcao = sc.nextInt();
                if (opcao <= 0) {
                    continue;
                }
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Digite apenas números.");
            }
            switch (opcao) {
                case 1:
                    try (FileReader fr = new FileReader("C:\\projetos\\desafioCadastro\\fomulario.txt");
                         BufferedReader br = new BufferedReader(fr)) {
                        String linha;
                        while ((linha = br.readLine()) != null) System.out.println(linha);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    System.out.print("\n1.Digite o nome e sobrenome do pet: ");
                    Pet pet = new Pet();
                    sc.nextLine();
                    String nome = sc.nextLine();
                    if (nome.isEmpty()) {
                        pet.setNome(pet.getNAO_INFORMADO());
                        System.out.println("Nome: " + pet.getNome());
                    } else {
                        pet.setNome(nome);
                        System.out.println("Nome: " + pet.getNome());
                        try {
                            pm.validarNome(pet.getNome());
                        } catch (NomeInvalidoException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                    }

                    System.out.print("\n2.Digite o tipo do pet (Gato 1/Cachorro 2): ");
                    try {
                        pet.setTipo(TipoPet.tipoPet(sc.nextInt()));
                        sc.nextLine();
                        System.out.println("Tipo: " + pet.getTipo());
                    } catch (OpcaoInvalidaException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    System.out.print("\n3.Digite o sexo do pet (M / F): ");
                    try {
                        pet.setSexo(SexoPet.sexoPet(sc.next().charAt(0)));
                        System.out.println("Sexo: " + pet.getSexo());
                    } catch (OpcaoInvalidaException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    System.out.println("\n4.Digite o endereço do pet");
                    System.out.print("Número da casa: ");
                    sc.nextLine();
                    String numeroDaCasa = sc.nextLine();
                    if (numeroDaCasa.isEmpty()) {
                        numeroDaCasa = pet.getNAO_INFORMADO();
                    }
                    System.out.print("Cidade: ");
                    String cidade = sc.nextLine();
                    if (cidade.isEmpty()) {
                        cidade = pet.getNAO_INFORMADO();
                    }
                    System.out.print("Rua: ");
                    String rua = sc.nextLine();
                    if (rua.isEmpty()) {
                        rua = pet.getNAO_INFORMADO();
                    }
                    pet.setEnderecoBairro(numeroDaCasa + ", " + cidade + ", " + rua);
                    System.out.println("Endereço: " + pet.getEnderecoBairro());

                    System.out.print("\n5.Digite a idade aproximada do pet: ");
                    String idade = sc.nextLine();
                    if (idade.isEmpty()) {
                        pet.setIdade(pet.getNAO_INFORMADO());
                        System.out.println("Idade: " + pet.getIdade());
                    } else {
                        System.out.print("A idade é em anos (1) ou meses (2)? ");
                        int opcaoIdade = sc.nextInt();
                        sc.nextLine();
                        switch (opcaoIdade) {
                            case 1:
                                if (!idade.matches("\\d+([.,]\\d+)?")) {
                                    System.out.println("Idade inválida, digite uma idade maior que 0 e menor que 20.");
                                    continue;
                                } else {
                                    pet.setIdade(idade);
                                    System.out.println("Idade aproximada: " + pet.getIdade() + " anos");
                                }
                                break;
                            case 2:
                                if (!idade.matches("\\d+([.,]\\d+)?")) {
                                    System.out.println("Idade inválida, digite uma idade maior que 0 e menor que 20.");
                                    continue;
                                } else {
                                    double idadeMeses = Double.parseDouble(idade) / 10;
                                    idade = String.valueOf(idadeMeses);
                                    pet.setIdade(idade);
                                    System.out.println("Idade aproximada: " + pet.getIdade() + " anos");
                                }
                        }
                    }

                    System.out.print("\n6.Digite o peso aproximado do pet: ");
                    String peso = sc.nextLine();
                    if (peso.isEmpty()) {
                        pet.setPeso(pet.getNAO_INFORMADO());
                        System.out.println("Idade: " + pet.getIdade());
                    } else if (!peso.matches("\\d+([.,]\\d+)?")) {
                        System.out.println("Peso inválido, digite um peso maior que 0.4kg e menor que 60kg.");
                        continue;
                    } else {
                        pet.setPeso(peso);
                        System.out.println("Peso aproximado: " + pet.getPeso() + " kg");
                    }

                    System.out.print("\n7.Digite a raça do pet: ");
                    String racaStr = sc.nextLine();
                    if (racaStr.isEmpty()) {
                        pet.setRaca(pet.getNAO_INFORMADO());
                        System.out.println("Raça: " + pet.getRaca());
                    } else if (!racaStr.matches("[a-zA-Zá-úÁ-Ú ]+")) {
                        System.out.println("Raça inválida, não utilize números ou caracteres especiais.");
                        continue;
                    } else {
                        pet.setRaca(racaStr);
                        System.out.println("Raça: " + pet.getRaca());
                    }

                    try {
                        pm.cadastrarPet(pet);
                        repositorio.salvarPet(pet);
                    } catch (IdadeInvalidaException | PesoInvalidoException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Para buscar por um pet, digite o tipo (Gato 1/Cachorro 2): ");
                    TipoPet tipo2 = null;
                    try {
                        tipo2 = TipoPet.tipoPet(sc.nextInt());
                    } catch (OpcaoInvalidaException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("1 - Nome ou sobrenome");
                    System.out.println("2 - Sexo");
                    System.out.println("3 - Endereço");
                    System.out.println("4 - Idade");
                    System.out.println("5 - Peso");
                    System.out.println("6 - Raça");
                    System.out.println("0 - Nenhuma das opções");
                    System.out.println("Gostaria de adicionar um segundo critério?");
                    System.out.print("Se sim digite a opção: ");
                    int opcaoDeBusca = sc.nextInt();
                    sc.nextLine();

                    String nome2 = null;
                    SexoPet sexo2 = null;
                    String endereco2 = null;
                    String idade2 = null;
                    String peso2 = null;
                    String raca2 = null;

                    switch (opcaoDeBusca) {
                        case 1:
                            System.out.print("Digite o nome: ");
                            nome2 = sc.nextLine();
                            break;
                        case 2:
                            System.out.print("Digite o sexo (M / F): ");
                            try {
                                sexo2 = SexoPet.sexoPet(sc.next().charAt(0));
                            } catch (OpcaoInvalidaException e) {
                                System.out.println(e.getMessage());
                            }
                            break;
                        case 3:
                            System.out.print("Digite o endereço: ");
                            endereco2 = sc.nextLine();
                            break;
                        case 4:
                            System.out.print("Digite a idade: ");
                            idade2 = sc.nextLine();
                            break;
                        case 5:
                            System.out.print("Digite o peso: ");
                            peso2 = sc.nextLine();
                            break;
                        case 6:
                            System.out.print("Digite a raça: ");
                            raca2 = sc.nextLine();
                            break;
                    }
                    List<Pet> listaDeBusca = pm.buscarPet(nome2, tipo2, sexo2, endereco2, idade2, peso2, raca2);
                    boolean buscouPorNome = nome2 != null;
                    boolean buscouPorSexo = sexo2 != null;
                    boolean buscouPorEndereco = endereco2 != null;
                    boolean buscouPorIdade = idade2 != null;
                    boolean buscouPorPeso = peso2 != null;
                    boolean buscouPorRaca = raca2 != null;
                    for (int i = 0; i < listaDeBusca.size(); i++) {
                        int soma = 1 + i;
                        String nomeFormatado = buscouPorNome ?
                                "\033[1m" + listaDeBusca.get(i).getNome() + "\033[0m" : listaDeBusca.get(i).getNome();
                        String sexoFormatado = buscouPorSexo ?
                                "\033[1m" + listaDeBusca.get(i).getSexo() + "\033[0m" : String.valueOf(listaDeBusca.get(i).getSexo());
                        String enderecoFormatado = buscouPorEndereco ?
                                "\033[1m" + listaDeBusca.get(i).getEnderecoBairro() + "\033[0m" : listaDeBusca.get(i).getEnderecoBairro();
                        String idadeFormatado = buscouPorIdade ?
                                "\033[1m" + listaDeBusca.get(i).getIdade() + "\033[0m" : listaDeBusca.get(i).getIdade();
                        String pesoFormatado = buscouPorPeso ?
                                "\033[1m" + listaDeBusca.get(i).getPeso() + "\033[0m" : listaDeBusca.get(i).getPeso();
                        String racaFormatado = buscouPorRaca ?
                                "\033[1m" + listaDeBusca.get(i).getRaca() + "\033[0m" : listaDeBusca.get(i).getRaca();


                        System.out.println(soma + ". "
                                + nomeFormatado + " - "
                                + "\033[1m" + listaDeBusca.get(i).getTipo() + "\033[0m" + " - "
                                + sexoFormatado + " - "
                                + enderecoFormatado + " - "
                                + idadeFormatado + " anos" + " - "
                                + pesoFormatado + "kg" + " - "
                                + racaFormatado);
                    }

                    System.out.print("\nDigite o número do pet que você deseja alterar um atributo: ");
                    int numeroPet = sc.nextInt();
                    sc.nextLine();
                    try {
                        Pet petSelecionado = listaDeBusca.get(numeroPet - 1);
                        System.out.println("1. Alterar o nome");
                        System.out.println("2. Alterar o endereço");
                        System.out.println("3. Alterar a idade");
                        System.out.println("4. Alterar o peso");
                        System.out.println("5. Alterar a raça");
                        System.out.print("Digite o atributo que deseja alterar: ");
                        int opcaoAtributo = sc.nextInt();
                        sc.nextLine();
                        switch (opcaoAtributo) {
                            case 1:
                                System.out.print("Digite o novo nome: ");
                                petSelecionado.setNome(sc.nextLine());
                                System.out.println("Nome alterado com sucesso!");
                                break;
                            case 2:
                                System.out.print("Digite o novo Endereço: ");
                                petSelecionado.setEnderecoBairro(sc.nextLine());
                                System.out.println("Endereço alterado com sucesso!");
                                break;
                            case 3:
                                System.out.print("Digite a nova idade: ");
                                petSelecionado.setIdade(sc.nextLine());
                                System.out.println("Idade alterada com sucesso!");
                                break;
                            case 4:
                                System.out.print("Digite o novo peso: ");
                                petSelecionado.setPeso(sc.nextLine());
                                System.out.println("Peso alterado com sucesso!");
                                break;
                            case 5:
                                System.out.print("Digite a nova raça: ");
                                petSelecionado.setRaca(sc.nextLine());
                                System.out.println("Raça alterada com sucesso!");
                                break;
                        } repositorio.atualizarPet(petSelecionado);
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println("Número inválido, escolha um número da lista!");
                        continue;
                    }
                    break;
                case 3:

                    break;
            }
        } while (opcao != 6);


    }
}
