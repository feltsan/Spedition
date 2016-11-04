package com.feltsan.spedition.until;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.AsyncTask;
import android.telephony.gsm.SmsManager;
import android.text.Html;
import android.util.Log;
import android.widget.Toast;


import com.feltsan.spedition.model.Documents;
import com.feltsan.spedition.model.Oil;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * Created by john on 09.10.15.
 */
public abstract class Sender {

    public static void sendEmail(String msg, final List<Documents> documentses, final List<Oil> oils) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("program.korol@gmail.com", "ao1441ax");
            }
        });

        try {
            final Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("program.korol@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("ivan-korol.m@mail.ru"));
            message.setSubject("ЗАКІНЧУЄТЬСЯ ТЕРМІН ДІЇ!");
            message.setContent(msg, "text/html; charset=utf-8");

            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Transport.send(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });

            thread.start();


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static void sendSMS(final Context context, final String msg, final List<Documents> documentses, final List<Oil> oils)
    {
        SmsManager sms = SmsManager.getDefault();

        String SENT = "SMS_SENT";
        String DELIVERED = "SMS_DELIVERED";

        final PendingIntent sentPI = PendingIntent.getBroadcast(context, 0,
                new Intent(SENT), 0);

        final PendingIntent deliveredPI = PendingIntent.getBroadcast(context, 0,
                new Intent(DELIVERED), 0);

        final ArrayList<String> smsBodyParts = sms.divideMessage(msg);
        final ArrayList<PendingIntent> sentPendingIntents = new ArrayList<PendingIntent>();
        final ArrayList<PendingIntent> deliveredPendingIntents = new ArrayList<PendingIntent>();

        for (int i = 0; i < smsBodyParts.size(); i++) {
            sentPendingIntents.add(sentPI);
            deliveredPendingIntents.add(deliveredPI);
        }

        //---when the SMS has been sent---
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        setConfirm(documentses, oils);
                        Toast.makeText(context, "SMS sent",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        notConfirmation(context);
                        Toast.makeText(context, "Generic failure",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        notConfirmation(context);
                        Toast.makeText(context, "No service",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        notConfirmation(context);
                        Toast.makeText(context, "Null PDU",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        notConfirmation(context);
                        Toast.makeText(context, "Radio off",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(SENT));

        //---when the SMS has been delivered---
        context.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context arg0, Intent arg1) {
                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        setConfirm(documentses, oils);
                        Toast.makeText(context, "SMS delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        notConfirmation(context);
                        Toast.makeText(context, "SMS not delivered",
                                Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter(DELIVERED));

        sms.sendMultipartTextMessage("+380979330846", null, smsBodyParts, sentPendingIntents, deliveredPendingIntents);


    }
//    "+380979330846"
//    "ivan-korol.m@mail.ru"

    public static void setConfirm( List<Documents> documentses, List<Oil> oils){
        for(Documents d:documentses) {
            d.setInform(true);
            d.saveInBackground();
        }

        for(Oil o:oils ){
            o.setInform(true);
            o.saveInBackground();
        }
    }

    public static void notConfirmation(Context context){
        ShPrManager.setConfirm(context,false);
    }

}