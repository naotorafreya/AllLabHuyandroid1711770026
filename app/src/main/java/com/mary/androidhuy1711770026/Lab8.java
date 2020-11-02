package com.mary.androidhuy1711770026;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
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
import android.widget.TabHost;
import android.app.TabActivity;
import android.widget.AdapterView;
import android.database.Cursor;


public class Lab8 extends TabActivity {
    RestaurantAdapter adapter = null;
    Cursor curRestaurant = null;
    RestaurantHelper helper = null;
    //private List<Restaurant> listRestaurant = new ArrayList<Restaurant>();

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            curRestaurant.moveToPosition(position);

            EditText name;
            EditText address;
            RadioGroup types;

            name = (EditText)findViewById(R.id.name);
            address = (EditText)findViewById(R.id.addr);
            types = (RadioGroup)findViewById(R.id.types);

            name.setText(helper.getName(curRestaurant));
            address.setText(helper.getAddress(curRestaurant));
            if (helper.getType(curRestaurant).equals("Sit down"))
                types.check(R.id.sit_down);
            else if (helper.getType(curRestaurant).equals("Take out"))
                types.check(R.id.take_out);
            else
                types.check(R.id.delivery);
            getTabHost().setCurrentTab(1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab8);
        helper = new RestaurantHelper(this);

        Button save = (Button)findViewById(R.id.save);
        save.setOnClickListener(onSave);

        ListView list = (ListView)findViewById(R.id.restaurants);
        list.setOnItemClickListener(onItemClickListener);


        curRestaurant = helper.getAll();
        startManagingCursor(curRestaurant);
        adapter = new RestaurantAdapter(curRestaurant);
        list.setAdapter(adapter);

        TabHost.TabSpec spec = getTabHost().newTabSpec("tag1");
        spec.setContent(R.id.restaurants);
        spec.setIndicator("List",getResources().getDrawable(R.drawable.list));
        getTabHost().addTab(spec);
        spec = getTabHost().newTabSpec("tag2");
        spec.setContent(R.id.details);
        spec.setIndicator("Details", getResources().getDrawable(R.drawable.restaurant));
        getTabHost().addTab(spec);
        getTabHost().setCurrentTab(0);
    }

    protected void onDestroy() {
        super.onDestroy();
        helper.close();
    }

    private View.OnClickListener onSave = new View.OnClickListener() {
        public void onClick(View v) {
            Restaurant restaurant = new Restaurant();

            EditText name = (EditText)findViewById(R.id.name);
            EditText address = (EditText)findViewById(R.id.addr);

            restaurant.setName(name.getText().toString());
            restaurant.setAddress(address.getText().toString());

            RadioGroup type = (RadioGroup)findViewById(R.id.types);

            switch (type.getCheckedRadioButtonId())
            {
                case R.id.take_out:
                    restaurant.setType("Take out");
                    Toast.makeText(v.getContext(), name.getText().toString() + " " + address.getText().toString() + " " + "Take out" ,Toast.LENGTH_SHORT).show();
                    break;
                case R.id.sit_down:
                    restaurant.setType("Sit down");
                    Toast.makeText(v.getContext(), name.getText().toString() + " " + address.getText().toString() + " " + "Sit down" ,Toast.LENGTH_SHORT).show();
                    break;
                case R.id.delivery:
                    restaurant.setType("Delivery");
                    Toast.makeText(v.getContext(), name.getText().toString() + " " + address.getText().toString() + " " + "Delivery" ,Toast.LENGTH_SHORT).show();
                    break;
            }
            //restaurantList.add(r);
            //listRestaurant.add(restaurant);
            helper.insert(restaurant.getName(), restaurant.getAddress(), restaurant.getType());
            curRestaurant.requery();
        }
    };

    class RestaurantAdapter extends CursorAdapter
    {
        public RestaurantAdapter(Cursor c)
        {
            super(Lab8.this, c);
        }
        public RestaurantAdapter(Context context, Cursor c) {
            super(context, c);
        }
        @Override
        public void bindView(View view, Context context, Cursor cursor) {
            View row = view;
            ((TextView)row.findViewById(R.id.title)).setText(helper.getName(cursor));
            ((TextView)row.findViewById(R.id.address)).setText(helper.getAddress(cursor));
            ImageView icon = (ImageView)row.findViewById(R.id.icon);
            String type = helper.getType(cursor);
            if (type.equals("Take out"))
                icon.setImageResource(R.drawable.icon_t);
            else if (type.equals("Sit down"))
                icon.setImageResource(R.drawable.icon_s);
            else
                icon.setImageResource(R.drawable.icon_d);
        }
        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = getLayoutInflater();
            View row = inflater.inflate(R.layout.row, parent, false);
            return row;
        }
    }
}