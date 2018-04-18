package com.example.administrator.hanafuda;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainGameActivity extends AppCompatActivity {

    private ImageButton[] handCardView;
    private RelativeLayout gameRegion;
    private Game newgame;
    private GameGuiUtils gui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maingame);

        gameRegion = findViewById(R.id.mainView);
        newgame = new Game();
        gui = new GameGuiUtils();

        newgame.gameStart();
        updateHandCardView();

    }

    public void updateHandCardView() {
        handCardView = new ImageButton[newgame.getActivePlayer().gethandCount()];
        gameRegion.removeAllViews();
        for (int i = 0; i < newgame.getActivePlayer().gethandCount(); i++) {
            handCardView[i] = new ImageButton(this);
            handCardView[i].setScaleType(ImageButton.ScaleType.FIT_XY);
            handCardView[i].setImageResource(gui.getRidByCardId(newgame.getActivePlayer().gethandCardByIdx(i).getId()));
            gameRegion.addView(handCardView[i]);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) handCardView[i].getLayoutParams();
            params.height = dp2px(this,80);
            params.width = dp2px(this,60);
            params.leftMargin = dp2px(this,5+55*i);
            params.bottomMargin = dp2px(this,5);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            handCardView[i].setLayoutParams(params);
        }
    }

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
