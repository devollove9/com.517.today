package com.us517.today.activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;

import com.us517.today.adapter.AddressAdapter;

import com.us517.today.databinding.ActivityAddressManageBinding;
import com.us517.today.model.CreditCard;
import com.us517.today.model.UserAddress;

import java.util.ArrayList;
import java.util.List;

public class AddressManageActivity extends BaseActivity  implements View.OnClickListener{
    ActivityAddressManageBinding binding;
    private AddressAdapter addressAdapter;
    private List<UserAddress> userAddressList;
    ActivityResultLauncher launcher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddressManageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        userAddressList = new ArrayList<>();

        getUserAddress();
        addressAdapter = new AddressAdapter(userAddressList, this, "0003");
        binding.addressManageAddressList.setAdapter(addressAdapter);
        binding.addressManageAddressNew.setOnClickListener(this);

    }

    public void getUserAddress() {
        UserAddress a = new UserAddress();
        a.setZip("48823");
        a.setPhone("5114957122");
        a.setStreet("500 W lake lansingdasdasddasdasd rd");
        a.setCity("East Lansing");
        a.setDisabled(false);
        a.setRoom("C45");
        a.setState("MI");
        a.setFirstName("Devo");
        a.setLastName("Li");
        a.setAddressId("0001");
        a.setType("home");
        a.setLabel("Homestead Apartmentasdasdaaaaaaa");
        userAddressList.add(a);

        UserAddress a1 = new UserAddress();
        a1.setZip("48823");
        a1.setPhone("4223451232");
        a1.setStreet("124 Cedar Street");
        a1.setCity("East Lansing");
        a1.setDisabled(false);
        a1.setRoom("C45");
        a1.setState("MI");
        a1.setFirstName("Bruce");
        a1.setLastName("Li");
        a1.setAddressId("0002");
        a1.setType("work");
        userAddressList.add(a1);

        UserAddress a11 = new UserAddress();
        a11.setZip("488233");
        a11.setPhone("5175448916");
        a11.setStreet("790 W lake lansing rd");
        a11.setCity("East Lansing");
        a11.setDisabled(false);
        //a11.setRoom("");
        a11.setState("MI");
        a11.setFirstName("Bruce");
        a11.setLastName("Li");
        a11.setAddressId("0003");
        a11.setLabel("Old address");
        userAddressList.add(a11);

        UserAddress a111 = new UserAddress();
        a111.setZip("48823");
        a111.setPhone("2454957122");
        a111.setStreet("1451 E lansing dr");
        a111.setCity("East Lansing");
        a111.setDisabled(false);
        a111.setRoom("223");
        a111.setState("MI");
        a111.setFirstName("Ba");
        a111.setLastName("Li");
        a111.setAddressId("0004");
        userAddressList.add(a111);

        UserAddress a1111 = new UserAddress();
        a1111.setZip("48823");
        a1111.setPhone("3254957122");
        a1111.setStreet("9528 E lansing dr");
        a1111.setCity("East Lansing");
        a1111.setDisabled(false);
        a1111.setRoom("269");
        a1111.setState("MI");
        a1111.setFirstName("Kirk");
        a1111.setLastName("Dr");
        a1111.setAddressId("0005");
        a1111.setType("apartment");
        userAddressList.add(a1111);
    }
    @Override
    public void onClick(View view) {

    }
}