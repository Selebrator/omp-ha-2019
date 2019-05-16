package _6._1;

/* a) REGENWETTER
* Menge: {R,E,G,N,W,T}
* Multimenge: {(R,2),(E,4),(G,1),(N,1),(W,1),(T,2)}
*/

public interface MultiSet<T> extends Iterable<T>{
    void add(T element);

    int count(T element);
}


