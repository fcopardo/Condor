package bit01.condor.Utils;
/*
 * Created by miguelost on 27-01-15.
 */

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import bit01.condor.R;

public class NavBarAdapter extends RecyclerView.Adapter<NavBarAdapter.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private String mNavTitles[];
    private int mIcons[];

    private String nameTwitter;
    private String userTwitter;
    private String avatarTwitterUrl;

    public NavBarAdapter(String Titles[], int Icons[], String nameTwitter, String userTwitter, String avatarUrl) {
        this.mNavTitles = Titles;
        this.mIcons = Icons;
        this.nameTwitter = nameTwitter;
        this.userTwitter = userTwitter;
        this.avatarTwitterUrl = avatarUrl;
    }

    @Override
    public NavBarAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
            return new ViewHolder(v, viewType);
        } else if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.header, parent, false);
            return new ViewHolder(v, viewType);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(NavBarAdapter.ViewHolder holder, int position) {
        if (holder.Holderid == 1) {
            holder.textView.setText(mNavTitles[position - 1]);
            holder.imageView.setImageResource(mIcons[position - 1]);
        } else {
            //holder.twitterAvatar.setImageResource(avatarTwitterUrl); TODO: replace for picasso
            holder.twitterName.setText(nameTwitter);
            holder.twitterUser.setText(userTwitter);
        }
    }

    @Override
    public int getItemCount() {
        return mNavTitles.length + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position))
            return TYPE_HEADER;

        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        int Holderid;
        TextView textView;
        ImageView imageView;
        ImageView twitterAvatar;
        TextView twitterName;
        TextView twitterUser;

        public ViewHolder(View itemView, int ViewType) {
            super(itemView);

            if (ViewType == TYPE_ITEM) {
                textView = (TextView) itemView.findViewById(R.id.rowText);
                imageView = (ImageView) itemView.findViewById(R.id.rowIcon);
                Holderid = 1;
            } else {
                twitterName = (TextView) itemView.findViewById(R.id.twitterName);
                twitterUser = (TextView) itemView.findViewById(R.id.twitterUser);
                twitterAvatar = (ImageView) itemView.findViewById(R.id.userImageView);
                Holderid = 0;
            }
        }
    }
}
