package com.mCom.app.Models;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.mCom.app.RegisterActivity;
import com.mCom.app.dto.User;

import java.util.HashMap;
import java.util.Map;

public class SignUp {

    private FirebaseAuth firebaseAuth;
    private User userObj;
    private View currentView;
    private FirebaseFirestore fireStore;
    private final String TAG = RegisterActivity.class.toString();

    public SignUp(View view,User userObj){
        this.currentView = view;
        this.userObj = userObj;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.fireStore = FirebaseFirestore.getInstance();
    }

    public void registerNewUser(){
        firebaseAuth.createUserWithEmailAndPassword(userObj.getEmail(), userObj.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            sendVerificationEmail();
                            saveUserData();
                        }else{
                            checkAndShowError(task);
                        }
                    }
                });
    }

    private void sendVerificationEmail(){
        firebaseAuth.getCurrentUser().sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Snackbar.make(currentView,"Register complete. Check your email",Snackbar.LENGTH_SHORT).show();
                        }else{
                            checkAndShowError(task);
                        }
                    }
                });
    }

    private void checkAndShowError(Task<?> task){
        Exception exception = task.getException();
        if(exception instanceof FirebaseNetworkException){
            Snackbar.make(currentView,"Network Error!",Snackbar.LENGTH_SHORT).show();
        }else if(exception instanceof FirebaseAuthUserCollisionException){
            Snackbar.make(currentView,exception.getMessage(),Snackbar.LENGTH_SHORT).show();
        }else{
            Snackbar.make(currentView,"An unknown error occurred",Snackbar.LENGTH_SHORT).show();
            Log.i(TAG,exception.toString());
        }
    }

    private void saveUserData(){
        Map<String,String> userMapData = new HashMap<>();
        userMapData.put("first_name", userObj.getFirst_name());
        userMapData.put("last_name", userObj.getLast_name());

        fireStore.collection("Users").add(userMapData)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Log.i(TAG,"New user data has been added to fireStore. DocRef--> "+documentReference.getId());

            }
        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG,"error while adding user data to fireStore --> "+e.getMessage());
                    }
                });
    }

}
