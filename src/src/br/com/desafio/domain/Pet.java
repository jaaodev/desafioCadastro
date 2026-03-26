package br.com.desafio.domain;

public class Pet {
    private String nome;
    private TipoPet tipo;
    private SexoPet sexo;
    private String enderecoBairro;
    private double idade;
    private double peso;
    private String raca;
    private static final String semResposta = "NÃO INFORMADO";

    public Pet() {
    }

    public Pet(String nome) {
        this.nome = nome;
    }

    public Pet(String nome, TipoPet tipo, SexoPet sexo, String enderecoBairro, double idade, double peso, String raca) {
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

    public double getIdade() {
        return idade;
    }

    public void setIdade(double idade) {
        this.idade = idade;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }
}
