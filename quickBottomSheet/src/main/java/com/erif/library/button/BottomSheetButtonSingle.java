package com.erif.library.button;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;

import com.erif.library.callback.BottomSheetSingleCallback;

public class BottomSheetButtonSingle {
    private String buttonTitle;
    private int textColor;
    private int backgroundDrawable;
    private BottomSheetSingleCallback callback;

    public BottomSheetButtonSingle() {
    }

    public BottomSheetButtonSingle(String buttonTitle, @ColorRes int textColor, @DrawableRes int backgroundDrawable, BottomSheetSingleCallback callback) {
        this.buttonTitle = buttonTitle;
        this.textColor = textColor;
        this.backgroundDrawable = backgroundDrawable;
        this.callback = callback;
    }

    public String getButtonTitle() {
        return buttonTitle;
    }

    public void setButtonTitle(String buttonTitle) {
        this.buttonTitle = buttonTitle;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(@ColorRes int textColor) {
        this.textColor = textColor;
    }

    public int getBackgroundDrawable() {
        return backgroundDrawable;
    }

    public void setBackgroundDrawable(@DrawableRes int backgroundDrawable) {
        this.backgroundDrawable = backgroundDrawable;
    }

    public BottomSheetSingleCallback getCallback() {
        return callback;
    }

    public void setCallback(BottomSheetSingleCallback callback) {
        this.callback = callback;
    }
}
