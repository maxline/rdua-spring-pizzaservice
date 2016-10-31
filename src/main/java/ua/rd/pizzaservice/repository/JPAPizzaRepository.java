package ua.rd.pizzaservice.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ua.rd.pizzaservice.domain.Pizza;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

@Repository("jpaPizzaRepository")
public class JPAPizzaRepository implements PizzaRepository {

//    @PersistenceUnit
//    private EntityManagerFactory emf;

    //если с одного метода вызываем другой метод должен быть тот же самый ентитименеджер
    @PersistenceContext //чтобы заинжектился, можно указываь имя персистенс юнита, если несколько
    private EntityManager em;  // в em при обращении к транзакшнл будет каждый раз записываться новый инстанс


    @Override
    public Pizza find(Integer id) {
        return em.find(Pizza.class, id);

    }

    //должен возвращать пицуу с ИД, если медот ничего не возвращает, то не должен менять состояние объекта
    @Override
    @Transactional  //надо, если хотим чтобы все методы были транзакционными, аннотакция над классом
    public Pizza save(Pizza pizza) {
        Pizza newPizza =  em.merge(pizza);
        return newPizza;
    }
}
