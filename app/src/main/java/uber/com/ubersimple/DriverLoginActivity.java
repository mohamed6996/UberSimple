package uber.com.ubersimple;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DriverLoginActivity extends AppCompatActivity implements View.OnClickListener {

    FirebaseAuth mAuth;
    DatabaseReference mDatabaseRef;


    EditText email_edt, password_edt;
    Button login_btn, register_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_login);

        mAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference().child("Users").child("Drivers");


        email_edt = findViewById(R.id.driver_email);
        password_edt = findViewById(R.id.driver_password);
        login_btn = findViewById(R.id.driver_login);
        register_btn = findViewById(R.id.driver_register);

        login_btn.setOnClickListener(this);
        register_btn.setOnClickListener(this);



    }


    @Override
    protected void onStart() {
        super.onStart();
//        if (mAuth.getCurrentUser() == null) {
//            Intent intent = new Intent(this, DriverLoginActivity.class);
//            startActivity(intent);
//            finish();
//        }

    }

    @Override
    public void onClick(View view) {
        String email = email_edt.getText().toString();
        String password = password_edt.getText().toString();

        int id = view.getId();

        switch (id) {
            case R.id.driver_login:
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Intent intent = new Intent(DriverLoginActivity.this, DriverMapActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });
                break;
            case R.id.driver_register:
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        String user_id = task.getResult().getUser().getUid();
                      //  String user_id = mAuth.getCurrentUser().getUid();// produce null pointer exception
                        mDatabaseRef.child(user_id).setValue(true).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Intent intent = new Intent(DriverLoginActivity.this, DriverMapActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });
                    }
                });
                break;

        }
    }
}
