package com.example.login;

import static android.content.ContentValues.TAG;
import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;

import java.time.Year;
import java.util.Calendar;

public class Account extends AppCompatActivity implements View.OnClickListener{
    ImageView home_icon, add_icon, attendance_icon, account_icon;
    TextView txtHome, txtAdd, txtAttendance, txtAccount;
    //For Add new disciple
//    EditText edittxtName, editTxtBDate, editTxtAddress, editTxtLeadersName, editTxtNetwork;
    DatePickerDialog.OnDateSetListener mDateSetListener;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        //For the Bottom icon in theMenu Item
        home_icon = findViewById(R.id.home_icon);
        add_icon = findViewById(R.id.add_icon);
        attendance_icon = findViewById(R.id.attendance_icon);
        account_icon = findViewById(R.id.account_icon);

        home_icon.setOnClickListener(this);
        add_icon.setOnClickListener(this);
        attendance_icon.setOnClickListener(this);
        account_icon.setOnClickListener(this);

        //For Label of the icon in the menu item
        txtHome = findViewById(R.id.txtHome);
        txtAdd = findViewById(R.id.txtAdd);
        txtAttendance = findViewById(R.id.txtAttendance);
        txtAccount = findViewById(R.id.txtAccount);

        //For the Add Disciple section
//        edittxtName = findViewById(R.id.edittxtName);
//        editTxtBDate = findViewById(R.id.editTxtBDate);
//        editTxtAddress = findViewById(R.id.editTxtAddress);
//        editTxtLeadersName = findViewById(R.id.editTxtLeadersName);
//        editTxtNetwork = findViewById(R.id.editTxtNetwork);
        accessFragments(new homeFragment());
    }

    public void onClick(View v){
        if(v.getId() == R.id.home_icon || v.getId() == R.id.add_icon || v.getId() == R.id.attendance_icon || v.getId() == R.id.account_icon )
            changeColorIcon(v);
    }



    public void changeColorIcon(View v){
        //To set icon to black color
        home_icon.setColorFilter(getResources().getColor(R.color.black));
        add_icon.setColorFilter(getResources().getColor(R.color.black));
        attendance_icon.setColorFilter(getResources().getColor(R.color.black));
        account_icon.setColorFilter(getResources().getColor(R.color.black));
        //To set text to black color
        txtHome.setTextColor(getResources().getColor(R.color.black));
        txtAdd.setTextColor(getResources().getColor(R.color.black));
        txtAttendance.setTextColor(getResources().getColor(R.color.black));
        txtAccount.setTextColor(getResources().getColor(R.color.black));

        if(v.getId() == R.id.home_icon) {
            home_icon.setColorFilter(getResources().getColor(R.color.icon_color));
            txtHome.setTextColor(getResources().getColor(R.color.icon_color));
            accessFragments(new homeFragment());
        }else if (v.getId() == R.id.add_icon) {
            add_icon.setColorFilter(getResources().getColor(R.color.icon_color));
            txtAdd.setTextColor(getResources().getColor(R.color.icon_color));
            accessFragments(new addDiscipleFragment());
        }else if (v.getId() == R.id.attendance_icon) {
            attendance_icon.setColorFilter(getResources().getColor(R.color.icon_color));
            txtAttendance.setTextColor(getResources().getColor(R.color.icon_color));
            accessFragments(new attendanceFragment());
        }else {
            account_icon.setColorFilter(getResources().getColor(R.color.icon_color));
            txtAccount.setTextColor(getResources().getColor(R.color.icon_color));
            accessFragments(new accountFragment());
        }
    }
    //To change fragments
    public void accessFragments(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, fragment);
        fragmentTransaction.commit();
    }
}

