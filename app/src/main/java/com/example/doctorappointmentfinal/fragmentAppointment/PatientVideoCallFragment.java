package com.example.doctorappointmentfinal.fragmentAppointment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.doctorappointmentfinal.MainActivityPatient;
import com.example.doctorappointmentfinal.R;
import com.google.android.material.card.MaterialCardView;

import org.jitsi.meet.sdk.JitsiMeetActivity;
import org.jitsi.meet.sdk.JitsiMeetConferenceOptions;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PatientVideoCallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PatientVideoCallFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PatientVideoCallFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PatientVideoCallFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PatientVideoCallFragment newInstance(String param1, String param2) {
        PatientVideoCallFragment fragment = new PatientVideoCallFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_patient_video_call, container, false);
        ImageView iconVideoCall=(ImageView) view.findViewById(R.id.iconVideoCall);
        MaterialCardView videoCallToMainPage=(MaterialCardView) view.findViewById(R.id.videoCallToMainPage);
        final boolean[] clickedCall = {false};
        if (clickedCall[0] ==false) videoCallToMainPage.setVisibility(View.GONE);
        iconVideoCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickedCall[0] =true;
                iconVideoCall.setVisibility(View.GONE);
                videoCallToMainPage.setVisibility(View.VISIBLE);
                /*String valueCall = getArguments().getString("messageFromCallToPay");
                Bundle bundle2 = new Bundle();
                bundle2.putString("messageFromPayCallToVideoCall", valueCall);*/
                try {
                    JitsiMeetConferenceOptions options = new JitsiMeetConferenceOptions.Builder()
                            .setServerURL(new URL("https://meet.jit.si"))
                            .setRoom("xxx")
                            .setAudioMuted(false)
                            .setVideoMuted(false)
                            .setAudioOnly(false)
                            .setConfigOverride("requireDisplayName", true)
                            .build();
                    JitsiMeetActivity.launch(getActivity(), options);
                    //finish();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
            }
        });

        videoCallToMainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), MainActivityPatient.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
