package com.cineapp.model;

import java.util.Date;

/**
 *
 * @author Selle
 */
public class Filme {
    private int codFilme;
    private String nomeFilme, descricao, genero;

    /**
     * @return the codFilme
     */
    public int getCodFilme() {
        return codFilme;
    }

    /**
     * @param codFilme the codFilme to set
     */
    public void setCodFilme(int codFilme) {
        this.codFilme = codFilme;
    }

    /**
     * @return the nomeFilme
     */
    public String getNomeFilme() {
        return nomeFilme;
    }

    /**
     * @param nomeFilme the nomeFilme to set
     */
    public void setNomeFilme(String nomeFilme) {
        this.nomeFilme = nomeFilme;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the genero
     */
    public String getGenero() {
        return genero;
    }

    /**
     * @param genero the genero to set
     */
    public void setGenero(String genero) {
        this.genero = genero;
    }

   
}
