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
public class ListaTarefasTest {
    
    public ListaTarefasTest() {
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
     * Test of novaTarefa method, of class ListaTarefas.
     */
    @Test
    public void testNovaTarefa() {
        System.out.println("novaTarefa");
        String ref = "1";
        String designacao = "Frontend development";
        String descrInformal = "Desenvolvimento de aplicacoes web";
        String descrTecnica = "html, css e javascript";
        Integer duracaoEst = 10;
        Double custoEst = 1000.0;
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        HashMap<CompetenciaTecnica, Boolean> map = new HashMap<>();
        map.put(c1, true);
        CategoriaTarefa ct = new CategoriaTarefa("Webdev", a, map);
        Colaborador c = new Colaborador("Jose", "918273645", "jose@mindera.pt");
        ListaTarefas instance = new ListaTarefas();
        Tarefa expResult = new Tarefa(ref, designacao, descrInformal, descrTecnica, duracaoEst, custoEst, ct, c);
        Tarefa result = instance.novaTarefa(ref, designacao, descrInformal, descrTecnica, duracaoEst, custoEst, ct, c);
        assertEquals(expResult, result);
    }

    /**
     * Test of registaTarefa method, of class ListaTarefas.
     */
    @Test
    public void testRegistaTarefa() {
        System.out.println("registaTarefa");
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        HashMap<CompetenciaTecnica, Boolean> map = new HashMap<>();
        map.put(c1, true);
        CategoriaTarefa ct = new CategoriaTarefa("Webdev", a, map);
        Colaborador c = new Colaborador("Jose", "918273645", "jose@mindera.pt");
        Tarefa oTarefa = new Tarefa("1", "Frontend development", "Desenvolvimento de aplicacoes web", "html, css, javascript", 21, 200.0, ct, c);
        ListaTarefas instance = new ListaTarefas();
        boolean expResult = true;
        boolean result = instance.registaTarefa(oTarefa);
        assertEquals(expResult, result);
    }

    /**
     * Test of addTarefa method, of class ListaTarefas.
     */
    @Test
    public void testAddTarefa() {
        System.out.println("addTarefa");
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        HashMap<CompetenciaTecnica, Boolean> map = new HashMap<>();
        map.put(c1, true);
        CategoriaTarefa ct = new CategoriaTarefa("Webdev", a, map);
        Colaborador c = new Colaborador("Jose", "918273645", "jose@mindera.pt");
        Tarefa oTarefa = new Tarefa("1", "Frontend development", "Desenvolvimento de aplicacoes web", "html, css, javascript", 21, 200.0, ct, c);
        ListaTarefas instance = new ListaTarefas();
        boolean expResult = true;
        boolean result = instance.addTarefa(oTarefa);
        assertEquals(expResult, result);
    }

    /**
     * Test of validaTarefa method, of class ListaTarefas.
     */
    @Test
    public void testValidaTarefa() {
        System.out.println("validaTarefa");
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        HashMap<CompetenciaTecnica, Boolean> map = new HashMap<>();
        map.put(c1, true);
        CategoriaTarefa ct = new CategoriaTarefa("Webdev", a, map);
        Colaborador c = new Colaborador("Jose", "918273645", "jose@mindera.pt");
        Tarefa oTarefa = new Tarefa("1", "Frontend development", "Desenvolvimento de aplicacoes web", "html, css, javascript", 21, 200.0, ct, c);
        ListaTarefas instance = new ListaTarefas();
        boolean expResult = true;
        boolean result = instance.validaTarefa(oTarefa);
        assertEquals(expResult, result);
    }

    /**
     * Test of getTarefaByRef method, of class ListaTarefas.
     */
    @Test
    public void testGetTarefaByRef() {
        System.out.println("getTarefaByRef");
        String strRef = "1";
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Javascript");
        CompetenciaTecnica c1 = new CompetenciaTecnica("1", "Javascript", "Desenvolvimento aplicacoes em javascript usando react.js", a);
        HashMap<CompetenciaTecnica, Boolean> map = new HashMap<>();
        map.put(c1, true);
        CategoriaTarefa ct = new CategoriaTarefa("Webdev", a, map);
        Colaborador c = new Colaborador("Jose", "918273645", "jose@mindera.pt");
        Tarefa expResult = new Tarefa("1", "Frontend development", "Desenvolvimento de aplicacoes web", "html, css, javascript", 21, 200.0, ct, c);        
        ListaTarefas instance = new ListaTarefas();
        instance.addTarefa(expResult);
        Tarefa result = instance.getTarefaByRef(strRef);
        assertEquals(expResult, result);
    }
    
}
