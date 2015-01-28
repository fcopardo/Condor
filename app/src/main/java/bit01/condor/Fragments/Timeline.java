package bit01.condor.Fragments;
/*
 * Created by miguelost on 27-01-15.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.melnykov.fab.FloatingActionButton;

import bit01.condor.R;

public class Timeline extends Fragment {
    FloatingActionButton buttonCompose;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_timeline, container, false);
        buttonCompose = (FloatingActionButton) rootView.findViewById(R.id.timeline_compose);
        buttonCompose.setOnClickListener(new composeTweetButtom());
        return rootView;
    }

    private class composeTweetButtom implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Log.d("Condor", "ComposeButtom");
        }
    }
}
