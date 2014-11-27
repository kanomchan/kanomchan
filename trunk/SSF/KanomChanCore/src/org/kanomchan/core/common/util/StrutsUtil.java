package org.kanomchan.core.common.util;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

	public static <T extends Object> List<CheckBox<T>> convertListCheckBox(List<T> listShow) {
//		
//		List<CheckBox<T>> listOut = new LinkedList<CheckBox<T>>();
//		if(list !=null){
//			for (T obje : list) {
//				listOut.add(new CheckBox<T>(obje));
//			}
//		}
		return convertListCheckBox(listShow,null);
	}

	public static <T extends Object> List<CheckBox<T>> convertListCheckBox(List<T> listShow, List<T> listedit) {
		
		List<CheckBox<T>> listOut = new LinkedList<CheckBox<T>>();
		Set<T> edit = new HashSet<T>(listedit);
		if(listShow !=null){
			for (T obje : listShow) {
				
				if(edit.contains(obje)){
					CheckBox<T> checkBox = new CheckBox<T>(obje);
					checkBox.setCheck(true);
					listOut.add(checkBox);
				}else{
					listOut.add(new CheckBox<T>(obje));
				}
				
			}
		}
		return listOut;
	}
	
	public static <T extends Object,E extends Object> List<CheckBox<T>> convertListCheckBox(List<T> listShow, List<E> listedit,ComparatorTwoObject<T,E> comparatorTwoObject ) {
		
		List<CheckBox<T>> listOut = new LinkedList<CheckBox<T>>();
		Set<E> edit = new HashSet<E>(listedit);
		if(listShow !=null){
			for (T obje : listShow) {
				
				if(comparatorTwoObject.contains(obje,edit)){
					CheckBox<T> checkBox = new CheckBox<T>(obje);
					checkBox.setCheck(true);
					listOut.add(checkBox);
				}else{
					listOut.add(new CheckBox<T>(obje));
				}
				
			}
		}
		return listOut;
	}

}
