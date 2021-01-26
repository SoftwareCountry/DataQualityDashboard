package com.arcadia.DataQualityDashboard.service;

import com.arcadia.DataQualityDashboard.dto.DbSettings;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DataQualityCheckService {

    private final ConcurrentHashMap<String, Integer> tasks = new ConcurrentHashMap<>();

    @SneakyThrows
    @PostConstruct
    public void init() {
        RConnectionWrapper rConnection = new RConnectionWrapper();
        rConnection.loadScripts();
        rConnection.close();
    }

    public String checkDataQuality(DbSettings dbSettings, String userId) throws RException {
        RConnectionWrapper rConnection = new RConnectionWrapper();

        Integer pid = rConnection.getRServerPid();
        tasks.put(userId, pid);

        String jsonResult = rConnection.checkDataQuality(dbSettings);

        rConnection.close();

        return jsonResult;
    }

    @SneakyThrows
    public void cancelCheckDataQualityProcess(String userId) {
        Integer pid = tasks.get(userId);

        if (pid != null) {
            RConnectionWrapper rConnection = new RConnectionWrapper();
            rConnection.cancel(pid);
            rConnection.close();
        }
    }

}
