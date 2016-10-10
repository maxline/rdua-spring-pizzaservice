package ua.rd.pizzaservice.services;

/**
 * @author andrii
 */
public class UsageSomeService {
    private SomeService someService;

    public UsageSomeService(SomeService someService) {
        this.someService = someService;
    }

    public void init() {
        System.out.println("Call: " + someService.getString());
    }

}
