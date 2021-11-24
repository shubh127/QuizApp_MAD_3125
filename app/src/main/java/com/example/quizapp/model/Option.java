package com.example.quizapp.model;

public class Option {
    String optionTxt;
    boolean isSelected;

    public Option(String optionTxt, boolean isSelected) {
        this.optionTxt = optionTxt;
        this.isSelected = isSelected;
    }

    public String getOptionTxt() {
        return optionTxt;
    }

    public void setOptionTxt(String optionTxt) {
        this.optionTxt = optionTxt;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
