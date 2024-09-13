package com.us517.today.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.us517.today.adapter.CreditCardAdapter;
import com.us517.today.databinding.ActivityPaymentManageBinding;
import com.us517.today.model.CreditCard;

import java.util.ArrayList;
import java.util.List;

public class PaymentManageActivity extends BaseActivity implements View.OnClickListener,CreditCardAdapter.OnDeleteClick {
    ActivityPaymentManageBinding binding;
    private CreditCardAdapter creditCardAdapter;
    private List<CreditCard> creditCardList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentManageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        creditCardList = new ArrayList<CreditCard>();

        // Currently add mock data;
        getCreditCard();
        creditCardAdapter = new CreditCardAdapter(creditCardList, this);
        binding.paymentManageCardList.setAdapter(creditCardAdapter);
        binding.paymentManageCreditCardNew.setOnClickListener(this);


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
            getCreditCard();
            creditCardAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public void onDeleteClick(int i) {
        // Delete credit card, here only performing local changes
        creditCardList.remove(i);
        creditCardAdapter.notifyDataSetChanged();
    }
}
