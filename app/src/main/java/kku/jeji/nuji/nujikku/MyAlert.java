package kku.jeji.nuji.nujikku;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

/**
 * Created by Admin on 12/11/2559.
 */

public class MyAlert {

    //ประกาศตัวแปล
    private Context context;
    private  int anInt; //icon
    private  String titleString,messageString;//กด alt+insert จะทำการ context ให้แบบด้านล่างนี้ ไม่ต้องพิมเอง

    public MyAlert(Context context, int anInt, String titleString, String messageString) {
        this.context = context;
        this.anInt = anInt;
        this.titleString = titleString;
        this.messageString = messageString;
    }

    public void  myDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setCancelable(false);
        builder.setIcon(anInt);
        builder.setTitle(titleString);
        builder.setMessage(messageString);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.show();

    }
}
