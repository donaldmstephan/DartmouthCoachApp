package com.dartmouth.cs.dartmouthcoach;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class ViewTicketActivity extends AppCompatActivity {
    private TicketDBHelper db;
    private long value = 0;
    private TicketEntry t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_ticket);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getLong("POSITION");
        }

        db = new TicketDBHelper(this);
        t = db.fetchEntryByIndex(value);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_return){
            new AlertDialog.Builder(this)
                    .setTitle("Notification")
                    .setMessage("Do you want to return the tickets?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            db.removeEntry(value);
                            Toast.makeText(getApplicationContext(), "Ticket has been returned", Toast.LENGTH_LONG).show();
                            finish();
                        }})
                    .setNegativeButton(android.R.string.no, null).show();

        }

        if(item.getItemId() == R.id.action_alarm) {
            new AlertDialog.Builder(this)
                    .setTitle("Notification")
                    .setMessage("Do you want to set an alarm for one hour before " + t.getDepartureTime() + "?")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            Toast.makeText(getApplicationContext(), "Alarm set", Toast.LENGTH_LONG).show();
                        }})
                    .setNegativeButton(android.R.string.no, null).show();
        }

        if(item.getItemId() == R.id.action_share) {
            ShareDialog cdd = new ShareDialog(this);
            cdd.show();
        }

        if(item.getItemId() == R.id.action_find_station) {
            startActivity(new Intent(getApplicationContext(), AirportLocationActivity.class));
        }

        return false;
    }

    public Bitmap takeScreenshot() {
        View rootView = findViewById(android.R.id.content).getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        File imagePath = new File(Environment.getExternalStorageDirectory() + "/screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Log.e("GREC", e.getMessage(), e);
        } catch (IOException e) {
            Log.e("GREC", e.getMessage(), e);
        }
    }
}
