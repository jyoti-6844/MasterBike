package ardents.`in`.masterbike.Model

data class CreateBookingRequest(
    val Billed_Amount: String,
    val Booking_AddedBy: String,
    val Booking_Address: String,
    val Booking_Date: String,
    val Booking_Description: String,
    val Booking_Email: String,
    val Booking_Landmark: String,
    val Booking_Lat: String,
    val Booking_LoginId: String,
    val Booking_Long: String,
    val Booking_Mobile: String,
    val Booking_Name: String,
    val Booking_PIN: String,
    val Booking_VehicleNo: String,
    val Brand_Name: String,
    val Login_Id: String,
    val Model_Name: String,
    val PrefredTimeSlot_SlotName: String,
)