package io.hlr.huawei.enumerator;

public enum CliresType {
    PERM("0"),
    TEMPRES("1"),
    TEMPALLOW("2");

    private final String cliresType;

    CliresType(String cliresType) {
        this.cliresType = cliresType;
    }

    public String getCliresType() {
        return this.cliresType;
    }
}
