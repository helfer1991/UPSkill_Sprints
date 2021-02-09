/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import static com.company.utils.Constantes.ROLE_COLABORADOR_ORGANIZACAO;
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
public class RepositorioColaboradorTest {
    Colaborador c1;
    Colaborador c2;
    
    public RepositorioColaboradorTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        c1 = new Colaborador("Ze", "91827365", "1171482@isep.ipp.pt");
        c2 = new Colaborador("Ze", "912345678", "cenas123@isep.pt");
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of novoColaborador method, of class RepositorioColaborador.
     */
    @Test
    public void testNovoColaborador() {
        System.out.println("novoColaborador");
        String nome = "Maria";
        String telefone = "219837465";
        String email = "maria@gmail.com";
        RepositorioColaborador instance = new RepositorioColaborador();
        Colaborador expResult = new Colaborador("Maria", "219837465", "maria@gmail.com");
        Colaborador result = instance.novoColaborador(nome, telefone, email);
        assertEquals(expResult, result);
    }

    /**
     * Test of registaColaborador method, of class RepositorioColaborador.
     */
    @Test
    public void testRegistaColaborador() { // A FALHAR
        System.out.println("registaColaborador");
        Colaborador colab = new Colaborador("zezezezezezezezezezezasdasdasd", "999998885", "zezezeasdasdasdasd@renas.pt");
        RepositorioColaborador instance = new RepositorioColaborador();
        boolean expResult = true;
        boolean result = instance.registaColaborador(ROLE_COLABORADOR_ORGANIZACAO, colab);
        assertEquals(expResult, result);
    }

    /**
     * Test of getColaboradorByEmail method, of class RepositorioColaborador.
     */
    @Test
    public void testGetColaboradorByEmail() {
        System.out.println("getColaboradorByEmail");
        String email = "cenas@isep.pt";
        RepositorioColaborador instance = new RepositorioColaborador();
        Colaborador expResult = c2;
        Colaborador result = instance.getColaboradorByEmail(email);
        assertEquals(expResult, result);
    }
    /**
     * Test of validaColaborador method, of class RepositorioColaborador.
     */
    @Test
    public void testValidaColaborador() {
        System.out.println("validaColaborador");
        Colaborador c = new Colaborador("Ze", "91827365", "1171482@isep.ipp.pt");;
        RepositorioColaborador instance = new RepositorioColaborador();
        boolean expResult = true;
        boolean result = instance.validaColaborador(c);
        assertEquals(expResult, result);
    }

    /**
     * Test of getColaboradorRole method, of class RepositorioColaborador.
     */
    @Test
    public void testGetColaboradorRole() {
        System.out.println("getColaboradorRole");
        String email = "1171482@isep.pt";
        RepositorioColaborador instance = new RepositorioColaborador();
        String expResult = "colaborador";
        String result = instance.getColaboradorRole(email);
        assertEquals(expResult, result);
    }
    
}
