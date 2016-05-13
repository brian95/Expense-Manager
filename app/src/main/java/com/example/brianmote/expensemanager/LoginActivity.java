package com.example.brianmote.expensemanager;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brianmote.expensemanager.firebase.FirebaseAuthHandler;
import com.example.brianmote.expensemanager.listeners.OnAuthListener;
import com.example.brianmote.expensemanager.pojos.User;
import com.firebase.client.FirebaseError;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = LoginActivity.class.getSimpleName();
    private FirebaseAuthHandler handler;
    private User user;
    @BindView(R.id.loginEmail)
    EditText loginEmail;
    @BindView(R.id.loginPassword)
    EditText loginPassword;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.createAcctBtn)
    Button createAcctBtn;

    @OnClick(R.id.createAcctBtn)
    public void createAcct() {
        startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
    }

    @OnClick(R.id.loginBtn)
    public void login() {
        final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
        dialog.setTitle("Loading...");
        dialog.setCancelable(false);
        if (!dialog.isShowing()) {
            dialog.show();
        }

        String email = loginEmail.getText().toString();
        String password = loginPassword.getText().toString();
        user = new User(email, password);
        handler = new FirebaseAuthHandler();

        handler.login(user, new OnAuthListener() {
            @Override
            public void onComplete() {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                Toast.makeText(LoginActivity.this, "Logging in", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, HomeActivity.class));
            }

            @Override
            public void onError(FirebaseError error) {
                if (dialog.isShowing()) {
                    dialog.dismiss();
                }
                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
