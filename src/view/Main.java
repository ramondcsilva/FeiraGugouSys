package view;

import controller.SeachController;
import java.io.IOException;
//import util.Console;
//import model.*;

public class Main {
    public static void main(String[] args) throws IOException {
        SeachController.carregaArquivos();
        //System.out.println("Digite uma palavra");
        //String k = Console.readString();
        //Pagina p = new Pagina(k);
        SeachController.getRank().getPaginas().printTree();
    }
}

//Renomear arquivos, reformatar os arquivos.