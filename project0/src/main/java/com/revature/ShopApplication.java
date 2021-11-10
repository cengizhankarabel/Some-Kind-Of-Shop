package com.revature;


import com.revature.exceptions.InvalidCredentialException;
import com.revature.exceptions.UserNotFoundException;
import com.revature.model.Offer;
import com.revature.model.StatusFilter;
import com.revature.model.User;
import com.revature.repository.*;
import com.revature.service.*;
import org.apache.log4j.Logger;

import java.util.Scanner;

public class ShopApplication {

    private static final Logger logger=Logger.getLogger("shoppingCenter");

    static Scanner scanner = new Scanner(System.in);

    //------------------------------------------------------------------------------
    // Init
    //------------------------------------------------------------------------------

    static User currentUser=null;

    static ItemRepository itemRepository = new JdbcItemRepository();  // Dependency
    static ItemService itemService = new ItemServiceImpl(itemRepository); // Dependent

    static UserRepository userRepository = new JdbcUserRepository(); // Dependency
    static UserService userService = new UserServiceImpl(userRepository); // Dependent

    static OfferRepository offerRepository = new JdbcOfferRepository(); // Dependency
    static OfferService offerService =new OfferServiceImpl(offerRepository); // Dependent

    static PaymentRepository paymentRepository=new JdbcPaymentRepository(); // Dependency
    static PaymentService paymentService=new PaymentServiceImpl(paymentRepository); // Dependent
    //------------------------------------------------------------------------------

    public static void main(String[] args) {

        logger.info("application - started");
        logger.info("application - ended");

        //------------------------------------------------------------------------------
        // Use
        //------------------------------------------------------------------------------


/*

//        while (true){
//
//            System.out.println("\n\nselect choice !\n");
//            System.out.println(""+
//                    "1- Add Item \n"+
//                    "2- View Item\n"+
//                    "3- Update Item Price\n"+
//                    "4- Update Item Available\n"+
//                    "5- Delete Item\n"+
//                    "6- Add User(Register)\n"+
//                    "7- Login\n"+
//                    "8- Find User\n"+
//                    "9- View Own Item\n"+
//                    "10-Make Offer\n"+
//                    "11-LogOut\n"+
//                    "12-List Offer List\n"+
//                    "13-Find My Offer\n"+
//                    "14-Accept Offer\n"+
//                    "15-Reject Offer\n"+
//                    "16-View My Remaining Payment\n"+
//                    "17-View All Remaining Payment\n"+
//                    "18-Pay Remaining Payment\n"+
//                    "19-View My Payment\n"+
//                    "20-View All payment\n"+
//                    "21-Remove Employee Account\n"+
//                    "22-Employee Account List\n"+
//                    "----------------------------\n"+
//                    "=> ");
//            int choice=scanner.nextInt();
//
//            switch (choice){
//                case 1:{
//                    addItem();
//                    break;
//                }
//                case 2:{
//                    viewAvailableItems();
//                    break;
//                }
//                case 3:{
//                    updateItemPrice();
//                    break;
//                }
//                case 4:{
//                    updateItemStatus();
//                    break;
//                }
//                case 5:{
//                    removeItem();
//                    break;
//                }
//                case 6:{
//                    createEmployeeAccount();
//                    break;
//                }
//                case 7:{
//                    login();
//                    break;
//                }
//                case 8:{
//                    findUser();
//                    break;
//                }
//                case 9:{
//                    viewOwnItems();
//                    break;
//                }
//                case 10:{
//                    makeOffer();
//                    break;
//                }
//                case 11:{
//                    logOut();
//                    break;
//                }
//                case 12:{
//                    viewALlOffer();
//                    break;
//                }
//                case 13:{
//                    viewMyOffer();
//                    break;
//                }
//                case 14:{
//                    acceptOffer();
//                    break;
//                }
//                case 15:{
//                    rejectOffer();
//                    break;
//                }
//                case 16:{
//                    viewMyRemainingPayment();
//                    break;
//                }
//                case 17:{
//                    viewAllRemainingPayment();
//                    break;
//                }
//                case 18:{
//                    payRemainingPayment();
//                    break;
//                }
//                case 19:{
//                    viewMyCompletedPayment();
//                    break;
//                }
//                case 20:{
//                    viewAllPayment();
//                    break;
//                }
//                case 21:{
//                    removeEmployeeAcc();
//                    break;
//                }
//                case 22:{
//                    employeeAccList();
//                    break;
//                }
//            }
//        }
*/

    }

    // User Issue-------------------------------------------------------
    public static User login() {
        //scanner.nextLine();
        System.out.println();
        System.out.print("Enter Email Address: ==> ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ==> ");
        String password = scanner.nextLine();
        try{
            currentUser = userService.loginUser(email,password);

            itemService.setUser(currentUser);
            offerService.setUser(currentUser);
            paymentService.setUser(currentUser);

        }catch (UserNotFoundException | InvalidCredentialException e){
            System.out.println("exception: "+ e.getMessage());
        }

        return currentUser;

    } // +

    public static void registerCustomerAccount() {
        scanner.nextLine();
        System.out.print("Enter Email Address: ==> ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ==> ");
        String password = scanner.nextLine();

        System.out.print("Enter First Name: ==> ");
        String firstName = scanner.nextLine();

        System.out.print("Enter Last Name: ==> ");
        String lastName = scanner.nextLine();

        User user = new User();

        user.setEmail(email);
        user.setLoginPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserType(1);

        userService.registerCustomerAccount(user);
        scanner.nextLine();


    } // +

