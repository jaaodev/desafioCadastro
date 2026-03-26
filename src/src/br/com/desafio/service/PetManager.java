package br.com.desafio.service;

import br.com.desafio.Exception.IdadeInvalidaException;
import br.com.desafio.Exception.NomeInvalidoException;
import br.com.desafio.Exception.PesoInvalidoException;
import br.com.desafio.domain.Pet;

import java.util.ArrayList;

public class PetManager {
    ArrayList<Pet> pets = new ArrayList<>();

    public void cadastrarPet(Pet pet) throws NomeInvalidoException, IdadeInvalidaException, PesoInvalidoException {
        if (pet.getIdade() > 20 || pet.getIdade() <= 0) {
            throw new IdadeInvalidaException("Digite uma idade maior que 0 ou menor que 21 anos.");
        } else if (pet.getPeso() > 60 || pet.getPeso() < 0.5) {
            throw new PesoInvalidoException("Digite um peso maior que 0.4kg ou menor que 60kg.");
        } else {
            pets.add(pet);
            System.out.println("Pet cadastrado com sucesso!");
        }
    }

    public void validarNome(String nome) throws NomeInvalidoException {
        String[] partes = nome.split(" ");
        if (!nome.matches("[a-zA-ZÀ-ú ]+")) {
            throw new NomeInvalidoException("Digite o nome apenas com letras e acentos.");
        } else if (partes.length < 2) {
            throw new NomeInvalidoException("Digite o nome e o sobrenome.");
        }
    }

}
