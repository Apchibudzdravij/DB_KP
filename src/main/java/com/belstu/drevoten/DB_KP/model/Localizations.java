package com.belstu.drevoten.DB_KP.model;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Localizations {
    @AllArgsConstructor
    public class Language {
        private String letter;
        private String name;

        public String getLetter() {
            return letter;
        }

        public String getName() {
            return name;
        }
    }

    private List<Language> languageList;

    public Localizations() {
        languageList = new LinkedList<Language>();
        languageList.add(new Language("E","English"));
    }

    public List<Language> getLanguageList() {
        return languageList;
    }
}
