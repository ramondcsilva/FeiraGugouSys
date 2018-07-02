package model;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class PaginaTest {
    private Pagina p;
    
    @Before
    public void setUp() throws Exception {
        p = new Pagina();
    }
    
    @Test
    public void testBasic() {
        p.setNome("pagina");
        p.setRelevanciaPalavras(2);
        p.setVisitas(6);
        
        assertEquals(2,p.getRelevanciaPalavras());
        assertEquals(6,p.getVisitas());
        assertEquals("pagina",p.getNome());
        
        p.setNome("word");
        p.setRelevanciaPalavras(3);
        p.setVisitas(5);
        
        assertEquals("word",p.getNome());
        assertEquals(5,p.getRelevanciaPalavras());
        assertEquals(11,p.getVisitas());
        
        Pagina temp = new Pagina();
        temp.setNome("word");
        temp.setRelevanciaPalavras(5);
        temp.setVisitas(11);
        assertTrue(temp.equals(p));
        
        temp.setRelevanciaPalavras(7);
        temp.setVisitas(0);
        assertFalse(temp.equals(p));
        
        temp.setRelevanciaPalavras(-7);
        temp.setVisitas(0);
        assertTrue(temp.equals(p));
    }
}