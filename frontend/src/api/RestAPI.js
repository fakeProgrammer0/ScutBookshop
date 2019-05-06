import axios from 'axios'

export const GET = 'get';
export const POST = 'post';
export const PUT = 'put';
export const DELETE = 'delete';

// 本机调试
export const baseUrl = 'http://localhost:8080';
// export const baseUrl = 'http://116.:8080';











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

