/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.model;

import java.util.ArrayList;
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
public class ListaCategoriasTarefaTest {
    
    public ListaCategoriasTarefaTest() {
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
     * Test of getCategoriaTarefaById method, of class ListaCategoriasTarefa.
     */
    @Test
    public void testGetCategoriaTarefaById() {
        System.out.println("getCategoriaTarefaById");
        String strId = "CatTar-1";
        ListaCategoriasTarefa instance = new ListaCategoriasTarefa();
        String descricao = "1";
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        HashMap<CompetenciaTecnica, Boolean> map = new HashMap<>();
        map.put(c1, true);
        CategoriaTarefa expResult = new CategoriaTarefa(descricao, a, map);
        instance.addCategoriaTarefa(expResult);
        CategoriaTarefa result = instance.getCategoriaTarefaById(expResult.getId());
        assertEquals(expResult, result);
    }
    
    /**
     * Test of novaCategoriaTarefa method, of class ListaCategoriasTarefa.
     */
    @Test
    public void testNovaCategoriaTarefa() { // a falhar por causa do id
        System.out.println("novaCategoriaTarefa");
        String descricao = "1";
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        HashMap<CompetenciaTecnica, Boolean> map = new HashMap<>();
        map.put(c1, true);
        ListaCategoriasTarefa instance = new ListaCategoriasTarefa();
        CategoriaTarefa expResult = new CategoriaTarefa(descricao, a, map);
        CategoriaTarefa result = instance.novaCategoriaTarefa(descricao, a, map);
        assertEquals(expResult, result);
    }

    /**
     * Test of registaCategoriaTarefa method, of class ListaCategoriasTarefa.
     */
    @Test
    public void testRegistaCategoriaTarefa() {
        System.out.println("registaCategoriaTarefa");
        String descricao = "1";
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        HashMap<CompetenciaTecnica, Boolean> map = new HashMap<>();
        map.put(c1, true);
        CategoriaTarefa oCatTarefa = new CategoriaTarefa(descricao, a, map);
        ListaCategoriasTarefa instance = new ListaCategoriasTarefa();
        boolean expResult = true;
        boolean result = instance.registaCategoriaTarefa(oCatTarefa);
        assertEquals(expResult, result);
    }

    /**
     * Test of addCategoriaTarefa method, of class ListaCategoriasTarefa.
     */
    @Test
    public void testAddCategoriaTarefa() {
        System.out.println("addCategoriaTarefa");
        String descricao = "1";
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        HashMap<CompetenciaTecnica, Boolean> map = new HashMap<>();
        map.put(c1, true);
        CategoriaTarefa oCatTarefa = new CategoriaTarefa(descricao, a, map);
        ListaCategoriasTarefa instance = new ListaCategoriasTarefa();
        boolean expResult = true;
        boolean result = instance.addCategoriaTarefa(oCatTarefa);
        assertEquals(expResult, result);
    }

    /**
     * Test of validaCategoriaTarefa method, of class ListaCategoriasTarefa.
     */
    @Test
    public void testValidaCategoriaTarefa() {
        System.out.println("validaCategoriaTarefa");
        String descricao = "1";
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        HashMap<CompetenciaTecnica, Boolean> map = new HashMap<>();
        map.put(c1, true);
        CategoriaTarefa oCatTarefa = new CategoriaTarefa(descricao, a, map);
        ListaCategoriasTarefa instance = new ListaCategoriasTarefa();
        boolean expResult = true;
        boolean result = instance.validaCategoriaTarefa(oCatTarefa);
        assertEquals(expResult, result);
    }

  

    /**
     * Test of getCategoriaTarefaByDescricao method, of class ListaCategoriasTarefa.
     */
    @Test
    public void testGetCategoriaTarefaByDescricao() {
        System.out.println("getCategoriaTarefaByDescricao");
        ListaCategoriasTarefa instance = new ListaCategoriasTarefa();
        String descricao = "1";
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        HashMap<CompetenciaTecnica, Boolean> map = new HashMap<>();
        map.put(c1, true);
        CategoriaTarefa expResult = new CategoriaTarefa(descricao, a, map);
        instance.addCategoriaTarefa(expResult);
        CategoriaTarefa result = instance.getCategoriaTarefaByDescricao(descricao);
        assertEquals(expResult, result);
    }
    
}
