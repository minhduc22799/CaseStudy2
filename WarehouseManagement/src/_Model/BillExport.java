package _Model;

import java.time.LocalDate;

public class BillExport extends Bill{
    private static int INDEX = 0;
    private long id;

    public BillExport() {
    }

    public BillExport(String name, Double price, LocalDate date, int amount, Product product, long id) {
        super(name, price, date, amount, product);
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}
