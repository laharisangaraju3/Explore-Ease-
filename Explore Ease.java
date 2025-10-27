import java.util.*;
class Colors {
    static final String reset = "\u001B[0m";
    static final String red = "\u001B[31m";
    static final String green = "\u001B[32m";
    static final String yellow = "\u001B[33m";
    static final String blue = "\u001B[34m";
    static final String pink = "\u001B[95m";
    static final String cyan = "\u001B[36m";
    static final String bold = "\u001B[1m";
   static final String magenta = "\u001B[35m";
}

//======================================================================================================= PAYMENT SECTION =================================================================================================================================

interface P {
    boolean paytm();
    boolean phonePay();
    boolean googlePay();
    boolean creditCard();
    boolean accountNumber();
}


class Payments implements P {
    static Scanner sc = new Scanner(System.in);
    static double amountToPay;

    // Simulate payment processing delay
    public static void processPayment() throws InterruptedException {
        System.out.println(Colors.yellow + "\n\t\t\t\t\t\t\tPayment is processing..." + Colors.reset);
        Thread.sleep(2000); 
        System.out.println(Colors.green + "\t\t\t\t\t\t\tPayment completed successfully!" + Colors.reset);
    }


    static int otpGenerator() {
        return 1000 + (int) (Math.random() * 8999);
    }

    @Override
    public boolean paytm() {
        int attempts = 3;
        while (attempts > 0) {

            System.out.println(Colors.magenta + "\t\t\t\t\t\t\tTotal amount: Rs " + amountToPay + Colors.reset);
            int generatedPin = otpGenerator();
            System.out.println(Colors.pink + "\t\t\t\t\t\t\tYour OTP is: " + generatedPin + Colors.reset);
            System.out.print(Colors.blue + "\t\t\t\t\t\t\tEnter OTP: " + Colors.reset);
            int enteredPin = sc.nextInt();

            if (generatedPin == enteredPin) {
                try {
                    processPayment();
                } catch (InterruptedException e) {
                    System.out.println(Colors.red + "\t\t\t\t\t\t\tPayment failed." + Colors.reset);
                }
                return true;
            } else {
                attempts--;
                System.out.println(Colors.red + "\t\t\t\t\t\t\tIncorrect OTP. " + attempts + " attempts left." + Colors.reset);
            }
        }
        System.out.println(Colors.red + "\t\t\t\t\t\t\tPayment failed after 3 attempts." + Colors.reset);
        return false;
    }

    @Override
    public boolean googlePay() {
           System.out.println(Colors.pink+"\t\t\t\t\t\t\t===========Google Pay===========");
        return paytm();
    }

    @Override
    public boolean phonePay() {
        System.out.println(Colors.pink+"\t\t\t\t\t\t\t===========Phone pay===========");
        return paytm();
    }

    @Override
    public boolean creditCard() {
        int attempts = 3;
        while (attempts > 0) {
           System.out.println(Colors.pink+"\t\t\t\t\t\t\t===========credit card===========");
            System.out.println(Colors.magenta + "\t\t\t\t\t\t\tTotal Amount: Rs " + amountToPay + Colors.reset);

            System.out.print(Colors.blue + "\t\t\t\t\t\t\tEnter the card holder name: " + Colors.reset);
            String name = sc.next();

        
            String cardNumber;
            while (true) {
                System.out.print(Colors.blue + "\t\t\t\t\t\t\tEnter the 16-digit card number: " + Colors.reset);
                cardNumber = sc.next();

                if (cardNumber.matches("\\d{16}")) {
                    break;
                } else {
                    System.out.println(Colors.red + "\t\t\t\t\t\t\tInvalid card number! Must contain exactly 16 digits." + Colors.reset);
                }
            }

            int generatedOtp = otpGenerator();
            System.out.println(Colors.pink + "\t\t\t\t\t\t\tYour OTP is: " + generatedOtp + Colors.reset);
            System.out.print(Colors.blue + "\t\t\t\t\t\t\tEnter OTP: " + Colors.reset);
            int enteredOtp = sc.nextInt();

            if (generatedOtp == enteredOtp) {
                try {
                    processPayment();
                } catch (InterruptedException e) {
                    System.out.println(Colors.red + "\t\t\t\t\t\t\tPayment process was interrupted." + Colors.reset);
                }
                return true;
            } else {
                attempts--;
                System.out.println(Colors.red + "\t\t\t\t\t\t\tWrong OTP. " + attempts + " attempts left." + Colors.reset);
            }
        }

        System.out.println(Colors.red + "\t\t\t\t\t\t\tPayment failed after 3 attempts." + Colors.reset);
        return false;
    }

    @Override
    public boolean accountNumber() {
        int attempts = 3;
        while (attempts > 0) {
           System.out.println(Colors.pink+"\t\t\t\t\t\t\t===========Account Number===========");
            System.out.println(Colors.magenta + "\t\t\t\t\t\t\tTotal Amount: Rs " + amountToPay + Colors.reset);

            // Validate account number (12 to 16 digits)
            String accNum;
            while (true) {
                System.out.print(Colors.blue + "\t\t\t\t\t\t\tEnter the account number: " + Colors.reset);
                accNum = sc.next();
                if (accNum.matches("\\d{12,16}")) {
                    break;
                } else {
                    System.out.println(Colors.red + "\t\t\t\t\t\t\tInvalid account number! Must contain 12 to 16 digits." + Colors.reset);
                }
            }

            // Validate IFSC Code (4 letters + 7 digits)
            String ifsc;
            while (true) {
                System.out.print(Colors.blue + "\t\t\t\t\t\t\tEnter the IFSC code: " + Colors.reset);
                ifsc = sc.next().toUpperCase();
                if (ifsc.matches("^[A-Z]{4}\\d{7}$")) {
                    break;
                } else {
                    System.out.println(Colors.red + "\t\t\t\t\t\t\tInvalid IFSC! Format should be like SBIN0001234." + Colors.reset);
                }
            }

       
            int generatedOtp = otpGenerator();
            System.out.println(Colors.pink + "\t\t\t\t\t\t\tYour OTP is: " + generatedOtp + Colors.reset);
            System.out.print(Colors.blue + "\t\t\t\t\t\t\tEnter OTP: " + Colors.reset);
            int enteredOtp = sc.nextInt();

            if (generatedOtp == enteredOtp) {
                try {
                    processPayment();
                } catch (InterruptedException e) {
                    System.out.println(Colors.red + "\t\t\t\t\t\t\tPayment process was failed." + Colors.reset);
                }
                return true;
            } else {
                attempts--;
                System.out.println(Colors.red + "\t\t\t\t\t\t\tWrong OTP. " + attempts + " attempts left." + Colors.reset);
            }
        }

        System.out.println(Colors.red + "\t\t\t\t\t\t\tPayment failed after 3 attempts." + Colors.reset);
        return false;
    }
}

//============================================================================================================ TICKETS BOOKING =====================================================================================================================================

class User {
    static final String bold = "\u001B[1m";
static Scanner sc = new Scanner(System.in);

public static void startTravelBooking() {
        while (true) {
            System.out.println(Colors.bold + Colors.cyan + "\n\t\t\t\t\t\t\t--- WELCOME TO TRAVEL EASY ---" + Colors.reset);
            System.out.println(Colors.yellow +"\t\t\t\t\t\t"+"1. Book a Ticket");
            System.out.println("\t\t\t\t\t\t"+"2. Instant Ride");
            System.out.println("\t\t\t\t\t\t3.back to main menu"+ Colors.reset);
            System.out.println(Colors.red +"\t\t\t\t\t\t"+"4. Exit"+ Colors.reset);
            System.out.print(Colors.blue +"\t\t\t\t\t\t"+"Enter your choice: "+ Colors.reset);
            int choice = sc.nextInt();

            switch (choice) {
                case 1: bookTicket(); break;
                case 2: instantRide(); break;
                case 3:
                    System.out.println(Colors.green +"\t\t\t\t\t\t"+ "Thank you for using Travel Easy!" + Colors.reset);
                    return;
               case 4:
                     System.out.println(Colors.green + "\n\t\t\t\tThank you for using our Explore Ease Application!");
                       System.out.println("\t\t\t\tWe hope to serve you again soon!" + Colors.reset);
                      System.exit(0);
                default:
                    System.out.println(Colors.red +"\t\t\t\t\t\t"+ "Invalid Choice!" + Colors.reset);
            }
        }
    }


    static void bookTicket() {
        System.out.println(Colors.bold + Colors.cyan + "\t\t\t\t\t\t\t"+"--- BOOK A TICKET ---" + Colors.reset);
        System.out.println( Colors.yellow + "\t\t\t\t\t\t"+"1. Train");
        System.out.println("\t\t\t\t\t\t"+"2. Bus");
        System.out.println("\t\t\t\t\t\t"+"3. Flight");
        System.out.println("\t\t\t\t\t\t4.back to main menu"+ Colors.reset);
        System.out.println( Colors.red + "\t\t\t\t\t\t5.exit"+ Colors.reset);
        System.out.print( Colors.blue + "\t\t\t\t\t\t"+"Enter your choice: "+ Colors.reset);
        int ch = sc.nextInt();

        System.out.print(Colors.pink +"\t\t\t\t\t\t"+"Enter Starting Point: "+ Colors.reset);
        sc.nextLine();
        String start = sc.nextLine();
        System.out.print(Colors.blue +"\t\t\t\t\t\t"+"Enter Destination Point: "+ Colors.reset);
        String end = sc.nextLine();

        switch (ch) {
            case 1: trainDetails(start, end); break;
            case 2: busDetails(start, end); break;
            case 3: flightDetails(start, end); break;
             case 4: return;
             case 5:
                        System.out.println(Colors.green + "\n\t\t\t\tThank you for using our Explore Ease Application!");
                       System.out.println("\t\t\t\tWe hope to serve you again soon!" + Colors.reset);
                       System.exit(0);
            default: System.out.println(Colors.red + "\t\t\t\t\t\t"+"Invalid Option!" + Colors.reset);
        }
    }
    static void trainDetails(String start, String end) {
    System.out.println(Colors.bold + Colors.pink + "\t\t\t\t\t\t"+"--- Available Trains ---" + Colors.reset);

    System.out.println(Colors.yellow + "\t\t\t\t\t\t"+"1. 12045 - Intercity Express");
    System.out.println("\t\t\t\t\t\t"+"   Time: 06:00 - 10:00 | cost: Rs 400");

    System.out.println("\t\t\t\t\t\t"+"2. 12289 - Superfast Express");
    System.out.println("\t\t\t\t\t\t"+"   Time: 07:30 - 11:00 | cost: Rs 450");

    System.out.println("\t\t\t\t\t\t"+"3. 12951 - Rajdhani Express");
    System.out.println("\t\t\t\t\t\t"+"   Time: 09:00 - 12:30 | cost: Rs 600");

    System.out.println("\t\t\t\t\t\t"+"4. 12077 - Jan Shatabdi Express");
    System.out.println("\t\t\t\t\t\t"+"   Time: 12:00 - 16:00 | cost: Rs 350");

    System.out.println("\t\t\t\t\t\t"+"5. 12809 - Garib Rath Express");
    System.out.println("\t\t\t\t\t\t"+"   Time: 18:00 - 22:00 | cost: Rs 500"+ Colors.reset);

    System.out.print(Colors.blue+"\t\t\t\t\t\t"+"Select Train (1-5): "+ Colors.reset);
    int c = sc.nextInt();

    String trainName = "";
    int cost = 0;
    String time = "";

    switch (c) {
        case 1:
            trainName = "12045 - Intercity Express";
            time = "06:00 - 10:00";
            cost = 400;
            break;
        case 2:
            trainName = "12289 - Superfast Express";
            time = "07:30 - 11:00";
            cost= 450;
            break;
        case 3:
            trainName = "12951 - Rajdhani Express";
            time = "09:00 - 12:30";
            cost = 600;
            break;
        case 4:
            trainName = "12077 - Jan Shatabdi Express";
            time = "12:00 - 16:00";
            cost = 350;
            break;
        case 5:
            trainName = "12809 - Garib Rath Express";
            time = "18:00 - 22:00";
            cost = 500;
            break;
        default:
            System.out.println(Colors.red +"\t\t\t\t\t\t"+ "Invalid Train Option!" + Colors.reset);
            return;
    }


    System.out.println(Colors.cyan + "\t\t\t\t\t\t"+"--- Train Details ---" + Colors.reset);
    System.out.println(Colors.green + "\t\t\t\t\t\t"+"Train: " + trainName + Colors.reset);
    System.out.println("\t\t\t\t\t\t"+"From: " + start + "  ->  To: " + end);
    System.out.println("\t\t\t\t\t\t"+"Time: " + time);
    System.out.println("\t\t\t\t\t\t"+"cost: Rs " + cost);


    makePayment(cost);

    System.out.println(Colors.green + "\t\t\t\t\t\t\t"+"Train booked successfully from " + start + " to " + end + "!" + Colors.reset);
}

static void busDetails(String start, String end) {
    System.out.println(Colors.bold + Colors.cyan + "\t\t\t\t\t\t"+"--- Available Buses ---" + Colors.reset);

    System.out.println(Colors.yellow +"\t\t\t\t\t\t"+"1. KA01 AB1234 - Volvo AC");
    System.out.println("\t\t\t\t\t\t"+"   Time: 07:00 - 11:00 | cost: Rs 300");

    System.out.println("\t\t\t\t\t\t"+"2. AP09 CD5678 - Orange Travels");
    System.out.println("\t\t\t\t\t\t"+"   Time: 08:30 - 12:00 | cost: Rs 350");

    System.out.println("\t\t\t\t\t\t"+"3. TS07 EF9012 - SRS Non-AC");
    System.out.println("\t\t\t\t\t\t"+"   Time: 09:00 - 13:00 | cost: Rs 280");

    System.out.println("\t\t\t\t\t\t"+"4. KA05 GH3456 - Kaveri Travels");
    System.out.println("\t\t\t\t\t\t"+"   Time: 14:00 - 18:00 | cost: Rs 320");

    System.out.println("\t\t\t\t\t\t"+"5. MH11 IJ7890 - VRL Sleeper");
    System.out.println("\t\t\t\t\t\t"+"   Time: 19:00 - 23:00 | cost: Rs 400"+ Colors.reset);

    System.out.print(Colors.blue +"\t\t\t\t\t\t"+"Select Bus : "+ Colors.reset);
    int c = sc.nextInt();

    String busName = "";
    int cost = 0;
    String time = "";

    switch (c) {
        case 1:
            busName = "KA01 AB1234 - Volvo AC";
            time = "07:00 - 11:00";
            cost = 300;
            break;
        case 2:
            busName = "AP09 CD5678 - Orange Travels";
            time = "08:30 - 12:00";
            cost = 350;
            break;
        case 3:
            busName = "TS07 EF9012 - SRS Non-AC";
            time = "09:00 - 13:00";
            cost = 280;
            break;
        case 4:
            busName = "KA05 GH3456 - Kaveri Travels";
            time = "14:00 - 18:00";
            cost = 320;
            break;
        case 5:
            busName = "MH11 IJ7890 - VRL Sleeper";
            time = "19:00 - 23:00";
            cost = 400;
            break;
        default:
            System.out.println(Colors.red +"\t\t\t\t\t\t"+ "Invalid Bus Option!" + Colors.reset);
            return;
    }
    System.out.println(Colors.cyan + "\t\t\t\t\t\t"+"--- Bus Details ---" + Colors.reset);
    System.out.println(Colors.green + "\t\t\t\t\t\t"+"Bus: " + busName + Colors.reset);
    System.out.println("\t\t\t\t\t\t"+"From: " + start + "  ->  To: " + end);
    System.out.println("\t\t\t\t\t\t"+"Time: " + time);
    System.out.println("\t\t\t\t\t\t"+"cost: Rs " + cost);

    
    makePayment(cost);

    System.out.println(Colors.green + "\t\t\t\t\t\t\t"+"Bus booked successfully from " + start + " to " + end + "!" + Colors.reset);
}



static void flightDetails(String start, String end) {
    System.out.println(Colors.bold + Colors.cyan + "\t\t\t\t\t\t"+"--- Available Flights ---" + Colors.reset);

    System.out.println(Colors.yellow +"\t\t\t\t\t\t"+"1. 6E-2456 - IndiGo Airlines");
    System.out.println("\t\t\t\t\t\t"+"   Time: 06:00 - 08:00 | cost: Rs 2500");

    System.out.println("\t\t\t\t\t\t"+"2. AI-308 - Air India");
    System.out.println("\t\t\t\t\t\t"+"   Time: 07:30 - 09:30 | cost: Rs 2800");

    System.out.println("\t\t\t\t\t\t"+"3. SG-401 - SpiceJet");
    System.out.println("\t\t\t\t\t\t"+"   Time: 09:00 - 11:00 | cost: Rs 2300");

    System.out.println("\t\t\t\t\t\t"+"4. UK-879 - Vistara");
    System.out.println("\t\t\t\t\t\t"+"   Time: 13:00 - 15:00 | cost: Rs 3000");

    System.out.println("\t\t\t\t\t\t"+"5. G8-512 - GoAir");
    System.out.println("\t\t\t\t\t\t"+"   Time: 18:30 - 20:30 | Fare: Rs 2600"+ Colors.reset);

    System.out.print(Colors.blue +"\t\t\t\t\t\t"+"Select Flight : "+ Colors.reset);
    int c = sc.nextInt();

    String flightName = "";
    String time = "";
    int cost = 0;

    switch (c) {
        case 1:
            flightName = "6E-2456 - IndiGo Airlines";
            time = "06:00 - 08:00";
           cost = 2500;
            break;
        case 2:
            flightName = "AI-308 - Air India";
            time = "07:30 - 09:30";
            cost = 2800;
            break;
        case 3:
            flightName = "SG-401 - SpiceJet";
            time = "09:00 - 11:00";
            cost = 2300;
            break;
        case 4:
            flightName = "UK-879 - Vistara";
            time = "13:00 - 15:00";
            cost = 3000;
            break;
        case 5:
            flightName = "G8-512 - GoAir";
            time = "18:30 - 20:30";
            cost = 2600;
            break;
        default:
            System.out.println(Colors.red + "\t\t\t\t\t\t"+"Invalid Flight Option!" + Colors.reset);
            return;
    }

    
    System.out.println(Colors.cyan + "\t\t\t\t\t\t"+"--- Flight Details ---" + Colors.reset);
    System.out.println(Colors.green + "\t\t\t\t\t\t"+"Flight: " + flightName + Colors.reset);
    System.out.println("\t\t\t\t\t\t"+"From: " + start + "  ->  To: " + end);
    System.out.println("\t\t\t\t\t\t"+"Time: " + time);
    System.out.println("\t\t\t\t\t\t"+"cost: Rs " + cost);


    makePayment(cost);

    System.out.println(Colors.green + "\t\t\t\t\t\t"+"Flight booked successfully from " + start + " to " + end + "!" + Colors.reset);
}


