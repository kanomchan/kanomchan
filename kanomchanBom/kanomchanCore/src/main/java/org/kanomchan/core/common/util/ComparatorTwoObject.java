package org.kanomchan.core.common.util;

import java.util.Set;

public interface ComparatorTwoObject<T,E> {
	
	boolean compare(T o1, E o2);
	
//	boolean equals(Object obj);
	
	boolean contains(T obje ,Set<E> e);

}
