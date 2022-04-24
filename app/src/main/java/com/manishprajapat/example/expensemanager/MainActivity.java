package com.manishprajapat.example.expensemanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.expensemanager.R;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnExpense;
    ArrayList<POJO> modalArrayList;
    DBHelper dbHandler;
    ExpenseDetailsAdapter courseRVAdapter;

    RecyclerView rListView;


    TextView tvTotalExpenses, tvTotalPendingAmt, tvExpenseDate, tvLooking;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnExpense = findViewById(R.id.btnAddExpense);
        tvTotalExpenses = findViewById(R.id.tvTotalMonthExpense);
        tvTotalPendingAmt = findViewById(R.id.tvTotalMonthPending);
        tvExpenseDate = findViewById(R.id.tvExpenseDate);
        rListView = findViewById(R.id.recycleView);

        //Set date for upto total expanses
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

//        month=month+1;  // Month by default start with index 0.
        String[] monthName = {"January", "February", "March", "April", "May", "June", "July",
                "August", "September", "October", "November",
                "December"};

        tvExpenseDate.setText("Expenses for " + monthName[month] + ", " + day);

        //set Total montly expanses on main activity
        dbHandler = new DBHelper(this);
        int totalMont = dbHandler.getTotalExpansesMonth();
        tvTotalExpenses.setText("Rs. " + totalMont + ".00");

        //set total pending expenses
        dbHandler = new DBHelper(this);
        int totalPending = dbHandler.getTotalPendingExpansesMonth();
        tvTotalPendingAmt.setText("Rs. " + totalPending + ".00");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        btnExpense.setOnClickListener(this);
        showExpenseModel();

    }

    private void showExpenseModel() {
        modalArrayList = new ArrayList<>();
        dbHandler = new DBHelper(MainActivity.this);

        modalArrayList = dbHandler.readExpenses();

        // on below line passing our array lost to our adapter class.
        ExpenseDetailsAdapter adapter = new ExpenseDetailsAdapter(MainActivity.this, modalArrayList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rListView.setLayoutManager(layoutManager);
        rListView.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ExpenseActivity.class);
        startActivity(intent);
        finish();
    }
}