package com.example.carpark.views.homefragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.carpark.R;

public class ParkingHistoryFragment extends Fragment {

    Button btnRebook;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_parking_history, container, false);


        btnRebook = root.findViewById ( R.id.btn_rebook );

        btnRebook.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View v) {
                Toast.makeText ( getActivity().getApplicationContext(), "Thanks for booking" , Toast.LENGTH_SHORT ).show ( );
            }
        } );

        return root;
    }
}
