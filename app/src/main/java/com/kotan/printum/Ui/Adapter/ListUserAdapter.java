package com.kotan.printum.Ui.Adapter;

/**
 * Created by Kotan@JoyDainc on 23/11/2016.
 */
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kotan.printum.Model.Users;
import com.kotan.printum.R;

public class ListUserAdapter  extends BaseAdapter {

    public static final String TAG = "ListEmployeesAdapter";

    private List<Users> mItems;
    private LayoutInflater mInflater;

    public ListUserAdapter(Context context, List<Users> listCompanies) {
        this.setItems(listCompanies);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public Users getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getUserId() : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewHolder holder;
        if(v == null) {
            v = mInflater.inflate(R.layout.list_item_emloyee, parent, false);
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
        Users currentItem = getItem(position);
        if(currentItem != null) {
            holder.txtEmployeeName.setText(currentItem.getFirstName()+" "+currentItem.getLastName());
            holder.txtAddress.setText(currentItem.getUserAddress());
            holder.txtEmail.setText(currentItem.getUserName());
            holder.txtPhoneNumber.setText(currentItem.getUserPhone());
            holder.txtSalary.setText(String.valueOf(currentItem.getTrollToken()));
            holder.txtCompanyName.setText(currentItem.getTrollToken().getPassword());
        }
        return v;
    }
    public List<Users> getItems() {
        return mItems;
    }
    public void setItems(List<Users> mItems) {
        this.mItems = mItems;
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
