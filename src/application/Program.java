package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

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

        System.out.println("--- Seller - insert(Seller) ---");
        Seller sellerToInsert = new Seller(
                null,
                "Mux Mool",
                "muxproductions@gmail.com",
                new Date(),
                3456.52,
                new Department(2, null)
        );
        sellerDao.insert(sellerToInsert);
        System.out.println("Seller inserted with id: " + sellerToInsert.getId());

        System.out.println("--- Seller - update(Seller) ---");
        sellerToInsert.setName("Penelope Cruz");
        sellerToInsert.setEmail("penelope.charming@crazyrace.com");
        sellerDao.update(sellerToInsert);
        System.out.println("Seller updated.");

        System.out.println("--- Seller - delete(id) ---");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an id to delete: ");
        sellerDao.deleteById(scanner.nextInt());
        System.out.println("Seller deleted.");

    }
}
