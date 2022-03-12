package com.javiermarin.graficos_animaciones.activities;

import com.javiermarin.graficos_animaciones.R;

public enum Activities {
    CIRCLES(R.id.circlesBtn),
    DISPLACEMENTS(R.id.displacementsBtn),
    FADES(R.id.fadesBtn),
    ROTATIONS(R.id.rotationsBtn),
    TEXT_SIZE(R.id.textSizeBtn),
    TWEEN(R.id.tweenBtn);

    private int id;

    Activities(int id) {
        this.id = id;
    }

    public static Activities getActivity(int id) {
        for (Activities activity : values()) {
            if (activity.id == id) return activity;
        }

        return null;
    }
}
