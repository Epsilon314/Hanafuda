package com.example.administrator.hanafuda;

import android.content.Context;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainGameActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton[] handCardView;
    private ImageView[] fieldCardView;
    private RelativeLayout gameRegion;
    private TableLayout fieldRegion;
    private Game newGame;
    private GameGuiUtils guiUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maingame);

        gameRegion = findViewById(R.id.mainView);
        fieldRegion = findViewById(R.id.field);
        newGame = new Game();
        guiUtils = new GameGuiUtils();

        newGame.gameStart();
        updateHandCardView();
        updateFieldCardView();
    }

    public void onClick(View v) {
        int cardId = v.getId();
        int playerHandCount = newGame.getActivePlayer().gethandCount();
        for (int i = 0; i < playerHandCount; i++) {
            if (cardId == newGame.getActivePlayer().gethandCardByIdx(i).getId()) {
                newGame.playCardRound(i);
                break;
            }
        }
        updateHandCardView();
        updateFieldCardView();
    }

    public void updateFieldCardView() {
        int fieldcount = newGame.getField().getFieldCardCount();
        fieldCardView = new ImageView[fieldcount];
        TableRow[] rows = new TableRow[2];
        rows[0] = new TableRow(this);
        rows[1] = new TableRow(this);
        fieldRegion.removeAllViews();
        for (int i = 0; i < fieldcount; i++) {
            fieldCardView[i] = new ImageView(this);
            fieldCardView[i].setScaleType(ImageView.ScaleType.FIT_XY);
            fieldCardView[i].setImageResource(guiUtils.getRidByCardId(newGame.getField().getCardIdByIdx(i)));
            rows[i%2].addView(fieldCardView[i]);
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) fieldCardView[i].getLayoutParams();
            params.height = guiUtils.dp2px(this,72);
            params.width = guiUtils.dp2px(this,54);
            fieldCardView[i].setLayoutParams(params);
        }
        fieldRegion.addView(rows[0]);
        fieldRegion.addView(rows[1]);
    }

    public void updateHandCardView() {
        if (handCardView != null) {
            for (int i = 0; i < handCardView.length; i++) {
                gameRegion.removeView(handCardView[i]);
            }
        }
        int handCardCount = newGame.getActivePlayer().gethandCount();
        handCardView = new ImageButton[handCardCount];
        for (int i = 0; i < handCardCount; i++) {
            handCardView[i] = new ImageButton(this);
            handCardView[i].setScaleType(ImageButton.ScaleType.FIT_XY);
            handCardView[i].setImageResource(guiUtils.getRidByCardId(newGame.getActivePlayer().gethandCardByIdx(i).getId()));
            handCardView[i].setId(newGame.getActivePlayer().gethandCardByIdx(i).getId());
            handCardView[i].setOnClickListener(this);
            gameRegion.addView(handCardView[i]);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) handCardView[i].getLayoutParams();
            params.height = guiUtils.dp2px(this, 80);
            params.width = guiUtils.dp2px(this, 60);
            params.leftMargin = guiUtils.dp2px(this, 5 + 55 * i);
            params.bottomMargin = guiUtils.dp2px(this, 5);
            params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
            handCardView[i].setLayoutParams(params);
        }
    }
}
