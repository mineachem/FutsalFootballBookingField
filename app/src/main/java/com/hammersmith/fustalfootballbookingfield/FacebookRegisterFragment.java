package com.hammersmith.fustalfootballbookingfield;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

/**
 * Created by HTPP on 08/31/2015.
 */
public class FacebookRegisterFragment extends Fragment {
   public String id,fullname,email,gender,photo;

    public static final String FULL_NAME = "fullname";
    public static final String EMAIL ="email";
    public static final String GENDER ="gender";
    public static final String PHOTO ="photo";
    LoginButton loginButton;
  //  CallbackManager callbackManager;
    AccessTokenTracker accessTokenTracker;
    AccessToken accessToken;
    ProfileTracker profileTracker;
    CallbackManager callbackManager;
    public FacebookRegisterFragment (){

    }


    private FacebookCallback<LoginResult>callback = new FacebookCallback<LoginResult>() {
        @Override
        public void onSuccess(LoginResult loginResult) {
            if (AccessToken.getCurrentAccessToken()!=null){
                Profile profile = Profile.getCurrentProfile();
                LoginManager.getInstance().logInWithReadPermissions(getActivity(),
                        Arrays.asList("public_profile", "user_friends", "email", "user_photos"));
                profileFb(profile);
            }
        }

        @Override
        public void onCancel() {

        }

        @Override
        public void onError(FacebookException error) {

        }
    };

    public void profileFb(Profile profile) {
        if (profile!=null){
            photo = profile.getId().toString();
        }
        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        JSONObject json = response.getJSONObject();
                        try {
                            if (json!=null){
                                id = json.getString("id");
                                fullname = json.getString("name");
                                email = json.getString("email");
                                gender = json.getString("gender");

                                Toast.makeText(getActivity().getApplicationContext(), "Success !" + fullname, Toast.LENGTH_LONG).show();
                                Toast.makeText(getActivity().getApplicationContext(), "Success !" + email, Toast.LENGTH_LONG).show();
                                Toast.makeText(getActivity().getApplicationContext(), "Success !" + gender, Toast.LENGTH_LONG).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,email,gender");
        request.setParameters(parameters);
        request.executeAsync();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setFragment(this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View facebooksignup = inflater.inflate(R.layout.activity_registerfacebook,container,false);
        return facebooksignup;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getActivity());
        callbackManager = CallbackManager.Factory.create();
        accessToken = AccessToken.getCurrentAccessToken();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                Profile.fetchProfileForCurrentAccessToken();
            }
        };

        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {
                profileFb(currentProfile);
            }
        };


        accessTokenTracker.stopTracking();
        profileTracker.startTracking();

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    public void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.startTracking();
    }

    @Override
    public void onResume() {
        super.onResume();
        profileFb(Profile.getCurrentProfile());
        // Profile profile = Profile.getCurrentProfile();
        // displayMessage(Profile.getCurrentProfile());
//        AppEventsLogger.activateApp(getActivity().getApplicationContext());
    }

    @Override
    public void onPause() {
        super.onPause();
        accessTokenTracker.stopTracking();
        profileTracker.startTracking();
        profileFb(Profile.getCurrentProfile());
        //      AppEventsLogger.deactivateApp(getActivity().getApplicationContext());
    }

}
