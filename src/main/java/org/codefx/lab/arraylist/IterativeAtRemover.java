package org.codefx.lab.arraylist;

import java.util.Arrays;
import java.util.List;

public class IterativeAtRemover {

	// intended for ArrayList

	public static <T> List<T> remove(List<T> list, int[] removeAts) {
		Arrays.sort(removeAts);
		for (int i = removeAts.length - 1; i >= 0; i--) {
			list.remove(removeAts[i]);
		}
		return list;
	}

}
