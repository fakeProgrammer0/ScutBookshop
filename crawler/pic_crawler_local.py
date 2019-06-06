import re
import requests
from bs4 import BeautifulSoup
from string import Template
import sys
import time
import pandas as pd
import os
import json
import mysql.connector

host = "localhost"
# host = "116.56.140.131"

crawler_ua = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36"
crawler_headers = {"user-agent": crawler_ua}

upload_image_headers = {
    "Content-Type": "jpg"
}

book_t = "book_t"
author_t = "author_t"

db_config = {
    'user': 'root',
    'password': '123456',
    'host': host,
    'database': 'bookshop',
    'raise_on_warnings': True
}

cnx = mysql.connector.connect(**db_config)
cursor = cnx.cursor()

def get_pic_urls(table):
    sql_read_pic_urls = f"select `id`, `douban_pic_url` from `{table}` where `pic_url` is NULL;"
    df_image_urls = pd.read_sql_query(sql_read_pic_urls, cnx)
    id_2_pic_urls = {}
    for (t_id, pic_url) in df_image_urls.values:
        id_2_pic_urls[t_id] = pic_url
    return id_2_pic_urls

zimg_base_url = f"http://{host}:4869/"
upload_image_url = f"http://{host}:4869/upload"
interval = 2

md5_pattern = re.compile(r'MD5:\s*([a-f0-9]+)')

def download_store_images(table, id_2_pic_urls):
    id_2_zimg_url = {}

    for t_id in id_2_pic_urls:
        image_url = id_2_pic_urls[t_id]
        try:
            print("before download img...")
            r = requests.get(image_url, headers=crawler_headers)
        except Exception as ex:
            print('download img failed: t_id = ' + str(t_id))
            print(ex)
            # break
            continue
        
        filename = image_url[str(image_url).rfind("/")+1:]
        # save_dir = __file__ + "/../images/"
        # if not os.path.exists(save_dir):
        #     os.makedirs(save_dir)
        # with open(save_dir + filename, 'wb') as image_file:
        #     image_file.write(r.content)
        try:
            print("before store img...")
            # res = requests.post(upload_image_url, headers=upload_image_headers, files={'file': r.content})
            res = requests.post(upload_image_url, files={'file': (filename, r.content, 'image/jpg')})
        except Exception as ex:
            print('store img failed: t_id = ' + str(t_id))
            print(ex)
            continue

        # json_res = json.loads(res.content)
        # md5 = (json_res['info'])['md5']

        html_res = str(res.content)
        md5_match = md5_pattern.search(html_res)
        if md5_match is None:
            print('store img failed: t_id = ' + str(t_id))
            continue
        md5 = str(md5_match.group(1))

        zimg_url = zimg_base_url + md5
        id_2_zimg_url[t_id] = zimg_url

        sql_write_zimg_url = f"update {table} set `pic_url` = '{zimg_url}'  where `id` = {t_id}"
        try:
            print("before store db...")
            cursor.execute(sql_write_zimg_url)
            cnx.commit()
        except Exception as ex:
            print('store db failed: t_id = ' + str(t_id))
            print(ex)
            continue
        print("%d Done" % t_id)
        # time.sleep(interval)

    return id_2_zimg_url




if __name__ == "__main__":
    table = book_t
    id_2_pic_urls = get_pic_urls(table)
    id_2_zimg_url = download_store_images(table, id_2_pic_urls)
    cnx.close()
    print("Done")






