package cjc39.cs262.calvin.edu.homework02_3;

import android.app.Activity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ArrayAdapter;
        import android.widget.TextView;

//taken partly from https://appsandbiscuits.com/listview-tutorial-android-12-ccef4ead27cc

public class ListAdapter extends ArrayAdapter {
    private final Activity context;

    private final Player[] playerArray;

    public ListAdapter( Activity context, Player[] playerArray ) {
        super(context, R.layout.listview_main , playerArray);
        this.context = context;
        this.playerArray = playerArray;
    }

    public View getView(int idx, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listview_main, null,true);

        TextView idTv = (TextView) rowView.findViewById(R.id.id);
        TextView emailTv = (TextView) rowView.findViewById(R.id.email);
        TextView nameTv = (TextView) rowView.findViewById(R.id.name);

        if ( rowView != null ) {
            idTv.setText( String.valueOf( playerArray[idx].id) );
            emailTv.setText(playerArray[idx].email);
            nameTv.setText(playerArray[idx].name);
        }

        return rowView;

    };

}