package com.sofn.core.util.encrypt;


import java.io.Serializable;

/**
 * 公钥系数对象
 * @author qmy
 *
 */
public class PublicKeyMap implements Serializable{
    public PublicKeyMap(){};
    public PublicKeyMap(String modulus,String exponent){
        this.exponent=exponent;
        this.modulus=modulus;
    }
	private String modulus;
	 private String exponent;
	 public String getModulus() {
	  return modulus;
	 }
	 public void setModulus(String modulus) {
	  this.modulus = modulus;
	 }
	 public String getExponent() {
	  return exponent;
	 }
	 public void setExponent(String exponent) {
	  this.exponent = exponent;
	 }
	 @Override
	 public String toString() {
	  return "PublicKeyMap [modulus=" + modulus + ", exponent=" + exponent
	    + "]";
	 }
	}
