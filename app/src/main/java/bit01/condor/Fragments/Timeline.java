package bit01.condor.Fragments;
/*
 * Created by miguelost on 27-01-15.
 */

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.melnykov.fab.FloatingActionButton;

import bit01.condor.Activities.ComposeTweet;
import bit01.condor.R;

public class Timeline extends Fragment {
    FloatingActionButton buttonCompose;
    RelativeLayout layoutTimeline;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_timeline, container, false);
        layoutTimeline = (RelativeLayout) rootView.findViewById(R.id.timeline_frame);
        buttonCompose = (FloatingActionButton) rootView.findViewById(R.id.timeline_compose);
        buttonCompose.setOnClickListener(new composeTweetButtom());
        return rootView;
    }

    private class composeTweetButtom implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent composeTweet = new Intent(getActivity(), ComposeTweet.class);
            getActivity().startActivity(composeTweet);
        }
    }


}
