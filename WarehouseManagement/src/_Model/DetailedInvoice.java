package _Model;

import java.io.Serializable;

public class DetailedInvoice extends Invoice implements Serializable {


    public DetailedInvoice(Double price, int amount, Product product) {
        super(price, amount, product,price*amount);
    }

    public DetailedInvoice() {
    }
}
