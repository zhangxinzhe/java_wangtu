package net.zdsoft.common.utils;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.http.HttpServletRequest;

import net.zdsoft.common.entity.BaseUser;

/**
 * 登录工具类
 * 
 * @author winupon
 * 
 */
public abstract class LoginUtil {
    /**
     * Cookie之间分割符号
     */
    public static final String COOKIE_SPLIT = "|";

    /**
     * Cookie内容分隔符号
     */
    public static final String COOKIE_INFO_SPLIT = "_";

    /**
     * 加密算法,可用DES,DESede,Blowfish
     */
    private static final String Algorithm = "DESede";

    /**
     * 24字节的密钥
     */
    protected static final byte[] KEY = { 0x11, 0x22, 0x4F, 0x58, (byte) 0x88, 0x10, 0x40, 0x38, 0x28, 0x25, 0x79,
            0x51, (byte) 0xCB, (byte) 0xDD, 0x55, 0x66, 0x77, 0x29, 0x74, (byte) 0x98, 0x30, 0x40, 0x36, (byte) 0xE2 };
    /**
     * 访问机构id的properties中配置的key
     */
    public static final String AGENCYID_COOKIE_NAME = "agencyid_cookie_name";

    /**
     * 获取当前的domain
     * 
     * @return
     */
    abstract public String getDomain(HttpServletRequest request);

    /**
     * 登录用户信息Cookie的名字
     */
    abstract public String getUserInfoCookieName();

    /**
     * 获取真正的cookieName
     * 
     * @param name
     * @param request
     * @return
     */
    abstract public String getCookieName(String name, HttpServletRequest request);

    /**
     * 解析用户Cookie
     * 
     * @param userInfoCookie
     *            cookie值
     * @return
     */
    abstract public BaseUser getUserInfo(String userInfoCookie);

    /**
     * 从请求提交的cookie中得到用户信息
     * 
     * @param request
     * @return
     */
    public BaseUser getUserFromRequest(HttpServletRequest request) {
        return getUserInfo(CookieUtil.getCookieValueByName(request, getCookieName(getUserInfoCookieName(), request)));
    }

    /**
     * 加密
     * 
     * @param keybyte
     *            为加密密钥，长度为24字节
     * @param src
     *            为被加密的数据缓冲区（源）
     * @return
     */
    protected static byte[] encryptMode(byte[] keybyte, byte[] src) {
        try {
            // 添加新安全算法,如果用JCE就要把它添加进去
            Security.addProvider(new com.sun.crypto.provider.SunJCE());
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            // 加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            return c1.doFinal(src);
        }
        catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        }
        catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * 
     * @param keybyte
     *            为加密密钥，长度为24字节
     * @param src
     *            为加密后的缓冲区
     * @return
     */
    protected static byte[] decryptMode(byte[] keybyte, byte[] src) {
        try {
            // 添加新安全算法,如果用JCE就要把它添加进去
            Security.addProvider(new com.sun.crypto.provider.SunJCE());
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, Algorithm);
            // 解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            return c1.doFinal(src);
        }
        catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        }
        catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        }
        catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

    /**
     * 字节码转换成十六进制字符串
     * 
     * @param b
     * @return
     */
    protected static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1) {
                hs = hs + "0" + stmp;
            }
            else {
                hs = hs + stmp;
            }
            if (n < b.length - 1) {
                hs = hs + COOKIE_INFO_SPLIT;
            }
        }
        return hs.toUpperCase();
    }

    /**
     * 将十六进制字符串转换成字节码
     * 
     * @param hex
     * @return
     */
    protected static byte[] hex2byte(String hex) {
        String[] hexStr = hex.split(COOKIE_INFO_SPLIT);
        String _hex = hex.replaceAll(COOKIE_INFO_SPLIT, "");
        byte[] bts = new byte[hexStr.length];
        for (int i = 0; i < bts.length; i++) {
            bts[i] = (byte) Integer.parseInt(_hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bts;
    }

}
