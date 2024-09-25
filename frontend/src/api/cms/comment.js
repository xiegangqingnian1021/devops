import request from '@/utils/request'

// 查询文章评论列表
export function listComment(query) {
  return request({
    url: '/cms/comment/list',
    method: 'get',
    params: query
  })
}

// 查询文章评论详细
export function getComment(id) {
  return request({
    url: '/cms/comment/' + id,
    method: 'get'
  })
}

// 新增文章评论
export function addComment(data) {
  return request({
    url: '/cms/comment',
    method: 'post',
    data: data
  })
}

// 修改文章评论
export function updateComment(data) {
  return request({
    url: '/cms/comment',
    method: 'put',
    data: data
  })
}

// 删除文章评论
export function delComment(id) {
  return request({
    url: '/cms/comment/' + id,
    method: 'delete'
  })
}

// 导出文章评论
export function exportComment(query) {
  return request({
    url: '/cms/comment/export',
    method: 'get',
    params: query
  })
}