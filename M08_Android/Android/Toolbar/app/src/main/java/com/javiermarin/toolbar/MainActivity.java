package com.javiermarin.toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar myToolbar;
    private Button showToolbar;
    private Button hideToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToolbar = findViewById(R.id.myToolbar);
        myToolbar.setTitle("Hola");
        setSupportActionBar(myToolbar);

        showToolbar = findViewById(R.id.showToolbar);
        showToolbar.setOnClickListener(v -> toggleToolbarVisibility("show"));

        hideToolbar = findViewById(R.id.hideToolbar);
        hideToolbar.setOnClickListener(v -> toggleToolbarVisibility("hide"));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                Toast.makeText(this, "You have selected Favorite", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "You have selected Settings", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.openMap:
                // Create a Uri from an intent string. Use the result to create an Intent.
                Uri gmmIntentUri = Uri.parse("https://goo.gl/maps/7YFkGDXn4qToC5Y59");

                // Create an Intent from gmmIntentUri. Set the action to ACTION_VIEW
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                // Make the Intent explicit by setting the Google Maps package
                mapIntent.setPackage("com.google.android.apps.maps ²");

                // Attempt to start an activity that can handle the Intent
                startActivity(mapIntent);

                return true;

            case R.id.openPhone:
                Uri phoneUri = Uri.parse("tel:123456789");
                Intent phoneIntent = new Intent(Intent.ACTION_DIAL, phoneUri);
                startActivity(phoneIntent);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    private void toggleToolbarVisibility(String action) {
        if (action.equals("show")) {
            getSupportActionBar().show();
        } else {
            getSupportActionBar().hide();
        }
    }
}