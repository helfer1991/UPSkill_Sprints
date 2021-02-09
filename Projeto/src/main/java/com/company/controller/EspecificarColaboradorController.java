/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.controller;

import com.company.model.*;
import com.company.utils.Constantes;
import java.util.ArrayList;

import java.util.List;

/**
 * Classe responsável pela ligação do UI com os métodos necessários, para registar novos Colaboradores.
 * 
 * @author Asus
 */
public class EspecificarColaboradorController {

    private Plataforma plat;
    private Organizacao org;

    /**
     * Constrói uma instância de EspecificarColaboradorController. 
     * Inicializa a plataforma com uma cópia da mesma, 
     * e copia a organizações correspondente ao e-mail utilizado no login da API.
     */
    public EspecificarColaboradorController() {
        this.plat = Plataforma.getInstance();
        this.org = plat.getRo().getOrganizacaoByEmailUtilizador(plat.getUsersAPI().getEmail());
    }

    /**
     * 
     * Valida e adiciona um novo objecto do tipo Colaborador construído com os parâmetros
     * de entrada.
     * 
     * @param nome nome do colaborador
     * @param telefone telefone do colaborador
     * @param email e-mail do colaborador
     * @return true se for possível adicionar o colaborador, caso contrário
     * devolve false com uma mensagem de erro
     */
    public boolean novoColaborador(String nome, String telefone, String email) throws Exception {
        try {
            Colaborador c = this.org.getRepositorioColaborador().novoColaborador(nome, telefone, email);
            return this.org.getRepositorioColaborador().registaColaborador(Constantes.ROLE_GESTOR_ORGANIZACAO,c);
        } catch (Exception ex) {
            throw ex;
        }
    }

    /**
     * Devolve a lista de colaboradores da organização.
     * 
     * @return lista de colaboradores da organização do tipo List
     */
    public List<Colaborador> getListColaboradores() {
        List<Colaborador> listAA = this.org.getRepositorioColaborador().getRC();
        return listAA;
    }
}
