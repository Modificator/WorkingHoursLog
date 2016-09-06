package cn.modificator.workinghourslog;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import cn.modificator.workinghourslog.working.AddWorkingLog;
import cn.modificator.workinghourslog.working.WorkingNowPage;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    FloatingActionButton btnAddLog;
    DrawerLayout drawer;
    Fragment[] fragments = null;
    FragmentManager mFragmentManager = null;
    AppCompatActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mFragmentManager = getFragmentManager();

        if (savedInstanceState == null) {
            WorkingNowPage workingNowPage = WorkingNowPage.getInstance();
            mFragmentManager.beginTransaction()
                    .add(R.id.contentPanel, workingNowPage, WorkingNowPage.class.getName())
                    .show(workingNowPage)
                    .commit();

        } else {
            WorkingNowPage workingNowPage = (WorkingNowPage) mFragmentManager.findFragmentByTag(WorkingNowPage.class.getName());
            mFragmentManager.beginTransaction()
                    .show(workingNowPage)
                    .commit();
        }


        //初始化toolbar
        initToolbar();
    }

    private void initViews() {
        btnAddLog = (FloatingActionButton) findViewById(R.id.fab);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);
        btnAddLog.setOnClickListener(v -> {
            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
                    .makeClipRevealAnimation(v, v.getTop(), v.getLeft(), v.getMeasuredWidth(), v.getMeasuredHeight());
            Intent intent = new Intent(mContext, AddWorkingLog.class);
            ActivityCompat.startActivity(mContext, intent, optionsCompat.toBundle());

        });
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //
        initViews();
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
