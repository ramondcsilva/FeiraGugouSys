package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import model.*;

public class SearchController {
    private static Ranking rank = new Ranking();

    public SearchController() {
    }
    /**
     * Carrega uma lista de arquivos de um diretorio.
     */
    public static void carregaArquivos() {
        FileFilter filter = (File file) -> file.getName().endsWith(".txt");

        File file = new File("src/view/txt");
        File afile[] = file.listFiles(filter);
        int i = 0;
        for (int j = afile.length; i < j; i++) {
            File arquivos = afile[i];
            guardaPalavras(arquivos.getName());
        }
    }
    /**
     *Guarda as palavras de um arquivo;
     * @param diretorio 
     */
    public static void guardaPalavras(String diretorio) {
        File fArq = new File("src/view/txt/" + diretorio);
        String linhaAtual = " ";
        Pagina pag = new Pagina(diretorio);

        if (fArq.exists()) {
            try {
                FileReader fReader = new FileReader("src/view/txt/" + diretorio);
                BufferedReader rLeitor = new BufferedReader(fReader);

                while ((linhaAtual = rLeitor.readLine()) != null) {
                    if (linhaAtual.contains(" ")) {
                        while (linhaAtual.contains(" ")) {
                            Palavra p = new Palavra(linhaAtual.substring(0, linhaAtual.indexOf(" ")));
                            linhaAtual = linhaAtual.substring(linhaAtual.indexOf(" ") + 1, linhaAtual.length());
                            p.setWord(replacePalavra(p));
                            if (!rank.getPalavras().contem((Comparable) p)) {
                                if (!p.getPaginas().contains(pag)) {
                                    p.addPaginas(pag);
                                } else {
                                    int j = p.getPaginas().indexOf(p);
                                    Pagina pag1 = (Pagina) p.getPaginas().get(j);
                                    pag1.setRelevanciaPalavras(1);
                                }
                                rank.addPalavras(p);
                            } else {
                                Palavra n = (Palavra) rank.getPalavras().buscarObj((Comparable) p);
                                n.addPaginas(pag);
                            }
                        }
                    }
                }
            } catch (IOException eEx) {
                System.out.println("Houve erros!: " + eEx);
            }
        }
    }
    /**
     * Imprime o arquivo txt corretamente na tableArea.
     * @param diretorio
     * @return  retornoTexto
     * @throws IOException 
     */
    public static String imprimeTxt(String diretorio) throws IOException {
        File fArq = new File("src/view/txt/" + diretorio);
        String linhaAtual = "";
        String retornoTexto = "";

        if (fArq.exists()) {
            FileReader fReader = new FileReader("src/view/txt/" + diretorio);
            BufferedReader rLeitor = new BufferedReader(fReader);

            while ((linhaAtual = rLeitor.readLine()) != null) {
                retornoTexto += linhaAtual + "\n";
            }
        }
        return retornoTexto;
    }
    /**
     * Procura uma palavra na TreeAVL, aumentando a relevancia a cada busca. 
     * @param p
     * @return palavraRetornada
     */
    public static Palavra search(Palavra p) {
        Palavra palavraRetornada = (Palavra) rank.getPalavras().buscarObj(p);
        if (palavraRetornada != null) {
            palavraRetornada.setRelevancia(1);
        }
        return palavraRetornada;
    }
    /**
     * Pega o Objeto Ranking.
     * @return rank
     */
    public static Ranking getRank() {
        return rank;
    }
    /**
     * Refatora o nome da palavra de acordo com os simbolos encontrados.
     * @param p
     * @return String
     */
    public static String replacePalavra(Palavra p) {
        String text = p.getWord();
        return text.replaceAll("[^A-Za-z0-9_-áàâãéèêíìóôõöúçñÁÀÂÃÉÈÊÍÌÓÔÕÖÚÇÑ\\s]", "");
    }
    /**
     * Salva uma String em um arquivo.
     * @param dir
     * @param text
     * @throws IOException 
     */
    public static void salvarArq(String dir,String text) throws IOException{
        FileWriter arqSaida = new FileWriter(dir);
        BufferedWriter arquivo = new BufferedWriter(arqSaida);
        
        arquivo.write("");
        arquivo.flush();
        arquivo.close();
        
        arqSaida = new FileWriter(dir,true);
        arquivo = new BufferedWriter(arqSaida);
       
        arquivo.write(text);
        arquivo.flush();
        arquivo.close();
    }
}    