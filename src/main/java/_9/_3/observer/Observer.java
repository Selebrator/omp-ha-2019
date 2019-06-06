package _9._3.observer;

public interface Observer<Who extends Observable<Who, What>, What> {
	void update(Who who, What what);
}
