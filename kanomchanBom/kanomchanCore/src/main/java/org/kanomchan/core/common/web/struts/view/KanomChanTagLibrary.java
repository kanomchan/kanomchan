package org.kanomchan.core.common.web.struts.view;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.TagLibraryDirectiveProvider;
import org.apache.struts2.views.TagLibraryModelProvider;
import org.kanomchan.core.common.web.struts.components.Div;
import org.kanomchan.core.common.web.struts.components.SetValueByString;
import org.kanomchan.core.common.web.struts.view.freemarker.tags.KanomChanModels;
import org.kanomchan.core.common.web.struts.views.velocity.components.MessageValidateDirective;

import com.opensymphony.xwork2.util.ValueStack;

public class KanomChanTagLibrary implements TagLibraryDirectiveProvider, TagLibraryModelProvider {

	@Override
	public Object getModels(ValueStack stack, HttpServletRequest req,
			HttpServletResponse res) {
		return new KanomChanModels(stack, req, res);
	}

	@Override
	public List<Class> getDirectiveClasses() {
		Class[] directives = new Class[] {
	            MessageValidateDirective.class
	        };
	        return Arrays.asList(directives);
	}

}
