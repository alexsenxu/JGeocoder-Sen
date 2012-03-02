/*
 * Copyright 2012 sxu.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.sourceforge.jgeocoder.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.Map;

import java.util.concurrent.TimeUnit;
import net.sourceforge.jgeocoder.AddressComponent;
import net.sourceforge.jgeocoder.CommonUtils;
import net.sourceforge.jgeocoder.us.AddressParser;
import net.sourceforge.jgeocoder.us.AddressStandardizer;

/**
 *
 * @author sxu
 */
public class CASSTest {
   public static String delimiter = "|";
   public static String space=" ";
   public static String addressSep=", ";
   static boolean debug=false;
   static boolean debugggg=false;
   public static void main(String[] args){
      //test file, delimited by "|", left: normalized, right: messy
      //in other words, left: target; right: candidate
      //File cass_stage1= new File("NATLSTG1_processed_concise.txt");
      File cass_stage1= new File("NATLSTG1_processed_AddressOnly.txt");
      
      File cass_stage1_jgeocoder_testresult= new File("jgeocoderTestResult_normalized.txt");
      int totalTest=150000;
      int parsedCount=0;//how many was recognized as address
      int parsedmatchesTargetCount=0;//how many parsed address matches 
      int notParsedCount=0;//how many that jgeocoder fails to recognized as address
      int parsedNoMatchCount=0; //how many that jgeocoder recognizes as address but the parsed result doesn't match the target
        try {
            BufferedReader br=new BufferedReader(new FileReader(cass_stage1));
            if (!cass_stage1_jgeocoder_testresult.exists()) cass_stage1_jgeocoder_testresult.createNewFile();
            BufferedWriter bw=new BufferedWriter(new FileWriter(cass_stage1_jgeocoder_testresult));
            String temp;
            long start = System.currentTimeMillis();
            while ((temp=br.readLine())!=null){
                String[] testcase=temp.split("\\"+delimiter);
                String target=testcase[0];
                String candidate=testcase[1];
                
//                System.out.println(target);
//                System.out.println(candidate);
                boolean hasNoName=false;
                Map<AddressComponent, String> m = AddressParser.parseAddress(candidate);
                
                
                if (m!=null){
                        //String s=addressComponents.toString();
                        //System.out.println(s);
                        Map<AddressComponent, String> addressComponents = AddressStandardizer.normalizeParsedAddress(m);
                        String normalizedAddr="";
                        String parsed="";
                        parsedCount++;
        
                        if (addressComponents.get(AddressComponent.NAME)!=null)  normalizedAddr=normalizedAddr.concat(addressComponents.get(AddressComponent.NAME));
                        else hasNoName=true;
                        if (addressComponents.get(AddressComponent.NUMBER)!=null){
                            if (hasNoName) normalizedAddr=normalizedAddr.concat(addressComponents.get(AddressComponent.NUMBER)); 
                            else normalizedAddr=normalizedAddr.concat(addressSep+addressComponents.get(AddressComponent.NUMBER));
                        }
                        if (addressComponents.get(AddressComponent.PREDIR)!=null) normalizedAddr=normalizedAddr.concat(space+addressComponents.get(AddressComponent.PREDIR));
                        if (addressComponents.get(AddressComponent.STREET)!=null)  normalizedAddr=normalizedAddr.concat(space+addressComponents.get(AddressComponent.STREET));
                        if (addressComponents.get(AddressComponent.TYPE)!=null)  normalizedAddr=normalizedAddr.concat(space+addressComponents.get(AddressComponent.TYPE));
                        if (addressComponents.get(AddressComponent.LINE2)!=null)  normalizedAddr=normalizedAddr.concat(space+addressComponents.get(AddressComponent.LINE2));
                        if (addressComponents.get(AddressComponent.CITY)!=null)  normalizedAddr=normalizedAddr.concat(addressSep+addressComponents.get(AddressComponent.CITY));
                        if (addressComponents.get(AddressComponent.STATE)!=null)  normalizedAddr=normalizedAddr.concat(space+addressComponents.get(AddressComponent.STATE));
                        if (addressComponents.get(AddressComponent.ZIP)!=null)  normalizedAddr=normalizedAddr.concat(space+addressComponents.get(AddressComponent.ZIP));
                        //System.out.println(parsed);
                        
                        
                        if (debugggg){//compared the cases where normalized are considered not matched and not normalized are considered matched
                            if (m.get(AddressComponent.NAME)!=null)  parsed=parsed.concat(m.get(AddressComponent.NAME));
                            else hasNoName=true;
                            if (m.get(AddressComponent.NUMBER)!=null){
                                if (hasNoName) parsed=parsed.concat(m.get(AddressComponent.NUMBER)); 
                                else parsed=parsed.concat(addressSep+m.get(AddressComponent.NUMBER));
                            }
                            if (m.get(AddressComponent.PREDIR)!=null) parsed=parsed.concat(space+m.get(AddressComponent.PREDIR));
                            if (m.get(AddressComponent.STREET)!=null)  parsed=parsed.concat(space+m.get(AddressComponent.STREET));
                            if (m.get(AddressComponent.TYPE)!=null)  parsed=parsed.concat(space+m.get(AddressComponent.TYPE));
                            if (m.get(AddressComponent.LINE2)!=null)  parsed=parsed.concat(space+m.get(AddressComponent.LINE2));
                            if (m.get(AddressComponent.CITY)!=null)  parsed=parsed.concat(addressSep+m.get(AddressComponent.CITY));
                            if (m.get(AddressComponent.STATE)!=null)  parsed=parsed.concat(space+m.get(AddressComponent.STATE));
                            if (m.get(AddressComponent.ZIP)!=null)  parsed=parsed.concat(space+m.get(AddressComponent.ZIP));
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                        }
                        
                        
                        //get rid of the 9 digit zipcode in the target
                        
                        int length=target.length();
                        if (target.charAt(length-5)=='-') target=target.substring(0,length-5);
                            
                        
                        
                        if (normalizedAddr.equals(target)) {
                            parsedmatchesTargetCount++;
                            bw.append("Normalized&Match"+delimiter);
                            bw.append(addressComponents.toString()+delimiter);
                            if (debug){
                                System.out.println("Parsed&Match:\t"+candidate+delimiter+target);
                                System.out.println(addressComponents.toString());
                            }
                            
                        }else{
                            parsedNoMatchCount++;
                            
                            bw.append("Normalized&Not_Match"+delimiter);
                            bw.append(addressComponents.toString()+delimiter);
                            if (debug){
                                System.out.println("Parsed_Not Matched:\t"+candidate+delimiter+target);
                                System.out.println(addressComponents.toString());
                            }
                            if (debugggg){
                                if (parsed.equals(target)) System.out.println("parsed matches but normalized not match:"+addressComponents.toString()+"|"+m.toString()+"|"+target);
                            }
                            
                        }
                        
                }else{
                    notParsedCount++;
                    bw.append("Not_Parsed"+delimiter);
                    if (debug) System.out.println("Not_Parsed:\t"+candidate+delimiter+target);
                }
                bw.append(target+delimiter);
                bw.append(candidate+delimiter);
                
                bw.newLine();
                
            }
            CommonUtils.printElapsed(start, TimeUnit.SECONDS);
            System.out.println("Total Number of Records in the Test case:"+totalTest);
            System.out.println("Number of Records Parsed: "+parsedCount);
            System.out.println("Number of Records Parsed and matches Normalized Address exactly: "+parsedmatchesTargetCount);
            System.out.println("Number of Records Parsed and DOESNOT match: "+parsedNoMatchCount);
            System.out.println("Number of Records Failed to be recognized by JGeocoder: "+notParsedCount);
        } catch (IOException ex) {
            Logger.getLogger(CASSTest.class.getName()).log(Level.SEVERE, null, ex);
        }
      
//      String addr1 = "123 Avenue of art, philadelphia pa 12345";
//      Map<AddressComponent, String> addressComponents = AddressParser.parseAddress(addr1);
//      assertEquals("12345", addressComponents.get(AddressComponent.ZIP));
//      assertEquals("philadelphia", addressComponents.get(AddressComponent.CITY));
//      assertEquals("pa", addressComponents.get(AddressComponent.STATE));
//      assertEquals("123", addressComponents.get(AddressComponent.NUMBER));
//      addressComponents = AddressParser.parseAddress("123 FISH AND GAME rd philadelphia pa 12345");
//      assertEquals("12345", addressComponents.get(AddressComponent.ZIP));
//      assertEquals("philadelphia", addressComponents.get(AddressComponent.CITY));
//      assertEquals("pa", addressComponents.get(AddressComponent.STATE));
//      assertEquals("123", addressComponents.get(AddressComponent.NUMBER));
//      assertEquals("FISH AND GAME", addressComponents.get(AddressComponent.STREET));
//      assertEquals("rd", addressComponents.get(AddressComponent.TYPE));
   }
}
