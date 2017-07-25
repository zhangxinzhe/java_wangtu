/*
 * @(#)DateConvert.java    Created on 2015年8月18日
 * Copyright (c) 2015 ZDSoft Networks, Inc. All rights reserved.
 * $Id$
 */
package net.zdsoft.common.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opensymphony.xwork2.conversion.impl.DefaultTypeConverter;

/**
 * @author Administrator
 * @version $Revision: 1.0 $, $Date: 2015年8月18日 上午10:29:33 $
 */
public class DateConverter extends DefaultTypeConverter {

    private static final Logger log = LoggerFactory.getLogger(DateConverter.class);

    private static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final String DATETMM_PATTERN = "yyyy-MM-dd HH:mm";

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    private static final String MONTH_PATTERN = "yyyy-MM";

    /**
     * Convert value between types
     */
    @Override
    public Object convertValue(Map ognlContext, Object value, Class toType) {
        Object result = null;
        if (toType == Date.class) {
            result = doConvertToDate(value);
        }
        else if (toType == String.class) {
            result = doConvertToString(value);
        }
        return result;
    }

    /**
     * Convert String to Date
     *
     * @param value
     * @return
     */
    private Date doConvertToDate(Object value) {
        Date result = null;
        if (value instanceof String) {
            if (StringUtils.isBlank((String) value)) {
                return null;
            }
            try {
                result = DateUtils.parseDate((String) value,
                        new String[] { DATE_PATTERN, DATETIME_PATTERN, DATETMM_PATTERN, MONTH_PATTERN });
            }
            catch (ParseException e1) {
                log.error(e1.getMessage(), e1);
            }

            // all patterns failed, try a milliseconds constructor
            if (result == null && StringUtils.isNotEmpty((String) value)) {

                try {
                    result = new Date(new Long((String) value).longValue());
                }
                catch (Exception e) {
                    log.error(e.getMessage(), e);
                }

            }

        }
        else if (value instanceof Object[]) {
            // let's try to convert the first element only
            Object[] array = (Object[]) value;

            if ((array != null) && (array.length >= 1)) {
                value = array[0];
                result = doConvertToDate(value);
            }

        }
        else if (Date.class.isAssignableFrom(value.getClass())) {
            result = (Date) value;
        }
        return result;
    }

    /**
     * Convert Date to String
     *
     * @param value
     * @return
     */
    private String doConvertToString(Object value) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATETIME_PATTERN);
        String result = null;
        if (value != null && value instanceof Date) {
            result = simpleDateFormat.format(value);
        }
        return result;
    }
}
