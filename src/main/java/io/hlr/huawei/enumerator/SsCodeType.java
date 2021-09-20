package io.hlr.huawei.enumerator;

public enum SsCodeType {
    CFU, /* call forwarding unconditional */
    CFB, /* call forwarding on busy*/
    CFNRY, /* call forwarding on no reply */
    CFNRC, /* call forwarding on no reachable */
    CFD, /* default forwarding */
    CFDCFB, /* CFD replaces CFB */
    CFDCFNRY, /* CFD replaces CFNRy */
    CFDCFNRC, /* CFD replaces CFNRc */
    SPNSERVICE /* Single Personal Number service*/
}
