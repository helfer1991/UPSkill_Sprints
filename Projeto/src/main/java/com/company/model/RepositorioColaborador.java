/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;


import java.util.ArrayList;
import com.company.utils.Constantes;

/**
 *
 * @author Asus
 */
public class RepositorioColaborador{
    /**
     * Declaração de um objecto do tipo arrayList de Colaborador.
     */
    private ArrayList<Colaborador> listaColaboradores;
    
    /**
     * Declaração da plataforma e atribuição de uma instância de cópia da mesma.
     */
    private static Plataforma plat = Plataforma.getInstance();
    
    /**
     * Constrói uma instância da lista de Colaboradores,
     * recebendo por parâmetro uma lista de Colaboradores pré preenchida.
     * 
     * @param rc lista de colaboradores pré preenchida
     */
    public RepositorioColaborador(ArrayList<Colaborador> rc) {
        this.listaColaboradores = new ArrayList<>(this.listaColaboradores);
    }
    
    /**
     * Constrói uma instância da lista de Colaboradores.
     * Inicializa a lista de Colaboradores vazia.
     */
    public RepositorioColaborador() {
        this.listaColaboradores = new ArrayList<>();
    }

    /**
     * Devolve uma cópia da lista de colaboradores.
     * 
     * @return cópia da lista de colaboradores do tipo arrayList
     */
    public ArrayList<Colaborador> getRC() {
        ArrayList<Colaborador> rc = new ArrayList<>();
        rc.addAll(this.listaColaboradores);
        return rc;
    }
    
    /**
     * Devolve um novo objecto do tipo colaborador construído com os parâmetros de entrada.
     * 
     * @param nome nome do colaborador
     * @param telefone telefone do colaborador
     * @param email e-mail do colaborador
     * @return novo objecto do tipo colaborador
     */
    public Colaborador novoColaborador(String nome, String telefone, String email) {
        return new Colaborador(nome, telefone, email);
    }

    /**
     * Valida e adiciona um colaborador à lista de colaboradores.
     * 
     * @param funcao função do colaborador
     * @param c colaborador da organização
     * @return true se for possível adicionar o colaborador, caso contrário devolve false
     */
    public boolean registaColaborador(String funcao,Colaborador c) {
        if (this.validaColaborador(c)) {
            if(plat.getUsersAPI().registerUserWithRoles(c.getNome(), c.getEmail(), plat.getAlgoritmoGeradorPwd().geraPassword(), funcao))
            return addColaborador(c);
        }
        return false;
    }
    
    /**
     * Adiciona um colaborador à lista de colaboradores.
     * 
     * @param c colaborador a adicionar
     * @return true se for adicionado o colaborador, caso contrário devolve false
     */
    private boolean addColaborador(Colaborador c) {
        return listaColaboradores.add(c);
    }
    
    /**
     * Verifica se a lista de colaboradores contém um colaborador com e-mail e/ou username igual ao do colaborador passado por parâmetro.
     * 
     * @param c colaborador a validar
     * @return true se o e-mail e/ou username forem diferentes dos do colaborador, caso contrário devolve false com uma mensagem de erro
     */
    public boolean validaColaborador(Colaborador c) {
        boolean flag = true;
        
        for(Colaborador colab : listaColaboradores) {
            if((colab.getEmail().equalsIgnoreCase(c.getEmail()) || colab.getNome().equalsIgnoreCase(c.getNome())) || (colab.getEmail().equalsIgnoreCase(c.getEmail()) && colab.getNome().equalsIgnoreCase(c.getNome()))) {
                flag = false;
                throw new IllegalArgumentException("Já existe um colaborador com este Email e/ou Username");
            }
        }      
        return flag;
    }
    
    /**
     * Devolve um colaborador existente na lista de colaboradores com e-mail igual ao passado por parâmetro.
     * 
     * @param email e-mail do colaborador da organização
     * @return colaborador, ou null caso o e-mail não exista na lista
     */
    public Colaborador getColaboradorByEmail(String email) {
        for(Colaborador colab : this.listaColaboradores)  {
            if (colab.hasEmail(email)) {
                return colab;
            }
        }        
        return null;
    }
    
    /**
     * Devolve a função de um colaborador existente na lista de colaboradores com e-mail igual ao passado por parâmetro.
     * 
     * @param email e-mail do colaborador da organização
     * @return função do utilizador da organização
     */
    public String getColaboradorRole(String email){ // apagar?
        Colaborador c = getColaboradorByEmail(email);
        Organizacao org = plat.getRo().getOrganizacaoByEmailUtilizador(email);
        for (Colaborador colab : listaColaboradores) {
            if(colab == org.getGestor()){
                return Constantes.ROLE_GESTOR_ORGANIZACAO;
            }
        }
        return Constantes.ROLE_COLABORADOR_ORGANIZACAO;
    }
}
