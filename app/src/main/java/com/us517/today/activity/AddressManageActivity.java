package com.us517.today.activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import com.us517.today.adapter.AddressAdapter;

import com.us517.today.databinding.ActivityAddressManageBinding;
import com.us517.today.model.CreditCard;
import com.us517.today.model.UserAddress;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AddressManageActivity extends BaseActivity  implements View.OnClickListener, AddressAdapter.OnEditClick{
    ActivityAddressManageBinding binding;
    private AddressAdapter addressAdapter;
    private List<UserAddress> userAddressList;
    ActivityResultLauncher<Intent> modifyLauncher;
    ActivityResultLauncher<Intent> newLauncher;
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

        modifyLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), modifyUserAddress());
        newLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), addUserAddress());

    }

    // Handle Callback form modify address activity, add new address
    public ActivityResultCallback<ActivityResult> addUserAddress() {
        return result -> {
            if(result.getResultCode() == Activity.RESULT_OK) {
                Intent d = result.getData();
                if (d!=null) {
                    Bundle extras = d.getExtras();
                    if (extras != null) {
                        if (extras.getParcelable("userAddress")!= null) {
                            UserAddress uA = d.getExtras().getParcelable("userAddress");
                            if (uA != null) {
                                userAddressList.add(uA);
                                addressAdapter.notifyDataSetChanged();
                            }

                        }
                    }
                }
            }
        };
    }

    // Handle Callback form modify address activity, modify & delete address
    public ActivityResultCallback<ActivityResult> modifyUserAddress() {
        return result -> {
            if(result.getResultCode() == Activity.RESULT_OK) {
                Intent d = result.getData();
                if (d!=null) {
                    Bundle extras = d.getExtras();
                    if (extras != null) {
                        if (extras.getParcelable("userAddress")!= null) {
                            UserAddress uA = d.getExtras().getParcelable("userAddress");
                            if (uA != null) {
                                int i = addressAdapter.getItemPosition(uA.getAddressId());
                                if (i != -1) {
                                    userAddressList.set(i,uA);
                                    addressAdapter.notifyDataSetChanged();
                                }
                            }

                        }
                    }
                }
            } else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                Intent d = result.getData();
                if (d!=null) {
                    Bundle extras = d.getExtras();
                    if (extras != null) {
                        if (extras.getParcelable("userAddress")!= null) {
                            UserAddress uA = d.getExtras().getParcelable("userAddress");

                            if (uA != null) {
                                int i = addressAdapter.getItemPosition(uA.getAddressId());
                                Log.d("asdasdasd", String.valueOf(i));
                                if (i != -1) {

                                    userAddressList.remove(i);
                                    addressAdapter.notifyDataSetChanged();
                                }
                            }

                        }
                    }
                }
            }
        };
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
    private void launchIntent(Intent intent, String mode) {
        if (mode.equals("new")) {
            newLauncher.launch(intent);
        } else if (mode.equals("modify")){
            modifyLauncher.launch(intent);
        }
    }
    @Override
    public void onClick(View view) {
        Intent intent = null;
        if (view == binding.addressManageAddressNew) {
            intent = new Intent(this, ModifyAddressActivity.class);
            //intent.putExtra("modify_mode", "new");
        }
        if (intent != null) {
            //startActivity(intent);
            if (view == binding.addressManageAddressNew) {
                launchIntent(intent, "new");
            }
            /*
            if (view == binding.) {
                launcher.launch(intent);
            } else {
                startActivity(intent);
            }
            // getActivity().overridePendingTransition(R.anim.anim_right_in, R.anim.anim_left_out);*/
        }
    }

    /**
     * Handling edit click of each address record
     * @param userAddress
     */
    @Override
    public void onEditClick(UserAddress userAddress) {
        Intent intent = null;
        intent = new Intent(this, ModifyAddressActivity.class);
        intent.putExtra("userAddress", userAddress);

        launchIntent(intent, "modify");
    }
}