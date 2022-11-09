package _System;

import _Manager.CategoryManager;
import _Manager.InvoiceManager;
import _Manager.ProductManager;

import java.util.Scanner;

public class MenuSystems {

    public static ProductManager productManager = new ProductManager();
    public static CategoryManager categoryManager = new CategoryManager();
    public static InvoiceManager invoiceManager = new InvoiceManager();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        do {
            String format = "│ %-45s │\n";
            System.out.println("┌───────────────────────────────────────────────┐");
            System.out.println("│                     HOME PAGE                 │");
            System.out.println("├───────────────────────────────────────────────┤");
            System.out.printf(format, "▶[1]. Product Manager.");
            System.out.printf(format, "▶[2]. Category Manager.");
            System.out.printf(format, "▶[3]. Invoice Manager.");
            System.out.printf(format, "▶[0]. Exit.");
            System.out.println("└───────────────────────────────────────────────┘");
            System.out.println("▶ Enter your choice:");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 4 || choice < 0 ) {
                    System.err.println("Over selection!");
                }
                switch (choice) {
                    case 1:
                        menuProduct(scanner);
                        break;
                    case 2:
                        menuCategory(scanner);
                        break;
                    case 3:
                        menuInvoice(scanner);
                        break;
                    case 0:
                        System.exit(0);
                }
            } catch (Exception e) {
                System.err.println("Please re-enter the selection!");
            }
        } while (true);
    }


    public static void menuCategory(Scanner scanner) {
        int choice2;
        do {
            try {
                String format = "│ %-45s │\n";
                System.out.println("┌-----------------------------------------------┐");
                System.out.println("│                Category Manager               │");
                System.out.println("├-----------------------------------------------┤");
                System.out.printf(format, "▶[1]. Add category.");
                System.out.printf(format, "▶[2]. Delete Category.");
                System.out.printf(format, "▶[3]. Display Category.");
                System.out.printf(format, "▶[0]. Return.");
                System.out.println("└-----------------------------------------------┘");
                System.out.println("▶ Enter your choice:");
                choice2 = Integer.parseInt(scanner.nextLine());
                if (choice2 == 0) {
                    break;
                } else if (choice2 >= 4 || choice2 < 0) {
                    System.err.println("Over selection!");
                }
                switch (choice2) {
                    case 1:
                        categoryManager.addCategory(scanner);
                        break;
                    case 2:
                        categoryManager.deleteCategory(scanner);
                        break;
                    case 3:
                        CategoryManager.displayCategory();
                        break;
                }
            } catch (Exception e) {
                System.err.println("Please re-enter choice: ");
            }
        } while (true);
    }

    public static void menuProduct(Scanner scanner) {
        int choice1;
        do {
            try {
                String format = "│ %-45s │\n";
                System.out.println("┌-----------------------------------------------┐");
                System.out.println("│                  Product Manager              │");
                System.out.println("├-----------------------------------------------┤");
                System.out.printf(format, "▶[1]. Add Product.");
                System.out.printf(format, "▶[2]. Update Product.");
                System.out.printf(format, "▶[3]. Delete Product.");
                System.out.printf(format, "▶[4]. Find Product.");
                System.out.printf(format, "▶[5]. Display Product.");
                System.out.printf(format, "▶[0]. Return.");
                System.out.println("└-----------------------------------------------┘");
                System.out.println("▶ Enter your choice:");
                choice1 = Integer.parseInt(scanner.nextLine());
                if (choice1 == 0) {
                    break;
                } else if (choice1 >= 6 || choice1 < 0 ) {
                    System.err.println("Over selection!");
                }
                switch (choice1) {
                    case 1:
                        productManager.addProduct(CategoryManager.categories,scanner);
                        break;
                    case 2:
                        productManager.updateProduct(CategoryManager.categories,scanner);
                        break;
                    case 3:
                        productManager.deleteProduct(scanner);
                        break;
                    case 4:
                        productManager.displayProductByNameContaining(scanner);
                        break;
                    case 5:
                        productManager.displayProduct();
                        break;
                }
            } catch (Exception e) {
                System.err.println("Please re-enter choice");
            }
        } while (true);
    }

    public static void menuInvoice(Scanner scanner) {
        int choice3;
        do {
            try {
                String format = "│ %-45s │\n";
                System.out.println("┌-----------------------------------------------┐");
                System.out.println("│                   MENU USER                   │");
                System.out.println("├-----------------------------------------------┤");
                System.out.printf(format, "▶[1]. Add Import Invoice.");
                System.out.printf(format, "▶[2]. Add Export Invoice.");
                System.out.printf(format, "▶[3]. Display Import Invoice.");
                System.out.printf(format, "▶[4]. Display Export Invoice.");
                System.out.printf(format, "▶[5]. profit.");
                System.out.printf(format, "▶[0]. Back.");
                System.out.println("└-----------------------------------------------┘");
                System.out.println("▶ Enter your choice:");
                choice3 = Integer.parseInt(scanner.nextLine());
                if (choice3 == 0) {
                    break;
                } else if (choice3 >= 6 || choice3 < 0 ) {
                    System.err.println("Over selection!");
                }
                switch (choice3) {
                    case 1:
                        invoiceManager.addBillImport(ProductManager.products,scanner);
                        break;
                    case 2:
                        invoiceManager.addBillExport(ProductManager.products,scanner);
                        break;
                    case 3:
                       invoiceManager.displayInvoiceImport();
                        break;
                    case 4:
                        invoiceManager.displayInvoiceExport();
                        break;
                    case 5:
                        invoiceManager.sumProfit();
                        break;
                }
            } catch (Exception e) {
                System.err.println("Please re-enter choice");
            }
        } while (true) ;
    }
}

