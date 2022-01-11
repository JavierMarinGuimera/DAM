package com.javiermarin.selectorscontrols.data;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AmericanCountries {
    public static final List<String> COUNTRIES = new ArrayList<>();
    public static final List<Integer> AREAS = new ArrayList<>();

    static {
        COUNTRIES.add("Canadá");
        AREAS.add(9984670);
        COUNTRIES.add("Estados Unidos");
        AREAS.add(9498196);
        COUNTRIES.add("Brasil");
        AREAS.add(8514877);
        COUNTRIES.add("Argentina");
        AREAS.add(2792600);
        COUNTRIES.add("México");
        AREAS.add(1964375);
        COUNTRIES.add("Perú");
        AREAS.add(1285216);
        COUNTRIES.add("Colombia");
        AREAS.add(1141748);
        COUNTRIES.add("Bolivia");
        AREAS.add(1098581);
        COUNTRIES.add("Venezuela");
        AREAS.add(916445);
        COUNTRIES.add("Chile");
        AREAS.add(755938);
        COUNTRIES.add("Paraguay");
        AREAS.add(406752);
        COUNTRIES.add("Ecuador");
        AREAS.add(283561);
        COUNTRIES.add("Guyana");
        AREAS.add(214969);
        COUNTRIES.add("Uruguay");
        AREAS.add(176215);
        COUNTRIES.add("Surinam");
        AREAS.add(163820);
        COUNTRIES.add("Nicaragua");
        AREAS.add(121430);
        COUNTRIES.add("Honduras");
        AREAS.add(112492);
        COUNTRIES.add("Cuba");
        AREAS.add(110860);
        COUNTRIES.add("Guatemala");
        AREAS.add(108990);
        COUNTRIES.add("Panamá");
        AREAS.add(78260);
        COUNTRIES.add("Costa Rica");
        AREAS.add(51160);
        COUNTRIES.add("República Dominicana");
        AREAS.add(48762);
        COUNTRIES.add("Haití");
        AREAS.add(27850);
        COUNTRIES.add("Belice");
        AREAS.add(22966);
        COUNTRIES.add("El Salvador");
        AREAS.add(21481);
        COUNTRIES.add("Bahamas");
        AREAS.add(13940);
        COUNTRIES.add("Jamaica");
        AREAS.add(11524);
        COUNTRIES.add("Trinidad y Tobago");
        AREAS.add(5128);
        COUNTRIES.add("Dominica");
        AREAS.add(754);
        COUNTRIES.add("Santa Lucía");
        AREAS.add(623);
        COUNTRIES.add("Antigua y Barbuda");
        AREAS.add(443);
        COUNTRIES.add("Barbados");
        AREAS.add(439);
        COUNTRIES.add("San Vicente y las Granadinas");
        AREAS.add(389);
        COUNTRIES.add("Granada");
        AREAS.add(344);
        COUNTRIES.add("San Cristóbal y Nieves");
        AREAS.add(261);
    }

    public static String convertDistance(int distance) {
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        return format.format(distance);
    }
}

