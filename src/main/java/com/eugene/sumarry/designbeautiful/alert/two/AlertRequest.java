package com.eugene.sumarry.designbeautiful.alert.two;

import java.io.Serializable;

/**
 * @author muyang
 * @create 2023/11/24 21:22
 */
public class AlertRequest implements Serializable {

    private Long requestCount;

    private Long successCount;

    private Long errorCount;

    public AlertRequest(Long requestCount, Long successCount, Long errorCount) {
        this.requestCount = requestCount;
        this.successCount = successCount;
        this.errorCount = errorCount;
    }

    public Long getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(Long requestCount) {
        this.requestCount = requestCount;
    }

    public Long getSuccessCount() {
        return successCount;
    }

    public void setSuccessCount(Long successCount) {
        this.successCount = successCount;
    }

    public Long getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Long errorCount) {
        this.errorCount = errorCount;
    }
}