    static void instantRide() {
        System.out.println(Colors.bold + Colors.cyan + "\t\t\t\t\t\t"+"--- INSTANT RIDE ---" + Colors.reset);
        System.out.println(Colors.yellow +"\t\t\t\t\t\t"+"1. Bike");
        System.out.println("\t\t\t\t\t\t"+"2. Auto");
        System.out.println("\t\t\t\t\t\t"+"3. Car");
        System.out.println("\t\t\t\t\t\t4.back to main menu");
        System.out.println("\t\t\t\t\t\t 5.exit");
        System.out.print(Colors.blue+"\t\t\t\t\t\t"+"Enter your choice: ");
        int ch = sc.nextInt();

        System.out.print(Colors.pink +"\t\t\t\t\t\t"+"Enter Pickup Point: ");
        sc.nextLine();
        String pickup = sc.nextLine();
        System.out.print(Colors.blue +"\t\t\t\t\t\t"+"Enter Destination Point: ");
        String destination = sc.nextLine();

        switch (ch) {
            case 1: bikeRide(pickup, destination); break;
            case 2: autoRide(pickup, destination); break;
            case 3: carRide(pickup, destination); break;
            case 4: return;
            case 5:
               System.out.println(Colors.green + "\n\t\t\t\tThank you for using our Explore Ease Application!");
                       System.out.println("\t\t\t\tWe hope to serve you again soon!" + Colors.reset);
                 System.exit(0);
            default: System.out.println(Colors.red + "\t\t\t\t\t\t"+"Invalid Option!" + Colors.reset);
        }
    }


static void bikeRide(String pickup, String dest) {
    System.out.println(Colors.bold + Colors.cyan + "\t\t\t\t\t\t"+"--- Available Bikes ---" + Colors.reset);

    System.out.println(Colors.yellow +"\t\t\t\t\t\t"+"1. MH01 AB1234 - Yamaha FZ");
    System.out.println("\t\t\t\t\t\t"+"   cost: Rs 50 per km");

    System.out.println("\t\t\t\t\t\t"+"2. AP09 CD5678 - Honda Activa");
    System.out.println("\t\t\t\t\t\t"+"  cost: Rs 60 per km");

    System.out.println("\t\t\t\t\t\t"+"3. TS07 EF9012 - Hero Splendor");
    System.out.println("\t\t\t\t\t\t"+"   cost: Rs 55 per km");

    System.out.println("\t\t\t\t\t\t"+"4. KA05 GH3456 - Bajaj Pulsar");
    System.out.println("\t\t\t\t\t\t"+"   cost: Rs 65 per km");

    System.out.println("\t\t\t\t\t\t"+"5. TN10 IJ7890 - TVS Ntorq");
    System.out.println("\t\t\t\t\t\t"+"   cost: Rs 70 per km"+Colors.reset+ Colors.reset);

    System.out.print(Colors.blue+"\t\t\t\t\t\t"+"Select Bike: "+ Colors.reset);
    int c = sc.nextInt();

    String bikeName = "";
    int cost = 0;

    switch (c) {
        case 1:
            bikeName = "MH01 AB1234 - Yamaha FZ";
            cost = 50;
            break;
        case 2:
            bikeName = "AP09 CD5678 - Honda Activa";
            cost = 60;
            break;
        case 3:
            bikeName = "TS07 EF9012 - Hero Splendor";
            cost = 55;
            break;
        case 4:
            bikeName = "KA05 GH3456 - Bajaj Pulsar";
            cost = 65;
            break;
        case 5:
            bikeName = "TN10 IJ7890 - TVS Ntorq";
            cost = 70;
            break;
        default:
            System.out.println(Colors.red +"\t\t\t\t\t\t"+ "Invalid Bike Option!" + Colors.reset);
            return;
    }

   
    System.out.println(Colors.cyan + "\t\t\t\t\t\t"+"--- Ride Details ---" + Colors.reset);
    System.out.println(Colors.pink +"\t\t\t\t\t\t"+ "Bike: " + bikeName + Colors.reset);
    System.out.println("\t\t\t\t\t\t"+"Pickup Location: " + pickup);
    System.out.println("\t\t\t\t\t\t"+"Destination: " + dest);
    System.out.println("\t\t\t\t\t\t"+"Fare: Rs " + cost + " per km"+ Colors.reset);

    
    makePayment(cost);

    System.out.println(Colors.green + "\t\t\t\t\t\t"+"Your " + bikeName + " is arriving at " 
                       + pickup + " to go to " + dest + "!" + Colors.reset);
}

   
static void autoRide(String pickup, String dest) {
    System.out.println(Colors.bold + Colors.cyan + "\t\t\t\t\t\t"+"--- Available Autos ---" + Colors.reset);

    System.out.println(Colors.yellow +"\t\t\t\t\t\t"+"1. TS09 AU1234 - Ola Auto");
    System.out.println("\t\t\t\t\t\t"+"   cost: Rs 80 per km");

    System.out.println("\t\t\t\t\t\t"+"2. AP11 AU5678 - Rapido Auto");
    System.out.println("\t\t\t\t\t\t"+"   cost: Rs 90 per km");

    System.out.println("\t\t\t\t\t\t"+"3. MH08 AU9101 - City Auto");
    System.out.println("\t\t\t\t\t\t"+"   cost: Rs 85 per km");

    System.out.println("\t\t\t\t\t\t"+"4. KA06 AU3456 - Green Auto");
    System.out.println("\t\t\t\t\t\t"+"   cost: Rs 100 per km");

    System.out.println("\t\t\t\t\t\t"+"5. TN02 AU7890 - Go Auto");
    System.out.println("\t\t\t\t\t\t"+"   cost: Rs 95 per km"+ Colors.reset);

    System.out.print(Colors.blue +"\t\t\t\t\t\t"+"Select Auto: "+ Colors.reset);
    int c = sc.nextInt();

    String autoName = "";
    int cost = 0;

    switch (c) {
        case 1:
            autoName = "TS09 AU1234 - Ola Auto";
            cost = 80;
            break;
        case 2:
            autoName = "AP11 AU5678 - Rapido Auto";
            cost = 90;
            break;
        case 3:
            autoName = "MH08 AU9101 - City Auto";
            cost = 85;
            break;
        case 4:
            autoName = "KA06 AU3456 - Green Auto";
            cost = 100;
            break;
        case 5:
            autoName = "TN02 AU7890 - Go Auto";
            cost = 95;
            break;
        default:
            System.out.println(Colors.red +"\t\t\t\t\t\t"+ "Invalid Auto Option!" + Colors.reset);
            return;
    }

    
    System.out.println(Colors.cyan + "\t\t\t\t\t\t"+"--- Ride Details ---" + Colors.reset);
    System.out.println(Colors.green +"\t\t\t\t\t\t"+ "Auto: " + autoName + Colors.reset);
    System.out.println("\t\t\t\t\t\t"+"Pickup Location: " + pickup);
    System.out.println("\t\t\t\t\t\t"+"Destination: " + dest);
    System.out.println("\t\t\t\t\t\t"+"cost: Rs " + cost + " per km");

    
    makePayment(cost);

    System.out.println(Colors.green + "\t\t\t\t\t\t"+"Your " + autoName + " is arriving at " 
                       + pickup + " to go to " + dest + "!" + Colors.reset);
}

 

static void carRide(String pickup, String dest) {
    System.out.println(Colors.bold + Colors.cyan + "\t\t\t\t\t\t--- Available Cars ---" + Colors.reset);

    System.out.println(Colors.yellow +"\t\t\t\t\t\t1. TS09 CR1234 - Ola Mini");
    System.out.println("\t\t\t\t\t\t   Cost: Rs 150 per km");

    System.out.println("\t\t\t\t\t\t2. AP11 CR5678 - Uber Sedan");
    System.out.println("\t\t\t\t\t\t   Cost: Rs 180 per km");

    System.out.println("\t\t\t\t\t\t3. MH08 CR9101 - Swift Dzire");
    System.out.println("\t\t\t\t\t\t   Cost: Rs 170 per km");

    System.out.println("\t\t\t\t\t\t4. KA06 CR3456 - Innova");
    System.out.println("\t\t\t\t\t\t   Cost: Rs 220 per km");

    System.out.println("\t\t\t\t\t\t5. TN02 CR7890 - Creta");
    System.out.println("\t\t\t\t\t\t   Cost: Rs 250 per km"+ Colors.reset);

    System.out.print(Colors.blue +"\t\t\t\t\t\tSelect Car : "+ Colors.reset);
    int c = sc.nextInt();

    String carName = "";
    int cost = 0;

    switch (c) {
        case 1:
            carName = "TS09 CR1234 - Ola Mini";
            cost = 150;
            break;
        case 2:
            carName = "AP11 CR5678 - Uber Sedan";
            cost = 180;
            break;
        case 3:
            carName = "MH08 CR9101 - Swift Dzire";
            cost = 170;
            break;
        case 4:
            carName = "KA06 CR3456 - Innova";
            cost = 220;
            break;
        case 5:
            carName = "TN02 CR7890 - Creta";
            cost = 250;
            break;
        default:
            System.out.println(Colors.red + "\t\t\t\t\t\tInvalid Car Option!" + Colors.reset);
            return;
    }

    
    System.out.println(Colors.cyan + "\t\t\t\t\t\t--- Ride Details ---" + Colors.reset);
    System.out.println(Colors.green + "\t\t\t\t\t\tCar: " + carName + Colors.reset);
    System.out.println("\t\t\t\t\t\tPickup Location: " + pickup);
    System.out.println("\t\t\t\t\t\tDestination: " + dest);
    System.out.println("\t\t\t\t\t\tCost: Rs " + cost + " per km");


    makePayment(cost);

    System.out.println(Colors.green + "\t\t\t\t\t\tYour " + carName + " is arriving at "
                       + pickup + " to go to " + dest + "!" + Colors.reset);
}


    static void makePayment(double amount) {
        Payments.amountToPay = amount;
        Payments pay = new Payments();

        System.out.println(Colors.cyan + "\t\t\t\t\t\t"+"Select Payment Method:" + Colors.reset);
        System.out.println(Colors.yellow+"\t\t\t\t\t\t"+"1. Paytm");
        System.out.println("\t\t\t\t\t\t"+"2. PhonePe");
        System.out.println("\t\t\t\t\t\t"+"3. GooglePay");
     System.out.println("\t\t\t\t\t\t"+"4. creditCard");
       System.out.println("\t\t\t\t\t\t"+"5. AccountNumber");
       System.out.println("\t\t\t\t\t\t6.back to main menu"+ Colors.reset);
        System.out.println(Colors.red+"\t\t\t\t\t\t"+"7.exit"+ Colors.reset);


    
        System.out.print(Colors.blue+"\t\t\t\t\t\t"+"Enter choice: ");
        int p = sc.nextInt();

        switch (p) {
            case 1: pay.paytm(); break;
            case 2: pay.phonePay(); break;
            case 3: pay.googlePay(); break;
            case 4: pay.creditCard();break;
            case 5: pay.accountNumber();break;
            case 6:return;
            case 7:
                 System.out.println(Colors.green + "\n\t\t\t\tThank you for using our Explore Ease Application!");
                       System.out.println("\t\t\t\tWe hope to serve you again soon!" + Colors.reset);
                 System.exit(0);

            default: System.out.println(Colors.red +"\t\t\t\t\t\t\t"+ "Invalid payment choice!" + Colors.reset);
        }

        System.out.println(Colors.green +"\t\t\t\t\t\t\t"+ " Booking Confirmed! Amount Paid: Rs " + amount + Colors.reset);
    }

//============================================================================================================ FAMOUS PLACES ======================================================================================================================================

//Famous Places


 public static void FamousPlaces() {
        while (true) {
            PlacesList();
            System.out.println(Colors.magenta+"\n\t\t\t\t\t\t\tDo you want to continue? (yes): ");
            String cont = sc.next();
            if (!cont.equalsIgnoreCase("yes")) {
                System.out.println(Colors.green+"\t\t\t\t\t\t\tThank you for using our service!");
                break;
            }
        }
    }

  public static void PlacesList() {
        System.out.println(Colors.cyan + "\t\t\t\t\t\t\t" + "===== Famous Places =====" + Colors.reset);
        System.out.println(Colors.yellow+"\t\t\t\t\t\t\t" + "1.India");
        System.out.println("\t\t\t\t\t\t\t" + "2.International");
        System.out.println("\t\t\t\t\t\t\t" +"3.back to main menu");
        System.out.println("\t\t\t\t\t\t\t4.exit");
        System.out.print("\t\t\t\t\t\t\t" + "Enter your choice: "+Colors.reset);
        int n1 = sc.nextInt();
        switch (n1) {
            case 1:
                india();
                break;
            case 2:
                inter();
                break;
            case 3:
                 return;
            case 4:
                  System.out.println(Colors.green + "\n\t\t\t\tThank you for using our Explore Ease Application!");
                       System.out.println("\t\t\t\tWe hope to serve you again soon!" + Colors.reset);
                  System.exit(0);
            default:
                System.out.println(Colors.red+"Enter correct input"+Colors.reset);
        }
    }

    // ===== INDIA PLACES =====
    public static void india() {
        System.out.println(Colors.cyan + "\t\t\t\t\t\t\t" + "==== Famous Places in India ====" + Colors.reset);
        System.out.println(Colors.yellow + "\t\t\t\t\t\t\t" + "1. Tirupathi" + Colors.reset);
        System.out.println(Colors.yellow + "\t\t\t\t\t\t\t" + "2. Goa" + Colors.reset);
        System.out.println(Colors.yellow + "\t\t\t\t\t\t\t" + "3. Araku" + Colors.reset);
        System.out.println(Colors.yellow + "\t\t\t\t\t\t\t" + "4. Pondicherry" + Colors.reset);
        System.out.println(Colors.yellow + "\t\t\t\t\t\t\t" + "5. Kodaikanal" );
        System.out.println("\t\t\t\t\t\t\t 6.back to main menu"+Colors.reset);
        System.out.println(Colors.red +"\t\t\t\t\t\t\t 7.exit"+Colors.reset);
        

        System.out.print(Colors.blue +"\t\t\t\t\t\t\t" + "Enter your choice: "+Colors.reset);
        int n2 = sc.nextInt();
        switch (n2) {
            case 1:
                tirupathi();
                break;
            case 2:
                goa();
                break;
            case 3:
                araku();
                break;
            case 4:
                pondicherry();
                break;
            case 5:
                kodaikanal();
                break;
            case 6:
                 return;
             case 7:
                     System.out.println(Colors.green + "\n\t\t\t\tThank you for using our Explore Ease Application!");
                       System.out.println("\t\t\t\tWe hope to serve you again soon!" + Colors.reset);
                        System.exit(0);
            default:
                System.out.println(Colors.red+"\t\t\t\t\t\t\t" +"Enter valid number");
        }


        guideBooking();
    }

  public static void guideBooking() {
        System.out.println(Colors.magenta+"\n\t\t\t\t\t\tDo you want to book a tourist guide? (yes/no): "+Colors.reset);
        String choice = sc.next();
        if (choice.equalsIgnoreCase("yes")) {
            System.out.println(Colors.blue+"\t\t\t\t\t\t\t" +"Select guide language: "+Colors.reset);
            System.out.println(Colors.pink+"\t\t\t\t\t\t\t" +"1. English");
            System.out.println("\t\t\t\t\t\t\t" +"2. Hindi");
            System.out.println("\t\t\t\t\t\t\t" +"3. Telugu");
            System.out.println("\t\t\t\t\t\t\t"+"4.back to main menu"+Colors.reset);
            System.out.println(Colors.red+"\t\t\t\t\t\t\t5.exit"+Colors.reset);
            System.out.print("\t\t\t\t\t\t\t" +"Enter your choice: ");
            int langChoice = sc.nextInt();
            String language = "";
            switch (langChoice) {
                case 1: language = "English";
                    System.out.println(Colors.yellow+"\t\t\t\t\t\t\t" +"======YOUR GUIDE DETAILS======"+Colors.reset);
                         System.out.println(Colors.cyan+"\t\t\t\t\t\t\t" +"Guide Name: ashok naik"+Colors.reset);
                         System.out.println(Colors.pink+"\t\t\t\t\t\t\t" +"Mobile Number: 56782039219"+Colors.reset);
                         break;
                 
                case 2: language = "Hindi";
                    System.out.println(Colors.yellow+"\t\t\t\t\t\t\t" +"======YOUR GUIDE DETAILS======"+Colors.reset);

                        System.out.println(Colors.cyan+"\t\t\t\t\t\t\t" +"Guide Name: ramesh "+Colors.reset);
                        System.out.println(Colors.pink+"\t\t\t\t\t\t\t" +"Mobile Number: 76782039219"+Colors.reset);
                        break;

                case 3: language = "Telugu"; 
                    System.out.println(Colors.yellow+"\t\t\t\t\t\t\t" +"======YOUR GUIDE DETAILS======"+Colors.reset);
                        
                        System.out.println(Colors.cyan+"\t\t\t\t\t\t\t" +"Guide Name: somesh"+Colors.reset);
                        System.out.println(Colors.pink+"\t\t\t\t\t\t\t" +"Mobile Number: 56782039219"+Colors.reset);
                        break;
               case 4:
                         return;
              case 5:
                     System.out.println(Colors.green + "\n\t\t\t\tThank you for using our Explore Ease Application!");
                       System.out.println("\t\t\t\tWe hope to serve you again soon!" + Colors.reset);
                      System.exit(0);
                default: 
                        language = "English";
                    System.out.println(Colors.yellow+"\t\t\t\t\t\t\t" +"======YOUR GUIDE DETAILS======"+Colors.reset);
                        System.out.println(Colors.cyan+"\t\t\t\t\t\t\t" +"Guide Name: shailendra naik"+Colors.reset);
                        System.out.println(Colors.pink+"\t\t\t\t\t\t\t" +"Mobile Number: 567820392654"+Colors.reset);
                        break;
            }

            System.out.print(Colors.yellow+"\t\t\t\t\t\t\t" +"Enter number of days for the guide: "+Colors.reset);
            int days = sc.nextInt();
            System.out.println(Colors.yellow+"\t\t\t\t\t\t\t" +"Enter the date to start the plan(dd/mm/yyyy)"+Colors.reset); 
            String date=sc.next();
            double costPerDay = 1000; 
            double total = days * costPerDay;
            
            System.out.println(Colors.cyan+"\n\t\t\t\t\t\t\tGuide Language: " + language);
            System.out.println("\t\t\t\t\t\t\t" +"Days: " + days);
            System.out.println("\t\t\t\t\t\t\t"+"Date: "+date+"to "+days+"days");
            System.out.println("\t\t\t\t\t\t\t" +"Total Cost: Rs " + total+Colors.reset);

            
            payment(total);

            System.out.println(Colors.green + "\t\t\t\t\t\t\t" +"Tourist guide booked successfully!" + Colors.reset);
        }
    }

public static void payment(double amount) {
    Payments.amountToPay = amount;  
    Payments payment = new Payments();

    System.out.println(Colors.pink+"\n\t\t\t\t\t\t\tSelect payment mode:"+Colors.reset);
    System.out.println(Colors.yellow+"\t\t\t\t\t\t\t" +"1. Paytm");
    System.out.println("\t\t\t\t\t\t\t" +"2. PhonePe");
    System.out.println("\t\t\t\t\t\t\t" +"3. Google Pay");
    System.out.println("\t\t\t\t\t\t\t" +"4. Credit Card");
    System.out.println("\t\t\t\t\t\t\t" +"5. Bank Transfer");
    System.out.println("\t\t\t\t\t\t\t6.back to main menu"+Colors.reset);
    System.out.println(Colors.red+"\t\t\t\t\t\t\t"+"7.exit"+Colors.reset);
 
    System.out.print(Colors.blue+"\t\t\t\t\t\t\t" +"Enter your choice: "+Colors.reset);
    int payChoice = sc.nextInt();
    boolean success = false;

    switch (payChoice) {
        case 1: success = payment.paytm(); break;
        case 2: success = payment.phonePay(); break;
        case 3: success = payment.googlePay(); break;
        case 4: success = payment.creditCard(); break;
        case 5: success = payment.accountNumber(); break;
        case 6:PlacesList(); 
        case 7: System.out.println(Colors.green + "\n\t\t\t\tThank you for using our Explore Ease Application!");
                       System.out.println("\t\t\t\tWe hope to serve you again soon!" + Colors.reset);
                 System.exit(0);
        default: System.out.println(Colors.red +"\t\t\t\t\t\t\t" + "Invalid payment option!" + Colors.reset);
    }

    if (success) {
        System.out.println(Colors.green +"\t\t\t\t\t\t\t" + "Thank you for booking your travel guide with us! We're excited to be part of your journey." + Colors.reset);
    } else {
        System.out.println(Colors.red +"\t\t\t\t\t\t\t" + "Payment failed!" + Colors.reset);
    }
}

