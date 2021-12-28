package com.sofn.sys.web.integration.unti;

import java.util.List;

public class SchemaEntity {

	private String objectClass ;
	
	private List<AttributeEntity> attrs ;

	public String getObjectClass() {
		return objectClass;
	}

	public void setObjectClass(String objectClass) {
		this.objectClass = objectClass;
	}

	public List<AttributeEntity> getAttrs() {
		return attrs;
	}

	public void setAttrs(List<AttributeEntity> attrs) {
		this.attrs = attrs;
	}
	
	
}
