package com.us517.today.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.AddressComponent;
import com.google.android.libraries.places.api.model.AddressComponents;
import com.google.android.libraries.places.api.model.PlaceTypes;
import com.google.android.libraries.places.api.model.AutocompleteSessionToken;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.net.FetchPlaceRequest;
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.us517.today.R;
import com.us517.today.TodayApplication;
import com.us517.today.adapter.SearchPlaceAdapter;
import com.us517.today.databinding.ActivityModifyAddressBinding;
import com.us517.today.model.SearchPlace;
import com.us517.today.model.UserAddress;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ModifyAddressActivity extends BaseActivity implements View.OnClickListener, SearchPlaceAdapter.OnResultClick{
    ActivityModifyAddressBinding binding;
    private static final String TAG = "ADDRESS_AUTOCOMPLETE";
    private PlacesClient placesClient;
    private FindAutocompletePredictionsRequest request;
    private SearchPlaceAdapter searchPlaceAdapter;
    private AutocompleteSessionToken sessionToken;
    private List<SearchPlace> searchAddressList;
    private String modifyMode = "new";
    private boolean searching = false;
    private UserAddress userAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityModifyAddressBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initiate user address
        userAddress = new UserAddress();
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            if (extras.getParcelable("userAddress")!= null) {
                userAddress = extras.getParcelable("userAddress");
                modifyMode = "modify";
                binding.modifyAddressDelete.setText(R.string.modify_address_delete);
                binding.modifyAddressDelete.setOnClickListener(this);
                fillUserAddressInfo(userAddress);
            }
        }

        // Get api key and start google places client
        createPlacesClient();

        // Generate session token to reduce bill
        sessionToken = AutocompleteSessionToken.newInstance();

        //Generate adapter and search result list
        searchAddressList = new ArrayList<>();
        searchPlaceAdapter = new SearchPlaceAdapter(searchAddressList,this);
        binding.modifyAddressSearchResult.setAdapter(searchPlaceAdapter);


        // Remove search icon listener
        binding.modifyAddressSearch.setOnTouchListener(getDrawableListener(binding.modifyAddressSearch));
        binding.modifyAddressSearch.setOnFocusChangeListener(searchPlaceFocus());
        binding.modifyAddressSearch.addTextChangedListener(searchAddressWatcher());
        // Use the builder to create a FindAutocompletePredictionsRequest.

        // Add save button listener
        binding.modifyAddressSave.setOnClickListener(this);

    }

    public void createPlacesClient() {
        TodayApplication tApp = (TodayApplication) getApplication();
        placesClient = tApp.getPlacesClient();
    }

    // Watch search address bar
    private TextWatcher searchAddressWatcher() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String originString = charSequence.toString();
                if (!originString.isEmpty() && !searching) {
                    performSearchPlace(originString);
                } else {
                    searchAddressList.clear();
                    searchPlaceAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }
    // Watches if card number field is on focus
    private View.OnFocusChangeListener searchPlaceFocus() {
        return (view, b) -> {
            if (b) {
                binding.modifyAddressSearch.setText("");
                binding.modifyAddressSearch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_search_24, 0, R.drawable.icon_remove_24, 0);
            } else {
                binding.modifyAddressSearch.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_search_24, 0, 0, 0);
                clearSearch();
            }
        };
    }

    public void performSearchPlace(String address) {

        if (address == null) return;
        if (address.isEmpty()) return;
        searching = true;
        placesClient.findAutocompletePredictions(generateGoogleAddressRequest(address)).addOnSuccessListener((response) -> {
            searchAddressList.clear();
            searching = false;
            for (AutocompletePrediction prediction : response.getAutocompletePredictions()) {
                SearchPlace a = new SearchPlace();
                a.setPlaceId(prediction.getPlaceId());
                a.setPrimaryText(prediction.getPrimaryText(null).toString());
                a.setSecondaryText(prediction.getSecondaryText(null).toString());
                if (prediction.getDistanceMeters() != null) a.setDistance(prediction.getDistanceMeters());
                a.setFullText(prediction.getFullText(null).toString());
                searchAddressList.add(a);
                Log.i(TAG, prediction.getPlaceId());
                Log.i(TAG, prediction.getPrimaryText(null).toString());
            }
            searchPlaceAdapter.notifyDataSetChanged();
        }).addOnFailureListener((exception) -> {
            searching = false;
            searchAddressList.clear();
            if (exception instanceof ApiException) {
                ApiException apiException = (ApiException) exception;
                Log.e(TAG, "Place not found: " + apiException.getStatusCode());
            }
        });
    }

    private View.OnTouchListener getDrawableListener(EditText view) {
        return new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (!binding.modifyAddressSearch.isFocused()) return false;
                final int DRAWABLE_RIGHT = 2;
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (event.getRawX() >= (view.getRight() - view.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                        // Click event on drawableRight
                        binding.modifyAddressSearch.clearFocus();
                        return true;
                    }
                }
                return false;
            }
        };
    }
    public void clearSearch() {
        binding.modifyAddressSearch.setText("");
        searchAddressList.clear();
        searchPlaceAdapter.notifyDataSetChanged();
    }

    public FindAutocompletePredictionsRequest generateGoogleAddressRequest(String address) {
        // Create a RectangularBounds object.
        RectangularBounds bounds = RectangularBounds.newInstance(
                new LatLng(-33.880490, 151.184363),
                new LatLng(-33.858754, 151.229596));
        if (sessionToken == null) {
            sessionToken = AutocompleteSessionToken.newInstance();
        }
        FindAutocompletePredictionsRequest request = FindAutocompletePredictionsRequest.builder()
                // Call either setLocationBias() OR setLocationRestriction().
                .setLocationBias(bounds)
                //.setLocationRestriction(bounds)
                .setOrigin(new LatLng(-33.8749937, 151.2041382))
                .setCountries("US")
                .setTypesFilter(Arrays.asList(PlaceTypes.ADDRESS))
                .setSessionToken(sessionToken)
                .setQuery(address)
                .build();
        return request;
    }
    private void fillUserAddressInfo(UserAddress uAddress) {


        if ( uAddress.getStreet() != null) binding.modifyAddressStreet.setText(uAddress.getStreet());
        if ( uAddress.getRoom() != null) binding.modifyAddressRoom.setText(uAddress.getRoom());
        if ( uAddress.getCity() != null) binding.modifyAddressCity.setText(uAddress.getCity());
        if ( uAddress.getState() != null) binding.modifyAddressState.setText(uAddress.getState());
        if ( uAddress.getZip() != null) binding.modifyAddressZip.setText(uAddress.getZip());
        if ( uAddress.getLabel() != null) binding.modifyAddressLabel.setText(uAddress.getLabel());
        if ( uAddress.getFirstName() != null) binding.modifyAddressFirstName.setText(uAddress.getFirstName());
        if ( uAddress.getLastName() != null) binding.modifyAddressLastName.setText(uAddress.getLastName().isEmpty() ? "" : uAddress.getLastName());
        if ( uAddress.getPhone() != null) binding.modifyAddressPhone.setText(uAddress.getPhone());
        // if ( !uAddress.getType().isEmpty()) binding.modifyAddressLabel.setText(uAddress.getType());
    }
    private void fillInAddress(SearchPlace place) {
        // Define a Place ID.
        final String placeId = place.getPlaceId();

        // Specify the fields to return.
        final List<Place.Field> placeFields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS_COMPONENTS,Place.Field.ADDRESS);

        // Construct a request object, passing the place ID and fields array.
        final FetchPlaceRequest request = FetchPlaceRequest.newInstance(placeId, placeFields);
        placesClient.fetchPlace(request).addOnSuccessListener((response) -> {
            Place result = response.getPlace();
            result.getAddressComponents().toString();
            Log.i(TAG, "Place found: " + result.getName());
            Log.i(TAG, "Details: " + result.getAddress());
            Log.i(TAG, "Components: " + result.getAddressComponents().toString());
            final AddressComponents components = result.getAddressComponents();
            if (components != null) {
                String street = "";
                String route = "";
                for (AddressComponent component : components.asList()) {
                    String type = component.getTypes().get(0);
                    switch (type) {
                        case "street_number":
                            street = component.getName();
                            break;

                        // Street
                        case "route":
                            route = component.getName();
                            break;

                        // City
                        case "locality":
                            userAddress.setCity(component.getName());
                            break;

                        case "postal_code":
                            userAddress.setZip(component.getName());
                            break;


                        // Retrieve State
                        case "administrative_area_level_1":
                            userAddress.setState(component.getShortName());
                            break;

                        // County
                        case "administrative_area_level_2":
                        case "postal_code_suffix":
                        case "country":
                            break;
                    }
                    userAddress.setStreet(street + " " + route);
                }
                binding.modifyAddressStreet.setText(userAddress.getStreet());
                binding.modifyAddressCity.setText(userAddress.getCity());
                binding.modifyAddressZip.setText(userAddress.getZip());
                binding.modifyAddressState.setText(userAddress.getState());
            }

        }).addOnFailureListener((exception) -> {
            if (exception instanceof ApiException) {
                final ApiException apiException = (ApiException) exception;
                Log.e(TAG, "Place not found: " + exception.getMessage());
                final int statusCode = apiException.getStatusCode();
                // TODO: Handle error with given status code.
            }
        });

        // Get each component of the address from the place details,
        // and then fill-in the corresponding field on the form.
        // Possible AddressComponent types are documented at https://goo.gle/32SJPM1



        //binding.autocompletePostal.setText(postcode.toString());

        // After filling the form with address components from the Autocomplete
        // prediction, set cursor focus on the second address line to encourage
        // entry of sub-premise information such as apartment, unit, or floor number.
        // binding.autocompleteAddress2.requestFocus();

        // Add a map for visual confirmation of the address
        // showMap(place);
    }
    public boolean validateRequiredField(EditText view) {
        boolean valid = false;
        String value = view.getText().toString();
        if (view == binding.modifyAddressStreet) {
            if (value.isEmpty()) binding.modifyAddressStreetValidation.setText(R.string.modify_address_street_validation);
            else {
                userAddress.setStreet(value);
                valid = true;
            }
        }
        else if (view == binding.modifyAddressFirstName) {
            if (value.isEmpty()) binding.modifyAddressFirstNameValidation.setText(R.string.modify_address_required_validation);
            else {
                userAddress.setFirstName(value);
                valid = true;
            }
        }
        else if (view == binding.modifyAddressCity) {
            if (value.isEmpty()) binding.modifyAddressCityValidation.setText(R.string.modify_address_required_validation);
            else {
                userAddress.setCity(value);
                valid = true;
            }
        }
        else if (view == binding.modifyAddressState) {
            if (value.isEmpty()) binding.modifyAddressStateValidation.setText(R.string.modify_address_required_validation);
            else {
                userAddress.setState(value);
                valid = true;
            }
        }
        else if (view == binding.modifyAddressLastName) {
            if (value.isEmpty()) binding.modifyAddressLastNameValidation.setText(R.string.modify_address_required_validation);
            else {
                userAddress.setLastName(value);
                valid = true;
            }
        }
        else if (view == binding.modifyAddressPhone) {
            if (value.isEmpty()) binding.modifyAddressPhoneValidation.setText(R.string.modify_address_phone_validation);
            else {
                userAddress.setPhone(value);
                valid = true;
            }
        }
        else if (view == binding.modifyAddressZip) {
            if (value.isEmpty()) binding.modifyAddressZipValidation.setText(R.string.modify_address_required_validation);
            else {
                userAddress.setZip(value);
                valid = true;
            }
        }
        return valid;
    }
    public boolean validateUserAddress() {
        Log.d("asdas11d","asdasdasdasd");
        boolean valid = false;
        if ( validateRequiredField(binding.modifyAddressStreet) &&
            validateRequiredField(binding.modifyAddressCity) &&
            validateRequiredField(binding.modifyAddressState) &&
            validateRequiredField(binding.modifyAddressZip) &&
            validateRequiredField(binding.modifyAddressFirstName) &&
            validateRequiredField(binding.modifyAddressLastName) &&
            validateRequiredField(binding.modifyAddressPhone)){
            userAddress.setRoom(binding.modifyAddressRoom.getText().toString());
            userAddress.setLabel(binding.modifyAddressLabel.getText().toString());
            valid = true;
        }
        return valid;
    }
    @Override
    public void onClick(View view) {
        if (view == binding.modifyAddressSave) {
            // Performing local data change, should be actual data update in production
            if (validateUserAddress()) {
                if (modifyMode.equals("new")) {
                    userAddress.setAddressId("0007");
                }
                Intent d = new Intent();
                d.putExtra("userAddress", userAddress);
                setResult(RESULT_OK, d);
                finish();
            }
        } else if (view == binding.modifyAddressDelete) {
            // Performing local data change, should be actual data update in production
            if (modifyMode.equals("modify")) {
                Intent d = new Intent();
                d.putExtra("userAddress", userAddress);
                setResult(RESULT_CANCELED, d);
                finish();
            }
        }
    }

    @Override
    public void onResultClick(SearchPlace userAddress) {
        fillInAddress(userAddress);
        binding.modifyAddressSearch.clearFocus();
    }
}
