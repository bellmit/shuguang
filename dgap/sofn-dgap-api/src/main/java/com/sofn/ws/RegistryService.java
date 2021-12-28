package com.sofn.ws;

import javax.jws.WebService;
import javax.xml.ws.wsaddressing.W3CEndpointReference;
import java.util.List;

/**
 * Created by helong on 16-11-16.
 */
@WebService(name = "registry")
public interface RegistryService {
    List<W3CEndpointReference> getAllWebServices();

    List<String> getAllWebServiceAddresses();
}
