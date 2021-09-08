package com.hlr.huawei.enumerator;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SubType {
        UNKNOWN( "Desconhecido", 0 ),
        HYBRID( "Pos e Pre", 3 ),
        VPN( "Gestor on line", 8 ),
        POSTPAID( "Pos Pago", 5 ),
        PREPAID_BL( "Banda Larga pre-paga", 9 ),
        POSTPAID_BL( "Banda Larga pos-pago", 10 ),
        LIVRE( "Livre - fixo", 14 );

        public String desc;
        public int value;

        SubType(String desc, int value) {
            this.desc = desc;
            this.value = value;
        }

        private static final Map<String, Integer> subTypes = Stream.of( values() ).collect( Collectors.toMap(k -> k.desc, v -> v.value ) );

        public static int getValue (final String desc) {
            return subTypes.getOrDefault(desc, 0);
        }
}
