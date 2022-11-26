package com.viralinnovation.minecraftplugin.paperapi;

import java.util.Collections;
import java.util.List;

public class PaperVersionData {

    private String projectId;
    private String projectName;
    private String version;
    private List<Integer> builds;

    public PaperVersionData() {}

    public String getProjectId() {
        return projectId;
    }

    public int getMaxBuild() {
        return Collections.max(builds);
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Integer> getBuilds() {
        return builds;
    }

    public void setBuilds(List<Integer> builds) {
        this.builds = builds;
    }

    @Override
    public String toString() {
        return "PaperVersionData{" +
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", version='" + version + '\'' +
                ", builds=" + builds +
                '}';
    }
}
