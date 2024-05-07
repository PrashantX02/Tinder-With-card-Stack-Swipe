package com.example.tinder;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.CardStackView;
import com.yuyakaido.android.cardstackview.Direction;
import com.yuyakaido.android.cardstackview.StackFrom;
import com.yuyakaido.android.cardstackview.SwipeableMethod;

import java.util.ArrayList;
import java.util.List;

public class swipe_card extends Fragment {

    private CardStackLayoutManager manager;
    private CardStackAdapter cardStackAdapter;
    private CardStackView cardStackView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.swipe_card,container,false);

        cardStackView = view.findViewById(R.id.cardStackView);

        manager = new CardStackLayoutManager(getContext(), new CardStackListener() {

            @Override
            public void onCardDragging(Direction direction, float ratio) {
                // Paginating

                if(manager.getTopPosition() == (cardStackAdapter.getItemCount())){

                }
            }

            @Override
            public void onCardSwiped(Direction direction) {
                // Toast.makeText(getApplicationContext(), manager.getTopPosition()+" ", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCardRewound() {

            }

            @Override
            public void onCardCanceled() {

            }

            @Override
            public void onCardAppeared(View view, int position) {

            }

            @Override
            public void onCardDisappeared(View view, int position) {

            }
        });

        manager.setStackFrom(StackFrom.None);
        manager.setVisibleCount(3);
        manager.setTranslationInterval(0.0f);
        manager.setScaleInterval(0.95f);

        manager.setSwipeThreshold(0.3f);
        manager.setMaxDegree(20.0f);
        manager.setDirections(Direction.FREEDOM);
        manager.setCanScrollHorizontal(true);
        manager.setSwipeableMethod(SwipeableMethod.Manual);
        manager.setOverlayInterpolator(new LinearInterpolator());

        cardStackAdapter = new CardStackAdapter(getList(),getContext());

        cardStackView.setLayoutManager(manager);
        cardStackView.setAdapter(cardStackAdapter);
        cardStackView.setItemAnimator(new DefaultItemAnimator());

        return view;
    }

    public List<itemmodel> getList(){
        List<itemmodel> list = new ArrayList<>();

        list.add(new itemmodel("Anuj ,23","Sindri",R.drawable.europe));
        list.add(new itemmodel("Arya ,19","korea",R.drawable.korean2));
        list.add(new itemmodel("Faizel ,21","Sindri",R.drawable.ask));
        list.add(new itemmodel("Shubham ,22","Maharastra",R.drawable.tshirt));
        list.add(new itemmodel("sanchit ,23","Himachal",R.drawable.sjs));
        list.add(new itemmodel("Alice ,23","Erope",R.drawable.up1));
        list.add(new itemmodel("Shurbhi ,28","Sindri",R.drawable.saree));

        return list;
    }
}
