package model.dao.impl;

import db.DB;
import db.exception.DBException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SellerDaoJDBC implements SellerDao {

    private Connection connection;

    public SellerDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Seller seller) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("insert into seller " +
                            "(name, email, birthdate, basesalary, departmentid) " +
                            " values (?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, seller.getName());
            statement.setString(2, seller.getEmail());
            statement.setDate(3, new Date(seller.getBirthDate().getTime()));
            statement.setDouble(4, seller.getBaseSalary());
            statement.setInt(5, seller.getDepartment().getId());

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected > 0) {
                resultSet = statement.getGeneratedKeys();
                resultSet.next();
                seller.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public void update(Seller seller) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("update seller set " +
                            "name = ?, email = ?, birthdate = ?, basesalary = ?, departmentid = ? " +
                            " where id = ?");

            statement.setString(1, seller.getName());
            statement.setString(2, seller.getEmail());
            statement.setDate(3, new Date(seller.getBirthDate().getTime()));
            statement.setDouble(4, seller.getBaseSalary());
            statement.setInt(5, seller.getDepartment().getId());
            statement.setInt(6, seller.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("delete from seller where id = ?");

            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public Seller findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("select s.*, d.name \"departmentname\" \n" +
                    "from seller s inner join department d\n" +
                    "on s.departmentid  = d.id\n" +
                    "where s.id  = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return mapResultSetToSeller(resultSet);
            }
            return null;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }


    @Override
    public List<Seller> findByDepartment(Integer departmentId) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("select s.*, d.name \"departmentname\" \n" +
                    "from seller s inner join department d\n" +
                    "on s.departmentid  = d.id\n" +
                    "where s.departmentid  = ?");
            statement.setInt(1, departmentId);
            resultSet = statement.executeQuery();

            List<Seller> sellers = new ArrayList<>();
            while(resultSet.next()) {
                sellers.add(mapResultSetToSeller(resultSet));
            }
            return sellers;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public List<Seller> findAll() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("select s.*, d.name \"departmentname\" \n" +
                    "from seller s inner join department d\n" +
                    "on s.departmentid  = d.id\n" +
                    "order by s.name");

            resultSet = statement.executeQuery();

            List<Seller> sellers = new ArrayList<>();
            while(resultSet.next()) {
                sellers.add(mapResultSetToSeller(resultSet));
            }
            return sellers;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }

    private Seller mapResultSetToSeller(ResultSet resultSet) throws SQLException {

        Department department = new Department(
                resultSet.getInt("departmentid"), resultSet.getString("departmentname")
        );

        return new Seller(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getDate("birthdate"),
                resultSet.getDouble("basesalary"),
                department
        );

    }
}
