package net.zdsoft.common.utils;

import java.util.UUID;

/**
 * GUID生成工具
 * 
 * @author shenl
 *
 */
public final class UUIDUtils {

    public static final String newId() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

    public static void main(String[] args) {
        System.out.println(newId());
    }
}
