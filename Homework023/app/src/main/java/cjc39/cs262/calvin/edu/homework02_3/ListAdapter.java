package cjc39.cs262.calvin.edu.homework02_3;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

/**
 * package-private class ListAdapter
 * an extension of ArrayAdapter that implements the ListView
 * taken partly from https://appsandbiscuits.com/listview-tutorial-android-12-ccef4ead27cc
 * @author  Caroline Carlson
 * @version 1.0
 * @since   2018-18-11
 */
class ListAdapter extends ArrayAdapter {
    private final Activity context;

    private final Player[] playerArray;

    public ListAdapter (Activity context, Player... playerArray ) {
        super(context, R.layout.listview_main , playerArray);
        this.context = context;
        this.playerArray = playerArray;
    }

    @NonNull
    public View getView(int idx, View view, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        @SuppressLint({"ViewHolder", "InflateParams"}) View rowView = inflater.inflate(R.layout.listview_main, null,true);

        TextView idTv = rowView.findViewById(R.id.id);
        TextView emailTv = rowView.findViewById(R.id.email);
        TextView nameTv = rowView.findViewById(R.id.name);

        idTv.setText( String.valueOf( playerArray[idx].id) );
        emailTv.setText(playerArray[idx].email);
        nameTv.setText(playerArray[idx].name);

        return rowView;
    }
}