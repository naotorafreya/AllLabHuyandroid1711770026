package com.mary.androidhuy1711770026;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;

import java.util.ArrayList;
import java.util.List;


public class Lab4 extends AppCompatActivity {
    private ArrayAdapter<Restaurant> adapter = null;
    private List<Restaurant> restaurantList = new ArrayList<Restaurant>();
    Restaurant r = new Restaurant();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button save = (Button)findViewById(R.id.save); // tham chiếu đến Button
        save.setOnClickListener(onSave); // khai báo listener cho Button
        ListView list = (ListView)findViewById(R.id.restaurants);
        adapter = new ArrayAdapter<Restaurant>(this,android.R.layout.simple_list_item_1,restaurantList);
        list.setAdapter(adapter);
    }

    public String toString() {
        return (r.getName());
    }

    private View.OnClickListener onSave = new View.OnClickListener() {
        public void onClick(View v) {
            //Restaurant r = new Restaurant();
            EditText name = (EditText)findViewById(R.id.name);
            EditText address = (EditText)findViewById(R.id.addr);
            r.setName(name.getText().toString());
            r.setAddress(address.getText().toString());
            RadioGroup type = (RadioGroup)findViewById(R.id.type);


            switch (type.getCheckedRadioButtonId())
            {
                case R.id.take_out:
                    r.setType("Take out");
                    Toast.makeText(v.getContext(), name.getText().toString() + " " + address.getText().toString() + " " + "Take out" ,Toast.LENGTH_SHORT).show();
                    break;
                case R.id.sit_down:
                    r.setType("Sit down");
                    Toast.makeText(v.getContext(), name.getText().toString() + " " + address.getText().toString() + " " + "Sit down" ,Toast.LENGTH_SHORT).show();
                    break;
                case R.id.delivery:
                    r.setType("Delivery");
                    Toast.makeText(v.getContext(), name.getText().toString() + " " + address.getText().toString() + " " + "Delivery" ,Toast.LENGTH_SHORT).show();
                    break;
            }
            restaurantList.add(r);


        }
    };


}