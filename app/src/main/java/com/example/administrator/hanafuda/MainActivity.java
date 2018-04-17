package com.example.administrator.hanafuda;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    private int[] allCardFace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainGame);
        RelativeLayout playerHandRegion = findViewById(R.id.mainView);
        allCardFace = new int[48];
        allCardFace[0] = R.drawable.cardface10;
        allCardFace[1] = R.drawable.cardface11;
        allCardFace[2] = R.drawable.cardface12;
        allCardFace[3] = R.drawable.cardface13;
        allCardFace[4] = R.drawable.cardface20;
        allCardFace[5] = R.drawable.cardface21;
        allCardFace[6] = R.drawable.cardface22;
        allCardFace[7] = R.drawable.cardface23;
        allCardFace[8] = R.drawable.cardface30;
        allCardFace[9] = R.drawable.cardface31;
        allCardFace[10] = R.drawable.cardface32;
        allCardFace[11] = R.drawable.cardface33;
        allCardFace[12] = R.drawable.cardface40;
        allCardFace[13] = R.drawable.cardface41;
        allCardFace[14] = R.drawable.cardface42;
        allCardFace[15] = R.drawable.cardface43;
        allCardFace[16] = R.drawable.cardface50;
        allCardFace[17] = R.drawable.cardface51;
        allCardFace[18] = R.drawable.cardface52;
        allCardFace[19] = R.drawable.cardface53;
        allCardFace[20] = R.drawable.cardface60;
        allCardFace[21] = R.drawable.cardface61;
        allCardFace[22] = R.drawable.cardface62;
        allCardFace[23] = R.drawable.cardface63;
        allCardFace[24] = R.drawable.cardface70;
        allCardFace[25] = R.drawable.cardface71;
        allCardFace[26] = R.drawable.cardface72;
        allCardFace[27] = R.drawable.cardface73;
        allCardFace[28] = R.drawable.cardface80;
        allCardFace[29] = R.drawable.cardface81;
        allCardFace[30] = R.drawable.cardface82;
        allCardFace[31] = R.drawable.cardface83;
        allCardFace[32] = R.drawable.cardface90;
        allCardFace[33] = R.drawable.cardface91;
        allCardFace[34] = R.drawable.cardface92;
        allCardFace[35] = R.drawable.cardface93;
        allCardFace[36] = R.drawable.cardface100;
        allCardFace[37] = R.drawable.cardface101;
        allCardFace[38] = R.drawable.cardface102;
        allCardFace[39] = R.drawable.cardface103;
        allCardFace[40] = R.drawable.cardface110;
        allCardFace[41] = R.drawable.cardface111;
        allCardFace[42] = R.drawable.cardface112;
        allCardFace[43] = R.drawable.cardface113;
        allCardFace[44] = R.drawable.cardface120;
        allCardFace[45] = R.drawable.cardface121;
        allCardFace[46] = R.drawable.cardface122;
        allCardFace[47] = R.drawable.cardface123;

        Game newgame = new Game();
        newgame.grantCard(8);
        for (int i = 0; i < 8; i++) {
            ImageButton cardButton = cardView(newgame.getActivePlayer().gethandCardByIdx(i).getId());
            playerHandRegion.addView(cardButton);
        }
    }

        public ImageButton cardView(int cardId) {
            ImageButton show = new ImageButton(this);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)show.getLayoutParams();
            params.height = dp2px(this,80);
            params.width = dp2px(this,60);
            show.setLayoutParams(params);
            show.setImageResource(allCardFace[cardId]);

            return show;
        }

        public static int dp2px(Context context, float dpValue) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dpValue * scale + 0.5f);
        }
}
