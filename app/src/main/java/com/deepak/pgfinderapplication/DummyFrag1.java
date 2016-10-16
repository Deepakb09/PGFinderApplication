package com.deepak.pgfinderapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class DummyFrag1 extends Fragment {
    EditText ed1, ed2, ed3, ed4, ed5;
    Button b1, b2;

    PGDatabase pg;


    public DummyFrag1() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dummy_frag1, container, false);
        ed1 = (EditText) v.findViewById(R.id.rentText);
        ed2 = (EditText) v.findViewById(R.id.areaText);
        ed3 = (EditText) v.findViewById(R.id.pgNameText);
        ed4 = (EditText) v.findViewById(R.id.contactText);
        ed5 = (EditText) v.findViewById(R.id.descText);
        b1 = (Button) v.findViewById(R.id.insert);
        b2 = (Button) v.findViewById(R.id.goButton);

        pg = new PGDatabase(getActivity());

        pg.open();

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int rent = Integer.parseInt(ed1.getText().toString());
                String area = ed2.getText().toString();
                String pgname = ed3.getText().toString();
                String contact = ed4.getText().toString();
                String desc = ed5.getText().toString();

                pg.insertPgDetails(rent, area, pgname, contact, desc);

            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<String> data = pg.readPGdetails();
                Toast.makeText(getActivity(), "Frag1", Toast.LENGTH_SHORT).show();
                SearchPG searchPG = new SearchPG();
                Bundle b = new Bundle();
                b.putStringArrayList("data", data);
                searchPG.setArguments(b);
                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = manager.beginTransaction();
                fragmentTransaction.replace(R.id.container1, searchPG);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        return v;
    }

}
