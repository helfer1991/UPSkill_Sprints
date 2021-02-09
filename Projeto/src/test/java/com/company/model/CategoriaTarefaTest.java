/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.util.HashMap;
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
public class CategoriaTarefaTest {
    
    public CategoriaTarefaTest() {
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
     * Test of hasId method, of class CategoriaTarefa.
     */
    @Test
    public void testHasId() {
        System.out.println("hasId");
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        HashMap<CompetenciaTecnica, Boolean> map = new HashMap<>();
        map.put(c1, true);
        CategoriaTarefa instance = new CategoriaTarefa("Webdev", a, map);
        boolean expResult = true;
        boolean result = instance.hasId(instance.getId());
        assertEquals(expResult, result);
    }
    
//        /**
//     * Test of hasId method, of class CategoriaTarefa.
//     */
//    @Test
//    public void testHasId2() {
//        System.out.println("hasId");
//        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
//        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
//        HashMap<CompetenciaTecnica, Boolean> map = new HashMap<>();
//        map.put(c1, true);
//        String idCategoria = "CatTar-44324";
//        CategoriaTarefa instance = new CategoriaTarefa("Webdev", a, map);
//        boolean expResult = false;
//        boolean result = instance.hasId(idCategoria);
//        assertEquals(expResult, result);
//    }
    
    /**
     * Test of equals method, of class CategoriaTarefa.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        HashMap<CompetenciaTecnica, Boolean> map = new HashMap<>();
        map.put(c1, true);
        CategoriaTarefa o = new CategoriaTarefa("Webdev", a, map);
        CategoriaTarefa instance = o;
        boolean expResult = true;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
    }
//    
//        /**
//     * Test of equals method, of class CategoriaTarefa.
//     */
//    @Test
//    public void testEquals2() {
//        System.out.println("equals");
//        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
//        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
//        HashMap<CompetenciaTecnica, Boolean> map = new HashMap<>();
//        map.put(c1, true);
//        CategoriaTarefa o = new CategoriaTarefa("Webdev", a, map);
//        CategoriaTarefa instance = new CategoriaTarefa("Cloud", a, map);
//        boolean expResult = false;
//        boolean result = instance.equals(o);
//        assertEquals(expResult, result);
//    }

    /**
     * Test of getId method, of class CategoriaTarefa.
     */
}
