package org.codefx.lab.arraylist;

import org.junit.jupiter.api.Nested;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class ArrayListRemoveTest extends RemoverTest {

	@Override
	public <T> ArrayList<T> remove(ArrayList<T> list, Collection<Integer> indices) {
		indices.stream()
				.sorted(Comparator.<Integer>naturalOrder().reversed())
				.mapToInt(i -> i)
				.forEach(list::remove);
		return list;
	}

	@Nested class EmptyList extends _EmptyList { }
	@Nested class SingleElementList extends _SingleElementList {}
	@Nested class ManyElementList extends _ManyElementList {}

}
