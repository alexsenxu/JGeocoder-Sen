package net.sourceforge.jgeocoder.test;



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
class JaysTest4HadoopSlaves{
  public static void main(String[] args) {
    JGeocoderConfig config = new JGeocoderConfig();
    config.setJgeocoderDataHome("/usr/tmp/jgeocoder/data");
    config.setTigerDataSource(H2DbDataSourceFactory.getH2DbDataSource("jdbc:h2:/usr/tmp/jgeocoder/tiger/tiger;LOG=0;UNDO_LOG=0"));
    JGeocoder jg = new JGeocoder(config);
    long start = System.currentTimeMillis();
//    for(int i =0; i<100; i++){
      //JGeocodeAddress addr = jg.geocodeAddress("lazaros pizza house 1743 south st philadelphia pa 19146");
      //JGeocodeAddress addr = jg.geocodeAddress("intelius inc, 320 vairo blvd, apt c, state college, pa 16803");//lat, lon is off by about 10 miles
      //JGeocodeAddress addr = jg.geocodeAddress("540 westerly parkway, state college, pa 16801");
      //String fulladdress = "163 Beale Street, Memphis, TN 38103";
      //String fulladdress = "7357 West Towne Way, Madison, WI 53719";
      //nullpointer bug
      //String fulladdress = "PSC 10 BOX 1819, APO AE";
      String fulladdress = "63 MAPLE ST STE 101, FLORENCE MA 01062";
      
    //JGeocodeAddress addr = jg.geocodeAddress("DR ROBERT TROGER DDS, 20314 HOLLIS AVENUE APARTMENT A1, SAINT ALBINS NY 11412");
      //
      //the following address won't parse!!!need bug fix!!!!!!
    //JGeocodeAddress addr = jg.geocodeAddress("Old Neversink Rd, Danbury, CT 06811");
      
//    }
    
    //String normalizedState=AddressStandardizer.normalizeState("LU");
    //System.out.println(normalizedState);
    
//geocoding test 1: fixing false PREDIR
    //input:534 E san gabriel ave, clovis, ca 93612
    //expected output: 534 W san gabriel ave, clovis, ca 93612
    // the E should be replaced by W as there is only "W san gabriel ave"
    
//geocoding test 2: fix fuzzy street type designator    
    
    
    
    
    
//geocoding test 3: fix misspelled street name: "Atheton" should be "Atherton"    
    //String fulladdress="500 N Atheton St, State College, PA 16803";
    
//geocoding test 4: APT number with letters     
    //String fulladdress="22 LEHIGH ST APT 2A, HACKENSOCK NJ 07601";

//geocoding test 5: extremely messy address    
    //String fulladdress="V99 CALLE 5, 00727";
    
//geocoding test 6: separated PREDIR for street name: "E B HOLLOW RD" should be "EB HOLLOW RD"
    //String fulladdress="271 E B HOLLOW RD, EIDSON TN 37731";
    
//geocoding test 7: messy highway name in PR
    //String fulladdress="D1 CALLE 5, ANASCO PR 00610";
    
//geocoding test 8: ordinal number written by words
    //String fulladdress="311 THIRTEENTH STREET APARTMENT 10W, CASTLE POINT NJ 07030";
    
//geocoding test 9: missing street type (it should be "MCCARTY CIR"
    //String fulladdress="57 MCCARTY, COVELL IL";
    
//geocoding test 10: road name correction , the correct road name should be "HC 4 BOX 18701, GURABI PR 00778"
    //String fulladdress="STAR ROUTE 4 BOX 18701, GURABI, PR 00778";

//geocoding test 11: correct the city name, should be  "LYNN"
    //String fulladdress="16 NELSON ST, EAST LYNN, MA 01905";

//geocoding test 12: addresses with POBox and APO addresses
    //String fulladdress="PO BOX 477023, CHICAGO IL 02493-7493";
    
    
    while (fulladdress.indexOf(", ,") !=-1) fulladdress=fulladdress.replace(", ,", ",");
    fulladdress=fulladdress.replace("  ", " ");
    System.out.println(fulladdress);
    Map<AddressComponent, String> addressComponents = AddressParser.parseAddress(fulladdress);
    System.out.println("parsed Address is:"+addressComponents.toString());
    Map<AddressComponent, String> m = AddressStandardizer.normalizeParsedAddress(addressComponents);
    System.out.println("normalized Address is:"+m.toString());
    
    JGeocodeAddress addr = jg.geocodeAddress(fulladdress);
    GeocodeAcuracy geoAccu=addr.getAcuracy();
    System.out.println(geoAccu);
    System.out.println(ToStringBuilder.reflectionToString(addr, ToStringStyle.MULTI_LINE_STYLE));
    

    
    
    CommonUtils.printElapsed(start, TimeUnit.SECONDS);
    
    jg.cleanup();
  }
}