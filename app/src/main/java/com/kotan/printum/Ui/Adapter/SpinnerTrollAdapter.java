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
        import com.kotan.printum.Model.TrollToken;
        import com.kotan.printum.R;
public class SpinnerTrollAdapter extends BaseAdapter {
    public static final String TAG = "SpinnerTrollAdapter";

    private List<TrollToken> mItems;
    private LayoutInflater mInflater;

    public SpinnerTrollAdapter(Context context, List<TrollToken> listCompanies) {
        this.setItems(listCompanies);
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().size() : 0 ;
    }

    @Override
    public TrollToken getItem(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position) : null ;
    }

    @Override
    public long getItemId(int position) {
        return (getItems() != null && !getItems().isEmpty()) ? getItems().get(position).getmID() : position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        ViewHolder holder;
        if(v == null) {
            v = mInflater.inflate(R.layout.spinner_item_troller, parent, false);
            holder = new ViewHolder();
            holder.txtCompanyName = (TextView) v.findViewById(R.id.txt_company_name);
            holder.txtWebsite = (TextView) v.findViewById(R.id.txt_website);
            v.setTag(holder);
        }
        else {
            holder = (ViewHolder) v.getTag();
        }
        // fill row data
        TrollToken currentItem = getItem(position);
        if(currentItem != null) {
            holder.txtCompanyName.setText(currentItem.getUsername());
            holder.txtWebsite.setText(currentItem.getTrollTok());
        }
        return v;

    }
    public List<TrollToken> getItems() {
        return mItems;
    }
    public void setItems(List<TrollToken> mItems) {
        this.mItems = mItems;
    }
    class ViewHolder {
        TextView txtCompanyName;
        TextView txtWebsite;
    }
}
