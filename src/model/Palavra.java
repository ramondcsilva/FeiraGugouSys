package model;

public class Palavra {
    private String word;
    
    public Palavra(String word) {
        this.word = word;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Palavra){
            Palavra palavra = (Palavra)obj;
            if(word.equals(palavra.getWord())){
                return true;
            }
        }
        return false;
    }
}