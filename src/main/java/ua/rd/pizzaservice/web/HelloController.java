package ua.rd.pizzaservice.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ua.rd.pizzaservice.services.PizzaService;
import ua.rd.pizzaservice.web.infrastructure.MyController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Serhii_Mykhliuk
 */
@Controller("/hello")
public class HelloController implements MyController {

    @Autowired
    private PizzaService pizzaService;

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try (PrintWriter out = response.getWriter()){
            out.println("Hello from HelloController!");

            for(int i = 0; i < 50; i++){
                out.println(pizzaService.find(i));
            }


        }

    }
}
