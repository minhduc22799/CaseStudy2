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
        if (!checkName(name)){
        Category category = new Category(name);

        if (categories.size() > 0){
            category.setId(categories.get(categories.size()-1).getId() +1);
        }
        categories.add(category);
        ioFile.writeFile(categories, "src/File/category.txt");
    }else {
            System.out.println("Category existed");
        }
    }
    public static void displayCategory() {
        System.out.println("ID "+" Category"+"\n");
        for (Category category : categories) {
            System.out.println(category.getId() + "   " + category.getName());
        }

    }
    public void  deleteCategory(Scanner scanner) {
        if (categories.size() == 0) {
            System.out.println("category null");
        } else {
            System.out.println("Enter id: ");
            long id = Long.parseLong(scanner.nextLine());

                for (int i = 0; i < categories.size(); i++) {
                if (categories.get(i).getId() == id) {
                    categories.remove(i);
                    ioFile.writeFile(categories, "src/File/category.txt");
                }
            }


            System.out.println("Xoa thanh cong");
        }
        displayCategory();
    }

    private boolean checkName(String name){
        for (int i = 0; i <categories.size() ; i++) {
            if (categories.get(i).getName().equalsIgnoreCase(name)){
                return true;
            }
        }return false;
    }


}
