package com.us517.today.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.us517.today.R;
import com.us517.today.adapter.CreditCardAdapter;
import com.us517.today.databinding.ActivityPaymentManageBinding;
import com.us517.today.model.CreditCard;

import java.util.ArrayList;
import java.util.List;

public class PaymentManageActivity extends BaseActivity implements View.OnClickListener,CreditCardAdapter.OnDeleteClick {
    ActivityPaymentManageBinding binding;
    private CreditCardAdapter creditCardAdapter;
    private List<CreditCard> creditCardList;
    ActivityResultLauncher launcher;
    CreditCard newCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentManageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        creditCardList = new ArrayList<CreditCard>();

        // Currently add mock data;
        getCreditCard();
        creditCardAdapter = new CreditCardAdapter(creditCardList, this);
        TextView eV = findViewById(R.id.payment_manage_credit_card_list_empty);
        binding.paymentManageCardList.setEmptyView(eV);
        binding.paymentManageCardList.setAdapter(creditCardAdapter);
        binding.paymentManageCreditCardNew.setOnClickListener(this);

        launcher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK) {
                            String cardExpire = result.getData().getStringExtra("cardExpire");
                            String cardNumber = result.getData().getStringExtra("cardNumber");
                            String cardType = result.getData().getStringExtra("cardType");
                            String cardCvv= result.getData().getStringExtra("cardCvv");
                            String cardZip= result.getData().getStringExtra("cardZip");
                            CreditCard newCard = new CreditCard();
                            newCard.setNumber(cardNumber);
                            newCard.setType(cardType);
                            newCard.setZip(cardZip);
                            newCard.setExpire(cardExpire);
                            newCard.setCvv(cardCvv);
                            creditCardList.add(newCard);
                            creditCardAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
    private void getCreditCard() {
        // creditCardList.clear();
        // Adding local data atm, usually this step will fetch user's card list from server
        CreditCard a = new CreditCard();
        a.setZip("48823");
        a.setCvv("956");
        a.setPaymentId("qwejiqwdji");
        a.setDisabled(false);
        a.setExpire("0725");
        a.setNumber("5892");
        a.setType("visa");
        a.setUserId("0001");
        creditCardList.add(a);
        CreditCard a1 = new CreditCard();
        a1.setZip("48823");
        a1.setCvv("956");
        a1.setPaymentId("qwejiqwdji2");
        a1.setDisabled(false);
        a1.setExpire("0928");
        a1.setNumber("9657");
        a1.setType("mastercard");
        a1.setUserId("0002");
        creditCardList.add(a1);
        CreditCard a11 = new CreditCard();
        a11.setZip("48823");
        a11.setCvv("956");
        a11.setPaymentId("qwejiqwdji2");
        a11.setDisabled(false);
        a11.setExpire("0122");
        a11.setNumber("4321");
        a11.setType("americanExpress");
        a11.setUserId("0003");
        creditCardList.add(a11);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        if (view == binding.paymentManageCreditCardNew) {
            // New credit card page
            // getCreditCard();
            // creditCardAdapter.notifyDataSetChanged();
            intent = new Intent(this, NewCardActivity.class);
        }
        if (intent != null) {
            if (view == binding.paymentManageCreditCardNew) {
                launcher.launch(intent);
            } else {
                startActivity(intent);
            }
            // getActivity().overridePendingTransition(R.anim.anim_right_in, R.anim.anim_left_out);
        }

    }


    @Override
    public void onDeleteClick(int i) {
        // Delete credit card, here only performing local changes
        creditCardList.remove(i);
        creditCardAdapter.notifyDataSetChanged();
    }
}
