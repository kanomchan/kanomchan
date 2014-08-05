package org.kanomchan.core.common.web.struts.view;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.views.TagLibraryDirectiveProvider;
import org.apache.struts2.views.TagLibraryModelProvider;
import org.apache.struts2.views.velocity.components.ActionDirective;
import org.apache.struts2.views.velocity.components.ActionErrorDirective;
import org.apache.struts2.views.velocity.components.ActionMessageDirective;
import org.apache.struts2.views.velocity.components.AnchorDirective;
import org.apache.struts2.views.velocity.components.BeanDirective;
import org.apache.struts2.views.velocity.components.CheckBoxDirective;
import org.apache.struts2.views.velocity.components.CheckBoxListDirective;
import org.apache.struts2.views.velocity.components.ComboBoxDirective;
import org.apache.struts2.views.velocity.components.ComponentDirective;
import org.apache.struts2.views.velocity.components.DateDirective;
import org.apache.struts2.views.velocity.components.DivDirective;
import org.apache.struts2.views.velocity.components.DoubleSelectDirective;
import org.apache.struts2.views.velocity.components.FieldErrorDirective;
import org.apache.struts2.views.velocity.components.FileDirective;
import org.apache.struts2.views.velocity.components.FormDirective;
import org.apache.struts2.views.velocity.components.HeadDirective;
import org.apache.struts2.views.velocity.components.HiddenDirective;
import org.apache.struts2.views.velocity.components.I18nDirective;
import org.apache.struts2.views.velocity.components.IncludeDirective;
import org.apache.struts2.views.velocity.components.LabelDirective;
import org.apache.struts2.views.velocity.components.OptionTransferSelectDirective;
import org.apache.struts2.views.velocity.components.ParamDirective;
import org.apache.struts2.views.velocity.components.PasswordDirective;
import org.apache.struts2.views.velocity.components.PropertyDirective;
import org.apache.struts2.views.velocity.components.PushDirective;
import org.apache.struts2.views.velocity.components.RadioDirective;
import org.apache.struts2.views.velocity.components.ResetDirective;
import org.apache.struts2.views.velocity.components.SelectDirective;
import org.apache.struts2.views.velocity.components.SetDirective;
import org.apache.struts2.views.velocity.components.SubmitDirective;
import org.apache.struts2.views.velocity.components.TextAreaDirective;
import org.apache.struts2.views.velocity.components.TextDirective;
import org.apache.struts2.views.velocity.components.TextFieldDirective;
import org.apache.struts2.views.velocity.components.TokenDirective;
import org.apache.struts2.views.velocity.components.URLDirective;
import org.apache.struts2.views.velocity.components.UpDownSelectDirective;
import org.kanomchan.core.common.web.struts.components.Div;
import org.kanomchan.core.common.web.struts.components.SetValueByString;
import org.kanomchan.core.common.web.struts.view.freemarker.tags.KanomChanModels;
import org.kanomchan.core.common.web.struts.view.freemarker.tags.SetValueByStringModel;

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
	            SetValueByString.class
	            ,Div.class
	        };
	        return Arrays.asList(directives);
	}

}
