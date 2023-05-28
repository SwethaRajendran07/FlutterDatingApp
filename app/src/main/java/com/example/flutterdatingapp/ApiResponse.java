package com.example.flutterdatingapp;


import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    private String message;
    private boolean status;
    private String token;

    @SerializedName("success")
    private boolean success;

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return token;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
