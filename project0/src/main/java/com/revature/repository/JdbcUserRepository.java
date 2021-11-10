package com.revature.repository;

import com.revature.db.MySQLConnectionFactory;
import com.revature.model.User;
import org.apache.log4j.Logger;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcUserRepository implements UserRepository {

    private static final Logger logger=Logger.getLogger("shoppingCenter");

    @Override
    public void registerCustomerAccount(User user) {

        logger.info("Customer account register processing");
        {
            Connection connection = null;
            try {

                connection = MySQLConnectionFactory.getConnection();
                // step-3 : create JDBC statements with SQL
                String sql = "insert into users (firstName,lastName,email,loginPassword, userType) values (?,?,?,?,?)";

                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getLoginPassword());
                ps.setInt(5,user.getUserType());


                // step-4 : execute JDBC statements & process result
                ps.executeUpdate();
                logger.info("New Customer inserted into database...");


                // step-5 : Handle SQL-exceptions
            } catch (SQLException e) {
                logger.warn(e.getMessage());
            } finally {
                // step-7 : close / release connection
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    @Override
    public void createEmployeeAccount(User user) {

        logger.info("Employee account creating");
        {
            Connection connection = null;
            try {

                connection = MySQLConnectionFactory.getConnection();
                // step-3 : create JDBC statements with SQL
                String sql = "insert into users (firstName,lastName,email,loginPassword, userType) values (?,?,?,?,?)";

                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, user.getFirstName());
                ps.setString(2, user.getLastName());
                ps.setString(3, user.getEmail());
                ps.setString(4, user.getLoginPassword());
                ps.setInt(5,user.getUserType());


                // step-4 : execute JDBC statements & process result
                ps.executeUpdate();
                logger.info("New Employee inserted into database...");


                // step-5 : Handle SQL-exceptions
            } catch (SQLException e) {
                logger.warn(e.getMessage());
            } finally {
                // step-7 : close / release connection
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }


    @Override
    public User findByEmail(String email) {


        {
            User user=null;

            Connection connection = null;
            try {

                connection = MySQLConnectionFactory.getConnection();
                // step-3 : create JDBC statements with SQL
                String sql = "select * from users where email=?";

                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1,email);

                // step-4 : execute JDBC statements & process result
                ResultSet rs = ps.executeQuery();


                while (rs.next()){
                    user =new User();
                    user.setUserId(rs.getInt("id"));
                    user.setFirstName(rs.getString("firstName"));
                    user.setLastName(rs.getString("lastName"));
                    user.setEmail(rs.getString("email"));
                    user.setLoginPassword(rs.getString("loginPassword"));
                    user.setUserType(rs.getInt("userType"));
                }


                // step-5 : Handle SQL-exceptions
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                // step-7 : close / release connection
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }
            return user;
        }

    }

    @Override
    public void removeEmployeeAcc(int userId) {

        logger.info("Employee account removing");
        Connection connection = null;
        try {
            connection=MySQLConnectionFactory.getConnection();

            // step-3 : create JDBC statements with SQL
            String sql = "delete from users where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1,userId);
            //step-4 : execute JDBC statements & process result

            ps.executeUpdate();
            logger.info("Employee account Deleted..");


            // step-5 : Handle SQL-exceptions
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        } finally {
            // step-7 : close / release connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    @Override
    public List<User> employeeAccList() {
        List<User> users = new ArrayList<>();

        Connection connection = null;
        try {

            connection = MySQLConnectionFactory.getConnection();
            // step-3 : create JDBC statements with SQL
            String sql = "select * from users where userType=2";

            PreparedStatement ps = connection.prepareStatement(sql);
            // step-4 : execute JDBC statements & process result
            ResultSet rs = ps.executeQuery();


            while (rs.next()){
                User user =new User();
                user.setUserId(rs.getInt("id"));
                user.setFirstName(rs.getString("firstName"));
                user.setLastName(rs.getString("lastName"));
                user.setEmail(rs.getString("email"));
                user.setLoginPassword(rs.getString("loginPassword"));
                user.setUserType(rs.getInt("userType"));
                users.add(user);
            }


            // step-5 : Handle SQL-exceptions
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // step-7 : close / release connection
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }
        return users;
    }

}

