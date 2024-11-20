package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;
import java.util.List;

public class Program {
    public static void main(String[] args) {
        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("--- Seller - findById(3) ---");
        System.out.println(sellerDao.findById(3));

        System.out.println("--- Seller - findByDepartment(2) ---");
        List<Seller> sellers = sellerDao.findByDepartment(2);
        for (Seller seller : sellers) {
            System.out.println(seller);
        }

        System.out.println("--- Seller - findAll() ---");
        sellers = sellerDao.findAll();
        for (Seller seller : sellers) {
            System.out.println(seller);
        }

    }
}
