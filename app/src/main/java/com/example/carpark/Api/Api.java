package com.example.carpark.Api;

import com.example.carpark.Api.Responses.BaseDataResponse;
import com.example.carpark.Api.Responses.BaseResponse;
import com.example.carpark.Api.Responses.LoginReg.UserResponse;
import com.example.carpark.Model.NewUser;
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
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface Api {

    //Registration APIs


    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("auth/register")
    Call <BaseDataResponse<UserResponse>> registerUser(@Body NewUser newUser);


    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("auth/send-otp")
    @FormUrlEncoded
    Call<BaseResponse> sendOTP(@Field("phone") String phone);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("auth/verify-otp")
    Call<BaseResponse> verifyOTP(@Body PhoneOtp phoneOtp);


    //Login APIs

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("auth/send-otp?login=true")
    @FormUrlEncoded
    Call<BaseResponse> getLoginOTP(@Field("phone") String phone);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("auth/login")
    Call<BaseDataResponse<UserResponse>> getLoginAccess(@Body PhoneOtp phoneOtp);


    //Vehicles
    @Headers({"Accept:application/json"})
    @GET("vehicles")
    Call<List<Vehicle>> getAllVehicles();

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @POST("vehicles")
    @FormUrlEncoded
    Call<BaseDataResponse<Vehicle>> addNewVehicle(@Field("plate_number") String plate_number, @Field("make_model") String make_model, @Field("main_ride") boolean main_ride);

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @PATCH("vehicles/{id}")
    @FormUrlEncoded
    Call<Vehicle> editVehicle(@Path("id") int id, @Field("plate_number") String plate_number, @Field("make_model") String make_model);



    @Headers({"Accept: ","Content-Type:text/plain"})
    @DELETE("vehicles/{id}")
    Call<Void> deleteVehicle(@Path("id") int id);


    //User

    //Endpoint doesn't look correct (Verify from Back-enders)
    @Headers({"Accept:application/javascript"})
    @GET("user")
    Call<User> getUserProfile();

    @Headers({"Accept:application/json","Content-Type:application/json"})
    @PATCH("user")
    Call<BaseDataResponse<UserProfile>> editUserProfile(@Body UserProfile userProfile);

}
