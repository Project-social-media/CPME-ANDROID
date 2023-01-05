package edu.intech.mediatech.models.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public   class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private final EditText et;

    public DatePickerFragment(EditText et) {
        this.et = et;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        Locale.setDefault(Locale.FRANCE);
        DatePickerDialog dialog = new DatePickerDialog(getActivity(), this, year, month, day);
        dialog.getDatePicker().setMinDate(c.getTimeInMillis());
        return dialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        // In the onDateSet() method, create a TimePickerDialog and set a listener to be notified when the time is set.
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), (view1, hourOfDay, minute) -> {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("EEEE d MMMM yyyy, 'à' hh:mm", Locale.FRANCE);
            c.set(Calendar.HOUR_OF_DAY, hourOfDay);
            c.set(Calendar.MINUTE, minute);
            c.set(year, month, day);
            et.setText(format.format(c.getTime()));
        }, 0, 0, false);

        // Show the TimePickerDialog.
        timePickerDialog.show();
    }

}
