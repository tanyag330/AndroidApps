package com.example.tanya.myfragment;

import android.app.Fragment;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;


/**
 * Created by Admin on 6/23/2016.
 */
public class SecondFragment  extends Fragment {
        @Override
        public View onCreateView (LayoutInflater inflater , ViewGroup container ,
                                  Bundle savedInstanceState )  {
            return inflater.inflate(R.layout.fragment_second,container,false);
        }
}
