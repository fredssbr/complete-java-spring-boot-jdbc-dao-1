package model.dao;

import model.dao.impl.DepartmentDaoJDBC;

public class DaoFactory {

    public static SellerDao createSellerDao() {
        return null;
    }

    public static DepartmentDao createDepartmentDao() {
        return new DepartmentDaoJDBC();
    }
}
