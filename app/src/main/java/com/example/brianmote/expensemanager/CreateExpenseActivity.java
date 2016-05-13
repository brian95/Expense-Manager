package com.example.brianmote.expensemanager;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.brianmote.expensemanager.firebase.ExpenseHandler;
import com.example.brianmote.expensemanager.listeners.OnDataInsertedListener;
import com.example.brianmote.expensemanager.pojos.Expense;
import com.firebase.client.FirebaseError;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateExpenseActivity extends AppCompatActivity {
    private static final String TAG = CreateExpenseActivity.class.getSimpleName();
    private ExpenseHandler handler;
    @BindView(R.id.expenseNameField)
    EditText expNameField;
    @BindView(R.id.expenseAmount)
    EditText expAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_expense);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final ProgressDialog dialog = new ProgressDialog(CreateExpenseActivity.this);
                dialog.setTitle("Loading...");
                dialog.setCancelable(false);
                if (!dialog.isShowing()) {
                    dialog.show();
                }

                String name = expNameField.getText().toString();
                String amount = expAmount.getText().toString();
                Expense expense = new Expense(name, amount);
                handler = new ExpenseHandler();

                handler.insert(expense, new OnDataInsertedListener() {
                    @Override
                    public void onComplete() {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        Toast.makeText(CreateExpenseActivity.this, "Added Expense", Toast
                                .LENGTH_SHORT)
                                .show();
                    }

                    @Override
                    public void onError(FirebaseError error) {
                        if (dialog.isShowing()) {
                            dialog.dismiss();
                        }
                        Toast.makeText(CreateExpenseActivity.this, error.getMessage(),
                                Toast.LENGTH_SHORT)
                                .show();

                    }
                });
            }
        });

    }

}
