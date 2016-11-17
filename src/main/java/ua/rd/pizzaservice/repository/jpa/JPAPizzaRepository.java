package ua.rd.pizzaservice.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.Pizza;
import ua.rd.pizzaservice.repository.PizzaRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("pizzaRepository")
public class JPAPizzaRepository implements PizzaRepository {

//    @PersistenceUnit
//    private EntityManagerFactory emf;

    //если с одного метода вызываем другой метод должен быть тот же самый ентитименеджер
    @PersistenceContext //чтобы заинжектился, можно указываь имя персистенс юнита, если несколько
    private EntityManager em;  // в em при обращении к транзакшнл будет каждый раз записываться новый инстанс


    @Override
    public Pizza findById(Integer id) {
        return em.find(Pizza.class, id);

    }

    @Override
    public List<Pizza> findAll() {
        TypedQuery<Pizza> query = em.createNamedQuery("Pizza.findAll", Pizza.class);
        return query.getResultList();
    }

    //должен возвращать пицуу с ИД, если медот ничего не возвращает, то не должен менять состояние объекта
    @Override
    @Transactional  //надо, если хотим чтобы все методы были транзакционными, аннотакция над классом
    public Pizza save(Pizza pizza) {
        Pizza newPizza = em.merge(pizza);
        return newPizza;
    }
}
