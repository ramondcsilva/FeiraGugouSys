package util;

public class LinkedList implements IList{
    private Node head;
    private Node tail;
    private int sizeList;
    
    private class Node{
        private Object data;
        private Node next;

        public Node(Object data){
            this.data = data;
        }
        public Node getNext(){
            return next;
        }
        public void setNext(Node next){
            this.next = next;
        }
        public Object getData(){
            return data;
        }
        public void setData(Object data){
            this.data = data;
        }
    }
    /**
     * Verifica se a LinkedList contém um dado específico.
     * @param data
     * @return Boolean.
     */
    @Override
    public boolean contains(Object data){
       for(Node n = head;n != null; n = n.getNext()){
           if(n.getData() != null && n.getData().equals(data)){
               return true;
           }
       }
       return false;
    }
    /**
     * Verifica se a LinkedList está vazia.
     * @return Boolean.
     */
    @Override
    public boolean isEmpty(){
       return head == null;
    }
    /**
     * Calcula o tamanho da LinkedList.
     * @return tamanho da LinkedList.
     */
    @Override
    public int size(){
        return sizeList;
    }
    /**
     * Adiciona um node no inicio da LinkedList.
     * @param data 
     */
    @Override
    public void addStart(Object data){
        if (isEmpty()){
            head = new Node(data);
            tail = head;
        }else{
            Node n = new Node(data);
            n.setNext(head);
            head = n;
        }
        sizeList++;
    }
    /**
     * Adiciona um node no final da LinkedList.
     * @param data 
     */
    @Override
    public void addLast(Object data){
        if(isEmpty()){
            head = new Node(data);
            tail = head;
        }else{
            Node n = new Node(data);
            tail.setNext(n);
            tail = n;
        }
        sizeList++;
    }
    /**
     * Remove um node do inicio da LinkedList.
     * @return dado removido.
     */
    @Override
    public Object toRemoveStart(){
        if(isEmpty()){
            return null;
        }else{
            Node n;
            n = head;
            head = head.getNext();
        sizeList--;
        return n.getData();
        }
    }
    /**
     * Remove um dado do final da LinkedList.
     * @return dado removido.
     */
    @Override
    public Object toRemoveLast(){   
        Node aux;
        aux = head;
        
        if(isEmpty()){
            return null;
        }if(size() == 1){
            sizeList--;
            Object data = head.getData();
            head = tail = null;
            return data;
        }else{
            sizeList--;
            for (Node n = head; n.getNext() != null; n = n.getNext()) {
                if(n.getNext().getNext() == null){
                   tail = n; 
                }
                aux = n;
            }
            Object data = aux.getNext().getData();
            tail.setNext(null);
            return data;
        }
    }
    /**
     * Remove um dado específico da LinkedList.
     * @param data
     * @return Boolean.
     */
    @Override
    public boolean remove(Object data){
        if(isEmpty()){
            return false;
        }else if(size() == 1){
            head = tail = null;
            sizeList--;
            return true;        
        }else if(!isEmpty()){
            for(Node n = head; n.getNext() != null; n = n.getNext()){
                Node prox = n.getNext();
                if(data.equals(prox.getData())){
                    n.setNext(prox.getNext());
                    sizeList--;
                    return true;
                }else if(n.getData().equals(data)){
                    head = head.getNext();
                    sizeList--;
                    return true;
                }
            }
        }
        return false;
    }
    /**
     *Procura um dado a partir de um index na LinkedList.
     * @param index
     * @return dado encontrado.
     */
    @Override
    public Object get(int index){
        if(index >= 0 && index < size()){
            Node aux = head;
            for(int i = 0 ; i < index; i++){
                aux = aux.getNext();
            }
            if(aux != null){
                Object data = aux.getData();
                return data;
            }
        }
        return null;
    }
    
    /**
     * Metodo para criar um array com os objetos atuais de uma lista.
     * Cria um novo array com o tamanho da lista no momento da invocação desse método
     * e referencia cada objeto da lista neste array recem criado.
     * @return array de objetos da lista.
     */
    @Override
    public Object[] toArray(){
        Object[] ret = new Object[sizeList];
        int i=0;
        
        for (Iterator it = iterator(); it.hasNext();) {
            ret[i] = it.next();
            i++;
        }
        return ret;
    }
    /**
     * Cria o iterator da LinkedList.
     * @return iterator da LinkedList. 
     */
    @Override
    public Iterator iterator(){
        ListIterator iteratorlist = new ListIterator(this.head);
        return iteratorlist;
    }
    
    private class ListIterator  implements Iterator{
        private Node current;

        ListIterator(Node head){
            current = head;
        }
        
        /**
         * Verifica se tem um proximo node na LinkedList.
         * @return Boolean. 
         */
        @Override
        public boolean hasNext(){
            return  current != null;
        }
        /**
         * Pega o dado do proximo node na LinkedList.
         * @return dado sucessor.
         */
        @Override
        public Object next(){
            Object data = current.getData();
            current = current.getNext();
            return data;
        }
    }
}
