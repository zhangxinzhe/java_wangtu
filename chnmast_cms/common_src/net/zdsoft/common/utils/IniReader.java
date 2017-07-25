package net.zdsoft.common.utils;

import java.io.BufferedReader;
import java.io.IOException;

public class IniReader {
    // 读取Ini文件配置信息
    public static String read(BufferedReader reader, String key) throws IOException {
        String line = null;
        String value = "";
        while ((line = reader.readLine()) != null) {
            // 带框
            if (line.matches("\\[.*\\]")) {

            }
            else if (line.matches(".*=.*")) {
                int i = line.indexOf("=");
                String name = line.substring(0, i);
                // 如获取到了 则跳出
                if (name.trim().equals(key)) {
                    value = line.substring(i + 1);
                    break;
                }
            }
        }
        return value;
    }
}
