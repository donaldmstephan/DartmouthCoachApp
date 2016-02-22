package com.dartmouth.cs.dartmouthcoach;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Donald on 2/18/2016.
 */

public class MyTicketFragment extends Fragment {
    private View view;
    private TicketDBHelper db;
    private ListView lv;
    public ArrayList<String> fromColumns;
    public List<TicketEntry> dbEntries;
    private ArrayAdapter<String> mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_my_tickets, container, false);

        loadList();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        loadList();
    }

    public void loadList() {

        db = new TicketDBHelper(getActivity());
        lv = (ListView)view.findViewById(R.id.List_View);
        fromColumns = new ArrayList<>();

        Loader loader = new Loader(this.getActivity());
        loader.loadInBackground();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                //add in the clicker
            }
        });
    }

    public class Loader extends AsyncTaskLoader {

        public Loader(Context context) {
            super(context);
        }

        @Override
        public Object loadInBackground() {
            dbEntries = db.fetchEntries();
            fromColumns = new ArrayList<>();

            for ( TicketEntry t : dbEntries) {
                fromColumns.add( "\n" + t.getDepartureLocation().toString() + " To " + t.getArrivalLocation().toString() + " \n" + "Depart: " + t.getDepartureTime() + "  |  Arrive: " + t.getArrivalTime().toString() + "  |  " + String.valueOf(t.getDateTime().get(Calendar.MONTH) + 1) + "/" + t.getDateTime().get(Calendar.DATE) + "/" + t.getDateTime().get(Calendar.YEAR) + "\n");
            }

            mAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, fromColumns);
            lv.setAdapter(mAdapter);
            return mAdapter;
        }
    }
}
