package cn.modificator.workinghourslog;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.transition.Explode;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import cn.modificator.workinghourslog.history.WorkingLogHistory;
import cn.modificator.workinghourslog.working.AddWorkingLog;
import cn.modificator.workinghourslog.working.WorkingNowPage;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Toolbar toolbar;
    DrawerLayout drawer;
    Fragment[] fragments = null;
    FragmentManager mFragmentManager = null;
    AppCompatActivity mContext;
    int lastPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mFragmentManager = getFragmentManager();

        if (savedInstanceState == null) {
            WorkingNowPage workingNowPage = WorkingNowPage.getInstance();
            WorkingLogHistory workingLogHistory = WorkingLogHistory.getInstance();
            fragments = new Fragment[]{workingNowPage, workingLogHistory};
            mFragmentManager.beginTransaction()
                    .add(R.id.contentPanel, workingNowPage, WorkingNowPage.class.getName())
                    .add(R.id.contentPanel, workingLogHistory, WorkingLogHistory.class.getName())
                    .show(workingNowPage)
                    .hide(workingLogHistory)
                    .commit();

        } else {
            WorkingNowPage workingNowPage = (WorkingNowPage) mFragmentManager.findFragmentByTag(WorkingNowPage.class.getName());
            WorkingLogHistory workingLogHistory = (WorkingLogHistory) mFragmentManager.findFragmentByTag(WorkingLogHistory.class.getName());
            fragments = new Fragment[]{workingNowPage, workingLogHistory};
            mFragmentManager.beginTransaction()
                    .show(workingNowPage)
                    .hide(workingLogHistory)
                    .commit();
        }


        //初始化toolbar
        initToolbar();
    }

    private void initViews() {
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ((NavigationView) findViewById(R.id.nav_view)).setNavigationItemSelectedListener(this);

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
        if (id == R.id.menu_worklist) {
            mFragmentManager.beginTransaction()
                    .show(fragments[1])
                    .hide(fragments[0])
                    .commit();
        } else if (id == R.id.menu_nowWork) {
            mFragmentManager.beginTransaction()
                    .show(fragments[0])
                    .hide(fragments[1])
                    .commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
