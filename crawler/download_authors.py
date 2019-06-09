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
import lxml
from selenium import webdriver

def get_author_url(id_author_url,down_id_author):
    driver = webdriver.PhantomJS(executable_path = "./webdriver/phantomjs/bin/phantomjs.exe")
    total_num=len(id_author_url)
    current_num=0
    down_set=set(down_id_author)
    try:
        with open("./results/authors.csv",'a+',encoding='utf8',newline="") as csvfile:

            writer=csv.writer(csvfile)
            if not os.path.exists("./results/authors.csv"):
                writer.wrieterow(['id','author_id','name','original','country','gender','born','died','intro','pic_url','douban_pic_url','douban_author_url'])
            for id,author_url in id_author_url:
                current_num+=1
                print("Starting {0}/{1}".format(current_num,total_num))

                if id in down_set:
                    print("Downnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn")
                    continue
                if current_num%50==0:
                    driver.quit()
                    driver = webdriver.PhantomJS(executable_path = "./webdriver/phantomjs/bin/phantomjs.exe")
                author_id=author_url[31:-1]
                print(author_url)
                driver.get(author_url)
                if len(driver.page_source)<1000:
                    print(driver.page_source)
                    break
                if driver.page_source.find("(展开全部)")>0:
                    driver.find_element_by_xpath("/html/body/div[3]/div[1]/div/div[1]/div[3]/div[2]/span[1]/a").click()
                    intro_form=1
                else:
                    intro_form=2
                tar=lxml.html.fromstring(driver.page_source)
                douban_author_url=author_url
                author_id=author_url[31:-1]

                #name
                try:
                    name=tar.xpath("//div[@id='content']/h1/text()")[0]
                except Exception as e:
                    print("name Failedddddddddddddddddddddddddddd")
                    continue

                #country
                try:
                    countrys=tar.xpath("//li[child::span[text()='国家/地区']]/text()")
                    country=''.join(item for item in countrys).replace(" ","").replace("\n","").replace(":","")
                except Exception as e:
                    country=''


                #gender
                try:
                    genders=tar.xpath("//li[child::span[text()='性别']]/text()")
                    gender_original=''.join(item for item in genders).replace(" ","").replace("\n","").replace(":","")
                except Exception as e:
                    gender_original=''
                if gender_original=="男":
                    gender=1
                elif gender_original=="女":
                    gender=2
                else:
                    gender=0


                #born
                try:
                    borns=tar.xpath("//li[child::span[text()='出生日期']]/text()")
                    born=''.join(item for item in borns).replace(" ","").replace("\n","").replace(":","")
                except Exception as e:
                    born=''

                #born-die
                global died;
                try:
                    born_dieds=tar.xpath("//li[child::span[text()='生卒日期']]/text()")
                    born_died=''.join(item for item in born_dieds).replace(" ","").replace("\n","").replace(":","")
                    if born_died:
                        born,died=born_died.split('至',1)
                    else:
                        born=''
                        died=''
                except Exception as e:
                    born=''
                    died=''

                #intro
                try:
                    if intro_form==1:
                        intros=tar.xpath("//div[@id='intro']/div[@class='bd']/span[@class='all hidden']/text()")
                    else:
                        intros=tar.xpath("//div[@id='intro']/div[@class='bd']/text()")
                    intro=''.join(item for item in intros).replace(" ","").replace("\n","").replace(":","").replace(",","，")
                except Exception as e:
                    intro=''

                #pic_url
                pic_url=''

                #douban_pic_url
                try:
                    pic_urls=tar.xpath("//div[@class='pic']/a[@class='nbg']/@href")
                    if pic_urls:
                        douban_pic_url=pic_urls[0]
                except Exception as e:
                    douban_pic_url=''

                writer.writerow([id,author_id,name,country,gender,born,died,intro,pic_url,douban_pic_url,douban_author_url])

                # try:
                #     writer.writerow([id,author_id,name,country,gender,born,died,intro,pic_url,douban_pic_url,douban_author_url])
                # except Exception as e:
                #     print("Write Failed")
                #     print(author_url)
                #     continue

    except KeyboardInterrupt as e:
        print(e)
        sys.exit(0)



def read_author_ids():
    author_id_col_all = pd.read_csv("./results/search_url.csv",usecols=["id","author_url"])

    return author_id_col_all.values.tolist()

def read_down_ids():
    down_id_author_col= pd.read_csv("./results/authors.csv",usecols=["id"]).values.tolist()
    down_id_author=[]
    for item in down_id_author_col:
        down_id_author.append(item[0])
    return down_id_author

def main():
    id_author_url = read_author_ids()
    down_id_authors=read_down_ids()
    print(len(down_id_authors))
    get_author_url(id_author_url,down_id_authors)


    #get_author(author_ids)



if __name__ == '__main__':
    main()
