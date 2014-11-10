package org.kanomchan.core.common.web.struts.result;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.StrutsConstants;
import org.apache.struts2.StrutsStatics;
//import org.apache.struts2.json.JSONUtil;
import org.apache.struts2.json.JSONWriter;
import org.apache.struts2.json.SerializationParams;
import org.apache.struts2.json.smd.SMDGenerator;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.Result;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.WildcardUtil;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;

public class DynamicResult implements Result {

    private static final long serialVersionUID = 8624350183189931165L;

    private static final Logger LOG = LoggerFactory.getLogger(DynamicResult.class);

    /**
     * This result type doesn't have a default param, null is ok to reduce noise in logs
     */
    public static final String DEFAULT_PARAM = null;

//    private String encoding;
//    private String defaultEncoding = "ISO-8859-1";
    private List<Pattern> includeProperties;
    private List<Pattern> excludeProperties;
    private String root;
//    private boolean wrapWithComments;
//    private boolean prefix;
//    private boolean enableSMD = false;
//    private boolean enableGZIP = false;
//    private boolean ignoreHierarchy = true;
//    private boolean ignoreInterfaces = true;
//    private boolean enumAsBean = JSONWriter.ENUM_AS_BEAN_DEFAULT;
//    private boolean noCache = false;
//    private boolean excludeNullProperties = false;
//    private int statusCode;
//    private int errorCode;
//    private String callbackParameter;
//    private String contentType;
//    private String wrapPrefix;
//    private String wrapSuffix;
    
    private DynamicWriter dynamicWriter;

//    @Inject(StrutsConstants.STRUTS_I18N_ENCODING)
//    public void setDefaultEncoding(String val) {
//        this.defaultEncoding = val;
//    }

    /**
     * Gets a list of regular expressions of properties to exclude from the JSON
     * output.
     *
     * @return A list of compiled regular expression patterns
     */
    public List<Pattern> getExcludePropertiesList() {
        return this.excludeProperties;
    }

    /**
     * Sets a comma-delimited list of regular expressions to match properties
     * that should be excluded from the JSON output.
     *
     * @param commaDelim A comma-delimited list of regular expressions
     */
    public void setExcludeProperties(String commaDelim) {
        Set<String> excludePatterns = DynamicUtil.commaDelimitedStringToSet(commaDelim);
        if (excludePatterns != null) {
            this.excludeProperties = new ArrayList<Pattern>(excludePatterns.size());
            for (String pattern : excludePatterns) {
                this.excludeProperties.add(Pattern.compile(pattern));
            }
        }
    }

    /**
     * Sets a comma-delimited list of wildcard expressions to match properties
     * that should be excluded from the JSON output.
     *
     * @param commaDelim A comma-delimited list of wildcard patterns
     */
    public void setExcludeWildcards(String commaDelim) {
        Set<String> excludePatterns = DynamicUtil.commaDelimitedStringToSet(commaDelim);
        if (excludePatterns != null) {
            this.excludeProperties = new ArrayList<Pattern>(excludePatterns.size());
            for (String pattern : excludePatterns) {
                this.excludeProperties.add(WildcardUtil.compileWildcardPattern(pattern));
            }
        }
    }

    /**
     * @return the includeProperties
     */
    public List<Pattern> getIncludePropertiesList() {
        return includeProperties;
    }

    /**
     * Sets a comma-delimited list of regular expressions to match properties
     * that should be included in the JSON output.
     *
     * @param commaDelim A comma-delimited list of regular expressions
     */
    public void setIncludeProperties(String commaDelim) {
        includeProperties = DynamicUtil.processIncludePatterns(DynamicUtil.commaDelimitedStringToSet(commaDelim), DynamicUtil.REGEXP_PATTERN);
    }

    /**
     * Sets a comma-delimited list of wildcard expressions to match properties
     * that should be included in the JSON output.
     *
     * @param commaDelim A comma-delimited list of wildcard patterns
     */
    public void setIncludeWildcards(String commaDelim) {
        includeProperties = DynamicUtil.processIncludePatterns(DynamicUtil.commaDelimitedStringToSet(commaDelim), DynamicUtil.WILDCARD_PATTERN);
    }

    public void execute(ActionInvocation invocation) throws Exception {
        ActionContext actionContext = invocation.getInvocationContext();
//        HttpServletRequest request = (HttpServletRequest) actionContext.get(StrutsStatics.HTTP_REQUEST);
        HttpServletResponse response = (HttpServletResponse) actionContext.get(StrutsStatics.HTTP_RESPONSE);

        Object rootObject;
		rootObject = findRootObject(invocation);
		dynamicWriter.writeToResponse(response, rootObject,excludeProperties,includeProperties);
    }


    private Object findRootObject(ActionInvocation invocation) {
        Object rootObject;
        if (this.root != null) {
            ValueStack stack = invocation.getStack();
            rootObject = stack.findValue(root);
        } else {
            rootObject = invocation.getStack().peek(); // model overrides action
        }
        return rootObject;
    }



    /**
     * @return OGNL expression of root object to be serialized
     */
    public String getRoot() {
        return this.root;
    }

    /**
     * Sets the root object to be serialized, defaults to the Action
     *
     * @param root OGNL expression of root object to be serialized
     */
    public void setRoot(String root) {
        this.root = root;
    }

    /**
     * If defined will be used instead of {@link #defaultEncoding}, you can define it with result
     * &lt;result name=&quot;success&quot; type=&quot;json&quot;&gt;
     *     &lt;param name=&quot;encoding&quot;&gt;UTF-8&lt;/param&gt;
     * &lt;/result&gt;
     *
     * @param encoding valid encoding string
     */
//    public void setEncoding(String encoding) {
//        this.encoding = encoding;
//    }
}
