package project.files.customer;

public class Customer {
    public static Integer id;
    public static String login;
    public static Double balance;
    public static String firstName;
    public static String lastName;
    public static Order[] orders;

    private Customer() {
    }

    public Double getBalance() {
        return balance;
    }

    public Order[] getOrders() {
        return orders;
    }

    public Integer getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

}
