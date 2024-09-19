package com.us517.today.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.us517.today.R;
import com.us517.today.model.SearchPlace;

import java.util.List;


public class SearchPlaceAdapter extends BaseAdapter {
    private List<SearchPlace> searchPlaceList;
    private Context context;
    private LayoutInflater inflater;

    public SearchPlaceAdapter(List<SearchPlace> list, Context context) {
        this.searchPlaceList = list;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }


    public int getItemPosition(String id) {
        for (int position=0; position<searchPlaceList.size(); position++)
            if (searchPlaceList.get(position).getPlaceId() == id)
                return position;
        return 0;
    }


    @Override
    public int getCount() {
        return this.searchPlaceList.size();
    }

    @Override
    public Object getItem(int i) {
        return searchPlaceList.get(i);
    }


    @Override
    public long getItemId(int i) {
        return 0;
    }
    // View lookup cache
    private static class ViewHolder {
        TextView textAddressFirst;
        TextView textAddressSecond;
    }

    public interface OnResultClick{
        void onResultClick(SearchPlace searchPlace);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;

        if (view == null) {
            viewHolder = new SearchPlaceAdapter.ViewHolder();
            view = inflater.inflate(R.layout.item_address_search, null);
            viewHolder.textAddressFirst = (TextView) view.findViewById(R.id.address_search_first);
            viewHolder.textAddressSecond = (TextView) view.findViewById(R.id.address_search_second);
            view.setTag(viewHolder);
        } else {
            viewHolder = (SearchPlaceAdapter.ViewHolder) view.getTag();
        }


        final SearchPlace userAddress = searchPlaceList.get(i);

        // Card last 4 digits
        if (viewHolder != null) {
            // Label
            String first = userAddress.getPrimaryText();
            String second = userAddress.getSecondaryText();

            viewHolder.textAddressFirst.setText(first);
            viewHolder.textAddressSecond.setText(second);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ((SearchPlaceAdapter.OnResultClick) context).onResultClick(userAddress);
                }
            });
        }

        return view;
    }
}
