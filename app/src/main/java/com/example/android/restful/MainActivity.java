package com.example.android.restful;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        output = (TextView) findViewById(R.id.output);
    }

    public void runClickHandler(View view) {
        //This will initialise the loader manager class and move the flow to callback function onCreateloader
        getSupportLoaderManager().restartLoader(0,null,this).forceLoad();

    }

    public void clearClickHandler(View view) {
        output.setText("");
    }

    @Override
    public Loader<String> onCreateLoader(int id, Bundle args) {
        output.append("Creating the Loader\n");
        //This creates an instance of MytaskLoader and performs the  task
        return new MyTaskLoader(this);
    }

    @Override
    public void onLoadFinished(Loader<String> loader, String data) {
        output.append("Loader finished, returned : " + data +"\n");


    }

    @Override
    public void onLoaderReset(Loader<String> loader) {

    }

    //Make sure to extend one that is in your support library com.android.supoort.v4.. or else erorrrrsss
    private static class MyTaskLoader extends android.support.v4.content.AsyncTaskLoader<String>{

        public MyTaskLoader(Context context) {
            super(context);
        }

        @Override
        public String loadInBackground() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "From the loader";
        }

        @Override
        public void deliverResult(String data) {
            data += ", delivered";
            super.deliverResult(data);
        }
    }
}
