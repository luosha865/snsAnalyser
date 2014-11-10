package netapi.weibo.Crawler

/**
 * Created by tianhaowei on 14-11-10.
 */

import netapi.weibo.API.SinaAPIs
import us.codecraft.webmagic.processor.PageProcessor

import scala.collection.mutable.ArrayBuffer
import us.codecraft.webmagic.{Page, Site, Spider}

class SinaCrawler  extends PageProcessor{
  private var site: Site = Site.me.setRetryTimes(3).setSleepTime(100)

  @Override
  def process(page: Page) {
    page.putField("id", "test")
    page.putField("uid", page.getUrl.regex("http://weibo\\.com/p/(\\d+)/.*").toString)

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
    val starturls = seedUserIds.map(x => SinaUser.getfollowUrl(x))

    Spider.create(new SinaCrawler).addUrl( starturls:_* ).thread(5).run
  }

  def main (args: Array[String]): Unit = {
    crawling()
  }

}
