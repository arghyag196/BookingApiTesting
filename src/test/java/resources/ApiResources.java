package resources;

public enum ApiResources {

    createBooking("/booking"),
    createToken("/auth"),
    updateBooking("/booking/{bookingId}"),
    partialUpdateBooking("/booking/{bookingId}"),
    deleteBooking("/booking/{bookingId}");

    public final String resource;

    ApiResources(String resource){
        this.resource = resource;
    }

    public String getResource(){
        return resource;
    }




}
