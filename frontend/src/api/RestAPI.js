import axios from 'axios'

export const GET = 'get';
export const POST = 'post';
export const PUT = 'put';
export const DELETE = 'delete';

// 本机调试
// export const baseUrl = 'http://localhost:8080'; // 本地调试
export const baseUrl = 'http://localhost:18080'; // api文档调试
// export const baseUrl = 'http://116.56.140.131:8080'; // 院长的超级服务器（华工校园网，夜间断网不可用）

// A1.获取作者详情信息
export const getAuthorDetail = (author_id) => {
  return axios({
    url: baseUrl + "/authors/" + author_id
  })
}

// A2.获取作者创作的热门图书
export const getAuthorHotBooks = (author_id, count) => {
  count = count ? count : 5;
  return axios({
    url: baseUrl + "/authors/" + author_id + "/hot-books",
    params: {
      "count": count
    }
  })
}

// B1.获取图书详情信息
export const getBookDetail = (book_id) => {
  return axios({
    url: baseUrl + "/books/" + book_id
  })
};

// B4.获取图书的豆瓣短评论
export const getShortComments = (book_id) => {
  return axios({
    url: baseUrl + "/books/" + book_id + "/short-comments",
  })
};

// B5.获取图书的标签
export const getBookTags = (book_id) => {
  return axios({
    url: baseUrl + "/books/" + book_id + "/tags"
  })
};

// B2.获取图书类别
export const getAllCategories = () => {
  return axios({
    url: baseUrl + "/books/categories"
  })
};

// B6.获取图书类别与类别下的热门图书
export const getAllCategoriesBooks = () => {
  return axios({
    url: baseUrl + "/books/categories-books"
  })
};

// B3.获取某一类别下的图书
export const getBooksInCategory = (category_id, page, per_page) =>{
  return axios({
    url: baseUrl + "/books/categories/" + category_id,
    params: {
      "page": page,
      "per_page": per_page
    }
  })
};

export const search = (keyword, size) => {
  return axios({
    url: baseUrl + "/search",
    params: {
      "keyword": keyword,
      "size": size
    }
  });
};

export const userLogin = (username, password) => {
  return axios({
    method: POST,
    url: baseUrl + "/users/login",
    data: {
      "username": username,
      "password": password
    }
  })
};







// others

export const addCollectedBook = (user_id, book_id) => {
  return axios({
    method: POST,
    url: baseUrl + "/users/" + user_id + "/collections/" + book_id,
  })
};

export const removeCollectedBook = (user_id, book_id) => {
  return axios({
    method: DELETE,
    url: baseUrl + "/users/" + user_id + "/collections/" + book_id,
  })
};

export const getCollectedMovies = params => {
  return axios({
    method: 'get',
    url: baseUrl + getCollectedMoves,
    params: params
  })
};

export const checkCollectedBook = (user_id, book_id) => {
  return axios({
    method: GET,
    url: baseUrl + "/users/" + user_id + "/collections/" + book_id,
  })
};

