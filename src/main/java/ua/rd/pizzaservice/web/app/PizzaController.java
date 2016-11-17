package ua.rd.pizzaservice.web.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Serhii_Mykhliuk
 */
@Controller
public class PizzaController {
//    http://localhost:8081/s-pizza-service/app/hello

    @RequestMapping("/hello")
    //@ResponseBody  //если без jsp
    public String hello(){
        //возвращаемая строка воспринимается как имя view, дальше срабатывает  prefix и suffix
        return "hello";
    }
}
