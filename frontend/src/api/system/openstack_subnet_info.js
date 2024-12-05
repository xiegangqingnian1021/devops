import request from '@/utils/request'

// 查询子网管理列表
export function listOpenstack_subnet_info(query) {
  return request({
    url: '/system/openstack_subnet_info/list',
    method: 'get',
    params: query
  })
}

// 查询子网管理详细
export function getOpenstack_subnet_info(subnetId) {
  return request({
    url: '/system/openstack_subnet_info/' + subnetId,
    method: 'get'
  })
}

// 新增子网管理
export function addOpenstack_subnet_info(data) {
  return request({
    url: '/system/openstack_subnet_info',
    method: 'post',
    data: data
  })
}

// 修改子网管理
export function updateOpenstack_subnet_info(data) {
  return request({
    url: '/system/openstack_subnet_info',
    method: 'put',
    data: data
  })
}

// 删除子网管理
export function delOpenstack_subnet_info(subnetId) {
  return request({
    url: '/system/openstack_subnet_info/' + subnetId,
    method: 'delete'
  })
}

// 导出子网管理
export function exportOpenstack_subnet_info(query) {
  return request({
    url: '/system/openstack_subnet_info/export',
    method: 'get',
    params: query
  })
}