package cn.modificator.workinghourslog.working;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import org.antlr.v4.Tool;

import java.text.SimpleDateFormat;
import java.util.Date;

import cn.modificator.workinghourslog.R;
import cn.modificator.workinghourslog.data.WorkingLogDao;
import cn.modificator.workinghourslog.data.bean.WorkingHourLog;

public class AddWorkingLog extends AppCompatActivity {

    Toolbar toolbar = null;
    AppCompatAutoCompleteTextView etProjectName;
    AppCompatAutoCompleteTextView etWorkingGroup;
    TextInputLayout pjtNameInputLayout;
    TextInputLayout groupInputLayout;
    TextInputLayout descInputLayout;
    EditText etDesc;
SimpleDateFormat dataFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.working_add_working_log);
        initToolbar();
    }

    private void initViews() {
        etProjectName = (AppCompatAutoCompleteTextView) findViewById(R.id.etProjectName);
        etWorkingGroup = (AppCompatAutoCompleteTextView) findViewById(R.id.etWorkingGroup);
        pjtNameInputLayout = (TextInputLayout) findViewById(R.id.pjtNameInputLayout);
        pjtNameInputLayout = (TextInputLayout) findViewById(R.id.pjtNameInputLayout);
        groupInputLayout = (TextInputLayout) findViewById(R.id.groupInputLayout);
        descInputLayout = (TextInputLayout) findViewById(R.id.descInputLayout);
        etDesc = (EditText) findViewById(R.id.etWorkingDesc);

        dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

        findViewById(R.id.btnAdd).setOnClickListener(v -> {
            String projectName = etProjectName.getText().toString();
            String groupName = etWorkingGroup.getText().toString();
            String desc = etDesc.getText().toString();

            if (projectName.isEmpty()) {
                pjtNameInputLayout.setErrorEnabled(true);
                pjtNameInputLayout.setError("请输入项目名称");
                return;
            }
            if (groupName.isEmpty()) {
                groupInputLayout.setErrorEnabled(true);
                groupInputLayout.setError("请输入工作组");
                return;
            }
            if (desc.isEmpty()) {
                descInputLayout.setErrorEnabled(true);
                descInputLayout.setError("请输入工作描述");
                return;
            }
            pjtNameInputLayout.setErrorEnabled(false);
            groupInputLayout.setErrorEnabled(false);
            descInputLayout.setErrorEnabled(false);

            WorkingHourLog log = new WorkingHourLog();
            log.setPjtName(projectName);
            log.setWorkingGroup(groupName);
            log.setWorkDesc(desc);
            log.setStartTime(dataFormat.format(new Date()));
            WorkingLogDao.insert(log);
            finish();
        });


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
