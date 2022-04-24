package com.manishprajapat.example.expensemanager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.expensemanager.R;

import java.util.ArrayList;

public class ExpenseDetailsAdapter extends RecyclerView.Adapter<ExpenseDetailsAdapter.myViewHolder> {

    ArrayList<POJO> expenseDetails;
    Context context;

        public ExpenseDetailsAdapter(Context context, ArrayList<POJO> expenseDetails) {
        this.expenseDetails = expenseDetails;
        this.context = context;
    }

    @NonNull
    @Override
    public ExpenseDetailsAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.expenses_list,parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseDetailsAdapter.myViewHolder holder, int position) {

        holder.name.setText(expenseDetails.get(position).supplierName);
        holder.category.setText(expenseDetails.get(position).category);
        holder.total.setText("Rs." + expenseDetails.get(position).expenseAmount);
        holder.paid.setText("Paid Rs." + expenseDetails.get(position).paidAmt);
        holder.due.setText("Due Rs. " + expenseDetails.get(position).PendingAmount);

        holder.day.setText(expenseDetails.get(position).day_of_month);
        holder.month.setText(expenseDetails.get(position).month_of_year);
        holder.year.setText(expenseDetails.get(position).year);
    }

    @Override
    public int getItemCount() {
        return expenseDetails.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        TextView name, category, paid, total, due;
                TextView day, month, year;
        public myViewHolder(@NonNull View view1) {
            super(view1);

        name = view1.findViewById(R.id.tv_elName);
        category = view1.findViewById(R.id.tv_elCat);
        paid = view1.findViewById(R.id.tv_elPaidRs);
        total = view1.findViewById(R.id.tv_elTotalSpend);
        due = view1.findViewById(R.id.tv_elDue);

        day = view1.findViewById(R.id.tv_elDay);
        month = view1.findViewById(R.id.tv_elMonth);
        year = view1.findViewById(R.id.tv_elYear);

        }
    }
}
