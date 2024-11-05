import request from '@/utils/request'

// 查询租户用户列表
export function listOpenstack_project_user(query) {
  return request({
    url: '/system/openstack_project_user/list',
    method: 'get',
    params: query
  })
}

// 查询租户用户详细
export function getOpenstack_project_user(id) {
  return request({
    url: '/system/openstack_project_user/' + id,
    method: 'get'
  })
}

// 新增租户用户
export function addOpenstack_project_user(data) {
  return request({
    url: '/system/openstack_project_user',
    method: 'post',
    data: data
  })
}

// 修改租户用户
export function updateOpenstack_project_user(data) {
  return request({
    url: '/system/openstack_project_user',
    method: 'put',
    data: data
  })
}

// 删除租户用户
export function delOpenstack_project_user(id) {
  return request({
    url: '/system/openstack_project_user/' + id,
    method: 'delete'
  })
}

// 导出租户用户
export function exportOpenstack_project_user(query) {
  return request({
    url: '/system/openstack_project_user/export',
    method: 'get',
    params: query
  })
}