package model;

import java.util.Objects;

public class Pagina implements Comparable{
    private String nome;
    private int relevancia;
    
    public Pagina(){
    }

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
        final Pagina other = (Pagina) obj;
        if (this.relevancia != other.relevancia) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
    
    

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public int compareTo(Object t) {
        return (this.nome.compareTo(((Pagina) t).getNome()));
    }
}