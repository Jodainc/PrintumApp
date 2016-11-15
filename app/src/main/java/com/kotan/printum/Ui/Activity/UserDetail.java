package com.kotan.printum.Ui.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import de.greenrobot.event.EventBus;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.kotan.printum.EventBus.OnItemClickEvent;
import  com.kotan.printum.Model.DataModel;
import  com.kotan.printum.R;
import com.kotan.printum.Ui.Fragments.SearchFragment;

import de.greenrobot.event.EventBus;

public class UserDetail extends AppCompatActivity {
    private DataModel dataModel;
    private String LOG_TAG = UserDetail.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_user_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.content_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Register to the bus
        EventBus.getDefault().registerSticky(this);

        // LoadBackdrop to the detail card View
        loadBackdrop();

        try  {
            CollapsingToolbarLayout collapsingToolbar =
                    (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            collapsingToolbar.setTitle(dataModel.getUser_name());
        }catch (Exception E){
            Log.d(LOG_TAG, "Error Message 2" + E.toString());
        }
        // Setting the Collapsing ToolBar


        // Floating Button with SnackBar
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.detail_fab_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    // onEvent Receive the Event
    public void onEventMainThread(OnItemClickEvent event) {
        dataModel = (DataModel) event.bundle.get("cupid_detail");
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void loadBackdrop() {
        try  {
            final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
            Glide.with(UserDetail.this).load(dataModel.getPhoto_large())
                    .crossFade()
                    .centerCrop()
                    .placeholder(R.drawable.test_profile)
                    .error(R.drawable.test_profile)
                    .into(imageView);
        }catch (Exception E){
            Log.d(LOG_TAG, "Error Message" + E.toString());
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
}
