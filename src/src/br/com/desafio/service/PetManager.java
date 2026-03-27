package br.com.desafio.service;

import br.com.desafio.Exception.IdadeInvalidaException;
import br.com.desafio.Exception.NomeInvalidoException;
import br.com.desafio.Exception.PesoInvalidoException;
import br.com.desafio.domain.Pet;
import br.com.desafio.domain.SexoPet;
import br.com.desafio.domain.TipoPet;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PetManager {
    ArrayList<Pet> pets = new ArrayList<>();

    public void cadastrarPet(Pet pet) throws IdadeInvalidaException, PesoInvalidoException {
        if (!pet.getIdade().equals(pet.getNAO_INFORMADO())) {
            String idadeStr = pet.getIdade().replace(',', '.');
            double idade = Double.parseDouble(idadeStr);
            if (idade > 20 || idade <= 0) {
                throw new IdadeInvalidaException("Digite uma idade maior que 0 ou menor que 21 anos.");
            }
        }
        if (!pet.getPeso().equals(pet.getNAO_INFORMADO())) {
            String pesoStr = pet.getPeso().replace(",", ".");
            double peso = Double.parseDouble(pesoStr);
            if (peso > 60 || peso < 0.5) {
                throw new PesoInvalidoException("Digite um peso maior que 0.4kg ou menor que 60kg.");
            }
        }
        pet.setDataCadastro(LocalDateTime.now());
        pets.add(pet);
        System.out.println("Pet cadastrado com sucesso!");

    }

    public List<Pet> buscarPet(String nome, TipoPet tipo, SexoPet sexo, String endereco,
                               String idade, String peso, String raca) {
        List<Pet> resultado = new ArrayList<>();
        for (Pet pet : pets) {
            if ((nome == null || removerAcentos(pet.getNome().toLowerCase())
                    .contains(removerAcentos(nome.toLowerCase()))) &&
                    (tipo == null || pet.getTipo().equals(tipo)) &&
                    (sexo == null || pet.getSexo().equals(sexo)) &&
                    (endereco == null || removerAcentos(pet.getEnderecoBairro().toLowerCase())
                            .contains(removerAcentos(endereco.toLowerCase()))) &&
                            (idade == null || pet.getIdade().contains(idade)) &&
                            (peso == null || pet.getPeso().contains(peso)) &&
                            (raca == null || pet.getRaca().contains(raca))) {
                resultado.add(pet);
            }
        }
        return resultado;
    }

    public void carregarPets(List<Pet> petsSalvos) {
        pets.addAll(petsSalvos);
    }

    public void validarNome(String nome) throws NomeInvalidoException {
        String[] partes = nome.split(" ");
        if (!nome.matches("[a-zA-ZÀ-ú ]+")) {
            throw new NomeInvalidoException("Digite o nome apenas com letras e acentos.");
        } else if (partes.length < 2) {
            throw new NomeInvalidoException("Digite o nome e o sobrenome.");
        }
    }

    private String removerAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");
    }

}
