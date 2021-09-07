package com.hlr.huawei.enumerator;

public enum CurrentNamType {
    BOTH("MSC and SGSN"),
    NONGPRS("Only MSC"),
    GPRS("Only SGSN");

    private final String currentNamType;

    CurrentNamType(String currentNamType) {
        this.currentNamType = currentNamType;
    }

    public String getCurrentNamType() {
        return this.currentNamType;
    }
}
