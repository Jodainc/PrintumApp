package com.kotan.printum.Ui.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kotan.printum.EventBus.OnItemClickEvent;
import com.kotan.printum.Model.DataModel;
import com.kotan.printum.R;
import com.kotan.printum.Ui.Activity.UserDetail;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolderData> {
    public LayoutInflater layoutInflater;
    public Context mContext;
    public ArrayList<DataModel> PrintumData;
    public SearchAdapter(Context mContext, ArrayList<DataModel> printumData) {
        layoutInflater = LayoutInflater.from(mContext);
        this.mContext = mContext;
        this.PrintumData = printumData;
    }
    @Override
    public ViewHolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.single_search_row, parent, false);
        ViewHolderData viewHolderData = new ViewHolderData(view);
        return viewHolderData;
    }
    @Override
    public void onBindViewHolder(final ViewHolderData holder, final int position) {
        Glide.with(mContext)
                .load(PrintumData.get(position).getPhoto_medium())
                .override(120, 120)
                .centerCrop()
                .crossFade(30)
                .placeholder(R.drawable.test_profile)
                .error(R.drawable.test_profile)
                .into(holder.mImageView);
        holder.mUserName.setText(PrintumData.get(position).getUser_name());
        String age = String.valueOf(PrintumData.get(position).getAge());
        String city = PrintumData.get(position).getLocation().getmCityName();
        String stateCode = PrintumData.get(position).getLocation().getmStateCode();
        holder.mLocation.setText(age + "-" + city + "," + stateCode);
        String match = Integer.parseInt(PrintumData.get(position).getMatch()) / 100 + "%";
        holder.mMatch.setText(match);
        holder.mMatchText.setText("Match");
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context, UserDetail.class);

                DataModel mDataModel = PrintumData.get(position);
                Bundle bundle = new Bundle();
                bundle.putParcelable("Printum_detail_detail", mDataModel);

                ActivityOptionsCompat options = ActivityOptionsCompat.
                        makeSceneTransitionAnimation((Activity) context,
                                holder.mImageView,
                                "profileImage");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    context.startActivity(intent, options.toBundle());
                } else {
                    context.startActivity(intent);
                }
                EventBus.getDefault().postSticky(new OnItemClickEvent(bundle));
            }
        });
    }
    @Override
    public int getItemCount() {
        return PrintumData.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {
        public final View mView;

        @Bind(R.id.profileImageView)
        ImageView mImageView;

        @Bind(R.id.LocationTextView)
        TextView mLocation;

        @Bind(R.id.MatchTextView)
        TextView mMatch;

        @Bind(R.id.UserNameTextView)
        TextView mUserName;

        @Bind(R.id.MatchText)
        TextView mMatchText;
        public ViewHolderData(View itemView) {
            super(itemView);
            mView = itemView;
            ButterKnife.bind(this, itemView);
        }
    }
}