    // ===== INDIA PLACES DETAILS =====
    public static void tirupathi() {
        System.out.println(Colors.cyan +"\t\t\t\t\t\t\t" + "<--- Welcome to Tirupathi --->");
        System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "These are the places to visit in Tirupathi" + Colors.reset);
        System.out.println(Colors.yellow + "\t\t\t\t\t\t\t" + "1. Sri Venkateswara Swamy Temple");
        System.out.println("\t\t\t\t\t\t\t" + "2. Sri Padmavathi Ammavari Temple");
        System.out.println("\t\t\t\t\t\t\t" + "3. Chandragiri Fort");
        System.out.println("\t\t\t\t\t\t\t" + "4. Talakona Water Falls");
        System.out.println("\t\t\t\t\t\t\t" + "5. Sri Venkateswara Zoological Park"+Colors.reset);
    }

    public static void goa() {
        System.out.println(Colors.cyan +"\t\t\t\t\t\t\t" + "<--- Welcome to Goa --->"+Colors.reset);
        System.out.println(Colors.pink+  "\t\t\t\t\t\t\t" + "These are the places to visit in Goa" + Colors.reset);
        System.out.println(Colors.yellow +  "\t\t\t\t\t\t\t" + "1. Dudhsagar Falls");
        System.out.println("\t\t\t\t\t\t\t" + "2. Basilica of Bom Jesus");
        System.out.println("\t\t\t\t\t\t\t" + "3. Fort Aguada");
        System.out.println("\t\t\t\t\t\t\t" + "4. Baga Beach");
        System.out.println("\t\t\t\t\t\t\t" + "5. St. Cajetan Church Goa"+Colors.reset);
    }

    public static void araku() {
        System.out.println(Colors.cyan +"\t\t\t\t\t\t\t" + "<--- Welcome to Araku --->");
        System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "These are the places to visit in Araku" + Colors.reset);
        System.out.println(Colors.yellow + "\t\t\t\t\t\t\t" + "1. Bora Caves");
        System.out.println("\t\t\t\t\t\t\t" + "2. Padmapuram Botanical Gardens");
        System.out.println("\t\t\t\t\t\t\t" + "3. Tribal Museum");
        System.out.println("\t\t\t\t\t\t\t" + "4. Coffee Plantations");
        System.out.println("\t\t\t\t\t\t\t" + "5. Katiki Waterfalls"+Colors.reset);
    }

    public static void pondicherry() {
        System.out.println(Colors.cyan +"\t\t\t\t\t\t\t" + "<--- Welcome to Pondicherry --->");
        System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "These are the places to visit in Pondicherry" + Colors.reset);
        System.out.println(Colors.yellow +  "\t\t\t\t\t\t\t" + "1. Promenade Beach");
        System.out.println("\t\t\t\t\t\t\t" + "2. Auroville");
        System.out.println("\t\t\t\t\t\t\t" + "3. White Town French Colony");
        System.out.println("\t\t\t\t\t\t\t" + "4. Paradise Beach");
        System.out.println("\t\t\t\t\t\t\t" + "5. Rock Beach"+Colors.reset);
    }

    public static void kodaikanal() {
        System.out.println(Colors.cyan +"\t\t\t\t\t\t\t" + "<--- Welcome to Kodaikanal --->");
        System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "These are the places to visit in Kodaikanal" + Colors.reset);
        System.out.println(Colors.yellow +  "\t\t\t\t\t\t\t" + "1. Kodaikanal Lake");
        System.out.println("\t\t\t\t\t\t\t" + "2. Coaker's Walk");
        System.out.println("\t\t\t\t\t\t\t" + "3. Pillar Rocks");
        System.out.println("\t\t\t\t\t\t\t" + "4. Bryant Park");
        System.out.println("\t\t\t\t\t\t\t" + "5. Pine Forest"+Colors.reset);
    }

    // ===== INTERNATIONAL PLACES =====
    public static void inter() {
        System.out.println(Colors.cyan + "\t\t\t\t\t\t" + "==== Famous International Places ====" + Colors.reset);
        System.out.println(Colors.yellow +  "\t\t\t\t\t\t\t" + "1. Paris");
        System.out.println("\t\t\t\t\t\t\t" + "2. Dubai");
        System.out.println("\t\t\t\t\t\t\t" + "3. Singapore");
        System.out.println("\t\t\t\t\t\t\t" + "4. Switzerland");
        System.out.println("\t\t\t\t\t\t\t" + "5. Thailand");
        System.out.println("\t\t\t\t\t\t\t"+"6.back to main menu"+ Colors.reset);
        System.out.println(Colors.red + "\t\t\t\t\t\t\t 7.exit"+ Colors.reset);
        System.out.print(Colors.blue + "\t\t\t\t\t\t\t" + "Enter your choice: "+ Colors.reset);
        int n3 = sc.nextInt();
        switch (n3) {
            case 1: paris(); break;
            case 2: dubai(); break;
            case 3: singapore(); break;
            case 4: switzerland(); break;
            case 5: thailand(); break;
            case 6:
                     return;
            case 7:
                   System.out.println(Colors.green + "\n\t\t\t\tThank you for using our Explore Ease Application!");
                       System.out.println("\t\t\t\tWe hope to serve you again soon!" + Colors.reset);

                    System.exit(0);
            default: System.out.println("\t\t\t\t\t\t\t"+"Enter valid number");
        }
      guideBooking();
    }

    public static void paris() { 
              System.out.println(Colors.cyan +"\t\t\t\t\t\t\t" +"<--- Welcome to Paris --->");
System.out.println(Colors.pink+"\t\t\t\t\t\t\t" +"These are the places to visit in paris"+Colors.reset);
        System.out.println(Colors.yellow+"\t\t\t\t\t\t\t" + "1. Eiffel Tower");
        System.out.println("\t\t\t\t\t\t\t" +"2. Louvre Museum");
        System.out.println("\t\t\t\t\t\t\t" +"3. Notre-Dame Cathedral");
        System.out.println("\t\t\t\t\t\t\t" +"4. Disneyland Paris");
        System.out.println("\t\t\t\t\t\t\t" +"5. Seine River Cruise"+Colors.reset);

                }
    public static void dubai() { 
             System.out.println(Colors.cyan +"\t\t\t\t\t\t\t" +"<--- Welcome to Dubai --->"+Colors.reset); 
System.out.println(Colors.pink+"\t\t\t\t\t\t\t" +"These are the places to visit in dubai"+Colors.reset);
        System.out.println(Colors.yellow+"\t\t\t\t\t\t\t" + "1. Burj Khalifa");
        System.out.println("\t\t\t\t\t\t\t" +"2. Palm Jumeirah");
        System.out.println("\t\t\t\t\t\t\t" +"3. Dubai Mall");
        System.out.println("\t\t\t\t\t\t\t" +"4. Desert Safari");
        System.out.println("\t\t\t\t\t\t\t" +"5. Dubai Marina"+Colors.reset);

     }
    public static void singapore() {
   System.out.println(Colors.cyan +"\t\t\t\t\t\t\t" +"<--- Welcome to Singapore --->"+Colors.reset);
System.out.println(Colors.pink+"These are the places to visit in singapore"+Colors.reset);
        System.out.println(Colors.yellow+"\t\t\t\t\t\t\t" + "1. Marina Bay Sands");
        System.out.println("\t\t\t\t\t\t\t" +"2. Gardens by the Bay");
        System.out.println("\t\t\t\t\t\t\t" +"3. Universal Studios");
        System.out.println("\t\t\t\t\t\t\t" +"4. Sentosa Island");
        System.out.println("\t\t\t\t\t\t\t" +"5. Merlion Park"+Colors.reset);

  }
    public static void switzerland() {
      System.out.println(Colors.cyan +"\t\t\t\t\t\t\t" +"<--- Welcome to Switzerland --->"+Colors.reset); 
System.out.println(Colors.pink+"\t\t\t\t\t\t\t" +"These are the places to visit in switzerland"+Colors.reset);
        System.out.println(Colors.yellow+ "\t\t\t\t\t\t\t" +"1. Mount Titlis");
        System.out.println("\t\t\t\t\t\t\t" +"2. Lake Geneva");
        System.out.println("\t\t\t\t\t\t\t" +"3. Rhine Falls");
        System.out.println("\t\t\t\t\t\t\t" +"4. Jungfraujoch");
        System.out.println("\t\t\t\t\t\t\t" +"5. Matterhorn"+Colors.reset);

   }
    public static void thailand() { 

System.out.println(Colors.cyan +"\t\t\t\t\t\t\t" +"<--- Welcome to Thailand --->"+Colors.reset);

System.out.println(Colors.pink+"\t\t\t\t\t\t\t" +"These are the places to visit in thailand"+Colors.reset);
        System.out.println(Colors.yellow+"\t\t\t\t\t\t\t" + "1. Phi Phi Islands");
        System.out.println("\t\t\t\t\t\t\t" +"2. Bangkok Temples");
        System.out.println("\t\t\t\t\t\t\t" +"3. Pattaya Beach");
        System.out.println("\t\t\t\t\t\t\t" +"4. Chiang Mai");
        System.out.println("\t\t\t\t\t\t\t" +"5. Phuket Big Buddha"+Colors.reset);
 
}



//========================================================================================================================== MAIN MENU ================================================================================================================================

//Main Menu



    int amount_food = 0;
    String cart1 = "";

    public void showMainMenu() {
        while (true) {
            System.out.println(Colors.cyan + "\n\t\t\t\t\t\t"+"========== MAIN MENU ==========" + Colors.reset);
            System.out.println(Colors.yellow +"\t\t\t\t\t\t\t"+ "1. Hotel Booking");
            System.out.println("\t\t\t\t\t\t\t"+"2. Food Section");
            System.out.println("\t\t\t\t\t\t\t"+"3. Famous Places ");
            System.out.println("\t\t\t\t\t\t\t"+"4. Tickets Booking");
            System.out.println("\t\t\t\t\t\t\t"+"5.Vehicle Rent");
            System.out.println(Colors.red +"\t\t\t\t\t\t\t"+ "6. Exit Application" + Colors.reset);
            System.out.print(Colors.blue + "\t\t\t\t\t\t\t"+"Enter your choice: " + Colors.reset);

            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    startHotelBooking();
                    break;
                case 2:
                    FoodSection();
                    break;
                case 3:
                        FamousPlaces();
                        break;
                case 4:
                         startTravelBooking();
                         break;
                                       
                case 5:
                          vehicleDetails();
                          break;                      
                case 6:
                    System.out.println(Colors.green + "\n\t\t\t\t\t\tThank you for using our Explore Ease Application!");
                       System.out.println("\t\t\t\t\t\tWe hope to serve you again soon!" + Colors.reset);

                    exitApp();
                    return;
                default:
                    System.out.println(Colors.red +"\t\t\t\t\t\t"+ "Invalid choice! Please enter a valid number." + Colors.reset);
            }
        }
    }

    public void exitApp() {
        System.out.println(Colors.pink + "\n\t\t\t\t\t\t"+"Thanks for choosing ExploreEase! Have a great day!" + Colors.reset);
        System.exit(0);
    }
    

    
    private boolean processPayment(double amount) {
        if (amount <= 0) {
            System.out.println(Colors.red +"\t\t\t\t\t\t"+ "Nothing to pay." + Colors.reset);
            return false;
        }
        Payments.amountToPay = amount;
        P paymentProcessor = new Payments();

        System.out.println(Colors.cyan + "\t\t\t\t\t\t"+"\n--- Select Payment Method ---" + Colors.reset);
        System.out.println(Colors.yellow +"\t\t\t\t\t\t"+ "1. Paytm");
        System.out.println("\t\t\t\t\t\t"+"2. PhonePe");
        System.out.println("\t\t\t\t\t\t"+"3. Google Pay");
        System.out.println("\t\t\t\t\t\t"+"4. Credit Card");
        System.out.println("\t\t\t\t\t\t"+"5. Bank Account Transfer" + Colors.reset);
        System.out.print(Colors.blue +"\t\t\t\t\t\t"+ "Enter your choice: " + Colors.reset);

        boolean success = false;
        int choice = sc.nextInt();
        switch (choice) {
            case 1: success = paymentProcessor.paytm(); break;
            case 2: success = paymentProcessor.phonePay(); break;
            case 3: success = paymentProcessor.googlePay(); break;
            case 4: success = paymentProcessor.creditCard(); break;
            case 5: success = paymentProcessor.accountNumber(); break;
            default: System.out.println(Colors.red +"\t\t\t\t\t\t"+ "Invalid payment option." + Colors.reset);
        }
        return success;
    }





