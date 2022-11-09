package _Manager;

import _IOFile.IOFile;
import _Model.Category;
import _Model.Product;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductManager {

    public static ArrayList<Product> products ;
    public static IOFile<Product> ioFile = new IOFile<>();


    public ProductManager(){
        products = ioFile.readFile("src/File/product.txt");
    }
    public void addProduct(ArrayList<Category> categories, Scanner scanner) {
        Category category;
        try {
            System.out.println("Enter new category: ");
            category = getCategoryByIndex(categories, scanner);
            if (category!=null) {
                System.out.println("Enter new name: ");
                String name = scanner.nextLine();
                if (!checkName(name)) {
                    System.out.println("Enter new price: ");
                    Double price = Double.parseDouble(scanner.nextLine());
                    System.out.println("Enter new quantity: ");
                    int quantity = Integer.parseInt(scanner.nextLine());
                    Product product = new Product(name, price, quantity, category);
                    if (products.size() > 0) {
                        product.setId(products.get(products.size() - 1).getId() + 1);
                    }
                    products.add(product);
                    ioFile.writeFile(products, "src/File/product.txt");
                }
                else {
                    System.out.println("Product existed");
                }
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.out.println(e.getMessage());
        }
        displayProduct();
    }

    private Category getCategoryByIndex(ArrayList<Category> categories, Scanner scanner) {
        for (int i = 0; i < categories.size(); i++) {
            System.out.println(i+1 + ". " + categories.get(i).getName());
        }
        System.out.println("0. Not choice");
        int choice;
        try {
            do {
                System.out.println("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                if (choice == 0) {
                    return null;
                }
                if (choice > 0 && choice <= categories.size()) {
                    return categories.get(choice - 1);
                }

                System.err.println("Please re-enter your selection!");
            } while (choice < 0 || choice > categories.size());
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public void updateProduct(ArrayList<Category> categories, Scanner scanner) {
        try {
            System.out.println("Enter id of product you want update: ");
            Long id = Long.parseLong(scanner.nextLine());
            Product productUpdate;
            if ((productUpdate = checkExist(id)) != null) {
                System.out.println("Enter new name: ");
                String name = scanner.nextLine();
                if (!name.equals("")) {
                    productUpdate.setName(name);
                }
                System.out.println("Enter new price: ");
                String price = scanner.nextLine();
                if (!price.equals("")) {
                    productUpdate.setPrice(Double.parseDouble(price));
                }
                System.out.println("Enter new quantity: ");
                String quantity = scanner.nextLine();
                if (!quantity.equals("")) {
                    productUpdate.setQuantity(Integer.parseInt(quantity));
                }
                System.out.println("Enter new category: ");
                Category category;
                if ((category = getCategoryByIndex(categories, scanner)) != null) {
                    productUpdate.setCategory(category);
                }
                ioFile.writeFile(products, "src/File/product.txt");

            } else {
                System.err.println("Not exist product have id!");
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
        displayProduct();
    }

    public void deleteProduct(Scanner scanner) {
        try {
            System.out.println("Enter id of product you want delete: ");
            Long id = Long.parseLong(scanner.nextLine());
            Product productDelete;
            if ((productDelete = checkExist(id)) != null) {
                products.remove(productDelete);
                ioFile.writeFile(products, "src/File/product.txt");


            } else {
                System.err.println("Not exist product have id!");
            }
        } catch (NumberFormatException | InputMismatchException e) {
            System.err.println(e.getMessage());
        }
        displayProduct();
    }

    private Product checkExist(Long id) {
        for (Product p : products) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }


    public void displayProductByNameContaining(Scanner scanner) {
        System.out.println("Enter character you want search: ");
        String search = scanner.nextLine();
        System.out.println("List product have name contains " + search + ": \n");
        System.out.printf("%-10s%-10s%-15s%-20s%s", "ID", "Name", "Price", "Quantity", "Category\n\n");
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(search.toLowerCase())) {
                p.display();
            }
        }
    }

    public void displayProduct() {
        System.out.printf("%-10s%-10s%-15s%-20s%s", "ID", "Name", "Price", "Quantity", "Category\n\n");
        for (Product product : products) {
            product.display();
        }
    }

    private boolean checkName(String name){
        for (int i = 0; i <products.size() ; i++) {
            if (products.get(i).getName().equalsIgnoreCase(name)){
                return true;
            }
        }return false;
    }


}
