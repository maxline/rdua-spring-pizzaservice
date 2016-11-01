package ua.rd.pizzaservice.web.infrastructure;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Serhii_Mykhliuk
 */
public interface MyController {
    void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
