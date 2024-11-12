import request from '@/utils/request'

// 查询镜像信息列表
export function listOpenstack_image_info(query) {
  return request({
    url: '/system/openstack_image_info/list',
    method: 'get',
    params: query
  })
}

// 查询镜像信息详细
export function getOpenstack_image_info(imageId) {
  return request({
    url: '/system/openstack_image_info/' + imageId,
    method: 'get'
  })
}

// 新增镜像信息
export function addOpenstack_image_info(data) {
  return request({
    url: '/system/openstack_image_info',
    method: 'post',
    data: data
  })
}

// 修改镜像信息
export function updateOpenstack_image_info(data) {
  return request({
    url: '/system/openstack_image_info',
    method: 'put',
    data: data
  })
}

// 删除镜像信息
export function delOpenstack_image_info(imageId) {
  return request({
    url: '/system/openstack_image_info/' + imageId,
    method: 'delete'
  })
}

// 导出镜像信息
export function exportOpenstack_image_info(query) {
  return request({
    url: '/system/openstack_image_info/export',
    method: 'get',
    params: query
  })
}