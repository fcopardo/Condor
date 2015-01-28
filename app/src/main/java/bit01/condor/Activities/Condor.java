package bit01.condor.Activities;
/*
 * Created by miguelost on 25-01-15.
 */

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import bit01.condor.Fragments.Timeline;
import bit01.condor.R;
import bit01.condor.Utils.NavBarAdapter;
import bit01.condor.Utils.RecyclerItemClickListener;

public class Condor extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condor);

        String twitterName = "Miguel A. Zapata";
        String twitterUser = "@Miguelost";            //or other secondary text (email?)
        String twitterAvatarUrl = "";

        String[] mDrawerTitles = getResources().getStringArray(R.array.navigation_drawer_items);
        int mDrawerIcons[] = new int[1];
        mDrawerIcons[0] = (R.drawable.ic_reorder);      //Icon For Timeline

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);

        final DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        final ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                mToolbar,
                R.string.drawer_open,
                R.string.drawer_close) {

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.RecyclerViewDrawer);
        RecyclerView.Adapter mAdapter = new NavBarAdapter(mDrawerTitles, mDrawerIcons, twitterName, twitterUser, twitterAvatarUrl);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Fragment fragment = null;
                        switch (position) {
                            case 0:
                                break;
                            case 1:
                                fragment = new Timeline();
                                break;
                            default:
                                break;
                        }

                        if (fragment != null) {
                            FragmentManager fragmentManager = getFragmentManager();
                            fragmentManager.beginTransaction().replace(R.id.FrameContainer, fragment).commit();
                            mDrawerLayout.closeDrawers();
                        }
                    }
                })
        );
        mRecyclerView.setLayoutManager(mLayoutManager);
    }
}
