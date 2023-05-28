package com.example.flutterdatingapp;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface YourApiService {

    @POST("users/phone_number_login")
    Call<ApiResponse> phoneNumberLogin(@Body Map<String, String> phoneNumber);

    @POST("users/verify_otp")
    Call<ApiResponse> verifyOTP(@Body Map<String, String> otpData);

    @GET("users/test_profile_list")
    Call<ProfileDataResponse> getTestProfileList(@Header("Authorization") String authToken);


}
