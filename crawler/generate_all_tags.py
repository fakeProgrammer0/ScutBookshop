# -*- coding: utf-8 -*-
import requests
import lxml.html
import pandas as pd
import time
import random



def read_tags():
    tags_col = pd.read_csv("./results/bookscopy.csv")
    tags_col=tags_col.dropna(axis=0,how='any',subset=['tags'],inplace=False)
    return tags_col['tags'].values.tolist()

def split_unique_tags(tag_col):
    all_tags=[]
    for tags in tag_col:
        for item in tags.split(" "):
            all_tags.append(item)
    return list(set(all_tags))

def write_tags(tag_unique):
    tagsDF=pd.DataFrame({'tag':tag_unique})
    tagsDF.to_csv("./results/all_tags.csv",index=False,sep=',')

def main():
    tag_col=read_tags()
    tag_unique=split_unique_tags(tag_col)
    write_tags(tag_unique)


if __name__ == '__main__':
    main()

