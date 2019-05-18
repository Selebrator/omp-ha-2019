package _5._2.car;

import java.util.Collection;

/*
 * Ein Interface hat keinen state.
 * Es kann also auch keine Anforderungen an seinen state stellen.
 * Um alle geforderten Multiplizitäten sicherzustellen müsste man
 * sich daher auf jeden der dies implementiert verlassen,
 * dass er sich an die Vereinbarung hällt.
 */
public interface CarComponent {
	String getName();

	Collection<CarComponent> getComponents();
}
