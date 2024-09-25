import request from '@/utils/request'

// 查询物料分类列表
export function listMaterialCategory(query) {
  return request({
    url: '/material/materialCategory/list',
    method: 'get',
    params: query
  })
}


