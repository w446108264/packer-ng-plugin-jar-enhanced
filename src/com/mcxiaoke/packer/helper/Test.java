package com.mcxiaoke.packer.helper;

import com.mcxiaoke.packer.helper.PackerNg.Helper;

/**
 * Packer-ng-plugin-jar 
 * Using the command to increased channel information for Apk
 * Base on {@link https://github.com/mcxiaoke/packer-ng-plugin}
 * Enhanced version {@link https://github.com/w446108264/packer-ng-plugin-jar}
 * Copyright 2016 Sj <shengjun8486@gmail.com>
 * @author sj
 *
 */
public class Test {

	  public static void main(String[] args) { 

		  Helper.println("--->");
		  for(String str : args) {
			  Helper.println(str);
		  }
		  
//		  String[] test = null;
		  
//	      test = {"/Users/sj/Desktop/yyy/test.apk","--m","/Users/sj/Desktop/yyy/markets.txt"}; 
		  String[] test = {"/Users/sj/Desktop/yyy/test.apk","--c","#iloveyou3","iloveyou2"," #------------------","111"};
		  
		  test = test == null ? args : test;
	      PackerNgEnhanced packerNgEnhanced = new PackerNgEnhanced(test);
	      packerNgEnhanced.run();
	  }
}
