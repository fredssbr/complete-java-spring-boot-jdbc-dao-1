package model.dao.impl;

import db.DB;
import db.exception.DBException;
import model.dao.DepartmentDao;
import model.entities.Department;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    private Connection connection;

    public DepartmentDaoJDBC(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void insert(Department department) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("insert into department " +
                            "(name) " +
                            " values (?)",
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, department.getName());

            int rowsAffected = statement.executeUpdate();
            if(rowsAffected > 0) {
                resultSet = statement.getGeneratedKeys();
                resultSet.next();
                department.setId(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }

    @Override
    public void update(Department department) {
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("update department set " +
                    "name = ? " +
                    " where id = ?");

            statement.setString(1, department.getName());
            statement.setInt(2, department.getId());

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
            statement = connection.prepareStatement("delete from department where id = ?");

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
    public Department findById(Integer id) {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("select * from department " +
                    "where id  = ?");
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            if(resultSet.next()) {
                return mapResultSetToDepartment(resultSet);
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
    public List<Department> findAll() {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("select * from department " +
                    "order by name");

            resultSet = statement.executeQuery();

            List<Department> departments = new ArrayList<>();
            while(resultSet.next()) {
                departments.add(mapResultSetToDepartment(resultSet));
            }
            return departments;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeResultSet(resultSet);
        }
    }

    private Department mapResultSetToDepartment(ResultSet resultSet) throws SQLException {
        return new Department(resultSet.getInt("id"), resultSet.getString("name"));
    }
}
