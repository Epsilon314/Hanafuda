package com.example.administrator.hanafuda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainGameActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageButton[] handCardView;
    private ImageView[] fieldCardView;
    private ImageView[] ownCardView;
    private RelativeLayout gameRegion;
    private TableLayout fieldRegion;
    private TableRow[] playerOwnRegion;
    private TableRow[] opponentOwnRegion;
    private TextView playerPoint;
    private TextView opponentPoint;
    private TextView deckRemain;
    private Game newGame;
    private GameGuiUtils guiUtils;
    private NaiveComputerPlayer computerPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maingame);

        gameRegion = findViewById(R.id.mainView);
        fieldRegion = findViewById(R.id.field);
        playerOwnRegion = new TableRow[5];
        opponentOwnRegion = new TableRow[5];
        playerOwnRegion[2] = findViewById(R.id.pown3);
        playerOwnRegion[3] = findViewById(R.id.pown4);
        playerOwnRegion[4] = findViewById(R.id.pown5);
        playerOwnRegion[0] = findViewById(R.id.pown1);
        playerOwnRegion[1] = findViewById(R.id.pown2);
        opponentOwnRegion[0] = findViewById(R.id.oown1);
        opponentOwnRegion[1] = findViewById(R.id.oown2);
        opponentOwnRegion[2] = findViewById(R.id.oown3);
        opponentOwnRegion[3] = findViewById(R.id.oown4);
        opponentOwnRegion[4] = findViewById(R.id.oown5);
        playerPoint = findViewById(R.id.playerpoint);
        opponentPoint = findViewById(R.id.opponentpoint);
        deckRemain = findViewById(R.id.remainedcard);

        newGame = new Game();
        guiUtils = new GameGuiUtils();
        computerPlayer = new NaiveComputerPlayer();

        newGame.gameStart();
        updateAllView();
    }

    public void onClick(View v) {
        if (newGame.isPlayerActive()) {
            int cardId = v.getId();
            int playerHandCount = newGame.getActivePlayer().getHandCount();
            for (int i = 0; i < playerHandCount; i++) {
                if (cardId == newGame.getActivePlayer().getHandCardByIdx(i).getId()) {
                    newGame.playCardRound(i);
                    break;
                }
            }
            newGame.changeActiveplayer();
            computerPlayer.playCard(newGame);
            newGame.changeActiveplayer();
            updateAllView();
        }
    }

    public void updateAllView() {
        updateHandCardView();
        updateFieldCardView();
        updateAllOwnCardView();
        updateAllTextView();
    }

    public void updateFieldCardView() {
        int fieldCount = newGame.getField().getFieldCardCount();
        fieldCardView = new ImageView[fieldCount];
        TableRow[] rows = new TableRow[2];
        rows[0] = new TableRow(this);
        rows[1] = new TableRow(this);
        fieldRegion.removeAllViews();
        for (int i = 0; i < fieldCount; i++) {
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
        int handCardCount = newGame.getActivePlayer().getHandCount();
        handCardView = new ImageButton[handCardCount];
        for (int i = 0; i < handCardCount; i++) {
            handCardView[i] = new ImageButton(this);
            handCardView[i].setScaleType(ImageButton.ScaleType.FIT_XY);
            handCardView[i].setImageResource(guiUtils.getRidByCardId(newGame.getActivePlayer().getHandCardByIdx(i).getId()));
            handCardView[i].setId(newGame.getActivePlayer().getHandCardByIdx(i).getId());
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

    public void updateAllOwnCardView() {
        updateOwnCardView();
        newGame.changeActiveplayer();
        updateOwnCardView();
        newGame.changeActiveplayer();
    }

    private void updateOwnCardView() {
        int ownCardCount = newGame.getActivePlayer().getOwnCount();

        for (int i = 0; i < 5; i++) {
            if (newGame.isPlayerActive()) {
                playerOwnRegion[i].removeAllViews();
            }
            else {
                opponentOwnRegion[i].removeAllViews();
            }
        }
        ownCardView = new ImageView[ownCardCount];
        for (int i = 0; i < ownCardCount; i++) {
            ownCardView[i] = new ImageView(this);
            ownCardView[i].setScaleType(ImageView.ScaleType.FIT_XY);
            ownCardView[i].setImageResource(guiUtils.getRidByCardId(newGame.getActivePlayer().getOwnCardByIdx(i).getId()));
            if (newGame.isPlayerActive()) {
                playerOwnRegion[i%5].addView(ownCardView[i]);
            }
            else {
                opponentOwnRegion[i%5].addView(ownCardView[i]);
            }
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) ownCardView[i].getLayoutParams();
            params.height = guiUtils.dp2px(this,32);
            params.width = guiUtils.dp2px(this,24);
            ownCardView[i].setLayoutParams(params);
        }
    }

    public void updateAllTextView() {
        String playerPointString = String.format("Player Point %d",newGame.getPlayer().getPoint());
        String oppoPointString = String.format("Opponent Point %d",newGame.getOpponent().getPoint());
        String remainDeckCardString = String.format("%d card(s) in deck",newGame.getDeckRemain());
        playerPoint.setText(playerPointString);
        opponentPoint.setText(oppoPointString);
        deckRemain.setText(remainDeckCardString);
    }
}
