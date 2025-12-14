package pageobjects.commons;

import com.microsoft.playwright.Playwright;
import pageobjects.hotelbookerui.RetrieveBookingPageObjects;

import java.util.Random;
import java.util.logging.Logger;


@SuppressWarnings("unused")
public class TestDataGenerator {
    private static final Logger logger = Logger.getLogger(TestDataGenerator.class.getName());
    public static String FirstName;
    public static String LastName;
    public static String PhoneNumber;
    public static String Email;
    public static String BookingReferenceNumber;
    public static String Booking_Pnr;
    public static String NoOfGuests;
    public static String NoOfRooms;

     public TestDataGenerator() {
        generateRandomTestData();
    }


    private  void generateRandomTestData() {
        FirstName = generateRandomFirstName();
        LastName = generateRandomLastName();
        PhoneNumber = generateRandomPhoneNumber();
        Email = generateRandomEmail();
    }

    public  String getRandomName() {
        return FirstName;
    }

    public  String getRandomLastName() {
        return LastName;
    }

    public  String getRandomPhoneNumber() {
        return PhoneNumber;
    }

    public  String getRandomEmail() {
        return Email;
    }


    private String generateRandomFirstName() {
        return generateRandomAlphabeticString(16);
    }

    private String generateRandomLastName() {
        return generateRandomAlphabeticString(16);
    }

    private String generateRandomAlphabeticString(int length) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(rand.nextInt(alphabet.length())));
        }
        return sb.toString();
    }

    private  String generateRandomPhoneNumber() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    private  String generateRandomEmail() {
        return getRandomName() + "@sabre.com";
    }

    public static void setBookingReferenceNumber(String bookingReferenceNumber) {
         BookingReferenceNumber = bookingReferenceNumber;
    }
    public static String getBookingPNR() {
        return Booking_Pnr;
    }
    public static void setBookingPNR(String bookingPNR) {
        Booking_Pnr = bookingPNR;
    }
    public static String getBookingReferenceNumber() {
        return BookingReferenceNumber;
    }
    public static String setNoOfGuests(String noOfGuests) {
        NoOfGuests = noOfGuests;
        return NoOfGuests;
    }
    public static String getNoOfGuests() {
        return NoOfGuests;
    }
    public static String getNoOfRooms() {

        return NoOfRooms;
    }
    public static String setNoOfRooms(String NoOfRoom) {
        NoOfRooms = NoOfRoom;
         return NoOfRooms;
    }
}
