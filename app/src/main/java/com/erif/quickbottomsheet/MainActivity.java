package com.erif.quickbottomsheet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;

import com.erif.library.button.BottomSheetButtonSingle;
import com.erif.library.QuickBottomSheet;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuickBottomSheet.Builder builder = new QuickBottomSheet.Builder(getSupportFragmentManager(), true);
        builder.setTitle("Abc", true);
        builder.setTitleLine(false);
        builder.setIllustration(R.mipmap.illustration);
        builder.setMessage("Lorem ipsum dolor sit amet bla bla bla bla bla bla bla bla bla", true);
        builder.setCancelable(true);
        builder.showCloseButton(true);
        builder.setSingleButton(new BottomSheetButtonSingle(
                "OKELAH", android.R.color.black,
                R.drawable.custom_bg_button, Dialog::dismiss
        ));
        //builder.setFont(R.font.chelon);
        /*builder.setMultipleButton("Batalkan", "Baiklah",
                new BottomSheetMultipleCallback() {
            @Override
            public void onClickLeftButton(Dialog dialog) {
                dialog.dismiss();
            }

            @Override
            public void onClickRightButton(Dialog dialog) {
                Toast.makeText(MainActivity.this, "OKE BOS", Toast.LENGTH_SHORT).show();
            }
        });*/

        QuickBottomSheet quickBottomSheet = builder.build();

        Button btnShow = findViewById(R.id.act_main_btnShow);
        btnShow.setOnClickListener(view -> {
            quickBottomSheet.show();
        });
    }

}