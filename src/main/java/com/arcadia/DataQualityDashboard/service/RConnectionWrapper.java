package com.arcadia.DataQualityDashboard.service;

import com.arcadia.DataQualityDashboard.dto.DbSettings;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.lang3.SystemUtils;
import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;

import static java.lang.String.format;
import static org.apache.commons.lang3.SystemUtils.IS_OS_UNIX;

@AllArgsConstructor
public class RConnectionWrapper {

    private final RConnection rConnection;

    @SneakyThrows
    public void loadScripts() {
        String rServerScriptCmd = "source('R/rServer.R')";
        String messageSenderScriptCmd = "source('R/messageSender.R')";
        String executionScriptCmd = "source('R/execution.R')";

        rConnection.voidEval(rServerScriptCmd);
        rConnection.voidEval(messageSenderScriptCmd);
        rConnection.voidEval(executionScriptCmd);
    }

    @SneakyThrows({REXPMismatchException.class, REngineException.class})
    public String checkDataQuality(DbSettings dbSettings, String userId) throws RException {
        String dqdCmd = format("dataQualityCheck(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\")",
                dbSettings.getDbType().toLowerCase(),
                dbSettings.getServer(),
                dbSettings.getPort(),
                format("%s.%s", dbSettings.getDatabase(), dbSettings.getSchema()),
                dbSettings.getUser(),
                dbSettings.getPassword(),
                userId
        );
        String runCmd = toTryCmd(dqdCmd);

        REXP runResponse = rConnection.parseAndEval(runCmd);

        if (runResponse.inherits("try-error")) {
            throw new RException(runResponse.asString());
        }

        return runResponse.asString();
    }

    @SneakyThrows
    public Integer getRServerPid() {
        String cmd = "Sys.getpid()";
        return rConnection.eval(cmd).asInteger();
    }

    @SneakyThrows
    public void cancel(int pid) {
        rConnection.eval("tools::pskill("+ pid + ")");
        rConnection.eval("tools::pskill("+ pid + ", tools::SIGKILL)");

        this.close();
    }

    @SneakyThrows
    public void close() {
        if (IS_OS_UNIX) {
            this.rConnection.close();
        } else {
            this.rConnection.shutdown();
        }
    }

    private String toTryCmd(String cmd) {
        return "try(eval(" + cmd + "),silent=TRUE)";
    }
}
