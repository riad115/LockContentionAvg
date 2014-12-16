package com.LockContentionAvg.Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class LockContentionAvg {

	/**
	 * @param args
	 */
	public static HashMap<String, Double> lockContention = new HashMap<String, Double>();
	public static HashMap<Integer, Double> threadTime = new HashMap<Integer, Double>();
	public static List<String> headers = new ArrayList<String>();
	public static void main(String[] args) {
		// TODO Agenerated method stub
		//String inputFileName = args[0];
		//System.out.println("Input file: " + inputFileName);
		//readInputFile(inputFileName);
		for (int i=0;i<10;i++){
			String inputFileName = args[i];
			System.out.println("Input file: " + inputFileName);
			readInputFile(inputFileName);
		}
		
		//System.out.println(lockContention.keySet());
		//System.out.println(lockContention.values());
		//System.out.println(threadTime.keySet());
		//System.out.println(threadTime.values());
		
		try {
			BufferedWriter br = new BufferedWriter(new FileWriter("C:\\Users\\Stigmatized\\Desktop\\canneal.csv"));
			StringBuilder sb = new StringBuilder();
			Double value;
			headers = Collections.list(Collections.enumeration(lockContention.keySet()));
			for (Integer key : threadTime.keySet()) {
				headers.add("Thread"+key.toString());
			}
			
			for (int i=0;i<headers.size();i++){
				sb.append(headers.get(i));
				sb.append(",");
			}
			
			sb.append("\n");
			for (String key : lockContention.keySet()) {
			    value=lockContention.get(key);
			    value =value/10;
			    sb.append(value.toString());
			    sb.append(",");
				//System.out.println("Thread: " + ++i +" Avg. waiting time: "+ ((double) waitTimeThreadSorted.get(key)/(double) value));
			}
			for (Integer key : threadTime.keySet()) {
			    value=threadTime.get(key);
			    value =value/10;
			    sb.append(value.toString());
			    sb.append(",");
				//System.out.println("Thread: " + ++i +" Avg. waiting time: "+ ((double) waitTimeThreadSorted.get(key)/(double) value));
			}
			//sb.append("something");
			br.write(sb.toString());
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	public static void readInputFile(String FileName){
	    
	    String line = null;
	    
	    //List<String> list = new ArrayList<String>();
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(FileName));
	        while((line = reader.readLine()) != null){
	        	if ( line.trim().length() == 0 ) {  
	        	    continue;  // Skip blank lines  
	        	  }
	        	//String[] vals = line.split("\\s+");
	        	String[] vals = line.split("[-:]+");
	        	for(int i=0;i<vals.length;i++){
	        		vals[i].replaceAll("\\s+","");
	        		//System.out.println(i+" "+vals[i]);
	        	}
	        	//System.out.println(vals[0]);
	        	if(vals[0].replaceAll("\\s+","").matches("Thread")){
	        		
	        		if(threadTime.containsKey(Integer.parseInt(vals[1].replaceAll("\\s+","")))){
	        			//System.out.println("Inside");
	        			double y = threadTime.get(Integer.parseInt(vals[1].replaceAll("\\s+",""))) + Double.parseDouble(vals[3].replaceAll("\\s+",""));
	        			threadTime.put(Integer.parseInt(vals[1].replaceAll("\\s+","")), y);
	        			
	        		}
	        		
	        		else{
	        			threadTime.put(Integer.parseInt(vals[1].replaceAll("\\s+","")), Double.parseDouble(vals[3].replaceAll("\\s+","")));;
	        		}
	        	}
	        	else{
	        		if(lockContention.containsKey(vals[0])){
	        			double y = lockContention.get(vals[0]) + Double.parseDouble(vals[1].replaceAll("\\s+",""));
	        			lockContention.put(vals[0], y);
	        			
	        		}
	        		
	        		else{
	        			lockContention.put(vals[0], Double.parseDouble(vals[1]));;
	        		}
	        	}
	        }
	        
	        reader.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	}
}
