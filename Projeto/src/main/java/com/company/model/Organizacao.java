/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.company.utils.Constantes;
import java.util.Objects;

/**
 *
 * @author Asus
 */
public class Organizacao {

    private static final String PATTERN_NUM = "[0-9]+";
    private static final String PATTERN_NOME = "[a-zA-Z]+";
    private static final String PATTERN_EMAIL = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$";
    private static final String PATTERN_SITE = "^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$";
    private String nome;
    private String nif;
    private EnderecoPostal endPostal;
    private String site;
    private String telefone;
    private String email;
    private Colaborador gestor;
    private RepositorioColaborador listaColabs;
    private ListaTarefas lstTarefas;

    /**
     * Constrói uma instância da classe Organização recebendo o nome, o número de identificação fiscal,
     * o site, o número de telefone, o e-mail, a morada, e o colaborador que faz o registo da organização.
     * Só é criada se todos os parâmetros forem válidos.
     * 
     * @param nome nome da organização
     * @param nif número de identificação fiscal da organização
     * @param site endereço web da organização
     * @param telefone contacto telefónico da organização
     * @param email e-mail da organização
     * @param morada endereço postal da organização
     * @param colab colaborador(por exemplo um gestor) que está a proceder ao registo da organização
     */
    public Organizacao(String nome, String nif, String site, String telefone,String email, EnderecoPostal morada, Colaborador colab) {
        if ((nome == null) || (nif == null) || (telefone == null)
                || (email == null) || (morada == null) || (colab == null)
                || (nome.isEmpty()) || (nif.isEmpty()) || (telefone.isEmpty())
                || (email.isEmpty())) {
            throw new IllegalArgumentException("Nenhum dos argumentos pode ser nulo ou vazio.");
        }

        setNome(nome);
        setNif(nif);
        setEndPostal(morada);
        setSite(site);
        setTelefone(telefone);
        setEmail(email);
        this.listaColabs = new RepositorioColaborador();
        this.lstTarefas = new ListaTarefas();
        setGestor(colab);

    }

    /**
     * Devolve um novo objecto do tipo Colaborador construído com os parâmetros de entrada.
     * 
     * @param nome nome do colaborador
     * @param telefone telefone do colaborador
     * @param email e-mail do colaborador
     * @return novo objecto do tipo Colaborador
     */
    public static Colaborador novoColaborador(String nome, String telefone, String email) {
        return new Colaborador(nome, telefone, email);
    }

    /**
     * Devolve um novo objecto do tipo Endereço Postal construído com os parâmetros de entrada.
     * 
     * @param rua rua do endereço postal
     * @param codPostal código postal do endereço postal
     * @param localidade localidade do endereço postal
     * @return novo objecto do tipo Endereço Postal
     */
    public static EnderecoPostal novoEnderecoPostal(String rua, String codPostal, String localidade) {
        return new EnderecoPostal(rua, codPostal, localidade);
    }

    /**
     * Devolve o objecto Gestor.
     * @return objecto Gestor do tipo Colaborador
     */
    public Colaborador getGestor() {
        return this.gestor;
    }

    /**
     * /**
     * Devolve o hashCode do número de identificação fiscal da Organização.
     *
     * @return hashCode do número de identificação fiscal da Organização
     * @see Object#hashCode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.nif);
        return hash;
    }

    /**
     * Compara a Organização com o objeto recebido.
     *
     * @param o objeto a comparar com a Organização
     * @return true se o objeto recebido representar uma organização
     * equivalente à organização. Caso contrário, retorna false.
     */
    @Override
    public boolean equals(Object o) {
        // self check
        if (this == o) {
            return true;
        }
        // null check
        if (o == null) {
            return false;
        }
        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }
        // field comparison
        Organizacao obj = (Organizacao) o;
        return (Objects.equals(nif, obj.nif));
    }

    /**
     * Devolve a descrição textual da Organização no formato: Nome -
     * NIF - Site - Telefone - Email - Endereço postal - Gestor.
     *
     * @return caraterísticas da Organização
     */
    @Override
    public String toString() {
        String str = String.format("%s - %s - %s - %s - %s - %s - %s", this.nome, this.nif, this.site, this.telefone, this.email, this.endPostal.toString(), this.gestor.toString());
        return str;
    }

    /**
     * Devolve uma lista de tarefas.
     * 
     * @return objecto lista de tarefas do tipo lista de tarefas
     */
    public ListaTarefas getRegistoListaTarefas() {
        return lstTarefas;
    }

    /**
     * Devolve uma lista de colaboradores.
     * 
     * @return objecto lista de colaboradores do tipo repositório colaborador
     */
    public RepositorioColaborador getRepositorioColaborador() {
        return listaColabs;
    }

    /**
     * Modifica o nome da Organização.
     * Verifica se apenas contém caracteres alfabéticos.
     * 
     * @param nome nome da Organização no padrão "[a-zA-Z]+"
     */
    private void setNome(String nome) {
        if (nome.matches(PATTERN_NOME)) {
            this.nome = nome;
        } else {
            throw new IllegalArgumentException("Nome não deve conter números.");
        }
    }

    /**
     * Modifica o número de identificação fiscal da Organização.
     * Verifica se é do tipo numérico.
     * 
     * @param nif número de identificação fiscal da organização no padrão "[0-9]+"
     */
    private void setNif(String nif) {
        if (nif.matches(PATTERN_NUM)) {
            this.nif = nif;
        } else {
            throw new IllegalArgumentException("Telefone deve ser do tipo numérico.");
        }
    }

    /**
     * Define o endereço postal da Organização.
     * 
     * @param endPostal endereço postal da Organização
     */
    private void setEndPostal(EnderecoPostal endPostal) {
        this.endPostal = endPostal;
    }

    /**
     * Modifica o endereço web da Organização
     * Valida se os caracteres estão dentro do padrão definido.
     * 
     * @param site endereço web da Organização no padrão 
     * "^(http:\\/\\/|https:\\/\\/)?(www.)?([a-zA-Z0-9]+).[a-zA-Z0-9]*.[a-z]{3}.?([a-z]+)?$"
     */
    private void setSite(String site) {
        if (site.matches(PATTERN_SITE)) {
            this.site = site;
        } else {
            throw new IllegalArgumentException("Email Inválido");
        }
    }

    /**
     * Define o contacto telefónico da Organização.
     * O contacto telefónico apenas assume o novo formato se for numérico,
     * se não o for apresenta uma mensagem de erro.
     * 
     * @param telefone contacto telefónico da organização no padrão "[0-9]+"
     */
    private void setTelefone(String telefone) {
        if (telefone.matches(PATTERN_NUM)) {
            this.telefone = telefone;
        } else {
            throw new IllegalArgumentException("Telefone deve ser do tipo numérico.");
        }
    }

    /**
     * Define o e-mail da Organização.
     * Valida se os caracteres estão dentro do padrão definido.
     * 
     * @param email e-mail da organização no padrão "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$"
     */
    private void setEmail(String email) {
        if (email.matches(PATTERN_EMAIL)) {
            this.email = email;
        } else {
            throw new IllegalArgumentException("Email Invalido");
        }
    }

    /**
     * Define o novo gestor da Organização e adiciona-o ao repositório de colaboradores.
     * 
     * @param gestor objecto Gestor do tipo Colaborador
     */
    private void setGestor(Colaborador gestor) {
        this.gestor = gestor;
        this.getRepositorioColaborador().registaColaborador(Constantes.ROLE_GESTOR_ORGANIZACAO,gestor);
    }

}
