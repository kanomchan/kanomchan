package org.kanomchan.core.common.web.struts.view.freemarker.tags;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.opensymphony.xwork2.util.ValueStack;

public class KanomChanModels {
    protected ValueStack stack;
    protected HttpServletRequest req;
    protected HttpServletResponse res;
    
    protected SetValueByStringModel setValueByString;
    
    public KanomChanModels(ValueStack stack, HttpServletRequest req, HttpServletResponse res) {
        this.stack = stack;
        this.req = req;
        this.res = res;
    }
	
    
    public SetValueByStringModel getSetValueByString() {
    	if(setValueByString ==null){
    		setValueByString = new SetValueByStringModel(stack, req, res);
    	}
		return setValueByString;
	}
}
