package _Manager;

import _IOFile.IOFile;
import _Model.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InvoiceManager {
    public static ArrayList<Invoice> listInvoices = new ArrayList<>();
    public static ArrayList<ArrayList<DetailedInvoice>> listDetailInvoiceImport = new ArrayList<>();
    public static ArrayList<ArrayList<DetailedInvoice>> listDetailInvoiceExport = new ArrayList<>();
    public static IOFile<Invoice> ioFile = new IOFile<>();
    public static IOFile<ArrayList<DetailedInvoice>> ioFileDetail = new IOFile<>();
    public static int count ;
    public InvoiceManager() {

    }


    private static void readFile() {
        listInvoices = ioFile.readFile("src/File/bill.txt");
        listDetailInvoiceImport = ioFileDetail.readFile("src/File/detail.txt");
        listDetailInvoiceExport = ioFileDetail.readFile("src/File/detailExport.txt");
    }
    public void addBillImport(ArrayList<Product> products, Scanner scanner) {
        readFile();
        count=listDetailInvoiceImport.size()  ;
        createDetailInvoiceImport(products, scanner);
        System.out.println("Enter date import:(dd/MM/yyy)");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String dateInput = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateInput, formatter);
        ImportInvoice importInvoice = new ImportInvoice(date, listDetailInvoiceImport.get(count ));
        if (listInvoices.size() > 0) {
            if (listInvoices.get(listInvoices.size() - 1) instanceof ImportInvoice) {
                ( importInvoice).setId(((ImportInvoice) listInvoices.get(listInvoices.size() - 1)).getId() + 1);
            }

        }
        listInvoices.add(importInvoice);
        ioFile.writeFile(listInvoices, "src/File/bill.txt");
        displayInvoice();
    }


    public  void addBillExport(ArrayList<Product> products, Scanner scanner) {
        readFile();
        count=listDetailInvoiceExport.size()  ;
        createDetailInvoiceExport(products, scanner);
        System.out.println("Enter date import:(dd/MM/yyy)");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String dateInput = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateInput, formatter);
        ExportInvoice exportInvoice = new ExportInvoice(date, listDetailInvoiceExport.get(count ));
        if (listInvoices.size() > 0) {
            if (listInvoices.get(listInvoices.size() - 1) instanceof ExportInvoice) {
                ( exportInvoice).setId(((ExportInvoice) listInvoices.get(listInvoices.size() - 1)).getId() + 1);
            }
        }
        listInvoices.add(exportInvoice);
        ioFile.writeFile(listInvoices, "src/File/bill.txt");
        displayInvoiceExport();
    }

    public void createDetailInvoiceImport(ArrayList<Product> products, Scanner scanner) {
        Product product;
        ArrayList<DetailedInvoice> listDetailInvoice = new ArrayList<>();
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
                ProductManager.ioFile.writeFile(ProductManager.products, "src/File/product.txt");
            }
        } while (product != null);
        listDetailInvoiceImport.add(listDetailInvoice);
        ioFileDetail.writeFile(listDetailInvoiceImport, "src/File/detail.txt");
    }

    public void createDetailInvoiceExport(ArrayList<Product> products, Scanner scanner) {
        Product product;
        ArrayList<DetailedInvoice> listDetailInvoice = new ArrayList<>();
        do {
            System.out.println("Enter product: ");
            product = getProductByIndex(products, scanner);
            if (product != null) {
                System.out.println("Enter amount import: ");
                    int amount = Integer.parseInt(scanner.nextLine());
                    double price = product.getPrice();
                    DetailedInvoice detailedInvoice = new DetailedInvoice(price, amount, product);
                    listDetailInvoice.add(detailedInvoice);
                    product.setQuantity(product.getQuantity() - amount);
                    ProductManager.ioFile.writeFile(ProductManager.products, "src/File/product.txt");
                }
        } while (product != null);
        listDetailInvoiceExport.add(listDetailInvoice);
        ioFileDetail.writeFile(listDetailInvoiceExport, "src/File/detailExport.txt");
    }

    public static void displayInvoice() {
        readFile();
        double sumTotal = 0;
        for (Invoice listInvoice : listInvoices) {
            if (listInvoice instanceof ImportInvoice) {
                System.out.printf("\n%-10s%-20s%-20s%-18s%-15s%-15s%s", "ID", "Name", "Date", "Name Product", "Price",
                        "Amount", "Total");
                System.out.printf("\n%-10s%-20s%-20s", ((ImportInvoice) listInvoice).getId(),
                        listInvoice.getName(), listInvoice.getDate());
                for (int j = 0; j < ((ImportInvoice) listInvoice).getDetailedInvoices().size(); j++) {
                    sumTotal += ((ImportInvoice) listInvoice).getDetailedInvoices().get(j).getTotal();
                    System.out.printf("\n%-10s%-20s%-20s%-18s%-15s%-15s%s", "", "", "",
                            ((ImportInvoice) listInvoice).getDetailedInvoices().get(j).getProduct().getName(),
                            ((ImportInvoice) listInvoice).getDetailedInvoices().get(j).getPrice()
                            , ((ImportInvoice) listInvoice).getDetailedInvoices().get(j).getAmount(),
                            ((ImportInvoice) listInvoice).getDetailedInvoices().get(j).getTotal());
                }
                System.out.println("\n------------------------------------------------------------------------------------" +
                        "--------------------\n\n");
            }
        }
        System.out.printf("\n%-10s%-20s%-20s%-18s%-15s%-15s%s", "Total", "", "", "", "", "", sumTotal + "\n");
    }

    public  void displayInvoiceExport() {
        readFile();
        double sumTotal = 0;
        for (Invoice listInvoice : listInvoices) {
            if (listInvoice instanceof ExportInvoice) {
                System.out.printf("\n%-10s%-20s%-20s%-18s%-15s%-15s%s", "ID", "Name", "Date", "Name Product", "Price",
                        "Amount", "Total");
                System.out.printf("\n%-10s%-20s%-20s", ((ExportInvoice) listInvoice).getId(),
                        listInvoice.getName(), listInvoice.getDate());
                for (int j = 0; j < ((ExportInvoice) listInvoice).getDetailedExport().size(); j++) {
                    sumTotal += ((ExportInvoice) listInvoice).getDetailedExport().get(j).getTotal();
                    System.out.printf("\n%-10s%-20s%-20s%-18s%-15s%-15s%s", "", "", "",
                            ((ExportInvoice) listInvoice).getDetailedExport().get(j).getProduct().getName(),
                            ((ExportInvoice) listInvoice).getDetailedExport().get(j).getPrice()
                            , ((ExportInvoice) listInvoice).getDetailedExport().get(j).getAmount(),
                            ((ExportInvoice) listInvoice).getDetailedExport().get(j).getTotal());
                }
                System.out.println("\n------------------------------------------------------------------------------------" +
                        "--------------------\n\n");
            }
        }
        System.out.printf("\n%-10s%-20s%-20s%-18s%-15s%-15s%s", "Total", "", "", "", "", "", sumTotal + "\n");
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
