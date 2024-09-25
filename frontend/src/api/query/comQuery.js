import request from '@/utils/request'

// 查询通用查询列表
export function listComQuery(query) {
  return request({
    url: '/query/comQuery/list',
    method: 'get',
    params: query
  })
}

// 查询通用查询详细
export function getComQuery(id) {
  return request({
    url: '/query/comQuery/' + id,
    method: 'get'
  })
}

// 新增通用查询
export function addComQuery(data) {
  return request({
    url: '/query/comQuery',
    method: 'post',
    data: data
  })
}

// 修改通用查询
export function updateComQuery(data) {
  return request({
    url: '/query/comQuery',
    method: 'put',
    data: data
  })
}

// 删除通用查询
export function delComQuery(id) {
  return request({
    url: '/query/comQuery/' + id,
    method: 'delete'
  })
}

// 导出通用查询
export function exportComQuery(query) {
  return request({
    url: '/query/comQuery/export',
    method: 'get',
    params: query
  })
}

export function testBizData(data) {
  return request({
    url: '/query/comQuery/test',
    method: 'post',
    data: data
  })
}

export function getQueryData(code,query) {
  return request({
    url: '/query/comQuery/data/' + code,
    method: 'get',
    params: query,
  })
}

export function checkCode(code) {
  return request({
    url: '/query/comQuery/check/' + code,
    method: 'get',
  })
}