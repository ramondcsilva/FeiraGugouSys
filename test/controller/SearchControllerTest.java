/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Palavra;
import model.Ranking;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ramon
 */
public class SearchControllerTest {
    
    public SearchControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of carregaArquivos method, of class SearchController.
     */
    @Test
    public void testCarregaArquivos() {
        System.out.println("carregaArquivos");
        SearchController.carregaArquivos();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of guardaPalavras method, of class SearchController.
     */
    @Test
    public void testGuardaPalavras() {
        System.out.println("guardaPalavras");
        String diretorio = "";
        SearchController.guardaPalavras(diretorio);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of imprimeTxt method, of class SearchController.
     */
    @Test
    public void testImprimeTxt() throws Exception {
        System.out.println("imprimeTxt");
        String diretorio = "";
        String expResult = "";
        String result = SearchController.imprimeTxt(diretorio);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of search method, of class SearchController.
     */
    @Test
    public void testSearch() {
        System.out.println("search");
        Palavra p = null;
        Palavra expResult = null;
        Palavra result = SearchController.search(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRank method, of class SearchController.
     */
    @Test
    public void testGetRank() {
        System.out.println("getRank");
        Ranking expResult = null;
        Ranking result = SearchController.getRank();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of replacePalavra method, of class SearchController.
     */
    @Test
    public void testReplacePalavra() {
        System.out.println("replacePalavra");
        Palavra p = null;
        String expResult = "";
        String result = SearchController.replacePalavra(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvarArq method, of class SearchController.
     */
    @Test
    public void testSalvarArq() throws Exception {
        System.out.println("salvarArq");
        String dir = "";
        String text = "";
        SearchController.salvarArq(dir, text);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
