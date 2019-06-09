# -*- coding: utf-8 -*-
import  pandas as pd
import csv
import sys
import re
import random
sys.getdefaultencoding()

iid=0
ititle=1
ibookurl=2
ipicurl=3
iauthor=4
ipress=5
ioriginal=6
itranslater=7
ipublishdate=8
ipages=9
iprice=10
ibinding=11
iisbn=12
iscore=13
iratingcount=14
isummart=15
iauthorintro=16
iauthorid=17
itags=18



def main():
    with open("./results/bookscopy.csv",'r',encoding='utf8') as file:
        csv_file=csv.reader(file)
        print(csv_file)

        book34=[]
        total_num=0
        for i in csv_file:
            total_num+=1
            if  len(i)==34:
                book34.append(i)
        book34_with_author=[]
        for i in range(1,len(book34)):
            if book34[i][17]:
                for j in range(len(book34[i])):
                    if not book34[i][j]:
                        book34[i][j]='NULL'
                #print(book34[i])
                book34_with_author.append(book34[i])

    with open("./results/book_table.csv",'w',encoding='utf8',newline='') as csv_file1:
        with open("./results/book_all.csv",'w',encoding='utf8',newline='') as csv_file2:
            writer_table=csv.writer(csv_file1)
            writer_all=csv.writer(csv_file2)

            col_name=['id','title','original','author_id','author','translator','press','pubish_date','pages','price','binding','douban_score','summary','author_intro','ISBN','pic_url','douban_pic_url','douban_book_url']
            col_name_all=["id", "title", "book_url", "pic_url", "author", "press", "original", "translator",
                             "publish_date", "pages","price","binding", "ISBN", "score", "rating_count", "summary",
                             "autor_intro", "author_id", "tags", "comment1","comment2", "comment3","comment4",
                             "comment5", "rec_id1", "rec_id2", "rec_id3", "rec_id4", "rec_id5", "rec_id6", "rec_id7",
                             "rec_id8","rec_id9", "rec_id10"]
            writer_table.writerow(col_name)
            writer_all.writerow(col_name_all)
            for i in book34_with_author:
                item=i
                try:
                    price=''
                    for i in item[iprice]:
                        if i>='0' and  i<='9':
                            price+=i
                        if i=='.':
                            price+=i
                except Exception as e:
                    price=random.randrange(10,100)
                if not price:
                    price=random.randrange(10,100)
                item[iprice]=price
                book_t=[item[iid],item[ititle],item[ioriginal],item[iauthorid],item[iauthor],item[itranslater],item[ipress],item[ipublishdate],item[ipages],price,item[ibinding],item[iscore],item[isummart],item[iauthorintro],item[iisbn],'NULL',item[ipicurl],item[ibookurl]]
                writer_table.writerow(book_t)
                writer_all.writerow(item)





if __name__ == '__main__':
    main()










