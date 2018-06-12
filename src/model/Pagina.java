package model;

import util.LinkedList;

public class Pagina {
    private String nome;
    private LinkedList palavras;
    private int relevancia;
    
    public Pagina(String nome){
        this.nome = nome;
        palavras = new LinkedList();
        relevancia  = 0;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public void addPalavra(Palavra word){
        if(word != null){
            palavras.addLast(word);
        }
    }
    
    public void removePalavra(Palavra word){
        if(word != null){
            palavras.remove(word);
        }
    }
    
    public LinkedList getPalavras() {
        return palavras;
    }

    public void setPalavras(LinkedList palavras) {
        this.palavras = palavras;
    }

    public int getRelevancia() {
        return relevancia;
    }

    public void setRelevancia(int relevance) {
        this.relevancia = relevancia + relevance;
    }
    
    public boolean equals(Object obj){
        if(obj instanceof Pagina){
            Pagina pag = (Pagina) obj;
            if(this.getNome().equals(pag.getNome())){
                return true;
            }
            if(this.getPalavras().equals(pag.getPalavras())){
                return true;
            }
            if(this.getRelevancia() == pag.getRelevancia()){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return nome+" ";
    }
}