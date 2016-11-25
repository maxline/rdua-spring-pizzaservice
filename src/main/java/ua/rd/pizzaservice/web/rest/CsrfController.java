package ua.rd.pizzaservice.web.rest;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Serhii_Mykhliuk
 */
@RestController
public class CsrfController {
    @RequestMapping("/csrf")
    public CsrfToken csrf(CsrfToken token){
        return token;
    }

}
