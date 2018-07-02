package model;

import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import util.TreeAVL;

public class RankingTest {
    private Ranking r;
    
    @Before
    public void setUp() throws Exception {
        r = new Ranking();
    }

    @Test
    public void testBasic() {
        assertTrue(r.getPalavras().estaVazia());
        assertEquals(r.getPalavras().size(), 0);

        Palavra p0 = new Palavra("um");
        Palavra p1 = new Palavra("dois");
        Palavra p2 = new Palavra("três");
        
        r.addPalavras(p0);
        r.addPalavras(p1);
        
        assertFalse(r.getPalavras().estaVazia());
        assertEquals(r.getPalavras().size(), 2);
        assertTrue(r.getPalavras().contem(p0));
        assertTrue(r.getPalavras().contem(p1));
        assertFalse(r.getPalavras().contem(p2));
        
        TreeAVL temp = new TreeAVL();
        temp.insert(p0);
        temp.insert(p1);
        
        r.setPalavras(temp);
        assertFalse(temp.estaVazia());
        assertEquals(temp,r.getPalavras());
    }
}