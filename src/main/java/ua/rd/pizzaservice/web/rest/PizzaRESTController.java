package ua.rd.pizzaservice.web.rest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.services.PizzaService;

import java.util.List;

import static org.springframework.hateoas.core.DummyInvocationUtils.methodOn;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@Secured("ROLE_ADMIN")
@RestController // чтобы не писать над каждым методом ResponseBody
public class PizzaRESTController {
    @Autowired
    private PizzaService pizzaService;

//    @Autowired
//    private PizzaRepository pizzaRepository;

    //можно специфицировать много параметров, например только гет запрос
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String[] hello() {
        // http://localhost:8081/s-pizza-service/rest/hello
        //текст это не вью, а просто хотим вернуть в респонс, будет как output writer
        //строку не захотело, сделали массив
        return new String[]{"Hello from REST Controller!"};
    }

//    @RequestMapping(value = "/pizza/{pizzaID}", method = RequestMethod.GET)
//    public Pizza pizza(@PathVariable("pizzaID") Integer pizzaID) {
//        //http://localhost:8081/s-pizza-service/rest/pizza/1
//        //надо связать плейсхолдер с переменной pizzaID
//        return pizzaService.findById(pizzaID);
//    }

    //добавим  код объека респонс ентити параметризуется тем объектом который реально находится в респонсе
    //плюс можем добавлять какие заголовки будет содержать

    @RequestMapping(value = "/pizza/{pizzaID}", method = RequestMethod.GET)
    public ResponseEntity<Pizza> find(@PathVariable("pizzaID") Integer pizzaID) {
        Pizza pizza = pizzaService.findById(pizzaID);
        if (pizza == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        //черз хатеоас появился набор дополлнительных аттрибутов
        Link link = linkTo(methodOn(PizzaRESTController.class).find(pizzaID)).withSelfRel();
        pizza.add(link);

        return new ResponseEntity<>(pizza, HttpStatus.FOUND);
    }


    @RequestMapping(value = "/pizzas}", method = RequestMethod.GET)
    public ResponseEntity<List<Pizza>> findAll() {
        List<Pizza> pizzas = pizzaService.findAll();
//todo дописать linkTo
//        Link link = linkTo(methodOn(PizzaRESTController.class).find(pizzaID)).withSelfRel();
//        pizza.add(link);
        //pizzas.forEach(p-> p.add(lintTo));

        return new ResponseEntity<>(pizzas, HttpStatus.FOUND);
    }

    //из реквеста формируем объект
//    @RequestMapping(value = "/pizza",  method = RequestMethod.POST,
//            consumes = "application/json"
//    )
//    public void pizza(@RequestBody Pizza pizza) {
//        System.out.println(pizza);
//    }


    //из реквеста формируем объект
    @RequestMapping(value = "/pizza", method = RequestMethod.POST,
            consumes = "application/json"
    )

    public ResponseEntity<Void> create(@RequestBody Pizza pizza,
                                       UriComponentsBuilder builder) {
        //todo поменять на pizzaService
        Pizza p = pizzaService.save(pizza);
        System.out.println(pizza);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/pizza/{id}").buildAndExpand(p.getPizzaId()).toUri());
        //хотим засетить Ид, помогает билдер
        //включаем хидер в респонсЕнтити

        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}
