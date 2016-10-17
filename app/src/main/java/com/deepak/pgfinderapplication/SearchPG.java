package com.deepak.pgfinderapplication;


import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchPG extends Fragment {
    ListView listView1;
    Cursor cursor;
    SimpleCursorAdapter simpleCursorAdapter;
    PGDatabase pgDatabase;


    public SearchPG() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_pg, container, false);
        listView1 = (ListView) view.findViewById(R.id.listView1);

        pgDatabase = new PGDatabase(getActivity());
        pgDatabase.open();
        cursor = pgDatabase.queryPGDetails();
        simpleCursorAdapter = new SimpleCursorAdapter(getActivity(), R.layout.row, cursor,
                    new String[]{ "advertisername","pgcity", "pgname", "contact", "pgrent"},
                    new int[]{R.id.advertisername, R.id.contact, R.id.city, R.id.pgName, R.id.rent});
        listView1.setAdapter(simpleCursorAdapter);

        return view;
    }

    /*private class MyAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return arrayList1.size();
        }

        @Override
        public Object getItem(int position) {
            return arrayList1.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View v = getActivity().getLayoutInflater().inflate(R.layout.row, null);

            ImageView imageView = (ImageView) v.findViewById(R.id.imageView1);
            TextView rent1 = (TextView) v.findViewById(R.id.rent);
            TextView area1 = (TextView) v.findViewById(R.id.area);
            TextView pgName1 = (TextView) v.findViewById(R.id.pgName);
            ImageView conatactImage = (ImageView) v.findViewById(R.id.contactImage);
            TextView contact1 = (TextView) v.findViewById(R.id.contact);
            TextView desc1 = (TextView) v.findViewById(R.id.desc);

            desc1.setMovementMethod(new ScrollingMovementMethod());

            Toast.makeText(getActivity(), "Setting textView", Toast.LENGTH_SHORT).show();

            return v;
        }
    }*/
}
