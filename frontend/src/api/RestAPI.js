import axios from 'axios'

export const GET = 'get';
export const POST = 'post';
export const PUT = 'put';
export const DELETE = 'delete';

// 本机调试
// export const baseUrl = 'http://localhost:8080'; // 本地调试
// export const baseUrl = 'http://localhost:18080'; // 调试
export const baseUrl = 'http://116.56.140.131:8080'; // 院长的超级服务器（华工校园网，夜间断网不可用）
// export const baseUrl = 'http://45.76.199.126:8080'; // 国外服务器 （短期内使用，不稳定；搜索接口不可用）

export const getBookDetail = (book_id) => {
  return axios({
    url: baseUrl + "/books/" + book_id
  })
};

export const getShortComments = (book_id) => {
  return axios({
    url: baseUrl + "/books/" + book_id + "/short-comments",
  })
};

export const getBookTags = (book_id) => {
  return axios({
    url: baseUrl + "/books/" + book_id + "/tags"
  })
};

export const getAllCategories = () => {
  return axios({
    url: baseUrl + "/books/categories"
  })
};

export const getAllCategoriesBooks = () => {
  return axios({
    url: baseUrl + "/books/categories-books"
  })
};

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

