package com.example.sdl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Emergency extends AppCompatActivity {
    ListView mListView;
    int[] images = {R.drawable.hello,
            R.drawable.newpolice,
            R.drawable.firefighter,
            R.drawable.natural_disaster,R.drawable.pipeline};
    String[] Names = {"1234567890", "2325212457", "3421457724", "1241789211","4528284029"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency);
        mListView = (ListView) findViewById(R.id.EmergencyListView);
        final CustomAdapter customAdapter = new CustomAdapter();
        mListView.setAdapter(customAdapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(Emergency.this,"contact :"+Names[position],Toast.LENGTH_SHORT).show();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                String phoneNo = "tel:" + Names[position];
                final int REQUEST_PHONE_CALL = 1;
                callIntent.setData(Uri.parse(phoneNo));
                if (ActivityCompat.checkSelfPermission(Emergency.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Emergency.this, new String[]{Manifest.permission.CALL_PHONE},REQUEST_PHONE_CALL);
                }
                startActivity(callIntent);
            }
        });

    }
    class CustomAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=getLayoutInflater().inflate(R.layout.customlayout,null);
            ImageView mImageView=(ImageView) view.findViewById(R.id.photo);
            TextView mTextView=(TextView)view.findViewById(R.id.contact);
            mImageView.setImageResource(images[position]);
            mTextView.setText(Names[position]);
            return view;
        }
    }
}
