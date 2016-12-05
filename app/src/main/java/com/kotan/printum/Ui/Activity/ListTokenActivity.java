package com.kotan.printum.Ui.Activity;
        import java.util.ArrayList;
        import java.util.List;
        import android.app.Activity;
        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.view.View.OnClickListener;
        import android.widget.AdapterView;
        import android.widget.AdapterView.OnItemClickListener;
        import android.widget.AdapterView.OnItemLongClickListener;
        import android.widget.ImageButton;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;
        import com.kotan.printum.Model.TrollToken;
        import com.kotan.printum.Model.Users;
        import com.kotan.printum.R;
        import com.kotan.printum.Ui.Adapter.ListUserAdapter;
        import com.kotan.printum.Ui.Adapter.SpinnerTrollAdapter;
        import com.kotan.printum.Ui.Dao.DaoTroller;
        import com.kotan.printum.Ui.Dao.UserDao;
/**
 * Created by Kotan@JoyDainc on 23/11/2016.
 */

public class ListTokenActivity extends Activity implements OnItemLongClickListener, OnItemClickListener, OnClickListener {

    public static final String TAG = "ListCompaniesActivity";

    public static final int REQUEST_CODE_ADD_COMPANY = 40;
    public static final String EXTRA_ADDED_TROLLER = "extra_key_added_troller";

    private ListView mListviewCompanies;
    private TextView mTxtEmptyListCompanies;
    private ImageButton mBtnAddCompany;

    private SpinnerTrollAdapter mAdapter;
    private List<TrollToken> mListCompanies;
    private DaoTroller mCompanyDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_companies);
        // initialize views
        initViews();
        // fill the listView
        mCompanyDao = new DaoTroller(this);
        mListCompanies = mCompanyDao.getAllCompanies();
        if(mListCompanies != null && !mListCompanies.isEmpty()) {
            mAdapter = new SpinnerTrollAdapter(this, mListCompanies);
            mListviewCompanies.setAdapter(mAdapter);
        }
        else {
            mTxtEmptyListCompanies.setVisibility(View.VISIBLE);
            mListviewCompanies.setVisibility(View.GONE);
        }
    }

    private void initViews() {
        this.mListviewCompanies = (ListView) findViewById(R.id.list_companies);
        this.mTxtEmptyListCompanies = (TextView) findViewById(R.id.txt_empty_list_companies);
        this.mBtnAddCompany = (ImageButton) findViewById(R.id.btn_add_company);
        this.mListviewCompanies.setOnItemClickListener(this);
        this.mListviewCompanies.setOnItemLongClickListener(this);
        this.mBtnAddCompany.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add_company:
                //Intent intent = new Intent(this, AddCompanyActivity.class);
                //startActivityForResult(intent, REQUEST_CODE_ADD_COMPANY);
                break;

            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQUEST_CODE_ADD_COMPANY) {
            if(resultCode == RESULT_OK) {
                // add the added company to the listCompanies and refresh the listView
                if(data != null) {
                    TrollToken createdCompany = (TrollToken) data.getSerializableExtra(EXTRA_ADDED_TROLLER);
                    if(createdCompany != null) {
                        if(mListCompanies == null)
                            mListCompanies = new ArrayList<TrollToken>();
                        mListCompanies.add(createdCompany);

                        if(mAdapter == null) {
                            if(mListviewCompanies.getVisibility() != View.VISIBLE) {
                                mListviewCompanies.setVisibility(View.VISIBLE);
                                mTxtEmptyListCompanies.setVisibility(View.GONE);
                            }

                            mAdapter = new SpinnerTrollAdapter(this, mListCompanies);
                            mListviewCompanies.setAdapter(mAdapter);
                        }
                        else {
                            mAdapter.setItems(mListCompanies);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        }
        else
            super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompanyDao.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        TrollToken clickedCompany = mAdapter.getItem(position);
        Log.d(TAG, "clickedItem : "+clickedCompany.getUsername());
        Intent intent = new Intent(this, ListUserActivity.class);
        intent.putExtra( ListUserActivity.EXTRA_SELECTED_COMPANY_ID, clickedCompany.getmID());
        startActivity(intent);
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        TrollToken clickedCompany = mAdapter.getItem(position);

        /*
        TrollToken clickedCompany = mAdapter.getItem(position);
        Log.d(TAG, "longClickedItem : "+clickedCompany.getUsername());
        showDeleteDialogConfirmation(clickedCompany);
        */
        return true;
    }

    private void showDeleteDialogConfirmation(final TrollToken clickedCompany) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle("Delete");
        alertDialogBuilder.setMessage("Are you sure you want to delete the \""+clickedCompany.getUsername()+"\" company ?");

        // set positive button YES message
        alertDialogBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // delete the company and refresh the list
                if(mCompanyDao != null) {
                    mCompanyDao.deleteCompany(clickedCompany);
                    mListCompanies.remove(clickedCompany);

                    //refresh the listView
                    if(mListCompanies.isEmpty()) {
                        mListviewCompanies.setVisibility(View.GONE);
                        mTxtEmptyListCompanies.setVisibility(View.VISIBLE);
                    }
                    mAdapter.setItems(mListCompanies);
                    mAdapter.notifyDataSetChanged();
                }

                dialog.dismiss();
                Toast.makeText(ListTokenActivity.this, R.string.company_deleted_successfully, Toast.LENGTH_SHORT).show();
            }
        });

        // set neutral button OK
        alertDialogBuilder.setNeutralButton(android.R.string.no, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        // show alert
        alertDialog.show();
    }
}

