package com.xyz.easyexcel.enumeration;

import org.apache.commons.codec.binary.StringUtils;

public enum PayToolEnum {

    WXPAY("WXPay", "微信"),
    ALIPAY("AliPay", "支付宝");
    // 工具
    private final String tool;
    // 描述
    private final String desc;

    // 构造器
    PayToolEnum(String tool, String desc) {
        this.tool = tool;
        this.desc = desc;
    }

    /**
     * 根据工具获取其描述
     *
     * @param tool
     * @return String
     */
    public static String getDescByTool(String tool) {
        for (PayToolEnum item : values()) {
            if (StringUtils.equals(item.tool, tool)) {
                return item.desc;
            }
        }
        return null;
    }

    /**
     * 根据描述获取其支付工具
     *
     * @param desc
     * @return String
     */
    public static String getToolByDesc(String desc) {
        for (PayToolEnum item : values()) {
            if (StringUtils.equals(item.desc, desc)) {
                return item.tool;
            }
        }
        return null;
    }

    public String getTool() {
        return tool;
    }

    public String getDesc() {
        return desc;
    }
}
