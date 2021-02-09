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
public class ListaTarefas {

    /**
     * Declaração de um objecto do tipo arrayList de Tarefa.
     */
    private ArrayList<Tarefa> listaTarefa;

    /**
     * Constrói uma instância da lista de tarefas, recebendo por parâmetro uma
     * lista de tarefas pré preenchida.
     *
     * @param listTarefa lista de tarefas pré preenchida
     */
    public ListaTarefas(ArrayList<Tarefa> listTarefa) {
        this.listaTarefa = new ArrayList<>(listTarefa);
    }

    /**
     * Constrói uma instância da lista de tarefas,. Inicializa a lista de
     * tarefas vazia.
     */
    public ListaTarefas() {
        this.listaTarefa = new ArrayList<>();
    }

    /**
     * Devolve uma cópia da lista de tarefas.
     *
     * @return cópia da lista de tarefas do tipo arrayList
     */
    public ArrayList<Tarefa> getLista() {
        ArrayList<Tarefa> lTarefa = new ArrayList<>();
        lTarefa.addAll(this.listaTarefa);
        // devolve a cópia do objecto
        return lTarefa;
    }

    /**
     * Devolve um novo objecto do tipo Tarefa construído com os parâmetros de
     * entrada.
     *
     * @param ref referência da Tarefa
     * @param designacao designação da Tarefa
     * @param descrInformal descrição informal da Tarefa
     * @param descrTecnica descrição técnica da Tarefa
     * @param duracaoEst duração estimada da Tarefa
     * @param custoEst custo estimado da Tarefa
     * @param catTarefa objecto do tipo categoria de tarefa
     * @param colab objecto do tipo colaborador
     * @return novo objecto do tipo Tarefa
     */
    public Tarefa novaTarefa(String ref, String designacao, String descrInformal, String descrTecnica, Integer duracaoEst, Double custoEst, CategoriaTarefa catTarefa, Colaborador colab) {
        return new Tarefa(ref, designacao, descrInformal, descrTecnica, duracaoEst, custoEst, catTarefa, colab);
    }

    /**
     * Valida e adiciona uma Tarefa à lista de tarefas.
     * 
     * @param oTarefa Tarefa a adicionar
     * @return true se for possível adicionar a Tarefa, caso contrário devolve false
     */
    public boolean registaTarefa(Tarefa oTarefa) {
        if (this.validaTarefa(oTarefa)) {
            return addTarefa(oTarefa);
        }
        return false;
    }

    /**
     * Adiciona uma Tarefa à lista de tarefas.
     * @param oTarefa Tarefa a adicionar
     * @return true se for adicionada a Tarefa, caso contrário devolve false
     */
    public boolean addTarefa(Tarefa oTarefa) {
        if (this.validaTarefa(oTarefa)) {
            return listaTarefa.add(oTarefa);
        }
        return false;
    }

    /**
     * Verifica se a lista de tarefas contém uma Tarefa 
     * com referência igual à referência do objecto Tarefa passada por parâmetro.
     * 
     * @param oTarefa Tarefa a validar
     * @return true se a referência da Tarefa for diferente de todas as referências da lista de tarefas,
     * caso contrário devolve false com uma mensagem de erro
     */
    public boolean validaTarefa(Tarefa oTarefa) {
        boolean valida = true;

        for (Tarefa t : listaTarefa) {
            if (t.getRef().equalsIgnoreCase(oTarefa.getRef())) {
                valida = false;
                throw new IllegalArgumentException("Já existe outra Tarefa com este ID.");
            }
        }
        return valida;
    }

    /**
     * Devolve uma Tarefa existente na lista de tarefas com referência igual à que é passada por parâmetro.
     * 
     * @param strRef referência da Tarefa
     * @return objecto Tarefa, ou null caso a referência não exista na lista
     */
    public Tarefa getTarefaByRef(String strRef) {

        for (Tarefa t : listaTarefa) {
            if (t.hasRef(strRef)) {
                return t;
            }
        }

        return null;
    }

}
