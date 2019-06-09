
const user_id = {
  state:{
    user_id:null,
    username:null,
    password:null
  },
  getters:{
    // 参数列表state指的是state数据
    getUser(state){
      return state;
    }
  },
  actions:{
    setUser({commit,state}, user){
      commit("setUserId", user.id);
      commit("setUserName",user.username)
      commit("setPassword",user.password)
    }
  },
  // 4. mutations
  mutations:{
    // state指的是state的数据
    // name传递过来的数据
    setUserId(state, user_id){
      state.user_id = user_id;
    },
    setUserName(state, username){
      state.username = username;
    },
    setPassword(state,password){
      state.password=password
    }
  }
};

export default user_id;
