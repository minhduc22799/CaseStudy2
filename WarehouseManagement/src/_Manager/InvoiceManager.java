package _Manager;

import _IOFile.IOFile;
import _Model.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class InvoiceManager {
    public ArrayList<ImportInvoice> listInvoicesImport = new ArrayList<>();

    public ArrayList<ExportInvoice> listInvoicesExport = new ArrayList<>();
    public ArrayList<ArrayList<DetailedInvoice>> listDetailInvoiceImport = new ArrayList<>();
    public ArrayList<ArrayList<DetailedInvoice>> listDetailInvoiceExport = new ArrayList<>();
    public IOFile<ImportInvoice> ioFileImport = new IOFile<>();
    public IOFile<ExportInvoice> ioFileExport = new IOFile<>();
    public IOFile<ArrayList<DetailedInvoice>> ioFileDetail = new IOFile<>();
    public int count;
    public double sumTotalImport;
    public double sumTotalExport;


    public InvoiceManager() {
    }

    private void readFile() {
        listInvoicesImport = ioFileImport.readFile("src/File/bill.txt");
        listInvoicesExport = ioFileExport.readFile("src/File/billExport.txt");
        listDetailInvoiceImport = ioFileDetail.readFile("src/File/detail.txt");
        listDetailInvoiceExport = ioFileDetail.readFile("src/File/detailExport.txt");
    }

    public void addBillImport(ArrayList<Product> products, Scanner scanner) {
        readFile();
        count = listDetailInvoiceImport.size();
        createDetailInvoiceImport(products, scanner);

        System.out.println("Enter date import:(dd/MM/yyy)");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String dateInput = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateInput, formatter);
        ImportInvoice importInvoice = new ImportInvoice(date, listDetailInvoiceImport.get(count));
        if (listInvoicesImport.size() > 0) {
            importInvoice.setId((listInvoicesImport.get(listInvoicesImport.size() - 1)).getId() + 1);
        }
        if (importInvoice.getAmount() > 0) {
            listInvoicesImport.add(importInvoice);
            ioFileImport.writeFile(listInvoicesImport, "src/File/bill.txt");
            displayInvoiceImport();
        }
    }

    public void addBillExport(ArrayList<Product> products, Scanner scanner) {
        readFile();
        count = listDetailInvoiceExport.size();
        createDetailInvoiceExport(products, scanner);
        System.out.println("Enter date import:(dd/MM/yyy)");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String dateInput = scanner.nextLine();
        LocalDate date = LocalDate.parse(dateInput, formatter);
        ExportInvoice exportInvoice = new ExportInvoice(date, listDetailInvoiceExport.get(count));
        if (listInvoicesExport.size() > 0) {
            exportInvoice.setId((listInvoicesExport.get(listInvoicesExport.size() - 1)).getId() + 1);
        }
        if (exportInvoice.getAmount() > 0) {
            listInvoicesExport.add(exportInvoice);
            ioFileExport.writeFile(listInvoicesExport, "src/File/billExport.txt");
            displayInvoiceExport();
        } else {
            System.out.println("Quantity is not enough");
        }
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
                if (product.getQuantity() < amount) {
                    System.out.println("quantity is not enough");
                    break;
                }
                product.setQuantity(product.getQuantity() - amount);
                ProductManager.ioFile.writeFile(ProductManager.products, "src/File/product.txt");
            }
        } while (product != null);
        listDetailInvoiceExport.add(listDetailInvoice);
        ioFileDetail.writeFile(listDetailInvoiceExport, "src/File/detailExport.txt");
    }

    public void displayInvoiceImport() {
        readFile();
        double sumInvoice = 0;
        double temp = 0;
        System.out.printf("%-15s%-15s%-15s%s", "", "", "", "Import Invoice\n\n");
        for (ImportInvoice listInvoice : listInvoicesImport) {
            System.out.printf("\n%-10s%-20s%-20s%-18s%-15s%-15s%s", "ID", "Name", "Date", "Name Product", "Price",
                    "Amount", "Total");
            System.out.printf("\n%-10s%-20s%-20s", listInvoice.getId(),
                    listInvoice.getName(), listInvoice.getDate());

            double sumDetail = 0;
            for (int j = 0; j < listInvoice.getDetailedInvoices().size(); j++) {
                sumDetail += listInvoice.getDetailedInvoices().get(j).getTotal();
                System.out.printf("\n%-10s%-20s%-20s%-18s%-15s%-15s%s", "", "", "",
                        listInvoice.getDetailedInvoices().get(j).getProduct().getName(),
                        listInvoice.getDetailedInvoices().get(j).getPrice()
                        , listInvoice.getDetailedInvoices().get(j).getAmount(),
                        listInvoice.getDetailedInvoices().get(j).getTotal());

            }
            temp += sumDetail;
            System.out.println("\n------------------------------------------------------------------------------------" +
                    "--------------------");
            System.out.printf("\n%-10s%-20s%-20s%-18s%-15s%-12s%s", "Total Invoice", "", "", "", "", "", sumDetail + "\n");


        }
        sumInvoice += temp;
        sumTotalImport = sumInvoice;

        System.out.printf("\n%-10s%-20s%-20s%-18s%-15s%-15s%s", "Total", "", "", "", "", "", sumInvoice + "\n");


    }

    public void displayInvoiceExport() {
        readFile();
        double sumInvoice = 0;
        double temp = 0;
        System.out.printf("%-15s%-15s%-15s%s", "", "", "", "Export Invoice\n\n");
        for (ExportInvoice listInvoice : listInvoicesExport) {
            System.out.printf("\n%-10s%-20s%-20s%-18s%-15s%-15s%s", "ID", "Name", "Date", "Name Product", "Price",
                    "Amount", "Total");
            System.out.printf("\n%-10s%-20s%-20s", listInvoice.getId(),
                    listInvoice.getName(), listInvoice.getDate());
            double sumDetail = 0;
            for (int j = 0; j < listInvoice.getDetailedExport().size(); j++) {
                sumDetail += listInvoice.getDetailedExport().get(j).getTotal();
                sumTotalExport += listInvoice.getDetailedExport().get(j).getTotal();
                System.out.printf("\n%-10s%-20s%-20s%-18s%-15s%-15s%s", "", "", "",
                        listInvoice.getDetailedExport().get(j).getProduct().getName(),
                        listInvoice.getDetailedExport().get(j).getPrice()
                        , listInvoice.getDetailedExport().get(j).getAmount(),
                        listInvoice.getDetailedExport().get(j).getTotal());
            }
            System.out.println("\n------------------------------------------------------------------------------------" +
                    "--------------------");
            System.out.printf("\n%-10s%-20s%-20s%-18s%-15s%-12s%s", "Total Invoice", "", "", "", "", "", sumDetail + "\n");
            temp += sumDetail;
        }
        sumInvoice += temp;
        sumTotalExport = sumInvoice;
        System.out.printf("\n%-10s%-20s%-20s%-18s%-15s%-15s%s", "Total", "", "", "", "", "", sumInvoice + "\n");
    }

    public void sumProfit() {
        double sumProfit;
        displayInvoiceImport();
        displayInvoiceExport();
        sumProfit = sumTotalExport - sumTotalImport;
        System.out.printf("\n%-10s%-20s%-20s%-18s%-15s%-13s%s", "Total Profit", "", "", "", "", "", sumProfit + "\n");
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
