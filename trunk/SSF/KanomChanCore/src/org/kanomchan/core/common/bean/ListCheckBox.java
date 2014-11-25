package org.kanomchan.core.common.bean;

import java.util.LinkedList;
import java.util.List;

public class ListCheckBox< E > extends LinkedList<CheckBox<E>> {
	
	public List<E> getCheck(){
		List<E> list = new LinkedList<E>();
		for (CheckBox<E> checkBox : this) {
			if(checkBox.isCheck()){
				list.add(checkBox.getValue());
			}
		}
		return list;
	}
	
	public List<E> getNonCheck(){
		List<E> list = new LinkedList<E>();
		for (CheckBox<E> checkBox : this) {
			if(!checkBox.isCheck()){
				list.add(checkBox.getValue());
			}
		}
		return list;
	}

}
