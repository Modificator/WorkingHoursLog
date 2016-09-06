package cn.modificator.workinghourslog.data.bean;

/**
 * Created by Modificator
 * time: 16/9/6.下午9:33
 * des:create file and achieve model
 */

public class WorkingHourLog {

    int id;
    String pjtName;
    String workingGroup;
    String workDesc;
    String startTime;
    String endTime;
    String workCount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPjtName() {
        return pjtName;
    }

    public void setPjtName(String pjtName) {
        this.pjtName = pjtName;
    }

    public String getWorkingGroup() {
        return workingGroup;
    }

    public void setWorkingGroup(String workingGroup) {
        this.workingGroup = workingGroup;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getWorkCount() {
        return workCount;
    }

    public void setWorkCount(String workCount) {
        this.workCount = workCount;
    }
}
