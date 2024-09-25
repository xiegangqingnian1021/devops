import request from '@/utils/request'

// 查询租户信息列表
export function listProject_info(query) {
  return request({
    url: '/system/openstack_project_info/list',
    method: 'get',
    params: query
  })
}

// 查询租户信息详细
export function getProject_info(projectId) {
  return request({
    url: '/system/openstack_project_info/' + projectId,
    method: 'get'
  })
}

// 新增租户信息
export function addProject_info(data) {
  return request({
    url: '/system/openstack_project_info',
    method: 'post',
    data: data
  })
}

// 修改租户信息
export function updateProject_info(data) {
  return request({
    url: '/system/openstack_project_info',
    method: 'put',
    data: data
  })
}

// 删除租户信息
export function delProject_info(projectId) {
  return request({
    url: '/system/openstack_project_info/' + projectId,
    method: 'delete'
  })
}

// 导出租户信息
export function exportProject_info(query) {
  return request({
    url: '/system/openstack_project_info/export',
    method: 'get',
    params: query
  })
}
