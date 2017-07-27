package org.kanomchan.core.common.web.struts.components;

import java.io.Writer;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTag;
import org.kanomchan.core.common.bean.UserBean;
import org.kanomchan.core.common.constant.CommonConstant;
import org.kanomchan.core.common.context.ApplicationContextUtil;
import org.kanomchan.core.common.exception.NonRollBackException;
import org.kanomchan.core.common.exception.RollBackException;
import org.kanomchan.core.common.service.ConfigService;
import org.kanomchan.core.security.authorize.dao.UserAuthorizeDao;

import com.opensymphony.xwork2.ActionContext;
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
    	Map<String, Object> session = ActionContext.getContext().getSession();
		UserBean userBean = (UserBean) session.get(CommonConstant.SESSION.USER_BEAN_KEY);
    	ConfigService configService = ApplicationContextUtil.getBean("configService", ConfigService.class);
    	UserAuthorizeDao userAuthorizeDao = ApplicationContextUtil.getBean("userAuthorizeDao", UserAuthorizeDao.class);
		Set<String> privileges;
		if( userBean != null){
			privileges = userBean.getPrivileges();
		}else{
			String idGuest = "7";
			try{
				idGuest = configService.get("GUEST_ID");
			}catch(Exception e){
				
			}
			try {
				privileges = userAuthorizeDao.getUserPrivilegesByRoleId(idGuest);
			} catch (RollBackException |NonRollBackException e) {
				privileges = new HashSet<String>();
			}
		}
    	String[] privilegesCode = code.split(",");
			for (String privilege : privilegesCode) {
				if(privileges.contains(privilege)) {
					return true;
				}
			}
			//No privilege
			return false;
    }
    
    public boolean end(Writer writer, String body) {
        return super.end(writer, body);
    }
}
