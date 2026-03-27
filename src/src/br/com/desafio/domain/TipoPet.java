package br.com.desafio.domain;

import br.com.desafio.Exception.OpcaoInvalidaException;

public enum TipoPet {
    GATO(1),
    CACHORRO(2);

    public final int VALOR;

    TipoPet(int valor) {
        this.VALOR = valor;
    }

    public static TipoPet tipoPet(int opcao) throws OpcaoInvalidaException {
        TipoPet[] tipos = TipoPet.values();
        for (TipoPet tp : tipos) {
            if (tp.VALOR == opcao) {
                return tp;
            }
        }
        throw new OpcaoInvalidaException("Digite um número válido (1 ou 2).");
    }
}
