package com.revature.repository;

import com.revature.db.MySQLConnectionFactory;
import com.revature.model.*;
import jdk.jshell.Snippet;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.ObjDoubleConsumer;

public class JdbcOfferRepository implements OfferRepository{

    private static final Logger logger=Logger.getLogger("shoppingCenter");

    @Override
    public void makeOffer(Offer offer, int userId) {

        logger.info("Offer processing on database...");
        {
            Connection connection = null;
            try {

                connection= MySQLConnectionFactory.getConnection();
                // step-3 : create JDBC statements with SQL
                String sql = "insert into offers (offerPrice,itemId,userId,offerStatus) values (?,?,?,?)";

                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setDouble(1, offer.getOfferPrice());
                ps.setInt(2, offer.getItemId());
                ps.setInt(3, userId);
                ps.setString(4,offer.getOfferStatus().toString());

                // step-4 : execute JDBC statements & process result
                ps.executeUpdate();

                logger.info("Offer processing completed");

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
    public void acceptOffer(int offerId) {

        logger.info("Offer accepting");

        List<Offer> offers =new ArrayList<>();
        List<Payment> payments=new ArrayList<>();

        Connection connection = null;
        Offer offer = new Offer();
        try {
            connection=MySQLConnectionFactory.getConnection();
            // step-3 : create JDBC statements with SQL
            String sql = "select * from offers where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setInt(1,offerId);

            // step-4 : execute JDBC statements & process result
            ResultSet rs = ps.executeQuery();

            // Mapping relational data to objects
            rs.next();
            offer.setItemId(rs.getInt("itemId")) ;
            offer.setUserId(rs.getInt("userId"));
            offer.setOfferPrice(rs.getDouble("offerPrice"));
            offers.add(offer);

            //update offer status
            sql = "update offers set offerStatus=? where id=?";
            ps = connection.prepareStatement(sql);

            ps.setString(1,"ACCEPTED");
            ps.setInt(2,offerId);
            ps.executeUpdate();
            logger.info("updated offer status...");

            sql = "update items set isAvailable=?, userId=? where id=?";
            ps = connection.prepareStatement(sql);

            ps.setBoolean(1,false);
            ps.setInt(2,offer.getUserId());
            ps.setInt(3,offer.getItemId());
            ps.executeUpdate();


            //Reject all others
            sql = "update offers set offerStatus=? where itemId=? and offerStatus='PENDING' ";
            ps = connection.prepareStatement(sql);

            ps.setString(1,"REJECTED");
            ps.setInt(2,offer.getItemId());
            ps.executeUpdate();
            logger.info("rejected other offers...");

            //create payment table

            sql = "insert into payments(offerId,itemId,userId,paymentAmount,paymentStatus) value (?,?,?,?,?)";

            ps = connection.prepareStatement(sql);
            ps.setInt(1, offerId);
            ps.setInt(2,offer.getItemId());
            ps.setInt(3, offer.getUserId());
            ps.setDouble(4, offer.getOfferPrice());
            ps.setString(5,PaymentStatus.WAITING.toString());
            ps.executeUpdate();
            logger.info("Offer accepted...");

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
    public void rejectOffer(int offerId) {

        logger.info("Offer rejecting");

        Connection connection = null;
        try {
            connection=MySQLConnectionFactory.getConnection();

            // step-3 : create JDBC statements with SQL
            String sql = "update offers set offerStatus='REJECTED' where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);

//            ps.setString(1,"REJECTED");
            ps.setInt(1,offerId);
            // step-4 : execute JDBC statements & process result

            ps.executeUpdate();
            logger.info("Offer rejected");

            // Mapping relational data to objects



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
    public List<Offer> findMineOffer(int userId) {
        List<Offer> offers=new ArrayList<>();

        Connection connection = null;
        try {
            connection=MySQLConnectionFactory.getConnection();

            // step-3 : create JDBC statements with SQL
            String sql = "select * from offers where userId=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1,userId);
            // step-4 : execute JDBC statements & process result
            ResultSet rs = ps.executeQuery();

            // Mapping relational data to objects
            while (rs.next()) {
                Offer offer = new Offer();

                offer.setId(rs.getInt("id"));
                offer.setOfferPrice(rs.getDouble("offerPrice"));
                offer.setItemId(rs.getInt("itemId"));
                offer.setUserId(rs.getInt("userId"));
                offer.setOfferStatus(StatusFilter.valueOf(rs.getString("offerStatus")));

                offers.add(offer);
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

        return offers;
    }

    @Override
    public List<Offer> findAllOffer() {
        List<Offer> offers=new ArrayList<>();

        Connection connection = null;
        try {
            connection=MySQLConnectionFactory.getConnection();

            // step-3 : create JDBC statements with SQL
            String sql = "select * from offers";
            PreparedStatement ps = connection.prepareStatement(sql);

            // step-4 : execute JDBC statements & process result
            ResultSet rs = ps.executeQuery();

            // Mapping relational data to objects
            while (rs.next()) {
                Offer offer = new Offer();

                offer.setId(rs.getInt("id"));
                offer.setOfferPrice(rs.getDouble("offerPrice"));
                offer.setItemId(rs.getInt("itemId"));
                offer.setUserId(rs.getInt("userId"));
                offer.setOfferStatus(StatusFilter.valueOf(rs.getString("offerStatus")));


                offers.add(offer);
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

        return offers;
    }



    }

