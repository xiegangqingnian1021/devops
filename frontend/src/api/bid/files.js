import request from '@/utils/request'

// 查询招标信息附件列表
export function listFiles(query) {
  return request({
    url: '/bid/files/list',
    method: 'get',
    params: query
  })
}

// 查询招标信息附件详细
export function getFiles(id) {
  return request({
    url: '/bid/files/' + id,
    method: 'get'
  })
}

// 新增招标信息附件
export function addFiles(data) {
  return request({
    url: '/bid/files',
    method: 'post',
    data: data
  })
}

// 修改招标信息附件
export function updateFiles(data) {
  return request({
    url: '/bid/files',
    method: 'put',
    data: data
  })
}

// 删除招标信息附件
export function delFiles(id) {
  return request({
    url: '/bid/files/' + id,
    method: 'delete'
  })
}

// 导出招标信息附件
export function exportFiles(query) {
  return request({
    url: '/bid/files/export',
    method: 'get',
    params: query
  })
}
