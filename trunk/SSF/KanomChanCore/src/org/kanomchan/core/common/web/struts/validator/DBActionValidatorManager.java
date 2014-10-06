package org.kanomchan.core.common.web.struts.validator;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.mapper.ActionMapping;
import org.kanomchan.core.common.bean.FieldValidatorBean;
import org.kanomchan.core.common.context.ApplicationContextUtil;
import org.kanomchan.core.common.service.ConfigService;
import org.kanomchan.core.common.web.struts.action.BaseAction;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.XWorkConstants;
import com.opensymphony.xwork2.config.ConfigurationException;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.opensymphony.xwork2.validator.ActionValidatorManager;
import com.opensymphony.xwork2.validator.DelegatingValidatorContext;
import com.opensymphony.xwork2.validator.FieldValidator;
import com.opensymphony.xwork2.validator.ShortCircuitableValidator;
import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.Validator;
import com.opensymphony.xwork2.validator.ValidatorConfig;
import com.opensymphony.xwork2.validator.ValidatorContext;
import com.opensymphony.xwork2.validator.ValidatorFactory;
import com.opensymphony.xwork2.validator.validators.VisitorFieldValidator;

public class DBActionValidatorManager implements ActionValidatorManager {

    private final Map<String, List<ValidatorConfig>> validatorCache = Collections.synchronizedMap(new HashMap<String, List<ValidatorConfig>>());
    private final Map<String, List<ValidatorConfig>> validatorFileCache = Collections.synchronizedMap(new HashMap<String, List<ValidatorConfig>>());
    private final Logger LOG = LoggerFactory.getLogger(DBActionValidatorManager.class);
    
    private ValidatorFactory validatorFactory;
    private boolean reloadingConfigs;
    
    @Inject
    public void setValidatorFactory(ValidatorFactory fac) {
        this.validatorFactory = fac;
    }
    
    @Inject(value = XWorkConstants.RELOAD_XML_CONFIGURATION, required = false)
    public void setReloadingConfigs(String reloadingConfigs) {
        this.reloadingConfigs = Boolean.parseBoolean(reloadingConfigs);
    }
	@Override
	public List<Validator> getValidators(Class clazz, String context, String method) {
		
		ActionMapping actionMapping = ServletActionContext.getActionMapping();
		final String validatorKey;
		if(clazz.isInstance(BaseAction.class)){
			validatorKey = actionMapping.getNamespace()+"/"+context;
		}else{
			validatorKey = buildValidatorKey(clazz, context);
		}
		
		
		
		ValueStack stack = ActionContext.getContext().getValueStack();
		
//		ConfigService configService = ApplicationContextUtil.getBean(ConfigService.class);
		
        if (validatorCache.containsKey(validatorKey)) {
            if (reloadingConfigs) {
                validatorCache.put(validatorKey, loadConfig(validatorKey));
            }
        } else {
            validatorCache.put(validatorKey, loadConfig(validatorKey));
        }
		
//		ConfigService configService = ApplicationContextUtil.getBean(ConfigService.class);
		
		
		// get the set of validator configs
        List<ValidatorConfig> cfgs = validatorCache.get(validatorKey);
//        List<ValidatorConfig> cfgs = loadConfig(validatorKey);

        // create clean instances of the validators for the caller's use
        ArrayList<Validator> validators = new ArrayList<Validator>(cfgs.size());
        for (ValidatorConfig cfg : cfgs) {
            if (method == null || method.equals(cfg.getParams().get("methodName"))) {
                Validator validator = validatorFactory.getValidator(cfg);
                validator.setValidatorType(cfg.getType());
                validator.setValueStack(stack);
                validators.add(validator);
            }
        }
		
		return validators;
	}

	@Override
	public List<Validator> getValidators(Class clazz, String context) {
		return getValidators(clazz, context, null);
	}

	
	
	
	public void validate(Object object, String context) throws ValidationException {
		validate(object, context, (String) null);
	}

	public void validate(Object object, String context, String method) throws ValidationException {
		ValidatorContext validatorContext = new DelegatingValidatorContext(object);
		validate(object, context, validatorContext, method);
	}

	public void validate(Object object, String context, ValidatorContext validatorContext) throws ValidationException {
		validate(object, context, validatorContext, null);
	}

