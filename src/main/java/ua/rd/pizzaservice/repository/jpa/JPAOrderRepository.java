package ua.rd.pizzaservice.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.order.Order;
import ua.rd.pizzaservice.repository.OrderRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("orderRepository")
public class JPAOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Order findById(Long id) {
        return em.find(Order.class, id);
    }

    @Override
    @Transactional
    public Order save(Order order) {
        Order newOrder = em.merge(order);
        return newOrder;
    }
}
