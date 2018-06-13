package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import model.*;
import util.*;

public class SeachController {
    private static Ranking rank = new Ranking();
    
    public SeachController(){
    }
    
    public static void carregaArquivos(){
        File file = new File("C:\\Users\\ramon\\Downloads\\JAVA\\MI Programacao\\PBL3\\FeiraGugouSys\\src\\view\\txt");
        File afile[] = file.listFiles();
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
        rank.addPaginas(pag);
        System.out.println(pag);
        
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
                            pag.addPalavra(p);
                            System.out.print(p+" ");
                        }
                    }
                }    
            }catch(IOException eEx){
                System.out.println("Houve erros!: "+eEx);
            }
        }
    }
    
    public static void imprimePaginas(){
        Iterator iterator = rank.getPaginas().iterator();
        while(iterator.hasNext()){
            Pagina p = (Pagina) iterator.next();
            System.out.println(p);
        }
    }
    
    public static void imprimeTexto(int index){
        Pagina p = (Pagina)rank.getPaginas().get(index);
        Iterator i =  p.getPalavras().iterator();
        int j = 0;
        System.out.println(p);
        while(i.hasNext()){
            Palavra word = (Palavra) i.next();
            if(j%12==0){
                System.out.println(word);
            }else{
                System.out.print(word);
            }
            j++;
        }
    }
    
    public static void imprimePalavra(int index){
        Pagina p = (Pagina)rank.getPaginas().get(index);
        System.out.print(p.getPalavras().get(index));
    }
    public static Ranking getRank() {
        return rank;
    }
}