package br.com.desafio.domain;

import br.com.desafio.Exception.NumeroInvalidoException;
import br.com.desafio.Exception.OpcaoInvalidaException;

public enum SexoPet {
    MASCULINO('M'),
    FEMININO('F');

    public final char SEXO;

    SexoPet(char sexo) {
        this.SEXO = sexo;
    }
    public static SexoPet sexoPet(char opcao) throws OpcaoInvalidaException {
        SexoPet[] sexos = SexoPet.values();
        for (SexoPet sp : sexos) {
            if (sp.SEXO == opcao) {
                return sp;
            }
        }
        throw new OpcaoInvalidaException("Digite um sexo válido (M ou F).");
    }
}
