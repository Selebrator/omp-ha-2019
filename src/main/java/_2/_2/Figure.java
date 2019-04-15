package _2._2;

import java.util.ArrayList;
import java.util.List;

/**
 * A Figure is described by either Text or (exclusive) a sequence of other Figures.
 */
public class Figure {
	private String name;
	private String description;
	private List<Figure> parts;

	public Figure(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public Figure(String name, List<Figure> parts) {
		this.name = name;
		this.parts = new ArrayList<>();
		for(Figure part : parts) {
			this.add(part);
		}
	}

	public boolean add(Figure figure) {
		if(figure == null || this.parts == null || this.contains(figure)) {
			return false;
		}
		return this.parts.add(figure);
	}

	private boolean contains(Figure figure) {
		if(this.parts == null) {
			return false;
		}
		if(this.parts.contains(figure)) {
			return true;
		}
		for(Figure part : this.parts) {
			if(part.parts == null) {
				continue;
			}
			if(part.equals(figure) || part.contains(figure)) {
				return true;
			}
		}
		return false;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		if(this.parts != null) {
			throw new IllegalStateException("This figure is already described as a sequence of other figures.");
		}
		this.description = description;
	}

	public List<Figure> getParts() {
		return this.parts;
	}

	public void setParts(List<Figure> parts) {
		if(this.description != null) {
			throw new IllegalStateException("This figure is already described by text.");
		}
		this.parts = parts;
	}

//TODO remove
//	@Override
//	public String toString() {
//		final StringBuilder sb = new StringBuilder();
//		sb.append(name);
//		if(parts != null) {
//			sb.append(": ").append(parts);
//		}
//		return sb.toString();
//	}
}
