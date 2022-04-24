package com.manishprajapat.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.expensemanager.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ExpenseActivity extends AppCompatActivity {

    //Expense Activity views
    EditText etInvoiceNumber, etName, etMobile, etCategory, etTotal, etPaidAmt;
    TextView tvDate, tvEdit, tvAttach, tvBalAmt;
    Button addExpenses;
    TextView backToMainActivity;

    DBHelper dbHelper;
   RecyclerView recyclerView;

    String day_f, month_f, year_f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);

        Toolbar toolbar = findViewById(R.id.expense_toolbar);
        setSupportActionBar(toolbar);

        dbHelper = new DBHelper(this);

        etInvoiceNumber = findViewById(R.id.et_eInvoiceNumber);
        etName = findViewById(R.id.et_eName);
        etMobile = findViewById(R.id.et_eMobile);
        etCategory = findViewById(R.id.et_eCategory);
        etPaidAmt = findViewById(R.id.et_ePaidAmount);
        etTotal = findViewById(R.id.et_eTotal);
        tvDate = findViewById(R.id.tv_eDate);
        tvEdit = findViewById(R.id.tv_eEdit);
        tvAttach = findViewById(R.id.tv_eAttach);
        tvBalAmt = findViewById(R.id.tv_eBalanceAmount);
        addExpenses = findViewById(R.id.btnAddExpense);
        recyclerView = findViewById(R.id.recycleView);
        backToMainActivity = findViewById(R.id.tv_expence_back);

//        ArrayList<POJO> expensesList = new ArrayList<>();

        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("dd MMMM yyyy");
        String currentDateandTime = sdf.format(new Date());
        tvDate.setText(currentDateandTime);


        SimpleDateFormat day_format = new SimpleDateFormat("dd");
        day_f = day_format.format(new Date());

        SimpleDateFormat year_format = new SimpleDateFormat("yyyy");
        year_f = year_format.format(new Date());


        SimpleDateFormat month_format = new SimpleDateFormat("MMMM");
        month_f = month_format.format(new Date());


        tvEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("pick date", "onClick: Pick date");
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(ExpenseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        String[] monthName = {"January", "February", "March", "April", "May", "June", "July",
                                "August", "September", "October", "November",
                                "December"};
                        String monthNameWithDate = monthName[i1];
                        String date = i2 + " " + monthNameWithDate + " " + i;
                        tvDate.setText(date);

                        day_f = String.valueOf(i2);
                        month_f = monthNameWithDate;
                        year_f = String.valueOf(i);

                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });

        backToMainActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        tvAttach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ExpenseActivity.this, "upload invoice bill..", Toast.LENGTH_SHORT).show();

            }
        });

        addExpenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String expName = etName.getText().toString();
                String exInvoiceNumber = etInvoiceNumber.getText().toString();
                String exMobile = etMobile.getText().toString();
                String exCat = etCategory.getText().toString();
                String exDate = tvDate.getText().toString();
                String exTotal = etTotal.getText().toString();
                String exPaidAmt = etPaidAmt.getText().toString();
                String tvBalanceAmount = tvBalAmt.getText().toString();

                    //This below line code not working
//                if (exInvoiceNumber.isEmpty() && expName.isEmpty()  && exMobile.isEmpty() && exCat.isEmpty() && exTotal.isEmpty() && exDate.isEmpty() && exPaidAmt.isEmpty()) {
////                    Toast.makeText(ExpenseActivity.this, "Please fill above details", Toast.LENGTH_SHORT).show();
////                }

                if (exInvoiceNumber.isEmpty()) {
                    Toast.makeText(ExpenseActivity.this, "Please fill above details", Toast.LENGTH_SHORT).show();
                } else if (expName.isEmpty()) {
                    Toast.makeText(ExpenseActivity.this, "Please fill above details", Toast.LENGTH_SHORT).show();
                } else if (exMobile.isEmpty()) {
                    Toast.makeText(ExpenseActivity.this, "Please fill above details", Toast.LENGTH_SHORT).show();
                } else if (exCat.isEmpty()) {
                    Toast.makeText(ExpenseActivity.this, "Please fill above details", Toast.LENGTH_SHORT).show();
                } else if (exTotal.isEmpty()) {
                    Toast.makeText(ExpenseActivity.this, "Please fill above details", Toast.LENGTH_SHORT).show();
                } else if (exPaidAmt.isEmpty()) {
                    Toast.makeText(ExpenseActivity.this, "Please fill above details", Toast.LENGTH_SHORT).show();
                } else if (tvBalanceAmount.isEmpty()) {
                    Toast.makeText(ExpenseActivity.this, "Balance Amount Click", Toast.LENGTH_SHORT).show();
                } else {

                    dbHelper.addExpenseDetails(exInvoiceNumber, expName, exDate, exMobile, exCat, exTotal, exPaidAmt, tvBalanceAmount, day_f, month_f, year_f);
                    Toast.makeText(ExpenseActivity.this, "Expenses details has been added!!", Toast.LENGTH_SHORT).show();
                    etInvoiceNumber.setText("");
                    etName.setText(" ");
                    etMobile.setText(" ");
                    etCategory.setText(" ");
                    etTotal.setText(" ");
                    tvEdit.setText(" ");
                    tvBalAmt.setText(" ");
                    etTotal.setText(" ");
                    etPaidAmt.setText(" ");
                    tvEdit.setText(" ");

                    Intent intent = new Intent(ExpenseActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        findViewById(R.id.tvGetDue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tot = etTotal.getText().toString();
                String paid = etPaidAmt.getText().toString();

                if (TextUtils.isEmpty(tot)) {
                    etTotal.setError("Total Expense");
                    return;
                } else if (TextUtils.isEmpty(paid)) {
                    etPaidAmt.setError("Paid amount");
                    return;
                }

                Integer t = Integer.parseInt(tot);
                Integer p = Integer.parseInt(paid);
                int bal = t - p;
                tvBalAmt.setText(bal + ".00");
            }
        });

    }

}