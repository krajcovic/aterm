package cz.monetplus.aterm.protocol;

import android.app.Activity;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceGroup;
import android.preference.PreferenceManager;

import cz.monetplus.aterm.R;
import cz.monetplus.aterm.base.MessageTemplate;

/**
 * Created by krajcovic on 12/11/15.
 */
public class MnspFactory {



    public static Mnsp create(Activity activity, MessageTemplate messageTemplate) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity.getApplicationContext());
        String mnsp_terminal_name = preferences.getString("mnsp_terminal_name", activity.getResources().getString(R.string.pref_default_mnsp_terminal_name));
        String mnsp_sequence_number = preferences.getString("mnsp_sequence_number", activity.getResources().getString(R.string.pref_default_mnsp_sequence_number));
        String mnsp_encrypt_type = preferences.getString("mnsp_encrypt_type", activity.getResources().getString(R.string.pref_default_mnsp_encrypt_types));

        Mnsp mnsp = new Mnsp(mnsp_terminal_name, mnsp_sequence_number, mnsp_encrypt_type);

        // TODO: set spdh.

        return mnsp;
    }

    private static String createHeader() {

        return null;
    }
}
