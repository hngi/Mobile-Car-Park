package service;

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
import retrofit2.http.Headers;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {

    // Registration
    @POST("auth/register")
    Call<UserResponse> registerUser(@Body NewUser newUser);

    @POST("auth/send-otp")
    @FormUrlEncoded
    Call<BaseDataResponse<BaseResponse>>sendOTP(@Field("phone") String phone);

    @POST("auth/verify-otp")
    Call<BaseDataResponse<BaseResponse>> verifyOTP(@Body PhoneOtp phoneOtp);

    @POST("auth/send-otp")
    @FormUrlEncoded
    Call<BaseDataResponse<BaseResponse>> loginUser(@Field("phone") String phone, @Query ("login") boolean status);

    @POST("auth/login")
    Call<BaseDataResponse<BaseResponse>> getUserAcessToken(@Body PhoneOtp phoneOtp);

    @GET("vehicles")
    Call<Vehicle> listAllVehicles();

    @POST("vehicles")
    @FormUrlEncoded
    Call<Vehicle> addNewVehicleRecord(@Field("plate_number") String plate_number, @Field("make_model") String make_model, @Field("main_ride") boolean main_ride);

    @PATCH("vehicles/{id}")
    @FormUrlEncoded
    Call<Vehicle> editVehicleRecord(@Path("id") int id, @Field("plate_number") String plate_number, @Field("make_model") String make_model);

    @DELETE("vehicles/{id}")
    @FormUrlEncoded
    Call<Void> deleteVehicleRecord(@Path("id") int id, @Field("plate_number") String plate_number, @Field("make_model") String make_model);

    @GET("user")
    Call<User> getUserProfile();

    @PATCH("user")
    Call<BaseDataResponse<User>> editVehicleRecord(@Body UserProfile userProfile);

}
