package com.cosmo.arquitecturamvpbase.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.model.CustomSharedPreferences;
import com.cosmo.arquitecturamvpbase.model.User;
import com.squareup.picasso.Picasso;

/**
 * Created by leidyzulu on 17/10/17.
 */

public class ProfileFragment extends BaseFragment{

    private ImageView imageView_profile;
    private TextView txtUsernameProfile;
    private TextView textView_likes;
    private TextView textView_followers;
    private TextView textView_following;
    private CustomSharedPreferences customSharedPreferences;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        loadViews(view);
        User user = customSharedPreferences.getObjectUser(Constants.USER);
        setData(user);
        return view;
    }

    private void setData(User user) {

        textView_likes.setText(String.valueOf(user.getLikes()));
        textView_followers.setText(String.valueOf(user.getFollowers()));
        textView_following.setText(String.valueOf(user.getFollowings()));
        txtUsernameProfile.setText(user.getUsername());
        Picasso.with(getActivity()).load(user.getPhoto()).into(imageView_profile);

    }

    private void loadViews(View view) {
        imageView_profile = (ImageView) view.findViewById(R.id.imageView_profile);
        textView_likes = (TextView) view.findViewById(R.id.textView_likes);
        textView_followers = (TextView) view.findViewById(R.id.textView_followers);
        textView_following = (TextView) view.findViewById(R.id.textView_following);
        txtUsernameProfile = (TextView) view.findViewById(R.id.txtUsernameProfile);
        customSharedPreferences = new CustomSharedPreferences(getActivity());


    }
}
