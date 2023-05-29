package com.example.flutterdatingapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import androidx.appcompat.app.AppCompatActivity;

public class OTPActivity extends AppCompatActivity {
    private EditText otpEditText;
    private Button verifyButton;
    private YourApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        String mobileNumber = getIntent().getStringExtra("mobileNumber");

        TextView textView = findViewById(R.id.textView8);
        textView.setText(mobileNumber);

        apiService = RetrofitClient.getClient().create(YourApiService.class);

        otpEditText = findViewById(R.id.editTextOTP);
        verifyButton = findViewById(R.id.buttonVerify);


        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String enteredOTP = otpEditText.getText().toString();
                if (enteredOTP.isEmpty()) {
                    Toast.makeText(OTPActivity.this, "Please enter the OTP", Toast.LENGTH_SHORT).show();
                } else {
                    // Call your API to verify the entered OTP
                    verifyOTP(mobileNumber,enteredOTP);
                }


            }

        });

    }
    private void verifyOTP(String mobileNumber,String enteredOTP) {
        System.out.println("mobileNumber: " + mobileNumber);
        System.out.println("otp: " + enteredOTP);
//        OTPVerificationRequest otpVerificationRequest = new OTPVerificationRequest(mobileNumber, enteredOTP);
        Map<String, String> otpData = new HashMap<>();
        otpData.put("number", mobileNumber);
        otpData.put("otp", enteredOTP);
        System.out.println("otpData: " + otpData);
        Call<ApiResponse> call = apiService.verifyOTP(otpData);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    System.out.println("apiResponse: " + apiResponse);
                    assert apiResponse != null;
                    String message = apiResponse.getMessage();
                    boolean status = apiResponse.getStatus();
                    System.out.println("Message: " + apiResponse.getMessage());
                    System.out.println("Status: " + status);
                    if (message!=null) {
                        Toast.makeText(OTPActivity.this, "OTP verification successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(OTPActivity.this, HomeScreen.class);
                        intent.putExtra("token", message);
                        startActivity(intent);


                    } else {
                        Toast.makeText(OTPActivity.this, "OTP verification failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(OTPActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                // API call failed
                Log.e("API", "API call failed", t);
                Toast.makeText(OTPActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
