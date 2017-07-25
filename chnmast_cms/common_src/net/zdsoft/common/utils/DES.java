/* 
 * @(#)DES.java    Created on 2010-12-16
 * Copyright (c) 2010 ZDSoft Networks, Inc. All rights reserved.
 * $Id: DES.java 12292 2011-02-15 01:34:02Z xiangll $
 */
package net.zdsoft.common.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import net.zdsoft.keel.util.SecurityUtils;

public class DES {
    private static String strDefaultKey = "zdsft.nt";

    private Cipher encryptCipher = null;

    private Cipher decryptCipher = null;

    /**
     * 将byte数组转换为表示16进制值的字符串， 如：byte[]{8,18}转换为：0813， 和public static byte[] hexStr2ByteArr(String strIn) 互为可逆的转换过程
     * 
     * @param arrB
     *            需要转换的byte数组
     * @return 转换后的字符串
     * @throws Exception
     *             本方法不处理任何异常，所有异常全部抛出
     */
    public static String byteArr2HexStr(byte[] arrB) throws Exception {
        int iLen = arrB.length;
        // 每个byte用两个字符才能表示，所以字符串的长度是数组长度的两倍
        StringBuffer sb = new StringBuffer(iLen * 2);
        for (int i = 0; i < iLen; i++) {
            int intTmp = arrB[i];
            // 把负数转换为正数
            while (intTmp < 0) {
                intTmp = intTmp + 256;
            }
            // 小于0F的数需要在前面补0
            if (intTmp < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(intTmp, 16));
        }
        return sb.toString();
    }

    /**
     * 将byte转成字符串
     * 
     * @param arr
     * @return
     */
    public static String byte2Hex(byte arr) {
        StringBuffer sb = new StringBuffer();
        int intTmp = arr;
        // 把负数转换为正数
        while (intTmp < 0) {
            intTmp = intTmp + 256;
        }
        // 小于0F的数需要在前面补0
        if (intTmp < 16) {
            sb.append("0");
        }
        sb.append(Integer.toString(intTmp, 16));
        return sb.toString();
    }

    /**
     * 将表示16进制值的字符串转换为byte数组， 和public static String byteArr2HexStr(byte[] arrB) 互为可逆的转换过程
     * 
     * @param strIn
     *            需要转换的字符串
     * @return 转换后的byte数组
     * @throws Exception
     *             本方法不处理任何异常，所有异常全部抛出
     * @author <a href="mailto:leo841001@163.com">LiGuoQing</a>
     */
    public static byte[] hexStr2ByteArr(String strIn) throws Exception {
        byte[] arrB = strIn.getBytes();
        int iLen = arrB.length;

        // 两个字符表示一个字节，所以字节数组长度是字符串长度除以2
        byte[] arrOut = new byte[iLen / 2];
        for (int i = 0; i < iLen; i = i + 2) {
            String strTmp = new String(arrB, i, 2);
            arrOut[i / 2] = (byte) Integer.parseInt(strTmp, 16);
        }

        return arrOut;
    }

    /**
     * 默认构造方法，使用默认密钥
     * 
     * @throws Exception
     */
    public DES() throws Exception {
        this(strDefaultKey);
    }

