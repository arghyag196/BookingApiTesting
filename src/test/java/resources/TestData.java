package resources;

import pojo.BookingDates;
import pojo.CreateToken;
import pojo.CreateUpdateBooking;

import java.time.LocalDate;

public class TestData {

    public static CreateUpdateBooking createUpdateBookingPayload(String fName, String lName, String additionalneeds){

        LocalDate checkin = LocalDate.now();
        LocalDate checkout = LocalDate.now().plusDays(10);
        CreateUpdateBooking createUpdateBooking = new CreateUpdateBooking();
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin(checkin.toString());
        bookingDates.setCheckout(checkout.toString());
        createUpdateBooking.setFirstname(fName);
        createUpdateBooking.setLastname(lName);
        createUpdateBooking.setDepositpaid(true);
        createUpdateBooking.setTotalprice(42000);
        createUpdateBooking.setAdditionalneeds(additionalneeds);
        createUpdateBooking.setBookingdates(bookingDates);

        return createUpdateBooking;

    }

    public static CreateToken createTokenPayload(String username, String password){
        CreateToken createToken = new CreateToken();
        createToken.setUsername(username);
        createToken.setPassword(password);
        return createToken;
    }




}
