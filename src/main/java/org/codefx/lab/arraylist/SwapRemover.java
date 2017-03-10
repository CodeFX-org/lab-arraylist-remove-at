package org.codefx.lab.arraylist;

import java.util.List;

public class SwapRemover {

	// intended for ArrayList

	public static <T> List<T> remove(List<T> list, int[] removeAts) {
		int copyFrom = list.size();
		for (int i = 0; i < removeAts.length; i++) {
			int removeAt = removeAts[i];
			copyFrom--;
			list.set(removeAt, list.get(copyFrom));
		}
		list.subList(copyFrom, list.size())
				.clear();
		return list;
	}

}
