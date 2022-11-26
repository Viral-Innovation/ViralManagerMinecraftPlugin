package com.viralinnovation.minecraftplugin.paperapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.viralinnovation.viralmanager.api.utils.ViralFileUtils;
import org.asynchttpclient.*;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

public final class PaperAPIManager {

    private final ObjectMapper objectMapper;

    public PaperAPIManager() {
        this.objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE);
    }

    public PaperProjectData getProjectData(String project) {
        PaperProjectData paperProjectData;
        try {
            URL url = new URL("https://api.papermc.io/v2/projects/" + project);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            paperProjectData = objectMapper.readValue(getFullResponse(con), PaperProjectData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return paperProjectData;
    }

    public PaperVersionData getProjectVersionData(String project, String version) {
        PaperVersionData paperVersionData;
        try {
            URL url = new URL("https://api.papermc.io/v2/projects/" + project + "/versions/" + version);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            paperVersionData = objectMapper.readValue(getFullResponse(con), PaperVersionData.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return paperVersionData;
    }

    public @NotNull String downloadBuild(String project, String baseVersion, String saveLocation) {
        PaperProjectData paperProjectData = getProjectData(project);
        String latestVersion = paperProjectData.findLatestVersion(baseVersion);
        PaperVersionData paperVersionData = getProjectVersionData(project, latestVersion);
        AsyncHttpClient client = Dsl.asyncHttpClient();
        FileOutputStream stream;
        try {
            stream = new FileOutputStream(saveLocation + "\\" + project + "-"
                    + latestVersion + "-" + paperVersionData.getMaxBuild() + ".jar");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            client.prepareGet("https://api.papermc.io/v2/projects/" + project
                    + "/versions/" + latestVersion + "/builds/"
                    + paperVersionData.getMaxBuild() + "/downloads/" + project + "-"
                    + latestVersion + "-" + paperVersionData.getMaxBuild() + ".jar").execute(new AsyncCompletionHandler<FileOutputStream>() {

                public State onBodyPartReceived(HttpResponseBodyPart bodyPart) throws Exception {
                    stream.getChannel().write(bodyPart.getBodyByteBuffer());
                    return State.CONTINUE;
                }

                @Override
                public FileOutputStream onCompleted(Response response) throws Exception {
                    return stream;
                }
            }).get();
            stream.getChannel().close();
            client.close();
        } catch (InterruptedException | ExecutionException | IOException e) {
            throw new RuntimeException(e);
        }
        return saveLocation + "\\" + project + "-"
                + latestVersion + "-" + paperVersionData.getMaxBuild() + ".jar";
    }

    private @NotNull String getFullResponse(@NotNull HttpURLConnection con) throws IOException {
        Reader streamReader = null;
        if (con.getResponseCode() > 299) {
            streamReader = new InputStreamReader(con.getErrorStream());
        } else {
            streamReader = new InputStreamReader(con.getInputStream());
        }
        BufferedReader in = new BufferedReader(streamReader);
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return content.toString();
    }
}
