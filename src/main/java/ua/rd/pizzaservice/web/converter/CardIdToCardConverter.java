package ua.rd.pizzaservice.web.converter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import ua.rd.pizzaservice.domain.customer.CustomerCard;



/**
 * @author Serhii_Mykhliuk
 */

public class CardIdToCardConverter implements Converter<String, CustomerCard> {
//    @Autowired
//    private CustomerCardService service;

    @Override
    public CustomerCard convert(String id) {
//        try {
//            System.out.println("PizzaCard Converter!!!!!!!!");
//            return service.findById(Long.valueOf(id));
//        } catch (NumberFormatException ex) {
//            return null;
//        }
        return null;
    }
}