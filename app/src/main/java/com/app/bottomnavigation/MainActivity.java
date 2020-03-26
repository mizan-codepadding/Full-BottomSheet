package com.app.bottomnavigation;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity  extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterPeopleLeft adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tools.setSystemBarColor(this,R.color.colorPrimary);
        initComponent();
        Toast.makeText(this, "Swipe up bottom sheet", Toast.LENGTH_SHORT).show();
    }

    private void initComponent() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //set data and list adapter
        adapter = new AdapterPeopleLeft(this, getPeopleData(this));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new AdapterPeopleLeft.OnItemClickListener() {
            @Override
            public void onItemClick(View view, People obj, int pos) {
                FragmentBottomSheetDialogFull fragment = new FragmentBottomSheetDialogFull();
                fragment.setPeople(obj);
                fragment.show(getSupportFragmentManager(), fragment.getTag());
            }
        });

        // display first sheet
//        FragmentBottomSheetDialogFull fragment = new FragmentBottomSheetDialogFull();
//        fragment.setPeople(adapter.getItem(0));
//        fragment.show(getSupportFragmentManager(), fragment.getTag());
    }






    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else {
            Toast.makeText(getApplicationContext(), item.getTitle(), Toast.LENGTH_SHORT).show();
        }
        return super.onOptionsItemSelected(item);
    }

    public static List<People> getPeopleData(Context ctx) {
        List<People> items = new ArrayList<>();
        TypedArray drw_arr = ctx.getResources().obtainTypedArray(R.array.people_images);
        String name_arr[] = ctx.getResources().getStringArray(R.array.people_names);

        for (int i = 0; i < drw_arr.length(); i++) {
            People obj = new People();
            obj.image = drw_arr.getResourceId(i, -1);
            obj.name = name_arr[i];
            obj.email = Tools.getEmailFromName(obj.name);
            obj.imageDrw = ctx.getResources().getDrawable(obj.image);
            items.add(obj);
        }
        Collections.shuffle(items);
        return items;
    }



}
