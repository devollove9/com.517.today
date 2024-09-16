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

import java.util.List;

public class CreditCardAdapter extends BaseAdapter {
    private List<CreditCard> cardList;
    private Context context;
    private LayoutInflater inflater;

    public CreditCardAdapter(List<CreditCard> list, Context context) {
        this.cardList = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return cardList.size();
    }

    @Override
    public Object getItem(int i) {
        return cardList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView textCardExpire;
        TextView textCardNumber;
        TextView textCardType;
        ImageView imageViewCardType;
        ImageView imageViewDelete;
    }
    public void remove(CreditCard creditCard) {
        cardList.remove(creditCard);
    }

    public interface OnDeleteClick{
        void onDeleteClick(int i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.item_credit_card, null);
            viewHolder.textCardExpire = (TextView) view.findViewById(R.id.credit_card_expire);
            viewHolder.textCardType = (TextView) view.findViewById(R.id.credit_card_type);
            viewHolder.textCardNumber = (TextView) view.findViewById(R.id.credit_card_number);
            viewHolder.imageViewCardType = (ImageView) view.findViewById(R.id.credit_card_avatar);
            viewHolder.imageViewDelete = (ImageView) view.findViewById(R.id.credit_card_delete);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        // Load credit card information
        CreditCard creditCard = cardList.get(i);

        // Card last 4 digits
        if (viewHolder != null) {
            viewHolder.textCardNumber.setText(creditCard.getNumber());

            // Card expiration
            String expire = creditCard.getExpire().substring(0,2)+"/"+creditCard.getExpire().substring(2);
            viewHolder.textCardExpire.setText(expire);

            // Card type
            String cardType = creditCard.getType();
            String cType = "Card";
            int icon = R.drawable.account_credit_card_24;
            switch (cardType) {
                case "visa":
                    cType = "Visa";
                    icon = R.drawable.icon_visa;
                    break;
                case "mastercard":
                    cType = "Mastercard";
                    icon = R.drawable.icon_mastercard;
                    break;
                case "americanExpress":
                    cType = "American Express";
                    icon = R.drawable.icon_americanexpress;
                    break;
                case "discover":
                    cType = "Discover";
                    icon = R.drawable.icon_discover;
                    break;

            }
            cType = cType + " **";
            viewHolder.textCardType.setText(cType);
            viewHolder.imageViewCardType.setImageResource(icon);

            viewHolder.imageViewDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((OnDeleteClick) context).onDeleteClick(i);
                }
            });
        }

        return view;
    }
}
