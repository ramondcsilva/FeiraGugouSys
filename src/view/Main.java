package view;

import controller.SeachController;
import java.io.IOException;
import util.Console;


public class Main {
    public static void main(String[] args) throws IOException {
        SeachController.carregaArquivos();
        SeachController.imprimePaginas();
        int j = 0;
        
        while(true){            
            System.out.print("\nDigite o numero do texto que voce quer imprimir: ");
            int i = Console.readInt();
            SeachController.imprimePalavra(i);
        }
    }
}

//Renomear arquivos, reformatar os arquivos.