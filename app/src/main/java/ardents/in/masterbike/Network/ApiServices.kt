package ardents.`in`.masterbike.Network

import ardents.`in`.masterbike.Model.BannerModel
import ardents.`in`.masterbike.Model.BookingData
import ardents.`in`.masterbike.Model.SignUpModel
import ardents.`in`.masterbike.Model.BrandModel
import ardents.`in`.masterbike.Model.CompanyModel
import ardents.`in`.masterbike.Model.CustomServicesModel
import ardents.`in`.masterbike.Model.Models
import ardents.`in`.masterbike.Model.PackagingModel
import ardents.`in`.masterbike.Model.TimeSlotModel
import ardents.`in`.masterbike.Model.CreateBookingData
import ardents.`in`.masterbike.Model.CreateBookingRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiServices {
    @FormUrlEncoded
    @POST("UserSignup")
    suspend fun singnUp(
        @Field("Login_Email") Login_Email:String,@Field("Login_Password") Login_Password:String,@Field("Login_Mobile") Login_Mobile:String,
        @Field("Login_Name") Login_Name:String
    ):Response<SignUpModel>

    @FormUrlEncoded
    @POST("Login")
    suspend fun login(
        @Field("Login_Email") Login_Email:String,@Field("Login_Password") Login_Password:String,@Field("Login_Mobile ") Login_Mobile:String
    ):Response<SignUpModel>

    @GET("AppBanner/0")
    suspend fun banner():Response<List<BannerModel>>

    @GET("Brand")
    suspend fun brand():Response<List<BrandModel>>

    @GET("Model/{Brand_Id}")
    suspend fun model(
        @Path("Brand_Id") Brand_Id:String
    ):Response<List<Models>>


    @GET("CustomService/{number}")
    suspend fun customService(
        @Path("number") number:String
    ):Response<List<CustomServicesModel>>

    @GET("TimeSlot")
    suspend fun timeSlot():Response<List<TimeSlotModel>>

    @GET("Package2Model/{Model_Id}")
    suspend fun periodicPackaging(
        @Path("Model_Id") model_id:String
    ):Response<List<PackagingModel>>

    @GET("PackageModel/{Model_Id}")
    suspend fun packageBooking(
        @Path("Model_Id") model_id:String
    ):Response<List<PackagingModel>>

    @GET("Company")
    suspend fun company():Response<List<CompanyModel>>

    @FormUrlEncoded
    @POST("Booking")
    suspend fun showBooking(
        @Field("Login_Email") Login_Email:String,@Field("Login_Id") Login_Id:String
    ):Response<List<BookingData>>


    @POST("CreateBooking")
    suspend fun createBooking(
        @Body body: CreateBookingRequest
    ):Response<CreateBookingData>

}