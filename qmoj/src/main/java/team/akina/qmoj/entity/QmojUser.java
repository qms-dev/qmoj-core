package team.akina.qmoj.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class QmojUser extends QmojUserKey {
    private String name;

    private String password;

    private String role;

    private Byte isActivated;

    private String info;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"password\":\"")
                .append(password).append('\"');
        sb.append(",\"role\":\"")
                .append(role).append('\"');
        sb.append(",\"isActivated\":")
                .append(isActivated);
        sb.append(",\"info\":\"")
                .append(info).append('\"');
        sb.append(",\"updateTime\":\"")
                .append(new SimpleDateFormat( "yyyy-MM-dd hh:mm:ss").format(updateTime)).append('\"');

        sb.append(",\"email\":\"")
                .append(getEmail()).append('\"');
        sb.append(",\"userCode\":\"")
                .append(getUserCode()).append('\"');

        sb.append('}');
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
    }

    public Byte getIsActivated() {
        return isActivated;
    }

    public void setIsActivated(Byte isActivated) {
        this.isActivated = isActivated;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}