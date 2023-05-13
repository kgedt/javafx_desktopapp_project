package project.files.database;

public class DbConst {
    private DbConst() {}

    public static final String CUSTOMER_TABLENAME = "Customers";
    public static final String CUSTOMER_ID = "customer_id";
    public static final String CUSTOMER_LOGIN = "login";
    public static final String CUSTOMER_BALANCE = "balance";
    public static final String CUSTOMER_PASSWORD = "password";
    public static final String CUSTOMER_FIRSTNAME = "first_name";
    public static final String CUSTOMER_LASTNAME = "last_name";


    public static final String PRODUCT_TABLENAME = "Products";
    public static final String PRODUCT_ID = "product_id";
    public static final String PRODUCT_TITLE = "title";
    public static final String PRODUCT_PRICE = "price";


    public static final String ORDER_TABLENAME = "Orders";
    public static final String ORDER_ID = "order_id";
    public static final String ORDER_CUSTOMER_ID = "customer_id";
    public static final String ORDER_PRODUCT_ID = "product_id";
    public static final String ORDER_QUANTITY = "quantity";


    public static final String PURCHASE_TABLENAME = "purchase_history";
    public static final String PURCHASE_ID = "purchase_id";
    public static final String PURCHASE_DATE = "purchase_date";
    public static final String PURCHASE_QUANTITY = "purchase_quantity";

}
