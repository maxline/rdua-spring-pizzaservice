package ua.rd.pizzaservice.web.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    @RequestMapping(value="/edit/{pizzaId}", method = RequestMethod.GET)
    public String pizza(@PathVariable("pizzaId") Integer pizzaId, Model model) {
        model.addAttribute("pizza", pizzaService.findById(pizzaId));

        System.out.println(pizzaService.findById(pizzaId));
        return "modifyPizza";
    }

    @RequestMapping("/pizzas")
    public String pizzas(Model model) {
        model.addAttribute("pizzaList", pizzaService.findAll());

        System.out.println(pizzaService.findAll());
        return "pizzas";
    }



    @RequestMapping("/create")
    public String create() {
        //model.addAttribute("pet", pet);
        return "newPizza";  //расценивается как имя view, желательно не хардкодить, но оно не привязано
    }

    @RequestMapping(value = "/addNew", method = RequestMethod.POST)
    public String addNew(@ModelAttribute Pizza pizza, Model model) {  //сделай сам объект из аттрибутов
        System.out.println(pizza);
        pizzaService.save(pizza);
        model.addAttribute("status", "created");

        return "redirect:pizzas";  //расценивается как имя view, желательно не хардкодить, но оно не привязано
    }

    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public String modify(@ModelAttribute Pizza pizza, Model model) {  //сделай сам объект из аттрибутов
        System.out.println(pizza);
        pizzaService.save(pizza);
        model.addAttribute("status", "modified");

        return "redirect:pizzas";
    }

//    @ModelAttribute
//    public void status(@RequestParam(name="status", required = false)){
//        System.out.println(status);
//
//    }

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
