//package com.example.flutterdatingapp;
//
//import android.annotation.SuppressLint;
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//public class PhoneNumberActivity extends AppCompatActivity {
//    private EditText phoneNumberEditText;
//    private Button continueButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_phone_number);
//
//        phoneNumberEditText = findViewById(R.id.editTextPhoneNumber);
//        continueButton = findViewById(R.id.buttonContinue);
//
//        continueButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String phoneNumber = phoneNumberEditText.getText().toString();
//                String otp = generateOTP();
//
//                Intent intent = new Intent(PhoneNumberActivity.this, OTPActivity.class);
//                intent.putExtra("phone_number", phoneNumber);
//                intent.putExtra("otp", otp);
//                startActivity(intent);
//
//
//
//
//            }
//        });
//    }
//
//    private String generateOTP() {
//        // Generate and return an OTP here
//        return "1234"; // Replace with your OTP generation logic
//
//    }
//}
