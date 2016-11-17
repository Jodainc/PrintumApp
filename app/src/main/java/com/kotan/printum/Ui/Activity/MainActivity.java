package com.kotan.printum.Ui.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.kotan.printum.Model.Users;
import com.kotan.printum.Network.RestService;
import com.kotan.printum.R;
import com.kotan.printum.Ui.Adapter.TabViewAdapter;
import com.kotan.printum.Ui.Fragments.BookMarkFragment;
import com.kotan.printum.Ui.Fragments.NearbyFragment;
import com.kotan.printum.Ui.Fragments.SearchFragment;
import java.io.InputStream;
import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.Callback;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private String LOG_TAG = MainActivity.class.getSimpleName();
    private Fragment mSearchFragment = new SearchFragment();
    private Fragment mNearbyFragment = new NearbyFragment();
    private Fragment mBookMarkFragment = new BookMarkFragment();
    private RestService restService;
    private String userName = " ";
    private String urlImage = " ";
    private int compaId = 0;
    @Bind(R.id.toolbar)
    Toolbar mToolbar;
    @Bind(R.id.viewpager)
    ViewPager mViewPager;
    @Bind(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    @Bind(R.id.nav_view)
    NavigationView navigationView;
    @Bind(R.id.tabs)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        restService = new RestService();
        // Bind all of the view
        ButterKnife.bind(this);

        // Set Toolbar
        setSupportActionBar(mToolbar);

        // Set Snack bar Listener
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        final View hView =  navigationView.inflateHeaderView(R.layout.nav_header_main);
        final TextView tv = (TextView)hView.findViewById(R.id.TittleCompa);
        final TextView tv1 = (TextView)hView.findViewById(R.id.TittleEmail);
        //final ImageView tv2 = (ImageView) hView.findViewById(R.id.UserPho);

        restService.getService().getUser(1, new Callback<Users>() {
            @Override
            public void success(Users users, Response response) {
                userName = users.UserName;compaId = users.CompanyId;urlImage = users.UserPhoto;
                urlImage = urlImage.substring(1);
                urlImage = "http://192.168.0.98:8080"+urlImage;
                Log.d("Apii",urlImage);
                new DownloadImageTask((ImageView) hView.findViewById(R.id.UserPho))
                        .execute(urlImage);
                //tv2.setImageResource();
                tv.setText(""+userName);
                tv1.setText("Printum");
            }
            @Override
            public void failure(RetrofitError error) {
                //Toast.makeText(null, error.getMessage().toString(), Toast.LENGTH_LONG).show();
            }});

        setupViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
    }
    private void setupViewPager() {
        if (mViewPager != null) {
            TabViewAdapter mTabViewAdapter = new TabViewAdapter(getSupportFragmentManager());
            mTabViewAdapter.addFragment(mSearchFragment, "Descuentos");
            mTabViewAdapter.addFragment(mNearbyFragment, "Productos");
            mTabViewAdapter.addFragment(mBookMarkFragment, "Certificados");
            mViewPager.setAdapter(mTabViewAdapter);
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_activity) {
            // Handle the camera action
            callToSnackBar("Descuentos");

        } else if (id == R.id.nav_matches) {
            callToSnackBar("Productos");
        } else if (id == R.id.nav_quick_match) {
            callToSnackBar("Certificados");
        } else if (id == R.id.nav_messages) {
            callToSnackBar("Hojas de seguridad");
        } else if (id == R.id.nav_visitors) {
            callToSnackBar("EquipoPrintum");
        } else if (id == R.id.nav_likes) {
            callToSnackBar("Conocenos");
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void callToSnackBar(String message) {
        Toast.makeText(this, message + " clicked!", Toast.LENGTH_SHORT).show();
    }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            bmImage.setMinimumWidth(50);
            bmImage.setMinimumHeight(50);
            bmImage.setImageBitmap(result);

        }
    }
}
