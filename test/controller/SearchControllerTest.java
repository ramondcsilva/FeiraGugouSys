package controller;

import model.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class SearchControllerTest {
    private Ranking rank = new Ranking();
    SearchController controller = new SearchController();
    
    @Test
    public void testSearch() {
        Palavra palavra = new Palavra("oi");
        rank.getPalavras().insert(palavra);
        Palavra retorno = (Palavra) rank.getPalavras().buscarObj(palavra);
        assertEquals(palavra,retorno);
    }

    @Test
    public void testReplacePalavra() {
        Palavra test = new Palavra("2342asfa***7mdafks");
        Palavra nova = new Palavra(SearchController.replacePalavra(test));
        Palavra igual = new Palavra("2342asfa7mdafks");
        assertFalse(test.equals(nova));
        assertTrue(igual.equals(nova));
    }
}