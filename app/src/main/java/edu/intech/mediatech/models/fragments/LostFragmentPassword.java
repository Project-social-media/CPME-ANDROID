package edu.intech.mediatech.models.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Objects;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import edu.intech.mediatech.R;
import edu.intech.mediatech.databinding.FragmentConnexionBinding;
import edu.intech.mediatech.databinding.FragmentLostPasswordBinding;
import kotlin.text.Regex;


public class LostFragmentPassword extends Fragment {

    FragmentLostPasswordBinding binding;
    Regex regex = new Regex("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");
    public LostFragmentPassword() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLostPasswordBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            //your codes here

        }


        binding.fgtPwdBackBtn.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });
// Create a new Properties object
        Properties props = new Properties();

// Set the SMTP server for the email
        props.put("mail.smtp.host", "smtp.office365.com");

// Set the port for the email
        props.put("mail.smtp.port", "587");

// Set the security protocol for the email
        props.put("mail.smtp.starttls.enable", "true");

// Set the authentication type for the email
        props.put("mail.smtp.auth", "true");

// Create a new Session object
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        // Set your email address and password
                        return new PasswordAuthentication("docnahel@outlook.fr", "E!s57EqP53qbPmEDWL9q");
                    }
                });

        binding.fgtPwdValidateBtn.setOnClickListener(v -> {
            if (regex.matches(binding.fgtPwdEmailBox.getText().toString())) {

                try {
                    // Create a new Message object
                    Message message = new MimeMessage(session);

                    // Set the sender and recipient for the email
                    message.setFrom(new InternetAddress("mediatechuwu@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(binding.fgtPwdEmailBox.getText().toString()));

                    // Set the subject and body for the email
                    message.setSubject("Test");
                    message.setText("UwU");

                    // Send the email
                    Transport.send(message);
                    Log.d("Mail", "Mail SENT !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! ");
                } catch (MessagingException e) {
                    e.printStackTrace();
                }

                Toast.makeText(getContext(), "Email envoyé", Toast.LENGTH_SHORT).show();
                Navigation.findNavController(v).navigate(R.id.action_lostFragmentPassword_to_connexionFragment);

            } else {
                Toast.makeText(getContext(), "Email invalide", Toast.LENGTH_SHORT).show();
                Log.d("DEBUG", binding.fgtPwdEmailBox.getText().toString());
            }
        });
    }
}