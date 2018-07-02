package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.LinkedList;

public class PalavraTest {
    private Palavra p;
    
    @Before
    public void setUp() throws Exception {
        p = new Palavra("word", 5);
    }

    @Test
    public void testBasic() {
        
        assertTrue(p.getPaginas().isEmpty());
        assertEquals(p.getPaginas().size(), 0);
        assertEquals(p.getRelevancia(),5);
        assertEquals(p.getWord(),"word");
        
        Pagina p0 = new Pagina("p0",5,5);
        Pagina p1 = new Pagina("p1",4,4);
        Pagina p2 = new Pagina("p2",4,3);
        Pagina p3 = new Pagina("p3",2,2);

        
        p.getPaginas().add(p0);
        p.getPaginas().add(p1);
        p.getPaginas().add(p2);
        
        assertEquals(p0, p.getPaginas().get(0));
        assertEquals(p1, p.getPaginas().get(1));
        assertEquals(p2, p.getPaginas().get(2));
        
        assertTrue(p.getPaginas().contains(p2));
        assertFalse(p.getPaginas().contains(p3));
        assertTrue(p.getPaginas().contains(p0));
        assertTrue(p.getPaginas().contains(p1));
        
        LinkedList list = new LinkedList();
        list.add(p0);
        list.add(p1);
        list.add(p2);

        Palavra temp = new Palavra("word",5);
        temp.setPaginas(list);
        p.setPaginas(list);
        
        assertTrue(p.getPaginas().equals(temp.getPaginas()));
        assertEquals(temp.getPaginas().size(), 3);

        assertEquals(p0, temp.getPaginas().get(0));
        assertEquals(p1, temp.getPaginas().get(1));
        assertEquals(p2, temp.getPaginas().get(2));

        LinkedList n = new LinkedList();

        temp.setPaginas(n);
        assertTrue(temp.getPaginas().isEmpty());
        temp.getPaginas().add(p0);
        assertFalse(temp.getPaginas().isEmpty());
        assertEquals(temp.getPaginas().size(), 1);
        
    }
}