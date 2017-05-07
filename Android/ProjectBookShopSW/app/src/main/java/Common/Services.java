package Common;

import android.content.SharedPreferences;
import android.provider.SyncStateContract;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yanis on 18/04/2017.
 */

public class Services {

    private void UpdateTokenPersonne(int id, String token) {
        URL url = null;
        HttpURLConnection httpURLConnection;
        String urlWS = null;
        HashMap<String, String> postDataParams = new HashMap<>();
        postDataParams.put("idPersonne", String.valueOf(id));
        postDataParams.put("token", token);

        try {
            url = new URL(urlWS);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream os = httpURLConnection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            //Flush nous permet d'envoyer toute les information sans rien laisser
            writer.flush();

            writer.close();
            os.close();

        }
        catch (Exception e)
        {

        }
    }
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder resultat = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if(first){
                first = false;
            }
            else {
                resultat.append("&");
            }
            resultat.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            resultat.append("=");
            resultat.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return resultat.toString();
    }
   /* public static void savePersonneLogged(Context context, Personne personne)
    {
        SharedPreferences prefs = context.getSharedPreferences();
        ShareedPreferences.Editor prefEditor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(personneLogged);
        prefEditor.putString();
    }*/
}
