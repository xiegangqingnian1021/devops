import request from '@/utils/request'

// 查询演示列表
export function listDemoUser(query) {
  return request({
    url: '/demo/demoUser/list',
    method: 'get',
    params: query
  })
}

// 查询演示详细
export function getDemoUser(id) {
  return request({
    url: '/demo/demoUser/' + id,
    method: 'get'
  })
}

// 新增演示
export function addDemoUser(data) {
  return request({
    url: '/demo/demoUser',
    method: 'post',
    data: data
  })
}

// 修改演示
export function updateDemoUser(data) {
  return request({
    url: '/demo/demoUser',
    method: 'put',
    data: data
  })
}

// 删除演示
export function delDemoUser(id) {
  return request({
    url: '/demo/demoUser/' + id,
    method: 'delete'
  })
}

// 导出演示
export function exportDemoUser(query) {
  return request({
    url: '/demo/demoUser/export',
    method: 'get',
    params: query
  })
}