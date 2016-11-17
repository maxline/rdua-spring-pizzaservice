package ua.rd.pizzaservice.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.rd.pizzaservice.services.CustomerService;
import ua.rd.pizzaservice.services.PizzaService;

/**
 * @author Serhii_Mykhliuk
 */
@Controller
public class PizzaController {
//    http://localhost:8081/s-pizza-service/app/hello

    @Autowired
    PizzaService pizzaService;

    @Autowired
    CustomerService customerService;

    @RequestMapping("/hello")
    //@ResponseBody  //если без jsp
    public String hello() {
        //возвращаемая строка воспринимается как имя view, дальше срабатывает  prefix и suffix
        return "hello";
    }

    @RequestMapping("/pizzas")
    public ModelAndView pizzas() {
        ModelAndView mv = new ModelAndView("pizzas");
        mv.setStatus(HttpStatus.OK);
        mv.addObject("pizzaList", pizzaService.findAll());

        return mv;
    }

    //todo не получает список кастомеров
    @RequestMapping("/customers")
    public ModelAndView customers() {
//        http://localhost:8081/s-pizza-service/app/customers
        ModelAndView mv = new ModelAndView("customers");
        mv.setStatus(HttpStatus.OK);
        mv.addObject("customerList", customerService.findAll());
        System.out.println(customerService.findAll());
        return mv;
    }

//    public String pizzas(){
//        return "pizzas";
//    }
}
