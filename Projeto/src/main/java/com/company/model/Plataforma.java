/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import com.company.controller.UsersAPI;

/**
 *
 * @author Asus
 */
public class Plataforma {

    private static Plataforma plataforma;
    private UsersAPI uAPI;
    private IAlgoritmoGeradorPasswords agp;
    private ListaAreasAtividade lat;
    private RegistoCompetenciasTecnicas rct;
    private ListaCategoriasTarefa m_lstCategoriaTarefa;
    private ListaTarefas m_lstTarefas;
    private RegistoOrganizacoes ro;
    public RepositorioColaborador rc;

    public Plataforma() {
        //Geração de passwords
        agp = new AlgoritmoGeradorPasswords();
        //UsersAPI
        uAPI = new UsersAPI();

        //Promoção do padrão HC/LC
        this.ro = new RegistoOrganizacoes();
        this.lat = new ListaAreasAtividade();
        this.rc = new RepositorioColaborador();
        this.rct = new RegistoCompetenciasTecnicas();
        this.m_lstCategoriaTarefa = new ListaCategoriasTarefa();
        this.m_lstTarefas = new ListaTarefas();

    }

    /**
     * Devolve a instância da plataforma para disponibilizar acesso aos seus
     * métodos.
     *
     * @return plataforma
     */
    public static Plataforma getInstance() {
        if (Plataforma.plataforma == null) {
            Plataforma.plataforma = new Plataforma();
        }
        return plataforma;
    }

    /**
     * Devolve uma lista de organizações.
     * 
     * @return lista de organizações
     */
    public RegistoOrganizacoes getRo() {
        return ro;
    }

    /**
     * Devolve o objecto que vai gerar a password.
     * @return objecto que vai gerar a password
     */
    public IAlgoritmoGeradorPasswords getAlgoritmoGeradorPwd() {
        return agp;
    }

    /**
     * Devolve o objecto users api.
     *
     * @return objecto uAPI do tipo UsersAPI
     */
    public UsersAPI getUsersAPI() {
        return uAPI;
    }

    /**
     * Reinicia a aplicação que trata dos utilizadores
     */
    public void restartUsersAPI() {
        this.uAPI = new UsersAPI();
    }

    /**
     * Devolve  a lista de competências técnicas.
     * 
     * @return lista de competências técnicas
     */
    public RegistoCompetenciasTecnicas getRct() {
        return rct;
    }

    /**
     * Devolve a lista de categorias de tarefa.
     * @return lista de categorias de tarefa
     */
    public ListaCategoriasTarefa getListaCategoriaTarefa() {
        return m_lstCategoriaTarefa;
    }

    /**
     * Devolva a lista de tarefas.
     * 
     * @return lista de tarefas
     */
    public ListaTarefas getListaTarefas() {
        return m_lstTarefas;
    }

    /**
     * Devolve a lista de áreas de atividade.
     * 
     * @return lista de áreas de atividade
     */
    public ListaAreasAtividade getLat() {
        return lat;
    }

    /**
     * DEvolve a lista de colaboradores.
     * @return lista de colaboradores
     */
    public RepositorioColaborador getRc() {
        return rc;
    }

}
