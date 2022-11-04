package _Model;

import java.io.Serializable;
import java.time.LocalDate;

public class ExportInvoice extends Invoice implements Serializable {
    private static int INDEX = 0;
    private long id;

    public ExportInvoice() {
    }

    public ExportInvoice(Double price, LocalDate date, int amount, Product product, Double total) {
        super("Bill Export" + product.getName(), price, date, amount, product, total);
        this.id = Long.valueOf(++INDEX);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
