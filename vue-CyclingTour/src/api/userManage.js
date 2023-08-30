import request from "@/utils/request";

export default {
  queryList(searchModel) {
    return request({
      url: '/user/query',
      method: 'get',
      params: {
        currentPage: searchModel.currentPage,
        sizePage: searchModel.sizePage,
        username: searchModel.username,
        phone: searchModel.phone
      }
    });
  },
  addUser(user) {
    return request({
      url: '/user/add',
      method: 'post',
      data: user
    });
  },
  // 修改根据id查询到数据返回到对话框
  queryById(id) {
    return request({
      url: '/user/' + id,
      method: 'get',
    });
  },
//   编辑数据
  updateUser(user) {
    return request({
      url: '/user/update',
      method: 'post',
      data: user
    });
  },
//   删除
  deleteById(id) {
    return request({
      url: '/user/' + id,
      method: 'delete',
    });
  },

//   登录获取token  自定义获取数据
  infoB(token) {
    return request({
      url: "/user/info",
      method: 'get',
      params:{
        token
      }
    })
  }
}
