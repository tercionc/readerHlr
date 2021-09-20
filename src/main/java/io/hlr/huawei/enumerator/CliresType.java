package io.hlr.huawei.enumerator;

public enum CliresType {
    PERM(0),
    TEMPRES(1),
    TEMPALLOW(2);

    private final Integer value;

    CliresType(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

}
