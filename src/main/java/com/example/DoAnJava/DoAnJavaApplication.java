package com.example.DoAnJava;

import com.example.DoAnJava.services.FirebaseService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;

@SpringBootApplication
public class DoAnJavaApplication {
	public static void main(String[] args) throws IOException {
		new FirebaseService().initialAppFireBase();
		SpringApplication.run(DoAnJavaApplication.class, args);
	}

}
