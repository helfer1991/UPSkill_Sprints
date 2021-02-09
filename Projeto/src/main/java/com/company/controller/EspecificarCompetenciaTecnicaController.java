/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import java.util.List;
import com.company.model.AreaAtividade;
import com.company.utils.Constantes;
import com.company.model.Plataforma;
import com.company.model.CompetenciaTecnica;
import com.company.model.RegistoCompetenciasTecnicas;
import com.company.ui.AlertaUI;
import com.company.ui.App;
import java.util.ArrayList;
import javafx.scene.control.Alert;

/**
 * Classe responsável pela ligação do UI com os métodos necessários, para registar novas Competências Técnicas.
 * 
 * @author Asus
 */
public class EspecificarCompetenciaTecnicaController {

    private Plataforma plat;
    private CompetenciaTecnica compTecnica;
    private RegistoCompetenciasTecnicas rct;

    /**
     * Constrói uma instância de EspecificarCompetenciaTecnicaController.
     * Valida o acesso do utilizador 'Administrador' para efectuar o registo das competências técnicas.
     */
    public EspecificarCompetenciaTecnicaController() {
        if (!Plataforma.getInstance().getUsersAPI().getRole().equalsIgnoreCase(Constantes.ROLE_ADMINISTRADOR)) {
            throw new IllegalStateException("Utilizador não Autorizado");
        }
        this.plat = Plataforma.getInstance();
        this.rct = plat.getRct();
    }
    
    /**
     * Valida e adiciona um novo objecto do tipo Competência Técnica construído com os parâmetros
     * de entrada.
     * 
     * @param id identificação da competência técnica
     * @param descBreve descrição breve da competência técnica
     * @param descDetalhada descrição detalhada da competência técnica
     * @param areaAtividade área de atividade em que se vai enquadrar a Competência Técnica
     * @return true se for possível adicionar a competência técnica, caso contrário
     * devolve false com uma mensagem de erro
     */
    public boolean novaCompetenciaTecnica(String id, String descBreve, String descDetalhada, AreaAtividade areaAtividade) throws Exception {
        try {
            this.compTecnica = this.plat.getRct().novaCompetenciaTecnica(id, descBreve, descDetalhada, areaAtividade);
            if (this.plat.getRct().validaCompetenciaTecnica(this.compTecnica)) 
                return this.plat.getRct().registaCompetenciaTecnica(compTecnica);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
        return false;
    }
    
     /**
     * Devolve uma lista de áreas de atividade existentes na plataforma.
     * 
     * @return lista de áreas de atividade existentes na plataforma
     */
    public List<AreaAtividade> getAreasAtividade() {
        return this.plat.getLat().getLat();
    }

    /**
     * Devolve uma cópia da lista de competências técnicas existentes na plataforma.
     * 
     * @return cópia da lista de competências técnicas existentes na plataforma
     */
    public List<CompetenciaTecnica> getListCompTec() {
        List<CompetenciaTecnica> lct = this.plat.getRct().getListaCompTec();
        return lct;
    }

    /**
     * Valida e adiciona um novo objecto do tipo Competência Técnica à lista de competências técnicas da plataforma.
     * 
     * @return true se for possível adicionar um novo objecto do tipo Competência Técnica,
     * caso contrário devolve false
     */
    public boolean registaCompetenciaTecnica() {
        return this.plat.getRct().addCompetenciaTecnica(this.compTecnica);
    }

    /**
     * Devolve a descrição textual da competência técnica.
     * 
     * @return descrição textual da competência técnica
     */
    public String getCompetenciaTecnicaString() {
        return this.compTecnica.toString();
    }

     /**
     * Devolve a lista de descrições e respectivos id's, de áreas de atividade.
     * 
     * @return lista de descrições e respectivos id's, de áreas de atividade, do tipo ArrayList
     */
    public ArrayList<String> getListaAreaAtividadeIdDescBreve() {
        List<AreaAtividade> list = new ArrayList<AreaAtividade>(getAreasAtividade());
        String idDescBreve = "";
        ArrayList<String> listd = new ArrayList<>();
        for (AreaAtividade aa : list) {
            idDescBreve = "ID: " + aa.getId() + " \n" + "DescBreve: " + aa.getDescBreve();
            listd.add(idDescBreve);
        }

        return listd;
    }

     /**
     * Devolve uma área de atividade existente na lista de áreas de atividade com id igual ao passado por parâmetro.
     * 
     * @param string identificação da area de atividade
     * @return área de atividade, ou null caso o id não exista na lista
     */
    public AreaAtividade getAreaAtividadeById(String string) {
        String trimmed[] = string.trim().split(" ");
        return plat.getLat().getAreaAtividadeById(trimmed[1]);
    }

}
