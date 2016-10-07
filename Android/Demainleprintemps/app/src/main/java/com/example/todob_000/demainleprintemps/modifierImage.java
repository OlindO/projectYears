package com.example.todob_000.demainleprintemps;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore.MediaColumns;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class modifierImage extends Activity {
    Uri currImageURI; String image_name,selectedPath;
    Button uploadimg,select;ImageView preview;
    TextView tv_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier_image);

        preview = (ImageView)findViewById(R.id.preview);
        tv_path = (TextView) findViewById(R.id.path);
        //upload button
        select = (Button) this.findViewById(R.id.select);
        uploadimg = (Button) this.findViewById(R.id.uploadimg);



        select.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"),1);

            }
        });


        uploadimg.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                HttpUploader uploader = new HttpUploader();

                try {
                    String image_name = uploader.execute(getRealPathFromURI(currImageURI)).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                preview.setImageResource(R.drawable.ic_launcher);
                tv_path.setText("Select image");
                uploadimg.setVisibility(View.GONE);


            }
        });
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        ImageView preview = (ImageView)findViewById(R.id.preview);

        if (resultCode == RESULT_OK) {
            if(data.getData() != null){
                currImageURI = data.getData();
            }else{
                Log.d("selectedPath1 : ","Came here its null !");

            }
            if (requestCode == 1)

            {

                // currImageURI is the global variable I�m using to hold the content:
                currImageURI = data.getData();
                System.out.println("Current image Path is ----->" +getRealPathFromURI(currImageURI) );

                tv_path.setText("Image path: "+getRealPathFromURI(currImageURI));

                uploadimg.setVisibility(View.VISIBLE);
                preview.setImageURI(currImageURI);
            }
        }
    }
    //Convert the image URI to the direct file system path of the image file
    public String getRealPathFromURI(Uri contentUri) {
        String [] proj={MediaColumns.DATA};
        android.database.Cursor cursor = managedQuery( contentUri,
                proj,     // Which columns to return
                null,     // WHERE clause; which rows to return (all rows)
                null,     // WHERE clause selection arguments (none)
                null);     // Order-by clause (ascending by name)
        int column_index = cursor.getColumnIndexOrThrow(MediaColumns.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);

    }

    //Uploader class
    public class HttpUploader extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... path) {

            Intent intent3 = getIntent();
            String loginRecupere = intent3.getStringExtra("Login");
            String outPut = null;

            for (String sdPath : path) {

                Bitmap bitmapOrg = BitmapFactory.decodeFile(sdPath);
                ByteArrayOutputStream bao = new ByteArrayOutputStream();

                //Resize the image
                double width = bitmapOrg.getWidth();
                double height = bitmapOrg.getHeight();
                double ratio = 400/width;
                int newheight = (int)(ratio*height);

                System.out.println("���-width" + width);
                System.out.println("���-height" + height);

                bitmapOrg = Bitmap.createScaledBitmap(bitmapOrg, 400, newheight, true);

                //Here you can define .PNG as well
                bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 95, bao);
                byte[] ba = bao.toByteArray();
                String ba1 = Base64.encodeToString(ba, 0);
                String ba2="discription";


                System.out.println("uploading image now ���" + ba1);

                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                nameValuePairs.add(new BasicNameValuePair("image", ba1));
                nameValuePairs.add(new BasicNameValuePair("name", ba2));
                nameValuePairs.add(new BasicNameValuePair("Login", loginRecupere));
                System.out.println("Name value pairs............" + nameValuePairs .toString());

                try {
                    HttpClient httpclient = new DefaultHttpClient();
                    HttpPost httppost = new HttpPost("http://ecole-theatrale.fr/Tests_Appli-mobile/upload.php");
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    Log.i("login",loginRecupere);
                    HttpResponse response = httpclient.execute(httppost);
                    HttpEntity entity = response.getEntity();

                    // print responce
                    outPut = EntityUtils.toString(entity);
                    Log.i("GET RESPONSE�-", outPut);

                    //is = entity.getContent();
                    Log.e("log_tag ******", "good connection");

                    bitmapOrg.recycle();


                } catch (Exception e) {
                    Log.e("log_tag ******", "Error in http connection " + e.toString());
                }
            }

            return outPut;

        }

    }
}