 package com.erif.library;

 import android.annotation.SuppressLint;
 import android.app.Dialog;
 import android.graphics.Typeface;
 import android.os.Bundle;
 import android.view.Gravity;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.widget.ImageView;
 import android.widget.LinearLayout;
 import android.widget.RelativeLayout;
 import android.widget.TextView;

 import androidx.annotation.DrawableRes;
 import androidx.annotation.FontRes;
 import androidx.annotation.NonNull;
 import androidx.annotation.Nullable;
 import androidx.core.content.ContextCompat;
 import androidx.core.content.res.ResourcesCompat;
 import androidx.fragment.app.FragmentManager;

 import com.erif.CountDown.CountDown;
 import com.erif.library.animation.QuickBottomSheetAnimation;
 import com.erif.library.button.BottomSheetButtonSingle;
 import com.erif.library.callback.BottomSheetMultipleCallback;
 import com.erif.library.callback.BottomSheetSingleCallback;
 import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class QuickBottomSheet extends BottomSheetDialogFragment {

    private int fontID;
    private FragmentManager fragmentManager;
    private Dialog dialog;
    private boolean rounded = false;

    // Content
    private String title;
    private boolean titleCenter = false;
    private String message;
    private boolean messageCenter = false;
    private boolean closeButton = true;
    private boolean useLine = true;
    private int illustration = 0;

    // Button Single
    private boolean singleButton = false;
    private String singleButtonText = "YES";
    private BottomSheetSingleCallback singleCallback;
    private BottomSheetButtonSingle singleButtonObj;

    // Button Multiple
    private boolean multipleButton = false;
    private String leftButtonText = "CANCEL";
    private String rightButtonText = "YES";
    private BottomSheetMultipleCallback multipleCallback;

    public QuickBottomSheet() {
        // Required empty public constructor
    }

    public QuickBottomSheet(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    private QuickBottomSheet(FragmentManager fragmentManager, String title) {
        this.fragmentManager = fragmentManager;
        this.title = title;
    }

    private QuickBottomSheet(FragmentManager fragmentManager, String title, String message) {
        this.fragmentManager = fragmentManager;
        this.title = title;
        this.message = message;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.MyBottomSheetDialogTheme);
    }

    @SuppressLint("InflateParams")
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        LayoutInflater inflater = LayoutInflater.from(requireContext());
        View contentView = inflater.inflate(R.layout.quick_bottom_sheet, null, false);
        LinearLayout layParent = contentView.findViewById(R.id.quick_bottom_sheet_layParent);
        TextView txtTitle = contentView.findViewById(R.id.quick_bottom_sheet_txtTitle);
        TextView txtMessage = contentView.findViewById(R.id.quick_bottom_sheet_txtMessage);
        RelativeLayout layClose = contentView.findViewById(R.id.quick_bottom_sheet_layClose);
        View viewLine = contentView.findViewById(R.id.quick_bottom_sheet_lineView);
        ImageView imgIllustration = contentView.findViewById(R.id.quick_bottom_sheet_imgIllustration);

        // Button
        TextView txtButtonSingle = contentView.findViewById(R.id.quick_bottom_sheet_txtBtnSingle);
        TextView txtButtonLeft = contentView.findViewById(R.id.quick_bottom_sheet_txtBtnLeft);
        TextView txtButtonRight = contentView.findViewById(R.id.quick_bottom_sheet_txtBtnRight);
        LinearLayout layMultipleButton = contentView.findViewById(R.id.quick_bottom_sheet_layMultipleButton);

        if (fontID != 0) {
            Typeface typeface = ResourcesCompat.getFont(requireContext(), fontID);
            txtTitle.setTypeface(typeface);
            txtMessage.setTypeface(typeface);
            txtButtonSingle.setTypeface(typeface);
            txtButtonLeft.setTypeface(typeface);
            txtButtonRight.setTypeface(typeface);
        }

        if (rounded)
            layParent.setBackgroundResource(R.drawable.bg_bottom_sheet_rounded);

        if (title != null) {
            txtTitle.setText(title);
            if (titleCenter)
                txtTitle.setGravity(Gravity.CENTER);
        }
        if (message != null) {
            txtMessage.setText(message);
            if (messageCenter)
                txtMessage.setGravity(Gravity.CENTER);
        }
        layClose.setVisibility(closeButton ? View.VISIBLE : View.GONE);
        layClose.setOnClickListener(view -> {
            new CountDown(300L, this::dismiss);
        });

        viewLine.setVisibility(useLine ? View.VISIBLE : View.GONE);
        if (illustration != 0) {
            imgIllustration.setImageResource(illustration);
            imgIllustration.setVisibility(View.VISIBLE);
        } else {
            imgIllustration.setVisibility(View.GONE);
        }

        layMultipleButton.setVisibility(multipleButton ? View.VISIBLE : View.GONE);
        txtButtonSingle.setVisibility(multipleButton ? View.GONE : singleButton ? View.VISIBLE : View.GONE);

        if (singleCallback != null) {
            if (singleButtonObj != null) {
                txtButtonSingle.setText(singleButtonObj.getButtonTitle());
                int textColor = singleButtonObj.getTextColor();
                if (textColor != 0) {
                    txtButtonSingle.setTextColor(ContextCompat.getColor(requireContext(), singleButtonObj.getTextColor()));
                }
                int bgDrawable = singleButtonObj.getBackgroundDrawable();
                if (bgDrawable != 0) {
                    txtButtonSingle.setBackgroundResource(bgDrawable);
                }
                txtButtonSingle.setOnClickListener(view -> singleButtonObj.getCallback().onClickButton(QuickBottomSheet.this.getDialog()));
            }else {
                txtButtonSingle.setText(singleButtonText);
                txtButtonSingle.setOnClickListener(view -> singleCallback.onClickButton(this.getDialog()));
            }
        }

        if (multipleCallback != null) {
            txtButtonLeft.setText(leftButtonText);
            txtButtonRight.setText(rightButtonText);
            txtButtonLeft.setOnClickListener(view -> multipleCallback.onClickLeftButton(this.getDialog()));
            txtButtonRight.setOnClickListener(view -> multipleCallback.onClickRightButton(this.getDialog()));
        }

        dialog.setContentView(contentView);
        QuickBottomSheetAnimation.bounce(layParent);

        return dialog;
    }

    private void setRounded(boolean rounded) {
        this.rounded = rounded;
    }

    private void setTitleCenter(boolean titleCenter) {
        this.titleCenter = titleCenter;
    }

    private void setMessageCenter(boolean messageCenter) {
        this.messageCenter = messageCenter;
    }

    private void showCloseButton(boolean closeButton) {
        this.closeButton = closeButton;
    }

    private void setUseLine(boolean useLine) {
        this.useLine = useLine;
    }

    private void setIllustration(@DrawableRes int illustration) {
        this.illustration = illustration;
    }

    private void setSingleButton(String buttonTitle, BottomSheetSingleCallback callback) {
        this.singleButtonText = buttonTitle;
        this.singleCallback = callback;
        this.singleButton = true;
    }

    private void setSingleButton(BottomSheetButtonSingle button) {
        this.singleButtonObj = button;
        this.singleButton = true;
        this.singleCallback = button.getCallback();
    }

    private void setMultipleButton(String leftButtonTitle, String rightButtonTitle, BottomSheetMultipleCallback callback) {
        this.leftButtonText = leftButtonTitle;
        this.rightButtonText = rightButtonTitle;
        this.multipleCallback = callback;
        this.multipleButton = true;
    }

    private void setFont(@FontRes int fontID) {
        this.fontID = fontID;
    }

    public static class Builder {

        private int fontID;
        private boolean rounded = false;
        private String title;
        private boolean titleCenter = false;
        private String message;
        private boolean messageCenter = false;
        private final FragmentManager fragmentManager;
        private boolean cancelable = true;
        private boolean closeButton = true;
        private boolean useLine = true;
        private int illustration;

        // Button Single
        private boolean singleButton = false;
        private String singleButtonText = "YES";
        private BottomSheetSingleCallback singleCallback;
        private BottomSheetButtonSingle singleButtonObj;

        // Button Multiple
        private boolean multipleButton = false;
        private String leftButtonText = "CANCEL";
        private String rightButtonText = "YES";
        private BottomSheetMultipleCallback multipleCallback;

        public Builder(FragmentManager fragmentManager) {
            this.fragmentManager = fragmentManager;
        }

        public Builder(FragmentManager fragmentManager, boolean rounded) {
            this.fragmentManager = fragmentManager;
            this.rounded = rounded;
        }

        public void setFont(@FontRes int fontID) {
            this.fontID = fontID;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setTitle(String title, boolean centerGravity) {
            this.title = title;
            this.titleCenter = centerGravity;
        }

        public void setMessage(String message){
            this.message = message;
        }

        public void setMessage(String message, boolean centerGravity){
            this.message = message;
            this.messageCenter = centerGravity;
        }

        public void setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
        }

        public void showCloseButton(boolean closeButton) {
            this.closeButton = closeButton;
        }

        public void setTitleLine(boolean useLine) {
            this.useLine = useLine;
        }

        public void setIllustration(@DrawableRes int illustration) {
            this.illustration = illustration;
        }

        public void setSingleButton(String buttonTitle, BottomSheetSingleCallback callback) {
            this.singleButtonText = buttonTitle;
            this.singleCallback = callback;
            this.singleButton = true;
        }

        public void setSingleButton(BottomSheetButtonSingle button) {
            this.singleButtonObj = button;
            this.singleButton = true;
            this.singleCallback = button.getCallback();
        }

        public void setMultipleButton(String leftButtonTitle, String rightButtonTitle, BottomSheetMultipleCallback callback) {
            this.leftButtonText = leftButtonTitle;
            this.rightButtonText = rightButtonTitle;
            this.multipleCallback = callback;
            this.multipleButton = true;
        }

        public QuickBottomSheet build() {
            QuickBottomSheet bottomSheet = null;
            if (title != null && message != null) {
                bottomSheet = new QuickBottomSheet(this.fragmentManager, title, message);
            } else if (title != null) {
                bottomSheet = new QuickBottomSheet(this.fragmentManager, title);
            } else if (message != null) {
                bottomSheet = new QuickBottomSheet(this.fragmentManager, "This is title bottom sheet", message);
            }

            if (bottomSheet != null) {
                bottomSheet.setRounded(rounded);
                bottomSheet.setTitleCenter(titleCenter);
                bottomSheet.setMessageCenter(messageCenter);
                bottomSheet.setCancelable(cancelable);
                bottomSheet.showCloseButton(closeButton);
                bottomSheet.setUseLine(useLine);
                bottomSheet.setIllustration(illustration);

                // Font
                if (fontID != 0)
                    bottomSheet.setFont(fontID);

                // Button
                if (singleButton) {
                    if (singleButtonObj != null) {
                        bottomSheet.setSingleButton(singleButtonObj);
                    } else {
                        bottomSheet.setSingleButton(singleButtonText, singleCallback);
                    }
                } else if (multipleButton) {
                    bottomSheet.setMultipleButton(leftButtonText, rightButtonText, multipleCallback);
                }
            }

            return bottomSheet;
        }

    }

    public void show() {
        if (fragmentManager != null) {
            if (!isAdded())
                show(fragmentManager, this.getTag());
        }
    }

    public void dismiss() {
        if (dialog != null)
            dialog.dismiss();
    }

}