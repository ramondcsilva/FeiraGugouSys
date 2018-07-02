package All;

import controller.*;
import model.*;
import util.*;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    SearchControllerTest.class,
    PaginaTest.class,
    PalavraTest.class,
    RankingTest.class,
    TreeAVLTest.class,
})
public class AllTests {
    
}
