import axios from 'axios'

export const GET = 'get';
export const POST = 'post';
export const PUT = 'put';
export const DELETE = 'delete';

// 本机调试
export const baseUrl = 'http://localhost:8080'; // 本地调试
// export const baseUrl = 'http://localhost:18080'; // api文档调试
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

// export const search = (keyword, size) => {
//   return axios({
//     url: baseUrl + "/search",
//     params: {
//       "keyword": keyword,
//       "size": size
//     }
//   });
// };

export const search = (search_type, keyword, size) => {
  return axios({
    url: baseUrl + "/search/" + search_type,
    params: {
      "keyword": keyword,
      "size": size
    }
  });
};

export const searchHybrid = (keyword, size) => {
  return search("hybrid", keyword, size);
};

export const searchBooks = (keyword, size) => {
  return search("books", keyword, size);
};

export const searchAuthors = (keyword, size) => {
  return search("authors", keyword, size);
};

export const searchISBN = (keyword, size) => {
  return search("isbn", keyword, size);
};

// 用户

// U1.用户注册
export const signUp = (userInfo) => {
  return axios({
    method: POST,
    url: baseUrl + "/users",
    data: userInfo
  })
};

// U2.用户登录
export const login = (username, password) => {
  return axios({
    method: POST,
    url: baseUrl + "/users/login",
    data: {
      "username": username,
      "password": password
    }
  })
};







// TODO: 用户收藏夹（待完善）

// F1.添加收藏夹
export const addFavorite = (user_id, favorite_type, favorite_id) => {
  return axios({
    method: POST,
    url: baseUrl + "/users/" + user_id + "/favorites/" + favorite_type + "s/" + favorite_id
  })
}

export const addFavoriteBook = (user_id, book_id) => {
  return addFavorite(user_id, "book", book_id);
}

export const addFavoriteAuthor = (user_id, author_id) => {
  return addFavorite(user_id, "author", author_id);
}

// F2.删除收藏夹
export const removeFavorite = (user_id, favorite_type, favorite_id) => {
  return axios({
    method: DELETE,
    url: baseUrl +  "/users/" + user_id + "/favorites/" + favorite_type + "s/" + favorite_id
  })
}

export const removeFavoriteBook = (user_id, book_id) => {
  return removeFavorite(user_id, "book", book_id);
}

export const removeFavoriteAuthor = (user_id, author_id) => {
  return removeFavorite(user_id, "author", author_id);
}

// F3.检查是否添加收藏夹
export const checkFavorite = (user_id, favorite_type, favorite_id) => {
  return axios({
    url: baseUrl +  "/users/" + user_id + "/favorites/" + favorite_type + "s/" + favorite_id
  })
}

export const checkFavoriteBook = (user_id, book_id) => {
  return checkFavorite(user_id, "book", book_id);
}

export const checkFavoriteAuthor = (user_id, author_id) => {
  return checkFavorite(user_id, "author", author_id);
}

// F4.获取收藏图书列表
export const getFavoriteBooks = (user_id, page, per_page) => {
  page = page ? page : 1;
  per_page = per_page ? per_page : 5;
  return new axios({
    url: baseUrl +  "/users/" + user_id + "/favorites/books",
    params: {
      "page": page,
      "per_page": per_page
    }
  })
}

// F4.获取收藏作者列表
export const getFavoriteAuthors = (user_id, page, per_page) => {
  page = page ? page : 1;
  per_page = per_page ? per_page : 5;
  return new axios({
    url: baseUrl +  "/users/" + user_id + "/favorites/authors",
    params: {
      "page": page,
      "per_page": per_page
    }
  })
}



// // UB1.添加图书到收藏夹
// export const addCollectedBook = (user_id, book_id) => {
//   return axios({
//     method: POST,
//     url: baseUrl + "/users/" + user_id + "/collections/" + book_id,
//   })
// };


// // UB2.从收藏夹中删除图书
// export const removeCollectedBook = (user_id, book_id) => {
//   return axios({
//     method: DELETE,
//     url: baseUrl + "/users/" + user_id + "/collections/" + book_id,
//   })
// };


// // UB3.获取用户收藏图书
// export const getCollectedMovies = (user_id, page, per_page) => {
//   page = page ? page : 1;
//   per_page = per_page ? per_page : 5;
//   return axios({
//     url: baseUrl + "/users/" + user_id + "/collections/",
//     params: {
//       "page": page,
//       "per_page": per_page
//     }
//   })
// };

// // UB4.检查用户是否收藏该图书
// export const checkCollectedBook = (user_id, book_id) => {
//   return axios({
//     method: GET,
//     url: baseUrl + "/users/" + user_id + "/collections/" + book_id,
//   })
// };

