/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

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
public class ListaAreasAtividadeTest {
    
    AreaAtividade a1;
    
    public ListaAreasAtividadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        a1 = new AreaAtividade("1", "Frontend", "Frontend development");
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of novaAreaAtividade method, of class ListaAreasAtividade.
     */
    @Test
    public void testNovaAreaAtividade() {
        System.out.println("novaAreaAtividade");
        String strCodigo = "1";
        String strDescricaoBreve = "Frontend";
        String strDescricaoDetalhada = "Frontend development";
        ListaAreasAtividade instance = new ListaAreasAtividade();
        AreaAtividade expResult = new AreaAtividade("1", "Frontend", "Frontend development");
        AreaAtividade result = instance.novaAreaAtividade(strCodigo, strDescricaoBreve, strDescricaoDetalhada);
        assertEquals(expResult, result);
    }

    /**
     * Test of registaAreaAtividade method, of class ListaAreasAtividade.
     */
    @Test
    public void testRegistaAreaAtividade() {
        System.out.println("registaAreaAtividade");
        AreaAtividade oArea = new AreaAtividade("1", "Frontend", "Frontend development"); ;
        ListaAreasAtividade instance = new ListaAreasAtividade();
        boolean expResult = true;
        boolean result = instance.registaAreaAtividade(oArea);
        assertEquals(expResult, result);
    }

    /**
     * Test of validaAreaAtividade method, of class ListaAreasAtividade.
     */
    @Test
    public void testValidaAreaAtividade() {
        System.out.println("validaAreaAtividade");
        AreaAtividade a1 = new AreaAtividade("1", "Frontend", "Frontend development"); 
        ListaAreasAtividade instance = new ListaAreasAtividade();
        boolean expResult = true;
        boolean result = instance.validaAreaAtividade(a1);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAreaAtividadeById method, of class ListaAreasAtividade.
     */
    @Test
    public void testGetAreaAtividadeById() {
        System.out.println("getAreaAtividadeById");
        String strId = "1";
        ListaAreasAtividade instance = new ListaAreasAtividade();
        AreaAtividade expResult = a1; 
        AreaAtividade result = instance.getAreaAtividadeById(strId);
        assertEquals(expResult, result);
    }
    
}
