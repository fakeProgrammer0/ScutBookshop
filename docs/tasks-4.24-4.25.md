# 4/24 - 4/25任务

## 1.任务分工

| 任务                | 负责 |
| ------------------- | ---- |
| 需求清单 + 原型设计 | 张媚 |
| 前端框架搭建        | 源森 |
| 后端框架搭建        | 绿佳 |
| 爬取图书数据        | 思凯 |
| 知识图谱推荐        | 束航 |

以上任务，尽量在4/24-4/25完成，周四讨论：进度、业务建模、需求清单……

## 2.具体任务

### 2.1.需求清单 + 原型

#### A.需求清单

| 优先级 | 需求项   | 描述 |
| :----: | -------- | ---- |
|   1    | 首页     | 无   |
|   1    | 图书详情 |      |
|   1    | 图书搜索 |      |
|   2    | 用户登录 |      |
|   2    | 收藏图书 |      |
|   2    | 评论图书 |      |

#### B.暂时不做的需求

| 需求项       | 解释                                                      |
| ------------ | --------------------------------------------------------- |
| 多角色       | 唯一角色只有用户                                          |
| 各种管理服务 |                                                           |
| 数据管理     | 例如数据导入API（新增、删除图书），数据直接导入数据库即可 |
|              |                                                           |
|              |                                                           |

#### C.原型设计

针对[A.需求清单]罗列的需求项，设计界面的草图。



### 2.2.框架搭建

#### A.前端框架搭建

选择熟悉的前端框架，创建好基本包结构

#### B.后端框架搭建

springboot + mybatis

controller + service + dao 三层结构

异常处理 + 响应格式 + 工具类

### 2.3.图书数据爬取

爬虫 + 数据清洗 + 导数据库

| 爬取数据   | 数量                                          |
| ---------- | --------------------------------------------- |
| 标签信息   | 【热门标签】页面列出的所有标签（共 `145` 个） |
| 图书信息   | 每个标签下 `N` 本书，共 `145N` 条记录         |
| 图书短评论 | 每本书 `5` 条 ，共 `145 * 5 * N` 条           |
| 图书标签   | 每本书的详情页列出的标签                      |
| 作者       | 每本书的作者                                  |

#### A.标签信息爬取

热门标签页面 ：[https://book.douban.com/tag/?view=type&icn=index-sorttags-all](https://book.douban.com/tag/?view=type&icn=index-sorttags-all)

爬取格式：（类别，标签名）

| category | tag  |
| -------- | ---- |
| 文学     | 小说 |
| 流行     | 漫画 |

> 数量少，且在单一页面，可以考虑复制粘贴到本地（notepad++），正则表达式处理……

#### B.图书详情页爬取

从爬取的标签进入单一标签下的图书页面，如 [https://book.douban.com/tag/ 小说](https://book.douban.com/tag/ 小说)，爬取该标签下 `N` 本图书。

* 图书信息 ✓
* 该图书的标签
* 5条短评论

| ... （原有字段） | author        | author_intro | tags               | comment1 | ...  | comment5 |
| ---------------- | ------------- | ------------ | ------------------ | -------- | ---- | -------- |
|                  | [日] 东野圭吾 | ……           | 东野圭吾 治愈 温暖 |          |      |          |

* 维持原有的 `author` 字段和 `author_intro` 字段。之前爬到的数据中，有些记录的 `author` 字段爬取不到例如这个页面：`https://book.douban.com/subject/1205370/`；或者带有 `<ahref>` 标签，需要清洗。
* `tag` 字段：多个标签之间用空格符分隔，后续数据预处理再切割

#### C.作者信息爬取

1. 如果在【B.图书详情页爬取】拿到了 `<ahref>` 标签，直接提取 author_url

2. 否则，在【B.图书详情页爬取】中拿到了author字段，去除国籍后，利用豆瓣的搜索接口`https://book.douban.com/j/subject_suggest?q=东野圭吾`，从响应的 `JSON` 中获取 author_url
3. 到该链接下爬取作者信息：（名字，性别，国家/地区，照片url，作者简介）

> 可用set记录author_url，避免重复爬取

#### X.爬虫细节

代码框架：[crawler/crawler.py](../crawler/crawler.py)

##### Steps:

1. 爬取标签页，存储到 `tags.csv` 
2. 依次爬取每个图书详情页，同时顺便爬取作者详情页，分别存储到 `books.csv` 和 `authors.csv`

##### Implementation: 

* 用 `down_html_sample` 函数下载html文件到本地，分析源码
* [BeautifulSoup](<https://www.crummy.com/software/BeautifulSoup/bs4/doc/index.html>) 解析html数据；正则表达式（[Python Standard Library / re](<https://docs.python.org/3/library/re.html>)）强行匹配字符串

##### tips

* 数据存csv文件，utf8编码，可参考 [Python Standard Library / csv](<https://docs.python.org/3/library/csv.html>) 。时刻将csv对象的数据缓冲刷新到磁盘csv文件！
* 使用ua，设置请求间隔（随机1~3秒）
* ★为**request.get()方法和html页面解析**添加 `try-catch` 异常处理快，避免程序因404异常等奔溃而丢失爬取数据
* 用 `set` 记录爬取过的数据，避免重复爬取
* 可参考 [这里的两份代码](<https://github.com/fakeProgrammer0/movieRecommandation/tree/master/crawler>)

### 2.4.知识图谱推荐

* 知识图谱推荐需要的数据格式？
* 算法怎么设计？
* 可以考虑先用随机向量数据测试

