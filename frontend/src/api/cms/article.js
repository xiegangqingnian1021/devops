import request from '@/utils/request'

// 查询文章管理列表
export function listArticle(query) {
  return request({
    url: '/cms/article/list',
    method: 'get',
    params: query
  })
}

// 查询文章管理详细
export function getArticle(id) {
  return request({
    url: '/cms/article/' + id,
    method: 'get'
  })
}

// 新增文章管理
export function addArticle(data) {
  return request({
    url: '/cms/article',
    method: 'post',
    data: data
  })
}

// 修改文章管理
export function updateArticle(data) {
  return request({
    url: '/cms/article',
    method: 'put',
    data: data
  })
}

// 删除文章管理
export function delArticle(id) {
  return request({
    url: '/cms/article/' + id,
    method: 'delete'
  })
}

// 导出文章管理
export function exportArticle(query) {
  return request({
    url: '/cms/article/export',
    method: 'get',
    params: query
  })
}