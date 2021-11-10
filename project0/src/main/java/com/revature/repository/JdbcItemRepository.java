package com.revature.repository;

import com.revature.db.MySQLConnectionFactory;
import com.revature.model.Item;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;



public class JdbcItemRepository implements ItemRepository {

    private static final Logger logger=Logger.getLogger("shoppingCenter");
    /*
        design pattern:

        dao ( data accessing object ) repository
        to do CRUD operations against datasource

        // CRUD -> Create, Read, Update, Delete

        why we need dao/repository pattern ?

        => isolate db operation from regular code
        => can-use db operation on multiple parts of applications

     */

    @Override
    public void addItem(Item item) {

        logger.info("Item adding - "+ "item_id : "+item.getItemId());

        {
            Connection connection = null;
            try {

                connection= MySQLConnectionFactory.getConnection();
                // step-3 : create JDBC statements with SQL
                String sql = "insert into items (itemName,itemPrice,isAvailable) values (?,?,?)";

                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, item.getItemName());
                ps.setDouble(2, item.getItemPrice());
                ps.setBoolean(3, item.isAvailable());


                // step-4 : execute JDBC statements & process result
                ps.executeUpdate();
                logger.info("Item added..");
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
    public void updateItem(int id, double price) {

        logger.info("Item updating - "+"item_id : "+ id);

        {
            Connection connection= null;
            try{
                connection=MySQLConnectionFactory.getConnection();
                String sql="update items set itemPrice=? where id=?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setDouble(1,price);
                ps.setInt(2,id);

                ps.executeUpdate();

            }catch (SQLException e) {
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
    public void updateItem(int id, boolean isAvailable) {

        logger.info("Item updating - "+"item_id : "+ id);

        {
            Connection connection= null;
            try{
                connection=MySQLConnectionFactory.getConnection();
                String sql="update items set isAvailable=? where id=?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setBoolean(1,isAvailable);
                ps.setInt(2,id);

                ps.executeUpdate();

            }catch (SQLException e) {
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
    public List<Item> viewAvailableItems() {

        List<Item> items=new ArrayList<>();

            Connection connection = null;
            try {
                connection=MySQLConnectionFactory.getConnection();

                // step-3 : create JDBC statements with SQL
                String sql = "select * from items where isAvailable=1";
                PreparedStatement ps = connection.prepareStatement(sql);

                // step-4 : execute JDBC statements & process result
                ResultSet rs = ps.executeQuery();

                // Mapping relational data to objects
                while (rs.next()) {
                    Item item = new Item();
                    item.setItemId(rs.getInt("id"));
                    item.setItemName(rs.getString("itemName"));
                    item.setItemPrice(rs.getDouble("itemPrice"));
                    item.setAvailable(rs.getBoolean("isAvailable"));
                    item.setUserId(rs.getInt("userId"));
                    items.add(item);
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

            return items;
        }

    @Override
    public List<Item> findOwnItems(int userId) {
        List<Item> items=new ArrayList<>();

        Connection connection = null;
        try {
            connection=MySQLConnectionFactory.getConnection();

            // step-3 : create JDBC statements with SQL
            String sql = "select * from items where userId=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,userId);
            // step-4 : execute JDBC statements & process result
            ResultSet rs = ps.executeQuery();

            // Mapping relational data to objects
            while (rs.next()) {
                Item item = new Item();
                item.setItemId(rs.getInt("id"));
                item.setItemName(rs.getString("itemName"));
                item.setItemPrice(rs.getDouble("itemPrice"));
                item.setAvailable(rs.getBoolean("isAvailable"));
                item.setUserId(rs.getInt("userId"));
                items.add(item);
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

        return items;
    }

    @Override
    public void removeItem ( int id){

        logger.info("Item removing - "+"item_id : "+ id);
        {
            Connection connection= null;
            try{
                connection=MySQLConnectionFactory.getConnection();
                String sql="delete from items where id=?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1,id);

                ps.executeUpdate();


            }catch (SQLException e) {
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
    public List<Item> viewAllItems() {
        List<Item> items=new ArrayList<>();

        Connection connection = null;
        try {
            connection=MySQLConnectionFactory.getConnection();

            // step-3 : create JDBC statements with SQL
            String sql = "select * from items";
            PreparedStatement ps = connection.prepareStatement(sql);

            // step-4 : execute JDBC statements & process result
            ResultSet rs = ps.executeQuery();

            // Mapping relational data to objects
            while (rs.next()) {
                Item item = new Item();
                item.setItemId(rs.getInt("id"));
                item.setItemName(rs.getString("itemName"));
                item.setItemPrice(rs.getDouble("itemPrice"));
                item.setAvailable(rs.getBoolean("isAvailable"));
                item.setUserId(rs.getInt("userId"));
                items.add(item);
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

        return items;
    }
    }