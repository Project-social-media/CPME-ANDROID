package edu.intech.mediatech.models;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

import edu.intech.mediatech.R;
import edu.intech.mediatech.databinding.FragmentPostCreationBinding;

public class PostCreationFragment extends Fragment {

    FragmentPostCreationBinding binding;

    public PostCreationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }



    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPostCreationBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.fgtBackBtn.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_postCreationFragment_to_dashboardFragment);
        });

        binding.fgtPostValidateBtn.setOnClickListener(v -> {
            String body = Objects.requireNonNull(binding.fgtPostcrtBodyInput.getText()).toString();
            String media = binding.fgtPostcrtMediaLink.getText().toString();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault());
            Date date = null;

            try {
                date = sdf.parse(binding.fgtPostcrtDate.getText().toString());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            boolean facebook = binding.frgPostcrtChipFacebook.isChecked();
            boolean instagram = binding.frgPostcrtChipInstagram.isChecked();
            boolean linkedin = binding.frgPostcrtChipLinkedin.isChecked();
            boolean twitter = binding.frgPostcrtChipTwitter.isChecked();

            Post post = new Post(body, Collections.singletonList(media), date, facebook, instagram, linkedin, twitter);

            binding.fgtPostcrtBodyInput.setText(post.toString());

            binding.fgtBtnDateDialog.setOnClickListener(v1 -> {
                Log.d("TAG", "onViewCreated: TESSSSSSSSSSSSSSSSSSSSSSSSSSSSSST");
                DatePickerFragment datePickerFragment = new DatePickerFragment(binding.fgtPostcrtDate);
                datePickerFragment.show(getChildFragmentManager(), "datePicker");

            });

        });


    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

        private final EditText et;

        public DatePickerFragment(EditText et){
            this.et = et;
        }

        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
            dialog.getDatePicker().setMaxDate(c.getTimeInMillis());
            return  dialog;
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(year, month, day);
            et.setText(c.toString());
        }
    }

}