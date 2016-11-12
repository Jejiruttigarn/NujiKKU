package kku.jeji.nuji.nujikku;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class SignUpActivity extends AppCompatActivity {

    //ประกาศตัวแปล
    private EditText nameEditText, phoneEditText, userEditText, passwordEditText;
    private ImageView imageView;
    private Button button;
    private String nameString, phoneString, userString, passwordString,imagePathString,imageNameString;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameEditText = (EditText) findViewById(R.id.editText);
        phoneEditText = (EditText) findViewById(R.id.editText2);
        userEditText = (EditText) findViewById(R.id.editText3);
        passwordEditText = (EditText) findViewById(R.id.editText4);
        imageView = (ImageView) findViewById(R.id.imageView);
        button = (Button) findViewById(R.id.button3);

        //SignUp controler
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {



                nameString=nameEditText.getText().toString().trim();
                phoneString = phoneEditText.getText().toString().trim();
                userString = userEditText.getText().toString().trim();
                passwordString = passwordEditText.getText().toString().trim();

                //check space
                if (nameString.equals("") || phoneString.equals("") || userString.equals("") || passwordString.equals("")){

                    Log.d("12onV1", "Have space");
                    MyAlert myAlert=new MyAlert(SignUpActivity.this,R.drawable.doremon48,"ช่องว่าง","กรุณากรอกให้ครบทุกช่องค่ะ");
                    myAlert.myDialog();

                }
        }
    });

        //image contoler
        imageView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("Image/*");
                startActivityForResult(Intent.createChooser(intent,"โปรดเลือกโปรแกรมดูภาพ"),0);//0 คือจะใส่เป็นอะไรก็ได้ ขอแค่ใส่เปน int


            }  //onclick
        });

    } // Main Method หรือ Method หลัก


    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if((requestCode ==0)&&(resultCode ==RESULT_OK)){
            Log.d("12novV1", "Result OK");

            uri=data.getData();
            try{
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(uri));
                imageView.setImageBitmap(bitmap);

            }catch (Exception e){
                e.printStackTrace();
            }
            //การหาพาท ของรูปภาพ
            imagePathString = myFindpath(uri);
            Log.d("12noVv1","imagePath ==>"+imagePathString);

            //หาชื่อภาพ

            imageNameString=imagePathString.substring(imagePathString.lastIndexOf("/"));
            Log.d("12noVv1","imageName ==>"+imageNameString);

        }
    }

    private String myFindpath(Uri uri) {
        String result = null;
        String[] string={MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, string, null, null, null);
        if (cursor !=null){
            cursor.moveToFirst();
            int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            result = cursor.getString(index);

        }else {
            result = uri.getPath();
        }


        return result;
    }

} //Main Class


