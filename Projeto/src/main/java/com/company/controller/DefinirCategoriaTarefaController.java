/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.utils.Constantes;
import com.company.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Classe responsável pela ligação do UI com os métodos necessários, para registar novas Categorias de Tarefa.
 * 
 * @author diana
 */
public class DefinirCategoriaTarefaController {

    private final Plataforma plat = Plataforma.getInstance();
    private CategoriaTarefa categoriaTarefa;
    private final ListaAreasAtividade lat = plat.getLat();
    private final RegistoCompetenciasTecnicas rct = plat.getRct();
    private ListaCategoriasTarefa lct = plat.getListaCategoriaTarefa();
    private HashMap<CompetenciaTecnica,Boolean> lcompTec = new HashMap<>();

    /**
     * Constrói uma instância de DefinirCategoriaTarefaController.
     * Valida o acesso do utilizador 'Administrador' para efectuar o registo de categorias de tarefa.
     */
    public DefinirCategoriaTarefaController() {
        if (!Plataforma.getInstance().getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_ADMINISTRADOR)) {
            throw new IllegalStateException("Utilizador não Autorizado");
        }
        this.lct = plat.getListaCategoriaTarefa();
    }

    /**
     * Valida e adiciona um novo objecto do tipo Categoria de Tarefa construído com os parâmetros
     * de entrada.
     * 
     * @param descricao descrição da categoria de tarefa
     * @param oArea área de atividade na qual se enquadra a categoria de tarefa
     * @param lcompTecCT lista de competências técnicas to tipo HashMap, da área de atividade recebida
     * @return true se for possível adicionar a categoria de tarefa, caso contrário
     * devolve false
     */
    public boolean novaCategoriaTarefa( String descricao, AreaAtividade oArea, HashMap<CompetenciaTecnica,Boolean> lcompTecCT)throws Exception  {
        try {
            this.categoriaTarefa = this.plat.getListaCategoriaTarefa().novaCategoriaTarefa(descricao, oArea, lcompTecCT);
            return this.plat.getListaCategoriaTarefa().validaCategoriaTarefa(this.categoriaTarefa);
            } catch (Exception ex) {
            this.categoriaTarefa = null;
            throw ex;
        }
    }
    
    /**
     * Adiciona a competência técnica com a respectiva obrigatoriedade à lista de competências técnicas.
     * 
     * @param ct competência técnica a adicionar
     * @param caraterct obrigatoriedade da competência técnica
     */
    public void addCompTecnicaToList(CompetenciaTecnica ct , Boolean caraterct){
        this.lcompTec.put(ct, caraterct);
    }
    
    /**
     * Remove a competência técnica, da lista de competências, passada por parâmetro.
     * 
     * @param ct competência técnica da lista de competências
     */
    public void RemoveCompTecnicaFromList(CompetenciaTecnica ct){
        this.lcompTec.remove(ct);
    }
    
    /**
     * Devolve a lista de competências técnicas com respectiva obrigatoriedade.
     * 
     * @return lista de competências técnicas com respectiva obrigatoriedade
     */
    public HashMap<CompetenciaTecnica,Boolean> getBuiltMap(){
        return this.lcompTec;
    }

     /**
     * Valida e adiciona um novo objecto do tipo Categoria de Tarefa à lista de categorias de tarefa da plataforma.
     * 
     * @return true se for possível adicionar um novo objecto do tipo Categorias de Tarefa,
     * caso contrário devolve false
     */
    public boolean registaCategoriaTarefa() {
        return this.plat.getListaCategoriaTarefa().registaCategoriaTarefa(this.categoriaTarefa);
    }

     /**
     * Devolve a descrição textual da categoria de tarefa.
     * 
     * @return descrição textual da categoria de tarefa
     */
    public String getCategoriaTarefaString() {
        return this.categoriaTarefa.toString();
    }
    
    /**
     * Adiciona uma competência técnica e respectiva obrigatoriedade à lista de competências técnicas.
     * 
     * @param compTec competência técnica a adicionar à categoria de tarefa
     * @param ct obrigatoriedade da competência técnica para a categoria de tarefa
     */
    public void criarListaCompTecnicaCT(CompetenciaTecnica compTec, Boolean ct){
        this.lcompTec.put(compTec,ct);
    }

    /**
     * Devolve uma lista de áreas de atividade.
     * 
     * @return lista de áreas de atividade
     */
    public ArrayList<AreaAtividade> getListaAreasAtividade() {
        return lat.getLat();
    }

    /**
     * Devolve uma lista de categorias de tarefa.
     * 
     * @return lista de categorias de tarefa
     */
    public ArrayList<CategoriaTarefa> getListaCatTarefa() {
        return lct.getListaCat();
    }

    /**
     * Devolve a lista de descrições e respectivos id's, de áreas de atividade.
     * 
     * @return lista de descrições e respectivos id's, de áreas de atividade, do tipo ArrayList
     */
    public ArrayList<String> getListaAreaAtividadeDescBreve() {
        List<AreaAtividade> list = new ArrayList<AreaAtividade>(getListaAreasAtividade());
        String idDescBreve = "";
        ArrayList<String> listd = new ArrayList<>();
        for (AreaAtividade aa : list) {
            idDescBreve = "ID: "+ aa.getId() + " \n" +"DescBreve"+ aa.getDescBreve();
            listd.add(idDescBreve);
        }

        return listd;
    }
    
     /**
     * Devolve a lista de descrições e respectivos id's, de competências técnicas.
     * 
     * @return lista de descrições e respectivos id's, de competências técnicas, do tipo ArrayList
     */
    public ArrayList<String> getListaCompetenciaTecnicaDescBreve() {
        List<CompetenciaTecnica> list = new ArrayList<CompetenciaTecnica>(plat.getRct().getListaCompTec());
        String idDescBreve = "";
        ArrayList<String> listd = new ArrayList<>();
        for (CompetenciaTecnica compT : list) {
            idDescBreve = compT.getDescBreve() + " - " + compT.getId();
            listd.add(idDescBreve);
        }

        return listd;
    }
    
    /**
     * Devolve uma lista de competências técnicas existentes na plataforma.
     * 
     * @return lista de competências técnicas existentes na plataforma
     */
    public ArrayList<CompetenciaTecnica> getListaCompetenciasTecnicas() {
        return plat.getRct().getListaCompTec();
         
    }

    /**
     * Devolve uma lista de competências técnicas e respectivas obrigatoriedades, de uma categoria de tarefa passada por parâmetro.
     * 
     * @param selectedItem categoria de tarefa passada por parâmetro
     * @return lista de competências técnicas e respectivas obrigatoriedades
     */
    public HashMap<CompetenciaTecnica, Boolean> getCatTarefaHashMap(CategoriaTarefa selectedItem) {
        return plat.getListaCategoriaTarefa().getCategoriaTarefaById(selectedItem.getId()).getLcompTec();
    }
}
