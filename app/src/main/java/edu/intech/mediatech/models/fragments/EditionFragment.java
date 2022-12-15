package edu.intech.mediatech.models.fragments;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import edu.intech.mediatech.databinding.FragmentEditionBinding;

public class EditionFragment extends Fragment {

    FragmentEditionBinding binding;

    int idSession = 1;

    String facebookMessage;
    String twitterMessage;
    String instagramMessage;
    String linkedinMessage;

    String facebookMedia;
    String twitterMedia;
    String instagramMedia;
    String linkedinMedia;

    Date facebookDate;
    Date twitterDate;
    Date instagramDate;
    Date linkedinDate;

    public EditionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditionBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnBack.setVisibility(View.INVISIBLE);
        binding.rsText.setText("Facebook");


        binding.btnBack.setOnClickListener(v -> {
            if (idSession == 2) {
                if (facebookMessage != null) {
                    binding.editionBodyText.setText(facebookMessage);
                    binding.editionMediaText.setText(facebookMedia);
                    binding.editionDateText.setText(facebookDate.toString());
                }
                binding.rsText.setText("Facebook");
                binding.btnBack.setVisibility(View.INVISIBLE);
                idSession--;
            } else if (idSession == 3) {
                if (instagramMessage != null) {
                    binding.editionBodyText.setText(instagramMessage);
                    binding.editionMediaText.setText(instagramMedia);
                    binding.editionDateText.setText(instagramDate.toString());
                }
                binding.rsText.setText("Instagram");
                idSession--;
            } else if (idSession == 4) {
                if (twitterMessage != null) {
                    binding.editionBodyText.setText(twitterMessage);
                    binding.editionMediaText.setText(twitterMedia);
                    binding.editionDateText.setText(twitterDate.toString());
                }
                binding.rsText.setText("Twitter");
                idSession--;
            }
        });

        binding.btnPass.setOnClickListener(v -> {
            if (idSession == 1) {
                facebookMessage = facebookMedia = null;
                facebookDate = null;
                binding.editionBodyText.setText("");
                binding.editionMediaText.setText("");
                binding.editionDateText.setText("");
                binding.rsText.setText("Instagram");
                binding.btnBack.setVisibility(View.VISIBLE);
                idSession++;
            } else if (idSession == 2) {
                instagramMessage = instagramMedia = null;
                instagramDate = null;
                binding.editionBodyText.setText("");
                binding.editionMediaText.setText("");
                binding.editionDateText.setText("");
                binding.rsText.setText("Twitter");
                idSession++;
            } else if (idSession == 3) {
                twitterMessage = twitterMedia = null;
                twitterDate = null;
                binding.editionBodyText.setText("");
                binding.editionMediaText.setText("");
                binding.editionDateText.setText("");
                binding.rsText.setText("Linkedin");
                idSession++;
            } else if (idSession == 4) {
                linkedinMessage = linkedinMedia = null;
                linkedinDate = null;
                binding.editionBodyText.setText("");
                binding.editionMediaText.setText("");
                binding.editionDateText.setText("");
            }
        });

        binding.btnSuivant.setOnClickListener(v -> {
            if (idSession == 1) {
                facebookMessage = binding.editionBodyText.getText().toString();
                facebookMedia = binding.editionMediaText.getText().toString();
                facebookDate = new Date();
                binding.editionBodyText.setText("");
                binding.editionMediaText.setText("");
                binding.editionDateText.setText("");
                binding.rsText.setText("Instagram");
                binding.btnBack.setVisibility(View.VISIBLE);
                idSession++;
            } else if (idSession == 2) {
                instagramMessage = binding.editionBodyText.getText().toString();
                instagramMedia = binding.editionMediaText.getText().toString();
                instagramDate = new Date();
                binding.editionBodyText.setText("");
                binding.editionMediaText.setText("");
                binding.editionDateText.setText("");
                binding.rsText.setText("Twitter");
                idSession++;
            } else if (idSession == 3) {
                twitterMessage = binding.editionBodyText.getText().toString();
                twitterMedia = binding.editionMediaText.getText().toString();
                twitterDate = new Date();
                binding.editionBodyText.setText("");
                binding.editionMediaText.setText("");
                binding.editionDateText.setText("");
                binding.rsText.setText("Linkedin");
                idSession++;
            } else if (idSession == 4) {
                linkedinMessage = binding.editionBodyText.getText().toString();
                linkedinMedia = binding.editionMediaText.getText().toString();
                linkedinDate = new Date();
                binding.editionBodyText.setText("");
                binding.editionMediaText.setText("");
                binding.editionDateText.setText("");
                binding.rsText.setText("Facebook");
                binding.btnBack.setVisibility(View.INVISIBLE);
                idSession = 1;
            }
        });

        binding.editionCalendarBtn.setOnClickListener(v -> {
            DatePickerFragment datePickerFragment = new DatePickerFragment(binding.editionDateText);
            datePickerFragment.show(getChildFragmentManager(), "datePicker");
        });
    }

}