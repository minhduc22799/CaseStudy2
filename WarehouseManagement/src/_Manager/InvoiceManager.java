package _Manager;

import _IOFile.IOFile;
import _Model.DetailedInvoice;
import _Model.Invoice;
import _Model.ImportInvoice;

import _Model.Product;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InvoiceManager {
    public static ArrayList<Invoice> listInvoices = new ArrayList<>();
    public static ArrayList<DetailedInvoice> listDetailInvoice = new ArrayList<>();

    public static IOFile<Invoice> ioFile = new IOFile<>();
    public static IOFile<DetailedInvoice> ioFileDetail = new IOFile<>();

    public InvoiceManager() {
        listInvoices = ioFile.readFile("src/File/bill.txt");
        listDetailInvoice = ioFileDetail.readFile("src/File/detail.txt");
    }

    public void addBillImport(ArrayList<Product> products, Scanner scanner) {
        createDetailInvoice(products,scanner);
        System.out.println("Enter date import:(dd-MM-yyy)");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String dateInput =scanner.nextLine();
        LocalDate date = LocalDate.parse(dateInput,formatter);
        Invoice importInvoice = new ImportInvoice(date,listDetailInvoice);
        if (listInvoices.size() > 0) {
            if (importInvoice instanceof ImportInvoice) {
                if (listInvoices.get(listInvoices.size() - 1) instanceof ImportInvoice) {
                    ((ImportInvoice) importInvoice).setId(((ImportInvoice) listInvoices.get(listInvoices.size() - 1)).getId()+1);
                }

            }
        }
        listInvoices.add(importInvoice);
        ioFile.writeFile(listInvoices,"src/File/bill.txt");
        displayInvoice();

    }

    public void createDetailInvoice(ArrayList<Product> products, Scanner scanner) {
        Product product;
        do {
            System.out.println("Enter product: ");
            product = getProductByIndex(products, scanner);
            if (product != null) {
                System.out.println("Enter amount import: ");
                int amount = Integer.parseInt(scanner.nextLine());
                System.out.println("Enter price import: ");
                double price = Double.parseDouble(scanner.nextLine());
                DetailedInvoice detailedInvoice = new DetailedInvoice(price, amount, product);

                listDetailInvoice.add(detailedInvoice);
                product.setQuantity(product.getQuantity() + amount);
                ProductManager.ioFile.writeFile(ProductManager.products,"src/File/product.txt");
            }
        } while (product != null);
        ioFileDetail.writeFile(listDetailInvoice,"src/File/detail.txt");
    }

    public static void displayDetail() {
        double sumTotal = 0;
        for (int i = 0; i <listDetailInvoice.size() ; i++) {
            sumTotal += listDetailInvoice.get(i).getTotal();
            System.out.printf("\n%-10s%-20s%-20s%-18s%-15s%-15s%s","","","",listDetailInvoice.get(i).getProduct().getName(),listDetailInvoice.get(i).getPrice()
                    ,listDetailInvoice.get(i).getAmount(), listDetailInvoice.get(i).getTotal());
        }
        System.out.println("\n--------------------------------------------------------------------------------------------------------");
        System.out.printf("%-10s%-20s%-20s%-18s%-15s%-15s%s","Total","","","","","",sumTotal+"\n");
    }


    public static void displayInvoice() {
        System.out.printf("\n%-10s%-20s%-20s%-18s%-15s%-15s%s", "ID", "Name","Date", "Name Product", "Price", "Amount", "Total");
        for (int i = 0; i < listInvoices.size(); i++) {
            if (listInvoices.get(i) instanceof ImportInvoice) {
                System.out.printf("\n%-10s%-20s%-20s", ((ImportInvoice) listInvoices.get(i)).getId(),
                        listInvoices.get(i).getName(),listInvoices.get(i).getDate());
                displayDetail();
            }
        }

    }

    private Product getProductByIndex(ArrayList<Product> products, Scanner scanner) {
        for (int i = 0; i < products.size(); i++) {
            System.out.println(i + 1 + ". " + products.get(i).getName());
        }
        System.out.println("0. Not choice");
        int choice;
        try {
            do {
                System.out.println("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) {
                    return null;
                } else if (choice > 0 && choice <= products.size()) {
                    return products.get(choice - 1);
                }
                System.err.println("Please re-enter your selection!");
            } while (choice < 0 || choice > products.size());
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

}
