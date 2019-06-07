import pandas as pd
import numpy as np
import mysql.connector
import math

books_csv = __file__ + "/../books_0_t0.csv"

df_book = pd.read_csv(books_csv, engine='python', encoding='utf8')

df_book = df_book.drop(columns=['title', 'book_url', 'pic_url', 'author','press','original','translator','publish_date','pages','price','binding','ISBN','score','rating_count','summary','autor_intro','author_id'])

db_config = {
        'user': 'root',
        'password': '123456',
        'host': '116.56.140.131',
        'database': 'bookshop',
        'raise_on_warnings': True
    }

cnx = mysql.connector.connect(**db_config)
cursor = cnx.cursor()

def write_tag():
    tag_set = set(' '.join(df_book['tags'].tolist()).split(' '))
    print("number of tags: %d" % len(tag_set))
    # tag_df = pd.DataFrame(data={'title':list(tag_set)})[['title']]
    # tag_df.to_sql('tag_t', con=cnx, schema='', if_exists='append')

    sql_read_tags = "select `title` from `tag_t`;"
    db_tag_df = pd.read_sql_query(sql_read_tags, cnx)
    db_tag_list = db_tag_df['title'].tolist()
    for tag in db_tag_list:
        tag = tag.replace("'", "\\\'").lower()
    dup_tag_set = set(db_tag_list)
    tag_set = tag_set - dup_tag_set

    tag_per_transaction = 1000
    i = 0
    tag_list = list(tag_set)
    while i < len(tag_list):
        tag_values = ""
        for j in range(0, tag_per_transaction):
            tag = tag_list[i].replace("'", "\\\'")
            if tag.lower() not in dup_tag_set:
                tag_values += "('" + tag + "'),"
                dup_tag_set.add(tag.lower())
            i += 1
            if i == len(tag_list):
                break
        if tag_values == "":
            break
        sql_write_tag = "insert into `tag_t` (`title`) values " + tag_values[:-1]
        try:
            # print(sql_write_tag)
            cursor.execute(sql_write_tag)
        except Exception as ex:
            print(ex)
        cnx.commit()
    # for tag in tag_set:
    #     try:
    #         cursor.execute(sql_write_tag.format(tag))
    #     except Exception as ex:
    #         print(ex)
    # cnx.commit()

def write_book_tags():

    tag2id = {}
    sql_read_tags = "select `id`, `title` from `tag_t`;"
    df_db_tags = pd.read_sql_query(sql_read_tags, cnx)
    for i, row in enumerate(df_db_tags.values):
        id, title = row
        tag2id[title.lower()] = id
        
    book_tag_id_list = []
    for (book_id, tag_str) in df_book[['id', 'tags']].values:
        tag_list = tag_str.split(' ')
        for tag in tag_list:
            if tag.lower() not in tag2id:
                print(tag)
                continue
            tag_id = tag2id[tag.lower()]
            book_tag_id_list.append((book_id, tag_id))

    book_tag_id_list = list(set(book_tag_id_list))
    tag_per_transaction = 1000
    i = 0
    while i < len(book_tag_id_list):
        id_pairs_str = ""
        for j in range(0, tag_per_transaction):
            id_pairs_str += str(book_tag_id_list[i]) + ","
            i += 1
            if i == len(book_tag_id_list):
                break
        if id_pairs_str == "":
            break
        sql_write_book_tag = "insert into `book_tag_t` (`book_id`, `tag_id`) values " + id_pairs_str[:-1]
        try:
            # print(sql_write_tag)
            cursor.execute(sql_write_book_tag)
        except Exception as ex:
            print(ex)
        cnx.commit()

    pass


def write_short_comments():
    
    book_id_cmt_list = []
    for (book_id, cmt1, cmt2, cmt3, cmt4, cmt5) in df_book[['id', 'comment1', 'comment2', 'comment3', 'comment4', 'comment5']].values:
        book_id_cmt_list.append((book_id, "'" + str(cmt1).replace("'", "\\\'") + "'"))
        book_id_cmt_list.append((book_id, "'" + str(cmt2).replace("'", "\\\'") + "'"))
        book_id_cmt_list.append((book_id, "'" + str(cmt3).replace("'", "\\\'") + "'"))
        book_id_cmt_list.append((book_id, "'" + str(cmt4).replace("'", "\\\'") + "'"))
        book_id_cmt_list.append((book_id, "'" + str(cmt5).replace("'", "\\\'") + "'"))
        

    record_per_transaction = 1000
    i = 0
    while i < len(book_id_cmt_list):
        book_id_cmt_pair_str = ""
        for j in range(0, record_per_transaction):
            book_id_cmt_pair_str += str(book_id_cmt_list[i]) + ","
            i += 1
            if i == len(book_id_cmt_list):
                break
        if book_id_cmt_pair_str == "":
            break
        sql_write_cmts = "insert into `short_comment_t` (`book_id`, `comment`) values " + book_id_cmt_pair_str[:-1]
        try:
            # print(sql_write_tag)
            cursor.execute(sql_write_cmts)
        except Exception as ex:
            print(ex)
        cnx.commit()


def write_rec_books():
    sql_read_book_ids = "select `id` from `book_t`"
    df_book_ids = pd.read_sql_query(sql_read_book_ids, cnx)
    book_id_set = set(df_book_ids['id'].tolist())

    rec_book_id_pair_list = []
    for (book_id, rec_id1, rec_id2, rec_id3, rec_id4, rec_id5, rec_id6, rec_id7, rec_id8, rec_id9, rec_id10) in df_book[['id', 'rec_id1', 'rec_id2', 'rec_id3', 'rec_id4', 'rec_id5', 'rec_id6', 'rec_id7', 'rec_id8', 'rec_id9', 'rec_id10']].values:
        book_id = int(book_id)
        if book_id not in book_id_set:
            continue
        rec_ids = [rec_id1, rec_id2, rec_id3, rec_id4, rec_id5, rec_id6, rec_id7, rec_id8, rec_id9, rec_id10]
        for rec_id in rec_ids:
            if math.isnan(rec_id):
                continue
            rec_id = int(float(rec_id))
            if rec_id in book_id_set:
                rec_book_id_pair_list.append((book_id, rec_id))

    record_per_transaction = 1000
    i = 0
    while i < len(rec_book_id_pair_list):
        rec_pairs_str = ""
        for j in range(0, record_per_transaction):
            rec_pairs_str += str(rec_book_id_pair_list[i]) + ","
            i += 1
            if i == len(rec_book_id_pair_list):
                break
        if rec_pairs_str == "":
            break
        sql_write_rec = "insert into `book_recommended_t` (`subject_id`, `rec_id`) values " + rec_pairs_str[:-1]
        try:
            cursor.execute(sql_write_rec)
        except Exception as ex:
            print(ex)
        cnx.commit()



if __name__ == "__main__":
    # write_tag()
    # write_book_tags()
    # write_short_comments()
    write_rec_books()
    cnx.close()
    print("Done")
