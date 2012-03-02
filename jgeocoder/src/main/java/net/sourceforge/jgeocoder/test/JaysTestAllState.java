package net.sourceforge.jgeocoder.test;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import net.sourceforge.jgeocoder.AddressComponent;
import net.sourceforge.jgeocoder.CommonUtils;
import net.sourceforge.jgeocoder.GeocodeAcuracy;
import net.sourceforge.jgeocoder.JGeocodeAddress;
import net.sourceforge.jgeocoder.tiger.H2DbDataSourceFactory;
import net.sourceforge.jgeocoder.tiger.JGeocoder;
import net.sourceforge.jgeocoder.tiger.JGeocoderConfig;
import net.sourceforge.jgeocoder.us.AddressParser;
import net.sourceforge.jgeocoder.us.AddressStandardizer;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
/**
 * blah blah
 * another revision?
 * @author jliang
 *
 */
class JaysTestAllState{
  public static void main(String[] args) {
    JGeocoderConfig config = new JGeocoderConfig();
    config.setJgeocoderDataHome("/usr/local/jgeocoder/data");
    //config.setTigerDataSource(H2DbDataSourceFactory.getH2DbDataSource("jdbc:h2:/usr/local/jgeocoder/tiger/tiger;LOG=0;UNDO_LOG=0"));
    config.setTigerDataSource(H2DbDataSourceFactory.getH2DbDataSource("jdbc:h2:/home/sxu/jgeocoder_tigerimport_test/tiger/tiger;LOG=0;UNDO_LOG=0;AUTO_SERVER=TRUE;"));
    //config.setTigerDataSource(H2DbDataSourceFactory.getH2DbDataSource("jdbc:h2:/home/sxu/workspace/tiger2006_loaded/tiger/tiger;LOG=0;UNDO_LOG=0"));
    
    JGeocoder jg = new JGeocoder(config);
    long start = System.currentTimeMillis();
//    for(int i =0; i<100; i++){
      //JGeocodeAddress addr = jg.geocodeAddress("lazaros pizza house 1743 south st philadelphia pa 19146");
      //JGeocodeAddress addr = jg.geocodeAddress("intelius inc, 320 vairo blvd, apt c, state college, pa 16803");//lat, lon is off by about 10 miles
      //JGeocodeAddress addr = jg.geocodeAddress("540 westerly parkway, state college, pa 16801");
      //String fulladdress = "163 Beale Street, Memphis, TN 38103";
      //String fulladdress = "1521 10th Avenue, Seattle, WA 98122";
      //String fulladdress = "1 W Pratt St, Baltimore, Maryland 21201";
        
    ArrayList<String> addressList=new ArrayList<String>();
    int StreetAccuCount=0;
    addressList.add("2000 West Dimond Boulevard, Anchorage, AK 99515");
    addressList.add("4240 Narrow Lane Road, Montgomery, AL 36111");
    addressList.add("917 South Broadway, Little Rock, AR 72201");
    addressList.add("3035 West Thunderbird Road, Phoenix, AZ 85029");
    addressList.add("1427 Haight Street, San Francisco, CA 94117");
    addressList.add("3320 Youngfield Street, Wheat Ridge, CO 80033");
    addressList.add("450 Sargent Drive, New Haven, CT 06511");
    addressList.add("1201 G Street Northwest, Washington, DC 20005");
    addressList.add("1368 Rose Valley School Road, Dover, DE 19904");
    addressList.add("250 Westshore Plaza, Tampa, FL 33609");
    addressList.add("5120 Warm Springs Road, Columbus, GA 31909");
    addressList.add("2394 Kuhio Avenue, Honolulu, HI 96815");
    addressList.add("606 Walnut Street, Des Moines, IA 50309");
    addressList.add("474 Shoup Avenue, Idaho Falls, ID 83402");
    addressList.add("2 North State Street, Chicago, IL 60602");
    addressList.add("6020 E. 82nd St., Indianapolis, IN 46250");
    addressList.add("13600 South Alden Street, Olathe, KS 66062");
    addressList.add("5000 Shelbyville Road, Louisville, KY 40207");
    addressList.add("9401 Cortana Place, Baton Rouge, LA 70815");
    addressList.add("1256 Massachusetts Avenue, Cambridge, MA 02138");
    addressList.add("1 W Pratt St, Baltimore, Maryland 21201");
    addressList.add("649 Turner Street, Auburn, ME 04210");
    addressList.add("1100 North Washington Avenue, Lansing, MI 48906");
    addressList.add("2000 4th Avenue South, Minneapolis, MN 55404");
    addressList.add("3015 Barrett Station Road, St. Louis, MO 63122");
    addressList.add("160 Courthouse Square, Oxford, MS 38655");
    addressList.add("1600 S 3rd Street West, Missoula, MT 59801");
    addressList.add("101 East Weaver Street, Carrboro, NC 27510");
    addressList.add("101 28th Avenue Southeast, Minot, ND 58701");
    addressList.add("700 South 72nd Street, Omaha, NE 68114");
    addressList.add("120 Laconia Road, Tilton, NH 03276");
    addressList.add("20 South Tulane Street, Princeton Township, NJ 08542");
    addressList.add("2820 Cerrillos Road, Santa Fe, NM 87507");
    addressList.add("2900 East Tropicana Avenue, Las Vegas, NV 89121");
    addressList.add("225 West 37th Street, New York, NY 10018");
    addressList.add("631 South 3rd Street, Columbus, OH 43206");
    addressList.add("111 N Perkins Rd, Stillwater, OK 74075");
    addressList.add("700 Sw Fifth Ave, Portland, OR 97204");
    addressList.add("501 Mahantongo Street, Pottsville, PA 17901");
    addressList.add("1590 Ave Ponce De Leon, San Juan, PR 00926");
    addressList.add("1400 Oaklawn Avenue, Cranston, RI 02920");
    addressList.add("724 Saluda Avenue, Columbia, SC 29205");
    addressList.add("1200 Mickelson Dr # 314, Watertown, SD 57201");
    addressList.add("163 Beale Street, Memphis, TN 38103");
    addressList.add("400 Nueces Street, Austin, TX 78701");
    addressList.add("175 West Center Street, Orem, UT 84057");
    addressList.add("316 East Main Street, Charlottesville, VA 22902");
    addressList.add("284 Main St, Warren, VT 05674");
    addressList.add("1521 10th Avenue, Seattle, WA 98122");
    addressList.add("7357 West Towne Way, Madison, WI 53719");
    addressList.add("144 Kruger Street, Wheeling, WV 26003");
    addressList.add("401 Southeast Wyoming Boulevard, Casper, WY 82609");
    
    
    
    for (int i=0;i<addressList.size();i++){
        String fulladdress = addressList.get(i);
        while (fulladdress.indexOf(", ,") !=-1) fulladdress=fulladdress.replace(", ,", ",");
        fulladdress=fulladdress.replace("  ", " ");
        JGeocodeAddress addr = jg.geocodeAddress(fulladdress);
        GeocodeAcuracy geoAccu=addr.getAcuracy();
        if (geoAccu==GeocodeAcuracy.STREET) StreetAccuCount++;
        else System.out.println(fulladdress.substring(fulladdress.length()-8, fulladdress.length()-6));
    }
    
    
    
    

    System.out.println("out of this many test:"+addressList.size());
    System.out.println("This many test has street level accuracy:"+StreetAccuCount);
    CommonUtils.printElapsed(start, TimeUnit.SECONDS);
    
    jg.cleanup();
  }
}