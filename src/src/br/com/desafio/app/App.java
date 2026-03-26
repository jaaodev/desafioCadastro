package br.com.desafio.app;

import br.com.desafio.Exception.*;
import br.com.desafio.domain.Pet;
import br.com.desafio.domain.SexoPet;
import br.com.desafio.domain.TipoPet;
import br.com.desafio.service.PetManager;

import java.io.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        PetManager pm = new PetManager();
        Scanner sc = new Scanner(System.in);
        MenuInicial menu = new MenuInicial();
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

                    System.out.print("1.Digite o nome e sobrenome do pet: ");
                    Pet pet = new Pet();
                    sc.nextLine();
                    pet.setNome(sc.nextLine());
                    System.out.println("Nome: "+pet.getNome());
                    try {
                        pm.validarNome(pet.getNome());
                    } catch (NomeInvalidoException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    System.out.print("2.Digite o tipo do pet (Gato 1/Cachorro 2): ");
                    try {
                        pet.setTipo(TipoPet.tipoPet(sc.nextInt()));
                        sc.nextLine();
                        System.out.println("Tipo: "+pet.getTipo());
                    } catch (OpcaoInvalidaException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    System.out.print("3.Digite o sexo do pet (M / F): ");
                    try {
                        pet.setSexo(SexoPet.sexoPet(sc.next().charAt(0)));
                        System.out.println("Sexo: "+pet.getSexo());
                    } catch (OpcaoInvalidaException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }

                    System.out.println("4.Digite o endereço do pet");
                    System.out.print("Número da casa: ");
                    sc.nextLine();
                    String numeroDaCasa = sc.nextLine();
                    System.out.print("Cidade: ");
                    String cidade = sc.nextLine();
                    System.out.print("Rua: ");
                    String rua = sc.nextLine();
                    pet.setEnderecoBairro("Número da casa " + numeroDaCasa + ", " +
                            "Cidade " + cidade + ", " +
                            "Rua " + rua);
                    System.out.println("Endereço: "+pet.getEnderecoBairro());

                    System.out.print("5.Digite a idade aproximada do pet: ");
                    String idadeStr = sc.nextLine();
                    if (!idadeStr.matches("\\d+([.,]\\d+)?")){
                        System.out.println("Idade inválida, digite uma idade maior que 0 e menor que 20.");
                        continue;
                    } else {
                        double idade = Double.parseDouble(idadeStr);
                        pet.setIdade(idade);
                        System.out.println("Idade aproximada: " + pet.getIdade() + " anos");
                    }

                    System.out.print("6.Digite o peso aproximado do pet: ");
                    String pesoStr = sc.nextLine();
                    if (!pesoStr.matches("\\d+([.,]\\d+)?")) {
                        System.out.println("Peso inválido, digite um peso maior que 0.4kg e menor que 60kg.");
                        continue;
                    } else {
                        double peso = Double.parseDouble(pesoStr);
                        pet.setPeso(peso);
                        System.out.println("Peso aproximado: " + pet.getPeso() + " kg");
                    }

                    try {
                        pm.cadastrarPet(pet);
                    } catch (NomeInvalidoException | IdadeInvalidaException | PesoInvalidoException e) {
                        System.out.println(e.getMessage());
                    }
                    //FALTA FAZER A PERGUNTA 7 SOBRE A RAÇA DO PET
                    // QUASE TERMINEI DE FAZER A PARTE DAS PERGUNTAS SOBRE O PET
                    //AGORA TENHO QUE ADICIONAR NÃO INFORMADO NOS CAMPOS QUE O USUARIO DEIXAR EM BRANCO
            }
        } while (opcao != 6);


    }
}
