package ohm.softa.a05.collections;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * A simple list containing just a few basic methods
 * Inherits the Iterable interface to ease the handling (e.g. extended for-loop)
 *
 * @param <T> type of the items which will be saved in the list
 */
public interface SimpleList<T> extends Iterable<T> {

	/**
	 * Add a given object to the back of the list.
	 *
	 * @param o item to add
	 */
	void add(T o);

	/**
	 * @param clazz Class instance to solve the instantiation problem
	 */
	@SuppressWarnings("unchecked")
	default void addDefault(Class<T> clazz) {
		try {
			/* better solution would be to use Google Guava to get a type token
			and use this token to create a new instance
			because we didn't include a reference to Guava this is the easiest way to create new instance of T */
			this.add(clazz.newInstance());
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return current size of the list
	 */
	int size();

	/**
	 * Generate a new list using the given filter instance.
	 *
	 * @param filter SimpleFilter instance to determine which elements should be included
	 * @return a new, filtered list
	 */
	@SuppressWarnings("unchecked")
	default SimpleList<T> filter(SimpleFilter<T> filter) {
		SimpleList<T> result;
		try {
			result = (SimpleList<T>) getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			result = new SimpleListImpl<>();
		}

		for (T o : this) {
			if (filter.include(o)) {
				result.add(o);
			}
		}
		return result;
	}

	/**
	 * @param transform
	 * @param <R>
	 * @return
	 */
	@SuppressWarnings("unchecked")
	// Bounds on Wildcards:
	// 1. Modify the map method of the SimpleList interface according to the PECS principle
	// For Function, PECS means: the input T is consumer (hence super),		   erlaubt alle Elternklassen von T
	// 							 the output R is the producer (hence extends). erlaubt alle Kindsklassen von R
	// Ich lese das T und schreibe das R (was rauskommt bei der Funktion ist das was ich beim add() reingebe)
	// T produziert mir die Einträge und R nimmt die entgegen
	// Ich nehme entgegen was T oder drüber ist und erhalte was was R oder drunter ist
	// Funktion bildet von T nach R ab. Will z.B. Plant -> Flower (Plant in Flower überführen)
	// Hab Plant oder was drüber und will in was übersetzen was Plant ist oder drunter (?)
	// wildcard bound: ? extends SuperType (upper) or ? super SubType (lower)
	/*Alte Methodendeklaration:
	default <R> SimpleList<R> map(Function<T, R> transform) {*/
	default <R> SimpleList<R> map(Function<? super T, ? extends R> transform) {
		SimpleList<R> result;
		try {
			result = (SimpleList<R>) getClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			result = new SimpleListImpl<>();
		}
		for (T t : this) {
			result.add(transform.apply(t));
		}
		return result;
	}
	// ? extends R means you can pass an object whose class is derived by R, a.k.a subclass of R.
	// ? super T (Klasse könnte T oder eine Basisklasse von T sein)

	//Bsp.:
	/*public static main(String[] args){
		SimpleList<Plant> p1 = new SimpleListImpl<>();
		SimpleList<Flower> p2 = new SimpleListImpl<>();
		SimpleList<Shrub> p3 = new SimpleListImpl<>();
		p3 = p1.map(f -> f);
	}*/

	// Wildcard = beliebiger Parameter wo versucht wird einzusetzen was passt
	// Ich erlaube das diese Variable nur in einem bestimmten Bereich ist,
	// also mit extends z.B. das die Variable nen Typ drunter haben muss
	// Wenn ich ne Transform Funktion hab kommt was was über T sitzt rein und was was unter R sitzt raus
	// Wildcards machen eig. nur Sinn in Kombination mit Bounds
}