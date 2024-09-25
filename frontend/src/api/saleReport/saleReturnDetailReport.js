import request from '@/utils/request'

// 查询发货退货明细报表列表
export function listSaleReturnDetailReport(query) {
  return request({
    url: '/saleReport/saleReturnDetailReport/list',
    method: 'get',
    params: query
  })
}


// 导出发货退货明细报表
export function exportSaleReturnDetailReport(query) {
  return request({
    url: '/saleReport/saleReturnDetailReport/export',
    method: 'get',
    params: query
  })
}


// 按月份统计销售-发货-退货报表
export function monthReport(query) {
  return request({
    url: '/saleReport/saleReturnDetailReport/byMonth',
    method: 'get',
    params: query
  })
}

// 按季度统计销售-发货-退货报表
export function quarterReport(query) {
  return request({
    url: '/saleReport/saleReturnDetailReport/byQuarter',
    method: 'get',
    params: query
  })
}