    /**
     * 指定密钥构造方法
     * 
     * @param strKey
     *            指定的密钥
     * @throws Exception
     */
    public DES(String key) throws Exception {
        encryptCipher = Cipher.getInstance("DES");
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes());
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
        encryptCipher.init(Cipher.ENCRYPT_MODE, secretKey);
        decryptCipher = Cipher.getInstance("DES");
        decryptCipher.init(Cipher.DECRYPT_MODE, secretKey);
    }

    /**
     * 加密字节数组
     * 
     * @param arrB
     *            需加密的字节数组
     * @return 加密后的字节数组
     * @throws Exception
     */
    public byte[] encrypt(byte[] arrB) throws Exception {
        return encryptCipher.doFinal(arrB);
    }

    /**
     * 加密字符串
     * 
     * @param strIn
     *            需加密的字符串
     * @return 加密后的字符串
     * @throws Exception
     */
    public String encrypt(String strIn) throws Exception {
        return byteArr2HexStr(encrypt(getByte(strIn.getBytes())));
    }

    /**
     * 字符串补ascii的零
     */
    public static byte[] getByte(byte[] bts) {
        byte[] newbyte = null;
        if (bts.length % 8 == 0) {
            newbyte = bts;
        }
        else {
            newbyte = new byte[bts.length + 8 - bts.length % 8];
            for (int i = 0; i < bts.length; i++) {
                newbyte[i] = bts[i];
            }
            for (int j = 0; j < 8 - bts.length % 8; j++) {
                newbyte[j + bts.length] = 0;
            }
        }
        return newbyte;
    }

    /**
     * 解密字节数组
     * 
     * @param arrB
     *            需解密的字节数组
     * @return 解密后的字节数组
     * @throws Exception
     */
    public byte[] decrypt(byte[] arrB) throws Exception {
        return decryptCipher.doFinal(arrB);
    }

    /**
     * 解密字符串
     * 
     * @param strIn
     *            需解密的字符串
     * @return 解密后的字符串 过滤掉ascii为0的数据，此值为加密时位数不够而补
     * @throws Exception
     */
    public String decrypt(String strIn) throws Exception {
        int count = 0;
        byte[] bytes = decrypt(hexStr2ByteArr(strIn));

        for (byte bt : bytes) {
            if (bt != 0) {
                count++;
            }
        }
        int count2 = 0;
        byte[] bytess = new byte[count];
        for (byte bt : bytes) {
            if (bt != 0) {
                bytess[count2] = bt;
                count2++;
            }
        }
        return new String(bytess);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            String test2 = "20101222330106000001\\n\\r12345678\\n\\r50\\n\\r123456789abcdefghijklmnopqrstuvw";
            DES des = new DES();// 自定义密钥
            System.out.println("加密前的字符：" + test2);
            System.out.println("默认加密后的字符：" + des.encrypt(test2));
            System.out.println("默认解密的字符：" + des.decrypt(des.encrypt(test2)));
            System.out.println("加密后的字符：" + des.encodeStr(test2));
            System.out.println("解密后的字符：" + des.decodeStr(des.encodeStr(test2)));
            System.out
                    .println("解密后的字符2："
                            + des.decodeStr("962A924290EE93588E7D88D2FB3A726A9EA98DB51161580DA43E24E1169D74DB5595A041545191D818F2909DB521A17FE6D9FDDC9EBA50A705BF0FF76C51A7F1973FA2EC8B20F17C2FFAFEB105DECB107E46DDC3FF009D443F30A2A439ADC9A8"));
            System.out
                    .println("解密批量充值的字符串："
                            + des.decodeStr("80882F5432E3D42EC3CE9E166681048DD9A9C7B5FB61F70DEA3EB5E1F19DC8DBFE71013BAB4785111B8DBD9420EB637AED2ED261FD32F9F94B71F370063250B90CFF20FB1A00682531223AE2F7197062B64E6ED3CF90F6A775EF094E05CF386E"));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encodeStr(String encodeStr) throws Exception {
        // if (encodeStr.length() % 8 != 0) {
        // throw new Exception("待加密字符串长度必须为8倍数");
        // }
        StringBuffer result = new StringBuffer();
        String str = "";
        byte[] newbyte = getByte(encodeStr.getBytes());
        // encodeStr = hexStr2Str(new String(newbyte));
        String md5str = SecurityUtils.encodeByMD5(newbyte).toUpperCase();
        // System.out.println("encode md5:" + md5str);
        byte[] md5byte = md5str.getBytes();
        String desstr = encrypt(encodeStr);
        desstr = desstr.substring(0, desstr.length() - 16);
        // System.out.println("encode des:" + desstr);
        byte[] desbyte = desstr.getBytes();
        for (int i = 0; i < desbyte.length / 2 || i < md5byte.length / 2; i++) {
            if (i < md5byte.length / 2) {
                result.append(byte2Hex(md5byte[2 * i]));
                result.append(byte2Hex(md5byte[2 * i + 1]));
            }
            if (i < desbyte.length / 2) {
                result.append(byte2Hex(desbyte[2 * i]));
                result.append(byte2Hex(desbyte[2 * i + 1]));
            }
        }
        str = hexStr2Str(result.toString());
        return str.toUpperCase();
    }

    public String decodeStr(String decodeStr) throws Exception {
        String result = "";
        int maxMixLength = 16;
        int nNewSrcDataLen = decodeStr.length() / 2;
        String[] strs = new String[nNewSrcDataLen];
        StringBuffer md5str = new StringBuffer();
        String[] desstr = new String[nNewSrcDataLen - maxMixLength];
        StringBuffer desbuffer = new StringBuffer();
        for (int i = 0; i < nNewSrcDataLen; i++) {
            String tmpstr = decodeStr.substring(i * 2, i * 2 + 2);
            strs[i] = byteArr2HexStr(tmpstr.getBytes());
        }
        int k = 0;
        int t = 0;
        for (int j = 0; j < strs.length; j++) {
            if ((j % 2 == 0 || (j >= (strs.length - maxMixLength) * 2)) && k < maxMixLength) {
                md5str.append(strs[j]);
                k++;
            }
            else {
                desstr[t] = strs[j];
                desbuffer.append(strs[j]);
                t++;
            }
        }
        // System.out.println("decode md5:" + hexStr2Str(md5str.toString()));
        // System.out.println("decode des:" + desbuffer.toString());
        result = decrypt(hexStr2Str(desbuffer.toString()) + "d79d5d92151d0537");
        return result;
    }

    /**
     * 
     * 十六进制转换字符串
     */

    public static String hexStr2Str(String hexStr) {
        String str = "0123456789ABCDEF";
        char[] hexs = hexStr.toCharArray();
        byte[] bytes = new byte[hexStr.length() / 2];
        int n;
        for (int i = 0; i < bytes.length; i++) {
            n = str.indexOf(hexs[2 * i]) * 16;
            n += str.indexOf(hexs[2 * i + 1]);
            bytes[i] = (byte) (n & 0xff);
        }
        return new String(bytes);
    }

}
