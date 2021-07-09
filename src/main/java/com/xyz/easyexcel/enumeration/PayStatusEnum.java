package com.xyz.easyexcel.enumeration;

import org.apache.commons.codec.binary.StringUtils;

public enum PayStatusEnum {
    A("A", "全部"),
    W("W", "待付款"),
    S("S", "已付款"),
    F("F", "付款失败"),
    C("C", "取消付款");
    // 状态
    private final String status;
    // 描述
    private final String desc;

    // 构造器
    PayStatusEnum(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    /**
     * 根据status获取desc
     *
     * @param status
     * @return String
     */
    public static String getDescByStatus(String status) {
        for (PayStatusEnum item : values()) {
            if (StringUtils.equals(item.status, status)) {
                return item.getDesc();
            }
        }
        return null;
    }

    /**
     * 根据desc获取status
     *
     * @param desc
     * @return String
     */
    public static String getStatusByDesc(String desc) {
        for (PayStatusEnum item : values()) {
            if (StringUtils.equals(item.desc, desc)) {
                return item.getStatus();
            }
        }
        return null;
    }


    public String getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
