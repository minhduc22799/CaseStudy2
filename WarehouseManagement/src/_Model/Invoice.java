package _Model;

import java.io.Serializable;
import java.time.LocalDate;

public class Invoice implements Serializable {
    private String name;
    private Double price;
    private LocalDate date;
    private int amount;
    private Product product;

    private Double total;

    public Invoice() {
    }

    public Invoice(String name, Double price, LocalDate date, int amount, Product product, Double total) {
        this.name = name;
        this.price = price;
        this.date = date;
        this.amount = amount;
        this.product = product;
        this.total = total;
    }

    public Invoice(String name, LocalDate date) {
        this.name = name;
        this.date = date;
    }

    public Invoice(Double price, int amount, Product product, Double total) {
        this.price = price;
        this.amount = amount;
        this.product = product;
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", date=" + date +
                ", amount=" + amount +
                ", product=" + product +
                '}';
    }
}
