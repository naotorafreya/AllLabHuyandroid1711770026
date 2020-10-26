package com.mary.androidhuy1711770026;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import java.util.ArrayList;
import java.util.List;


public class Lab6 extends AppCompatActivity {
    RestaurantAdapter adapter = null;
    List<Restaurant> listRestaurant = new ArrayList<Restaurant>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button save = (Button)findViewById(R.id.save); // tham chiếu đến Button
        save.setOnClickListener(onSave); // khai báo listener cho Button
        ListView list = (ListView)findViewById(R.id.restaurants);
        adapter = new RestaurantAdapter();
        list.setAdapter(adapter);
    }



    private View.OnClickListener onSave = new View.OnClickListener() {
        public void onClick(View v) {
            Restaurant r = new Restaurant();

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
            //restaurantList.add(r);
            listRestaurant.add(r);

        }
    };

    class RestaurantAdapter extends ArrayAdapter<Restaurant>
    {
        public RestaurantAdapter(Context context,int textViewResourceld){
            super(context,textViewResourceld);
        }
        public RestaurantAdapter()
        {
            super(Lab6.this,android.R.layout.simple_list_item_1,listRestaurant);
        }
        public View getView(int position, View convertView, ViewGroup parent){
            View row = convertView;
            if(row == null){
                LayoutInflater inflater = getLayoutInflater();
                row = inflater.inflate(R.layout.row,null);
            }
            Restaurant r = listRestaurant.get(position);

            ((TextView)row.findViewById(R.id.title)).setText(r.getName());
            ((TextView)row.findViewById(R.id.title)).setText(r.getAddress());
            ImageView icon = (ImageView)row.findViewById(R.id.icon);

            String type = r.getType();
            if(type.equals("Take out"))
                icon.setImageResource(R.drawable.icon_t);
            else if(type.equals("Sit down"))
                icon.setImageResource(R.drawable.icon_s);
            else
                icon.setImageResource(R.drawable.icon_d);
            return row;
        }
    }
}