package model;

import java.util.LinkedList;
import java.util.Objects;

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
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Palavra other = (Palavra) obj;
        if (this.relevancia != other.relevancia) {
            return false;
        }
        if (!Objects.equals(this.word, other.word)) {
            return false;
        }
        if (!Objects.equals(this.paginas, other.paginas)) {
            return false;
        }
        return true;
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
