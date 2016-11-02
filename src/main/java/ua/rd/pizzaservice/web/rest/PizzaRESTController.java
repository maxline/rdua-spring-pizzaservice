package ua.rd.pizzaservice.web.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Serhii_Mykhliuk
 */

@RestController // чтобы не писать над каждым методом ResponseBody
public class PizzaRESTController {
    //можно специфицировать много параметров, например только гет запрос
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String[] hello() {
        // http://localhost:8081/s-pizza-service/rest/hello
        //текст это не вью, а просто хотим вернуть в респонс, будет как output writer
        //строку не захотело, сделали массив
        return new String[]{"Hello from REST Controller!"};
    }


}
