package com.sofn;

import com.sofn.core.oid.IdGenerator;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Administrator on 2016/9/6.
 */
public class OidTest {
    public static int count=0;
    public synchronized static int getCount(){
        if(count>9)
            count=0;
        return count++;
    }
    public static void main(String[]args) throws InterruptedException {/*
        //测试获取各种码
        IdGenerator idGen=new IdGenerator();
        //主体身份码
        String mainBodyIdentityCode=idGen.getMainBodyIdentityCode(IdGenerator.MainBodyCategories.MainManagementBody, IdGenerator.OrganizationForms.Individual,"123456789012345678");
        //主体用户码
        String mainBodyUserCode=idGen.getMainBodyUserCode(IdGenerator.MainBodyCategories.MainManagementBody, IdGenerator.OrganizationForms.Individual,"123456789012345678",1);
        //生产批次码
        String productBatchCode=idGen.getProductBatchCode(IdGenerator.MainBodyCategories.MainManagementBody, IdGenerator.OrganizationForms.Individual,"123456789012345678","12345678");
        //产品追溯码
        String productTraceabilityCode=idGen.getProductTraceabilityCode(IdGenerator.MainBodyCategories.MainManagementBody, IdGenerator.OrganizationForms.Individual,"123456789012345678","12345678");
        //入市追溯凭证
        String intoCityTraceability=idGen.getIntoCityTraceability(IdGenerator.MainBodyCategories.MainManagementBody, IdGenerator.OrganizationForms.Individual,"123456789012345678","12345678");
        //临时主体身份码
        String temporaryIdentityCode=idGen.getTemporaryIdentityCode(IdGenerator.MainBodyCategories.MainManagementBody, IdGenerator.OrganizationForms.Individual,"123456789012345678");
        //产品临时码
        String productTemporaryCode=idGen.getProductTemporaryCode(IdGenerator.MainBodyCategories.MainManagementBody, IdGenerator.OrganizationForms.Individual,"123456789012345678","12345678");

        System.out.println("count:"+mainBodyIdentityCode.replace(".","").length()+"     主体身份码:"+mainBodyIdentityCode);
        System.out.println("count:"+mainBodyUserCode.replace(".","").length()+"     主体用户码:"+mainBodyUserCode);
        System.out.println("count:"+productBatchCode.replace(".","").length()+"     生产批次码:"+productBatchCode);
        System.out.println("count:"+productTraceabilityCode.replace(".","").length()+"     产品追溯码:"+productTraceabilityCode);
        System.out.println("count:"+intoCityTraceability.replace(".","").length()+"     入市追溯凭证:"+intoCityTraceability);
        System.out.println("count:"+temporaryIdentityCode.replace(".","").length()+"     临时主体身份码:"+temporaryIdentityCode);
        System.out.println("count:"+productTemporaryCode.replace(".","").length()+"     产品临时码:"+productTemporaryCode);
*/
    }
}
