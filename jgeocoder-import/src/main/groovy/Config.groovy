
class Config{
  def cleanup = true  //this drops everything and rebuilds
  def driverClass = 'org.h2.Driver'

  //Sen Mod
  //def connectionString = /jdbc:h2:C:\Users\jliang\Desktop\jgeocoder\tiger\ca;LOG=0;UNDO_LOG=0/
  //def tigerDataFiles = /C:\Users\jliang\Desktop\tiger\CA/
  //Sen mod 2
  //def connectionString = /jdbc:h2:\/usr\/local\/jgeocoder\tiger\ca;LOG=0;UNDO_LOG=0/
  def connectionString = /jdbc:h2:\/home\/sxu\/jgeocoder_tigerimport_test\/tiger\/tiger;LOG=0;UNDO_LOG=0/
  def tigerDataFiles = /TX/
  
  def db = null
}