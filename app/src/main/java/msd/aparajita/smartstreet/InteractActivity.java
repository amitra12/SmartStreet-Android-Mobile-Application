package msd.aparajita.smartstreet;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Switch;

import com.firebase.client.Firebase;

import java.util.Map;

import msd.com.smartstreet.R;

public class InteractActivity extends AppCompatActivity
{


    public static final int COLOR_RED   = 0;
    public static final int COLOR_BLUE  = 1;
    public static final int COLOR_GREEN = 2;




    private long lastUpdate = System.currentTimeMillis();
    private boolean pHueOn = false;

    Switch light = null;
    private Map<String, Object> mUserData;

    /* References to the Firebase */
    private Firebase mFirebaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interact);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
