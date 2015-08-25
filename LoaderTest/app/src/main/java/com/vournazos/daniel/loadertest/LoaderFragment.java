package com.vournazos.daniel.loadertest;

import android.app.ProgressDialog;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dvournazos on 8/24/15.
 */
public class LoaderFragment extends Fragment implements LoaderManager.LoaderCallbacks{

    private static final String LOADER_RUNNING = "loader_running";
    private static final String TAG = LoaderFragment.class.getSimpleName();
    private ProgressDialog dialog;
    private boolean isRunning;
    private AsyncTaskLoader loader;

    public LoaderFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Oncreate");
        dialog = new ProgressDialog(getActivity());
        dialog.setCanceledOnTouchOutside(false);

        isRunning = false;
        if(savedInstanceState != null && savedInstanceState.getBoolean(LOADER_RUNNING))
        {
            dialog.setMessage("Test Async");
            dialog.show();
            isRunning = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "Start the loader");
        loader = (AsyncTaskLoader) getLoaderManager().initLoader(0, null, this);
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onLoadFinished(Loader loader, Object data) {
        if(dialog != null && dialog.isShowing())
        {
            dialog.dismiss();
        }
        isRunning = false;
        Log.d(TAG, "onLoadFinished");
    }

    @Override
    public void onLoaderReset(Loader loader) {
        Log.d(TAG, "onLoaderReset");
    }

    @Override
    public android.support.v4.content.Loader onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "onCreateLoader");
        dialog.setMessage("Test Async");
        dialog.show();
        isRunning = true;
        return new LoaderTask(getActivity());
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        //so we don't leak
        if(dialog != null && dialog.isShowing())
        {
            dialog.dismiss();
        }

        if(isRunning)
        {
            savedInstanceState.putBoolean(LOADER_RUNNING, true);
        }
        else
        {
            savedInstanceState.putBoolean(LOADER_RUNNING, false);
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        Log.d(TAG, "Detached");
    }
}
