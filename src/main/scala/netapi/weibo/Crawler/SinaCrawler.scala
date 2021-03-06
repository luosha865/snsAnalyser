package netapi.weibo.Crawler

/**
 * Created by tianhaowei on 14-11-10.
 */

import netapi.weibo.API.SinaAPIs
import netapi.weibo.Crawler.LoginModel
import us.codecraft.webmagic.processor.PageProcessor

import scala.collection.mutable.ArrayBuffer
import us.codecraft.webmagic.{Page, Site, Spider}
import us.codecraft.webmagic.pipeline.FilePipeline

class SinaCrawler  extends PageProcessor{
  private var site: Site = Site.me.setRetryTimes(3).setSleepTime(1000)
    .addCookie("Cookie",LoginModel.getCookie).addHeader("User-Agent","Mozilla/5.0 (compatible; Baiduspider/2.0; +http://www.baidu.com/search/spider.html) ")

  var visited = Set[String]("2007962507")

  @Override
  def process(page: Page) {
    page.putField("test","test")
    page.putField("uid", page.getUrl.regex("http://weibo\\.com/(\\d+)/.*").toString)
    val reg = """<a class=\\\"S_txt1\\\" target=\\\"_blank\\\"  usercard=\\\"id=(\d+)\\\" href=\"http://weibo.com/(\w+)/\\\"\"(\\/u)?\\/(\w+)\\\" >""" //(([^\x00-\xff]|\w)+)<\\/a>
    val userlist = page.getHtml.regex(reg).all()
    page.putField("follow",page.getHtml.regex(reg).all())
    val cnt = userlist.size()
    for(i <- 0 to cnt-1){
      val user =userlist.get(i)
      if(!visited(user)){
        visited += user
        for(url<-SinaUser.getfollowUrl(user)){
          page.addTargetRequest(url) //java.util.List[String]
        }
      }
    }

    if (page.getResultItems.get("id") == null) {
      page.setSkip(true)
    }
  }

  @Override
  def getSite: Site = {
    return site
  }

}

object SinaCrawler {

  val seedUserIds = Array[String]("2007962507") //, "2322151934"

  def crawling(): Unit ={
    val weibospider = Spider.create(new SinaCrawler)
      //.addPipeline(new neo4jPipeline)
    //val starturls = seedUserIds.map(x => SinaUser.getfollowUrl(x))
    for (uid <- seedUserIds){
      weibospider.addUrl(SinaUser.getfollowUrl(uid):_*)
      //print(SinaUser.getfollowUrl(uid))
    }
    weibospider.thread(5).run
    //Spider.create(new SinaCrawler).addUrl("http://weibo.com/2007962507/follow?page=1").thread(5).run
  }

  def main (args: Array[String]): Unit = {
    print("begin\n")
    print(LoginModel.getCookie)
    crawling()
  }

}
