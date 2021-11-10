package com.revature.repository;

import com.revature.db.MySQLConnectionFactory;
import com.revature.model.Payment;
import com.revature.model.PaymentStatus;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcPaymentRepository implements PaymentRepository{

    private static final Logger logger=Logger.getLogger("shoppingCenter");

    @Override
    public void payRemainingPayment(int payId) {

        logger.info("Offer payment processing...");

        {
            Connection connection= null;
            try{
                connection=MySQLConnectionFactory.getConnection();
                String sql="update payments set paymentStatus=? where id=?";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1,PaymentStatus.COMPLETED.toString());
                ps.setInt(2,payId);

                ps.executeUpdate();
                logger.info("Offer payment process completed...");

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
    public List<Payment> viewAllRemainingPayment() {
        List<Payment> payments = new ArrayList<>();

        Connection connection = null;
        try {
            connection=MySQLConnectionFactory.getConnection();

            // step-3 : create JDBC statements with SQL
            String sql = "select * from payments where paymentStatus=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,PaymentStatus.WAITING.toString());

            // step-4 : execute JDBC statements & process result
            ResultSet rs = ps.executeQuery();

            // Mapping relational data to objects
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setOfferId(rs.getInt("offerId"));
                payment.setItemId(rs.getInt("itemId"));
                payment.setItemId(rs.getInt("userId"));
                payment.setPaymentAmount(rs.getDouble("paymentAmount"));
                payment.setPaymentStatus(PaymentStatus.valueOf(rs.getString("paymentStatus")));

                payments.add(payment);
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
        if (payments.size() == 0) {
            System.out.println("Don't Have any Remaining Payment.. ");
        }else{
            System.out.println("All Remaining Payment List");
            System.out.println("---------------------------");
        }


        return payments;
    }

    @Override
    public List<Payment> viewMyRemainingPayment(int userId) {
        List<Payment> payments = new ArrayList<>();

        Connection connection = null;
        try {
            connection = MySQLConnectionFactory.getConnection();

            // step-3 : create JDBC statements with SQL
            String sql = "select * from payments where userId=? and paymentStatus='WAITING'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            // step-4 : execute JDBC statements & process result
            ResultSet rs = ps.executeQuery();

            // Mapping relational data to objects
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setOfferId(rs.getInt("offerId"));
                payment.setItemId(rs.getInt("itemId"));
                payment.setItemId(rs.getInt("userId"));
                payment.setPaymentAmount(rs.getDouble("paymentAmount"));
                payment.setPaymentStatus(PaymentStatus.valueOf(rs.getString("paymentStatus")));

                payments.add(payment);
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
        if (payments.size() == 0) {
            System.out.println("You Don't Have any Remaining Payment.. ");
        }else{
            System.out.println("My Remaining Payment List");
            System.out.println("--------------------------");
        }
        return payments;
    }

    @Override
    public List<Payment> viewAllCompletedPayment() {
        List<Payment> payments = new ArrayList<>();

        Connection connection = null;
        try {
            connection=MySQLConnectionFactory.getConnection();

            // step-3 : create JDBC statements with SQL
            String sql = "select * from payments where paymentStatus=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1,PaymentStatus.COMPLETED.toString());

            // step-4 : execute JDBC statements & process result
            ResultSet rs = ps.executeQuery();

            // Mapping relational data to objects
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setOfferId(rs.getInt("offerId"));
                payment.setItemId(rs.getInt("itemId"));
                payment.setItemId(rs.getInt("userId"));
                payment.setPaymentAmount(rs.getDouble("paymentAmount"));
                payment.setPaymentStatus(PaymentStatus.valueOf(rs.getString("paymentStatus")));

                payments.add(payment);
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
        if (payments.size() == 0) {
            System.out.println("Don't have any completed payment.. ");
        }else{
            System.out.println("All Payment List");
            System.out.println("--------------------------");
        }
        return payments;
    }

    @Override
    public List<Payment> viewMyCompletedPayment(int userId) {
        List<Payment> payments = new ArrayList<>();

        Connection connection = null;
        try {
            connection = MySQLConnectionFactory.getConnection();

            // step-3 : create JDBC statements with SQL
            String sql = "select * from payments where userId=? and paymentStatus='COMPLETED'";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, userId);
            // step-4 : execute JDBC statements & process result
            ResultSet rs = ps.executeQuery();

            // Mapping relational data to objects
            while (rs.next()) {
                Payment payment = new Payment();
                payment.setId(rs.getInt("id"));
                payment.setOfferId(rs.getInt("offerId"));
                payment.setItemId(rs.getInt("itemId"));
                payment.setItemId(rs.getInt("userId"));
                payment.setPaymentAmount(rs.getDouble("paymentAmount"));
                payment.setPaymentStatus(PaymentStatus.valueOf(rs.getString("paymentStatus")));

                payments.add(payment);
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
        if (payments.size() == 0) {
            System.out.println("You don't have any completed payment.. ");
        }else{
            System.out.println("My Completed Payment List");
            System.out.println("-------------------------");
        }
        return payments;
    }


    }