//=================================================================================================================================== HOTEL BOOKING ============================================================================================================
  public void startHotelBooking() {
        System.out.println(Colors.bold + Colors.pink + "\t\t\t\t\t\t"+"\n\t\t\t\t\t\t=== WELCOME TO EXPLOREEASE HOTEL BOOKING ===" + Colors.reset);
        while (true) {
            System.out.println(Colors.cyan + "\t\t\t\t\t\t"+"\n\t\t\t\t\t\t\t---- HOTEL BOOKING MENU ----" + Colors.reset);
            System.out.println(Colors.yellow +"\t\t\t\t\t\t\t"+ "1. India Tours");
            System.out.println("\t\t\t\t\t\t\t"+"2. International Tours");
            System.out.println("\t\t\t\t\t\t\t"+"3. Exit ");
            System.out.println(Colors.red + "\t\t\t\t\t\t\t"+"4. Back to Main Menu" + Colors.reset);
            System.out.print(Colors.blue + "\t\t\t\t\t\t\t"+"Enter your choice: " + Colors.reset);
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    indiaTours();
                    break;
                case 2:
                    internationalTours();
                    break;
                case 3:System.out.println(Colors.pink + "\t\t\t\t\t\t\t"+"exiting... Thank you for visiting !"+ Colors.reset);break;
                case 4:
                    return;
                default:
                    System.out.println(Colors.red + "\t\t\t\t\t\t"+"Invalid choice! Please try again." + Colors.reset);
            }
        }
    }
    
    void indiaTours() {
        System.out.println(Colors.cyan + "\t\t\t\t\t\t"+"\n\t\t\t\t\t\t\t--- Popular Places in India ---" + Colors.reset);
        System.out.println(Colors.yellow +"\t\t\t\t\t\t\t"+ "1. Tirupathi");
        System.out.println("\t\t\t\t\t\t\t"+"2. Goa");
        System.out.println("\t\t\t\t\t\t\t"+"3. Araku");
        System.out.println("\t\t\t\t\t\t\t"+"4. Pondicherry");
        System.out.println("\t\t\t\t\t\t\t"+"5. Kodaikanal"+ Colors.reset);
       
        System.out.print(Colors.blue +"\t\t\t\t\t\t\t"+ "Select your destination: " + Colors.reset);
        int choice = sc.nextInt();

        switch (choice) {
            case 1: bookHotelIndia("Tirupathi"); break;
            case 2: bookHotelIndia("Goa"); break;
            case 3: bookHotelIndia("Araku"); break;
            case 4: bookHotelIndia("Pondicherry"); break;
            case 5: bookHotelIndia("Kodaikanal"); break;
            default: System.out.println(Colors.red +"\t\t\t\t\t\t\t"+ "Invalid selection!" + Colors.reset);
        }
    }

    void bookHotelIndia(String place) {
        String hotelName = "";
        int acPrice = 0, nonAcPrice = 0;

        System.out.println(Colors.cyan +"\t\t\t\t\t\t"+ "\n\t\t\t\t\t\t\t--- Hotels in " + place + " ---" + Colors.reset);
        switch (place) {
    case "Tirupathi":
        System.out.println(Colors.yellow +"\t\t\t\t\t\t1. Renest Tirupati     | AC: 3500 | Non-AC: 2000");
        System.out.println("\t\t\t\t\t\t2. Golden Tulip        | AC: 4000 | Non-AC: 2500");
        System.out.println("\t\t\t\t\t\t3. Minerva Grand       | AC: 3700 | Non-AC: 2100");
        System.out.println("\t\t\t\t\t\t4. Hotel Pai Viceroy   | AC: 3600 | Non-AC: 1900");
        System.out.println("\t\t\t\t\t\t5. Marasa Sarovar      | AC: 4200 | Non-AC: 2400"+ Colors.reset);
        break;

    case "Goa":
        System.out.println(Colors.yellow +"\t\t\t\t\t\t1. Taj Exotica         | AC: 5000 | Non-AC: 3000");
        System.out.println("\t\t\t\t\t\t2. Novotel Goa         | AC: 4500 | Non-AC: 2500");
        System.out.println("\t\t\t\t\t\t3. Leela Goa           | AC: 5500 | Non-AC: 3200");
        System.out.println("\t\t\t\t\t\t4. Holiday Inn Resort  | AC: 4800 | Non-AC: 2800");
        System.out.println("\t\t\t\t\t\t5. Radisson Blu        | AC: 4700 | Non-AC: 2600"+ Colors.reset);
        break;

    case "Araku":
        System.out.println(Colors.yellow +"\t\t\t\t\t\t1. Haritha Resort      | AC: 3000 | Non-AC: 1500");
        System.out.println("\t\t\t\t\t\t2. Ushodaya Resort     | AC: 2800 | Non-AC: 1400");
        System.out.println("\t\t\t\t\t\t3. Nature Nest Resort  | AC: 3200 | Non-AC: 1600");
        System.out.println("\t\t\t\t\t\t4. Hill View Resort    | AC: 3100 | Non-AC: 1500");
        System.out.println("\t\t\t\t\t\t5. Green Valley Resort | AC: 2900 | Non-AC: 1300"+ Colors.reset);
        break;

    case "Pondicherry":
        System.out.println(Colors.yellow +"\t\t\t\t\t\t1. Le Dupleix          | AC: 4000 | Non-AC: 2500");
        System.out.println("\t\t\t\t\t\t2. Villa Shanti        | AC: 3800 | Non-AC: 2200");
        System.out.println("\t\t\t\t\t\t3. The Promenade       | AC: 4200 | Non-AC: 2600");
        System.out.println("\t\t\t\t\t\t4. Shenbaga Hotel      | AC: 3900 | Non-AC: 2400");
        System.out.println("\t\t\t\t\t\t5. Accord Puducherry   | AC: 4100 | Non-AC: 2300"+ Colors.reset);
        break;

    case "Kodaikanal":
        System.out.println(Colors.yellow +"\t\t\t\t\t\t1. Sterling Kodai      | AC: 4500 | Non-AC: 2500");
        System.out.println("\t\t\t\t\t\t2. Kodai Resort        | AC: 4300 | Non-AC: 2300");
        System.out.println("\t\t\t\t\t\t3. JC Residency        | AC: 4600 | Non-AC: 2600");
        System.out.println("\t\t\t\t\t\t4. Hilltop Towers      | AC: 4400 | Non-AC: 2400");
        System.out.println("\t\t\t\t\t\t5. Raindrops Resort    | AC: 4700 | Non-AC: 2700"+ Colors.reset);
        break;
        }

        System.out.print(Colors.pink +"\t\t\t\t\t\t\t"+ "Select hotel: " + Colors.reset);
        int choice = sc.nextInt();

        // Assign hotel details based on selection
       if (place.equals("Tirupathi")) {
    if (choice == 1) { hotelName = "Renest Tirupati"; acPrice = 3500; nonAcPrice = 2000; }
    else if (choice == 2) { hotelName = "Golden Tulip"; acPrice = 4000; nonAcPrice = 2500; }
    else if (choice == 3) { hotelName = "Fortune Select Grand"; acPrice = 4200; nonAcPrice = 2600; }
    else if (choice == 4) { hotelName = "Hotel Bliss"; acPrice = 3800; nonAcPrice = 2200; }
    else if (choice == 5) { hotelName = "Minerva Grand"; acPrice = 3600; nonAcPrice = 2100; }

} else if (place.equals("Goa")) {
    if (choice == 1) { hotelName = "Taj Exotica"; acPrice = 5000; nonAcPrice = 3000; }
    else if (choice == 2) { hotelName = "Novotel Goa"; acPrice = 4500; nonAcPrice = 2500; }
    else if (choice == 3) { hotelName = "Leela Goa"; acPrice = 5200; nonAcPrice = 3200; }
    else if (choice == 4) { hotelName = "Radisson Blu Resort"; acPrice = 4800; nonAcPrice = 2800; }
    else if (choice == 5) { hotelName = "Holiday Inn Resort"; acPrice = 4600; nonAcPrice = 2700; }

} else if (place.equals("Araku")) {
    if (choice == 1) { hotelName = "Haritha Resort"; acPrice = 3000; nonAcPrice = 1500; }
    else if (choice == 2) { hotelName = "Ushodaya Resort"; acPrice = 2800; nonAcPrice = 1400; }
    else if (choice == 3) { hotelName = "SRK Resort"; acPrice = 3200; nonAcPrice = 1600; }
    else if (choice == 4) { hotelName = "Hill View Resort"; acPrice = 3100; nonAcPrice = 1550; }
    else if (choice == 5) { hotelName = "Nature Nest Resort"; acPrice = 3300; nonAcPrice = 1700; }

} else if (place.equals("Pondicherry")) {
    if (choice == 1) { hotelName = "Le Dupleix"; acPrice = 4000; nonAcPrice = 2500; }
    else if (choice == 2) { hotelName = "Villa Shanti"; acPrice = 3800; nonAcPrice = 2200; }
    else if (choice == 3) { hotelName = "The Promenade"; acPrice = 4200; nonAcPrice = 2600; }
    else if (choice == 4) { hotelName = "Anandha Inn"; acPrice = 3700; nonAcPrice = 2100; }
    else if (choice == 5) { hotelName = "Shenbaga Hotel"; acPrice = 3900; nonAcPrice = 2300; }

} else if (place.equals("Kodaikanal")) {
    if (choice == 1) { hotelName = "Sterling Kodai"; acPrice = 4500; nonAcPrice = 2500; }
    else if (choice == 2) { hotelName = "Kodai Resort"; acPrice = 4300; nonAcPrice = 2300; }
    else if (choice == 3) { hotelName = "Hilltop Towers"; acPrice = 4100; nonAcPrice = 2200; }
    else if (choice == 4) { hotelName = "Villa Retreat"; acPrice = 4400; nonAcPrice = 2400; }
    else if (choice == 5) { hotelName = "The Carlton"; acPrice = 4800; nonAcPrice = 2800; }
}


    if (hotelName.isEmpty()) {
            System.out.println(Colors.red + "\t\t\t\t\t\t"+"Invalid hotel selection!" + Colors.reset);
            return;
        }

        System.out.println(Colors.yellow + "\n\t\t\t\t\t\t\t1. Book Room");
        System.out.println("\t\t\t\t\t\t\t2. Book Table");
        System.out.println("\t\t\t\t\t\t\t3. Back" + Colors.reset);
        System.out.print(Colors.blue + "\t\t\t\t\t\t\tEnter your choice: " + Colors.reset);
        int type = sc.nextInt();

        int amount = 0;
        switch (type) {
            case 1:
                System.out.println(Colors.pink + "\t\t\t\t\t\t\tSelect Room Type:" + Colors.reset);
                System.out.println(Colors.yellow +"\t\t\t\t\t\t\t1. AC Room - Rs " + acPrice);
                System.out.println("\t\t\t\t\t\t\t2. Non-AC Room - Rs " + nonAcPrice);
                System.out.println("\t\t\t\t\t\t\t3. Back"+ Colors.reset);
                System.out.print(Colors.blue + "\t\t\t\t\t\t\tEnter your choice: " + Colors.reset);
                int room = sc.nextInt();
                if (room == 3) return;
                System.out.print(Colors.pink + "\t\t\t\t\t\t\tEnter number of rooms: " + Colors.reset);
                int n = sc.nextInt();
                if (room == 1) amount = acPrice * n;
                else if (room == 2) amount = nonAcPrice * n;
                break;

            case 2:
                System.out.print(Colors.pink + "\t\t\t\t\t\t\tEnter number of people: " + Colors.reset);
                int p = sc.nextInt();
                amount = p * 500;
                break;

            case 3:
                return;

            default:
                System.out.println(Colors.red + "\t\t\t\t\t\t\tInvalid choice!" + Colors.reset);
                return;
        }

        Payments.amountToPay = amount;
        Payments pay = new Payments();

        System.out.println(Colors.cyan + "\n\t\t\t\t\t\t\t--- PAYMENT OPTIONS ---" + Colors.reset);
        System.out.println(Colors.yellow +"\t\t\t\t\t\t\t1. Paytm");
        System.out.println("\t\t\t\t\t\t\t2. Google Pay");
        System.out.println("\t\t\t\t\t\t\t3. PhonePe");
        System.out.println("\t\t\t\t\t\t\t4. Credit Card");
        System.out.println("\t\t\t\t\t\t\t5. Account Transfer");
        System.out.println("\t\t\t\t\t\t\t6. Cancel Payment"+ Colors.reset);
        System.out.print(Colors.blue + "\t\t\t\t\t\t\tSelect Mode: " + Colors.reset);
        int payCh = sc.nextInt();

        boolean success = false;
        switch (payCh) {
            case 1:
                success = pay.paytm();
                break;
            case 2:
                success = pay.googlePay();
                break;
            case 3:
                success = pay.phonePay();
                break;
            case 4:
                success = pay.creditCard();
                break;
            case 5:
                success = pay.accountNumber();
                break;
            case 6:
                System.out.println(Colors.red + "\t\t\t\t\t\t\tPayment Cancelled!" + Colors.reset);
                return;
            default:
                System.out.println(Colors.red + "\t\t\t\t\t\t\tInvalid Mode!" + Colors.reset);
        }

        if (success)
            System.out.println(Colors.green + "\n\t\t\t\t\t\t\tBooking Successful at " + hotelName + "!" + Colors.reset);
        else
            System.out.println(Colors.red + "\n\t\t\t\t\t\t\tBooking Failed!" + Colors.reset);
    }


    void internationalTours() {
        System.out.println(Colors.pink +"\n\t\t\t\t\t\t"+ "--- Popular International Destinations ---" + Colors.reset);
        System.out.println(Colors.yellow + "\t\t\t\t\t\t\t"+"1. Paris");
           System.out.println("\t\t\t\t\t\t\t"+"2. Dubai");
         System.out.println("\t\t\t\t\t\t\t"+"3. Singapore");
         System.out.println("\t\t\t\t\t\t\t"+"4. Maldives");
         System.out.println("\t\t\t\t\t\t\t"+"5. Switzerland"+ Colors.reset);
        
        
        System.out.print(Colors.blue +"\t\t\t\t\t\t\t"+ "Select your destination: " + Colors.reset);
        int choice = sc.nextInt();

        switch (choice) {
            case 1: bookHotelInternational("Paris"); break;
            case 2: bookHotelInternational("Dubai"); break;
            case 3: bookHotelInternational("Singapore"); break;
            case 4: bookHotelInternational("Maldives"); break;
            case 5: bookHotelInternational("Switzerland"); break;
            default: System.out.println(Colors.red +"\t\t\t\t\t\t"+ "Invalid selection!" + Colors.reset);
        }
    }

    void bookHotelInternational(String place) {
        String hotelName = "";
        int acPrice = 0, nonAcPrice = 0;

        System.out.println(Colors.cyan + "\n\t\t\t\t\t\t\t"+"--- Hotels in " + place + " ---" + Colors.reset);
        switch (place) {
    case "Paris":
        System.out.println(Colors.yellow +"\t\t\t\t\t\t1. Hotel Eiffel        | AC: 10000 | Non-AC: 8000");
        System.out.println("\t\t\t\t\t\t2. Hotel Louvre        | AC: 9500  | Non-AC: 7500");
        System.out.println("\t\t\t\t\t\t3. Pullman Paris Tour  | AC: 10500 | Non-AC: 8500");
        System.out.println("\t\t\t\t\t\t4. Le Meurice          | AC: 12000 | Non-AC: 9500");
        System.out.println("\t\t\t\t\t\t5. Hotel Plaza Athenee | AC: 11500 | Non-AC: 9000"+ Colors.reset);
        break;

    case "Dubai":
        System.out.println(Colors.yellow +"\t\t\t\t\t\t1. Burj Al Arab        | AC: 15000 | Non-AC: 12000");
        System.out.println("\t\t\t\t\t\t2. Atlantis The Palm   | AC: 14000 | Non-AC: 11000");
        System.out.println("\t\t\t\t\t\t3. Jumeirah Beach Hotel| AC: 13500 | Non-AC: 10500");
        System.out.println("\t\t\t\t\t\t4. The Ritz-Carlton    | AC: 14500 | Non-AC: 11500");
        System.out.println("\t\t\t\t\t\t5. Armani Hotel        | AC: 16000 | Non-AC: 13000"+ Colors.reset);
        break;

    case "Singapore":
        System.out.println(Colors.yellow +"\t\t\t\t\t\t1. Marina Bay Sands    | AC: 14000 | Non-AC: 12000");
        System.out.println("\t\t\t\t\t\t2. Raffles Hotel       | AC: 13000 | Non-AC: 11000");
        System.out.println("\t\t\t\t\t\t3. The Fullerton       | AC: 12500 | Non-AC: 10500");
        System.out.println("\t\t\t\t\t\t4. Pan Pacific         | AC: 12000 | Non-AC: 9500");
        System.out.println("\t\t\t\t\t\t5. Shangri-La          | AC: 13500 | Non-AC: 11500"+ Colors.reset);
        break;

    case "Maldives":
        System.out.println(Colors.yellow +"\t\t\t\t\t\t1. Taj Coral Reef      | AC: 11000 | Non-AC: 9000");
        System.out.println("\t\t\t\t\t\t2. Soneva Jani         | AC: 10000 | Non-AC: 8500");
        System.out.println("\t\t\t\t\t\t3. Paradise Island     | AC: 9500  | Non-AC: 8000");
        System.out.println("\t\t\t\t\t\t4. Sun Siyam Iru Fushi | AC: 10500 | Non-AC: 8700");
        System.out.println("\t\t\t\t\t\t5. Baros Maldives      | AC: 11500 | Non-AC: 9500"+ Colors.reset);
        break;

    case "Switzerland":
        System.out.println(Colors.yellow +"\t\t\t\t\t\t1. The Dolder Grand    | AC: 20000 | Non-AC: 18000");
        System.out.println("\t\t\t\t\t\t2. Badrutt's Palace    | AC: 18000 | Non-AC: 16000");
        System.out.println("\t\t\t\t\t\t3. Kulm Hotel          | AC: 17500 | Non-AC: 15500");
        System.out.println("\t\t\t\t\t\t4. Grand Hotel Zermatt | AC: 19000 | Non-AC: 17000");
        System.out.println("\t\t\t\t\t\t5. Hotel Eden Roc      | AC: 18500 | Non-AC: 16500"+ Colors.reset);
        break;
}
System.out.print(Colors.pink + "\t\t\t\t\t\t\tSelect hotel: " + Colors.reset);
int choice = sc.nextInt();
    if (place.equals("Paris")) {
    if (choice == 1) { hotelName = "Hotel Eiffel"; acPrice = 10000; nonAcPrice = 8000; }
    else if (choice == 2) { hotelName = "Hotel Louvre"; acPrice = 9500; nonAcPrice = 7500; }
    else if (choice == 3) { hotelName = "Pullman Paris Tour"; acPrice = 10500; nonAcPrice = 8500; }
    else if (choice == 4) { hotelName = "Le Meurice"; acPrice = 12000; nonAcPrice = 9500; }
    else if (choice == 5) { hotelName = "Hotel Plaza Athenee"; acPrice = 11500; nonAcPrice = 9000; }

} else if (place.equals("Dubai")) {
    if (choice == 1) { hotelName = "Burj Al Arab"; acPrice = 15000; nonAcPrice = 12000; }
    else if (choice == 2) { hotelName = "Atlantis The Palm"; acPrice = 14000; nonAcPrice = 11000; }
    else if (choice == 3) { hotelName = "Jumeirah Beach Hotel"; acPrice = 13500; nonAcPrice = 10500; }
    else if (choice == 4) { hotelName = "The Ritz-Carlton"; acPrice = 14500; nonAcPrice = 11500; }
    else if (choice == 5) { hotelName = "Armani Hotel"; acPrice = 16000; nonAcPrice = 13000; }

} else if (place.equals("Singapore")) {
    if (choice == 1) { hotelName = "Marina Bay Sands"; acPrice = 14000; nonAcPrice = 12000; }
    else if (choice == 2) { hotelName = "Raffles Hotel"; acPrice = 13000; nonAcPrice = 11000; }
    else if (choice == 3) { hotelName = "The Fullerton"; acPrice = 12500; nonAcPrice = 10500; }
    else if (choice == 4) { hotelName = "Pan Pacific"; acPrice = 12000; nonAcPrice = 9500; }
    else if (choice == 5) { hotelName = "Shangri-La"; acPrice = 13500; nonAcPrice = 11500; }

} else if (place.equals("Maldives")) {
    if (choice == 1) { hotelName = "Taj Coral Reef"; acPrice = 11000; nonAcPrice = 9000; }
    else if (choice == 2) { hotelName = "Soneva Jani"; acPrice = 10000; nonAcPrice = 8500; }
    else if (choice == 3) { hotelName = "Paradise Island"; acPrice = 9500; nonAcPrice = 8000; }
    else if (choice == 4) { hotelName = "Sun Siyam Iru Fushi"; acPrice = 10500; nonAcPrice = 8700; }
    else if (choice == 5) { hotelName = "Baros Maldives"; acPrice = 11500; nonAcPrice = 9500; }

} else if (place.equals("Switzerland")) {
    if (choice == 1) { hotelName = "The Dolder Grand"; acPrice = 20000; nonAcPrice = 18000; }
    else if (choice == 2) { hotelName = "Badrutt's Palace"; acPrice = 18000; nonAcPrice = 16000; }
    else if (choice == 3) { hotelName = "Kulm Hotel"; acPrice = 17500; nonAcPrice = 15500; }
    else if (choice == 4) { hotelName = "Grand Hotel Zermatt"; acPrice = 19000; nonAcPrice = 17000; }
    else if (choice == 5) { hotelName = "Hotel Eden Roc"; acPrice = 18500; nonAcPrice = 16500; }
}

       if (hotelName.isEmpty()) {
            System.out.println(Colors.red + "\t\t\t\t\t\t"+"Invalid hotel selection!" + Colors.reset);
            return;
        }

        System.out.println(Colors.yellow + "\n\t\t\t\t\t\t\t1. Book Room");
        System.out.println("\t\t\t\t\t\t\t2. Book Table");
        System.out.println("\t\t\t\t\t\t\t3. Back" + Colors.reset);
        System.out.print(Colors.blue + "\t\t\t\t\t\t\tEnter your choice: " + Colors.reset);
        int type = sc.nextInt();

        int amount = 0;
        switch (type) {
            case 1:
                System.out.println(Colors.pink + "\t\t\t\t\t\t\tSelect Room Type:" + Colors.reset);
                System.out.println(Colors.yellow +"\t\t\t\t\t\t\t1. AC Room - Rs " + acPrice);
                System.out.println("\t\t\t\t\t\t\t2. Non-AC Room - Rs " + nonAcPrice);
                System.out.println("\t\t\t\t\t\t\t3. Back" + Colors.reset);
                System.out.print(Colors.blue + "\t\t\t\t\t\t\tEnter your choice: " + Colors.reset);
                int room = sc.nextInt();
                if (room == 3) return;
                System.out.print(Colors.pink + "\t\t\t\t\t\t\t\tEnter number of rooms: " + Colors.reset);
                int n = sc.nextInt();
                if (room == 1) amount = acPrice * n;
                else if (room == 2) amount = nonAcPrice * n;
                break;

            case 2:
                System.out.print(Colors.pink + "\t\t\t\t\t\t\tEnter number of people: " + Colors.reset);
                int p = sc.nextInt();
                amount = p * 500;
                break;

            case 3:
                return;

            default:
                System.out.println(Colors.red + "\t\t\t\t\t\t\tInvalid choice!" + Colors.reset);
                return;
        }

         Payments.amountToPay = amount;
        Payments pay = new Payments();

        System.out.println(Colors.cyan + "\n\t\t\t\t\t\t\t--- PAYMENT OPTIONS ---" + Colors.reset);
        System.out.println(Colors.yellow +"\t\t\t\t\t\t\t1. Paytm");
        System.out.println("\t\t\t\t\t\t\t2. Google Pay");
        System.out.println("\t\t\t\t\t\t\t3. PhonePe");
        System.out.println("\t\t\t\t\t\t\t4. Credit Card");
        System.out.println("\t\t\t\t\t\t\t5. Account Transfer");
        System.out.println("\t\t\t\t\t\t\t6. Cancel Payment" + Colors.reset);
        System.out.print(Colors.blue + "\t\t\t\t\t\t\tSelect Mode: " + Colors.reset);
        int payCh = sc.nextInt();

        boolean success = false;
        switch (payCh) {
            case 1:
                success = pay.paytm();
                break;
            case 2:
                success = pay.googlePay();
                break;
            case 3:
                success = pay.phonePay();
                break;
            case 4:
                success = pay.creditCard();
                break;
            case 5:
                success = pay.accountNumber();
                break;
            case 6:
                System.out.println(Colors.red + "\t\t\t\t\t\tPayment Cancelled!" + Colors.reset);
                return;
            default:
                System.out.println(Colors.red + "\t\t\t\t\t\tInvalid Mode!" + Colors.reset);
        }

        if (success)
            System.out.println(Colors.green + "\n\t\t\t\t\t\t\tBooking Successful at " + hotelName + "!" + Colors.reset);
        else
            System.out.println(Colors.red + "\n\t\t\t\t\t\tBooking Failed!" + Colors.reset);
    }




//============================================================================================================= FOOD SECTION ========================================================================================================================================


