package _2._2;

import java.util.List;

public class StandardDance extends Dance {
	public StandardDance(String name, String beat) {
		super(name, beat);
	}

	public StandardDance(String name, String beat, List<Figure> figures) {
		super(name, beat, figures);
	}
}
