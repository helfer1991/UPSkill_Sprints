/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;


import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author diana
 */
public class ListaCategoriasTarefa {

    /**
     * Declaração de um objecto do tipo arrayList de CategoriaTarefa.
     */
    private ArrayList<CategoriaTarefa> lcat;

    /**
     * Constrói uma instância da lista de categorias de tarefa, 
     * recebendo por parâmetro uma lista de categorias de tarefa pré preenchida.
     * 
     * @param lcat lista de categorias de tarefa pré preenchida
     */
    public ListaCategoriasTarefa(ArrayList<CategoriaTarefa> lcat) {
        this.lcat = new ArrayList<>(lcat);
    }
    
    /**
     * Constrói uma instância da lista de categorias de tarefa.
     * Inicializa a lista de categorias de tarefa vazia.
     */
    public ListaCategoriasTarefa() {
        this.lcat = new ArrayList<>();
    }

    /**
     * Devolve uma cópia da lista de categorias de tarefa.
     *
     * @return cópia da lista de categorias de tarefa do tipo arrayList
     */
    public ArrayList<CategoriaTarefa> getListaCat() {
        ArrayList<CategoriaTarefa> lCatTarefa = new ArrayList<>();
        lCatTarefa.addAll(this.lcat);
        // devolve a cópia do objecto
        return lCatTarefa;
    }
    /**
     * Devolve um novo objecto do tipo categoria de tarefa construído com os parâmetros de entrada.
     * 
     * @param descricao descrição da categoria de tarefa
     * @param oArea objecto área de atividade
     * @param lcompTecCT lista de competências técnicas do tipo HashMap (CompetenciaTecnica, Obrigatoriedade)
     * @return novo objecto do tipo categoria de tarefa
     */
    public CategoriaTarefa novaCategoriaTarefa(String descricao, AreaAtividade oArea, HashMap<CompetenciaTecnica,Boolean> lcompTecCT) {
        return new CategoriaTarefa(descricao, oArea,lcompTecCT);
    }
    
    /**
     * Valida e adiciona uma categoria de tarefa à lista de categorias de tarefa.
     * 
     * @param oCatTarefa categoria de tarefa a adicionar
     * @return true se for possível adicionar a categoria de tarefa, caso contrário devolve false
     */
     public boolean registaCategoriaTarefa(CategoriaTarefa oCatTarefa) {
        if (this.validaCategoriaTarefa(oCatTarefa)) {
            return addCategoriaTarefa(oCatTarefa);
        }
        return false;
    }

     /**
      * Adiciona uma categoria de tarefa à lista de categorias de tarefa.
      * 
      * @param oCatTarefa categoria de tarefa a adicionar
      * @return true se for adicionada a categoria de tarefa, caso contrário devolve false
      */
    public boolean addCategoriaTarefa(CategoriaTarefa oCatTarefa) {
        if (this.validaCategoriaTarefa(oCatTarefa)) {
            return this.lcat.add(oCatTarefa);
        }
        return false;
    }

    /**
     * Verifica se a lista de categorias de tarefa contém uma categoria de tarefa com id igual ao da 
     * categoria de tarefa passada por parâmetro.
     * @param oCatTarefa categoria de tarefa a validar
     * @return true se o id da categoria de tarefa for diferente de todos os id's da lista da categorias de tarefa,
     * caso contrário devolve false com uma mensagem de erro
     */
    public boolean validaCategoriaTarefa(CategoriaTarefa oCatTarefa) {
        boolean valida = true;

        for (CategoriaTarefa c : lcat) {
            if (c.getId().equalsIgnoreCase(oCatTarefa.getId())) {
                valida = false;
                throw new IllegalArgumentException("Já existe outra categoria de tarefa com este ID.");
            }
        }
        return valida;
    }

    /**
     * Devolve uma categoria de tarefa existente na lista de categorias de tarefa com id igual ao passado por parâmetro.
     * 
     * @param strId identificação da categoria de tarefa
     * @return categoria de tarefa, ou null caso o id não exista na lista
     */
    public CategoriaTarefa getCategoriaTarefaById(String strId) {

        for (CategoriaTarefa c : lcat) {
            if (c.hasId(strId)) {
                return c;
            }
        }

        return null;
    }
    
    /**
     * Devolve uma categoria de tarefa existente na lista de categorias de tarefa com descricao igual à passada por parâmetro.
     * 
     * @param strDescricao descrição da categoria de tarefa
     * @return categoria de tarefa, ou null caso a descrição não exista na lista
     */
    public CategoriaTarefa getCategoriaTarefaByDescricao(String strDescricao) {

        for (CategoriaTarefa c : lcat) {
            if (c.getDescricao().equalsIgnoreCase(strDescricao)) {
                return c;
            }
        }

        return null;
    }

}