//food section
 public void FoodSection() {
        while (true) {
            System.out.println(Colors.cyan +"\t\t\t\t\t\t\t"+ "--- Food Regions ---");
            System.out.println(Colors.yellow +"\t\t\t\t\t\t\t"+ "1. North India");
            System.out.println("\t\t\t\t\t\t\t"+"2. South India");
            System.out.println("\t\t\t\t\t\t\t"+"3. International");
            System.out.println("\t\t\t\t\t\t\t"+"4. Back to main menu " );
            System.out.println("\t\t\t\t\t\t\t5. exit" + Colors.reset);


            System.out.print("\t\t\t\t\t\t\t"+"Enter your choice : ");

            int choice = sc.nextInt();
            switch (choice) {
                case 1: showMealType("North India"); 
                        break;
                case 2: showMealType("South India"); 
                        break;
                case 3: showMealType("International"); 
                        break;
                case 4: showMainMenu();
                case 5:
                  System.out.println(Colors.green + "\n\t\t\t\t\t\t\tThank you for using our Explore Ease Application!");
                       System.out.println("\t\t\t\t\t\t\tWe hope to serve you again soon!" + Colors.reset);

                        System.exit(0);
                default: System.out.println("\t\t\t\t\t\t\t"+"Enter the valid number");
            }
        }
    }

    private void showMealType(String region) {
        System.out.println(Colors.cyan +"\t\t\t\t\t\t\t"+ "--- Select Meal Type for " + region + " ---");
        System.out.println(Colors.yellow + "\t\t\t\t\t\t\t"+"1. Breakfast");
        System.out.println("\t\t\t\t\t\t\t"+"2. Lunch");
        System.out.println("\t\t\t\t\t\t\t"+"3. Dinner");
        System.out.println("\t\t\t\t\t\t\t"+"4. Snacks");
        System.out.println("\t\t\t\t\t\t\t"+"5. Back" );
       System.out.println("\t\t\t\t\t\t\t"+"6. exit" + Colors.reset);
       System.out.println("\t\t\t\t\t\t\t"+"Enter your choice");
        int choice = sc.nextInt();
        switch (region) {
            case "North India":
                if (choice == 1) 
                     showItemNorthIndiaBreakfast(); 
                else if (choice == 2) 
                     showItemNorthIndiaLunch();
                else if (choice == 3) 
                     showItemNorthIndiaDinner();
                else if(choice==4)
                     showItemNorthIndiaSnacks();
                else if(choice==5)
                      return;
                else if(choice==6)
                      System.out.println(Colors.green + "\n\t\t\t\t\t\t\tThank you for using our Explore Ease Application!");
                       System.out.println("\t\t\t\t\t\t\tWe hope to serve you again soon!" + Colors.reset);

                      System.exit(0);
                      break;  
            case "South India":
                if (choice == 1) 
                    showItemSouthIndiaBreakfast();
                else if (choice == 2)
                      showItemSouthIndiaLunch();
                else if (choice == 3) 
                   showItemSouthIndiaDinner();
                else if(choice==4)
                    showItemSouthIndiaSnacks();
                 else if(choice==5)
                      return;  
                else if(choice==6)
                     System.out.println(Colors.green + "\n\t\t\t\t\t\t\tThank you for using our Explore Ease Application!");
                       System.out.println("\t\t\t\t\t\t\tWe hope to serve you again soon!" + Colors.reset);

                      System.exit(0);
                      
                break;
            case "International":
                if (choice == 1) 
                    showItemInternationalBreakfast();
                else if (choice == 2) 
                    showItemInternationalLunch();
                else if (choice == 3) 
                    showItemInternationalDinner();
                else if(choice==4)
                    showItemInternationalSnacks();
                 else if(choice==5)
                      return;
               else if(choice==6)
                      System.out.println(Colors.green + "\n\t\t\t\t\t\t\t\tThank you for using our Explore Ease Application!");
                       System.out.println("\t\t\t\t\t\t\tWe hope to serve you again soon!" + Colors.reset);
                      System.exit(0);
                      
                break;
        }
    }
   
    public void showItemNorthIndiaBreakfast() {

    while (true) {
        System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "--- North Indian Breakfast Menu ---"+ Colors.reset);
        System.out.println(Colors.blue +"\t\t\t\t\t\t\t"+"1. Aloo Paratha with Curd       - 120 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"2. Paneer Paratha with Butter   - 150 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"3. Chole Bhature                - 130 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"4. Poori Sabzi                  - 110 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"5. Rajma Chawal (light style)   - 140 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"6. Stuffed Kulcha with Chole    - 130 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"7. Masala Chai with Pakoras     - 90 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"8. Paneer Bhurji with Roti      - 160 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"9. Lassi (Sweet/Salted)         - 80 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"10. Bread Pakora                - 100 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"11. Back to Breakfast Menu");
 System.out.println("\t\t\t\t\t\t\t"+"12. exit" + Colors.reset);
        System.out.print("\t\t\t\t\t\t\t"+"Enter your choice");



        int n = sc.nextInt();
        switch (n) {
            case 1:
  System.out.println(Colors.blue+
bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Kesar Da Dhaba, Amritsar" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Stuffed, Buttery, Served with Curd");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.9 / 5");
                handleFoodOrder("Aloo Paratha with Curd", 120);
                break;
            case 2:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Amritsari Zaika, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Paneer-filled, Soft, Rich in Butter");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.8 / 5");
                handleFoodOrder("Paneer Paratha with Butter", 150);
                break;
            case 3:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Sitaram Diwan Chand, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Fluffy Bhature with Spicy Chole");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.7 / 5");
                handleFoodOrder("Chole Bhature", 130);
                break;
            case 4:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t"+"Restaurant : Kanha Sweets, Jaipur" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Crispy Pooris with Tangy Sabzi");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.6 / 5");
                handleFoodOrder("Poori Sabzi", 110);
                break;
            case 5:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t"+"Restaurant : Rajma Rajesh, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Light, Comforting Rajma with Rice");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.5 / 5");
                handleFoodOrder("Rajma Chawal (light style)", 140);
                break;
            case 6:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t"+"Restaurant : Amritsari Kulcha Hub, Amritsar" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Stuffed Kulcha with Spiced Chole");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.8 / 5");
                handleFoodOrder("Stuffed Kulcha with Chole", 130);
                break;
            case 7:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Local Street Stall, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Strong Chai with Crunchy Pakoras");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.7 / 5");
                handleFoodOrder("Masala Chai with Pakoras", 90);
                break;
            case 8:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Punjabi Tandoor, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Spiced Paneer Bhurji with Fresh Rotis");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.6 / 5");
                handleFoodOrder("Paneer Bhurji with Roti", 160);
                break;
            case 9:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Gianâ€™s Lassi, Amritsar" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Sweet or Salted, Thick & Refreshing");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.9 / 5");
                handleFoodOrder("Lassi", 80);
                break;
            case 10:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t"+"Restaurant : Haldiramâ€™s, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Deep-fried Bread stuffed with Spices");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.6 / 5");
                handleFoodOrder("Bread Pakora", 100);
                break;
            case 11:
                FoodSection();
                break;
           case 12:
             System.out.println(Colors.green + "\n\t\t\t\t\t\t\tThank you for using our Travel Booking Application!");
             System.out.println("\t\t\t\t\t\t\tWe hope to serve you again soon!" + Colors.reset);
                 System.exit(0);
            default:
                System.out.println("\t\t\t\t\t\t\t"+"Invalid option.");
        }
    }
}

 public void showItemNorthIndiaLunch() {

    while (true) {
        System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "\n\t\t\t\t\t\t\t--- North Indian Lunch Menu ---"+ Colors.reset);
        System.out.println(Colors.blue +"\t\t\t\t\t\t\t"+"1. Shahi Paneer with Naan       - 260 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"2. Chicken Curry with Rice      - 300 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"3. Mutton Korma                 - 350 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"4. Dal Tadka with Jeera Rice    - 180 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"5. Baingan Bharta with Roti     - 200 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"6. Butter Naan (2 pcs)          - 80 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"7. Palak Paneer                 - 240 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"8. Chicken Kadhai               - 320 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"9. Veg Thali                    - 220 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"10. Rajma Masala with Rice      - 210 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"11. Back to Lunch Menu");
        System.out.println("\t\t\t\t\t\t\t"+"12. exit" + Colors.reset);
        System.out.println("\t\t\t\t\t\t\t"+"Enter your choice");



        int n = sc.nextInt();
        switch (n) {
            case 1:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Moti Mahal, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Creamy, Tomato-based, Mildly Spicy");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.8 / 5");
                handleFoodOrder("Shahi Paneer with Naan", 260);
                break;
            case 2:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Karimâ€™s, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Spicy Chicken Curry with Steamed Rice");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.7 / 5");
                handleFoodOrder("Chicken Curry with Rice", 300);
                break;
            case 3:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Mughal Darbar, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Rich, Aromatic, Tender Mutton");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.6 / 5");
                handleFoodOrder("Mutton Korma", 350);
                break;
            case 4:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Haldiramâ€™s, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Comfort Food, Spiced Dal with Rice");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.7 / 5");
                handleFoodOrder("Dal Tadka with Jeera Rice", 180);
                break;
            case 5:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Punjabi Zaika, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Smoky Baingan with Desi Masala");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.5 / 5");
                handleFoodOrder("Baingan Bharta with Roti", 200);
                break;
            case 6:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t"+"Restaurant : Bukhara, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Soft, Buttery Indian Bread");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.9 / 5");
                handleFoodOrder("Butter Naan", 80);
                break;
            case 7:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Rajdhani, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Spinach Gravy with Paneer Cubes");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.6 / 5");
                handleFoodOrder("Palak Paneer", 240);
                break;
            case 8:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Pind Balluchi, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Spicy Chicken Curry in Kadhai Masala");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.8 / 5");
                handleFoodOrder("Chicken Kadhai", 320);
                break;
            case 9:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Rajdhani Thali, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t\t"+"Taste      : Full Veg Thali with Dal, Sabzi, Roti, Rice");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.7 / 5");
                handleFoodOrder("Veg Thali", 220);
                break;
            case 10:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Rajma Rajesh, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Spiced Rajma Masala with Rice");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.6 / 5");
                handleFoodOrder("Rajma Masala with Rice", 210);
                break;
            case 11:
                FoodSection();
                break;
           case 12:
               System.out.println("Thank you.......");
               System.exit(0);
            default:
                System.out.println("\t\t\t\t\t\t"+"Invalid option.");
        }
    }
}
public void showItemNorthIndiaDinner() {

    while (true) {
        System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "\n\t\t\t\t\t\t\t--- North Indian Dinner Menu ---"+ Colors.reset);
        System.out.println(Colors.blue +"\t\t\t\t\t\t\t"+"1. Butter Chicken with Naan     - 350 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"2. Mutton Rogan Josh            - 380 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"3. Paneer Butter Masala         - 260 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"4. Dal Makhani with Naan        - 220 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"5. Chicken Tikka Masala         - 340 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"6. Veg Pulao with Raita         - 200 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"7. Malai Kofta                  - 250 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"8. Fish Curry with Rice         - 360 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"9. Tandoori Roti (2 pcs)        - 60 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"10. Gulab Jamun (Dessert)       - 90 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"11. Back to Dinner Menu" );
       System.out.println("\t\t\t\t\t\t\t"+"12. exit" + Colors.reset);
        System.out.println("\t\t\t\t\t\t\t"+"Enter your choice");



        int n = sc.nextInt();
        switch (n) {
            case 1:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t"+"Restaurant : Karimâ€™s, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Creamy, Spicy, Rich");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.9 / 5");
                handleFoodOrder("Butter Chicken with Naan", 350);
                break;
            case 2:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Mughal Darbar, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Juicy Mutton, Kashmiri Spices");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.7 / 5");
                handleFoodOrder("Mutton Rogan Josh", 380);
                break;
            case 3:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Moti Mahal, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Creamy Paneer Curry");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.8 / 5");
                handleFoodOrder("Paneer Butter Masala", 260);
                break;
            case 4:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Haveli, Amritsar" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Smoky, Creamy Dal");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.9 / 5");
                handleFoodOrder("Dal Makhani with Naan", 220);
                break;
            case 5:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Bukhara, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Grilled Chicken in Spicy Curry");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.8 / 5");
                handleFoodOrder("Chicken Tikka Masala", 340);
                break;
            case 6:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Rajdhani, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Aromatic Veg Pulao with Raita");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.6 / 5");
                handleFoodOrder("Veg Pulao with Raita", 200);
                break;
            case 7:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t"+"Restaurant : Punjabi Grill, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Creamy Kofta Curry");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.7 / 5");
                handleFoodOrder("Malai Kofta", 250);
                break;
            case 8:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Pind Balluchi, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Spicy Fish Curry with Rice");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.6 / 5");
                handleFoodOrder("Fish Curry with Rice", 360);
                break;
            case 9:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Local Dhaba, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Smoky, Freshly made Roti");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.5 / 5");
                handleFoodOrder("\t\t\t\t\t\t\t"+"Tandoori Roti", 60);
                break;
            case 10:
  System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------\n");
                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Haldiramâ€™s, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Sweet, Soft, Juicy");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.9 / 5");
                handleFoodOrder("Gulab Jamun", 90);
                break;
            case 11:
                FoodSection();
                break;
            case 12:
                 System.exit(0);
            default:
                System.out.println("\t\t\t\t\t\t"+"Invalid option.");
        }
    }
}
public void showItemNorthIndiaSnacks() {

    while (true) {
        System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "\n\t\t\t\t\t\t\t--- North Indian Snacks Menu ---" + Colors.reset);
        System.out.println(Colors.blue + "\t\t\t\t\t\t\t" + "1. Samosa                      - 40 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "2. Kachori                     - 50 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "3. Aloo Tikki Chaat            - 70 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "4. Golgappa (Pani Puri)        - 60 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "5. Pav Bhaji                   - 90 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "6. Dahi Bhalla                 - 80 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "7. Chaat Papdi                 - 75 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "8. Chole Kulche                - 100 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "9. Paneer Pakora               - 120 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "10. Jalebi (Sweet)             - 60 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "11. Back to Snacks Menu");
        System.out.println("\t\t\t\t\t\t\t" + "12. exit" + Colors.reset);
        System.out.println("\t\t\t\t\t\t\t" + "Enter your choice");

        int n = sc.nextInt();
        switch (n) {
            case 1:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Haldiram’s, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Crispy, Spiced Potato filling");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.8 / 5");
                handleFoodOrder("Samosa", 40);
                break;
            case 2:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Kanha Sweets, Jaipur" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Crispy, Stuffed with Dal Masala");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.7 / 5");
                handleFoodOrder("Kachori", 50);
                break;
            case 3:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Bitto Tikki Wala, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Spicy Potato Patty with Chutneys");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.6 / 5");
                handleFoodOrder("Aloo Tikki Chaat", 70);
                break;
            case 4:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Rajouri Garden Stall, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Tangy, Spicy, Crunchy Pani Puri");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.9 / 5");
                handleFoodOrder("Golgappa", 60);
                break;
            case 5:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Sardar Pav Bhaji, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Spicy Mashed Veg Curry with Butter Pav");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.8 / 5");
                handleFoodOrder("Pav Bhaji", 90);
                break;
            case 6:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Natraj Dahi Bhalla, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Soft, Sweet-Spicy Yogurt Balls");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.7 / 5");
                handleFoodOrder("Dahi Bhalla", 80);
                break;
            case 7:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Chandni Chowk Stall, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Crispy Papdi with Yogurt & Chutneys");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.6 / 5");
                handleFoodOrder("Chaat Papdi", 75);
                break;
            case 8:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Delhi Street Food" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Soft Kulche with Spicy Chole");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.7 / 5");
                handleFoodOrder("Chole Kulche", 100);
                break;
            case 9:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Local Dhaba, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Paneer stuffed & Deep fried Pakora");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.8 / 5");
                handleFoodOrder("Paneer Pakora", 120);
                break;
            case 10:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Old Famous Jalebi Wala, Delhi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Crispy, Juicy Sweet");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.9 / 5");
                handleFoodOrder("Jalebi", 60);
                break;
            case 11:
                FoodSection();
                break;
            case 12:
                System.exit(0);
            default:
                System.out.println("\t\t\t\t\t\t\t" + "Invalid option.");
        }
    }
}
public void showItemSouthIndiaBreakfast() {

    while (true) {
        System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "\n\t\t\t\t\t\t\t--- South Indian Breakfast Menu ---" + Colors.reset);
        System.out.println(Colors.blue + "\t\t\t\t\t\t\t" + "1. Masala Dosa               - 120 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "2. Idli Sambar               - 80 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "3. Medu Vada                 - 100 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "4. Upma                      - 90 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "5. Pongal                    - 110 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "6. Rava Dosa                 - 130 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "7. Onion Uttapam             - 100 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "8. Coconut Chutney           - 50 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "9. Filter Coffee             - 60 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "10. Kesari Bath (Sweet)      - 90 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "11. Back to Breakfast Menu");
        System.out.println("\t\t\t\t\t\t\t" + "12. exit" + Colors.reset);
        System.out.println("\t\t\t\t\t\t\t" + "Enter your choice");

        int n = sc.nextInt();
        switch (n) {
            case 1:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : MTR, Bangalore" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Crispy Dosa with Spiced Potato Filling");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.8 / 5");
                handleFoodOrder("Masala Dosa", 120);
                break;
            case 2:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Ratna Cafe, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Soft Idlis with Flavorful Sambar");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.7 / 5");
                handleFoodOrder("Idli Sambar", 80);
                break;
            case 3:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : A2B, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Crispy Fried Vada with Chutney");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.6 / 5");
                handleFoodOrder("Medu Vada", 100);
                break;
            case 4:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Saravana Bhavan, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Savory Semolina Porridge with Veggies");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.5 / 5");
                handleFoodOrder("Upma", 90);
                break;
            case 5:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Murugan Idli Shop, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Creamy Rice-Lentil Dish, Lightly Spiced");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.7 / 5");
                handleFoodOrder("Pongal", 110);
                break;
            case 6:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Sangeetha, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Crispy, Spiced Semolina Dosa");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.6 / 5");
                handleFoodOrder("Rava Dosa", 130);
                break;
            case 7:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : A2B, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Soft Uttapam with Onions & Spices");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.5 / 5");
                handleFoodOrder("Onion Uttapam", 100);
                break;
            case 8:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Local South Indian Stall" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Fresh Coconut Chutney");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.7 / 5");
                handleFoodOrder("Coconut Chutney", 50);
                break;
            case 9:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Indian Coffee House, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Strong, Filter Coffee");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.8 / 5");
                handleFoodOrder("Filter Coffee", 60);
                break;
            case 10:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Sree Krishna Sweets, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Sweet, Soft, Traditional South Indian Dessert");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.9 / 5");
                handleFoodOrder("Kesari Bath", 90);
                break;
            case 11:
                FoodSection();
                break;
            case 12:
                System.exit(0);
            default:
                System.out.println("\t\t\t\t\t\t\t" + "Invalid option.");
        }
    }
}
public void showItemSouthIndiaLunch() {

    while (true) {
        System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "\n\t\t\t\t\t\t\t--- South Indian Lunch Menu ---" + Colors.reset);
        System.out.println(Colors.blue + "\t\t\t\t\t\t\t" + "1. Andhra Meals               - 180 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "2. Tamil Nadu Thali           - 200 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "3. Kerala Sadhya              - 220 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "4. Hyderabadi Biryani         - 250 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "5. Lemon Rice with Pickle     - 150 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "6. Curd Rice                  - 130 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "7. Rasam with Rice            - 140 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "8. Fish Curry Meal            - 280 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "9. Sambar Rice with Papad     - 160 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "10. Payasam (Sweet)           - 100 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "11. Back to Lunch Menu");
        System.out.println("\t\t\t\t\t\t\t" + "12. exit" + Colors.reset);
        System.out.println("\t\t\t\t\t\t\t" + "Enter your choice");

        int n = sc.nextInt();
        switch (n) {
            case 1:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Rayalaseema Ruchulu, Hyderabad" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Spicy Andhra style full meals with Ghee Rice");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.8 / 5");
                handleFoodOrder("Andhra Meals", 180);
                break;
            case 2:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Anjappar, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Authentic Tamil Nadu Thali with Poriyal & Vatha Kuzhambu");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.7 / 5");
                handleFoodOrder("Tamil Nadu Thali", 200);
                break;
            case 3:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Paragon, Kozhikode" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Traditional Kerala feast with Banana Leaf serving");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.9 / 5");
                handleFoodOrder("Kerala Sadhya", 220);
                break;
            case 4:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Paradise, Hyderabad" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Flavourful, Spicy Biryani with Raita and Mirchi ka Salan");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.8 / 5");
                handleFoodOrder("Hyderabadi Biryani", 250);
                break;
            case 5:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Local Andhra Mess, Vijayawada" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Tangy Lemon Rice with Spicy Pickle");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.6 / 5");
                handleFoodOrder("Lemon Rice with Pickle", 150);
                break;
            case 6:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Sangeetha Veg, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Cool, Refreshing Curd Rice with Mustard Tadka");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.7 / 5");
                handleFoodOrder("Curd Rice", 130);
                break;
            case 7:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Saravana Bhavan, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Tangy, Peppery Rasam with Rice");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.6 / 5");
                handleFoodOrder("Rasam with Rice", 140);
                break;
            case 8:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Malabar Coast, Kochi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Spicy Fish Curry served with Rice and Sides");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.8 / 5");
                handleFoodOrder("Fish Curry Meal", 280);
                break;
            case 9:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : A2B, Bangalore" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Aromatic Sambar Rice with Crunchy Papad");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.7 / 5");
                handleFoodOrder("Sambar Rice with Papad", 160);
                break;
            case 10:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Sree Krishna Sweets, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Sweet, Creamy South Indian Payasam");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.9 / 5");
                handleFoodOrder("Payasam", 100);
                break;
            case 11:
                FoodSection();
                break;
            case 12:
                System.exit(0);
            default:
                System.out.println("\t\t\t\t\t\t\t" + "Invalid option.");
        }
    }
}
public void showItemSouthIndiaDinner() {

    while (true) {
        System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "\n\t\t\t\t\t\t\t--- South Indian Dinner Menu ---" + Colors.reset);
        System.out.println(Colors.blue + "\t\t\t\t\t\t\t" + "1. Ghee Roast Dosa            - 130 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "2. Vegetable Pulao            - 160 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "3. Chicken Chettinad Curry    - 240 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "4. Malabar Parotta with Curry - 180 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "5. Tamarind Rice (Puliyogare) - 150 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "6. Paneer Butter Masala Dosa  - 170 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "7. Mushroom Biryani           - 200 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "8. Egg Curry with Appam       - 210 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "9. Roti with Veg Kurma        - 160 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "10. Payasam (Dessert)         - 100 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "11. Back to Dinner Menu");
        System.out.println("\t\t\t\t\t\t\t" + "12. exit" + Colors.reset);
        System.out.println("\t\t\t\t\t\t\t" + "Enter your choice");

        int n = sc.nextInt();
        switch (n) {
            case 1:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : MTR, Bangalore" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Crispy Dosa roasted in Ghee");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.8 / 5");
                handleFoodOrder("Ghee Roast Dosa", 130);
                break;
            case 2:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Sangeetha Veg, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Aromatic rice cooked with spices and vegetables");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.7 / 5");
                handleFoodOrder("Vegetable Pulao", 160);
                break;
            case 3:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Anjappar, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Spicy Chettinad-style Chicken Curry");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.9 / 5");
                handleFoodOrder("Chicken Chettinad Curry", 240);
                break;
            case 4:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Paragon, Kozhikode" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Soft Parotta with Spicy Kerala Curry");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.8 / 5");
                handleFoodOrder("Malabar Parotta with Curry", 180);
                break;
            case 5:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Udupi Krishna Bhavan, Mysore" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Tangy Tamarind flavored rice with peanuts");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.6 / 5");
                handleFoodOrder("Tamarind Rice", 150);
                break;
            case 6:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : A2B, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Dosa filled with rich Paneer Butter Masala");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.8 / 5");
                handleFoodOrder("Paneer Butter Masala Dosa", 170);
                break;
            case 7:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Savera Hotel, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Aromatic Mushroom Biryani with Raita");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.7 / 5");
                handleFoodOrder("Mushroom Biryani", 200);
                break;
            case 8:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Malabar Coast, Kochi" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Spiced Egg Curry served with Appam");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.8 / 5");
                handleFoodOrder("Egg Curry with Appam", 210);
                break;
            case 9:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Saravana Bhavan, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Soft Roti with Flavored Vegetable Kurma");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.6 / 5");
                handleFoodOrder("Roti with Veg Kurma", 160);
                break;
            case 10:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------\n");
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Sree Krishna Sweets, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Sweet South Indian Payasam Dessert");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.9 / 5");
                handleFoodOrder("Payasam", 100);
                break;
            case 11:
                FoodSection();
                break;
            case 12:
                System.exit(0);
            default:
                System.out.println("\t\t\t\t\t\t\t" + "Invalid option.");
        }
    }
}
public void showItemSouthIndiaSnacks() {

    while (true) {
        System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "\n\t\t\t\t\t\t\t--- South Indian Snacks Menu ---" + Colors.reset);
        System.out.println(Colors.blue + "\t\t\t\t\t\t\t" + "1. Mysore Pak                 - 60 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "2. Banana Chips               - 50 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "3. Murukku                    - 70 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "4. Medu Vada                  - 100 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "5. Masala Idli Bites          - 120 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "6. Paniyaram                  - 90 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "7. Bhajiya                    - 80 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "8. Pakora                     - 70 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "9. Sundal                     - 60 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "10. Sweet Pongal              - 80 Rs");
        System.out.println("\t\t\t\t\t\t\t" + "11. Back to Snacks Menu");
        System.out.println("\t\t\t\t\t\t\t" + "12. exit" + Colors.reset);
        System.out.println("\t\t\t\t\t\t\t" + "Enter your choice");

        int n = sc.nextInt();
        switch (n) {
            case 1:
                System.out.println(Colors.blue + bold + "\t\t\t\t\t\t\t" + "--------Item Details--------");
                System.out.println();
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Sree Krishna Sweets, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Sweet, Melt-in-mouth, Traditional");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.8 / 5");
                handleFoodOrder("Mysore Pak", 60);
                break;
            case 2:
                System.out.println("\t\t\t\t\t\t\t" + "--------Item Details--------");
                System.out.println();
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Local Kerala Stall" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Crispy, Salted, Thin Banana Chips");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.7 / 5");
                handleFoodOrder("Banana Chips", 50);
                break;
            case 3:
                System.out.println("\t\t\t\t\t\t\t" + "--------Item Details--------");
                System.out.println();
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : A2B, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Crunchy, Spiced, Fried Murukku");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.6 / 5");
                handleFoodOrder("Murukku", 70);
                break;
            case 4:
                System.out.println("\t\t\t\t\t\t\t" + "--------Item Details--------");
                System.out.println();
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : MTR, Bangalore" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Crispy Fried Vada with Chutney");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.8 / 5");
                handleFoodOrder("Medu Vada", 100);
                break;
            case 5:
                System.out.println("\t\t\t\t\t\t\t" + "--------Item Details--------");
                System.out.println();
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Local South Indian Stall" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Spiced Mini Idlis, Perfect for Snacking");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.7 / 5");
                handleFoodOrder("Masala Idli Bites", 120);
                break;
            case 6:
                System.out.println("\t\t\t\t\t\t\t" + "--------Item Details--------");
                System.out.println();
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Saravana Bhavan, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Soft, Savory, Pan-fried Dumplings");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.6 / 5");
                handleFoodOrder("Paniyaram", 90);
                break;
            case 7:
                System.out.println("\t\t\t\t\t\t\t" + "--------Item Details--------");
                System.out.println();
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Murugan Idli Shop, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Fried Veg/Spice Fritters");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.7 / 5");
                handleFoodOrder("Bhajiya", 80);
                break;
            case 8:
                System.out.println("\t\t\t\t\t\t\t" + "--------Item Details--------");
                System.out.println();
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Local South Indian Stall" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Crisp, Spiced Pakoras");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.6 / 5");
                handleFoodOrder("Pakora", 70);
                break;
            case 9:
                System.out.println("\t\t\t\t\t\t\t" + "--------Item Details--------");
                System.out.println();
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Temple Stall, Tamil Nadu" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Boiled Chickpeas with Coconut and Spices");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.5 / 5");
                handleFoodOrder("Sundal", 60);
                break;
            case 10:
                System.out.println("\t\t\t\t\t\t\t" + "--------Item Details--------");
                System.out.println();
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t" + "Restaurant : Local Sweet Shop, Chennai" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t" + "Taste      : Soft, Sweet Pongal with Ghee");
                System.out.println("\t\t\t\t\t\t\t" + "Reviews    : 4.8 / 5");
                handleFoodOrder("Sweet Pongal", 80);
                break;
            case 11:
                FoodSection();
                break;
            case 12:
                System.exit(0);
            default:
                System.out.println("\t\t\t\t\t\t\t" + "Invalid option.");
        }
    }
}

