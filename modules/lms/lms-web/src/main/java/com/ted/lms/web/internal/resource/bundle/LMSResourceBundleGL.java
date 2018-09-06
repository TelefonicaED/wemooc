package com.ted.lms.web.internal.resource.bundle;

import com.liferay.portal.kernel.language.UTF8Control;

import java.util.Enumeration;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

@Component(
	property = { "language.id=gl_ES" }, 
	service = ResourceBundle.class
)
public class LMSResourceBundleGL extends ResourceBundle {

	@Override
    protected Object handleGetObject(String key) {
        return _resourceBundle.getObject(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return _resourceBundle.getKeys();
    }

    private final ResourceBundle _resourceBundle = ResourceBundle.getBundle(
        "content.Language_gk", UTF8Control.INSTANCE);

}
