package com.vournazos.daniel.loadertest;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

/**
 * Created by dvournazos on 8/24/15.
 */
public class LoaderTask extends AsyncTaskLoader<Void> {
    private static final String TAG = LoaderTask.class.getSimpleName();

    public LoaderTask(Context context)
    {
        super(context);
        Log.d(TAG,"LoadTask constructor");
    }

    @Override
    public Void loadInBackground() {
        Log.d(TAG, "loadInBackground");
        for(int i = 0; i < 10; i++)
        {
            Log.d(TAG, "Timer " + i);
            try {
                Thread.sleep(5000);
            }
            catch (InterruptedException e)
            {

            }
        }
        return null;
    }

    @Override
    public void deliverResult(Void nothing)
    {
        Log.d(TAG, "deliverResult");
        if(isReset())
        {
            Log.d(TAG, "isReset true");
        }

        if(isStarted())
        {
            Log.d(TAG, "isStarted true");
            super.deliverResult(null);
        }
    }

    @Override
    public void onStartLoading()
    {
        Log.d(TAG, "onStartLoading");
        forceLoad();
    }

    @Override
    protected void onStopLoading() {
        Log.d(TAG, "onStopLoading");
        cancelLoad();
    }

    @Override
    public void onCanceled(Void nothing)
    {
        super.onCanceled(nothing);
        Log.d(TAG, "onCanceled");
    }

    @Override
    protected void onAbandon() {
        Log.d(TAG, "onAbandon");
        super.onAbandon();
    }
}
