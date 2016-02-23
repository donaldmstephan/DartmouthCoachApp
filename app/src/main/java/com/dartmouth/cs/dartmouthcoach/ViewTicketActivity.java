package com.dartmouth.cs.dartmouthcoach;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

import java.util.Calendar;

public class ViewTicketActivity extends AppCompatActivity {
    private TicketDBHelper db;
    private long value = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ticket);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getLong("POSITION");
        }

        db = new TicketDBHelper(this);
        TicketEntry t = db.fetchEntryByIndex(value);

        setTitle("My Tickets");

        TextView tv = (TextView) findViewById(R.id.departure_text);
        tv.setText(t.getDepartureLocation() + " To " + t.getArrivalLocation());

        tv = (TextView) findViewById(R.id.time_text);
        tv.setText("Depart: " + t.getDepartureTime() + "  |  Arrive: " + t.getArrivalTime().toString() + "  |  " + String.valueOf(t.getDateTime().get(Calendar.MONTH) + 1) + "/" + t.getDateTime().get(Calendar.DATE) + "/" + t.getDateTime().get(Calendar.YEAR));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.ticket_menu, menu);
        return true;
    }
}
