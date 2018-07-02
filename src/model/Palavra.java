package model;

import java.util.LinkedList;

public class Palavra implements Comparable {

    private String word;
    private LinkedList paginas;
    private int relevancia;

    public Palavra(){
        this.word = "";
        this.relevancia = 0;
        paginas = new LinkedList();
    }

    public Palavra(String word) {
        this.word = word;
        this.relevancia = 0;
        paginas = new LinkedList();
    }
    
    public Palavra(String word, int relevancia){
        this.word = word;
        this.relevancia = relevancia;
        paginas = new LinkedList();
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getRelevancia() {
        return relevancia;
    }

    public void setRelevancia(int one) {
        this.relevancia = relevancia + one;
    }

    public LinkedList getPaginas() {
        return paginas;
    }

    public void setPaginas(LinkedList paginas) {
        this.paginas = paginas;
    }

    public void addPaginas(Pagina p) {
        if (p != null) {
            paginas.addLast(p);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return (this.word.equals(((Palavra) obj).getWord()));
    }

    @Override
    public String toString() {
        return word;
    }

    @Override
    public int compareTo(Object t) {
        return (this.word.compareTo(((Palavra) t).getWord()));
    }
}
