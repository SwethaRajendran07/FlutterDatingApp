package com.example.flutterdatingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import android.util.Log;
import android.widget.ImageView;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeScreen extends AppCompatActivity {

    private YourApiService apiService;
    String Image1;
    String Image2;
    String Image3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        apiService = RetrofitClient.getClient().create(YourApiService.class);

        String authToken = getIntent().getStringExtra("token"); // Replace with your actual authorization token

        getData(authToken);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.discover:
                        // Handle Home tab click
                        return true;
                    case R.id.mail:
                        // Handle Search tab click
                        return true;
                    case R.id.matches:
                        // Handle Profile tab click
                        return true;
                    case R.id.profile:
                        // Handle Profile tab click
                        return true;
                }
                return false;
            }
        });
    }
    private void getData(String token) {
        System.out.println(token);
        Call<ProfileDataResponse> call = apiService.getTestProfileList(token);

        call.enqueue(new Callback<ProfileDataResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProfileDataResponse> call, @NonNull Response<ProfileDataResponse> response) {
                System.out.println(response.body());
                ProfileDataResponse apiResponse = response.body();


                if (apiResponse!=null) {
                    System.out.println("heyy");
                    Likes likes = apiResponse.getLikes();
                    System.out.println(likes);
                    if(likes!=null){
                        System.out.println(likes);
                        System.out.println("likes");


                        List<Profile> profiles = apiResponse.getLikes().getProfiles();
                        for (Profile profile : profiles) {
                            String firstName = profile.getFirst_name();
                            String avatarUrl = profile.getAvatar();

                            // Do something with the values
                            System.out.println("First Name: " + firstName);
                            System.out.println("Avatar URL: " + avatarUrl);
                        }
                       // new DownloadImageFromInternet((ImageView) findViewById(R.id.imageView7)).execute("https://pbs.twimg.com/profile_images/630285593268752384/iD1MkFQ0.png");


                    }

// Handle the response
                } else {
                    // Handle API request failure
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProfileDataResponse> call, @NonNull Throwable t) {
                // Handle API request failure
            }
        });
    }
    @SuppressLint("StaticFieldLeak")
    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {
        ImageView imageView;
        public DownloadImageFromInternet(ImageView imageView) {
            this.imageView=imageView;
            Toast.makeText(getApplicationContext(), "Please wait, it may take a few minute...",Toast.LENGTH_SHORT).show();
        }
        protected Bitmap doInBackground(String... urls) {
            String imageURL=urls[0];
            Bitmap bimage=null;
            try {
                InputStream in=new java.net.URL(imageURL).openStream();
                bimage=BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;
        }
        protected void onPostExecute(Bitmap result) {
            imageView.setImageBitmap(result);
        }
    }

}