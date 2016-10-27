package ua.rd.pizzaservice.domain;

/**
 * @author andrii
 */
public class Customer {
    private String name;
    private Address address;
    //private BigDecimal cardBalance;
    private CustomerCard customerCard;

    //todo проверить где создается customerCard, контейнер?
    public Customer(String name, Address address, CustomerCard customerCard) {
        this.name = name;
        this.address = address;
        this.customerCard = customerCard;
    }

//    public void increaseBalance(BigDecimal sum) {
//        customerCard.increaseBalance(sum);
//    }
//
//    public BigDecimal getBalance() {
//        return cardBalance;
//    }

    public String getName() {
        return name;
    }

    public CustomerCard getCustomerCard() {
        return customerCard;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "Customer{" + name + '\'' +
                ", address='" + address + '\'' +
                ", card balance ='" + customerCard.getBalance() + '\'' +
                '}';
    }
}
