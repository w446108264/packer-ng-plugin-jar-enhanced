package com.mcxiaoke.packer.helper;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterDescription;
import com.mcxiaoke.packer.helper.PackerNg.Helper;
import com.mcxiaoke.packer.helper.PackerNg.MarketExistsException;

/**
 * Packer-ng-plugin-jar 
 * Using the command to increased channel information for Apk
 * Base on {@link https://github.com/mcxiaoke/packer-ng-plugin}
 * Enhanced version {@link https://github.com/w446108264/packer-ng-plugin-jar}
 * Copyright 2016 Sj <shengjun8486@gmail.com>
 * @author sj
 *
 */
public class PackerNgEnhanced {
	  
	private static final String PARAMETER_ERROR = "PARAMETER ERROR";
	
	private static final String USAGE_TEXT =
	            "For Help: java -jar PackerNg-x.x.x.jar --help ";
	
    private static final String INTRO_TEXT =
            "\nAttention: if your app using Android gradle plugin 2.2.0 or later, " +
                    "be sure to install one of the generated Apks to device or emulator, " +
                    "to ensure the apk can be installed without errors. " +
                    "More details please go to github " +
                    "https://github.com/mcxiaoke/packer-ng-plugin .\n";
    
    private String getHead() {
    	StringBuffer sb = new StringBuffer(); 
    	sb.append("\n");   
		sb.append("                           .-'      '-. \n"); 
		sb.append("                          /            \\ \n"); 
		sb.append("                         |              | \n"); 
		sb.append("                         |,  .-.  .-.  ,| \n"); 
		sb.append("                         | )(__/  \\__)( | \n"); 
		sb.append("                         |/     /\\     \\| \n"); 
		sb.append("            (@_          (_     ^^     _) \n"); 
		sb.append(" _           ) \\__________\\__|IIIIII|__/_____________________________ \n"); 
		sb.append("(_)Zhongdaxia{}<___________|-\\IIIIII/-|______________________________> \n"); 
		sb.append("             )_/           \\          / \n"); 
		sb.append("            (@              `--------`\n"); 
		sb.append("\n");  
		return sb.toString();
    }
    
	public PackerNgEnhanced(String args[]) {
		
		for(String str : args) {
			  Helper.println(str);
		  }
		
		JCommander jc = new JCommander(this, args);
		jc.setColumnSize(2000);  
	  
		/**
		 * for help
		 */
		if (this.help) { 
			StringBuffer sb = new StringBuffer();    
			sb.append(getHead());
			sb.append("\n");   
			sb.append("Packer-ng-plugin-jar\n");
			sb.append("Using the command to increased channel information for Apk\n"); 
			sb.append("Base on https://github.com/mcxiaoke/packer-ng-plugin\n"); 
			sb.append("Enhanced version https://github.com/w446108264/packer-ng-plugin-jar\n"); 
			sb.append("Copyright 2016 Sj <shengjun8486@gmail.com>\n");   
			sb.append("\n"); 
			sb.append("Usage: java -jar PackerNg-x.x.x.jar --help\n"); 
			sb.append("Usage: java -jar PackerNg-x.x.x.jar apkFile [outputDir] --m marketFile\n"); 
			sb.append("Usage: java -jar PackerNg-x.x.x.jar apkFile [outputDir] --c channelname1 channelname2 ...\n\n"); 
			jc.setProgramName(sb.toString());
			jc.usage();
			System.exit(1);
	        return;
		}
		  
		/**
		 * check apkfile
		 */
		if(filePaths.size()>0){ 
			apkFile = new File(filePaths.get(0));
			if (!apkFile.exists()) {
		         Helper.printErr("Apk file '" + apkFile.getAbsolutePath() +
		                    "' is not exists or not readable.");
		         Helper.println(USAGE_TEXT);
		         System.exit(1);
		         return;
		    }
		}else{ 
			Helper.printErr(PARAMETER_ERROR);
			Helper.println(USAGE_TEXT);
			System.exit(0);
		}
		 
		/**
		 * check output
		 */
		outputDir = new File(filePaths.size()>1 ? filePaths.get(1) : "apks");
		if (!outputDir.exists()) {
            outputDir.mkdirs();
        }
		
		/**
		 * check marketfile if not null
		 */
		if(marketFile!= null && marketFile.length() > 0) {
			marketF = new File(marketFile);
			if (!marketF.exists()) {
		         Helper.printErr("Market file '" + marketF.getAbsolutePath() +
		                    "' is not exists or not readable.");
		         Helper.println(USAGE_TEXT);
		         System.exit(1);
		         return;
		    } 
			try {
	            markets = Helper.parseMarkets(marketF);
	        } catch (IOException e) {
	            Helper.printErr("Market file parse failed.");
	            System.exit(1);
	        } 
			return;
		}
		
		/**
		 * check channel if not null
		 */
		if(channels== null || channels.size() == 0) {
			Helper.printErr(PARAMETER_ERROR); 
	        Helper.println(USAGE_TEXT);
	        System.exit(1);
	        return;
		} 
		markets = new ArrayList<String>(); 
		for(String channel : channels) {
			markets.add(channel.trim());
		} 
	}
 
	List<String> markets = null;
    File apkFile , outputDir, marketF; 
	public void run() { 
		
		if(apkFile == null || outputDir == null) {
			Helper.printErr("not init");
			System.exit(1);
            return;
		}
		
	     if (markets == null || markets.isEmpty()) {
	            Helper.printErr("No markets found.");
	            System.exit(1);
	            return;
	     }
	       
    	 Helper.println(getHead()); 
		 Helper.println("Apk File: " + apkFile.getAbsolutePath());
	     Helper.println("Market File: " + (marketF == null ? "" : marketF.getAbsolutePath()));
	     Helper.println("Output Dir: " + outputDir.getAbsolutePath() + "\n");  
    	 
	     final String baseName = Helper.getBaseName(apkFile.getName());
	        final String extName = Helper.getExtension(apkFile.getName());
	        int processed = 0;
	        try {
	            for (final String market : markets) {
	                final String apkName = baseName + "-" + market + "." + extName;
	                File destFile = new File(outputDir, apkName);
	                Helper.copyFile(apkFile, destFile);
	                Helper.writeMarket(destFile, market);
	                if (Helper.verifyMarket(destFile, market)) {
	                    ++processed;
	                    Helper.println("Generating apk " + apkName);
	                } else {
	                    destFile.delete();
	                    Helper.printErr("Failed to generate " + apkName);
	                }
	            }
	            Helper.println("[Success] All " + processed
	                    + " apks saved to " + outputDir.getAbsolutePath());
	            Helper.println(INTRO_TEXT);
	        } catch (MarketExistsException ex) {
	            Helper.printErr("Market info exists in '" + apkFile
	                    + "', please using a clean apk.");
	            System.exit(1);
	        } catch (IOException ex) {
	            Helper.printErr("" + ex);
	            System.exit(1);
	        }
	}
	
	@Parameter(description = "FilePaths include apkFile(can't be null) \n           and outputDir(The same path with jar when null) ")
	private List<String> filePaths = new ArrayList<>();
	 
	@Parameter(names = {"--help", "-help", "-?", "-h"}, description = "Get help for the particular command.")
	private boolean help;
	
	@Parameter(names = { "-m", "--m", "--marketFile" }, description = "for multiple channels form marketFile.txt\n      (These are to be construed as commentary which start with #)")
	public String marketFile;

	@Parameter(names = { "-c", "--c", "--channels" }, variableArity = true, description = "for multiple channels like --channels a1 a2 a3")
	public List<String> channels = new ArrayList<>();  
	 
}
