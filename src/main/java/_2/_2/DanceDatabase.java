package _2._2;

import java.util.Arrays;

public class DanceDatabase {

	private static final Figure BASIC_MOVE   = new Figure("Basic Move",   "Lorem ipsum.");
	private static final Figure NATURAL_TURN = new Figure("Natural Turn", "Lorem ipsum.");
	private static final Figure SPIN_TURN    = new Figure("Spin Turn",    "Lorem ipsum.");
	private static final Figure FAN          = new Figure("Fan",          "Lorem ipsum.");
	private static final Figure PROMENADE    = new Figure("Promenade",    "Lorem ipsum.");
	private static final Figure CHASSE       = new Figure("Chassé",       "Lorem ipsum.");
	private static final Figure WHISK        = new Figure("Whisk", Arrays.asList(PROMENADE, CHASSE));

	public static final LatinDance    JIVE      = new LatinDance(   "Jive",      "4/4", Arrays.asList(BASIC_MOVE));
	public static final LatinDance    RUMBA     = new LatinDance(   "Rumba",     "4/4", Arrays.asList(BASIC_MOVE, FAN));
	public static final LatinDance    CHACHACHA = new LatinDance(   "ChaChaCha", "4/4", Arrays.asList(BASIC_MOVE, FAN));
	public static final StandardDance TANGO     = new StandardDance("Tango",     "4/4", Arrays.asList(BASIC_MOVE, PROMENADE));
	public static final StandardDance QUICKSTEP = new StandardDance("Quickstep", "4/4", Arrays.asList(BASIC_MOVE, SPIN_TURN));
	public static final StandardDance WALTZ     = new StandardDance("Waltz",     "3/4", Arrays.asList(NATURAL_TURN, SPIN_TURN, WHISK));

	public static void main(String[] args) {
		/*
		 * Unnötig! Wenn die Instanzen in der main Methode angelegt würden,
		 * könnte man sie nicht von außen verwenden, dann wäre also
		 * die Funktion einer Datenbank nicht gewährleistet.
		 */
	}
}
