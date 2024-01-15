package com.mCom.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseUser;
import com.mCom.app.ViewModels.LoginViewModel;
import com.mCom.app.dto.User;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    private TextView firstName,lastName,email,password;
    private View currentView;
    private FirebaseAuth firebaseAuth;
    private final String TAG = RegisterActivity.class.toString();
    private LoginViewModel viewModel;
    FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        firstName = findViewById(R.id.r_FirstName);
        lastName = findViewById(R.id.r_LastName);
        email = findViewById(R.id.r_Email);
        password = findViewById(R.id.r_Password);
        currentView = findViewById(R.id.registerView);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        viewModel.getIsVerified().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                    startActivity(intent);
                }
            }
        });
        viewModel.checkVerificationStatus();

        findViewById(R.id.r_Register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String stringFirstName = firstName.getText().toString();
                String stringLastName = lastName.getText().toString();
                String stringEmail = email.getText().toString();
                String stringPassword = password.getText().toString();

                if(stringFirstName.isEmpty()){
                    Snackbar.make(currentView,"Please Enter First Name",Snackbar.LENGTH_SHORT).show();
                } else if (stringLastName.isEmpty()) {
                    Snackbar.make(currentView,"Please Enter Last Name",Snackbar.LENGTH_SHORT).show();
                } else if (stringEmail.isEmpty()) {
                    Snackbar.make(currentView,"Please Enter Email",Snackbar.LENGTH_SHORT).show();
                }else if(!Pattern.compile("^[a-zA-Z0-9+-._]+@[a-z0-9+-]+.[a-z]+").matcher(stringEmail).matches()){
                    Snackbar.make(currentView,"Invalid Email",Snackbar.LENGTH_SHORT).show();
                } else if (stringPassword.isEmpty()) {
                    Snackbar.make(currentView,"Please Enter Password",Snackbar.LENGTH_SHORT).show();
                } else if (stringPassword.length() < 8) {
                    Snackbar.make(currentView,"Password must be 8 or more characters",Snackbar.LENGTH_SHORT).show();
                } else{

                    User user = new User();
                    user.setEmail(stringEmail);
                    user.setPassword(stringPassword);
                    user.setFirst_name(stringFirstName);
                    user.setLast_name(stringLastName);

                    viewModel.registerNewUser(currentView,user);

                }
            }
        });

    }
}