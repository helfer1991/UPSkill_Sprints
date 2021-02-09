/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.util.ArrayList;

/**
 *
 * @author diana
 */
public class RegistoCompetenciasTecnicas {

    /**
     * Declaração de um objecto do tipo arrayList CompetenciaTecnica.
     */
    private ArrayList<CompetenciaTecnica> lcompTec;

    /**
     * Constrói uma instância da lista de competências técnicas, recebendo por
     * parâmetro uma lista de competências técnicas pré preenchida.
     *
     * @param lcompTec lista de competências técnicas pré preenchida
     */
    public RegistoCompetenciasTecnicas(ArrayList<CompetenciaTecnica> lcompTec) {
        this.lcompTec = new ArrayList<>(lcompTec);
    }

    /**
     * Constrói uma instância da lista de competências técnicas. Inicializa a
     * lista de competências técnicas vazia.
     */
    public RegistoCompetenciasTecnicas() {
        this.lcompTec = new ArrayList<>();
    }

    /**
     * Devolve um novo objecto do tipo competência técnica construído com os
     * parâmetros de entrada.
     *
     * @param id identificação da competência técnica
     * @param descBreve descrição breve da competência técnica
     * @param descDetalhada descrição detalhada da competência técnica
     * @param aa objecto do tipo área de atividade
     * @return novo objecto do tipo Competência Técnica
     */
    public CompetenciaTecnica novaCompetenciaTecnica(String id, String descBreve, String descDetalhada, AreaAtividade aa) {
        return new CompetenciaTecnica(id, descBreve, descDetalhada, aa);
    }

    /**
     * Valida e adiciona uma competência técnica à lista de ccompetências
     * técnicas.
     *
     * @param ct objecto do tipo competência técnica a adicionar
     * @return true se for possível adicionar a competência técnica, caso
     * contrário devolve false
     */
    public boolean registaCompetenciaTecnica(CompetenciaTecnica ct) {
        if (this.validaCompetenciaTecnica(ct)) {
            return addCompetenciaTecnica(ct);
        }
        return false;
    }

    /**
     * Devolve uma lista de competências técnicas.
     *
     * @return lista de competências técnicas do tipo arrayList.
     */
    public ArrayList<CompetenciaTecnica> getListaCompTec() {
        return this.lcompTec;
    }

    /**
     * Adiciona uma competência técnica à lista de competências técnicas.
     *
     * @param oCompTec objecto do tipo competência técnica a adicionar
     * @return true se for adicionada a competência técnica, caso contrário
     * devolve false
     */
    public boolean addCompetenciaTecnica(CompetenciaTecnica oCompTec) {
        if (this.validaCompetenciaTecnica(oCompTec)) {
            return lcompTec.add(oCompTec);
        }
        return false;
    }

    /**
     * Verifica se a lista de competências técnicas contém uma competência
     * técnica com id igual ao da competência técnica passada por parâmetro.
     *
     * @param oCompTec objecto do tipo competência técnica a validar
     * @return true se o id da competência técnica for diferente de todos os
     * id's da lista de competências técnicas, caso contrário devolve false com
     * uma mensagem de erro
     */
    public boolean validaCompetenciaTecnica(CompetenciaTecnica oCompTec) {
        boolean valida = true;

        for (CompetenciaTecnica ct : lcompTec) {
            if (ct.getId().equalsIgnoreCase(oCompTec.getId())) {
                valida = false;
                throw new IllegalArgumentException("Já existe outra competência técnica com este ID.");
            }
        }
        return valida;
    }

    /**
     * Devolve uma competência técnica existente na lista de competências técnicas com id igual ao passado por parâmetro.
     * 
     * @param strId identificação da competência técnica
     * @return competência técnica, ou null caso o id não exista na lista
     */
    public CompetenciaTecnica getCompetenciaTecnicaById(String strId) {

        for (CompetenciaTecnica ct : lcompTec) {
            if (ct.hasId(strId)) {
                return ct;
            }
        }

        return null;
    }

    /**
     * Devolve uma competência técnica existente na lista de competências técnicas com a descrição igual à que é passada por parâmetro.
     * 
     * @param strDescricao descrição da competência técnica
     * @return competência técnica, ou null caso a descrição não exista na lista
     */
    public CompetenciaTecnica getCompetenciaTecnicaByDescricao(String strDescricao) {

        for (CompetenciaTecnica ct : lcompTec) {
            if (ct.getDescBreve().equalsIgnoreCase(strDescricao)) {
                return ct;
            }
        }

        return null;
    }

}
