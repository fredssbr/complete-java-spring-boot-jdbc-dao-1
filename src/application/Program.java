package application;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        // testSeller();
        testDepartment();
    }

    private static void testDepartment() {
        DepartmentDao departmentDao = DaoFactory.createDepartmentDao();

        System.out.println("--- Department - findById(3) ---");
        System.out.println(departmentDao.findById(3));

        System.out.println("--- Department - findAll() ---");
        List<Department> departments = departmentDao.findAll();
        for (Department department : departments) {
            System.out.println(department);
        }

        System.out.println("--- Department - insert(Department) ---");
        Department departmentToInsert = new Department(
                null,
                "Music"
        );
        departmentDao.insert(departmentToInsert);
        System.out.println("Department inserted with id: " + departmentToInsert.getId());

        System.out.println("--- Department - update(Department) ---");
        departmentToInsert.setName("Music and orchestra");
        departmentDao.update(departmentToInsert);
        System.out.println("Department updated.");

        System.out.println("--- Department - delete(id) ---");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter an id to delete: ");
        departmentDao.deleteById(scanner.nextInt());
        System.out.println("Department deleted.");
    }

    private static void testSeller() {
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
