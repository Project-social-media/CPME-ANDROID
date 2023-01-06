package edu.intech.mediatech.models.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import edu.intech.mediatech.R;
import edu.intech.mediatech.databinding.FragmentLostPasswordBinding;
import edu.intech.mediatech.models.bdd.User;
import edu.intech.mediatech.viewmodels.UserViewModel;
import kotlin.text.Regex;

import android.os.StrictMode;
import android.util.Log;

import org.mindrot.jbcrypt.BCrypt;

import javax.mail.PasswordAuthentication;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class LostFragmentPassword extends Fragment {

    FragmentLostPasswordBinding binding;
    UserViewModel userViewModel;
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
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
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

        }

        binding.fgtPwdBackBtn.setOnClickListener(v -> {
            requireActivity().onBackPressed();
        });

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.office365.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("docnahel@outlook.fr", "E!s57EqP53qbPmEDWL9q");
                    }
                });

        binding.fgtPwdValidateBtn.setOnClickListener(v -> {
            if (regex.matches(binding.fgtPwdEmailBox.getText().toString())) {

                LiveData<User> user = userViewModel.getUserByMail(binding.fgtPwdEmailBox.getText().toString());

                user.observe(getViewLifecycleOwner(), users -> {
                    Log.d("UserViewModel", user.toString());
                    if (user.getValue() != null) {

                        String newPassword = BCrypt.hashpw(String.valueOf(new Random().nextInt(100000)), BCrypt.gensalt(12));
                        String cuttedPassword = newPassword.substring(7, 17);
                        Log.d("cuttedPassword", cuttedPassword);

                        User updateUserPassword = new User("", "", cuttedPassword);

                        userViewModel.updateUser(binding.fgtPwdEmailBox.getText().toString(), updateUserPassword);

                        try {
                            Message message = new MimeMessage(session);

                            message.setFrom(new InternetAddress("docnahel@outlook.fr"));
                            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(binding.fgtPwdEmailBox.getText().toString()));
                            message.setSubject("MEDIATECH : Nouveau mot de passe");

                            message.setText("Bonjour,\n" +
                                    "\n" +
                                    "Voici votre nouveau mot de passe généré automatiquement. Ne le communiquez à personne.\n" +
                                    "\n" +
                                    cuttedPassword +
                                    "\n" +
                                    "\n" +
                                    "Prenez soin de le conserver en lieu sûr.\n" +
                                    "\n" +
                                    "Bonne journée.\n");

                            Transport.send(message);
                            Log.d("Mail", "Mail sent !");
                        } catch (MessagingException e) {
                            Log.e("MAIL", "Error sending email", e);
                        }

                        Toast.makeText(getContext(), "Un email contenant un nouveau mot de passe vous a été envoyé", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(v).navigate(R.id.action_lostFragmentPassword_to_connexionFragment);
                    } else {
                        Toast.makeText(getContext(), "Ce compte n'existe pas", Toast.LENGTH_SHORT).show();
                    }
                });
            } else {
                Toast.makeText(getContext(), "Format de l'email invalide", Toast.LENGTH_SHORT).show();
                Log.d("DEBUG", binding.fgtPwdEmailBox.getText().toString());
            }
        });


    }
}