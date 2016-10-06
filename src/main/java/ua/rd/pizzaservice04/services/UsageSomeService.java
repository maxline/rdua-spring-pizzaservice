package ua.rd.pizzaservice04.services;

/**
 *
 */
public class UsageSomeService {
    private SomeService someService;

    public UsageSomeService(SomeService someService) {
        this.someService = someService;
    }

    //чтобы не доставвать этот бин из контекста
    //можем так сделать потому что инит вызывается когда объект уже создан
    public  void init(){
        System.out.println("Call usage: " + someService.getString());
    }

}
