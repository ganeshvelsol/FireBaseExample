package velsol.com.firebaseexample;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class MyFbIdService extends FirebaseInstanceIdService
{
    public void onTokenRefresh()
    {
        String token= FirebaseInstanceId.getInstance().getToken();

        sendRegToServer(token);
    }
    void sendRegToServer(String tokin)
    {

    }
}
