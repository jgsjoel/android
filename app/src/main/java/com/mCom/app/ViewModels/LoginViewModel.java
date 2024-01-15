package com.mCom.app.ViewModels;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.mCom.app.Models.SignUp;
import com.mCom.app.app.AppThreads;
import com.mCom.app.dto.User;

public class LoginViewModel extends ViewModel {

    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private MutableLiveData<Boolean> isVerified = new MutableLiveData<Boolean>();
    private boolean notVerified = true;

    public MutableLiveData<Boolean> getIsVerified(){
        return isVerified;
    }

    public void checkVerificationStatus(){
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        AppThreads.runScheduledThread(()->{
            while(notVerified){
                firebaseUser.reload();
                if(firebaseUser.isEmailVerified()){
                    isVerified.setValue(true);
                    notVerified = false;
                    Log.i("Register","--------------Email is Verified--------------");
                }else{
                    Log.i("Register","--------------Email Not Verified--------------");
                }
            }
        });
    }

    public void registerNewUser(View currentView, User user){
        SignUp signUp = new SignUp(currentView,user);
        signUp.registerNewUser();
    }



}