public void showItemInternationalBreakfast() {
    while (true) {
        System.out.println(Colors.blue +"\t\t\t\t\t\t\t"+ "\n\t\t\t\t\t\t\t--- International Breakfast Menu ---"+ Colors.reset);
        System.out.println(Colors.blue +"\t\t\t\t\t\t\t"+"1. Croissant (France)             - 150 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"2. Pancakes with Syrup (USA)      - 180 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"3. Full English Breakfast (UK)    - 350 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"4. Bagel with Cream Cheese (USA)  - 200 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"5. Churros with Chocolate (Spain) - 180 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"6. Back to Food Menu");
   System.out.println("\t\t\t\t\t\t\t"+"7.exit" + Colors.reset);


       System.out.println("\t\t\t\t\t\t\t"+"Enter your choice");



        int n = sc.nextInt();
        switch (n) {
            case 1:
                System.out.println("\t\t\t\t\t\t\t"+"--------Item Details--------");
                System.out.println();

                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : CafÃ© de Flore, Paris" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Buttery, flaky, soft layers");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.8 / 5");
                handleFoodOrder("Croissant", 150);
                break;
            case 2:
                System.out.println("\t\t\t\t\t\t\t"+"--------Item Details--------");
                System.out.println();

                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : IHOP, USA" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Fluffy, Sweet with Maple Syrup");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.7 / 5");
                handleFoodOrder("Pancakes with Syrup", 180);
                break;
            case 3:
                System.out.println("\t\t\t\t\t\t\t"+"--------Item Details--------");
                System.out.println();

                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Local UK CafÃ©" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Eggs, Sausage, Bacon, Toast");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.6 / 5");
                handleFoodOrder("Full English Breakfast", 350);
                break;
            case 4:
               System.out.println("\t\t\t\t\t\t\t"+"--------Item Details--------");
                System.out.println();

                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : New York Bagel Co., USA" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Soft Bagel with Cream Cheese Spread");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.7 / 5");
                handleFoodOrder("Bagel with Cream Cheese", 200);
                break;
            case 5:
               System.out.println("\t\t\t\t\t\t\t"+"--------Item Details--------");
                System.out.println();

                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : ChurrerÃ­a, Madrid" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Fried Dough with Sweet Chocolate Dip");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.8 / 5");
                handleFoodOrder("\t\t\t\t\t\t\t"+"Churros with Chocolate", 180);
                break;
            case 6:
                FoodSection();
                break;

            case 7:
                System.exit(0);
            default:
                System.out.println("\t\t\t\t\t\t\t"+"Invalid option.");
        }
    }
}
public void showItemInternationalLunch() {
    while (true) {
        System.out.println(Colors.blue +"\t\t\t\t\t\t\t"+ "\n\t\t\t\t\t\t\t--- International Lunch Menu ---"+Colors.reset);
        System.out.println(Colors.blue +"\t\t\t\t\t\t\t"+"1. Sushi (Japan)                   - 600 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"2. Pizza Margherita (Italy)        - 400 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"3. Tacos (Mexico)                  - 250 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"4. Caesar Salad (USA)              - 200 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"5. Pad Thai (Thailand)             - 300 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"6. Back to Food Menu");
    System.out.println("\t\t\t\t\t\t\t"+"7. Back to Dinner Menu" + Colors.reset);


       System.out.println("\t\t\t\t\t\t\t"+"Enter your choice");



        int n = sc.nextInt();
        switch (n) {
            case 1:
               System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------");
                System.out.println();

                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Sukiyabashi Jiro, Tokyo" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Fresh Fish, Vinegared Rice");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.8 / 5");
                handleFoodOrder("Sushi", 600);
                break;
            case 2:
                System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------");
                System.out.println();

                System.out.println(Colors.pink + "\t\t\t\t\t\t\t"+"Restaurant : Pizzeria da Michele, Naples" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Thin Crust, Tomato, Mozzarella");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.9 / 5");
                handleFoodOrder("Pizza Margherita", 400);
                break;
            case 3:
             System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------");
                System.out.println();

                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : El Califa, Mexico City" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Corn Tortilla, Spiced Fillings");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.7 / 5");
                handleFoodOrder("Tacos", 250);
                break;
            case 4:
               System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------");
                System.out.println();

                System.out.println(Colors.pink + "\t\t\t\t\t\t\t"+"Restaurant : The Cheesecake Factory, USA" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Fresh Lettuce, Parmesan, Croutons");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.6 / 5");
                handleFoodOrder("Caesar Salad", 200);
                break;
            case 5:
                 System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------");
                System.out.println();

                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Thai Street Food, Bangkok" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Stir-Fried Noodles with Tamarind Sauce");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.7 / 5");
                handleFoodOrder("Pad Thai", 300);
                break;
            case 6:
               FoodSection();
                break;

            case 7:
                   System.out.println(Colors.green + "\n\t\t\t\t\t\t\tThank you for using our Explore Ease Application!");
                       System.out.println("\t\t\t\t\t\t\t\tWe hope to serve you again soon!" + Colors.reset);
                 System.exit(0);

            default:
                System.out.println("\t\t\t\t\t\t\t"+"Invalid option.");
        }
    }
}
public void showItemInternationalDinner() {
    while (true) {
        System.out.println(Colors.pink + "\t\t\t\t\t\t"+"\n\t\t\t\t\t\t\t--- International Dinner Menu ---"+ Colors.reset);
        System.out.println(Colors.blue +"\t\t\t\t\t\t\t"+"1. Burger with Fries (USA)        - 350 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"2. Lasagna (Italy)                - 400 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"3. Paella (Spain)                 - 500 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"4. Beef Steak (Argentina)         - 600 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"5. Grilled Salmon (Norway)        - 550 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"6. Back to Food Menu");
       System.out.println("\t\t\t\t\t\t\t"+"7. exit" + Colors.reset);


       System.out.println("\t\t\t\t\t\t\t"+"Enter your choice");



        int n = sc.nextInt();
        switch (n) {
            case 1:
                System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------");
                System.out.println();
                System.out.println(Colors.pink + "\t\t\t\t\t\t\t"+"Restaurant : Shake Shack, New York" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Juicy Beef Patty, Cheese, Lettuce, Bun");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.7 / 5");
                handleFoodOrder("Burger with Fries", 350);
                break;
            case 2:
               System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------");
                System.out.println();

                System.out.println(Colors.pink + "\t\t\t\t\t\t\t"+"Restaurant : Trattoria da Vinci, Italy" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Layers of Pasta, Cheese, BÃ©chamel Sauce");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.8 / 5");
                handleFoodOrder("Lasagna", 400);
                break;
            case 3:
                System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------");
                System.out.println();

                System.out.println(Colors.pink + "\t\t\t\t\t\t\t"+"Restaurant : La Barraca, Valencia" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Saffron Rice with Seafood and Chicken");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.9 / 5");
                handleFoodOrder("Paella", 500);
                break;
            case 4:
                System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------");
                System.out.println();

                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Don Julio, Argentina" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Grilled Tender Beef with Herbs");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.8 / 5");
                handleFoodOrder("Beef Steak", 600);
                break;
            case 5:
                System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------");
                System.out.println();

                System.out.println(Colors.pink + "\t\t\t\t\t\t\t"+"Restaurant : Norda, Norway" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Fresh Salmon Grilled with Lemon & Herbs");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.7 / 5");
                handleFoodOrder("Grilled Salmon", 550);
                break;
            case 6:
                 FoodSection();
                break;

            case 7:
                   System.out.println(Colors.green + "\n\t\t\t\t\t\t\tThank you for using our Explore Ease Application!");
                       System.out.println("\t\t\t\t\t\t\tWe hope to serve you again soon!" + Colors.reset);
                 System.exit(0);

            default:
                System.out.println("\t\t\t\t\t\t\t"+"Invalid option.");
        }
    }
}
public void showItemInternationalSnacks() {
    while (true) {
        System.out.println(Colors.pink +"\t\t\t\t\t\t"+ "\n\t\t\t\t\t\t\t--- International Snacks Menu ---"+ Colors.reset);
        System.out.println(Colors.blue +"\t\t\t\t\t\t\t"+"1. Nachos with Cheese (Mexico)    - 200 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"2. Spring Rolls (China)           - 180 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"3. Falafel (Middle East)          - 180 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"4. Cheese Quesadilla (Mexico)     - 220 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"5. Macarons (France)              - 150 Rs");
        System.out.println("\t\t\t\t\t\t\t"+"6. Back to Food Menu");
        System.out.println("\t\t\t\t\t\t\t"+"7. exit" + Colors.reset);


      System.out.println("\t\t\t\t\t\t"+"Enter your choice");



        int n = sc.nextInt();
        switch (n) {
            case 1:    
                 
                System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------");
                System.out.println();

                System.out.println(Colors.pink + "  \t\t\t\t\t\t\t"+" | Restaurant : Local Mexican Stall |" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"|  Taste      : Crispy Corn Chips with Melted Cheese | ");
                System.out.println("\t\t\t\t\t\t\t"+"| Reviews    : 4.7 / 5 |");
             handleFoodOrder("Nachos with Cheese", 200);
                break;
            case 2:
               System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+" --------Item Details-------- ");
                System.out.println();

                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : Din Tai Fung, China" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Crispy Rolls with Savory Veg/Meat Filling");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.8 / 5");
                handleFoodOrder("Spring Rolls", 180);
                break;
            case 3:
               System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------");
                System.out.println();

                System.out.println(Colors.pink + "\t\t\t\t\t\t\t"+"Restaurant : Al Hallab, Beirut" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Crispy Chickpea Balls with Tahini Sauce");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.7 / 5");
                handleFoodOrder("Falafel", 180);
                break;
            case 4:
               System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------");
                System.out.println();

                System.out.println(Colors.pink + "\t\t\t\t\t\t"+"Restaurant : Local Mexican Kitchen" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Tortilla with Melted Cheese Filling");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.6 / 5");
                handleFoodOrder("Cheese Quesadilla", 220);
                break;
            case 5:
                System.out.println(Colors.blue+bold+"\t\t\t\t\t\t\t"+"--------Item Details--------");
                System.out.println();

                System.out.println(Colors.pink +"\t\t\t\t\t\t\t"+ "Restaurant : LadurÃ©e, Paris" + Colors.reset);
                System.out.println("\t\t\t\t\t\t\t"+"Taste      : Sweet, Crispy, Colorful French Macarons");
                System.out.println("\t\t\t\t\t\t\t"+"Reviews    : 4.8 / 5");
                handleFoodOrder("Macarons", 150);
                break;
            case 6:
                 FoodSection();
                break;

          case 7:
               System.out.println(Colors.green + "\n\t\t\t\t\t\t\tThank you for using our Explore Ease Application!");
                       System.out.println("\t\t\t\t\t\t\tWe hope to serve you again soon!" + Colors.reset);
                 System.exit(0);

            default:
                System.out.println("\t\t\t\t\t\t\t"+"Invalid option.");
        }
    }
}

