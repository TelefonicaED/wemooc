package com.ted.prerequisite.model;

import com.ted.prerequisite.registry.PrerequisiteFactoryRegistryUtil;

import aQute.bnd.annotation.ProviderType;

/**
 * Interfaz para las postcondiciones que se ejecutan cuando un usuario ha finalizado un curso
 * @author Virginia Martín Agudo
 *
 */
@ProviderType
public abstract class BasePrerequisite implements Prerequisite{
	
	protected PrerequisiteRelation prerequisiteRelation;
	
	public BasePrerequisite(PrerequisiteRelation prerequisiteRelation) {
		this.prerequisiteRelation = prerequisiteRelation;
	}
	
	@Override
	public PrerequisiteFactory getPrerequisiteFactory(){
		if (prerequisiteFactory != null) {
			return prerequisiteFactory;
		}

		prerequisiteFactory =
			(PrerequisiteFactory)
			PrerequisiteFactoryRegistryUtil.
					getPrerequisiteFactoryByClassName(getClassName());

		return prerequisiteFactory;
	}
	
	private PrerequisiteFactory prerequisiteFactory;
}
