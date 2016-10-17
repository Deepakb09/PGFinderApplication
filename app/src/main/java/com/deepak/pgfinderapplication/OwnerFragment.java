package com.deepak.pgfinderapplication;


import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class OwnerFragment extends Fragment {
    EditText editText1,editText2,editText3,editText4, editText5, editText6;
    TextView textView1,textView2,textView3;
    Button button1;
    Spinner spinner1;
    String[] city = {"Attibele","Bommanahalli","BTM","Banaswadi","Banashankri","Hoskote","Hosur","Bommasandra","Begur","Electronic City"};
    String[] gender = {"Male", "Female", "Any"};
    ArrayAdapter<String> arrayAdapter, arrayAdapter2;

    RadioGroup radioGroup1, radioGroup2;
    RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    String food, negotiable;

    AutoCompleteTextView autoCompleteTextView;

    String gender1, pgcity;
    int[] image = {R.drawable.house};

    PGDatabase pgDatabase;
    Cursor cursor;

    public OwnerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View v = inflater.inflate(R.layout.fragment_owner, container, false);
        editText1 = (EditText) v.findViewById(R.id.editText1);
        editText2 = (EditText) v.findViewById(R.id.editText2);
        editText3 = (EditText) v.findViewById(R.id.editText3);
        editText4 = (EditText) v.findViewById(R.id.editText4);
        editText5 = (EditText) v.findViewById(R.id.editText5);
        editText6 = (EditText) v.findViewById(R.id.editText6);

        textView1 = (TextView) v.findViewById(R.id.textView1);
        textView3 = (TextView) v.findViewById(R.id.textView3);
        button1 = (Button) v.findViewById(R.id.button1);

        radioGroup1 = (RadioGroup) v.findViewById(R.id.radiogroup1);
        radioButton1 = (RadioButton) v.findViewById(R.id.veg);
        radioButton2 = (RadioButton) v.findViewById(R.id.nonveg);
        radioGroup2 = (RadioGroup) v.findViewById(R.id.radiogroup2);
        radioButton3 = (RadioButton) v.findViewById(R.id.yes);
        radioButton4 = (RadioButton) v.findViewById(R.id.no);
        //int selectedId = radioGroup1.getCheckedRadioButtonId();
        //radioButton1 = (RadioButton) v.findViewById(selectedId);

        spinner1 = (Spinner) v.findViewById(R.id.spinner1);

        arrayAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, gender);
        spinner1.setAdapter(arrayAdapter);

        arrayAdapter2 = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, city);
        autoCompleteTextView = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteTextView1);
        autoCompleteTextView.setAdapter(arrayAdapter2);

        arrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender1 = gender[position];
                //creating adapter for spinner
                //attaching adapter to spinner
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        radioGroup1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        negotiable = "Yes";
                    }
                });

                radioButton2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        negotiable = "No";
                    }
                });
            }
        });

        radioGroup2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioButton3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        food = "Veg";
                    }
                });

                radioButton4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        food = "Non-Veg";
                    }
                });
            }
        });

        pgDatabase = new PGDatabase(getActivity());
        pgDatabase.open();

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int date = calendar.get(Calendar.DATE);
                String dateOP = ""+month+"/"+date+"/"+year;

                String advertisername = editText1.getText().toString();
                String pgname = editText2.getText().toString();
                float contact = Float.parseFloat(editText3.getText().toString());
                String city = autoCompleteTextView.getText().toString();
                String area = editText4.getText().toString();
                int rent = Integer.parseInt(editText5.getText().toString());
                String negotiable2 = negotiable;
                String gender2 = gender1;
                String food2 = food;
                String desc = editText6.getText().toString();
                String dateofposting = dateOP;
                String latitude = "12.9855° N";
                String longitude = "77.6639° E";

                try {
                    pgDatabase.insertPgDetails(advertisername, pgname, contact, city, area, rent, negotiable2, gender2, food2, desc,
                            dateofposting, image[0], image[0], latitude, longitude);
                }catch (SQLException e){
                    e.printStackTrace();
                }
                editText1.setText("");editText2.setText("");editText3.setText("");editText4.setText("");
                editText5.setText("");editText6.setText("");
                editText1.requestFocus();
                radioGroup1.clearCheck();radioGroup2.clearCheck();

            }
        });

        return v;

    }

    /*public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.veg:
                if (checked) food = "Veg";
                // Pirates are the best
                break;
            case R.id.nonveg:
                if (checked) food = "Non-Veg";
                // Ninjas rule
                break;
        }
    }*/
}


