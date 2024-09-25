import request from '@/utils/request'

// 查询城市信息列表
export function listCity(query) {
  return request({
    url: '/location/city/list',
    method: 'get',
    params: query
  })
}

// 查询城市信息详细
export function getCity(id) {
  return request({
    url: '/location/city/' + id,
    method: 'get'
  })
}

// 新增城市信息
export function addCity(data) {
  return request({
    url: '/location/city',
    method: 'post',
    data: data
  })
}

// 修改城市信息
export function updateCity(data) {
  return request({
    url: '/location/city',
    method: 'put',
    data: data
  })
}

// 删除城市信息
export function delCity(id) {
  return request({
    url: '/location/city/' + id,
    method: 'delete'
  })
}

// 导出城市信息
export function exportCity(query) {
  return request({
    url: '/location/city/export',
    method: 'get',
    params: query
  })
}