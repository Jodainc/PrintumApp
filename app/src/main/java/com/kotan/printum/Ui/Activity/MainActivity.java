package com.kotan.printum.Ui.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.print.PrintHelper;
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
import com.kotan.printum.Ui.Dao.DaoTroller;
import com.kotan.printum.Ui.Dao.DbHelper;
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
    private DaoTroller dao1;
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
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        dao1 = new DaoTroller(this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Envie su mensaje", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(getApplicationContext(), Webview.class);
                intent.putExtra("EXTRA_SESSION_ID", "http://www.printum-uv.com/contactenos");
                startActivity(intent);
            }
        });
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        final View hView = navigationView.inflateHeaderView(R.layout.nav_header_main);
        final TextView tv = (TextView) hView.findViewById(R.id.TittleCompa);
        final TextView tv1 = (TextView) hView.findViewById(R.id.TittleEmail);
        String tocken1 = "Bearer";
        String tocken2 ="HwlTHUt-Fn3Em_rXgf6HCgX19ItiQioOWAUcIkEILraHi6O5bDHD57siFhPVWv7ofD_UzmA5pvF4Rwn6WKOV1gDSwSB5ERG-d5D6gty2WG1dT7J7t4h2IzJ4m5a6V_6Q7QnHmJbqzjoKSrTgS4UR0ddTv5xrxpQOxAlSPlT_CnDdTFo-4w1pPfTF7ubLe_HRowCbHsMYJ5hRwI-9PjYKk6jGTo-HaMJkMB8SK7zV_6rJG6pe4Sc-2XXWLgucxO5WdMZt7uQnagP1fmtsgYT3oqcmf4AJoq4BgzrAa8YQa0Muh_9x7uz8JJ1iz5SpPhK2pgiGvEzXbYiaaS18aO08Ds1lbOAdFbAGjVRcpsbfpH5fSm0lyd07037NR0vvulZ9ALoAuGT1Wo5EPjeFIsBoiRkffLr268_uH2IdJWslwZIZ7ZVr1S_4lJujMH0TdmIquvHTcbEmO70S4s8LxKvhfFZQ2j4nmn0Z7ZCmvC2p1oc8R5b-Bnmov133i3Hwu-P8zBxGTlWmwPylTt6N6iEK6NRcv5RCW4X_oe5ufMYyjZ8";
        StringBuffer strBuf = new StringBuffer();
        strBuf.append(tocken1);
        strBuf.append(" ");
        strBuf.append(tocken2);
        restService.getService().getUser(strBuf.toString(),1, new Callback<Users>() {
            @Override
            public void success(Users users, Response response) {
                userName = users.UserName;compaId = users.CompanyId;urlImage = users.UserPhoto;
                urlImage = urlImage.substring(1);
                urlImage = "http://192.168.0.98:8080"+urlImage;
                Log.d("Apii",urlImage);
                new DownloadImageTask((ImageView) hView.findViewById(R.id.UserPho))
                   .execute(urlImage);
                tv.setText(""+userName);
                tv1.setText("Printum");
            }
            @Override
            public void failure(RetrofitError error) {
            }});

        setupViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
    }
    private void setupViewPager() {
        if (mViewPager != null) {
            TabViewAdapter mTabViewAdapter = new TabViewAdapter(getSupportFragmentManager());
            mTabViewAdapter.addFragment(mSearchFragment, "Descuentos");
            mTabViewAdapter.addFragment(mNearbyFragment, "Productos");
            //mTabViewAdapter.addFragment(mBookMarkFragment, "Certificados");
            mViewPager.setAdapter(mTabViewAdapter);
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            //super.onBackPressed();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.action_Log){
         dao1.removeAll();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_activity) {
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_matches) {
            Intent intent = new Intent(getApplicationContext(),Hsej.class);
            startActivity(intent);
        } else if (id == R.id.nav_quick_match) {
            Intent intent = new Intent(getApplicationContext(),Certica.class);
            startActivity(intent);
        } else if (id == R.id.nav_messages) {
            Intent intent = new Intent(getApplicationContext(),FichaTec.class);
            startActivity(intent);
        } else if (id == R.id.nav_visitors) {
            Intent intent = new Intent(getApplicationContext(), Webview.class);
            intent.putExtra("EXTRA_SESSION_ID", "http://www.printum-uv.com/");
            startActivity(intent);
        } else if (id == R.id.nav_likes) {
            Intent intent = new Intent(getApplicationContext(), Webview.class);
            intent.putExtra("EXTRA_SESSION_ID", "http://www.printum-uv.com/contactenos");
            startActivity(intent);

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
