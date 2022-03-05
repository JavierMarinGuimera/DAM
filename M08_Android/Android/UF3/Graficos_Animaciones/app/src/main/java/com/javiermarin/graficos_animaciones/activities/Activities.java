package com.javiermarin.graficos_animaciones.activities;

import com.javiermarin.graficos_animaciones.R;

public enum Activities {
    CHANGE_COLOR(R.id.colorBtn),
    CIRCLES(R.id.circlesBtn),
    DISPLACEMENTS(R.id.displacementsBtn),
    FADES(R.id.fadesBtn),
    PROPERTY(R.id.propertyBtn),
    ROTATIONS(R.id.rotationsBtn),
    TWEEN(R.id.tweenBtn);

    private int id;

    Activities(int id){
        this.id=id;
    }

    public static Activities getActivity(int id) {
        for (Activities activity:values()) {
            if(activity.id==id) return activity;
        }

        return null;
    }
}
