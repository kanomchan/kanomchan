package org.kanomchan.core.common.web.struts.result;

import java.util.Collection;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletResponse;

public interface DynamicWriter {

	
	public void writeToResponse(HttpServletResponse response,Object object, Collection<Pattern> excludeProperties,Collection<Pattern> includeProperties);

//	public void writeToResponse(HttpServletResponse response, Object rootObject);
}
