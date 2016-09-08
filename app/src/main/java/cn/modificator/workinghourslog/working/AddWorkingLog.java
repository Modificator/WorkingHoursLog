package cn.modificator.workinghourslog.working;

import android.support.design.widget.TextInputLayout;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.Toolbar;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.TransitionSet;
import android.widget.EditText;

import org.antlr.v4.Tool;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.modificator.workinghourslog.R;
import cn.modificator.workinghourslog.anim.ChangeColor;
import cn.modificator.workinghourslog.anim.ChangePosition;
import cn.modificator.workinghourslog.anim.CommentEnterTransition;
import cn.modificator.workinghourslog.anim.ShareElemEnterRevealTransition;
import cn.modificator.workinghourslog.data.WorkingLogDao;
import cn.modificator.workinghourslog.data.bean.WorkingHourLog;

public class AddWorkingLog extends AppCompatActivity {

    Toolbar toolbar = null;
    TextInputLayout descInputLayout;
    EditText etDesc;
    SimpleDateFormat dataFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.working_add_working_log);
//        getWindow().setEnterTransition(new CommentEnterTransition(this, findViewById(R.id.mAppBarLayout), findViewById(R.id.working_add_working_log)));
//        getWindow().setEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.working_log_add_enter));
        getWindow().setSharedElementEnterTransition(buildShareElemEnterSet());
        initToolbar();
    }

    private void initViews() {
        descInputLayout = (TextInputLayout) findViewById(R.id.descInputLayout);
        etDesc = (EditText) findViewById(R.id.etWorkingDesc);

        dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            String desc = etDesc.getText().toString();


            if (desc.isEmpty()) {
                descInputLayout.setErrorEnabled(true);
                descInputLayout.setError("请输入工作描述");
                return;
            }
            descInputLayout.setErrorEnabled(false);

            /*WorkingHourLog log = new WorkingHourLog();
            log.setPjtName(projectName);
            log.setWorkingGroup(groupName);
            log.setWorkDesc(desc);
            log.setStartTime(dataFormat.format(new Date()));
            WorkingLogDao.insert(log);*/
            finish();
        });


    }

    private TransitionSet buildShareElemEnterSet() {
        TransitionSet transitionSet = new TransitionSet();

        Transition changePos = new ChangePosition();
        changePos.setDuration(250);
        changePos.addTarget(R.id.working_add_working_log);
        transitionSet.addTransition(changePos);

//        Transition revealTransition = new ShareElemEnterRevealTransition(mCommentBox);
        Transition revealTransition = new ShareElemEnterRevealTransition(findViewById(R.id.working_add_working_log));
        transitionSet.addTransition(revealTransition);
        revealTransition.addTarget(R.id.working_add_working_log);
        revealTransition.setInterpolator(new FastOutSlowInInterpolator());
        revealTransition.setDuration(200);

        ChangeColor changeColor = new ChangeColor(0xff009688, getResources().getColor(R.color.white));
        changeColor.addTarget(R.id.working_add_working_log);
        changeColor.setDuration(250);

        transitionSet.addTransition(changeColor);

        transitionSet.setDuration(450);

        return transitionSet;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("添加日志");
        initViews();
    }
}
