package org.kanomchan.core.common.util;

import org.kanomchan.core.common.bean.BeanLang;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;


public class BeanLangUtil {
	public static <T extends Object> BeanLang<T> prepareBeanLang(BeanLang<T> beanLang,T t, String langCode) throws RollBackException, NonRollBackException {
		if(beanLang == null )
			beanLang = new BeanLang<T>();
		if(CommonConstant.ENG.equals(langCode)){
			beanLang.setBeanEng(t);
		}else{
			beanLang.setBeanOtherLang(t);
			beanLang.setLangCode(langCode);
		}
		return beanLang;
	}
}
