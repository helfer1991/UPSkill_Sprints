/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import com.company.controller.UsersAPI;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Asus
 */
public class RegistoOrganizacoesTest {
    
    public RegistoOrganizacoesTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of novaOrganizacao method, of class RegistoOrganizacoes.
     */
    @Test
    public void testNovaOrganizacao() {
        System.out.println("novaOrganizacao");
        String nome = "Mindera";
        String nif = "500123321";
        String site = "www.mindera.pt";
        String telefone = "229876541";
        String email = "mindera@mindera.pt";
        EnderecoPostal endPostal = new EnderecoPostal("rua", "4450-123", "Ramalde");
        Colaborador colab = new Colaborador("Poupas", "1232131", "123@123.pt");
        RegistoOrganizacoes instance = new RegistoOrganizacoes();
        Organizacao expResult = new Organizacao("Mindera", "500123321", "www.mindera.pt", "229876541", "mindera@mindera.pt", endPostal, colab);
        Organizacao result = instance.novaOrganizacao(nome, nif, site, telefone, email, endPostal, colab);
        assertEquals(expResult, result);
    }

    /**
     * Test of registaOrganizacao method, of class RegistoOrganizacoes.
     */
    @Test
    public void testRegistaOrganizacao() {
        System.out.println("registaOrganizacao");        
        EnderecoPostal endPostal = new EnderecoPostal("rua", "4450-123", "Ramalde");
        Colaborador colab = new Colaborador("Poupas", "1232131", "123@123.pt");
        Organizacao org = new Organizacao("Mindera", "500123321", "www.mindera.pt", "229876541", "mindera@mindera.pt", endPostal, colab);
        RegistoOrganizacoes instance = new RegistoOrganizacoes();
        boolean expResult = true;
        boolean result = instance.registaOrganizacao(org);
        assertEquals(expResult, result);
    }

    /**
     * Test of getOrganizacaoByEmailUtilizador method, of class RegistoOrganizacoes.
     */
    @Test
    public void testGetOrganizacaoByEmailUtilizador() {
        System.out.println("getOrganizacaoByEmailUtilizador");
        String email = "hmcfxxxffggfasdasdasd@test.pt";
        RegistoOrganizacoes instance = new RegistoOrganizacoes();
        EnderecoPostal endPostal = new EnderecoPostal("rua", "4450-666", "Ramalde");
        Colaborador colab = new Colaborador("dgfgdfgdfgdbcvbcvbdgfdfggfasddsasd", "123213165", "hmcfxxxffggfasdasdasd@test.pt");
        Organizacao o = new Organizacao("efa", "222555222", "www.hmcf.pt", "22987999", "fgh@poupas.pt", endPostal, colab);
        instance.addOrganizacao(o);
        Organizacao expResult = o;
        Organizacao result = instance.getOrganizacaoByEmailUtilizador(email);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of registaColaboradorComoUtilizador method, of class RegistoOrganizacoes.
     */
    @Test
    public void testRegistaColaboradorComoUtilizador() {
        System.out.println("registaColaboradorComoUtilizador");
        Colaborador colab = new Colaborador("nfgnfgnfgbfgbmghmghmclbfggfasdasadsdasd", "432123456", "fdgfnbfgnfgnmghmghmclbfggfasdasasddasd@asdfdsa.pt");
        RegistoOrganizacoes instance = new RegistoOrganizacoes();
        boolean expResult = true;
        boolean result = instance.registaColaboradorComoUtilizador(colab);
        assertEquals(expResult, result);
    }
    
}