private void handleFoodOrder(String itemName, int price) {
    System.out.println(Colors.yellow + "\t\t\t\t\t\t\t"+"1. Buy now");
    System.out.println("\t\t\t\t\t\t\t"+"2. Add to cart");
    System.out.println("\t\t\t\t\t\t\t"+"3. Back to main menu" );
System.out.println("\t\t\t\t\t\t\t"+"4. exit" + Colors.reset);
    System.out.print("\t\t\t\t\t\t\tEnter your choice :");
    int choice = sc.nextInt();

    if (choice == 1) { 
        address();  
        System.out.println("\t\t\t\t\t\t"+"Enter the quantity:");
        int qty = sc.nextInt();
        if (qty <= 0) {
            System.out.println(Colors.red + "\t\t\t\t\t\t\t"+"Quantity must be positive." + Colors.reset);
            return;
        }
        int total = qty * price;
        amount_food = total;
        cart1 = itemName + " x " + qty + " = " + total;
        System.out.println(Colors.green + "\t\t\t\t\t\t\t"+"Order successful!" + Colors.reset);
        System.out.println("\t\t\t\t\t\t\t"+"Bill: " + cart1);
        System.out.println("\t\t\t\t\t\t\t"+"Total Amount: " + amount_food + " Rs");
        processPayment1(total);

        postPurchaseOptions();

    } else if (choice == 2) {   
        System.out.println("\t\t\t\t\t\t\t"+"Enter the quantity:");
        int qty = sc.nextInt();
        if (qty <= 0) {
            System.out.println(Colors.red + "\t\t\t\t\t\t\t\t"+"Quantity must be positive." + Colors.reset);
            return;
        }
        int total = qty * price;

        amount_food += total;
        cart1 += itemName + " x " + qty + " = " + total + "\n";

        System.out.println(Colors.green + "\n\t\t\t\t\t\t\t"+"Item added to cart!" + Colors.reset);
        System.out.println(Colors.blue + "\n\t\t\t\t\t\t\t"+"--- Cart Summary ---" + Colors.reset);
        System.out.println(Colors.pink+"\t\t\t\t\t\t\t==================================================================");
        System.out.println("\t\t\t\t\t\t\t"+cart1);
        System.out.println("\t\t\t\t\t\t\t"+"Total Amount: " + amount_food + " Rs");
        System.out.println(Colors.pink+"\t\t\t\t\t\t\t==================================================================");


        while (true) {
            System.out.println(Colors.yellow + "\t\t\t\t\t\t\t"+"Choose an option:");
            System.out.println("\t\t\t\t\t\t\t"+"1. Buy Now ");
            System.out.println("\t\t\t\t\t\t\t"+"2. Checkout");
            System.out.println("\t\t\t\t\t\t\t"+"3. Continue Shopping");
            System.out.println("\t\t\t\t\t\t\t"+"4. Exit" + Colors.reset);

            int nextChoice = sc.nextInt();

            switch (nextChoice) {
                case 1: 
                    address();
                    System.out.println("\t\t\t\t\t\t\t"+"You bought " + itemName + " for " + total + " Rs.");
                    processPayment1(total);
                    postPurchaseOptions();
                    return;

                case 2: 
                    if (amount_food == 0) {
                        System.out.println(Colors.red + "\t\t\t\t\t\t\t"+"Cart is empty." + Colors.reset);
                        return;
                    }

                    System.out.println(Colors.blue +"\t\t\t\t\t\t\t"+ "\n--- Cart Preview ---" + Colors.reset);
                    System.out.println(Colors.green +"\t\t\t\t\t\t\t"+ cart1 + Colors.reset);
                    System.out.println("\t\t\t\t\t\t\t"+"Total Amount: " + amount_food + " Rs");

                    System.out.println("\t\t\t\t\t\t\t"+"\nChoose an option:");
                    System.out.println(Colors.yellow+"\t\t\t\t\t\t\t"+"1. Buy Now ");
                    System.out.println("\t\t\t\t\t\t\t"+"2. Back to Home Menu"+Colors.reset);
                   System.out.println("\t\t\t\t\t\t\t"+"Enter your choice");

                    int checkoutChoice = sc.nextInt();

                    switch (checkoutChoice) {
                        case 1: 
                            address();
                            processPayment1(amount_food);
                            cart1 = "";
                            amount_food = 0;
                            postPurchaseOptions();
                            return;
                        case 2: 
                             FoodSection();
                             break;

                        default:
                            System.out.println(Colors.red +"\t\t\t\t\t\t\t"+ "Invalid choice. Try again." + Colors.reset);
                    }
                    break;
                   
                case 3:                     
                        FoodSection();
                        break;

                case 4: 
                         System.out.println(Colors.green+"\t\t\t\t\t\t\t"+ "Thank you for visiting! Have a great day!" + Colors.reset);
                    exitApp();
                    return;

                default:
                    System.out.println(Colors.red +"\t\t\t\t\t\t\t"+ "Invalid choice. Try again." + Colors.reset);
            }
        }

    } else if (choice == 3) {   
        FoodSection();
                

    }
 else if (choice == 4) { 
                        System.out.println(Colors.green+"\t\t\t\t\t\t\t"+ "Thank you for visiting! Have a great day!" + Colors.reset);  
        System.exit(0);
    } else {
        System.out.println(Colors.red + "\t\t\t\t\t\t\t"+"Invalid choice. Try again." + Colors.reset);
    }
}
private void postPurchaseOptions() {
    while (true) {
     
        System.out.println(Colors.yellow +"\t\t\t\t\t\t\t1. Continue Shopping");
        System.out.println("\t\t\t\t\t\t\t2.back to menu");
        System.out.println("\t\t\t\t\t\t\t3. Exit" + Colors.reset);
        System.out.print("\t\t\t\t\t\t\tEnter your choice: ");
        int opt = sc.nextInt();

        if (opt == 1) {
            return; 
        } else if (opt == 2) {
             FoodSection();
             break;
             
         }else if(opt==3){
                            System.out.println(Colors.green+"\t\t\t\t\t\t\t"+ "Thank you for visiting! Have a great day!" + Colors.reset);
             exitApp();
            return;
        } else {
            System.out.println(Colors.red + "\t\t\t\t\t\t\t"+"Invalid choice. Try again." + Colors.reset);
        }
    }
}
private static String houseNo, street, city, state, pincode,phoneNumber;


    public static void address() {
    
      sc.nextLine();
        System.out.println(Colors.pink +"\t\t\t\t\t\t\tEnter your House/Flat No: ");
    houseNo = sc.nextLine();

    System.out.println(Colors.blue +"\t\t\t\t\t\t\tEnter your Street Name: ");
    street = sc.nextLine();

    // Validate city
    while (true) {
        System.out.print(Colors.cyan +"\t\t\t\t\t\t\tEnter your City: ");
        city = sc.nextLine();
        if (city.matches("[a-zA-Z ]+")) break;
        System.out.println(Colors.red+"\t\t\t\t\t\t\tInvalid City! Please use only letters.");
    }

    // Validate state
    while (true) {
        System.out.print(Colors.yellow +"\t\t\t\t\t\t\tEnter your State: ");
        state = sc.nextLine();
        if (state.matches("[a-zA-Z ]+")) break;
        System.out.println(Colors.red+"\t\t\t\t\t\t\tInvalid State! Please use only letters.");
    }


    while (true) {
        System.out.print("\t\t\t\t\t\t\tEnter your 6-digit Pincode: ");
        pincode = sc.nextLine();
        if (pincode.matches("\\d{6}")) break;
        System.out.println(Colors.red+"\t\t\t\t\t\t\tInvalid Pincode! Please enter exactly 6 digits."+Colors.reset);
    }
     while (true) {
        System.out.print(Colors.pink + "\t\t\t\t\t\t\tEnter your 10-digit Phone Number: ");
        phoneNumber = sc.nextLine();
        if (phoneNumber.matches("[6-9]\\d{9}")) break;
        System.out.println(Colors.red + "\t\t\t\t\t\t\tInvalid Phone Number! It must be 10 digits and start with 6-9." + Colors.reset);
    }

    String fullAddress1 = houseNo + ", " + street + ", " + city + ", " + state + " - " + pincode;

    System.out.println(Colors.green+"\n\t\t\t\t\t\t\tYour Address has been saved successfully!"+Colors.reset);
    System.out.println("\t\t\t\t\t\t\t---------------------------------------------");
    System.out.println("\t\t\t\t\t\t\t"+fullAddress1 +"                       ");
    System.out.println(Colors.cyan+"\t\t\t\t\t\t\tPhone: " + phoneNumber + "                  ");
  
  
    System.out.println("\t\t\t\t\t\t\t---------------------------------------------");
}


private boolean processPayment1(double amount) {
        if (amount <= 0) {
            System.out.println(Colors.red +"\t\t\t\t\t\t\t"+ "Nothing to pay." + Colors.reset);
            return false;
        }
        Payments.amountToPay = amount;
        P paymentProcessor = new Payments();

        System.out.println(Colors.cyan + "\t\t\t\t\t\t"+"\n\t\t\t\t\t\t\t\t--- Select Payment Method ---" + Colors.reset);
        System.out.println(Colors.yellow +"\t\t\t\t\t\t\t"+ "1. Paytm");
        System.out.println("\t\t\t\t\t\t\t"+"2. PhonePe");
        System.out.println("\t\t\t\t\t\t\t"+"3. Google Pay");
        System.out.println("\t\t\t\t\t\t\t"+"4. Credit Card");
        System.out.println("\t\t\t\t\t\t\t"+"5. Bank Account Transfer");
        System.out.println("\t\t\t\t\t\t\t"+"6.back to menu");
        System.out.println("\t\t\t\t\t\t\t"+"7.exit"+ Colors.reset);
        System.out.print(Colors.blue +"\t\t\t\t\t\t\t"+ "Enter your choice: " + Colors.reset);

        boolean success = false;
        int choice = sc.nextInt();
        switch (choice) {
            case 1: success = paymentProcessor.paytm();

               String fullAddress2 = houseNo + ", " + street + ", " + city + ", " + state + " - " + pincode;

               System.out.println(Colors.green +"\n\t\t\t\t\t\t\tYour order delevered to this address !"+Colors.reset);
               System.out.println("\t\t\t\t\t\t\t---------------------------------------------");
               System.out.println(Colors.pink+"\t\t\t\t\t\t\t"+fullAddress2+Colors.reset);
               System.out.println("\t\t\t\t\t\t\t---------------------------------------------");

                               break;
            case 2: success = paymentProcessor.phonePay();
             String fullAddress3 = houseNo + ", " + street + ", " + city + ", " + state + " - " + pincode;

               System.out.println(Colors.green +"\n\t\t\t\t\t\t\tYour order delevered to this address !");
               System.out.println("\t\t\t\t\t\t\t---------------------------------------------");
               System.out.println(Colors.pink+"\t\t\t\t\t\t\t"+fullAddress3);
               System.out.println("\t\t\t\t\t\t\t---------------------------------------------");

                             break;
            case 3: success = paymentProcessor.googlePay(); 
                     String fullAddress4 = houseNo + ", " + street + ", " + city + ", " + state + " - " + pincode;

               System.out.println(Colors.green +"\n\t\t\t\t\t\t\tYour order delevered to this address !");
               System.out.println("\t\t\t\t\t\t\t---------------------------------------------");
               System.out.println(Colors.pink+"\t\t\t\t\t\t\t"+fullAddress4);
               System.out.println("\t\t\t\t\t\t\t\t---------------------------------------------");

                       break;
            case 4: success = paymentProcessor.creditCard();
 String fullAddress5 = houseNo + ", " + street + ", " + city + ", " + state + " - " + pincode;

               System.out.println(Colors.green +"\n\t\t\t\t\t\t\tYour order delevered to this address !");
               System.out.println("\t\t\t\t\t\t---------------------------------------------");
               System.out.println(Colors.pink+"\t\t\t\t\t\t\t"+fullAddress5);
               System.out.println("\t\t\t\t\t\t\t---------------------------------------------");

                  break;
            case 5: success = paymentProcessor.accountNumber(); 

String fullAddress6 = houseNo + ", " + street + ", " + city + ", " + state + " - " + pincode;

               System.out.println(Colors.green +"\n\t\t\t\t\t\t\tYour order delevered to this address !");
               System.out.println("\t\t\t\t\t\t\t---------------------------------------------");
               System.out.println(Colors.pink+"\t\t\t\t\t\t\t"+fullAddress6);
               System.out.println("\t\t\t\t\t\t\t---------------------------------------------");

              break;
          case 6:
              FoodSection();
              break;
         case 7: 
           System.out.println(Colors.green+"\t\t\t\t\t\t\tThank you for visiting....");
            System.exit(0);
            default: System.out.println(Colors.red +"\t\t\t\t\t\t\t"+ "Invalid payment option." + Colors.reset);
        }
        return success;
    }




//====================================================================================================== RENTAL VEHICLES ============================================================================================================================================

//vehicle booking

    static double totalAmount = 0;
    static String vehicleLog = "";

  

 static void vehicleDetails() {
        System.out.println(Colors.cyan + "\n\t\t\t\t\t\t====== Vehicle Selection Department ======" +  Colors.reset);
        System.out.println(Colors.yellow +"\t\t\t\t\t\t\t"+"1. Two Wheelers");
        System.out.println("\t\t\t\t\t\t\t"+"2. Four Wheelers");
        System.out.println("\t\t\t\t\t\t\t"+"3. Busses");
        System.out.println("\t\t\t\t\t\t\t"+"4. auto");
        System.out.println("\t\t\t\t\t\t\t5. back to main menu"+  Colors.reset);
        System.out.println(Colors.red +"\t\t\t\t\t\t\t"+"6. Exit"+  Colors.reset);
        System.out.print(Colors.blue +"\t\t\t\t\t\t\t"+"Enter your choice: "+  Colors.reset);
        int choice = sc.nextInt();

        switch (choice) {
            case 1: twoWheelers(); break;
            case 2: fourWheelers(); break;
            case 3: busses(); break;
            case 4: auto();break;
            case 5: return;
            case 6:
                System.out.println(Colors.green+"\t\t\t\t\t\t"+ "Thank you for visiting! Have a great day!" + Colors.reset);
                System.exit(0);
                break;
            default:
                System.out.println(Colors.red +"\t\t\t\t\t\t"+ "Invalid choice. Please try again." +  Colors.reset);
                vehicleDetails();
        }
    }


    static void twoWheelers() {
        System.out.println(Colors.cyan + "\n\t\t\t\t\t\t\t====== Two Wheelers ======" +  Colors.reset);
        System.out.println(Colors.yellow +"\t\t\t\t\t\t"+"1. Bike");
        System.out.println("\t\t\t\t\t\t"+"2. Scooty");
        System.out.println("\t\t\t\t\t\t"+"3. Back"+  Colors.reset);
        System.out.print(Colors.blue +"\t\t\t\t\t\t"+"Enter your choice: "+  Colors.reset);
        int choice = sc.nextInt();

        switch (choice) {
            case 1: bike(); break;
            case 2: scooty(); break;
            case 3: vehicleDetails(); break;
            default:
                System.out.println(Colors.red + "Invalid choice." +  Colors.reset);
                twoWheelers();
        }
    }
  static void auto() {
    System.out.println(Colors.cyan + "\n\t\t\t\t\t====== Auto Models ======" + Colors.reset);
    System.out.println(Colors.yellow +"\t\t\t\t\t1. Bajaj RE (Rs 250/day)");
    System.out.println("\t\t\t\t\t2. Piaggio Ape Auto (Rs 270/day)");
    System.out.println("\t\t\t\t\t3. Mahindra Alfa (Rs 300/day)");
    System.out.println("\t\t\t\t\t4. TVS King (Rs 280/day)");
    System.out.println("\t\t\t\t\t5. Electric Auto (Rs 320/day)");
    System.out.println("\t\t\t\t\t6. Back"+ Colors.reset);
    System.out.print(Colors.blue +"\t\t\t\t\tEnter your choice: "+ Colors.reset);
    int ch = sc.nextInt();

    switch (ch) {
        case 1: processPayment(250, "Auto | Bajaj RE |"); break;
        case 2: processPayment(270, "Auto | Piaggio Ape Auto |"); break;
        case 3: processPayment(300, "Auto | Mahindra Alfa |"); break;
        case 4: processPayment(280, "Auto | TVS King |"); break;
        case 5: processPayment(320, "Auto | Electric Auto |"); break;
        case 6: twoWheelers(); break;
        default:
            System.out.println(Colors.red + "\t\t\t\t\tInvalid choice." + Colors.reset);
            auto();
    }
}





    static void bike() {
        System.out.println(Colors.cyan + "\n\t\t\t\t\t\t\t====== Bike Models ======" +  Colors.reset);
        System.out.println(Colors.yellow +"\t\t\t\t\t\t"+"1. Hero Honda (Rs 300/day)");
        System.out.println("\t\t\t\t\t\t"+"2. Royal Enfield (Rs 600/day)");
        System.out.println("\t\t\t\t\t\t"+"3. KTM Duke (Rs 550/day)");
        System.out.println("\t\t\t\t\t\t"+"4. Yamaha RX100 (Rs 300/day)");
        System.out.println("\t\t\t\t\t\t"+"5. Back"+ Colors.reset);
        System.out.print(Colors.blue +"\t\t\t\t\t\t"+"Enter your choice: "+ Colors.reset);
        int ch = sc.nextInt();
        switch (ch) {
            case 1: processPayment(300, "Bike | Hero Honda |"); break;
            case 2: processPayment(600, "Bike | Royal Enfield |"); break;
            case 3: processPayment(550, "Bike | KTM Duke |"); break;
            case 4: processPayment(300, "Bike | Yamaha RX100 |"); break;
            case 5: twoWheelers(); break;
            default:
                System.out.println(Colors.red+"\t\t\t\t\t\t"+ "Invalid choice." + Colors.reset);
                bike();
        }
    }

    static void scooty() {
        System.out.println(Colors.cyan + "\n\t\t\t\t\t====== Scooty Models ======" +  Colors.reset);
        System.out.println(Colors.yellow +"\t\t\t\t\t\t"+"1. Honda Activa (Rs 300/day)");
        System.out.println("\t\t\t\t\t\t"+"2. Suzuki Access (Rs 310/day)");
        System.out.println("\t\t\t\t\t\t"+"3. Vespa LX 125 (Rs 400/day)");
        System.out.println("\t\t\t\t\t\t"+"4. Ola S1 Pro (Rs 350/day)");
        System.out.println("\t\t\t\t\t\t"+"5. Ather 450X (Rs 370/day)");
        System.out.println("\t\t\t\t\t\t"+"6. Back"+ Colors.reset);
        System.out.print("\t\t\t\t\t\t"+Colors.blue +"Enter your choice: "+ Colors.reset);
        int ch = sc.nextInt();
        switch (ch) {
            case 1: processPayment(300, "Scooty | Honda Activa |"); break;
            case 2: processPayment(310, "Scooty | Suzuki Access |"); break;
            case 3: processPayment(400, "Scooty | Vespa LX 125 |"); break;
            case 4: processPayment(350, "Scooty | Ola S1 Pro |"); break;
            case 5: processPayment(370, "Scooty | Ather 450X |"); break;
            case 6: twoWheelers(); break;
            default:
                System.out.println(Colors.red+"\t\t\t\t\t\t"+ "Invalid choice." +  Colors.reset);
                scooty();
        }
    }


    static void fourWheelers() {
        System.out.println(Colors.cyan + "\n\t\t\t\t\t\t\t====== Four Wheelers ======" +  Colors.reset);
        System.out.println(Colors.yellow +"\t\t\t\t\t\t"+"1. 5-Seater Cars");
        System.out.println("\t\t\t\t\t\t"+"2. 7-Seater Cars");
        System.out.println("\t\t\t\t\t\t"+"3. Back" +  Colors.reset);
        System.out.print(Colors.yellow +"\t\t\t\t\t\t"+"Enter your choice: " +  Colors.reset);
        int choice = sc.nextInt();

        switch (choice) {
            case 1: seaterFive(); break;
            case 2: seaterSeven(); break;
            case 3: vehicleDetails(); break;
            default:
                System.out.println(Colors.red + "\t\t\t\t\t\t"+"Invalid choice." +  Colors.reset);
                fourWheelers();
        }
    }

    static void seaterFive() {
        System.out.println(Colors.cyan + "\n\t\t\t\t\t\t\t====== 5-Seater Cars ======" +  Colors.reset);
        System.out.println(Colors.yellow +"\t\t\t\t\t\t"+"1. Maruti Swift (Rs 800/day)");
        System.out.println("\t\t\t\t\t\t"+"2. Hyundai i20 (Rs 850/day)");
        System.out.println("\t\t\t\t\t\t"+"3. Tata Nexon (Rs 900/day)");
        System.out.println("\t\t\t\t\t\t"+"4. Back" +  Colors.reset);
        System.out.print(Colors.yellow +"Enter your choice: " +  Colors.reset);
        int ch = sc.nextInt();
        switch (ch) {
            case 1: processPayment(800, "Car | Maruti Swift |"); break;
            case 2: processPayment(850, "Car | Hyundai i20 |"); break;
            case 3: processPayment(900, "Car | Tata Nexon |"); break;
            case 4: fourWheelers(); break;
            default:
                System.out.println(Colors.red + "\t\t\t\t\t\t"+"Invalid choice." +  Colors.reset);
                seaterFive();
        }
    }

    static void seaterSeven() {
        System.out.println(Colors.cyan + "\n\t\t\t\t\t\t\t====== 7-Seater Cars ======" +  Colors.reset);
        System.out.println(Colors.yellow +"\t\t\t\t\t\t"+"1. Toyota Innova (Rs 1300/day)");
        System.out.println("\t\t\t\t\t\t"+"2. Mahindra XUV500 (Rs 1250/day)");
        System.out.println("\t\t\t\t\t\t"+"3. Renault Triber (Rs 1000/day)");
        System.out.println("\t\t\t\t\t\t"+"4. Back" +  Colors.reset);
        System.out.print(Colors.blue +"\t\t\t\t\t\t"+"Enter your choice: " +  Colors.reset);
        int ch = sc.nextInt();
        switch (ch) {
            case 1: processPayment(1300, "Car | Toyota Innova |"); break;
            case 2: processPayment(1250, "Car | Mahindra XUV500 |"); break;
            case 3: processPayment(1000, "Car | Renault Triber |"); break;
            case 4: fourWheelers(); break;
            default:
                System.out.println(Colors.red + "\t\t\t\t\t\t"+"Invalid choice." +  Colors.reset);
                seaterSeven();
        }
    }


    static void busses() {
        System.out.println(Colors.cyan + "\n\t\t\t\t\t\t====== Busses ======" +  Colors.reset);
        System.out.println(Colors.yellow +"\t\t\t\t\t\t"+"1. Mini Van (Rs 1500/day)");
        System.out.println("\t\t\t\t\t\t"+"2. Bus (Rs 3500/day)");
        System.out.println("\t\t\t\t\t\t"+"3. Back" +  Colors.reset);
        System.out.print(Colors.blue+"\t\t\t\t\t\t"+"Enter your choice: " +  Colors.reset);
        int choice = sc.nextInt();

        switch (choice) {
            case 1: processPayment(1500, "Mini Van | Force Traveller |"); break;
            case 2: processPayment(3500, "Bus | Tata Motors Starbus Ultra |"); break;
            case 3: vehicleDetails(); break;
            default:
                System.out.println(Colors.red+"\t\t\t\t\t\t"+ "Invalid choice." +  Colors.reset);
                busses();
        }
    }

  
    static void processPayment(double amount, String vehicleName) {
        System.out.print(Colors.pink+"\n\t\t\t\t\t\t\tEnter number of days to rent: "+  Colors.reset);
        int days = sc.nextInt();
        double total = amount * days;
        totalAmount += total;
        vehicleLog += vehicleName + " " + days + " days = Rs " + total + "\n";
        System.out.println(Colors.cyan + "\t\t\t\t\t\t"+"---Your Vehicle Deatils------"+  Colors.reset);
        System.out.println("\t\t\t\t\t\t"+Colors.green+ "Vehicle Name :"+vehicleName +"No of Days:" +days+""+ Colors.reset);
        showOptions();
    }


    static void showOptions() {
        System.out.println(Colors.pink + "\n\t\t\t\t\t\t\t\t--- Options ---" +  Colors.reset);
        System.out.println(Colors.yellow +"\t\t\t\t\t\t"+"1. Continue Vehicle Booking");
        System.out.println("\t\t\t\t\t\t"+"2. Proceed to Payment"+  Colors.reset);
        System.out.println(Colors.red +"\t\t\t\t\t\t"+"3. Exit"+  Colors.reset);
        System.out.print(Colors.pink +"\t\t\t\t\t\t"+"Enter your choice: "+  Colors.reset);
        int ch = sc.nextInt();
        switch (ch) {
            case 1: vehicleDetails(); break;
            case 2: paymentDetails(); break;
            case 3:
                System.out.println(Colors.green +"\t\t\t\t\t\t"+ "Thank you for using Vehicle Rental System!" + Colors.reset);
                System.exit(0);
            default:
                System.out.println(Colors.red +"\t\t\t\t\t\t"+ "Invalid choice." +  Colors.reset);
                showOptions();
        }
    }


    static void paymentDetails() {
        System.out.println(Colors.cyan + "\n\t\t\t\t\t\t====== Payment Section ======" +  Colors.reset);
        System.out.println(Colors.green +"\t\t\t\t\t\t"+"Total Bill: Rs " + totalAmount+  Colors.reset);
        System.out.println(Colors.blue +"\t\t\t\t\t\t"+"Choose Payment Mode:"+  Colors.reset);
        System.out.println(Colors.yellow +"\t\t\t\t\t\t"+"1. Paytm");
        System.out.println("\t\t\t\t\t\t"+"2. PhonePe");
        System.out.println("\t\t\t\t\t\t"+"3. Google Pay");
        System.out.println("\t\t\t\t\t\t"+"4. Credit Card");
        System.out.println("\t\t\t\t\t\t"+"5. Bank Account Transfer"+Colors.reset);
        System.out.print(Colors.blue + "\t\t\t\t\t\t\t"+"Enter your choice: "+Colors.reset);
        int choice = sc.nextInt();

        int otp = 1000 + (int)(Math.random() * 9000);
        System.out.println(Colors.pink + "\t\t\t\t\t\t"+"Enter OTP sent to your device: " + otp+Colors.reset);
        System.out.print(Colors.blue + "\t\t\t\t\t\t"+"Re-enter OTP: "+Colors.reset);
        int enteredOtp = sc.nextInt();

        if (enteredOtp == otp) {
            System.out.println(Colors.green + "\t\t\t\t\t\t"+"Payment Successful!" +  Colors.reset);
            System.out.println(Colors.cyan +"\n\t\t\t\t\t\t\t===== Booking Summary ====="+  Colors.reset);
            System.out.print("\n\t\t\t\t\t\t\t");
            System.out.println(vehicleLog);

            System.out.println(Colors.yellow +"\t\t\t\t\t\t"+"Total Amount Paid: Rs " + totalAmount+  Colors.reset);
            System.out.println(Colors.green +"\t\t\t\t\t\t"+ "Thank you for your booking!" +  Colors.reset);
             totalAmount = 0;
             vehicleLog = "";


         vehicleDetails();
        } else {
            System.out.println(Colors.red + "\t\t\t\t\t\t"+"Incorrect OTP. Try again." + Colors.reset);
            paymentDetails();
        }
    }




}

