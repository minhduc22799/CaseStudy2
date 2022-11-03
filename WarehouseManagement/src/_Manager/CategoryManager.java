package _Manager;

import _IOFile.IOFile;
import _Model.Category;


import java.util.ArrayList;
import java.util.Scanner;

public class CategoryManager {

    public static ArrayList<Category> categories = new ArrayList<>();
    public static IOFile<Category> ioFile = new IOFile<>();

    public CategoryManager() {
        categories = ioFile.readFile("src/File/category.txt");
    }

    public void addCategory(Scanner scanner) {
        System.out.println("Enter name category");
        String name = scanner.nextLine();
        Category category = new Category(name);
        if (categories.size() > 0){
            category.setId(categories.size());
        }
        categories.add(category);
        ioFile.writeFile(categories, "src/File/category.txt");
    }
    public  void displayCategory() {
        System.out.println("ID "+" Category");
        for (Category category : categories) {
            System.out.println(category.toString());
        }

    }
    public void  deleteCategory(Scanner scanner) {
        if (categories.size() == 0) {
            System.out.println("category null");
        } else {
            System.out.println("Enter id: ");
            long id = Long.parseLong(scanner.nextLine());
            Category categoryTemp = new Category();
                for (int i = 0; i < categories.size(); i++) {
                if (categories.get(i).getId() == id) {
                        categoryTemp = categories.get(i);
                    ioFile.writeFile(categories, "src/File/category.txt");
                }
            }
                categories.remove(categoryTemp);

            System.out.println("Xoa thanh cong");
        }
        displayCategory();
    }



}
