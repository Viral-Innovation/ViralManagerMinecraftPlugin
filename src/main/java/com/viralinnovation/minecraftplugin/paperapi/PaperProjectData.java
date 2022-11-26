package com.viralinnovation.minecraftplugin.paperapi;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PaperProjectData {

    private String projectId;
    private String projectName;
    private List<String> versionGroups;
    private List<String> versions;

    public PaperProjectData() {}

    public String findLatestVersion(String baseVersion) {
        if(versions.get(0).split("\\.").length > 2) {
            List<Integer> subVersion = new LinkedList<>();
            for(String string: versions) {
                if(string.split("\\.").length > 2) {
                    String[] versionSplit = string.split("\\.");
                    if (versionSplit[1].equals(baseVersion)) {
                        subVersion.add(Integer.valueOf(versionSplit[2]));
                    }
                }
            }
            return "1." + baseVersion + "." +  Collections.max(subVersion);
        }
        return "1." + baseVersion;
    }

    public String getProjectId() {
        return projectId;
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

    public List<String> getVersionGroups() {
        return versionGroups;
    }

    public void setVersionGroups(List<String> versionGroups) {
        this.versionGroups = versionGroups;
    }

    public List<String> getVersions() {
        return versions;
    }

    public void setVersions(List<String> versions) {
        this.versions = versions;
    }

    @Override
    public String toString() {
        return "PaperProjectData{" +
                "projectId='" + projectId + '\'' +
                ", projectName='" + projectName + '\'' +
                ", versionGroups=" + versionGroups +
                ", versions=" + versions +
                '}';
    }
}
