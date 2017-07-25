/*
 * @(#)KeyValue.java    Created on 2014-4-4
 * Copyright (c) 2014 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.entity;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 值与名称对
 *
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2014-4-4 下午4:24:06 $
 */
public class ValueName<V, N> implements Serializable {
    private V value;
    private N nameValue;

    public ValueName(V value, N nameValue) {
        this.value = value;
        this.nameValue = nameValue;
    }

    @Override
    public String toString() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream;
        String v = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this);
            // v = byteArrayOutputStream.toString("ISO-8859-1");
            // v = java.net.URLEncoder.encode(v, "UTF-8");
            v = byteArrayOutputStream.toString();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return v;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public N getNameValue() {
        return nameValue;
    }

    public void setNameValue(N nameValue) {
        this.nameValue = nameValue;
    }

    public static void main(String[] aa) {
        ValueName<String, String> valueName = new ValueName<String, String>("12", "机构");
        System.out.print(valueName);
    }
}
