package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

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

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println(seller);
    }
}
