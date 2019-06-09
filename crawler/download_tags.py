# -*- coding: utf-8 -*-
import requests
import lxml.html
import csv


if __name__ == '__main__':
    tags_url = "https://book.douban.com/tag/?view=type&icn=index-sorttags-all"
    content=requests.get(tags_url).text
    tar_html=lxml.html.fromstring(content)

    #类别
    catagories=tar_html.xpath("//a[@class='tag-title-wrapper']/@name")

    with open("./results1/tags.csv","w",encoding="utf8",newline="") as csvfile:
        writer=csv.writer(csvfile)
        writer.writerow(["catagory","tag"])

        #某类别下tags
        for catagory in catagories:
            path="//table[preceding-sibling::a[@name='%s']]/tbody/tr/td/a/text()"%catagory
            tags=tar_html.xpath(path)

            for tag in tags:
                writer.writerow([catagory,tag])









