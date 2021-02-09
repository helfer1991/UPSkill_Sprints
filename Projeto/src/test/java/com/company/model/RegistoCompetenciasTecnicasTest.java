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
public class RegistoCompetenciasTecnicasTest {

    RegistoCompetenciasTecnicas instance;
    AreaAtividade a1;
    CompetenciaTecnica c1;
    CompetenciaTecnica c2;
    CompetenciaTecnica c3;

    public RegistoCompetenciasTecnicasTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
        instance = new RegistoCompetenciasTecnicas();
        a1 = new AreaAtividade("1", "Frontend", "Frontend development");
        c1 = new CompetenciaTecnica("1", "Javascript", "Javascript", a1);
        c2 = new CompetenciaTecnica("2", "Java", "Java", a1);
        c3 = new CompetenciaTecnica("3", "Ruby", "Ruby", a1);
        instance.registaCompetenciaTecnica(c2);
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of novaCompetenciaTecnica method, of class
     * RegistoCompetenciasTecnicas.
     */
    @Test
    public void testNovaCompetenciaTecnica_4args_1() {
        System.out.println("novaCompetenciaTecnica");
        AreaAtividade a1 = new AreaAtividade("1", "Frontend", "Frontend development");
        String id = "1";
        String descBreve = "Javascript";
        String descDetalhada = "Javascript";
        RegistoCompetenciasTecnicas instance = new RegistoCompetenciasTecnicas();
        CompetenciaTecnica expResult = new CompetenciaTecnica("1", "Javascript", "Javascript", a1);
        CompetenciaTecnica result = instance.novaCompetenciaTecnica(id, descBreve, descDetalhada, a1);
        assertEquals(expResult, result);
    }

    /**
     * Test of registaCompetenciaTecnica method, of class
     * RegistoCompetenciasTecnicas.
     */
    @Test
    public void testRegistaCompetenciaTecnica() {
        System.out.println("registaCompetenciaTecnica");
        a1 = new AreaAtividade("1", "Frontend", "Frontend development");
        c1 = new CompetenciaTecnica("1", "Javascript", "Javascript", a1);
        RegistoCompetenciasTecnicas instance = new RegistoCompetenciasTecnicas();
        boolean expResult = true;
        boolean result = instance.registaCompetenciaTecnica(c1);
        assertEquals(expResult, result);
    }

    /**
     * Test of addCompetenciaTecnica method, of class
     * RegistoCompetenciasTecnicas.
     */
    @Test
    public void testAddCompetenciaTecnica() {
        System.out.println("addCompetenciaTecnica");
        RegistoCompetenciasTecnicas instance = new RegistoCompetenciasTecnicas();
        boolean arr[] = new boolean[3];
        a1 = new AreaAtividade("1", "Frontend", "Frontend development");
        c1 = new CompetenciaTecnica("1", "Javascript", "Javascript", a1);
        c2 = new CompetenciaTecnica("2", "Java", "Java", a1);
        c3 = new CompetenciaTecnica("3", "Ruby", "Ruby", a1);
        arr[0] = instance.addCompetenciaTecnica(c1);
        arr[1] = instance.addCompetenciaTecnica(c2);
        arr[2] = instance.addCompetenciaTecnica(c3);
        int expRes = 3;
        int result = 0;
        for (int i = 0; i < 3; i++) {
            if (arr[i] == true) {
                result++;
            }
        }
        assertEquals(expRes, result);
    }

    /**
     * Test of validaCompetenciaTecnica method, of class
     * RegistoCompetenciasTecnicas.
     */
    @Test
    public void testValidaCompetenciaTecnica() {
        System.out.println("validaCompetenciaTecnica");
        a1 = new AreaAtividade("1", "Frontend", "Frontend development");
        c1 = new CompetenciaTecnica("1", "Javascript", "Javascript", a1);
        RegistoCompetenciasTecnicas instance = new RegistoCompetenciasTecnicas();
        boolean expResult = true;
        boolean result = instance.validaCompetenciaTecnica(c1);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompetenciaTecnicaById method, of class
     * RegistoCompetenciasTecnicas.
     */
    @Test
    public void testGetCompetenciaTecnicaById() {
        System.out.println("getCompetenciaTecnicaById");
        String strId = "CompTec-1";
        RegistoCompetenciasTecnicas instance = new RegistoCompetenciasTecnicas();
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Frontend development");
        CompetenciaTecnica c = new CompetenciaTecnica("1", "Javascript", "Javascript", a);
        instance.addCompetenciaTecnica(c);
        CompetenciaTecnica expResult = c;
        CompetenciaTecnica result = instance.getCompetenciaTecnicaById(strId);
        assertEquals(expResult, result);
    }

    /**
     * Test of getCompetenciaTecnicaByDescricao method, of class
     * RegistoCompetenciasTecnicas.
     */
    @Test
    public void testGetCompetenciaTecnicaByDescricao() {
        System.out.println("getCompetenciaTecnicaByDescricao");
        String strDescricao = "Java";
        RegistoCompetenciasTecnicas instance = new RegistoCompetenciasTecnicas();
        AreaAtividade a = new AreaAtividade("1", "Frontend", "Frontend development");
        CompetenciaTecnica c = new CompetenciaTecnica("2", "Java", "Java", a);
        instance.addCompetenciaTecnica(c);
        CompetenciaTecnica expResult = c;
        CompetenciaTecnica result = instance.getCompetenciaTecnicaByDescricao(strDescricao);
        assertEquals(expResult, result);
    }

}
