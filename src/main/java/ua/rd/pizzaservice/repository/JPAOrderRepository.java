package ua.rd.pizzaservice.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Sergey Mikhluk.
 */
@Repository("orderRepository")
public class JPAOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Order find(Long id) {
        return em.find(Order.class, id);
    }

    @Override
    @Transactional
    public Order save(Order order) {
        Order newOrder =  em.merge(order);
        return newOrder;
    }
}
