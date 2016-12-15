package com.kotan.printum.Ui.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kotan.printum.Model.Users;
import com.kotan.printum.R;
import com.kotan.printum.Model.CertiModel;

import java.util.List;

/**
 * Created by Kotan@JoyDainc on 06/12/2016.
 */

public class CertiAdapter extends BaseAdapter {

    private List<CertiModel> certiModelList;
    private LayoutInflater mInflater;

    public CertiAdapter(Context context1,List<CertiModel> list){
        this.mInflater = LayoutInflater.from(context1);
        this.certiModelList =list;
    }
    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public CertiModel getItem(int i) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(i) : null ;
    }

    @Override
    public long getItemId(int i) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(i).getId() : i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = view;
        ViewHolder holder;
        if(v == null) {
            v = mInflater.inflate(R.layout.list_item_emloyee, viewGroup, false);
            holder = new ViewHolder();
            holder.txtEmployeeName = (TextView) v.findViewById(R.id.txt_employee_name);
            holder.txtCompanyName = (TextView) v.findViewById(R.id.txt_company_name);
            holder.txtAddress = (TextView) v.findViewById(R.id.txt_address);
            holder.txtPhoneNumber = (TextView) v.findViewById(R.id.txt_phone_number);
            holder.txtEmail = (TextView) v.findViewById(R.id.txt_email);
            holder.txtSalary = (TextView) v.findViewById(R.id.txt_salary);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }
        // fill row data
        CertiModel currentItem = getItem(i);
        if(currentItem != null) {
            holder.txtEmployeeName.setText(currentItem.getC8Codigo());
            holder.txtAddress.setText(currentItem.getC8epp());
            holder.txtEmail.setText(currentItem.getC8mASiNFO());
            holder.txtPhoneNumber.setText(currentItem.getC8pROTECCION());
            holder.txtSalary.setText("");
            holder.txtCompanyName.setText("");
        }
        return v;
    }
    public List<CertiModel> getItems() {
        return certiModelList;
    }
    public void setItems(List<CertiModel> mItems) {
        this.certiModelList = mItems;
    }
    class ViewHolder {
        TextView txtEmployeeName;
        TextView txtEmail;
        TextView txtPhoneNumber;
        TextView txtAddress;
        TextView txtCompanyName;
        TextView txtSalary;
    }

}
