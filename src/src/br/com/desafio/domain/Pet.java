package br.com.desafio.domain;

import java.time.LocalDateTime;

public class Pet {
    private String nome;
    private TipoPet tipo;
    private SexoPet sexo;
    private String enderecoBairro;
    private String idade;
    private String peso;
    private String raca;
    private final String NAO_INFORMADO = "NÃO INFORMADO";
    LocalDateTime dataCadastro;

    public Pet() {
    }

    public Pet(String nome) {
        this.nome = nome;
    }

    public Pet(String nome, TipoPet tipo, SexoPet sexo, String enderecoBairro, String  idade, String peso, String raca) {
        this.nome = nome;
        this.tipo = tipo;
        this.sexo = sexo;
        this.enderecoBairro = enderecoBairro;
        this.idade = idade;
        this.peso = peso;
        this.raca = raca;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoPet getTipo() {
        return tipo;
    }

    public void setTipo(TipoPet tipo) {
        this.tipo = tipo;
    }

    public SexoPet getSexo() {
        return sexo;
    }

    public void setSexo(SexoPet sexo) {
        this.sexo = sexo;
    }

    public String getEnderecoBairro() {
        return enderecoBairro;
    }

    public void setEnderecoBairro(String enderecoBairro) {
        this.enderecoBairro = enderecoBairro;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public String getNAO_INFORMADO() {
        return NAO_INFORMADO;
    }

    public LocalDateTime getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(LocalDateTime dataCadastro) {
        this.dataCadastro = dataCadastro;
    }
}
