import request from '@/utils/request'

// 查询企业投标材料列表
export function listBidfile(query) {
  return request({
    url: '/bid/bidfile/list',
    method: 'get',
    params: query
  })
}

// 查询企业投标材料详细
export function getBidfile(id) {
  return request({
    url: '/bid/bidfile/' + id,
    method: 'get'
  })
}

// 新增企业投标材料
export function addBidfile(data) {
  return request({
    url: '/bid/bidfile',
    method: 'post',
    data: data
  })
}

// 修改企业投标材料
export function updateBidfile(data) {
  return request({
    url: '/bid/bidfile',
    method: 'put',
    data: data
  })
}

// 删除企业投标材料
export function delBidfile(id) {
  return request({
    url: '/bid/bidfile/' + id,
    method: 'delete'
  })
}

// 导出企业投标材料
export function exportBidfile(query) {
  return request({
    url: '/bid/bidfile/export',
    method: 'get',
    params: query
  })
}
