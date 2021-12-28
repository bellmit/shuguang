package com.sofn.ws;

import javax.jws.WebService;

/**
 * author tianjie
 *
 * @date 2016/12/1
 */
@WebService
public interface DataAccessService {

    public String accessData(String xml);

}
