package ua.rd.pizzaservice;

import ua.rd.pizzaservice.domain.Address;
import ua.rd.pizzaservice.domain.Customer;
import ua.rd.pizzaservice.domain.CustomerCard;
import ua.rd.pizzaservice.domain.Pizza;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.math.BigDecimal;

/**
 *
 */
public class JPAAppRunner {
    public static void main(String[] args) {
        //pizzaExample();
        customerExample();
    }

    private static void customerExample() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");  //персистенс юнит который мы прописали в xml
        EntityManager em = emf.createEntityManager();

        Customer customer = new Customer("Adam", new Address("Earth"), new CustomerCard());

        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(customer);
        transaction.commit(); // update будет делаться на коммите

        em.close();
        emf.close();
    }

    private static void pizzaExample() {
        //надо поднять контейнер entity manager
        //чем меньше он живет тем лучше, каждый раз создается новый, не так как спринг, а потом будем инжектить

        //создаем фабрику
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa");  //персистенс юнит который мы прописали в xml
        EntityManager em = emf.createEntityManager();

        Pizza pizza = new Pizza(null, "Bavarian", Pizza.PizzaType.MEAT, new BigDecimal("0.00"));
        Pizza pizza2 = new Pizza(null, "Bavarian2", Pizza.PizzaType.MEAT, new BigDecimal("0.00"));
        //надо получить ентити менеджер, это как процедурное программирование, передаем параметр объект, и он с ним что-то делает
        //также можно передавать запросы, как нативные так и gpql
        //управляет жизненным циклом объекта
        //в спринге один контейнер и потокобезопасный, в хибернейте их много и они не потокобезопасны
        //не делать из разных потоков с одним ентити менеджером, лучше в каждом потоке создавать свой ентити менеджер
        //цикл жизни короткий, при следующей операции должны создавать заново
        //хранит и управляет состоянием всех объектов которые мы ему передали, они все будут лежать в его контексте


        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        System.out.println("Before persist " + pizza.getId());

        em.persist(pizza);  //сохраняет объект, кладет в Entity context, не в базу, по этому нужно создать транзакцию
        em.persist(pizza2); //два селекта будет, не оптимально (если знает что будет много сохраняться, делать диапазон   @SequenceGenerator сколько за раз брать
        em.flush(); // надо сохранить на сейчас
        pizza.setName("Hawai");  //сохраняет состояние на момент коммита! а не персист
        System.out.println(pizza.getId());

        //transaction.rollback();
        transaction.commit(); // update будет делаться на коммите


        //em.clear(); //чтобы подтягивал из базы а не из менеджера
        Pizza p = em.find(Pizza.class, 3);
        System.out.println(p == pizza);

        em.close();
        emf.close();
    }
}
