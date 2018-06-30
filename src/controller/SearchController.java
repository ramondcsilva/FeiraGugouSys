package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.IOException;
import model.*;

public class SearchController {
    private static Ranking rank = new Ranking();
    
    public SearchController(){
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
        File fArq = new File("src/view/txt/"+diretorio);
        String linhaAtual = " ";
        int id = 0;
        Pagina pag = new Pagina(diretorio);
        
        if(fArq.exists()){
            try{
                FileReader fReader = new FileReader("src/view/txt/"+diretorio);
                BufferedReader rLeitor = new BufferedReader(fReader);
                
                while((linhaAtual=rLeitor.readLine())!= null){
                    if(linhaAtual.contains(" ")){
                        while(linhaAtual.contains(" ")){
                            //[^A-Za-z0_-áàâãéèêíìóôõöúçñÁÀÂÃÉÈÊÍÌÓÔÕÖÚÇÑ\\s]
                            Palavra p = new Palavra(linhaAtual.substring(0,linhaAtual.indexOf(" ")));
                            linhaAtual = linhaAtual.substring(linhaAtual.indexOf(" ")+1,linhaAtual.length());
                            p.setId(id++);
                            p.setWord(replacePalavra(p));
                            if(!rank.getPalavras().contem((Comparable)p)){
                                if(!p.getPaginas().contains(pag)){
                                    p.addPaginas(pag);
                                }else{
                                    int j = p.getPaginas().indexOf(p);
                                    Pagina pag1 = (Pagina)p.getPaginas().get(j);
                                    pag1.setRelevancia(1);
                                }
                                rank.addPalavras(p);
                            }else{
                                Palavra n = (Palavra)rank.getPalavras().buscarObj((Comparable)p);
                                n.addPaginas(pag);
                            }
                        }
                    }
                }    
            }catch(IOException eEx){
                System.out.println("Houve erros!: "+eEx);
            }
        }
    }
    
    public static String imprimeTxt(String diretorio) throws IOException{
        File fArq = new File("src/view/txt/"+diretorio);
        String linhaAtual = "";
        String retornoTexto = "";
        
        if(fArq.exists()){
            FileReader fReader = new FileReader("src/view/txt/"+diretorio);
            BufferedReader rLeitor = new BufferedReader(fReader);
            
            while((linhaAtual=rLeitor.readLine())!= null){
                retornoTexto += linhaAtual+"\n";
            }    
        }
        return retornoTexto;
    }
    
    public static Palavra search(Palavra p){
        Palavra palavraRetornada = (Palavra)rank.getPalavras().buscarObj(p);
        if(palavraRetornada != null){
            palavraRetornada.setRelevancia(1);
        }
        return palavraRetornada;
    }
    
    public static Ranking getRank() {
        return rank;
    }
    public static String replacePalavra(Palavra p){
        String text = p.getWord();
        return text.replaceAll("[^A-Za-z0-9_-áàâãéèêíìóôõöúçñÁÀÂÃÉÈÊÍÌÓÔÕÖÚÇÑ\\s]", "");
    }
}