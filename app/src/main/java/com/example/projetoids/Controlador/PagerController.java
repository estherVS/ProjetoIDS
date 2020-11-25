package com.example.projetoids.Controlador;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.projetoids.Fragments.Estrelas.Estrelas;
import com.example.projetoids.Fragments.Medalhas;
import com.example.projetoids.Fragments.Trofeus;

public class PagerController extends FragmentPagerAdapter {

    int numtabs;

    public PagerController(@NonNull FragmentManager fm, int bahavior) {
        super(fm,bahavior);
        this.numtabs = bahavior;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Estrelas();
            case 1:
                return new Medalhas();
            case 2:
                return new Trofeus();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numtabs;
    }
}

