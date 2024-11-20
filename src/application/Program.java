package application;

import entities.Department;
import entities.Seller;

import java.util.Date;

public class Program {
    public static void main(String[] args) {
        Department department = new Department(1, "Literature");
        Seller seller = new Seller(12,
                "Martin Fox",
                "martin.fox@mylibrary.com",
                new Date(),
                4985.52,
                department);
        System.out.println(seller);
    }
}
