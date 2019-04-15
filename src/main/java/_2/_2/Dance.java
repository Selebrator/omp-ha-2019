package _2._2;

import java.util.ArrayList;
import java.util.List;

public class Dance {
	private String name;
	private String beat;
	private List<Figure> figures;

	public Dance(String name, String beat) {
		this.name = name;
		this.beat = beat;
		this.figures = new ArrayList<>();
	}

	public Dance(String name, String beat, List<Figure> figures) {
		this.name = name;
		this.beat = beat;
		this.figures = figures;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBeat() {
		return this.beat;
	}

	public void setBeat(String beat) {
		this.beat = beat;
	}

	public List<Figure> getFigures() {
		return this.figures;
	}

	public void setFigures(List<Figure> figures) {
		this.figures = figures;
	}

//TODO remove
//	@Override
//	public String toString() {
//		final StringBuilder sb = new StringBuilder(this.getClass().getSimpleName());
//		sb.append(" ");
//		sb.append(name);
//		sb.append(" (").append(beat).append(") ");
//		sb.append(figures);
//		return sb.toString();
//	}
}
