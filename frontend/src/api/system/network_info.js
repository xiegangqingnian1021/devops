import request from '@/utils/request'

// 查询网络管理列表
export function listNetwork_info(query) {
  return request({
    url: '/system/network_info/list',
    method: 'get',
    params: query
  })
}

// 查询网络管理详细
export function getNetwork_info(networkId) {
  return request({
    url: '/system/network_info/' + networkId,
    method: 'get'
  })
}

// 新增网络管理
export function addNetwork_info(data) {
  return request({
    url: '/system/network_info',
    method: 'post',
    data: data
  })
}

// 修改网络管理
export function updateNetwork_info(data) {
  return request({
    url: '/system/network_info',
    method: 'put',
    data: data
  })
}

// 删除网络管理
export function delNetwork_info(networkId) {
  return request({
    url: '/system/network_info/' + networkId,
    method: 'delete'
  })
}

// 导出网络管理
export function exportNetwork_info(query) {
  return request({
    url: '/system/network_info/export',
    method: 'get',
    params: query
  })
}