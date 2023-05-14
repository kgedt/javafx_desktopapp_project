package project.files.comparators;

import project.files.customer.Product;

import java.util.Comparator;

public class ZAComparator implements Comparator<Product> {

    @Override
    public int compare(Product o1, Product o2) {
        if (o1.getTitle().compareTo(o2.getTitle()) > 0) return -1;
        else if (o1.getTitle().compareTo(o2.getTitle()) < 0) return 1;
        else return 0;
    }
}