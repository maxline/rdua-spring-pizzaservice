package ua.rd.pizzaservice.domain.order;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.domain.customer.Customer;
import ua.rd.pizzaservice.domain.discount.Discount;
import ua.rd.pizzaservice.domain.discount.DiscountCardBalance;
import ua.rd.pizzaservice.domain.discount.DiscountFourPizza;
import ua.rd.pizzaservice.domain.order.StatusManager.Status;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;

import static ua.rd.pizzaservice.domain.order.StatusManager.Status.NEW;
import static ua.rd.pizzaservice.domain.order.StatusManager.isTransitionAllowed;

/**
 * @author andrii
 */
@Entity
@Component
@Scope("prototype")
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private Status status;

    @ElementCollection
    @CollectionTable(name = "orders_to_pizzas")
    @MapKeyJoinColumn(name = "pizza_id")
    @Column(name = "quantity")
    //@Cascade({CascadeType.PERSIST, CascadeType.MERGE})
//    @ElementCollection(fetch = FetchType.EAGER)
//    @CollectionTable(name = "pizzas_quantities", joinColumns = @JoinColumn(name = "order_id", nullable = false))
//    @MapKeyJoinColumn(name = "pizza_id")
//    @Column(name = "quantity", nullable = false)
    private Map<Pizza, Integer> pizzas;

    @Transient
    List<Discount> discountList = Arrays.asList(new DiscountFourPizza(), new DiscountCardBalance());

    public Order() {
        id = 1L;  //todo
    }

    public Order(Customer customer, List<Pizza> pizzas, Status status) {
        this.pizzas = listToMap(pizzas);
        this.customer = customer;
        this.status = status;
    }

    public Order(Customer customer, List<Pizza> pizzas) {
        this(customer, pizzas, NEW);
    }

    @PostConstruct    //чтобы инит заработал через аннотакции
    public void init() {
        this.status = NEW;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPizzas(Map<Pizza, Integer> pizzas) {
        this.pizzas = pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = listToMap(pizzas);
    }

    private Map<Pizza, Integer> listToMap(List<Pizza> pizzasList) {
        Map<Pizza, Integer> map = new HashMap<>();
        for (Pizza pizza : pizzasList) {
            if (map.containsKey(pizza)) {
                int quantity = map.get(pizza);
                map.put(pizza, quantity + 1);
            } else {
                map.put(pizza, 1);
            }
        }
        return map;
    }

    public List<Pizza> getPizzas() {
        List<Pizza> pizzasList = new ArrayList<>();
        for (Pizza pizza : pizzas.keySet()) {
            for (int i = 0; i < pizzas.get(pizza); i++) {
                pizzasList.add(pizza);
            }
        }
        return pizzasList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public BigDecimal getPrice() {
        BigDecimal orderPrice = new BigDecimal("0.0");
        for (Pizza pizza : getPizzas()) {
            orderPrice = orderPrice.add(pizza.getPrice());
        }
        return orderPrice;
    }

    public BigDecimal getPriceWithDiscount() {
        return getPrice().subtract(calculateTotalDiscount());
    }

    private BigDecimal calculateTotalDiscount() {
        BigDecimal discountTotal = new BigDecimal("0.00");

        for (Discount discount : discountList) {
            discountTotal = discountTotal.add(discount.calculateDiscount(this));
        }
        return discountTotal;
    }

    public Status getStatus() {
        return status;
    }

    public void changeStatus(Status statusTo) {
        if (isTransitionAllowed(status, statusTo)) {
            status = statusTo;
        } else {
            throw new IllegalArgumentException("It is not allowed moving from " + status + " to " + statusTo + " status!");
        }
    }

    @Override
    public String toString() {
        return "Order{pizzas=" + pizzas + ". Price=" + getPriceWithDiscount() + ". Status=" + getStatus() + '}';
    }
}

