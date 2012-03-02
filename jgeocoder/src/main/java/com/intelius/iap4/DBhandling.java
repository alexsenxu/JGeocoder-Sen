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
package com.intelius.iap4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.lang.builder.ToStringBuilder;



/**
 *
 * @author sxu
 */
class TigerLineHit{
  long tlid;
  String streetNum; //FIXME this doesnt belong here, putting it here for now because of laziness
  String frAddR;
  String frAddL;
  String toAddR;
  String toAddL;
  String zipL;
  String zipR;
  float toLat;
  float toLon;
  float frLat;
  float frLon;
  float lon1;
  float lon2;
  float lon3;
  float lon4;
  float lon5;
  float lon6;
  float lon7;
  float lon8;
  float lon9;
  float lon10;
  float lat1;
  float lat2;
  float lat3;
  float lat4;
  float lat5;
  float lat6;
  float lat7;
  float lat8;
  float lat9;
  float lat10;
  String fedirp; //pre dir
  String fetype;
  String fedirs; //suffix dir
  
  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }
}



public class DBhandling {
    
    private static final String TIGER_QUERY = "select t.tlid, t.fraddr, t.fraddl, t.toaddr, t.toaddl,"+ 
" t.zipL, t.zipR, t.tolat, t.tolong, t.frlong, t.frlat,"+  
" t.long1, t.lat1, t.long2, t.lat2, t.long3, t.lat3, t.long4, t.lat4,"+
" t.long5, t.lat5, t.long6, t.lat6, t.long7, t.lat7, t.long8, t.lat8,"+
" t.long9, t.lat9, t.long10, t.lat10, t.fedirp, t.fetype, t.fedirs from TIGER_{0} t where t.fename = ? and "+
"(" + 
"       (t.fraddL <= ? and t.toaddL >= ?) or (t.fraddL >= ? and t.toaddL <= ?) "+
"    or (t.fraddR <= ? and t.toaddR >= ?) or (t.fraddR >= ? and t.toaddR <= ?) "+
")" +  
"  and (t.zipL = ? or t.zipR = ?)";
    
    private static String generateSelectQuery(String state){
        if(state==null || state.length() != 2){
            throw new IllegalArgumentException(state+" is not a valid 2 letter state code");
        }
            return MessageFormat.format(TIGER_QUERY, state);
    }
    
    public static void main(String[] args){
        
        String _tigerDs = "jdbc:h2:/home/sxu/playground/tiger";
        ResultSet rs = null;
        PreparedStatement ps = null;
        List<TigerLineHit> ret = new ArrayList<TigerLineHit>();
        try {
    //      if (_tigerDs instanceof JdbcDataSource) {
    //        JdbcDataSource ds = (JdbcDataSource) _tigerDs;
    //        conn = ds.getPooledConnection().getConnection();
    //      }else{
    //        conn = _tigerDs.getConnection();
    //      }

         //try address "540 westerly parkway, state college, pa 16801"

          Class.forName("org.h2.Driver");
          Connection conn = DriverManager.getConnection(_tigerDs, "sa", "");
          ps = conn.prepareStatement(generateSelectQuery("PA"));
          int i=1;
          String streetNum="540";
          String zip="16801";
          ps.setString(i++, "Westerly");
          ps.setString(i++, streetNum);
          ps.setString(i++, streetNum);
          ps.setString(i++, streetNum);
          ps.setString(i++, streetNum);
          ps.setString(i++, streetNum);
          ps.setString(i++, streetNum);
          ps.setString(i++, streetNum);
          ps.setString(i++, streetNum);
          ps.setString(i++, zip);
          ps.setString(i++, zip);
          rs = ps.executeQuery();
          while(rs.next()){
            TigerLineHit hit = new TigerLineHit();
            hit.streetNum = streetNum;
            hit.tlid = rs.getLong("tlid");
            hit.frAddL = rs.getString("fraddl");
            hit.frAddR = rs.getString("fraddr");
            hit.toAddL = rs.getString("toaddl");
            hit.toAddR = rs.getString("toaddr");
            hit.zipL = rs.getString("zipL");
            hit.zipR = rs.getString("zipR");
            hit.toLat = rs.getFloat("tolat");
            hit.toLon = rs.getFloat("tolong");
            hit.frLat = rs.getFloat("frlat");
            hit.frLon = rs.getFloat("tolong");
            hit.lat1 = rs.getFloat("lat1");
            hit.lat2 = rs.getFloat("lat2");
            hit.lat3 = rs.getFloat("lat3");
            hit.lat4 = rs.getFloat("lat4");
            hit.lat5 = rs.getFloat("lat5");
            hit.lat6 = rs.getFloat("lat6");
            hit.lat7 = rs.getFloat("lat7");
            hit.lat8 = rs.getFloat("lat8");
            hit.lat9 = rs.getFloat("lat9");
            hit.lat10 = rs.getFloat("lat10");
            hit.lon1 = rs.getFloat("long1");
            hit.lon2 = rs.getFloat("long2");
            hit.lon3 = rs.getFloat("long3");
            hit.lon4 = rs.getFloat("long4");
            hit.lon5 = rs.getFloat("long5");
            hit.lon6 = rs.getFloat("long6");
            hit.lon7 = rs.getFloat("long7");
            hit.lon8 = rs.getFloat("long8");
            hit.lon9 = rs.getFloat("long9");
            hit.lon10 = rs.getFloat("long10");
            hit.fedirp = rs.getString("fedirp");
            hit.fetype = rs.getString("fetype");
            hit.fedirs = rs.getString("fedirs");
            ret.add(hit);
            
//            
            System.out.println(ret.toString());
//
            }
        } catch (Exception e) {
          e.printStackTrace();
        }finally{
          //DbUtils.closeQuietly(conn);
          DbUtils.closeQuietly(rs);
          DbUtils.closeQuietly(ps);
        }
        //return ret;
        }
}