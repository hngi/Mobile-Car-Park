package service;

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

    @POST("auth/register")
    Call<UserResponse> registerUser(@Body NewUser newUser);

    @POST("auth/send-otp")
    @FormUrlEncoded
    Call<BaseDataResponse<BaseResponse>>sendOTP(@Field("phone") String phone);

    @POST("auth/verify-otp")
    @FormUrlEncoded
    Call<BaseDataResponse<BaseResponse>> verifyOTP(@Field("phone") String phone, @Field("otp") String otp);

    @POST("auth/send-otp")
    @FormUrlEncoded
    Call<BaseDataResponse<BaseResponse>> loginUser(@Field("phone") String phone, @Query ("login") String status);

    @POST("auth/login")
    @FormUrlEncoded
    Call<BaseDataResponse<BaseResponse>> getUserAcessToken(@Field("phone") String phone, @Field("otp") String otp);

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
    Call<Vehicle> listAllVehicles();

    @PATCH("user")
    @FormUrlEncoded
    Call<BaseDataResponse<Vehicle>> editVehicleRecord(@Body UserProfile userProfile);

}
