package com.example.brianmote.expensemanager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brianmote.expensemanager.firebase.FirebaseAuthHandler;
import com.example.brianmote.expensemanager.listeners.OnCreateAccountListener;
import com.example.brianmote.expensemanager.pojos.User;
import com.firebase.client.FirebaseError;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    private User user;
    private FirebaseAuthHandler handler;
    @BindView(R.id.regUsername)
    EditText regUsername;
    @BindView(R.id.regEmail)
    EditText regEmail;
    @BindView(R.id.regPassword)
    EditText regPassword;
    @BindView(R.id.regSubmitBtn)
    Button regSubmitBtn;


    @OnClick(R.id.regSubmitBtn)
    public void createAcctLogin() {
        final ProgressDialog dialog = new ProgressDialog(RegisterActivity.this);
        dialog.setTitle("Loading...");
        dialog.setCancelable(false);
        if (!dialog.isShowing()) {
            dialog.show();
        }

        String username = regUsername.getText().toString();
        String email = regEmail.getText().toString();
        String password = regPassword.getText().toString();
        user = new User(username, email, password);
        handler = new FirebaseAuthHandler();

        handler.createAccount(user, new OnCreateAccountListener() {
            @Override
            public void onComplete() {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                Toast.makeText(RegisterActivity.this, "Account Created! Logging in",
                        Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
            }

            @Override
            public void onError(FirebaseError error) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                Toast.makeText(RegisterActivity.this, error.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ButterKnife.bind(this);
    }

}
