package com.carpark.views.homefragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.carpark.Model.review.ParkingHistoryModel;
import com.carpark.R;
import com.carpark.adapter.ParkingHistoryAdapter;

import java.util.ArrayList;
import java.util.List;

public class ParkingHistoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private ParkingHistoryAdapter parkingHistoryAdapter;
    private List<ParkingHistoryModel> parkingHistory;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_parking_history, container, false);

        //FOR TESTING PURPOSES
        parkingHistory = new ArrayList<>();
        /*parkingHistory.add(new ParkingHistoryModel("2018-01-20", "10:00AM", "Ikeja City Mall Section 1 Lane 20", "SZEK0932","500"));
        parkingHistory.add(new ParkingHistoryModel("2018-06-10", "09:12PM", "Section 5 Lane 5", "SEJK0965","200"));
        parkingHistory.add(new ParkingHistoryModel("2018-09-02", "03:20PM", "Silverbird Galleria Section 3 Lane 12", "SXEQ2313","1000"));
        parkingHistory.add(new ParkingHistoryModel("2019-01-01", "06:40AM", "Palms Shopping MallSection 4 Lane 1", "SARO0079", "650"));
        parkingHistory.add(new ParkingHistoryModel("2019-03-25", "01:30AM", "University of Lagos Section 20 Lane 3", "SWER4829","350"));
        parkingHistory.add(new ParkingHistoryModel("2019-06-15", "12:00AM", "National Theatre Section 11 Lane 1", "STUY1212", "500"));
*/
        recyclerView = root.findViewById(R.id.recyclerView);
        parkingHistoryAdapter = new ParkingHistoryAdapter(this.getContext(), parkingHistory);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getContext());

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(parkingHistoryAdapter);

        return root;
    }
}
