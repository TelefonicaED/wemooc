package com.ted.prerequisite.internal.resource.bundle;

import com.liferay.portal.kernel.language.UTF8Control;

import java.util.Enumeration;
import java.util.ResourceBundle;

import org.osgi.service.component.annotations.Component;

@Component(
	property = { "language.id=en_US" }, 
	service = ResourceBundle.class
)
public class PrerequisiteResourceBundleEN extends ResourceBundle {

	@Override
    protected Object handleGetObject(String key) {
        return _resourceBundle.getObject(key);
    }

    @Override
    public Enumeration<String> getKeys() {
        return _resourceBundle.getKeys();
    }

    private final ResourceBundle _resourceBundle = ResourceBundle.getBundle(
        "content.Language_en", UTF8Control.INSTANCE);

}
