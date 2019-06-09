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




def request_get(url):
    time.sleep(3)
    return requests.get(url, headers={'User-Agent': random.choice(head)},proxies=proxies).text

def get_author_urls(search_urls,down_id_set):
    driver = webdriver.PhantomJS(executable_path="./webdriver/phantomjs/bin/phantomjs.exe")
    with open("./results/search_url.csv","a+",newline="") as csvfile:
        with open("./results/failed_search_url_id.csv","a+",newline="") as csvFaild:
            writer=csv.writer(csvfile)
            writer_failed=csv.writer(csvFaild)
            if not os.path.exists("./results/search_url.csv"):
                writer.writerow(['id','author_url'])
            if not os.path.exists("./results/failed_search_url_id.csv"):
                writer_failed.writerow(['id','author_url'])


            id_author_urls=[]
            total_num=len(search_urls)
            current_num=0
            for id,sub_author_url in search_urls:
                current_num+=1
                print("Starting {0}/{1}".format(current_num,total_num))
                if id in down_id_set:
                    print("Downnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn")
                    continue
                url="https://book.douban.com"+sub_author_url
                print(url)
                driver.get(url)
                if(len(driver.page_source)<1000):
                    print(driver.page_source)
                    break
                if driver.page_source.find('/author/')<0:
                    writer_failed.writerow([id,sub_author_url])
                    print("Faileddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd1")
                    continue

                tar=lxml.html.fromstring(driver.page_source)

                try:
                    author_url=tar.xpath("//div[@class='sc-bxivhb hRIaFd']/div/a/@href")[0]
                except Exception as e:
                    writer_failed.writerow([id,sub_author_url])
                    print("Faileddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd2")
                    continue
                if(current_num%50==0):
                    driver.quit()
                    driver=webdriver.PhantomJS(executable_path="./webdriver/phantomjs/bin/phantomjs.exe")
                print(author_url)
                writer.writerow([id,author_url])
                id_author_urls.append([id,author_url])
    return id_author_urls




def get_author_id(id_sub_author_url,down_id_set):
    driver = webdriver.PhantomJS(executable_path = "./webdriver/phantomjs/bin/phantomjs.exe")
    search_urls=[]
    author_urls=[]
    for id,author_id in id_sub_author_url:
        if author_id.find('author')<0:
            search_urls.append([id,author_id])
        else:
            author_urls.append([id,"https://book.douban.com/"+author_id])
    id_author_urls=get_author_urls(search_urls,down_id_set)

    id_author_urls.append(item for item in author_urls)

    with open("./results/id_author_urls.csv","w",newline="") as csvfile:
        writer=csv.writer(csvfile)
        writer.writerow(['id','author_url'])
        for i in id_author_urls:
            writer.writerow(i)






def read_down_id():
    down_id_success_col = pd.read_csv("./results/search_url.csv",usecols=["id"]).values.tolist()
    print(down_id_success_col)
    down_id_success=[]
    for item in down_id_success_col:
        down_id_success.append(item[0])
    down_id_failed_col= pd.read_csv("./results/failed_search_url_id.csv",usecols=["id"]).values.tolist()
    down_id_failed=[]
    for item in down_id_failed_col:
        down_id_failed.append(item[0])
    print(down_id_failed)
    down_id_success_set=set(down_id_success)
    down_id_failed_set=set(down_id_failed)
    down_id_set=down_id_success_set|down_id_failed_set
    return  down_id_set







def read_author_ids():
    author_id_col_all = pd.read_csv("./results/book_table.csv",usecols=["id","author_id"])

    return author_id_col_all.values.tolist()



def main():
    id_author_url = read_author_ids()
    down_id_set=read_down_id()
    get_author_id(id_author_url,down_id_set)


    #get_author(author_ids)



if __name__ == '__main__':
    main()
