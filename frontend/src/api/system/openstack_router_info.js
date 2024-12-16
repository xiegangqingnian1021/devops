import request from '@/utils/request'

// 查询路由信息列表
export function listOpenstack_router_info(query) {
  return request({
    url: '/system/openstack_router_info/list',
    method: 'get',
    params: query
  })
}

// 查询路由信息详细
export function getOpenstack_router_info(routerId) {
  return request({
    url: '/system/openstack_router_info/' + routerId,
    method: 'get'
  })
}

// 新增路由信息
export function addOpenstack_router_info(data) {
  return request({
    url: '/system/openstack_router_info',
    method: 'post',
    data: data
  })
}

// 修改路由信息
export function updateOpenstack_router_info(data) {
  return request({
    url: '/system/openstack_router_info',
    method: 'put',
    data: data
  })
}

// 删除路由信息
export function delOpenstack_router_info(routerId) {
  return request({
    url: '/system/openstack_router_info/' + routerId,
    method: 'delete'
  })
}

// 导出路由信息
export function exportOpenstack_router_info(query) {
  return request({
    url: '/system/openstack_router_info/export',
    method: 'get',
    params: query
  })
}