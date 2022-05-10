package com.erif.library.button;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;

import com.erif.library.callback.BottomSheetMultipleCallback;
import com.erif.library.callback.BottomSheetSingleCallback;

public class BottomSheetButtonMultiple {
    private String buttonLeftTitle;
    private int textColorLeft;
    private int backgroundDrawableLeft;
    private String buttonRightTitle;
    private int textColorRight;
    private int backgroundDrawableRight;
    private BottomSheetMultipleCallback callback;

    public BottomSheetButtonMultiple() {
    }

    public String getButtonLeftTitle() {
        return buttonLeftTitle;
    }

    public void setButtonLeftTitle(String buttonLeftTitle) {
        this.buttonLeftTitle = buttonLeftTitle;
    }

    public int getTextColorLeft() {
        return textColorLeft;
    }

    public void setTextColorLeft(@ColorRes int textColorLeft) {
        this.textColorLeft = textColorLeft;
    }

    public int getBackgroundDrawableLeft() {
        return backgroundDrawableLeft;
    }

    public void setBackgroundDrawableLeft(@DrawableRes int backgroundDrawableLeft) {
        this.backgroundDrawableLeft = backgroundDrawableLeft;
    }

    public String getButtonRightTitle() {
        return buttonRightTitle;
    }

    public void setButtonRightTitle(String buttonRightTitle) {
        this.buttonRightTitle = buttonRightTitle;
    }

    public int getTextColorRight() {
        return textColorRight;
    }

    public void setTextColorRight(@ColorRes int textColorRight) {
        this.textColorRight = textColorRight;
    }

    public int getBackgroundDrawableRight() {
        return backgroundDrawableRight;
    }

    public void setBackgroundDrawableRight(@DrawableRes int backgroundDrawableRight) {
        this.backgroundDrawableRight = backgroundDrawableRight;
    }

    public BottomSheetMultipleCallback getCallback() {
        return callback;
    }

    public void setCallback(BottomSheetMultipleCallback callback) {
        this.callback = callback;
    }
}
