package com.us517.today.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

import com.us517.today.MainActivity;
import com.us517.today.R;
import com.us517.today.TodayApplication;
import com.us517.today.databinding.ActivityLanguageSelectBinding;
import com.us517.today.util.Dialog;

public class LanguageSelectActivity extends BaseActivity implements View.OnClickListener {
    ActivityLanguageSelectBinding binding;
    RadioGroup languageSelectGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLanguageSelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Set current language as selected radio button
        languageSelectGroup = findViewById(R.id.language_select_group);
        TodayApplication tApp = (TodayApplication) getApplication();

        if (tApp.getLanguage().toString().equals("zh")) {
            languageSelectGroup.check(R.id.language_select_chinese);
        } else {
            languageSelectGroup.check(R.id.language_select_english);
        }
        // Add confirm button listener
        Button button = findViewById(R.id.language_select_confirm);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //
        TodayApplication tApp = (TodayApplication) getApplication();
        String curLang = tApp.getLanguage();
        String sL = "en";
        if (languageSelectGroup.getCheckedRadioButtonId() == R.id.language_select_chinese) {
            sL = "zh";
        }
        final String seLang = sL;

        if (!curLang.equals(seLang.toString())) {
            Dialog.showYesNoDialog(LanguageSelectActivity.this, 0, null, getString(R.string.language_select_confirm_selection), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    tApp.setLanguage(seLang);
                    dialog.dismiss();
                }
            }, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // finish();
                    dialog.dismiss();
                }
            });
        }
    }
}
