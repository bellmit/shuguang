package com.sofn.infroflow.resources;

import com.cvicse.workflow.api.resources.Resource;
import org.apache.commons.lang.StringUtils;

public class EngineResource {

    private static final Resource[] resourcesArray = new Resource[1];

    static {
        Resource resource0 = new Resource();
        resource0.setId("createRole");
        resource0.setName("实例创建角色");
        resource0.setDescription("实例创建角色");

        resourcesArray[0] =  resource0;
    }

    public static Resource[] getResources(){
        return resourcesArray;
    }

    public static Resource getResourceById(String id){
        for (Resource resource : resourcesArray) {
            if (StringUtils.equals(resource.getId(), id)) {
                return resource;
            }
        }

        return null;
    }
}