	public void validate(Object object, String context, ValidatorContext validatorContext, String method) throws ValidationException {
        List<Validator> validators = new ArrayList<Validator>();
        Set<String> shortcircuitedFields = null;
        if(object instanceof BaseAction){
        	BaseAction baseAction = (BaseAction) object;
        	ConfigService configService= ApplicationContextUtil.getBean(ConfigService.class);
        	ActionMapping actionMapping = ServletActionContext.getActionMapping();
        	if(actionMapping!=null){
        		String inputResultName = configService.getInputResultName(actionMapping.getNamespace(),actionMapping.getName());
        		if(inputResultName!=null){
        			baseAction.setInputResultName(inputResultName);
            		validators = getValidators(object.getClass(), context, method);
        		}
        		
        	}
        	
        	
        }else{
        	validators = getValidators(object.getClass(), context, method);
        }

        for (final Validator validator : validators) {
        try {
                validator.setValidatorContext(validatorContext);

                if (LOG.isDebugEnabled()) {
                    LOG.debug("Running validator: " + validator + " for object " + object + " and method " + method);
                }

                FieldValidator fValidator = null;
                String fullFieldName = null;

                if (validator instanceof FieldValidator) {
                    fValidator = (FieldValidator) validator;
                    fullFieldName = new InternalValidatorContextWrapper(fValidator.getValidatorContext()).getFullFieldName(fValidator.getFieldName());

                    // This is pretty crap, but needed to support short-circuited validations on nested visited objects
                    if (validatorContext instanceof VisitorFieldValidator.AppendingValidatorContext) {
                        VisitorFieldValidator.AppendingValidatorContext appendingValidatorContext =
                                (VisitorFieldValidator.AppendingValidatorContext) validatorContext;
                        fullFieldName = appendingValidatorContext.getFullFieldNameFromParent(fValidator.getFieldName());
                    }

                    if ((shortcircuitedFields != null) && shortcircuitedFields.contains(fullFieldName)) {
                        if (LOG.isDebugEnabled()) {
                            LOG.debug("Short-circuited, skipping");
                        }

                        continue;
                    }
                }

                if (validator instanceof ShortCircuitableValidator && ((ShortCircuitableValidator) validator).isShortCircuit()) {
                    // get number of existing errors
                    List<String> errs = null;

                    if (fValidator != null) {
                        if (validatorContext.hasFieldErrors()) {
                            Collection<String> fieldErrors = validatorContext.getFieldErrors().get(fullFieldName);

                            if (fieldErrors != null) {
                                errs = new ArrayList<String>(fieldErrors);
                            }
                        }
                    } else if (validatorContext.hasActionErrors()) {
                        Collection<String> actionErrors = validatorContext.getActionErrors();

                        if (actionErrors != null) {
                            errs = new ArrayList<String>(actionErrors);
                        }
                    }

                    validator.validate(object);

                    if (fValidator != null) {
                        if (validatorContext.hasFieldErrors()) {
                            Collection<String> errCol = validatorContext.getFieldErrors().get(fullFieldName);

                            if ((errCol != null) && !errCol.equals(errs)) {
                                if (LOG.isDebugEnabled()) {
                                    LOG.debug("Short-circuiting on field validation");
                                }

                                if (shortcircuitedFields == null) {
                                    shortcircuitedFields = new TreeSet<String>();
                                }

                                shortcircuitedFields.add(fullFieldName);
                            }
                        }
                    } else if (validatorContext.hasActionErrors()) {
                        Collection<String> errCol = validatorContext.getActionErrors();

                        if ((errCol != null) && !errCol.equals(errs)) {
                            if (LOG.isDebugEnabled()) {
                                LOG.debug("Short-circuiting");
                            }

                            break;
                        }
                    }

                    continue;
                }

                validator.validate(object);
            }
            finally {
                validator.setValidatorContext(null);
            }
        }
	}

	
    protected class InternalValidatorContextWrapper {
        private ValidatorContext validatorContext = null;

        InternalValidatorContextWrapper(ValidatorContext validatorContext) {
            this.validatorContext = validatorContext;
        }


