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
import android.widget.TabHost;
import android.app.TabActivity;
import android.widget.AdapterView;


public class Lab7 extends TabActivity {
    RestaurantAdapter adapter = null;
    List<Restaurant> listRestaurant = new ArrayList<Restaurant>();

    private AdapterView.OnItemClickListener onListClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Restaurant r = listRestaurant.get(position); // lấy item được chọn
            EditText name;
            EditText address;
            RadioGroup types;
// Tham chiếu đến các view trong details
            name = (EditText)findViewById(R.id.name);
            address = (EditText)findViewById(R.id.addr);
            types = (RadioGroup)findViewById(R.id.types);
// thiết lập thông tin tương ứng
            name.setText(r.getName());
            address.setText(r.getAddress());
            if (r.getType().equals("Sit down"))
                types.check(R.id.sit_down);
            else if (r.getType().equals("Take out"))
                types.check(R.id.take_out);
            else
                types.check(R.id.delivery);
// sinh viên có thể bổ sung lệnh sau để chuyển view về tab details
            getTabHost().setCurrentTab(1);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab7);

        Button save = (Button)findViewById(R.id.save); // tham chiếu đến Button
        save.setOnClickListener(onSave); // khai báo listener cho Button

        ListView list = (ListView)findViewById(R.id.restaurants);
        adapter = new RestaurantAdapter();
        list.setAdapter(adapter);
        list.setOnItemClickListener(onListClick);

        TabHost.TabSpec spec = getTabHost().newTabSpec("tag1");
        spec.setContent(R.id.restaurants);
        spec.setIndicator("List",getResources().getDrawable(R.drawable.list));
        getTabHost().addTab(spec);
        spec = getTabHost().newTabSpec("tag2");
        spec.setContent(R.id.details);
        spec.setIndicator("Details",
                getResources().getDrawable(R.drawable.restaurant));
        getTabHost().addTab(spec);
        getTabHost().setCurrentTab(0);
    }



    private View.OnClickListener onSave = new View.OnClickListener() {
        public void onClick(View v) {
            Restaurant r = new Restaurant();

            EditText name = (EditText)findViewById(R.id.name);
            EditText address = (EditText)findViewById(R.id.addr);

            r.setName(name.getText().toString());
            r.setAddress(address.getText().toString());
            RadioGroup type = (RadioGroup)findViewById(R.id.types);


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
            super(Lab7.this,android.R.layout.simple_list_item_1,listRestaurant);
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