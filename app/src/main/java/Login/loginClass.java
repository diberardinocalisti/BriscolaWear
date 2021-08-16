package Login;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.facebook.AccessToken;
import com.facebook.Profile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class loginClass {

    /**
     *
     * @return true sel'utente è già loggato in facebook, else altrimenti
     */
    public static boolean isFacebookLoggedIn()
    {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        return accessToken != null && !accessToken.isExpired();
    }

    public static String getFBUserId()
    {
        return isFacebookLoggedIn() ? AccessToken.getCurrentAccessToken().getUserId() : null;
    }

    public static void setImgProfile(AppCompatActivity activity, String userId, ImageView imageIcon) {
        new Thread(() -> {
            URL imageURL = null;
            try {
                imageURL = new URL("https://graph.facebook.com/" + userId + "/picture?type=large");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Bitmap bitmap = null;
            try {
                bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            Bitmap finalBitmap = bitmap;
            activity.runOnUiThread(() -> imageIcon.setImageBitmap(finalBitmap));
        }).start();

    }

    public static String getFBCognome()
    {
        return Profile.getCurrentProfile().getLastName();
    }

    public static String getFBNome()
    {
        return Profile.getCurrentProfile().getFirstName();
    }

    public static String getFullFBName(){
        return getFBNome() + " " + getFBCognome();
    }

}
