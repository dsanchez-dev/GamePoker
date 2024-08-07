package com.example.game;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.List;

public class CardAdapter extends BaseAdapter {
    private Context context;
    private List<Card> cards;

    public CardAdapter(Context context, List<Card> cards) {
        this.context = context;
        this.cards = cards;
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Object getItem(int position) {
        return cards.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
public View getView(int position, View convertView, ViewGroup parent) {
    Card card = cards.get(position);
    View view = convertView;

    if (view == null) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.card_item, null);
    }

    ImageView cardImageView = view.findViewById(R.id.cardImage);
    cardImageView.setImageResource(card.isFlipped() ? card.getImageResourceId() : R.drawable.poker_back);

    return view;
}
}