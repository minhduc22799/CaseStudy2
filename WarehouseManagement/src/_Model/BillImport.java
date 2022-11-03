package _Model;

import java.time.LocalDate;

public class BillImport extends Bill{
    private static int INDEX = 0;
    private long id;

    public BillImport() {
    }

    public BillImport(String name, Double price, LocalDate date, int amount, Product product, long id) {
        super(name, price, date, amount, product);
        this.id = Long.valueOf(++INDEX);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
