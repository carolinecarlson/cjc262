package cjc39.cs262.calvin.edu.homework02_3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;

public class QueryLoader extends AsyncTaskLoader<String> {

    String queryString;

    public QueryLoader(@NonNull Context context, String str) {
        super(context);
        queryString = str;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetworkUtils.getPlayerInfo(queryString);

    }
}
