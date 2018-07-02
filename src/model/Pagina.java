package model;

import java.util.Objects;

public class Pagina implements Comparable{
    private String nome;
    private int relevanciaPalavras;
    private int visitas;
    
    public Pagina(){
    }

    public Pagina(String nome){
        this.nome = nome;
        relevanciaPalavras  = 0;
        visitas = 0;
    }
    
    public Pagina(String nome, int relevanciaPalavras, int visitas){
        this.nome = nome;
        this.relevanciaPalavras  = relevanciaPalavras;
        this.visitas = visitas;
    }
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getRelevanciaPalavras() {
        return relevanciaPalavras;
    }

    public void setRelevanciaPalavras(int relevance) {
        this.relevanciaPalavras = relevanciaPalavras + relevance;
    }

    public int getVisitas() {
        return visitas;
    }

    public void setVisitas(int visit) {
        this.visitas = visitas + visit;
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
        if (this.relevanciaPalavras != other.relevanciaPalavras) {
            return false;
        }
        if (this.visitas != other.visitas) {
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