//======================================================================================================================== AUTHENTICATION ===================================================================================================================


class InvalidLoginException extends RuntimeException {
    public InvalidLoginException(String message) {
        super(message);
    }
}


class Gmail {
    private String username;
    private String password;
    private String email;
    private String mobile;

    public Gmail(String username, String password, String email, String mobile) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getMobile() { return mobile; }

    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    public void setMobile(String mobile) { this.mobile = mobile; }
}


 class ExploreEase2 {

    private static Scanner sc = new Scanner(System.in);
    private static Gmail account;

    public static void main(String[] args) {
        Welcome();                   
        mainMenu();            
        User user = new User();      
        user.showMainMenu();
    }

    // Welcome  method
    static void Welcome() {
        String[] banner = {
            Colors.magenta + "\t\t\t\t" + "@@       @@  @@@@@@@@  @@        @@@@@@@@  @@@@@@@@  @@@     @@@  @@@@@@@@ ",
            Colors.magenta + "\t\t\t\t" + "@@       @@  @@        @@        @@        @@    @@  @@ @@ @@ @@  @@       ",
            Colors.magenta + "\t\t\t\t" + "@@   @   @@  @@@@@@    @@        @@        @@    @@  @@  @@@  @@  @@@@@@   ",
            Colors.magenta + "\t\t\t\t" + "@@  @@@  @@  @@        @@        @@        @@    @@  @@   @   @@  @@       ",
            Colors.magenta + "\t\t\t\t" + "@@ @@ @@ @@  @@        @@        @@        @@    @@  @@       @@  @@       ",
            Colors.magenta + "\t\t\t\t" + "@@@     @@@  @@@@@@@@  @@@@@@@@  @@@@@@@@  @@@@@@@@  @@       @@  @@@@@@@@ ",
             "",
            Colors.magenta + "\t\t\t\t" + "                                       -**#*+.                           ",
            Colors.magenta + "\t\t\t\t" + "                                      .-****#**.               -****-     ",
            Colors.magenta + "\t\t\t\t" + "                             ^         :*********-   +-*************#*.   ",
            Colors.magenta + "\t\t\t\t" + "!!!!!!!!!!!!  !!!!!!!!!    +**-.         +#-**-*********************-    ",
            Colors.magenta + "\t\t\t\t" + "    !!!       !!     !!   ****-        :-**********************--:        ",
            Colors.magenta + "\t\t\t\t" + "    !!!       !!     !!   -******#***********************:                ",
            Colors.magenta + "\t\t\t\t" + "    !!!       !!     !!   -***************************.                   ",
            Colors.magenta + "\t\t\t\t" + "    !!!       !!     !!    **************-:  -*******                     ",
            Colors.magenta + "\t\t\t\t" + "    !!!       !!!!!!!!!      :::::.          ******-                      ",
            Colors.magenta + "\t\t\t\t" + "                                            ******-                        ",
            Colors.magenta + "\t\t\t\t" + "                                           +*****:                         ",
            Colors.magenta + "\t\t\t\t" + "                                          *****                            ",
            Colors.magenta + "\t\t\t\t" + "                                         -***-                             ",
            Colors.magenta + "\t\t\t\t" + "                                        #-:                                ",
            "",
            Colors.magenta + "\t\t" + "#######   #      #   #######  #       ####### ####### #######     #######       #       ####### ####### ",
            Colors.magenta + "\t\t" + "#          #    #    #     #  #       #     # #     # #           #            # #      #       #       ",
            Colors.magenta + "\t\t" + "#           #  #     #     #  #       #     # #     # #           #           #   #     #       #       ",
            Colors.magenta + "\t\t" + "######      ###      #######  #       #     # ####### ###### ~*~  #######    #######    ####### ####### ",
            Colors.magenta + "\t\t" + "#          #   #     #        #       #     # # #     #           #         #       #         # #       ",
            Colors.magenta + "\t\t" + "#         #     #    #        #       #     # #   #   #           #        #         #        # #       ",
            Colors.magenta + "\t\t" + "#######  #       #   #        ####### ####### #     # #######     ####### #           # ####### ####### "

        };

        for (String line : banner) {
            for (char ch : line.toCharArray()) {
                System.out.print(ch);
                try {
                    Thread.sleep(5); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(Colors.reset);
        }
        System.out.println();
    }


   // ===== CENTERED MAIN MENU =====
    private static void mainMenu() {
        while (true) {
            printCentered(Colors.green + "\t\t\t\t\t\t\t============================" + Colors.reset);
            printCentered(Colors.yellow + "\t\t\t\t\t\t\tExploreEase         " + Colors.reset);
            printCentered(Colors.green + "\t\t\t\t\t\t\t============================" + Colors.reset);
            printCentered(Colors.cyan + "\t\t\t\t\t\t\t1. Sign Up" + Colors.reset);
            printCentered(Colors.cyan + "\t\t\t\t\t\t\t2. Login" + Colors.reset);
            printCentered(Colors.cyan + "\t\t\t\t\t\t\t3. Exit" + Colors.reset);
            printCentered(Colors.yellow + "\t\t\t\t\t\t\tChoose an option: " + Colors.reset);

            int choice = getChoice();

            switch (choice) {
                case 1:
                    signup();  // After signup, login will be called directly
                    return;    // exit menu loop
                case 2:
                    if (account == null) {
                        printCentered(Colors.red + "\t\t\t\t\t\t\tYou need to Sign Up first!" + Colors.reset);
                    } else {
                        login();
                        return;  // program ends after successful login
                    }
                    break;
                case 3:
                    printCentered(Colors.green + "\t\t\t\t\t\t\tThank you for using ExploreEase! Goodbye!" + Colors.reset);
                    System.exit(0);
                default:
                    printCentered(Colors.red + "\t\t\t\t\t\t\tInvalid option! Try again." + Colors.reset);
            }
        }
    }

    // ===== SIGN UP =====
    private static void signup() {
        printCentered(Colors.cyan + "\n\t\t\t\t\t\t\t\t\t=== Sign Up ===" + Colors.reset);
        System.out.print(Colors.yellow + "\t\t\t\t\t\t\tEnter Username: "+ Colors.reset);
        String username;
        while (true) {
            username = sc.nextLine().trim();
            if (username.isEmpty()) printCentered(Colors.red + "\t\t\t\t\t\t\tUsername cannot be empty!" + Colors.reset);
            else if (isNumeric(username)) printCentered(Colors.red + "\t\t\t\t\t\t\tYou are entering numeric value! Please enter string value." + Colors.reset);
            else break;
        }

        String mobile = manualMobile();
        if (mobile == null) return;

        String email = manualEmail();
        if (email == null) return;

        String password = manualPassword();
        if (password == null) return;

        account = new Gmail(username, password, email, mobile);
        printCentered(Colors.green + "\n\t\t\t\t\t\t\tSign-up successful! Now proceed to login.\n" + Colors.reset);

        login(); // Directly call login after signup
    }

    // ===== LOGIN =====
    private static void login() {
        boolean loggedIn = false;
        while (!loggedIn) {
            printCentered(Colors.cyan + "\n\t\t\t\t\t\t\t\t=== Login ===" + Colors.reset);
            System.out.print(Colors.yellow +"\t\t\t\t\t\t\tEnter Username or Email: "+ Colors.reset);
            String id = sc.nextLine();
            System.out.print(Colors.yellow +"\t\t\t\t\t\t\tEnter Password: "+ Colors.reset);
            String pass = sc.nextLine();
            try {
                if ((!id.equals(account.getUsername()) && !id.equals(account.getEmail())) 
                      ||  !pass.equals(account.getPassword()))
                    throw new InvalidLoginException("\t\t\t\t\t\t\tInvalid login! ID or password is incorrect.");
                printCentered(Colors.green + "\t\t\t\t\t\tLogin successful! Welcome " + account.getUsername() + Colors.reset);
                
                loggedIn = true;
                  return;
            } catch (InvalidLoginException e) {
                printCentered(Colors.red + e.getMessage() + Colors.reset);
                printCentered(Colors.blue +"\t\t\t\t\t\tChoose one option:"+ Colors.reset);
                printCentered(Colors.yellow +"\t\t\t\t\t\t1. Try login again"+ Colors.reset);
                printCentered(Colors.yellow +"\t\t\t\t\t\t\t2. Update account details"+ Colors.reset);
                printCentered(Colors.yellow +"\t\t\t\t\t\t3. Exit"+ Colors.reset);
                int choice = getChoice();
                switch (choice) {
                    case 1:
                        break;
                    case 2:
                        updateAccount();
                        break;
                    case 3:
                        printCentered(Colors.red + "\t\t\t\t\t\t\tExiting program. Goodbye!" + Colors.reset);
                        System.exit(0);
                    default:
                        printCentered(Colors.red + "\t\t\t\t\t\t\tInvalid option. Try again." + Colors.reset);
                }
            }
        }
        // End program after successful login
        printCentered(Colors.green + "\n\t\t\t\t\t\t\tThank you for using ExploreEase! Exiting program.\n" + Colors.reset);
        System.exit(0);
    }

    // ===== UPDATE ACCOUNT =====
    private static void updateAccount() {
        printCentered(Colors.cyan + "\n\t\t\t\t\t\t\t=== Update Account ===" + Colors.reset);

        // Username
        String newUsername;
        while (true) {
            System.out.print(Colors.yellow +"\t\t\t\t\t\t\tEnter new Username: "+ Colors.reset);
            newUsername = sc.nextLine().trim();
            if (newUsername.isEmpty()) printCentered(Colors.red + "\t\t\t\t\t\t\tUsername cannot be empty!" + Colors.reset);
            else if (isNumeric(newUsername)) printCentered(Colors.red + "\t\t\t\t\t\t\tYou are entering numeric value! Please enter string value." + Colors.reset);
            else break;
        }
        account.setUsername(newUsername);

        // Password
        String newPassword = manualPassword();
        account.setPassword(newPassword);

        // Email
        String newEmail = manualEmail();
        account.setEmail(newEmail);

        // Mobile
        String newMobile = manualMobile();
        account.setMobile(newMobile);

        printCentered(Colors.green + "\t\t\t\t\t\t\tAccount details updated successfully!\n" + Colors.reset);
    }

    // ===== MANUAL MOBILE VALIDATION =====
    private static String manualMobile() {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print(Colors.yellow +"\t\t\t\t\t\t\tEnter Mobile (10 digits, starts with 6/7/8/9): "+ Colors.reset);
            String mobile = sc.nextLine();
            if (mobile.length() == 10 && (mobile.charAt(0) >= '6' && mobile.charAt(0) <= '9')) {
                boolean valid = true;
                for (char c : mobile.toCharArray()) if (!Character.isDigit(c)) valid = false;
                if (valid) return mobile;
            }
            attempts++;
            printCentered(Colors.red + "\t\t\t\t\t\t\tInvalid Mobile! Attempts left: " + (3 - attempts) + Colors.reset);
        }
        printCentered(Colors.red + "\t\t\t\t\t\t\tMobile attempts exceeded!" + Colors.reset);
        mainMenu(); 
        return null;
    }

    // ===== MANUAL EMAIL VALIDATION =====
    private static String manualEmail() {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print(Colors.yellow +"\t\t\t\t\t\t\tEnter Email: " + Colors.reset);
            String email = sc.nextLine();
            int atIndex = email.indexOf('@');
            int dotIndex = email.indexOf('.', atIndex);
            if (atIndex > 0 && dotIndex > atIndex + 1 && dotIndex < email.length() - 1)
                return email;
            attempts++;
            printCentered(Colors.red + "\t\t\t\t\t\t\tInvalid Email! Attempts left: " + (3 - attempts) + Colors.reset);
        }
        printCentered(Colors.red + "\t\t\t\t\t\t\tEmail attempts exceeded!" + Colors.reset);
        mainMenu(); 
        return null;
    }

    // ===== MANUAL PASSWORD VALIDATION =====
    private static String manualPassword() {
        int attempts = 0;
        while (attempts < 3) {
            System.out.print(Colors.yellow +"\t\t\t\t\t\t\tEnter Password (8+ chars, 1 upper, 1 lower, 1 digit, 1 special): "+ Colors.reset);
            String password = sc.nextLine();
            if (isValidPassword(password)) return password;
            attempts++;
            printCentered(Colors.red + "\t\t\t\t\t\t\tInvalid Password! Attempts left: " + (3 - attempts) + Colors.reset);
        }
        printCentered(Colors.red + "\t\t\t\t\t\t\tPassword attempts exceeded!" + Colors.reset);
        mainMenu(); 
        return null;
    }

    private static boolean isValidPassword(String password) {
        if (password.length() < 8) return false;
        boolean upper = false, lower = false, digit = false, special = false;
        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) upper = true;
            else if (Character.isLowerCase(c)) lower = true;
            else if (Character.isDigit(c)) digit = true;
            else if ("!@#$%^&*()_+-=[]{}|;:'\",.<>?/`~".indexOf(c) >= 0) special = true;
        }
        return upper && lower && digit && special;
    }

    // ===== NUMERIC CHOICE INPUT =====
    private static int getChoice() {
        while (true) {
            String input = sc.nextLine().trim();
            if (input.isEmpty()) continue;
            if (isNumeric(input)) return Integer.parseInt(input);
            else printCentered(Colors.red + "\t\t\t\t\t\t\tYou are entering a string! Please enter numeric value." + Colors.reset);
        }
    }

    // ===== HELPER =====
    private static boolean isNumeric(String str) {
        for (char c : str.toCharArray()) if (!Character.isDigit(c)) return false;
        return true;
    }

    // ===== CENTERED PRINT =====
    private static void printCentered(String text) {
        int width = 60; // width of "console" line
        int padding = (width - text.replaceAll("\u001B\\[[;\\d]*m", "").length()) / 2;
        if (padding < 0) padding = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < padding; i++) sb.append(" ");
        sb.append(text);
        System.out.println(sb.toString());
    


        }
    
}








