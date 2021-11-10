package com.revature;

import com.revature.model.User;
import org.apache.log4j.Logger;


import java.util.Scanner;

public class App {

    private static final Logger logger=Logger.getLogger("shoppingCenter");

    static Scanner scanner = new Scanner(System.in);

    static User currentUser=null;


    public static void main(String[] args) {

        logger.info("application - started");

        loginPage();

    }

            /* -----------------------------------------------
                        Decide user type

                        id=1 => Customer
                        id=2 => Employee
                        id=3 => Manager
         ----------------------------------------------- */

    public static void loginPage() {

        System.out.println("\n");
        System.out.println("   "+"*****************************************************************************");
        System.out.println("   "+"*                                                                           *");
        System.out.println("   "+"*-------------------       Welcome Shopping Center       -------------------*");
        System.out.println("   "+"*                                                                           *");
        System.out.println("   "+"*------------                  press -1- Login                  ------------*");
        System.out.println("   "+"*                                                                           *");
        System.out.println("   "+"*-----                        press -2- Register                       -----*");
        System.out.println("   "+"*                                                                           *");
        System.out.println("   "+"*****************************************************************************");
        System.out.print("Please Select Your Choice =>> ");
        int select = scanner.nextInt();

        if (select == 1) {
            currentUser = ShopApplication.login();

            if (currentUser.getUserType() == 1) { //  id=1 => Customer

                logger.info("Customer Login Successful id: "+currentUser.getUserId());

                while (true) {

                    System.out.println("\n");
                    System.out.println("   "+"|***            CUSTOMER MENU           ***|");
                    System.out.println("   "+"____________________________________________");
                    System.out.println("   "+"|                                          |");
                    System.out.println("   "+"|       "+"1- View Available Items"+"            |");
                    System.out.println("   "+"|       "+"2- Make an Offer"+"                   |");
                    System.out.println("   "+"|       "+"3- View My Offers"+"                  |");
                    System.out.println("   "+"|       "+"4- View My Remaining Payments"+"      |");
                    System.out.println("   "+"|       "+"5- Pay My Remaining Payment"+"        |");
                    System.out.println("   "+"|       "+"6- View My Completed Payments"+"      |");
                    System.out.println("   "+"|       "+"7- View Own Item"+"                   |");
                    System.out.println("   "+"|       "+"8- Log Out"+"                         |");
                    System.out.println("   "+"|__________________________________________|");
                    System.out.println("\n");
                    System.out.print("Hi " + ShopApplication.getCurrentUserName() + " Welcome to The Shopping Center. How can I help You" + "\nPlease Select Your Choice =>> ");

                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1: {
                            ShopApplication.viewAvailableItems();
                            break;
                        }
                        case 2: {
                            ShopApplication.makeOffer();
                            break;
                        }
                        case 3: {
                            ShopApplication.viewMyOffer();
                            break;
                        }
                        case 4: {
                            ShopApplication.viewMyRemainingPayment();
                            break;
                        }
                        case 5: {
                            ShopApplication.payRemainingPayment();
                            break;
                        }
                        case 6: {
                            ShopApplication.viewMyCompletedPayment();
                            break;
                        }
                        case 7: {
                            ShopApplication.viewOwnItems();
                            break;
                        }
                        case 8: {
                            ShopApplication.logOut();
                            logger.info("Customer Logout Successful id: "+currentUser.getUserId());
                            loginPage();
                            break;
                        }
                    }
                }

            } else if (currentUser.getUserType() == 2) { //  id=2 => Employee

                logger.info("Employee Login Successful id: "+currentUser.getUserId());

                while (true) {

                    System.out.println("\n");
                    System.out.println("   "+"|***            EMPLOYEE MENU           ***|");
                    System.out.println("   "+"____________________________________________");
                    System.out.println("   "+"|                                          |");
                    System.out.println("   "+"|       "+"1- Add an Item"+"                     |");
                    System.out.println("   "+"|       "+"2- View All Items"+"                  |");
                    System.out.println("   "+"|       "+"3- Update Item Price"+"               |");
                    System.out.println("   "+"|       "+"4- Update Item Status"+"              |");
                    System.out.println("   "+"|       "+"5- View All Offers"+"                 |");
                    System.out.println("   "+"|       "+"6- Accept an Offer"+"                 |");
                    System.out.println("   "+"|       "+"7- Reject an Offer"+"                 |");
                    System.out.println("   "+"|       "+"8- Remove Item"+"                     |");
                    System.out.println("   "+"|       "+"9- View All Remaining Payments"+"     |");
                    System.out.println("   "+"|      "+"10- View All Payments"+"               |");
                    System.out.println("   "+"|      "+"11- Log Out"+"                         |");
                    System.out.println("   "+"|__________________________________________|");
                    System.out.println("\n");
                    System.out.print("Hi " + ShopApplication.getCurrentUserName() + " Work Work Work. " + "\nPlease Select Your Choice =>> ");

                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1: {
                            ShopApplication.addItem();
                            break;
                        }
                        case 2: {
                            ShopApplication.viewAllItems();
                            break;
                        }
                        case 3: {
                            ShopApplication.updateItemPrice();
                            break;
                        }
                        case 4: {
                            ShopApplication.updateItemStatus();
                            break;
                        }
                        case 5: {
                            ShopApplication.viewALlOffer();
                            break;
                        }
                        case 6: {
                            ShopApplication.acceptOffer();
                            break;
                        }
                        case 7: {
                            ShopApplication.rejectOffer();
                            break;
                        }
                        case 8: {
                            ShopApplication.removeItem();
                            break;
                        }
                        case 9: {
                            ShopApplication.viewAllRemainingPayment();
                            break;
                        }
                        case 10: {
                            ShopApplication.viewAllPayment();
                            break;
                        }
                        case 11: {
                            ShopApplication.logOut();
                            logger.info("Employee Logout Successful id: "+currentUser.getUserId());
                            loginPage();
                            break;
                        }
                    }
                }

            } else if (currentUser.getUserType() == 3) { //  id=3 => Manager

                logger.info("Manager Login Successful id: "+currentUser.getUserId());

                while (true) {

                    System.out.println("\n");
                    System.out.println("   "+"|***         MANAGER MENU         ***|");
                    System.out.println("   "+"______________________________________");
                    System.out.println("   "+"|                                    |");
                    System.out.println("   "+"|     "+"1- View Employee List"+"          |");
                    System.out.println("   "+"|     "+"2- Create Employee Account"+"     |");
                    System.out.println("   "+"|     "+"3- Remove Employee Account"+"     |");
                    System.out.println("   "+"|     "+"4- Log Out"+"                     |");
                    System.out.println("   "+"|____________________________________|");
                    System.out.println("\n");
                    System.out.print("Hi " + ShopApplication.getCurrentUserName() + " Your are a good Manager!. How can I help You today?" + "\nPlease Select Your Choice =>> ");

                    int choice = scanner.nextInt();

                    switch (choice) {
                        case 1: {
                            ShopApplication.employeeAccList();
                            break;
                        }
                        case 2: {
                            ShopApplication.createEmployeeAccount();
                            break;
                        }
                        case 3: {
                            ShopApplication.removeEmployeeAcc();
                            break;
                        }
                        case 4: {
                            ShopApplication.logOut();
                            logger.info("Manager Logout Successful id: "+currentUser.getUserId());
                            loginPage();
                            break;
                        }
                    }
                }

            }
        }
        else if (select == 2) {
            ShopApplication.registerCustomerAccount();
            loginPage();

        } else {
            System.out.println("-----------------------Wrong chose! can you Try Again-----------------------");
        }



    }


}


