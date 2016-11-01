package ua.rd.pizzaservice.web;

import org.springframework.stereotype.Controller;
import ua.rd.pizzaservice.web.infrastructure.MyController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Serhii_Mykhliuk
 */
@Controller
public class HelloController implements MyController {
    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try (PrintWriter out = response.getWriter()){
            out.println("Hello from HelloController!");
        }

    }
}
