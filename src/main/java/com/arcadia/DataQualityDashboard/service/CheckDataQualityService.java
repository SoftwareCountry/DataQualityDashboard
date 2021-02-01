package com.arcadia.DataQualityDashboard.service;

import com.arcadia.DataQualityDashboard.dto.DbSettings;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;

import static java.lang.String.format;

@AllArgsConstructor
@Service
public class CheckDataQualityService {

    private final StorageService storageService;

    private final ConcurrentHashMap<String, Integer> tasks = new ConcurrentHashMap<>();

    @SneakyThrows
    @PostConstruct
    public void init() {
        RConnectionWrapper rConnection = new RConnectionWrapper();
        rConnection.loadScripts();
        rConnection.close();
    }

    @Async
    public Future<String> checkDataQuality(DbSettings dbSettings, String userId) throws RException {
        RConnectionWrapper rConnection = new RConnectionWrapper();

        Integer pid = rConnection.getRServerPid();
        tasks.put(userId, pid);

        String jsonResult = rConnection.checkDataQuality(dbSettings);

        rConnection.close();

        String result = storageService.store(format("%s.json", userId), jsonResult);

        return new AsyncResult<>(result);
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
