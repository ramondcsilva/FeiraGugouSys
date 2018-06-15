package model;

public class Palavra {
    private String word;
    private int id;
    private int relevancia;
    
    public Palavra(String word) {
        this.word = word;
        this.id = 0;
        this.relevancia = 0;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRelevancia() {
        return relevancia;
    }

    public void setRelevancia(int one) {
        one = 1;
        this.relevancia = relevancia + one;
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Palavra){
            Palavra palavra = (Palavra)obj;
            if(word.equals(palavra.getWord())){
                return true;
            }
            if(id == palavra.getId()){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return word+" ";
    }
}