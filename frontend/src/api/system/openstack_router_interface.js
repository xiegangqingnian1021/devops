import request from '@/utils/request'

// 查询路由接口列表
export function listOpenstack_router_interface(query) {
  return request({
    url: '/system/openstack_router_interface/list',
    method: 'get',
    params: query
  })
}

// 查询路由接口详细
export function getOpenstack_router_interface(id) {
  return request({
    url: '/system/openstack_router_interface/' + id,
    method: 'get'
  })
}

// 新增路由接口
export function addOpenstack_router_interface(data) {
  return request({
    url: '/system/openstack_router_interface',
    method: 'post',
    data: data
  })
}

// 修改路由接口
export function updateOpenstack_router_interface(data) {
  return request({
    url: '/system/openstack_router_interface',
    method: 'put',
    data: data
  })
}

// 删除路由接口
export function delOpenstack_router_interface(id) {
  return request({
    url: '/system/openstack_router_interface/' + id,
    method: 'delete'
  })
}

// 导出路由接口
export function exportOpenstack_router_interface(query) {
  return request({
    url: '/system/openstack_router_interface/export',
    method: 'get',
    params: query
  })
}