import request from '@/utils/request'

// 查询供应物料列表
export function listSupplierMaterial(query) {
  return request({
    url: '/purchase/supplierMaterial/list',
    method: 'get',
    params: query
  })
}

// 查询供应物料详细
export function getSupplierMaterial(id) {
  return request({
    url: '/purchase/supplierMaterial/' + id,
    method: 'get'
  })
}

// 新增供应物料
export function addSupplierMaterial(data) {
  return request({
    url: '/purchase/supplierMaterial',
    method: 'post',
    data: data
  })
}

// 修改供应物料
export function updateSupplierMaterial(data) {
  return request({
    url: '/purchase/supplierMaterial',
    method: 'put',
    data: data
  })
}

// 删除供应物料
export function delSupplierMaterial(id) {
  return request({
    url: '/purchase/supplierMaterial/' + id,
    method: 'delete'
  })
}

// 导出供应物料
export function exportSupplierMaterial(query) {
  return request({
    url: '/purchase/supplierMaterial/export',
    method: 'get',
    params: query
  })
}