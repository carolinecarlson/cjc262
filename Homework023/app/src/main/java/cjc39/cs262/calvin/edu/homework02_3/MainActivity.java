package cjc39.cs262.calvin.edu.homework02_3;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<String> {

    Boolean EmptySearch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onFetchBtnPressed(View view) {
        EditText et = findViewById(R.id.editText);
        String queryString = et.getText().toString();

        if ( queryString.isEmpty()  ) {
            queryString = "players/";
            EmptySearch = true;
        } else if ( Integer.parseInt(queryString) >= 0) {
            queryString = "player/" + queryString;
            EmptySearch = false;
        } else {
            Toast.makeText(this, R.string.errorMessage,
              Toast.LENGTH_LONG).show();
        }

        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                InputMethodManager.HIDE_NOT_ALWAYS);

        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected() ) {
            Bundle queryBundle = new Bundle();
            queryBundle.putString("queryString", queryString);
            getSupportLoaderManager().restartLoader(0, queryBundle,this);
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new QueryLoader(this, bundle.getString("queryString"));
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String JsonString) {
        Player[] playersArray;

        if (EmptySearch) {
            playersArray = new Player[3];

            try {
                JSONObject jObject = new JSONObject(JsonString);
                JSONArray jArray = jObject.getJSONArray("items");
                for( int i = 0; i<jArray.length(); i++ ){
                    JSONObject player = jArray.getJSONObject(i);
                    playersArray[i] = new Player();
                    setAttributes(player, playersArray, i);
                }
            } catch (Exception e){
                e.printStackTrace();
            }

        } else {
            playersArray = new Player[1];
            playersArray[0] = new Player();
            JSONObject player = null;

            try {
                player = new JSONObject(JsonString);
            } catch (JSONException e) {
                e.printStackTrace(); }
            setAttributes(player, playersArray, 0);
        }

        for (Player p : playersArray) {
            if (p.id == -1) {
                Toast.makeText(this, R.string.errorMessage,
                        Toast.LENGTH_LONG).show();
                return;
            }
        }

        ListAdapter adapter = new ListAdapter(this, playersArray);
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
    }

    public void setAttributes(JSONObject player, Player[] playersArray, Integer i) {
        try{
            if( player.has("id") ) {
                playersArray[i].setId( player.getInt("id") );
            }
            if( player.has("emailAddress") ) {
                playersArray[i].setEmail( player.getString("emailAddress") );
            }
            if( player.has("name") ) {
                playersArray[i].setName( player.getString("name") );
            } else {
                playersArray[i].setName( "no name" );
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
    }
}
