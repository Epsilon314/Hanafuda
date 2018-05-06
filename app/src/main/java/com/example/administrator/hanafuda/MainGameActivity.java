package com.example.administrator.hanafuda;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

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

    /**
     * single player by default
     */
    private int gameMode = Game.GameMode.SINGLEPLAYER;

    private GameMessage.initMessage initMsg;
    private GameMessage.stepMessage sendMsg;
    private GameMessage.stepMessage receiveMsg;
    private static final boolean isServer = true;
    private long exitTime = 0;

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

        newGame = new Game(gameMode);
        guiUtils = new GameGuiUtils();
        computerPlayer = new NaiveComputerPlayer();

        gameStart();
    }

    public void gameStart() {
        /**
         * decide how to start the game by game mode
         */
        switch (newGame.mode.getMode()) {
            case Game.GameMode.SINGLEPLAYER:
                newGame.gameStartSingle();
                updateAllView();
                break;
            case Game.GameMode.MULTIPLAYER_WIFI:
                /**
                 * start a multi-player game
                 */
                if (isServer) {
                    /**
                     * runs server programs
                     */
                    initMsg = new GameMessage.initMessage(GameMessage.initMessage.YOUFIRST);
                    newGame.gameStartMultiplayer(initMsg,isServer);
                    for (int i = 0; i < Deck.DECKMAX; i++) {
                        initMsg.writeDeckCardIdByIdx(i,newGame.getDeck().getCardIdByIdx(i));
                    }
                    /**
                     * Todo:send msg
                     */
                    updateAllView();
                }
                else {
                    /**
                     * runs client programs
                     * Todo:receive msg
                     */
                    newGame.gameStartMultiplayer(initMsg,isServer);
                    updateAllView();
                }
                break;
            default:
                break;
        }
    }

    public void onClick(View v) {
        /**
         * when the player click a card
         */
        switch (newGame.mode.getMode()) {
            case Game.GameMode.SINGLEPLAYER: {
                /**
                 * event callback for single player mode
                 */
                if (newGame.isPlayerActive() && newGame.isGameActive()) {

                    /**
                     * if it is player's turn in a active game
                     * and the player click a card
                     */
                    int cardId = v.getId();
                    int playerHandCount = newGame.getActivePlayer().getHandCount();
                    for (int i = 0; i < playerHandCount; i++) {
                        if (cardId == newGame.getActivePlayer().getHandCardByIdx(i).getId()) {
                            newGame.playCardRound(i);
                            break;
                        }
                    }
                    if (checkGameAutoEnd()) {
                        endGame(newGame);
                    }
                    if (newGame.getActivePlayer().isMeetGameEndRequirements() && newGame.isGameActive()) {
                        showEndGameDiag();
                    }

                    /**
                     *computer player's turn start
                     */

                    newGame.changeActiveplayer();
                    computerPlayer.randomPlayCard(newGame);
                    if (checkGameAutoEnd()) {
                        endGame(newGame);
                    }
                    if (newGame.getActivePlayer().isMeetGameEndRequirements()) {
                        computerPlayer.chooseEndGame(newGame);
                    }
                    if (!newGame.isGameActive()) {
                        showGameOverDiag();
                    }
                    newGame.changeActiveplayer();
                    updateAllView();
                }
                break;
            }
            case Game.GameMode.MULTIPLAYER_WIFI: {
                /**
                 * multi-player click event
                 */
                if (newGame.isPlayerActive() && newGame.isGameActive()) {
                    int cardId = v.getId();
                    int playerHandCount = newGame.getActivePlayer().getHandCount();
                    for (int i = 0; i < playerHandCount; i++) {
                        if (cardId == newGame.getActivePlayer().getHandCardByIdx(i).getId()) {
                            newGame.playCardRound(i);
                            break;
                        }
                    }
                    if (checkGameAutoEnd()) {
                        endGame(newGame);
                    }
                    if (newGame.getActivePlayer().isMeetGameEndRequirements() && newGame.isGameActive()) {
                        showEndGameDiag();
                    }
                    newGame.changeActiveplayer();
                    sendMsg = new GameMessage.stepMessage(cardId);
                    /**
                     * Todo send sendMsg
                     */
                }
                break;
            }
            default:
                break;
        }
    }

    public void updateAllView() {
        /**
         * update the game view
         * modify this function if add more views that needs updating
         */
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
        String playerPointString = String.format("玩家得分 %d",newGame.getPlayer().getPoint());
        String oppoPointString = String.format("对手得分 %d",newGame.getOpponent().getPoint());
        String remainDeckCardString = String.format("卡堆中还剩%d张牌",newGame.getDeckRemain());
        playerPoint.setText(playerPointString);
        opponentPoint.setText(oppoPointString);
        deckRemain.setText(remainDeckCardString);
    }

    public void showEndGameDiag() {
        final AlertDialog.Builder endGameDiag = new AlertDialog.Builder(this);
        endGameDiag.setTitle("结束游戏?");
        /**
         * restrict the players from cancelling the dialog
         */
        endGameDiag.setCancelable(false);
        endGameDiag.setPositiveButton("是",
                /**
                 * end the game
                 */
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        endGame(newGame);
                    }
                });
        endGameDiag.setNegativeButton("否",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        newGame.getActivePlayer().giveUpEndGame();
                    }
                });
        endGameDiag.show();
    }

    public void endGame(Game game) {
        /**
         * will disable the game and show score dialog
         */
        game.endGame();
        showGameOverDiag();
    }

    public void showGameOverDiag() {
        String gameEndScore = String.format("你的得分: %d \n对手的得分: %d",
                newGame.getPlayer().getPoint(),newGame.getOpponent().getPoint());
        final AlertDialog.Builder gameOverDiag = new AlertDialog.Builder(this);
        gameOverDiag.setTitle("游戏结束");
        gameOverDiag.setMessage(gameEndScore);
        gameOverDiag.setCancelable(false);
        gameOverDiag.setPositiveButton("再来一局",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /**
                         * start a new game
                         */
                        restartMainGameActivity();
                    }
                });
        gameOverDiag.setNegativeButton("返回菜单"
                , new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                        /**
                         * back to menu activity
                         */
                    }
                });
        gameOverDiag.show();
    }

    public boolean checkGameAutoEnd() {
        /**
         * if both player run out of hand cards, the game is ended automatically
         */
        return  newGame.getPlayer().getHandCount() == 0 && newGame.getOpponent().getHandCount() == 0;
    }

    private void restartMainGameActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(MainGameActivity.this,"再按一次回到登录界面",Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode,event);
    }
}
