package com.hlr.huawei.enumerator;

public enum OdbroamType {
    ODBOH("Baring of roaming when the user roams outside the home PLMN"),
    ODBOHC("Barring of roaming when the user roams outside the home PLMN country"),
    BROHPLMNCGPRS("Baring of GPRS when the user roams outside the home PLMN country"),
    NONE("(default) No barring");

    private final String odbroamType;

    OdbroamType(String odbroamType) {
        this.odbroamType = odbroamType;
    }

    public String getOdbroamType() {
        return this.odbroamType;
    }

}
