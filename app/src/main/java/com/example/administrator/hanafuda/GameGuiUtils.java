package com.example.administrator.hanafuda;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by Administrator on 2018/4/18.
 */

public class GameGuiUtils {
    private int[] allCardFace;

    public GameGuiUtils() {
        /**
         * organize game image source in id order
         */
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
    }

    public int getRidByCardId(int id) {
        return allCardFace[id];
    }

    public static int dp2px(Context context, float dpValue) {
        /**
         * covert dp value to px value
         */
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Nullable
    public static byte[] serialize(Object obj) {
        ObjectOutputStream os = null;
        ByteArrayOutputStream bs = null;
        try {
            bs = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bs);
            os.writeObject(obj);
            byte[] array = bs.toByteArray();
            return array;
        }
        catch (Exception e) {

        }
        return null;
    }

    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {

        }
        return null;
    }

}
