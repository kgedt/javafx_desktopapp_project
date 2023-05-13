package project.files.database;

import project.files.customer.Order;
import project.files.customer.Product;

import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static project.files.Helper.cypher;
import static project.files.database.DbConst.*;

public class DbHandler {
    static Connection dbConnection;

    public static Connection getDbConnection() throws SQLException {
        String connectionString = "jdbc:postgresql://" + DbConnectInfo.DB_HOST + ":"
                + DbConnectInfo.DB_PORT + "/" + DbConnectInfo.DB_NAME;

        // Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, DbConnectInfo.DB_USER, DbConnectInfo.DB_PASS);

        return dbConnection;
    }

    public static void addCustomer(String login, String password, Double balance, String firstName, String lastName) {
        String insertQuery =
                "INSERT INTO " + CUSTOMER_TABLENAME + " ("
                        + CUSTOMER_LOGIN + ", "
                        + CUSTOMER_PASSWORD + ", "
                        + CUSTOMER_BALANCE + ", "
                        + CUSTOMER_FIRSTNAME + ", "
                        + CUSTOMER_LASTNAME + ") "
                        + "VALUES (?, ?, ?, ?, ?);";


        try (PreparedStatement prSt = getDbConnection().prepareStatement(insertQuery)) {
            int indCounter = 1;
            prSt.setString(indCounter++, login);
            prSt.setString(indCounter++, cypher(password));
            prSt.setDouble(indCounter++, balance);
            prSt.setString(indCounter++, firstName);
            prSt.setString(indCounter++, lastName);

            prSt.executeUpdate();

        } catch (SQLException e) {
//            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            System.out.println("Values into " + CUSTOMER_TABLENAME + " successfully added!");
        }

    }


    public static String getCustomerColumn(String login, String column) {
        String result = "";
        String selectFirstnameQuery =
                "SELECT " + column.strip() + " FROM " + CUSTOMER_TABLENAME +
                        " WHERE " + "login = '" + login.strip() + "';";

        try (PreparedStatement prSt = getDbConnection().prepareStatement(selectFirstnameQuery)) {
            ResultSet rs = prSt.executeQuery();

            rs.next();
            result = rs.getString(column);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    public static Product getProductById(Integer id) throws SQLException {
//        Product prod = new Product();
        String selectQuery = "SELECT " + PRODUCT_ID + ", " + PRODUCT_TITLE + ", " + PRODUCT_PRICE + " " +
                " FROM " + PRODUCT_TABLENAME + " " +
                " WHERE " + PRODUCT_ID + " = " + id.toString() + ";";



        try (PreparedStatement prSt = getDbConnection().prepareStatement(selectQuery)) {
            ResultSet rs = prSt.executeQuery();
            while (rs.next()) {
                //                prod.setId(rs.getInt(PRODUCT_ID));
//                prod.setTitle(rs.getString(PRODUCT_TITLE));
//                prod.setPrice(rs.getDouble(PRODUCT_PRICE));

                return new Product(rs.getInt(PRODUCT_ID), rs.getString(PRODUCT_TITLE), rs.getDouble(PRODUCT_PRICE));
            }
        }
        return new Product();
    }

    public static List<Product> getAllProductList() throws SQLException {
        List<Product> resultList = new ArrayList<>();
        for (int i = 1; i <= maxId(PRODUCT_TABLENAME, PRODUCT_ID); i++) {
            resultList.add(getProductById(i));
        }

        return resultList;
    }

    public static List<Product> getOrdersById(Integer id) {
        List<Product> resultList = new ArrayList<>();
        String selectQuery = " SELECT products.product_id, products.title, products.price, orders.quantity, orders.order_id " +
        "FROM orders " +
        " INNER JOIN products ON orders.product_id = products.product_id " +
        " INNER JOIN customers ON orders.customer_id = customers.customer_id " +
        " WHERE orders.customer_id = " + id + ";";

        try (PreparedStatement prSt = getDbConnection().prepareStatement(selectQuery)) {
            ResultSet rs = prSt.executeQuery();

            while (rs.next()) {
                resultList.add(new Product(rs.getInt(PRODUCT_ID),
                                            rs.getString(PRODUCT_TITLE),
                                            rs.getDouble(PRODUCT_PRICE),
                                            rs.getInt(ORDER_QUANTITY),
                                            rs.getInt(ORDER_ID)));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultList;
    }

    public static void addOrders(List<Order> orders) {
        String insertQuery = "INSERT INTO orders (customer_id, product_id, quantity) " +
                             " VALUES (?, ?, ?);";

        for (Order cur: orders) {
            try (PreparedStatement prSt = getDbConnection().prepareStatement(insertQuery)) {
                int cnt = 1;
                prSt.setInt(cnt++, cur.getCustomer_id());
                prSt.setInt(cnt++, cur.getProduct_id());
                prSt.setInt(cnt++, cur.getQuantity());

                prSt.executeUpdate();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void orderUpdate(Integer order_id, Integer quantity) {
        String query = "";
        if (quantity != 0) {
            query = "UPDATE orders " +
                    "SET quantity = " + quantity +
                    "WHERE order_id = " + order_id + ";";
        } else {
            query = "DELETE FROM orders WHERE order_id = " + order_id + ";";
        }

        try(PreparedStatement prSt = getDbConnection().prepareStatement(query)) {
            prSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /*
    SELECT products.product_id, products.title, products.price
FROM orders
INNER JOIN products ON orders.product_id = products.product_id
INNER JOIN customers ON orders.customer_id = customers.customer_id
WHERE orders.customer_id = 1;
    */

    public static int maxId(String tableName, String idName) throws SQLException {
        int res = -1;

        String selectQuery = "SELECT MAX(" + idName + ") FROM " + tableName + ";";
        PreparedStatement prSt = getDbConnection().prepareStatement(selectQuery);

        ResultSet rs = prSt.executeQuery();

        while (rs.next()) {
            res = rs.getInt("max");
        }

        return res;
    }

    public static boolean customerIsExist(String enteredLogin) {
        String selectQuery = "SELECT " + CUSTOMER_LOGIN + " " +
                " FROM " + CUSTOMER_TABLENAME + ";";
        boolean result = false;
        try (PreparedStatement prSt = getDbConnection().prepareStatement(selectQuery)) {
            ResultSet rs = prSt.executeQuery();
            while (rs.next()) {
                if (enteredLogin.equals(rs.getString("login")))
                    return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

}




/*
CREATE TABLE Customers (
	customer_id SERIAL PRIMARY KEY,
	login TEXT NOT NULL UNIQUE,
	password TEXT NOT NULL,
	balance DECIMAL(20, 2) DEFAULT 0.0,
	first_name TEXT NOT NULL,
	last_name TEXT NOT NULL
);

CREATE TABLE Products (
	product_id SERIAL PRIMARY KEY,
	title TEXT NOT NULL UNIQUE,
	price DECIMAL(20, 2) NOT NULL
);

CREATE TABLE Orders (
	order_id SERIAL PRIMARY KEY,
	customer_id INT NOT NULL,
	product_id INT NOT NULL,
	is_bought BOOLEAN NOT NULL,
	FOREIGN KEY (customer_id) REFERENCES Customers (customer_id),
	FOREIGN KEY (product_id) REFERENCES Products (product_id)
);
*/