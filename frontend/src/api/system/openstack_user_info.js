import request from '@/utils/request'

// 查询用户信息列表
export function listOpenstack_user_info(query) {
  return request({
    url: '/system/openstack_user_info/list',
    method: 'get',
    params: query
  })
}

// 查询用户信息详细
export function getOpenstack_user_info(userId) {
  return request({
    url: '/system/openstack_user_info/' + userId,
    method: 'get'
  })
}

// 新增用户信息
export function addOpenstack_user_info(data) {
  return request({
    url: '/system/openstack_user_info',
    method: 'post',
    data: data
  })
}

// 修改用户信息
export function updateOpenstack_user_info(data) {
  return request({
    url: '/system/openstack_user_info',
    method: 'put',
    data: data
  })
}

// 删除用户信息
export function delOpenstack_user_info(userId) {
  return request({
    url: '/system/openstack_user_info/' + userId,
    method: 'delete'
  })
}

// 导出用户信息
export function exportOpenstack_user_info(query) {
  return request({
    url: '/system/openstack_user_info/export',
    method: 'get',
    params: query
  })
}