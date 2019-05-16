package _6._1;

import java.util.HashMap;
import java.util.Iterator;

public class HashMapMultiSet<T> implements MultiSet<T> {
    private HashMap<T,Integer> map = new HashMap<>();

    public Iterator<T> iterator() {
        return new HashMapMultiSetIterator();
    }

    public void add(T element) {
        if (map.containsKey(element)) {
            map.put(element,map.get(element)+1);
        } else {
            map.put(element,1);
        }
    }

    public int count(T element) {
        if (map.containsKey(element)) {
            return map.get(element);
        }
        return 0;
    }

    class HashMapMultiSetIterator implements Iterator<T> {
        @Override
        public boolean hasNext() {
            return map.keySet().iterator().hasNext();
        }

        @Override
        public T next() {
            return map.keySet().iterator().next();
        }

    }

}
