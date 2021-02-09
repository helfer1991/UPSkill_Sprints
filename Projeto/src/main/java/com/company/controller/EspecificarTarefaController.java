/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.utils.Constantes;
import com.company.model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela ligação do UI com os métodos necessários, para registar novas Tarefas.
 * 
 * @author diana
 */
public class EspecificarTarefaController {

    /**
     * Inicializa a plataforma com uma cópia com a mesma assinatura.
     */
    private Plataforma plat = Plataforma.getInstance();
    
    /**
     * Declaração de uma organização.
     */
    private Organizacao org;

    /**
     * Constrói uma instância de EspecificarTarefaController.
     * Valida o acesso do utilizador ao registar das tarefas.
     */
    public EspecificarTarefaController() {
        if (!Plataforma.getInstance().getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_COLABORADOR_ORGANIZACAO)
                && !Plataforma.getInstance().getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_GESTOR_ORGANIZACAO)) {
            throw new IllegalStateException("Utilizador não Autorizado");
        }
        this.org = plat.getRo().getOrganizacaoByEmailUtilizador(this.plat.getUsersAPI().getEmail());
    }

    /**
     * Valida e adiciona um novo objecto do tipo Tarefa construído com os parâmetros
     * de entrada.
     * 
     * @param ref referência da Tarefa
     * @param designacao designação da Tarefa
     * @param descrInformal descrição informal da Tarefa
     * @param descrTecnica descrição técnica da Tarefa
     * @param duracaoEst duração estimada da Tarefa
     * @param custoEst custo estimado da Tarefa
     * @param catTarefa categoria de tarefa em que se vai enquadrar a Tarefa
     * @param colab colaborador que faz o registo da Tarefa
     * @return true se for possível adicionar a tarefa, caso contrário
     * devolve false com uma mensagem de erro
     */
    public boolean novaTarefa(String ref, String designacao, String descrInformal,
            String descrTecnica, Integer duracaoEst, Double custoEst, CategoriaTarefa catTarefa, Colaborador colab) throws Exception {
        try {
            Tarefa t = this.org.getRegistoListaTarefas().novaTarefa(ref, designacao, descrInformal, descrTecnica, duracaoEst, custoEst, catTarefa, colab);
            this.org.getRegistoListaTarefas().validaTarefa(t);
            return this.org.getRegistoListaTarefas().addTarefa(t);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * Devolve a lista de descrições e respectivos id's, de categorias de tarefa.
     * @return lista de descrições e respectivos id's, de categorias de tarefa, do tipo ArrayList
     */
    public ArrayList<String> getListaCatTarefaIdDesc() {
        List<CategoriaTarefa> list = new ArrayList<>(plat.getListaCategoriaTarefa().getListaCat());
        String idDescBreve = "";
        ArrayList<String> listd = new ArrayList<>();
        if (list == null) {
            idDescBreve = "null";
            listd.add(idDescBreve);
        }
        for (CategoriaTarefa catTar : list) {
            idDescBreve = "ID: " + catTar.getId() + " \n" + "Descrição: " + catTar.getDescricao();
            listd.add(idDescBreve);
        }

        return listd;
    }

    /**
     * Devolve o objecto colaborador correspondente ao e-mail de login.
     * @return objecto colaborador correspondente ao e-mail de login
     */
    public Colaborador getColaborador() {
        String email = this.plat.getUsersAPI().getEmail();
        return this.org.getRepositorioColaborador().getColaboradorByEmail(email);
    }

    /**
     * Devolve o objecto categoria de tarefa correspondente ao id passado por parâmetro.
     * 
     * @param s id da categoria de tarefa
     * @return objecto categoria de tarefa
     */
    public CategoriaTarefa getCategoriaTarefa(String s) {
        return this.plat.getListaCategoriaTarefa().getCategoriaTarefaById(s);
    }

    /**
     * Devolve uma lista de tarefas existentes na organização.
     * @return lista de tarefas existentes na organização
     */
    public ArrayList<Tarefa> getListaTarefas() {
        return this.org.getRegistoListaTarefas().getLista();
    }
}
