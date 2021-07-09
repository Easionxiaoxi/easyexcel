package com.xyz.easyexcel.enumeration;

import org.apache.commons.codec.binary.StringUtils;

public enum NeedInvoiceEnum {

    HAS("1", "有"),
    NONE("0", "无");

    // 是否需要发票
    private final String need;
    // 描述
    private final String desc;

    NeedInvoiceEnum(String need, String desc) {
        this.need = need;
        this.desc = desc;
    }

    /**
     * 根据need获取desc
     *
     * @param need
     * @return String
     */
    public static String getDescByNeed(String need) {
        for (NeedInvoiceEnum item : values()) {
            if (StringUtils.equals(item.need, need)) {
                return item.getDesc();
            }
        }
        return null;
    }

    /**
     * 根据desc获取need
     *
     * @param desc
     * @return String
     */
    public static String getNeedByDesc(String desc) {
        for (NeedInvoiceEnum item : values()) {
            if (StringUtils.equals(item.desc, desc)) {
                return item.getNeed();
            }
        }
        return null;
    }

    public String getNeed() {
        return need;
    }

    public String getDesc() {
        return desc;
    }
}
