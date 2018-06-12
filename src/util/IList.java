package util;

public interface IList{
    public boolean isEmpty();
    
    public boolean contains(Object data);
    
    public int size();

    public void addStart(Object o);

    public void addLast(Object o);
    
    public Object toRemoveStart();
    
    public Object toRemoveLast();
    
    public boolean remove(Object data);

    public Object get(int index);
    
    public Object[] toArray();
            
    public Iterator iterator();
	
}
