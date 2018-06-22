package model;

public class Pagina implements Comparable{
    private String nome;
    private int relevancia;
    
    public Pagina(String nome){
        this.nome = nome;
        relevancia  = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getRelevancia() {
        return relevancia;
    }

    public void setRelevancia(int relevance) {
        this.relevancia = relevancia + relevance;
    }
    
    public boolean equals(Object obj){
        return (this.nome.equals(((Pagina) obj).getNome()));
    }

    @Override
    public String toString() {
        return nome+" ";
    }

    @Override
    public int compareTo(Object t) {
        return (this.nome.compareTo(((Pagina) t).getNome()));
    }
}