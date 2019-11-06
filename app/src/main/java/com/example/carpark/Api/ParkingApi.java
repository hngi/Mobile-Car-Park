package com.example.carpark.Api;

import com.example.carpark.Api.Responses.BaseDataResponse;
import com.example.carpark.Api.Responses.BaseResponse;
import com.example.carpark.Api.Responses.LoginReg.UserResponse;
import com.example.carpark.Api.Responses.Park.ActiveAndInactiveParkingSpaceAllResponse;
import com.example.carpark.Api.Responses.Park.PageParkingSpaceAllResponse;
import com.example.carpark.Api.Responses.Park.ParkingSpaceAllResponse;
import com.example.carpark.Api.Responses.Park.SingleParkingSpaceResponse;
import com.example.carpark.Api.Responses.VehicleList;
import com.example.carpark.Model.NewUser;
import com.example.carpark.Model.Park.NewParkingSpace;
import com.example.carpark.Model.PhoneOtp;
import com.example.carpark.Model.User;
import com.example.carpark.Model.UserProfile;
import com.example.carpark.Model.Vehicle;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ParkingApi {


    //Auth
    //  Registration APIs
    // End  User
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("auth/send-otp")
    @FormUrlEncoded
    Call<BaseResponse> sendOTP(@Field("phone") String phone);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("auth/verify-otp")
    Call<BaseResponse> verifyOTP(@Body PhoneOtp phoneOtp);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("auth/register/user")
    Call <BaseDataResponse<UserResponse>> registerUser(@Body NewUser newUser);


    /*Admin and Partners.... Empty for now*/


    //Login
    //End User
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("auth/send-otp?login=true")
    @FormUrlEncoded
    Call<BaseResponse> getLoginOTP(@Field("phone") String phone);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("auth/login/user")
    Call<BaseDataResponse<UserResponse>> getLoginAccess(@Body PhoneOtp phoneOtp);

    /*Admin and Partners.... Empty for now*/


    //Profile Management
    @Headers({"Accept:application/json"})
    @GET("user")
    Call<User> getProfileDetails(@Header("Authorization") String token);

    //API is Faulty
    @Headers({"Accept:application/json","Content-Type:application/json", })
    @PUT("user")
    Call<BaseDataResponse<UserProfile>> editUserProfile(@Header("Authorization") String token, @Body UserProfile userProfile);

    //More methods



    //Parking Space Management

    //Add New Parking Space
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("park")
    Call<SingleParkingSpaceResponse> addNewParkingSpace(@Header("Authorization") String token, @Body NewParkingSpace newParkingSpace);

    @Headers({"Accept:application/json"})
    @GET("park")
    Call<ParkingSpaceAllResponse> getAllParkingSpaceA(@Header("Authorization") String token);

    @Headers({"Accept:application/json"})
    @GET("park/{id}")
    Call<SingleParkingSpaceResponse> getDetailsForSingleParking(@Path("id") int id, @Header("Authorization") String token);

    @Headers({"Accept:application/json"})
    @GET("park/active")
    Call<ActiveAndInactiveParkingSpaceAllResponse> getAllActiveParkingSpaces(@Header("Authorization") String token);

    @Headers({"Accept:application/json"})
    @GET("park/inactive")
    Call<ActiveAndInactiveParkingSpaceAllResponse> getAllInactiveParkingSpaces(@Header("Authorization") String token);

    @Headers({"Accept:application/json"})
    @GET("park/all")
    Call<PageParkingSpaceAllResponse> getAllParkingSpacesB(@Header("Authorization") String token);

    //TODO Response is incorrect but API works
    @Headers({"Accept:application/json","Content-Type:application/json"})
    @PATCH("park/{id}")
    @FormUrlEncoded
    Call<Vehicle> updateParkingSpaceDetails(@Header("Authorization") String token, @Path("id") int id, @Field("phone") String phone, @Field("status") String status);



    //Vehicles
    @Headers({"Accept:application/json"})
    @GET("vehicles")
    Call<VehicleList<Vehicle>> getAllVehicles(@Header("Authorization") String token);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("vehicles")
    @FormUrlEncoded
    Call<BaseDataResponse<Vehicle>> addNewVehicle(@Header("Authorization") String token, @Field("plate_number") String plate_number, @Field("make_model") String make_model, @Field("main_ride") boolean main_ride);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @PUT("vehicles/{id}")
    @FormUrlEncoded
    Call<BaseDataResponse<Vehicle>> editVehicle(@Header("Authorization") String token, @Path("id") int id, @Field("plate_number") String plate_number, @Field("make_model") String make_model);


    @Headers({"Accept: ","Content-Type:text/plain"})
    @DELETE("vehicles/{id}")
    Call<Void> deleteVehicle(@Header("Authorization") String token, @Path("id") int id);


    //Space Booking

    //TODO Response is incorrect
    @Headers({"Accept:application/json"})
    @GET("vehicles")
    Call<List<Vehicle>> bookASpace(@Header("Authorization") String token);

}
