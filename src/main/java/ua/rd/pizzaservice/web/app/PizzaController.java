package ua.rd.pizzaservice.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.services.CustomerService;
import ua.rd.pizzaservice.services.PizzaService;

/**
 * @author Serhii_Mykhliuk
 */
@Controller
public class PizzaController {
//    http://localhost:8081/s-pizza-service/app/hello

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/hello")
    //@ResponseBody  //если без jsp
    public String hello() {
        //возвращаемая строка воспринимается как имя view, дальше срабатывает  prefix и suffix
        return "hello";
    }

//    @RequestMapping("/pizzas")
//    public ModelAndView pizzas() {
//        ModelAndView mv = new ModelAndView("pizzas");
//        mv.setStatus(HttpStatus.OK);
//        mv.addObject("pizzaList", pizzaService.findAll());
//
//        System.out.println(pizzaService.findAll());
//        return mv;
//    }

    //другой вариант /pizzas
    @RequestMapping("/pizzas")
    public String pizzas(Model model) {
        model.addAttribute("pizzaList", pizzaService.findAll());

        System.out.println(pizzaService.findAll());
        return "pizzas";
    }


    @RequestMapping("/create")
    public String create() {
        //model.addAttribute("pet", pet);
        return "modifyPizza";  //расценивается как имя view, желательно не хардкодить, но оно не привязано
    }

    @RequestMapping(value = "/addNew", method = RequestMethod.POST)
    public String addNew(@ModelAttribute Pizza pizza) {  //сделай сам объект из аттрибутов
        System.out.println(pizza);
        pizzaService.save(pizza);

        return "redirect:pizzas";  //расценивается как имя view, желательно не хардкодить, но оно не привязано
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
