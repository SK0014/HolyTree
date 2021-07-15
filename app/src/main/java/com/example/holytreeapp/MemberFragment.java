package com.example.holytreeapp;

import android.app.ActionBar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MemberFragment extends Fragment {
    ArrayList<membership_types> memberships = new ArrayList<>();
    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_member,container,false);
       /* ViewPager viewPager;

        memberships.add(new membership_types("Got a busy lifestyle ? Try liquid diet. Get the most nutritious beverages like smoothies, juices and nut milk on a daily basis"
                ,"Doctor's meal",R.drawable.doctor_meal));
        memberships.add(new membership_types("Get the healthiest 30ml immunity booster shots and kick that corona virus out of your body."
                ,"Immunity shots", R.drawable.immunity_shots));
        memberships.add(new membership_types("Get tasty and vitamin rich smoothies in 500ml and 1lt. bottles according to your appetite."
                ,"Smoothies", R.drawable.smoothies));
        memberships.add(new membership_types("dskvshkskkkseksekskkskb"
                ,"Smoothies", R.drawable.smoothies));*/
//        memberships.add(new memberships_types("Got a busy lifestyle ? Try liquid diet. Get the most nutritious beverages like smoothies, juices and nut milk on a daily basis"
//                                             ,"Doctor's meal",R.mipmap.doctor_meal));
//        memberships.add(new memberships_types("Get the healthiest 30ml immunity booster shots and kick that corona virus out of your body."
//                                             ,"Immunity shots", R.mipmap.immunity_shots));
//        memberships.add(new memberships_types("Get tasty and vitamin rich smoothies in 500ml and 1lt. bottles according to your appetite."
//                                             ,"Smoothies", R.mipmap.smoothies));
//        memberships.add(new memberships_types("Get 300ml and 500ml bottle of both delicious and nutritious nut milk according to your choice."
//                                             ,"Nut milk", R.drawable.nut_milk));

       /* viewPager = view.findViewById(R.id.memberships_view);

        Recycler_Adapter pager_view = new Recycler_Adapter(getContext(), memberships);
        viewPager.setAdapter(pager_view);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
*/

        memberships.add(new membership_types("Got a busy lifestyle ? Try liquid diet. Get the most nutritious beverages like smoothies, juices and nut milk on a daily basis"
                ,"Salad",R.drawable.doctor_meal,"5000"));
        memberships.add(new membership_types("Get the healthiest 30ml immunity booster shots and kick that corona virus out of your body."
                ,"Immunity shots", R.drawable.immunity_shots,"7500"));
        memberships.add(new membership_types("Get 300ml and 500ml bottle of both delicious and nutritious nut milk according to your choice."
                ,"Nut milk", R.drawable.nut_milk,"1800"));
        memberships.add(new membership_types("Get tasty and vitamin rich smoothies in 500ml and 1lt. bottles according to your appetite."
                ,"Smoothies", R.drawable.smoothies,"6500"));


        RecyclerView membership_recyler_view = view.findViewById(R.id.memberships_view);

        Recycler_Adapter recycler_adapter = new Recycler_Adapter(getContext(), memberships);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        membership_recyler_view.setAdapter(recycler_adapter);
        membership_recyler_view.setLayoutManager(manager);
        membership_recyler_view.setHasFixedSize(false);


        return view;
        
    }
}
