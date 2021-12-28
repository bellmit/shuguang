package com.sofn.core.util.encrypt;

import com.sofn.core.support.security.Hex;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by Administrator on 2016/11/24.
 * RSA自定义存放系数的类
 */
public class RSAKeyPair implements Serializable {
    public RSAKeyPair(){}

    /**
     *
     * @param hexModulus RSA系数
     * @param hexPrivateExponent 私钥指数
     * @param hexPublicExponent 公钥指数
     */
    public RSAKeyPair(BigInteger hexModulus, BigInteger hexPrivateExponent, BigInteger hexPublicExponent){
        this.hexModulus=new String(Hex.encodeHex(hexModulus.toByteArray()));
        this.hexPrivateExponent=new String(Hex.encodeHex(hexPrivateExponent.toByteArray()));
        this.hexPublicExponent=new String(Hex.encodeHex(hexPublicExponent.toByteArray()));
    }
    /**
     * RSA系数
     */
    private String hexModulus;
    /**
     * 私钥指数
     */
    private String hexPrivateExponent;
    /**
     * 公钥指数
     */
    private String hexPublicExponent;

    public String getHexModulus() {
        return hexModulus;
    }

    public void setHexModulus(String hexModulus) {
        this.hexModulus = hexModulus;
    }

    public String getHexPrivateExponent() {
        return hexPrivateExponent;
    }

    public void setHexPrivateExponent(String hexPrivateExponent) {
        this.hexPrivateExponent = hexPrivateExponent;
    }

    public String getHexPublicExponent() {
        return hexPublicExponent;
    }

    public void setHexPublicExponent(String hexPublicExponent) {
        this.hexPublicExponent = hexPublicExponent;
    }
}

