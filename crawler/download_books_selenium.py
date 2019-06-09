# -*- coding: utf-8 -*-
import requests
import lxml.html
import random
import time
import pandas as pd
import signal
import sys
import csv
import os
from selenium import webdriver


head = ['Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.163 Safari/535.1',
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0',
        'Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Win64; x64; Trident/6.0)',
        'Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; ARM; Trident/6.0)'
        ]

proxies = {
    'http': 'http://121.204.150.97:8118',
    'http': 'http://116.209.54.142:9999',
    'http': 'http://180.118.247.29:9000',
    'http': 'http://125.123.140.211:8118',
    'http': 'http://218.91.112.182:9999',
    'http': 'http://117.90.4.255:9000'
}
def exit(signum, frame):
        print('You choose to stop me.')
        sys.exit(0)


def get_books(urls):
    with open("./results/books.csv","a+",encoding="utf8",newline="")as csvfile:
        writer=csv.writer(csvfile)
        if not os.path.exists("./results/books.csv"):
            writer.writerow(["id", "title", "book_url", "pic_url", "author", "press", "original", "translator",
                             "publish_date", "pages","price","binding", "ISBN", "score", "rating_count", "summary",
                             "autor_intro", "author_id", "tags", "comment1","comment2", "comment3","comment4",
                             "comment5", "rec_id1", "rec_id2", "rec_id3", "rec_id4", "rec_id5", "rec_id6", "rec_id7",
                             "rec_id8","rec_id9", "rec_id10"])

        total_num=len(urls)
        print(total_num)
        current_num=0
        driver = webdriver.PhantomJS(executable_path="./webdriver/phantomjs/bin/phantomjs.exe")

        for url in urls:

            current_num+=1
            book_url =url

            id=book_url[32:-1]

            try:
                driver.get(book_url)
                tar=driver.page_source
                if(len(tar)<1000):
                    print(tar)
                    sys.exit(0)

            except Exception as e:
                print(e)
                print("get failed")
                continue
            tar_html = lxml.html.fromstring(tar)
            # 书名
            try:
                title = tar_html.xpath("//span[@property='v:itemreviewed']/text()")[0]
            except Exception as e:
                print("title failed")
                continue
            # 图片url
            pic_url = tar_html.xpath("//a[@class='nbg']/@href")[0]

            # 作者
            print(book_url)
            try:
                author = tar_html.xpath("//div[@id='info']/span/a/text()")[0]
            except Exception as e:
                print("author failed")

                continue


            print()
            #
            # 封面url
            pic_urls=tar_html.xpath("//div[@id='mainpic']/a[@class='nbg']/@href")
            if pic_urls:
                pic_url=pic_urls[0]
            else:
                print("pic_url failed")
                continue

            authorids = tar_html.xpath("//div[@id='info']/span[1]/a/@href")
            if authorids:
                author_id=authorids[0]
            else:
                author_id=""


            #出版社
            presses=tar_html.xpath("//text()[preceding-sibling::span[text()='出版社:']][following-sibling::br]")
            if presses:
                press=presses[0].replace("\n","").replace(" ","")
            else:
                press=""



            #原作名
            originals=tar_html.xpath("//text()[preceding-sibling::span[text()='原作名:']][following-sibling::br]")
            if originals:
                original=originals[0].replace("\n","").replace(" ","")
            else:
                original=""


            #译者
            translators = tar_html.xpath("//a[parent::span[child::span[text()=' 译者']]]/text()")
            if translators:
                translator = '/'.join((i.strip() for i in translators))
            else:
                translator = ''


            #出版年
            publish_dates=tar_html.xpath("//text()[preceding-sibling::span[text()='出版年:']][following-sibling::br]")
            if publish_dates:
                publish_date=publish_dates[0].replace("\n","").replace(" ","")
            else:
                publish_date=""



            #页数
            pages = tar_html.xpath("//text()[preceding-sibling::span[text()='页数:']][following-sibling::br]")
            if pages:
                page = pages[0].replace("\n", "").replace(" ", "")
            else:
                page = ""

            # 定价
            prices = tar_html.xpath("//text()[preceding-sibling::span[text()='定价:']][following-sibling::br]")
            if prices:
                price = prices[0].replace("\n", "").replace(" ", "")
            else:
                price= ""


            #装帧
            bindings = tar_html.xpath("//text()[preceding-sibling::span[text()='装帧:']][following-sibling::br]")
            if bindings:
                binding = bindings[0].replace("\n", "").replace(" ", "")
            else:
                binding = ""


            #ISBN
            ISBNs = tar_html.xpath("//text()[preceding-sibling::span[text()='ISBN:']][following-sibling::br]")
            if ISBNs:
                ISBN = ISBNs[0].replace("\n", "").replace(" ", "")
            else:
                ISBN = ""


            #豆瓣评分
            scores=tar_html.xpath("//strong[@property='v:average']/text()")
            global score
            if scores:
                if scores[0].strip():
                    score=scores[0].strip()
            else:
                score=""


            #评分人数rating_count
            rating_counts=tar_html.xpath("//span[@property='v:votes']/text()")
            if rating_counts:
                if rating_counts[0].strip():
                    rating_count=rating_counts[0].strip()
            else:
                rating_count=""


            #内容简介summary
            summarys=tar_html.xpath("//div[@class='intro']")
            global summary
            if summarys:
                try:
                    summary = ''.join(item for item in summarys[0].xpath('p/text()')).replace(',','，').replace(" ","").replace("\n","")
                except Exception as e:
                    summary=""

            #作者简介autro_intro
            if summarys:
                try:
                    author_intro = ''.join(item for item in summarys[1].xpath('p/text()')).replace(',','，').replace(" ","").replace("\n","")
                except Exception as e:
                    author_intro = ""


            #标签
            tag_data=tar_html.xpath("//div[@id='db-tags-section']/div/span/a/text()")
            if tag_data:
                tags=''.join((" ".join(item for item in tag_data)))
            else:
                tags=""

            #评论
            comments=tar_html.xpath("//p[@class='comment-content']/span/text()")
            for i in range(5):
                names = locals()
                try:
                    names['comment' + str(i + 1)] = comments[i].replace(',','，').replace(" ","").replace("\n","")
                except Exception as e:
                    names['comment' + str(i + 1)] = ""


            rec=tar_html.xpath("//div[@id='db-rec-section']//dt/a/@href")

            book=[id, title, book_url, pic_url, author, press, original, translator, publish_date, page, price,
                    binding, ISBN, score, rating_count, summary, author_intro, author_id,tags]

            for i in range(5):
                book.append(names['comment' + str(i + 1)])

            for item in rec:
                book.append(item[32:-1])

            print(str(current_num)+"/"+str(total_num)+"： "+title)
            if(current_num%50==0):
                driver.quit()
                driver=webdriver.PhantomJS(executable_path="./webdriver/phantomjs/bin/phantomjs.exe")
            try:
                writer.writerow(book)
            except Exception as e:
                print(title+" write filed.")
                continue



def read_urls():
    tags_col_all = pd.read_csv("./results/book_urls.csv")
    total_num=len(tags_col_all['url'].values.tolist())
    if not os.path.exists("./results/books.csv"):
        return tags_col_all['url'].values.tolist()
    else:
        tags_col_exit=pd.read_csv("./results/books.csv")
        current_num=len(tags_col_exit['book_url'].values.tolist())
        print("Having downloaded {0}/{1}".format(current_num,total_num))
        return list(set(tags_col_all['url'].values.tolist())-set(tags_col_exit['book_url'].values.tolist()))




def main():
    signal.signal(signal.SIGINT, exit)
    signal.signal(signal.SIGTERM, exit)
    urls = read_urls()
    get_books(urls)



if __name__ == '__main__':
    main()
