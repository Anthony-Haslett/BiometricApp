package com.example.biometricapp;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.biometric.BiometricPrompt;

import android.widget.Button;

import com.example.biometricapp.deviceauth.OnDeviceAuth;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Biometric calls
        OnDeviceAuth deviceAuth = new OnDeviceAuth();
        BiometricPrompt.PromptInfo promptInfo = deviceAuth.setAllowedAuthenticators();
        BiometricPrompt biometricPrompt = deviceAuth.biometricPrompt(this);

        // Prompt appears when user clicks "Auth".
        // Consider integrating with the keystore to unlock cryptographic operations,
        // if needed by your app.
        Button biometricLoginButton = findViewById(R.id.biometric_button);

        biometricLoginButton.setOnClickListener(view -> biometricPrompt.authenticate(promptInfo));
    }
}