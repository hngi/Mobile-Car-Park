package com.carpark.views.homefragments;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.carpark.R;
import com.carpark.views.JavaMailAPI;

public class SupportFragment extends Fragment {

    public TextView mEmail;
    public EditText mSubject;
    public EditText mMessage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View root =  inflater.inflate(R.layout.fragment_support, container, false);

        mEmail = root.findViewById(R.id.mailID);
        mMessage = root.findViewById(R.id.messageID);
        mSubject = root.findViewById(R.id.subjectID);

        Button fab = root.findViewById(R.id.btn_send);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });

        getActivity().setTitle("Support");

        return root;
    }

    private void sendMail() {

        String mail = mEmail.getText().toString().trim();
        String message = mMessage.getText().toString();
        String subject = mSubject.getText().toString().trim();

        //Send Mail
        JavaMailAPI javaMailAPI = new JavaMailAPI(getContext(),mail,subject,message);

        javaMailAPI.execute();

    }


}