    public static void logOut() {
        scanner.nextLine();
        currentUser=null;
        itemService.setUser(null);
    } // +
    //-------------------------------------------------------

    // Customer Issues//-------------------------------------------------------
    public static void viewAvailableItems() {
        System.out.println("------------------------------Available Items List------------------------------");
        itemService.viewAvailableItems()
                .forEach(System.out::println);
    } // +

    public static void makeOffer() {


        System.out.print("Enter Item Id: ==> ");
        int itemId = scanner.nextInt();

        System.out.print("Enter Offer Price: ==> ");
        double offerPrice= scanner.nextDouble();

        Offer offer=new Offer();

        offer.setOfferPrice(offerPrice);
        offer.setItemId(itemId);
        offer.setUserId(currentUser.getUserId());
        offer.setOfferStatus(StatusFilter.PENDING);

        offerService.makeOffer(offer);

    } // +

    public static void viewMyOffer() {
        System.out.println("------------------------------My Offer List------------------------------");
        offerService.findMyOffer()
                .forEach(System.out::println);
    }

    public static void viewMyRemainingPayment() {
        System.out.println("------------------------------My Remaining Payment List------------------------------");
        paymentService.viewMyRemainingPayment()
                .forEach(System.out::println);
    } // +

    public static void payRemainingPayment(){
        System.out.print("Enter Payment Id: ==> ");
        int payId = scanner.nextInt();
        paymentService.payRemainingPayment(payId);
    } // +

    public static void viewMyCompletedPayment() {
        System.out.println("------------------------------My Completed Payment List------------------------------");
        paymentService.viewMyCompletedPayment()
                .forEach(System.out::println);
    }

    public static void viewOwnItems() {
        System.out.println("------------------------------My Items List------------------------------");
        itemService.getOwnItems()
                .forEach(System.out::println);
    } // +
    //-------------------------------------------------------


    // Employee Issues//-------------------------------------------------------
    public static void addItem() {

        System.out.print("Enter Item name: ==> ");
        String itemName = scanner.nextLine();

        System.out.print("Enter Item Price: ==> ");
        double itemPrice = scanner.nextDouble();

        itemService.addItem(itemName,itemPrice,true);
    } // +

    public static void viewAllItems() {
        System.out.println("-----------------------------------All Items-----------------------------------");
        itemService.viewAllItems()
                .forEach(System.out::println);
    } // +

    public static void updateItemPrice() {

        System.out.print("Enter item id: ==> ");
        int itemId= scanner.nextInt();

        System.out.print("Enter item new-price: ==> ");
        double newPrice=scanner.nextDouble();

        itemService.editItem(itemId,newPrice);

    }
    
    public static void updateItemStatus() {
        System.out.print("Enter item id: ==> ");
        int itemId= scanner.nextInt();

        System.out.println("if Item available: ==> enter 'true' ");
        System.out.print("if Item not available: ==> enter 'false' ");
        boolean isAvailable=scanner.nextBoolean();

        itemService.editItem(itemId,isAvailable);
    }
    
    public static void removeItem() {
        System.out.print("Enter item id: ==> ");
        int itemId= scanner.nextInt();
        itemService.deleteItem(itemId);
    } // +

    public static void viewALlOffer() {
        System.out.println("------------------------------Offer List------------------------------");
        offerService.findAllOffer()
                .forEach(System.out::println);
    }

    public static void acceptOffer(){

        System.out.print("Enter Offer Id: ==> ");
        int offerId = scanner.nextInt();
        offerService.acceptOffer(offerId);
        System.out.println("Offer ACCEPTED");
    } // +

    public static void rejectOffer() {

        System.out.print("Enter Offer Id: ==> ");
        int offerId=scanner.nextInt();
        offerService.rejectOffer(offerId);
        System.out.println("Offer REJECTED");
    } // +

    public static void viewAllRemainingPayment() {
        paymentService.viewAllRemainingPayment()
                .forEach(System.out::println);
    }

    public static void viewAllPayment() {
        System.out.println("------------------------------Payment List------------------------------");

        paymentService.viewAllCompletedPayment()
                .forEach(System.out::println);
    }
    //-------------------------------------------------------


    // Manager Issues//-------------------------------------------------------
    public static void employeeAccList() {

        System.out.println("-------------------------------------Employee Account List-------------------------------------");
        System.out.println();
        userService.employeeAccList()
                .forEach(System.out::println);

    } // +

    public static void createEmployeeAccount() {

        System.out.print("Enter Email Address: ==> ");
        String email = scanner.nextLine();

        System.out.print("Enter Password: ==> ");
        String password = scanner.nextLine();

        System.out.print("First Name: ==> ");
        String firstName = scanner.nextLine();

        System.out.print("Last Name: ==> ");
        String lastName = scanner.nextLine();
        System.out.println("\n");

        User user = new User();

        user.setEmail(email);
        user.setLoginPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUserType(2);

        userService.createEmployeeAccount(user);


    } // +

    public static void removeEmployeeAcc() {

        System.out.print("Enter User Id: ==> ");
        int userId=scanner.nextInt();
        userService.removeEmployeeAcc(userId);
    } // +



    //Extras-------------------------------------------------------
    public static void findUser() {
        scanner.nextLine();
        System.out.println("Enter Email Address");
        String email=scanner.nextLine();
        System.out.println("Enter Password");
        String password=scanner.nextLine();

        userService.loginUser(email,password);
    }


    public static String getCurrentUserName(){
        return currentUser.getFirstName();
    }



}



