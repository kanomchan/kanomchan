package org.kanomchan.core.common.util;

import java.util.LinkedList;
import java.util.List;

import org.kanomchan.core.common.bean.CheckBox;

public class StrutsUtil {
	
	public static <T extends Object>  List<T> getCheck(List<CheckBox<T>> checkBoxs){
		List<T> list = new LinkedList<T>();
		if(checkBoxs !=null){
			for (CheckBox<T> checkBox : checkBoxs) {
				if (checkBox.isCheck()) {
					list.add(checkBox.getValue());
				}
			}
		}
		return list;
	}
	
	public static <T extends Object>  List<T> getNonCheck(List<CheckBox<T>> checkBoxs){
		List<T> list = new LinkedList<T>();
		if(checkBoxs !=null){
			for (CheckBox<T> checkBox : checkBoxs) {
				if (!checkBox.isCheck()) {
					list.add(checkBox.getValue());
				}
			}
		}
		return list;
	}

}
