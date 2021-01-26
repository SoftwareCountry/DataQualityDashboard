package com.arcadia.DataQualityDashboard.service;

import com.arcadia.DataQualityDashboard.dto.DbSettings;
import lombok.SneakyThrows;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static java.lang.String.format;

@Service
public class RServerService {

    private RConnection rConnection;

    public RServerService() throws RserveException {
    }

    @SneakyThrows
    @PostConstruct
    public void init() {
        rConnection = new RConnection();

        String executionScriptCmd = "source('R/execution.R')";
        String rServerScriptCmd = "source('R/rServer.R')";

        rConnection.voidEval(executionScriptCmd);
        rConnection.voidEval(rServerScriptCmd);
    }

    @PreDestroy
    public void destroy() {
        rConnection.close();
    };

    public String dataQualityCheck(DbSettings dbSettings) throws REngineException, REXPMismatchException, RException {
        String dqdCmd = format("dataQualityCheck(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\")",
                dbSettings.getDbType(),
                dbSettings.getServer(),
                dbSettings.getPort(),
                format("%s.%s", dbSettings.getDatabase(), dbSettings.getSchema()),
                dbSettings.getUser(),
                dbSettings.getPassword()
        );

        String runCmd = toTryCmd(dqdCmd);

        REXP runResponse = rConnection.parseAndEval(runCmd);

        if (runResponse.inherits("try-error")) {
            throw new RException(runResponse.asString());
        }

        return runResponse.asString();
    }

    private String toTryCmd(String cmd) {
        return "try(eval(" + cmd + "),silent=TRUE)";
    }
}
