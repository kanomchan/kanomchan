package org.kanomchan.core.common.web.struts.components;

import java.io.Writer;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTag;
import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.context.CurrentThread;
import org.kanomchan.core.common.processhandler.ProcessContext;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name="authen", tldBodyContent="JSP", tldTagClass="org.kanomchan.core.common.web.struts.view.jsp.AuthenTag", description="Assigns a value to a variable in a specified scope")
public class Authen extends Component {

    Boolean answer;
    String code;

    public void setCode(String code) {
		this.code = code;
	}
	public Authen(ValueStack stack) {
		super(stack);
	}

    public boolean start(Writer writer) {
    	ProcessContext pageContext  = CurrentThread.getProcessContext();
    	UserBean user =pageContext.getUserBean();
    	String[] privileges = code.split(",");
//		if (revoked != null && "true".equals(revoked)) {
//			for (String privilege : privileges) {
//				if(user.getPrivileges().contains(privilege)) {
//					return Tag.SKIP_BODY;
//				}
//			}
//			return Tag.EVAL_BODY_INCLUDE;
//		} else {
			for (String privilege : privileges) {
				if(user.getPrivileges().contains(privilege)) {
					return true;
				}
			}
			//No privilege
			return false;
//		}
//        answer = (Boolean) findValue(test, Boolean.class);
//
//        if (answer == null) {
//            answer = Boolean.FALSE;
//        }
//        stack.getContext().put(ANSWER, answer);
//        return answer.booleanValue();
    }
    
    public boolean end(Writer writer, String body) {
//        stack.getContext().put(ANSWER, answer);
        return super.end(writer, body);
    }
}
