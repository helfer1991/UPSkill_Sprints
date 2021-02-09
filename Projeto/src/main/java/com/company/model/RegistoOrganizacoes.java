/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import com.company.controller.UsersAPI;
import java.util.ArrayList;
import com.company.utils.Constantes;

/**
 *
 * @author Asus
 */
public class RegistoOrganizacoes {

    /**
     * Declaração de um objecto do tipo arrayList de Organização.
     */
    private ArrayList<Organizacao> lOrg;

    /**
     * Declaração de um objecto do tipo UsersAPI.
     */
    private UsersAPI uAPI;

    /**
     * Declaração de um objecto do tipo AlgoritmoGeradorPasswords que vai
     * assumir uma password aleatória.
     */
    private AlgoritmoGeradorPasswords agp;

    /**
     * Constrói uma instância da lista de organizações, recebendo por parâmetro
     * uma lista de organizações pré preenchida.
     *
     * @param lo lista de organizações pré preenchida
     */
    public RegistoOrganizacoes(ArrayList<Organizacao> lo) {
        this.lOrg = lo;
    }

    /**
     * Constrói uma instância da lista de organizações. Inicializa a lista de
     * organizações vazia.
     */
    public RegistoOrganizacoes() {
        this.lOrg = new ArrayList<>();
        this.uAPI = new UsersAPI();
        this.agp = new AlgoritmoGeradorPasswords();
    }

    /**
     * Devolve o objecto users api.
     *
     * @return objecto uAPI do tipo UsersAPI
     */
    public UsersAPI getUsersAPI() {
        return this.uAPI;
    }

    /**
     * Devolve a lista de organizações.
     *
     * @return lista de organizações do tipo arrayList.
     */
    public ArrayList<Organizacao> getlOrg() {
        return lOrg;
    }

    /**
     * Devolve um novo objecto do tipo Organização construído com os parâmetros
     * de entrada.
     *
     * @param nome nome da organização
     * @param nif número de identificação fiscal da organização
     * @param site endereço web da organização
     * @param telefone contacto telefónico da organização
     * @param email e-mail da organização
     * @param endPostal endereço postal da organização
     * @param colab colaborador(por exemplo um gestor) que está a proceder ao
     * registo da organização
     * @return novo objecto do tipo Organização
     */
    public Organizacao novaOrganizacao(String nome, String nif, String site, String telefone, String email, EnderecoPostal endPostal, Colaborador colab) {
        return new Organizacao(nome, nif, site, telefone, email, endPostal, colab);
    }

    /**
     * Valida e adiciona uma organização à lista de organizações.
     *
     * @param org organização a adicionar
     * @return true se for possível adicionar a organização, caso contrário
     * devolve false
     */
    public boolean registaOrganizacao(Organizacao org) {
        if (this.validaOrganizacao(org)) {
            return addOrganizacao(org);
        }
        return false;
    }

    /**
     * Adiciona uma organização à lista de organizações.
     *
     * @param org organização a adicionar
     * @return true se for adicionada a organização, caso contrário devolve
     * false
     */
    public boolean addOrganizacao(Organizacao org) {
        return lOrg.add(org);
    }

    /**
     * Verifica se a lista de organizações contém uma organização igual à que é
     * passada por parâmetro.
     *
     * @param org organização a validar
     * @return true se a organização for diferente de todas as organizações da
     * lista, caso contrário devolve false com uma mensagem de erro
     */
    public boolean validaOrganizacao(Organizacao org) {
        boolean flag = true;
        for (Organizacao organizacao : lOrg) {
            if (org.equals(organizacao)) {
                flag = false;
                throw new IllegalArgumentException("Já existe outra organização com os dados inseridos.");
            }
        }
        return flag;
    }

    /**
     * Adiciona um registo de um novo colaborador como utilizador à lista de
     * colaboradores.
     *
     * @param colab colaborador da organização
     * @return true se o registo for feito com sucesso, caso contrário devolve
     * false
     */
    public boolean registaColaboradorComoUtilizador(Colaborador colab) {
        return this.uAPI.registerUserWithRoles(colab.getNome(), colab.getEmail(), agp.geraPassword(), Constantes.ROLE_COLABORADOR_ORGANIZACAO);
    }

//    /**
//     * Adiciona um registo de um novo colaborador como utilizador à lista de colaboradores.
//     * 
//     * @param funcao função desempenhada pelo utilizador
//     * @param c organização à qual está associado o utilizador
//     * @param colab colaborador que se pretende registar
//     * @return true se o registo for feito com sucesso, caso contrário devolve
//     * false
//     */
//    private boolean registaGestorComoUtilizador(String funcao, Organizacao c, Colaborador colab) {
//        return c.getRepositorioColaborador().registaColaborador(funcao, colab);
//    }

    /**
     * Devolve uma Organização existente na lista de organizações com e-mail igual ao passado por parâmetro.
     * 
     * @param email e-mail da organização
     * @return organização, ou null caso o e-mail não exista na lista
     */
    public Organizacao getOrganizacaoByEmailUtilizador(String email) {
        for (Organizacao o : lOrg) {
            for (Colaborador c : o.getRepositorioColaborador().getRC()) {
                if (c.getEmail().equalsIgnoreCase(email)) {
                    return o;
                }
            }
        }
        return null;
    }
}
