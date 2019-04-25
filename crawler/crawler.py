'''
some tips of a crawler
'''

import re
import requests
from bs4 import BeautifulSoup
from string import Template
import sys
import time
import pandas as pd
import os

crawler_ua = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36"
crawler_headers = {"user-agent": crawler_ua}

tags_url = "https://book.douban.com/tag/?view=type&icn=index-sorttags-all"
tag_subject_url = "https://book.douban.com/tag/小说"
subject_url = "https://book.douban.com/subject/1205370/"
author_url = "https://book.douban.com/author/4508516/"
search_author_api = "https://book.douban.com/j/subject_suggest?q=东野圭吾"

# 以下仅供参考
subject_url_pattern = re.compile(r"https://book.douban.com/subject/\d+")
tag_subject_url_template = Template("https://book.douban.com/tag/${tag}?start=${count}&type=T")
search_author_api_template = Template("https://book.douban.com/j/subject_suggest?q=${author}")

# 用 down_html_sample 函数下载html文件到本地，分析源码
def down_html_sample(url, filename="sample.html"):
     r = requests.get(url, headers=crawler_headers)
     save_dir = __file__ + "/../htmls/"
     if not os.path.exists(save_dir):
         os.makedirs(save_dir)
     with open(save_dir + filename, 'w', encoding=r.encoding) as html_file:
         html_file.write(r.text)

# 用set记录爬取过的数据，避免重复爬取
author_url_set = set()
book_url_set = set()
tags_set = set()

def download_htmls():
    down_html_sample(tags_url, "tags.html")
    down_html_sample(tag_subject_url, "tag_subject.html")
    down_html_sample(subject_url, "subject.html")
    down_html_sample(author_url, "author.html")

def fetch_tags():
    pass

count_per_page = 20

def fetch_books_authors(N=20):
    
    def get_subject_urls(subject_url_list):
        pass

    for tag in tags_set:
        try:
            r = requests.get(tag_subject_url_template.substitute(tag=tag, count=count_per_page), headers=crawler_headers)
            book_cnt = 0
            while book_cnt < N:
                # fetch_a_book
                pass
        except Exception:
            pass
    pass

def fetch_a_book(subject_url):

    def fetch_a_author(author_url):
        pass

    try:
        r = requests.get(subject_url, headers=crawler_headers)
        # fetch_a_author()
        pass
    except Exception:
        pass

    pass

if __name__ == "__main__":
    download_htmls()

    # fetch_tags()
    # fetch_books_authors()















