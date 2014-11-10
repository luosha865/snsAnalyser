package netapi.weibo.API

import netapi.weibo.SinaAPIs
import us.codecraft.webmagic.processor.PageProcessor
import us.codecraft.webmagic.{Page, Site, Spider}


/**
 * Created by tianhaowei on 14-11-9.
 */

class SinaAPICrawler extends PageProcessor {

  private var site: Site = Site.me.setRetryTimes(3).setSleepTime(100)

  @Override
  def process(page: Page) {
    val page_json = page.getJson
    page.putField("name", "test") //page_json.jsonPath("name").toString
    if (page.getResultItems.get("name") == null) {
      page.setSkip(true)
    }
    println("line")
  }

  @Override
  def getSite: Site = {
    return site
  }

}

object SinaAPICrawler {

  def main(args: Array[String]): Unit = {
    val start = SinaAPIs.userinfo + "1439317162"
    printf(start + "\n")

    Spider.create(new SinaCrawler).addUrl(SinaAPIs.public_timeline).thread(5).run

  }

}