package com.mohy.mohamed.sunshinezeft;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.ShareActionProvider;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {
    public DetailActivityFragment() {
        setHasOptionsMenu(true);
    }
  private static final String Share_hashtag = " #Weather_App";
    private static final String LOG_TAG =DetailActivityFragment.class.getSimpleName();
    private String mforecastsrting;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater){
        menuInflater.inflate(R.menu.detail_fragment,menu);
        MenuItem  menuItem = menu.findItem(R.id.action_share);
        ShareActionProvider shareActionProvider = (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);
        if(shareActionProvider!=null){
            shareActionProvider.setShareIntent(CreateShareIntent());
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        int id = menuItem.getItemId();
        if (id ==R.id.action_settings){
            Intent intent = new Intent(getActivity(),SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_detail, container, false);
        Intent intent = getActivity().getIntent();
        if(intent!=null && intent.hasExtra(intent.EXTRA_TEXT)){
            String s = intent.getStringExtra(intent.EXTRA_TEXT);
            mforecastsrting=s;
            ((TextView)rootview.findViewById(R.id.detail_text)).setText(s);
        }
        setHasOptionsMenu(true);
        return rootview;
    }
    private Intent CreateShareIntent(){
        Intent ShareIntent = new Intent(Intent.ACTION_SEND);
        ShareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        ShareIntent.setType("text/plain");
        ShareIntent.putExtra(Intent.EXTRA_TEXT,mforecastsrting+Share_hashtag);
        return ShareIntent;
    }
}
