package com.us517.today.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.us517.today.R;
import com.us517.today.model.CreditCard;
import com.us517.today.model.UserAddress;

import java.util.List;

public class AddressAdapter extends BaseAdapter {
    private List<UserAddress> userAddressList;
    private int preferredAddress;
    private Context context;
    private LayoutInflater inflater;

    public AddressAdapter(List<UserAddress> list, Context context, String preferredAddressId) {
        this.userAddressList = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        setPreferredAddress(preferredAddressId);
    }
    public AddressAdapter(List<UserAddress> list, Context context) {
        this.userAddressList = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.preferredAddress = 0;
    }

    public void setPreferredAddress(String id) {
        int p = getItemPosition(id);
        this.preferredAddress = p;
        sortAddress(p);
    }

    public void sortAddress(int preferredLocation) {
        UserAddress pref = this.userAddressList.get(preferredLocation);
        this.userAddressList.remove(preferredLocation);
        this.userAddressList.add(0, pref);
    }

    public int getItemPosition(String id) {
        for (int position=0; position<userAddressList.size(); position++)
            if (userAddressList.get(position).getAddressId() == id)
                return position;
        return 0;
    }


    @Override
    public int getCount() {
        return this.userAddressList.size();
    }

    @Override
    public Object getItem(int i) {
        return userAddressList.get(i);
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }
    // View lookup cache
    private static class ViewHolder {
        TextView textAddressName;
        TextView textAddressLabel;
        TextView textAddressType;
        TextView textAddressPhone;
        TextView textAddressFull;
        ImageView imageViewAddressType;
        ImageView imageViewEdit;
    }

    public interface OnDeleteClick{
        void onDeleteClick(UserAddress userAddress);
    }
    public interface OnEditClick{
        void onEditClick(UserAddress userAddress);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new AddressAdapter.ViewHolder();
            view = inflater.inflate(R.layout.item_address, null);
            viewHolder.textAddressLabel = (TextView) view.findViewById(R.id.address_label);
            viewHolder.textAddressType = (TextView) view.findViewById(R.id.address_type);
            viewHolder.textAddressName = (TextView) view.findViewById(R.id.address_name);
            viewHolder.textAddressPhone = (TextView) view.findViewById(R.id.address_phone);
            viewHolder.textAddressFull = (TextView) view.findViewById(R.id.address_full);
            viewHolder.imageViewAddressType = (ImageView) view.findViewById(R.id.address_type_icon);
            viewHolder.imageViewEdit = (ImageView) view.findViewById(R.id.address_edit);
            view.setTag(viewHolder);
        } else {
            viewHolder = (AddressAdapter.ViewHolder) view.getTag();
        }


        final UserAddress userAddress = userAddressList.get(i);

        // Card last 4 digits
        if (viewHolder != null) {
            // Label
            String label = "";
            String name = "";
            String uL = userAddress.getLabel();
            if (uL != null) {
                if (uL.isEmpty()) {
                    label = userAddress.getFirstName() + " " + userAddress.getLastName();
                } else {
                    label = uL;
                    name = userAddress.getFirstName() + " " + userAddress.getLastName();
                }
            } else {
                label = userAddress.getFirstName() + " " + userAddress.getLastName();
            }

            viewHolder.textAddressLabel.setText(label);
            viewHolder.textAddressName.setText(name);

            viewHolder.textAddressPhone.setText(userAddress.getPhone());

            // Street
            String st = userAddress.getStreet();
            String csz = userAddress.getCity() + "," + userAddress.getState() + "," + userAddress.getZip();

            if (userAddress.getRoom() != null) {
                viewHolder.textAddressFull.setText(st + " " + userAddress.getRoom() + "," + csz);
            } else {
                viewHolder.textAddressFull.setText(st + "," + csz);
            }

            // Address type
            String addressType = userAddress.getType();
            String cType = "";
            int icon = R.drawable.account_address_24;
            if (addressType != null) {
                switch (addressType) {
                    case "home":
                        cType = " -Home";
                        icon = R.drawable.icon_home_outline_24;
                        break;
                    case "apartment":
                        cType = " -Apartment";
                        icon = R.drawable.icon_apartment_outline_24;
                        break;
                    case "work":
                        cType = " -Work";
                        icon = R.drawable.icon_work_outline_24;
                        break;

                }
            }

            //viewHolder.textAddressType.setText(cType);
            viewHolder.imageViewAddressType.setImageResource(icon);

            viewHolder.imageViewEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((AddressAdapter.OnEditClick) context).onEditClick(userAddress);
                }
            });
        }

        return view;
    }
}
