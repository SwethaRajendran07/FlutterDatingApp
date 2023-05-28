package com.example.flutterdatingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private EditText editTextPhoneNumber;
    private Button buttonContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        buttonContinue = findViewById(R.id.buttonContinue);

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editTextPhoneNumber.getText().toString();

                if (phoneNumber.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a mobile number", Toast.LENGTH_SHORT).show();
                } else {
                    verifyMobileNumber(phoneNumber);
                }

            }
        });
    }


    private void verifyMobileNumber(String mobileNumber) {
        YourApiService apiService = RetrofitClient.getClient().create(YourApiService.class);
        Map<String, String> phoneNumberData = new HashMap<>();
        phoneNumberData.put("number", mobileNumber);
        Call<ApiResponse> call = apiService.phoneNumberLogin(phoneNumberData);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(@NonNull Call<ApiResponse> call, @NonNull Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    ApiResponse apiResponse = response.body();
                    assert apiResponse != null;
                    String message = apiResponse.getMessage();
                    boolean status = apiResponse.getStatus();
                    if (status) {
                        // Mobile number verification successful
                        Toast.makeText(MainActivity.this, "Mobile number verification successful", Toast.LENGTH_SHORT).show();
                        // Navigate to OTPVerificationActivity
                        Intent intent = new Intent(MainActivity.this, OTPActivity.class);
                        intent.putExtra("mobileNumber", mobileNumber);
                        startActivity(intent);
                    } else {
                        // Mobile number verification failed
                        Toast.makeText(MainActivity.this, "Mobile number verification failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // API call failed
                    Toast.makeText(MainActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<ApiResponse> call, @NonNull Throwable t) {
                // API call failed
                Toast.makeText(MainActivity.this, "API call failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private void navigateToOTPVerification(String phoneNumber, String otp) {
//        Intent intent = new Intent(MainActivity.this, OTPActivity.class);
//        intent.putExtra("phoneNumber", phoneNumber);
//        intent.putExtra("otp", otp);
//        startActivity(intent);
//    }
}
