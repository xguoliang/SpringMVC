/**
 * Copyright 1993-2010 Kingdee Software (China), Co. Ltd., All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 */

package com.mongo.morphia.complex.core.cdt;

import java.math.BigDecimal;

/**
 * mongo db不支持BigDecimal，所以包装成这个类 
 * implemented by mgl 2010-7-29
 * 
 * @since 2010-7-16
 * @author pl
 */
public class Decimal extends Number implements Comparable<Decimal> {
    private static final long serialVersionUID = 7769209364924093092L;
    private String decimalString;

    public Decimal() {

    }

    public Decimal(BigDecimal bd) {
        setDecimalString(bd.toString());
    }
    
    public Decimal(String decimalString) {
    	setDecimalString(decimalString);
    }

    /**
     * @param decimalString
     *            the decimalString to set
     */
    public void setDecimalString(String decimalString) {
        this.decimalString = decimalString;
    }

    /**
     * @return the decimalString
     */
    public String getDecimalString() {
        return decimalString;
    }

    public BigDecimal get() {
        return new BigDecimal(getDecimalString());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(Decimal o) {
        return get().compareTo(o.get());
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Number#intValue()
     */
    @Override
    public int intValue() {
        return get().intValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Number#longValue()
     */
    @Override
    public long longValue() {
        return get().longValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Number#floatValue()
     */
    @Override
    public float floatValue() {
        // TODO Auto-generated method stub
        return get().floatValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Number#doubleValue()
     */
    @Override
    public double doubleValue() {
        return get().doubleValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return get().hashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null)
            return false;

        if (obj instanceof Decimal)
            return get().equals(((Decimal) obj).get());

        if (obj instanceof BigDecimal)
            return get().equals(obj);

        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return get().toString();
    }
    
    public Decimal add(Decimal d) {
        return new Decimal(this.get().add(d.get()));
    }
}
