package ua.rd.pizzaservice.web.rest;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Serhii_Mykhliuk
 */

@Controller
public class PizzaRESTController {
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        // http://localhost:8081/s-pizza-service/rest/hello
        //текст это не вью, а просто хотим вернуть в респонс, будет как output writer
        return "<b>Hello from REST Controller!</b>";
    }

}
