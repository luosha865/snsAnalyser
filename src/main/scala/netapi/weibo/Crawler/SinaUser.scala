package netapi.weibo.Crawler

/**
 * Created by tianhaowei on 14-11-10.
 */

object SinaUser{

  def getfollowUrl(uid:String) : String = {
    return "http://weibo.com/p/" + uid + "/follow?page="
  }

  def getfollowingUrl(uid:String) : String = {
    return "http://weibo.com/p/" + uid + "/follow?relate=fans&page="
  }

  def getweibopostUrl(uid:String) : String = {
    return "http://weibo.com/p/" + uid + "/follow?page="
  }

}



