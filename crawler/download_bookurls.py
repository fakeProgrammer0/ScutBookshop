# -*- coding: utf-8 -*-
import requests
import lxml.html
import pandas as pd
import time
import random

head=['Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.1 (KHTML, like Gecko) Chrome/14.0.835.163 Safari/535.1',
      'Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0',
      'Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; Win64; x64; Trident/6.0)',
      'Mozilla/5.0 (compatible; MSIE 10.0; Windows NT 6.2; ARM; Trident/6.0)'
]

proxies = {
  'http': 'http://182.88.179.80:8123',
  'http': 'http://59.52.184.113:808',
  'http': 'http://113.121.22.112:9999',
  'http': 'http://121.233.251.50:9999',
  'http': 'http://117.91.251.241:9999',
  'http': 'http://175.42.68.96:9999'
}

def requests_get(url):
    time.sleep(1)
    return requests.get(url,headers={'User-Agent': random.choice(head)},proxies=proxies).text

def read_tags():
    tags_col = pd.read_csv("./results/tags.csv")
    return tags_col['tag'].values


def crawle_urls(tags,N):
    urls=set()
    for tag in tags:
        # url开始数
        start=0
        #当前tag所得url数
        num=0
        #计算当前页面有，但不更新urls时，有可能是tags下图书比较少，也有可能
        break_num=0
        while (True):
            print(tag+str(start))
            target_url = "https://book.douban.com/tag/%s?start=%d" % (tag, start)
            content = requests_get(target_url)
            tar_html = lxml.html.fromstring(content)
            certain_urls = tar_html.xpath("//li[@class='subject-item']/div[@class='info']/h2/a/@href")
            origin_len=len(urls)
            for url in certain_urls:
                urls.add(url)
            #数量足够或者当前页面没有或者当前页面有并没有更新urls
            if len(urls)==origin_len:
                break_num+=1
                print(break_num)
                #可能书太火，前面tag已经有了，3好像有点小，
                if break_num>5:
                    break
            if not num<N:
                break
            num+=len(urls)-origin_len
            start+=len(certain_urls)
    return urls




def write(urls):
    dataframe = pd.DataFrame({'url': list(urls)})
    dataframe.to_csv("./results/book_urls1.csv", index=False, sep=',')


def main():
    N=200
    tags=read_tags()
    urls=crawle_urls(tags,N)
    write(urls)
if __name__ == '__main__':
    main()

