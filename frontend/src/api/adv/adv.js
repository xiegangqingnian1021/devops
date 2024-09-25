import request from '@/utils/request'

// 查询广告信息列表
export function listAdv(query) {
  return request({
    url: '/adv/adv/list',
    method: 'get',
    params: query
  })
}

// 查询广告信息详细
export function getAdv(id) {
  return request({
    url: '/adv/adv/' + id,
    method: 'get'
  })
}

// 新增广告信息
export function addAdv(data) {
  return request({
    url: '/adv/adv',
    method: 'post',
    data: data
  })
}

// 修改广告信息
export function updateAdv(data) {
  return request({
    url: '/adv/adv',
    method: 'put',
    data: data
  })
}

// 删除广告信息
export function delAdv(id) {
  return request({
    url: '/adv/adv/' + id,
    method: 'delete'
  })
}

// 导出广告信息
export function exportAdv(query) {
  return request({
    url: '/adv/adv/export',
    method: 'get',
    params: query
  })
}


// 广告状态修改
export function changeStatus(id, status) {
  const data = {
    id,
    status
  }
  return request({
    url: '/adv/adv/changeStatus',
    method: 'put',
    data: data
  })
}