package com.deepak.pgfinderapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    ArrayList<String> arrayList1;
    MyAdapter myAdapter;
    ArrayList<Integer> rent = new ArrayList<Integer>();
    ArrayList<String> area = new ArrayList<String>();
    ArrayList<String> pgname = new ArrayList<String>();
    ArrayList<String> contact = new ArrayList<String>();
    ArrayList<String> desc = new ArrayList<String>();

    PGDatabase pg;

    public SearchPG() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search_pg, container, false);
        listView1 = (ListView) view.findViewById(R.id.listView1);
        arrayList1 = new ArrayList<String>();
        myAdapter = new MyAdapter();
        listView1.setAdapter(myAdapter);

        Toast.makeText(getActivity(), "SearchPage", Toast.LENGTH_SHORT).show();

        Bundle b = getArguments();
        arrayList1 = b.getStringArrayList("data");
        //arrayList1 = pgdata;
        for(String item : arrayList1){
            String[] parts = item.split("/");
            rent.add(Integer.parseInt(parts[0]));
            area.add(parts[1]);
            pgname.add(parts[2]);
            contact.add(parts[3]);
            desc.add(parts[4]);
        }

        return view;
    }

    private class MyAdapter extends BaseAdapter{
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
            int rent2 = rent.get(position);
            String area2 = area.get(position);
            String pgname2 = pgname.get(position);
            String contact2 = contact.get(position);
            String desc2 = desc.get(position);

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
            rent1.setText("\u20B9 "+rent2);
            area1.setText(area2);
            pgName1.setText(pgname2);
            contact1.setText(contact2);
            desc1.setText(desc2);

            return v;
        }
    }
}
