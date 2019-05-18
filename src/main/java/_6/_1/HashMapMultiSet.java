package _6._1;

import java.util.*;

public class HashMapMultiSet<T> implements MultiSet<T> {
    private Map<T,Integer> map = new HashMap<>();

    @Override
    public Iterator<T> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public void add(T element) {
            map.put(element,count(element)+1);
    }

    @Override
    public int count(T element) {
        if (map.containsKey(element)) {
            return map.get(element);
        }
        return 0;
    }
}
