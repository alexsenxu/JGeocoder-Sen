package net.sourceforge.jgeocoder.tiger;

import javax.sql.DataSource;

import net.sourceforge.jgeocoder.CommonUtils;

import org.h2.jdbcx.JdbcDataSource;
/**
 * TODO javadocs me
 * @author jliang
 *
 */
public class H2DbDataSourceFactory{
  private H2DbDataSourceFactory(){}
  private static String _tigerUrl =
    //CommonUtils.nvl(System.getProperty("JGEOCODER_TIGER_URL"), "jdbc:h2:/usr/local/jgeocoder/tiger/tiger;LOG=0;UNDO_LOG=0");
    //Sen mod, the line below is for hadoop slaves
          CommonUtils.nvl(System.getProperty("JGEOCODER_TIGER_URL"), "jdbc:h2:/usr/tmp/jgeocoder/tiger/tiger;LOG=0;UNDO_LOG=0");

  public static DataSource getH2DbDataSource(){
    return getH2DbDataSource(_tigerUrl);
  }
  
  public static DataSource getH2DbDataSource(String tigerUrl){
    JdbcDataSource ds =  new JdbcDataSource();
    ds.setURL(tigerUrl);
    return ds;
  }
}