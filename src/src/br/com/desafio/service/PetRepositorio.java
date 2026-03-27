package br.com.desafio.service;

import br.com.desafio.domain.Pet;
import br.com.desafio.domain.SexoPet;
import br.com.desafio.domain.TipoPet;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class PetRepositorio {
    public void salvarPet(Pet pet) {
        LocalDateTime data = LocalDateTime.now();
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
        String nome = pet.getNome().replace(" ","").toUpperCase();
        File folderDiretorio = new File("C:\\projetos\\desafioCadastro\\petsCadastrados");
        folderDiretorio.mkdirs();
        File fileDiretorio = new File(folderDiretorio, data.format(formatador) + nome + ".txt");

        boolean isCreated;

        try {
            isCreated = fileDiretorio.createNewFile();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (isCreated) {

            try (FileWriter fw = new FileWriter(fileDiretorio, true);
                 BufferedWriter bw = new BufferedWriter(fw);) {
                bw.write("1 - " + pet.getNome());
                bw.newLine();
                bw.write("2 - " + pet.getTipo());
                bw.newLine();
                bw.write("3 - " + pet.getSexo());
                bw.newLine();
                bw.write("4 - " + pet.getEnderecoBairro());
                bw.newLine();
                bw.write("5 - " + pet.getIdade() + " anos");
                bw.newLine();
                bw.write("6 - " + pet.getPeso() + "kg");
                bw.newLine();
                bw.write("7 - " + pet.getRaca());
                bw.newLine();
                bw.write("8 - " + pet.getDataCadastro().format(formatador));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

   public List<Pet> carregarPets() {
       LocalDateTime data = LocalDateTime.now();
       DateTimeFormatter formatador = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");
       File folderDiretorio = new File("C:\\projetos\\desafioCadastro\\petsCadastrados");
       File[] arquivos = folderDiretorio.listFiles();
       List<Pet> pets = new ArrayList<>();
       if (arquivos == null) return pets;
       for(File arquivo : arquivos) {
           try (FileReader fr = new FileReader(arquivo);
                BufferedReader br = new BufferedReader(fr);) {
               Pet pet = new Pet();
               int contador = 0;
               String linha;
               while ((linha = br.readLine()) != null) {
                   String valor = linha.split(" - ")[1];
                   switch (contador) {
                       case 0: pet.setNome(valor); break;
                       case 1: pet.setTipo(TipoPet.valueOf(valor)); break;
                       case 2: pet.setSexo(SexoPet.valueOf(valor)); break;
                       case 3: pet.setEnderecoBairro(valor); break;
                       case 4: pet.setIdade(valor); break;
                       case 5: pet.setPeso(valor); break;
                       case 6: pet.setRaca(valor); break;
                       case 7: pet.setDataCadastro(LocalDateTime.parse(valor, formatador)); break;
                   }
                   contador++;
               }
               pets.add(pet);

           } catch (IOException e) {
               e.printStackTrace();
           }
       }return pets;
   }

}
