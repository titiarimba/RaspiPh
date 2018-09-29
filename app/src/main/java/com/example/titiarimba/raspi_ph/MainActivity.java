package com.example.titiarimba.raspi_ph;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    TextView txtNilai, txtDate, txtTime;
    Button cekdata;
    List<Ph> arrayPh = new ArrayList<Ph>();

    //get reference database
    private DatabaseReference mDatabase;

    RecyclerView recyclerView;

    //adapter
    PhAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup Recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        cekdata = (Button)findViewById(R.id.btn_cekdata);
        txtNilai = (TextView)findViewById(R.id.tv_ph);
        txtDate = (TextView)findViewById(R.id.tv_date);
        txtTime = (TextView)findViewById(R.id.tv_time);

        //get firebase reference
        mDatabase = FirebaseDatabase.getInstance().getReference();

        cekdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("Fakultas Teknik").child("ph").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
//                        int i = 0;
                        arrayPh.clear();
                        for (DataSnapshot childSnapshot : dataSnapshot.getChildren()){
                            Ph ph = childSnapshot.getValue(Ph.class);
//                            String date = ph.getDate().toString();
//                            String time = ph.getTime().toString();
//                            String nilai = ph.getNilai().toString();
//                            Log.i("Date:",""+date);
//                            Log.i("Time:",""+time);
//                            Log.i("Nilai:",""+nilai);
                            arrayPh.add(ph);
//                            i++;
                        }
//                        Log.i("Length Ph", String.valueOf(i));
//                        Log.i("Length array", String.valueOf(arrayPh.size()));

                        //tampilkan data array dari terbaru
                        Collections.reverse(arrayPh);
                        List<Ph> newArray = new ArrayList<Ph>();
                        for(int i=0; i < 10; i++){
                            newArray.add(arrayPh.get(i));
                        }
                        adapter = new PhAdapter(newArray, MainActivity.this);
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}