        public String getFullFieldName(String field) {
            if (validatorContext instanceof VisitorFieldValidator.AppendingValidatorContext) {
                VisitorFieldValidator.AppendingValidatorContext appendingValidatorContext =
                        (VisitorFieldValidator.AppendingValidatorContext) validatorContext;
                return appendingValidatorContext.getFullFieldNameFromParent(field);
            }
            return validatorContext.getFullFieldName(field);
        }

    }
    protected static String buildValidatorKey(Class clazz, String context) {
        StringBuilder sb = new StringBuilder(clazz.getName());
        sb.append("/");
        sb.append(context);
        return sb.toString();
    }

//    private List<ValidatorConfig> buildValidatorConfigs(Class clazz, String context, boolean checkFile, Set<String> checked) {
//        List<ValidatorConfig> validatorConfigs = new ArrayList<ValidatorConfig>();
//
//        if (checked == null) {
//            checked = new TreeSet<String>();
//        } else if (checked.contains(clazz.getName())) {
//            return validatorConfigs;
//        }
//
//        if (clazz.isInterface()) {
//            for (Class anInterface : clazz.getInterfaces()) {
//                validatorConfigs.addAll(buildValidatorConfigs(anInterface, context, checkFile, checked));
//             }
//        } else {
//            if (!clazz.equals(Object.class)) {
//                validatorConfigs.addAll(buildValidatorConfigs(clazz.getSuperclass(), context, checkFile, checked));
//            }
//        }
//
//        // look for validators for implemented interfaces
//        for (Class anInterface1 : clazz.getInterfaces()) {
//            if (checked.contains(anInterface1.getName())) {
//                continue;
//            }
//
//            validatorConfigs.addAll(buildClassValidatorConfigs(anInterface1, checkFile));
//
//            if (context != null) {
//                validatorConfigs.addAll(buildAliasValidatorConfigs(anInterface1, context, checkFile));
//            }
//
//            checked.add(anInterface1.getName());
//        }
//
//        validatorConfigs.addAll(buildClassValidatorConfigs(clazz, checkFile));
//
//        if (context != null) {
//            validatorConfigs.addAll(buildAliasValidatorConfigs(clazz, context, checkFile));
//        }
//
//        checked.add(clazz.getName());
//
//        return validatorConfigs;
//    }
//    
//    private List<ValidatorConfig> buildClassValidatorConfigs(Class aClass, boolean checkFile) {
////        String fileName = aClass.getName().replace('.', '/') ;
//
//        return loadConfig(aClass.getName());
//    }
//    
//    private List<ValidatorConfig> buildAliasValidatorConfigs(Class aClass, String context, boolean checkFile) {
////        String fileName = aClass.getName().replace('.', '/') + "-" + context;
//
//        return loadConfig(aClass.getName()+ "-" + context);
//    }
    
    private List<ValidatorConfig> loadConfig(String name){
    	GsonBuilder gsonBuilder = new GsonBuilder();
    	Gson gson = gsonBuilder.create(); 
    	List<ValidatorConfig> validatorCfgs = new ArrayList<ValidatorConfig>();
    	ConfigService configService= ApplicationContextUtil.getBean(ConfigService.class);
    	List<FieldValidatorBean> s = configService.getPageValidators(name);
    	if(s!=null){
        	for (FieldValidatorBean  fieldValidatorBean : s) {
        		
        		String validatorType = fieldValidatorBean.getType();
        		Map<String, Object> extraParams = new ConcurrentHashMap<String, Object>();
        		Type typeOfSrc = new TypeToken<Map<String, Object>>(){}.getType();
        		try{
	        		Map<String, Object> out = gson.fromJson(fieldValidatorBean.getParameter(), typeOfSrc);
	        		if(out!=null)
	        			extraParams.putAll(out);
        		}catch(Exception E){}
                extraParams.put("fieldName", fieldValidatorBean.getField());
        		// ensure that the type is valid...
                try {
                	validatorFactory.lookupRegisteredValidatorType(validatorType);
                } catch (IllegalArgumentException ex) {
                    throw new ConfigurationException("Invalid validation type: " + validatorType, fieldValidatorBean);
                }
                
                ValidatorConfig.Builder vCfg = new ValidatorConfig.Builder(validatorType)
                .addParams(extraParams);
//                .location(DomHelper.getLocationObject(validatorElement))
//                .shortCircuit(Boolean.valueOf(validatorElement.getAttribute("short-circuit")).booleanValue());
                
                vCfg.defaultMessage(fieldValidatorBean.getMessage());
                vCfg.messageKey(fieldValidatorBean.getMessageKey());
                
                
        		
        		typeOfSrc = new TypeToken<String[]>(){}.getType();
        		try{
	        		String[] bodyMap = gson.fromJson(fieldValidatorBean.getMessageParameter(), typeOfSrc);
	                vCfg.messageParams(bodyMap);
        		}catch(Exception E){}
                validatorCfgs.add(vCfg.build());
    		}
    	}

    	
    	return validatorCfgs;
    };
}
