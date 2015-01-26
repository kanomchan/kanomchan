package org.kanomchan.core.common.util;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.kanomchan.core.common.bean.CheckBox;
import org.kanomchan.core.common.bean.EntityBean;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.security.authorize.bean.MenuBean;

import com.opensymphony.xwork2.ActionContext;


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
	public static <T extends EntityBean>  List<T> getPushStatus(List<CheckBox<T>> checkBoxs,String statusTrue,String statusFalse){
		List<T> list = new LinkedList<T>();
		if(checkBoxs !=null){
			for (CheckBox<T> checkBox : checkBoxs) {
				if (checkBox.isCheck()) { //Status True
					checkBox.getValue().setStatus(statusTrue);
					list.add(checkBox.getValue());
				}
				if (!checkBox.isCheck()) { //Status False
					checkBox.getValue().setStatus(statusFalse);
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
		Set<T> edit = null;
		if(listedit != null)
			edit = new HashSet<T>(listedit);
		else
			edit = new HashSet<T>();
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
	
	public static <T extends Object,E extends Object> List<CheckBox<E>> convertListCheckBox(final Class<E> clazz,List<T> listShow, List<E> listedit,ComparatorTwoObject<T,E> comparatorTwoObject,SetData<T,E> set ) {
		
		List<CheckBox<E>> listOut = new LinkedList<CheckBox<E>>();
		Set<E> edit = new HashSet<E>(listedit);
		if(listShow !=null){
			for (T obje : listShow) {
				
				if(comparatorTwoObject.contains(obje,edit)){
					for (E e : edit) {
						if(comparatorTwoObject.compare(obje, e)){
							E out = set.setData(obje,e);
							CheckBox<E> checkBox = new CheckBox<E>(out);
							checkBox.setCheck(true);
							listOut.add(checkBox);
						}
					}
					
				}else{
					E out;
					try {
						out = set.setData(obje,clazz.newInstance());
						listOut.add(new CheckBox<E>(out));
					} catch (InstantiationException | IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
		}
		return listOut;
	}
	
	public static MenuBean find(String action , int menuId){
		Map<Integer, MenuBean> mapMenu = (Map<Integer, MenuBean>)ActionContext.getContext().getSession().get(CommonConstant.SESSION.MENU_BEAN_MAP_KEY);
		MenuBean menuBeanOut = null;
		MenuBean menuBean = mapMenu.get(menuId);
		MenuBean menuBeanParent = mapMenu.get(menuBean.getParentId());
		int index = menuBeanParent.getChildMenu().indexOf(menuBean);
		if ("previous".equals(action)) {
			if (index-1 >= 0) {
				menuBeanOut = menuBeanParent.getChildMenu().get(index-1);
			} else {
				menuBeanOut = find(action,menuBeanParent.getMenuId());
			}
		} else if ("next".equals(action)) {
			if (index+1 < menuBeanParent.getChildMenu().size()) {
				menuBeanOut = menuBeanParent.getChildMenu().get(index+1);
			}else{
				menuBeanOut = find(action,menuBeanParent.getMenuId());
			}
		} else {
			menuBeanOut = mapMenu.get(menuId);
		}
		return menuBeanOut;
	}

}
