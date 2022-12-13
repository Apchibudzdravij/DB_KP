package com.belstu.drevoten.DB_KP.model;

import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;

public class Themes {
    @AllArgsConstructor
    public class ThemeEntity {
        private String letter;
        private String name;

        public String getLetter() {
            return letter;
        }

        public String getName() {
            return name;
        }
    }

    private List<ThemeEntity> themeEntityList;

    public Themes() {
        themeEntityList = new LinkedList<ThemeEntity>();
        themeEntityList.add(new ThemeEntity("S","Standard Classic"));
    }

    public List<ThemeEntity> getThemesList() {
        return themeEntityList;
    }
}
