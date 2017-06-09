package com.oyinloyeayodeji.www.meals;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView mealTotalText;
    ArrayList<Food> orders;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView storedOrders = (ListView)findViewById(R.id.selected_food_list);

        orders = getListItemData();
        mealTotalText = (TextView)findViewById(R.id.meal_total);
        OrderAdapter adapter = new OrderAdapter(this, orders);

        storedOrders.setAdapter(adapter);
        adapter.registerDataSetObserver(observer);
    }

    public int calculateMealTotal(){
        int mealTotal = 0;
        for(Food order : orders){
            mealTotal += order.getmAmount() * order.getmQuantity();
        }
        return mealTotal;
    }

    DataSetObserver observer = new DataSetObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
            setMealTotal();
        }
    };

    private ArrayList<Food> getListItemData(){
        ArrayList<Food> listViewItems = new ArrayList<Food>();
        listViewItems.add(new Food("Rice",30));
        listViewItems.add(new Food("Beans",40));
        listViewItems.add(new Food("Yam",60));
        listViewItems.add(new Food("Pizza",80));
        listViewItems.add(new Food("Fries",100));

        return listViewItems;
    }

    public void setMealTotal(){
        mealTotalText.setText("GH"+"\u20B5"+" "+ calculateMealTotal());
    }
}
