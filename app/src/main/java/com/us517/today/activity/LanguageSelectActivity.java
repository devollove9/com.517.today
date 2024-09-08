package com.us517.today.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.us517.today.MainActivity;
import com.us517.today.R;
import com.us517.today.TodayApplication;
import com.us517.today.databinding.ActivityLanguageSelectBinding;

public class LanguageSelectActivity extends BaseActivity implements View.OnClickListener{
    ActivityLanguageSelectBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLanguageSelectBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        RadioButton rbc = findViewById(R.id.language_select_chinese);
        RadioButton rbe = findViewById(R.id.language_select_english);
        RadioGroup languageSelectGroup = findViewById(R.id.language_select_group);
        languageSelectGroup.check(R.id.language_select_chinese);


        Button button = findViewById(R.id.language_select_confirm);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        TodayApplication tApp = (TodayApplication) getApplication();
        tApp.setLanguage("zh");
        Intent intent  = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
