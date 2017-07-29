package com.example.tanya.myfragment;
import android.support.v4.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * Created by Admin on 6/23/2016.
 */
public class FirstFragment extends Fragment  {

    TextView fragName;
        @Override
        public View onCreateView (LayoutInflater inflater , ViewGroup container ,
                                  Bundle savedInstanceState )  {
            View rootView = inflater.inflate(R.layout.fragment_first,container,false);
            fragName = (TextView) rootView.findViewById(R.id.tv_fragment_name);
            fragName.setTextColor(Color.RED);
            return fragName;
        }

}
