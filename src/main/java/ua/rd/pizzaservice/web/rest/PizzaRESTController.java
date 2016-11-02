package ua.rd.pizzaservice.web.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.services.PizzaService;


/**
 * @author Serhii_Mykhliuk
 */

@RestController // чтобы не писать над каждым методом ResponseBody
public class PizzaRESTController {
    @Autowired
    private PizzaService pizzaService;

    //можно специфицировать много параметров, например только гет запрос
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String[] hello() {
        // http://localhost:8081/s-pizza-service/rest/hello
        //текст это не вью, а просто хотим вернуть в респонс, будет как output writer
        //строку не захотело, сделали массив
        return new String[]{"Hello from REST Controller!"};
    }

    @RequestMapping(value = "/pizza/{pizzaID}", method = RequestMethod.GET)
    public Pizza pizza(@PathVariable("pizzaID") Integer pizzaID) {
        //http://localhost:8081/s-pizza-service/rest/pizza/1
        //надо связать плейсхолдер с переменной pizzaID
        return pizzaService.find(pizzaID);
    }

}
