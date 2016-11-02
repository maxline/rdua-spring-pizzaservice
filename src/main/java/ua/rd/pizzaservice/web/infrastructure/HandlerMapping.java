package ua.rd.pizzaservice.web.infrastructure;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Serhii_Mykhliuk
 */
public interface HandlerMapping {

    MyController getController(HttpServletRequest request);
}
