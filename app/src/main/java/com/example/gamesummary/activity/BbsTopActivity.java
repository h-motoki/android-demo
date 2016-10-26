package com.example.gamesummary.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gamesummary.R;
import com.example.gamesummary.task.InformationAsyncTask;

import java.util.ArrayList;
import java.util.Map;

public class BbsTopActivity extends AbstructActivity {

    protected Intent intent;
    protected Activity activity = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bbs_top);

        TextView tileText = (TextView) findViewById(R.id.header_title);
        tileText.setText("BBS一覧");
        this.intent = getIntent();
    }

    @Override
    public void onBulletionBoardButtonClick(View v) {}

    public void onChatButtonClick(View v) {
        intent.setClassName("com.example.otyon.gamesummary", "com.example.otyon.gamesummary.activity.BbsListActivity");
        intent.putExtra("baseUrl", "https://gamy.jp/unisonleague/bbs/chats");
        activity.startActivity(intent);
    }

    public void onQuestionButtonClick(View v) {
        intent.setClassName("com.example.otyon.gamesummary", "com.example.otyon.gamesummary.activity.BbsListActivity");
        intent.putExtra("baseUrl", "https://gamy.jp/unisonleague/bbs/qa");
        activity.startActivity(intent);
    }

    public void onFriendButtonClick(View v) {
        intent.setClassName("com.example.otyon.gamesummary", "com.example.otyon.gamesummary.activity.BbsListActivity");
        intent.putExtra("baseUrl", "https://gamy.jp/unisonleague/bbs/other");
        activity.startActivity(intent);
    }

    public void onInvitationButtonClick(View v) {
        intent.setClassName("com.example.otyon.gamesummary", "com.example.otyon.gamesummary.activity.BbsListActivity");
        intent.putExtra("baseUrl", "https://gamy.jp/unisonleague/bbs/invites");
        activity.startActivity(intent);
    }
}
