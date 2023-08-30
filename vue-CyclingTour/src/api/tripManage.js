import request from "@/utils/request";
import {getInfo} from "@/api/user";

export default {
  queryList(searchModel) {
    return request({
      url: '/trip/query',
      method: 'get',
      params: {
        currentPage: searchModel.currentPage,
        sizePage: searchModel.sizePage,
        routeName: searchModel.routeName,
        routeRating: searchModel.routeRating
      }
    });
  },
  addTrip(trip) {
    return request({
      url: '/trip/add',
      method: 'post',
      data:trip
    });
  },
  // 修改根据id查询到数据返回到对话框
  queryById(id) {
    return request({
      url: '/trip/'+id,
      method: 'get',
    });
  },
//   编辑数据
  updateTrip(trip) {
    return request({
      url: '/trip/update',
      method: 'post',
      data:trip
    });
  },
//   删除
  deleteById( id) {
    return request({
      url: '/trip/'+id,
      method: 'delete',
    });
  },

//   查询数据用于画图
  listTwo() {
    return request({
      url: '/trip',
      method: 'get',
    });
  },


//   骑行挑战 新增
  addChallenge(challenge) {
    return request({
      url: '/challenge/add',
      method: 'post',
      data:challenge
    });
  },

// 挑战记录
  recordList(){
    return request({
      url:"/challenge",
      method:'get'
    })
  }
}
