package com.example.sdl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SignUp extends AppCompatActivity {
    TextInputLayout regName,regUsername,regEmail,regPhoneNo,regPassword;
    Button regBtn,regToLoginBtn;
    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_sign_up);

        regName=findViewById(R.id.reg_name);
        regUsername=findViewById(R.id.reg_username);
        regEmail=findViewById(R.id.reg_email);
        regPhoneNo=findViewById(R.id.reg_phoneno);
        regPassword=findViewById(R.id.reg_password);
        regBtn=findViewById(R.id.reg_btn);
        regToLoginBtn=findViewById(R.id.reg_login_btn);
        mAuth=FirebaseAuth.getInstance();
        regToLoginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SignUp.this,Login.class);
                startActivity(intent);
                finish();
            }
        });
        regBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                rootNode=FirebaseDatabase.getInstance();
                reference=rootNode.getReference("users");
                if(!validateEmail() | !validateName() | !validatePassword() | !validatePhoneNo() | !validateUsername()){
                    return;
                }
                final String name=regName.getEditText().getText().toString();
                final String username=regUsername.getEditText().getText().toString();
                final String email=regEmail.getEditText().getText().toString();
                final String phoneNo=regPhoneNo.getEditText().getText().toString();
                final String password=regPassword.getEditText().getText().toString();
                mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent=new Intent(getApplicationContext(),AdditionDetails.class);
                            intent.putExtra("name", name);
                            intent.putExtra("username", username);
                            intent.putExtra("password", password);
                            intent.putExtra("phoneNo", phoneNo);
                            intent.putExtra("email", email);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Registration failed!!" + " Please try again later", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    private Boolean validateName(){
        String val=regName.getEditText().getText().toString();
        if(val.isEmpty()){
            regName.setError("Field cannot be empty");
            return false;
        }
        else{
            regName.setError(null);
            regName.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateUsername(){
        String val=regUsername.getEditText().getText().toString();
        if(val.isEmpty()){
            regUsername.setError("Field cannot be empty");
            return false;
        }
        else{
            regUsername.setError(null);
            regUsername.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateEmail(){
        String val=regEmail.getEditText().getText().toString();
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        if(val.isEmpty()){
            regEmail.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(emailPattern)){
            regEmail.setError("Invalid Email address");
            return false;
        }
        else{
            regEmail.setError(null);
            regEmail.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePassword(){
        String val=regPassword.getEditText().getText().toString();
        if(val.isEmpty()){
            regPassword.setError("Field cannot be empty");
            return false;
        }
        else{
            regPassword.setError(null);
            regPassword.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validatePhoneNo(){
        String val=regPhoneNo.getEditText().getText().toString();
        if(val.isEmpty()){
            regPhoneNo.setError("Field cannot be empty");
            return false;
        }
        else{
            regPhoneNo.setError(null);
            regPhoneNo.setErrorEnabled(false);
            return true;
        }
    }
}
