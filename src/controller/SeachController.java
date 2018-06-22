package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import model.*;

public class SeachController {
    private static Ranking rank = new Ranking();
    
    public SeachController(){
    }
    
    public static void carregaArquivos(){
        FileFilter filter = (File file) -> file.getName().endsWith(".txt");
        
        File file = new File("C:\\Users\\ramon\\Downloads\\JAVA\\MI Programacao\\PBL3\\FeiraGugouSys\\src\\view\\txt");
        File afile[] = file.listFiles(filter);
        int i = 0;
        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];
            guardaPalavras(arquivos.getName());
        }
    } 
    
    public static void guardaPalavras(String diretorio){
        File fArq = new File("C:\\Users\\ramon\\Downloads\\JAVA\\MI Programacao\\PBL3\\FeiraGugouSys\\src\\view\\txt\\"+diretorio);
        String linhaAtual = " ";
        int id = 0;
        Pagina pag = new Pagina(diretorio);
        
        if(fArq.exists()){
            try{
                FileReader fReader = new FileReader("C:\\Users\\ramon\\Downloads\\JAVA\\MI Programacao\\PBL3\\FeiraGugouSys\\src\\view\\txt\\"+diretorio);
                BufferedReader rLeitor = new BufferedReader(fReader);
                
                while((linhaAtual=rLeitor.readLine())!= null){
                    if(linhaAtual.contains(" ")){
                        while(linhaAtual.contains(" ")){
                            Palavra p = new Palavra(linhaAtual.substring(0,linhaAtual.indexOf(" ")));
                            linhaAtual = linhaAtual.substring(linhaAtual.indexOf(" ")+1,linhaAtual.length());
                            p.setId(id++);
                            if(!rank.getPalavras().contem((Comparable)p)){
                                rank.addPalavras(p);
                                p.addPaginas(pag);
                            }
                            //System.out.print(p+" ");
                        }
                    }
                }    
            }catch(IOException eEx){
                System.out.println("Houve erros!: "+eEx);
            }
        }
    }
    
    //Verificar cast palavra e pagina para imprimir;
    public static void imprimeTexto(String palavra){
        rank.getPalavras().printTree();
    }
    
    public static void imprimePalavra(int index){
    }
    
    public static Ranking getRank() {
        return rank;
    }
}