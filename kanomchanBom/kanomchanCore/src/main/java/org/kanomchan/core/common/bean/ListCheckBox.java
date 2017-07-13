package org.kanomchan.core.common.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ListCheckBox<T> implements List<CheckBox<T>>,Serializable{

/**
	 * 
	 */
	private static final long serialVersionUID = -8201881723740784766L;

//	private List<CheckBox<T>> list;
	
	public ListCheckBox(List<T> list) {
	}
	@Override
	public int size() {
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public boolean contains(Object o) {
		return false;
	}

	@Override
	public Iterator<CheckBox<T>> iterator() {
		return null;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@SuppressWarnings("hiding")
	@Override
	public <T extends Object> T[] toArray(T[] a) {
		return null;
	}

	@Override
	public boolean add(CheckBox<T> e) {
		return false;
	}

	@Override
	public boolean remove(Object o) {
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends CheckBox<T>> c) {
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends CheckBox<T>> c) {
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}

	@Override
	public void clear() {
		
	}

	@Override
	public CheckBox<T> get(int index) {
		return null;
	}

	@Override
	public CheckBox<T> set(int index, CheckBox<T> element) {
		return null;
	}

	@Override
	public void add(int index, CheckBox<T> element) {
		
	}

	@Override
	public CheckBox<T> remove(int index) {
		return null;
	}

	@Override
	public int indexOf(Object o) {
		return 0;
	}

	@Override
	public int lastIndexOf(Object o) {
		return 0;
	}

	@Override
	public ListIterator<CheckBox<T>> listIterator() {
		return null;
	}

	@Override
	public ListIterator<CheckBox<T>> listIterator(int index) {
		return null;
	}

	@Override
	public List<CheckBox<T>> subList(int fromIndex, int toIndex) {
		return null;
	}
	

}
