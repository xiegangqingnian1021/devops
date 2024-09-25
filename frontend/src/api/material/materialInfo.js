import request from '@/utils/request'

// 查询物料档案管理列表
export function listMaterialInfo(query) {
  return request({
    url: '/material/materialInfo/list',
    method: 'get',
    params: query
  })
}


