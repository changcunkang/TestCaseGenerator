package com.fico.testCaseGenerator.XSTream;

import com.fico.testCaseGenerator.util.TestCaseUtils;
import com.thoughtworks.xstream.converters.SingleValueConverter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author XiangbinYuan stevenYuan
 * @CreationDate 12/29/2017
 */
public class XStreamDateConverter implements SingleValueConverter {

    public boolean canConvert(Class arg0) {
        return Date.class == arg0;
    }

    public Object fromString(String arg0) {
        try {
            return new SimpleDateFormat(TestCaseUtils.DATE_FORMAT).parse(arg0);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String toString(Object arg0) {
        return new SimpleDateFormat(TestCaseUtils.DATE_FORMAT).format((Date)arg0);
    }
}
