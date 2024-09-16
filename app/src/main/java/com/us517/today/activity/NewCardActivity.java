package com.us517.today.activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;

import com.us517.today.R;
import com.us517.today.databinding.ActivityNewCardBinding;
import com.us517.today.model.CreditCard;
import com.us517.today.util.CreditCardDetector;

import java.util.ArrayList;
import java.util.Calendar;

public class NewCardActivity extends BaseActivity implements View.OnClickListener {
    ActivityNewCardBinding binding;
    ArrayList<String> cardTypes;
    private String cardType = "card";
    private String cardExpire = "";
    private String cardNumber = "";
    private String cardCvv = "";
    private String cardZip = "";
    private static final char space = ' ';
    private String prevString = "";
    private String prevString2 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNewCardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setCardTypeList();
        binding.newCardNumber.addTextChangedListener(cardNumberWatcher());
        binding.newCardExpire.addTextChangedListener(cardExpireWatcher());

        binding.newCardNumber.setOnFocusChangeListener(cardNumberFocus());
        binding.newCardExpire.setOnFocusChangeListener(cardExpireFocus());
        binding.newCardCvv.setOnFocusChangeListener(cardCvvFocus());
        binding.newCardZip.setOnFocusChangeListener(cardZipFocus());

        binding.newCardNumberClear.setOnClickListener(cardNumberRemove());
        binding.newCardAdd.setOnClickListener(this);
    }

    // Watches if card number field is on focus
    private View.OnFocusChangeListener cardNumberFocus() {
        return (view, b) -> {
            if (b) {
                binding.newCardNumberClear.setVisibility(View.VISIBLE);
                binding.newCardNumberValidation.setText("");
            } else {
                binding.newCardNumberClear.setVisibility(View.GONE);
                validateCardNumber();
            }
        };
    }

    // Watches if card number field is on focus
    private View.OnFocusChangeListener cardExpireFocus() {
        return (view, b) -> {
            if (b) {
                binding.newCardExpireValidation.setText("");
            } else {
                validateCardExpire();
            }
        };
    }

    // Watches if card number field is on focus
    private View.OnFocusChangeListener cardCvvFocus() {
        return (view, b) -> {
            if (b) {
                binding.newCardCvvValidation.setText("");
            } else {
                validateCardCvv();
            }
        };
    }

    // Watches if card number field is on focus
    private View.OnFocusChangeListener cardZipFocus() {
        return (view, b) -> {
            if (b) {
                binding.newCardZipValidation.setText("");
            } else {
                validateCardZip();
            }
        };
    }


    // Watches card number changes
    private TextWatcher cardNumberWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Current input
                String ccNum = charSequence.toString().replace(" ","");
                String cardT = CreditCardDetector.getCardType(ccNum);
                // Show correspond card icon
                if (cardT != cardType) {
                    cardType = cardT;
                    int icon = R.drawable.account_credit_card_24;
                    switch (cardT) {
                        case "visa":
                            icon = R.drawable.icon_visa;
                            break;
                        case "mastercard":
                            icon = R.drawable.icon_mastercard;
                            break;
                        case "americanExpress":
                            icon = R.drawable.icon_americanexpress;
                            break;
                        case "union":
                            icon = R.drawable.icon_unionpay;
                            break;
                        case "discover":
                            icon = R.drawable.icon_discover;
                            break;
                        case "dinclb":
                        case "jcb":
                            break;
                    }
                    binding.newCardNumber.setCompoundDrawablesWithIntrinsicBounds(icon, 0, 0, 0);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                String originString = s.toString();
                String ccNum = originString.replace(" ","");
                String processed = "";
                for (int i = 0; i< ccNum.length(); i ++ ) {

                    if (i != 0 && i % 4 == 0 ) {
                        processed += " ";
                    }
                    processed += String.valueOf(ccNum.charAt(i));
                }
                if (!ccNum.equals(prevString)) {
                    prevString = ccNum;
                    s.replace(0,originString.length(), processed);
                }
            }
        };
    }

    // Watch card expire
    private TextWatcher cardExpireWatcher(){
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable s) {
                String originString = s.toString();
                String ccNum = originString.replace("/","");
                String processed = "";
                for (int i = 0; i< ccNum.length(); i ++ ) {
                    if (i == 2 ) {
                        processed += "/";
                    }
                    processed += String.valueOf(ccNum.charAt(i));
                }
                if (!ccNum.equals(prevString2)) {
                    prevString2 = ccNum;
                    s.replace(0,originString.length(), processed);
                }
            }
        };
    }

    private boolean validateCardInfo() {
        return validateCardNumber() && validateCardExpire() && validateCardCvv() && validateCardZip();
    }


    private boolean validateCardNumber() {
        String cardNumber = binding.newCardNumber.getText().toString().replace(" ","");
        boolean valid = false;
        if (cardNumber.length() > 16 && (!cardType.equals("union") && !cardType.equals("card"))) {
            String errorMessage = "! " + getString(R.string.new_card_validation_card_number);
            binding.newCardNumberValidation.setText(errorMessage);
        } else if (cardNumber.length() == 15 && !cardType.equals("americanExpress" )){
            String errorMessage = "! " + getString(R.string.new_card_validation_card_number);
            binding.newCardNumberValidation.setText(errorMessage);
        } else if (cardNumber.length() < 12) {
            String errorMessage = "! " + getString(R.string.new_card_validation_card_number);
            binding.newCardNumberValidation.setText(errorMessage);
        } else if (!checkLuhn(cardNumber)) {
            String errorMessage = "! " + getString(R.string.new_card_validation_card_number);
            binding.newCardNumberValidation.setText(errorMessage);
        } else {
            binding.newCardNumberValidation.setText("");
            this.cardNumber = cardNumber;
            valid = true;
        }
        return valid;
    }

    private boolean validateCardExpire() {
        String oS = binding.newCardExpire.getText().toString();
        String cardExpire = oS.replace("/","");
        boolean valid = false;
        if (cardExpire.length()!=4) {
            String errorMessage = "! " + getString(R.string.new_card_validation_card_expire);
            binding.newCardExpireValidation.setText(errorMessage);
        } else {
            int month = Integer.parseInt(cardExpire.substring(0,2));
            int year = Integer.parseInt(cardExpire.substring(2,4));
            int curYear = Calendar.getInstance().get(Calendar.YEAR);
            int curMonth = Calendar.getInstance().get(Calendar.MONTH);

            if (month == 0 || month >12) {
                String errorMessage = "! " + getString(R.string.new_card_validation_card_expire);
                binding.newCardExpireValidation.setText(errorMessage);
            } else if (year + 2000 < curYear || year + 2000 > curYear + 20) {
                String errorMessage = "! " + getString(R.string.new_card_validation_card_expire);
                binding.newCardExpireValidation.setText(errorMessage);
            } else if ( year + 2000 == curYear && month < curMonth) {
                String errorMessage = "! " + getString(R.string.new_card_validation_card_expire);
                binding.newCardExpireValidation.setText(errorMessage);
            } else {
                this.cardExpire = cardExpire;
                valid = true;
                binding.newCardExpireValidation.setText("");
            }
        }
        return valid;
    }

    private boolean validateCardCvv() {
        String cardCvv = binding.newCardCvv.getText().toString();
        boolean valid = false;
        if (cardCvv.length() != 3 && cardCvv.length()!= 4) {
            String errorMessage = "! " + getString(R.string.new_card_validation_card_cvv);
            binding.newCardCvvValidation.setText(errorMessage);
        } else {
            this.cardCvv = cardCvv;
            binding.newCardCvvValidation.setText("");
            valid = true;
        }
        return valid;
    }
    private boolean validateCardZip() {
        String cardZip = binding.newCardZip.getText().toString();
        boolean valid = false;
        if (cardZip.length() != 6 && cardZip.length()!=5) {
            String errorMessage = "! " + getString(R.string.new_card_validation_card_zip);
            binding.newCardZipValidation.setText(errorMessage);
        } else {
            this.cardZip = cardZip;
            binding.newCardZipValidation.setText("");
            valid = true;
        }
        return valid;
    }
    private void setCardTypeList() {
        ArrayList<String> listOfPattern = new ArrayList<>();

        String ptVisa = "^4[0-9]{6,}$";
        listOfPattern.add(ptVisa);
        String ptMasterCard = "^5[1-5][0-9]{5,}$";
        listOfPattern.add(ptMasterCard);
        String ptAmeExp = "^3[47][0-9]{5,}$";
        listOfPattern.add(ptAmeExp);
        String ptDinClb = "^3(?:0[0-5]|[68][0-9])[0-9]{4,}$";
        listOfPattern.add(ptDinClb);
        String ptDiscover = "^6(?:011|5[0-9]{2})[0-9]{3,}$";
        listOfPattern.add(ptDiscover);
        String ptJcb = "^(?:2131|1800|35[0-9]{3})[0-9]{3,}$";
        listOfPattern.add(ptJcb);
        cardTypes = listOfPattern;
    }


    static boolean checkLuhn(String cardNo)
    {
        int nDigits = cardNo.length();

        int nSum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--)
        {

            int d = cardNo.charAt(i) - '0';

            if (isSecond == true)
                d = d * 2;

            // We add two digits to handle
            // cases that make two digits
            // after doubling
            nSum += d / 10;
            nSum += d % 10;

            isSecond = !isSecond;
        }
        return (nSum % 10 == 0);
    }

    private View.OnClickListener cardNumberRemove() {
        return view -> binding.newCardNumber.setText("");
    }



    @Override
    public void onClick(View view) {
        if (view == binding.newCardAdd) {
            if(validateCardInfo()) {
                // This should be request to update credit card, using local variable here
                String cNumber = cardNumber.substring(cardNumber.length() -4);
                Intent intent = new Intent();
                intent.putExtra("cardType", cardType);
                intent.putExtra("cardNumber", cNumber);
                intent.putExtra("cardZip", cardZip);
                intent.putExtra("cardExpire", cardExpire);
                setResult(RESULT_OK, intent);
                finish();
            }
        }
    }
}
