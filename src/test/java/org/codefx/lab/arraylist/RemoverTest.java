package org.codefx.lab.arraylist;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

import static java.util.Arrays.asList;
import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

abstract class RemoverTest {

	abstract <T> ArrayList<T> remove(ArrayList<T> list, Collection<Integer> indices);

	private final ArrayList<String> list = new ArrayList<>();

	// inheriting nested classes does not seem to work (junit-team/junit5#717),
	// so they are not nested here and implementers have to extend them

	class _EmptyList {

		@Test
		void removeNone_emptyList() throws Exception {
			ArrayList<String> removed = remove(list, emptySet());
			assertThat(removed).isEmpty();
		}

		@Test
		void removeSome_exception() throws Exception {
			assertThrows(
					RuntimeException.class,
					() -> remove(list, singleton(0)));
		}

	}

	class _SingleElementList {

		@BeforeEach
		void fillList() {
			list.add("A");
		}

		@Test
		void removeNone_listHasSameElement() throws Exception {
			ArrayList<String> removed = remove(list, emptySet());
			assertThat(removed).containsExactly("A");
		}

		@Test
		void removeOnlyElement_emptyList() throws Exception {
			ArrayList<String> removed = remove(list, singleton(0));
			assertThat(removed).isEmpty();
		}

		@Test
		void removeInvalidIndex_exception() throws Exception {
			assertThrows(
					RuntimeException.class,
					() -> remove(list, singleton(1)));
		}

	}

	class _ManyElementList {

		@BeforeEach
		void fillList() {
			list.addAll(asList("A", "B", "C"));
		}

		@Test
		void removeFirst_listHasSameElements() throws Exception {
			ArrayList<String> removed = remove(list, emptySet());
			assertThat(removed).containsExactly("A", "B", "C");
		}

		@Test
		void removeFirstElement_firstElementGone() throws Exception {
			ArrayList<String> removed = remove(list, singleton(0));
			assertThat(removed).containsExactly("B", "C");
		}

		@Test
		void removeMiddleElement_middleElementGone() throws Exception {
			ArrayList<String> removed = remove(list, singleton(1));
			assertThat(removed).containsExactly("A", "C");
		}

		@Test
		void removeLastElement_lastElementGone() throws Exception {
			ArrayList<String> removed = remove(list, singleton(2));
			assertThat(removed).containsExactly("A", "B");
		}

		@Test
		void removeFirstElementTwice_firstTwoElementGone() throws Exception {
			ArrayList<String> removed = remove(list, asList(0, 0));
			assertThat(removed).containsExactly("C");
		}

		@Test
		void removeMiddleElements_twoElementGone() throws Exception {
			ArrayList<String> removed = remove(list, asList(1, 1));
			assertThat(removed).containsExactly("A");
		}

		@Test
		void removeLastElementTwice_exception() throws Exception {
			assertThrows(
					RuntimeException.class,
					() -> remove(list, asList(2, 2)));
		}

	}

}
