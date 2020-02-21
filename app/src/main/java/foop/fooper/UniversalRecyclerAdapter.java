package foop.fooper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class UniversalRecyclerAdapter extends RecyclerView.Adapter<UniversalRecyclerAdapter.ViewHolder>
        implements Observer{
    private final MaterialRecyclerView.OnSwipeAnimation mSwipeAnimation;
    private final MaterialRecyclerView.OnAdapterItemsChanged mItemAnimation;
    private final List<Card> mCardList = new ArrayList<>();

    public UniversalRecyclerAdapter(@NonNull final MaterialRecyclerView.OnSwipeAnimation swipeAnimation,
                                  @NonNull final MaterialRecyclerView.OnAdapterItemsChanged itemAnimation) {
        mSwipeAnimation = swipeAnimation;
        mItemAnimation = itemAnimation;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.build(getCard(i));
    }
    @Nullable
    public Card getCard(int position) {
        if (position >= 0 && position < mCardList.size()) {
            return mCardList.get(position);
        }
        return null;
    }
    @Override
    public int getItemCount() {
        return mCardList.size();
    }

    @Override
    public int getItemViewType(final int position) {
        try {
            return mCardList.get(position).getProvider().getLayout();
        } catch (Exception e) {
            e.printStackTrace();

            return 0;
        }
    }

    /**
     * Is the list empty?
     *
     * @return {@code true} if the list is empty or {@code false} otherwise.
     */
    public boolean isEmpty() {
        return mCardList.isEmpty();
    }
    /**
     * Get the position of a specified Card.
     *
     * @param card to get the position of.
     * @return the position.
     */
    public int getPosition(@NonNull Card card) {
        return mCardList.indexOf(card);
    }
    /**
     * Add all Cards.
     *
     * @param cards to add.
     */
    public void addAll(@NonNull final Collection<Card> cards) {
        mCardList.clear();
        notifyDataSetChanged();
        int index = 0;
        for (Card card : cards) {
            add(index++, card, false);
        }
    }
    /**
     * Add a Card at a specific position with or without a scroll animation.
     *
     * @param position of the card to insert.
     * @param card     to insert.
     * @param scroll   will trigger an animation if it is set to <code>true</code> otherwise not.
     */
    public void add(final int position, @NonNull final Card card, final boolean scroll) {
        mCardList.add(position, card);
    }
    /**
     * Remove a Card withProvider or without an animation.
     *
     * @param card    to remove.
     * @param animate {@code true} to animate the remove process or {@code false} otherwise.
     */
    public void remove(@NonNull final Card card, boolean animate) {
        if (card.isDismissible()) {
            card.getProvider().deleteObserver(this);
            if (animate) {
                mSwipeAnimation.animate(getPosition(card));
            } else {
                mCardList.remove(card);
                mItemAnimation.onRemoveItem();
                notifyDataSetChanged();
            }
        }
    }
    @Override
    public void update(final Observable observable, final Object data) {
        if (data instanceof Card) {
            notifyDataSetChanged();
        }
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardLayout view;

        public ViewHolder(@NonNull final View v) {
            super(v);
            view = (CardLayout) v;
        }

        public void build(Card card) {
            view.build(card);
        }
    }
}