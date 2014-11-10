package netapi.weibo

import us.codecraft.webmagic.Page
import us.codecraft.webmagic.Site
import us.codecraft.webmagic.Spider
import us.codecraft.webmagic.monitor.SpiderMonitor
import us.codecraft.webmagic.processor.PageProcessor
import us.codecraft.webmagic.pipeline.JsonFilePipeline


/**
 * Created by tianhaowei on 14-11-9.
 */

class SinaCrawler extends PageProcessor {

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

object SinaCrawler {

  def main(args: Array[String]): Unit = {
    val start = SinaAPIs.userinfo + "1439317162"
    printf(start + "\n")

    Spider.create(new SinaCrawler).addUrl(SinaAPIs.public_timeline).thread(5).run

  }

}