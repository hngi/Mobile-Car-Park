package com.example.carpark.Api;

import com.example.carpark.Api.Responses.BaseDataResponse;
import com.example.carpark.Api.Responses.BaseResponse;
import com.example.carpark.Api.Responses.LoginReg.UserResponse;
import com.example.carpark.Model.NewUser;
import com.example.carpark.Model.PhoneOtp;
import com.example.carpark.Model.User;
import com.example.carpark.Model.UserProfile;
import com.example.carpark.Model.Vehicle;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    //Registration APIs
    @POST("auth/register")
    Call<UserResponse> registerUser(@Body NewUser newUser);

    @POST("auth/send-otp")
    @FormUrlEncoded
    Call<BaseDataResponse<UserResponse>>sendOTP(@Field("phone") String phone);

    @POST("auth/verify-otp")
    Call<BaseDataResponse<UserResponse>> verifyOTP(@Body PhoneOtp phoneOtp);

    @POST("auth/send-otp?login=true")
    @FormUrlEncoded
    Call<BaseDataResponse<UserResponse>> getLoginOTP(@Field("phone") String phone);

    @POST("auth/login")
    Call<BaseDataResponse<UserResponse>> getUserAcessToken(@Body PhoneOtp phoneOtp);


    //Vehicles
    @GET("vehicles")
    Call<Vehicle> listAllVehicles();

    @POST("vehicles")
    @FormUrlEncoded
    Call<Vehicle> addNewVehicleRecord(@Field("plate_number") String plate_number, @Field("make_model") String make_model, @Field("main_ride") boolean main_ride);

    @PATCH("vehicles/{id}")
    @FormUrlEncoded
    Call<Vehicle> editVehicleRecord(@Path("id") int id, @Field("plate_number") String plate_number, @Field("make_model") String make_model);

    @DELETE("vehicles/{id}")
    Call<Void> deleteVehicleRecord(@Path("id") int id);


    //User
    @GET("user")
    Call<User> getUserProfile();

    @PATCH("user")
    Call<BaseDataResponse<User>> editVehicleRecord(@Body UserProfile userProfile);

}
