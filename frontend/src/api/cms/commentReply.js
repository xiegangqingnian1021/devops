import { listComment, getComment, delComment, addComment, updateComment, exportComment } from "@/api/cms/comment";

// 查询评论回复列表
export function listCommentReply(query) {
  return listComment(query)
}

// 查询评论回复详细
export function getCommentReply(id) {
  return getComment(id)
}

// 新增评论回复
export function addCommentReply(data) {
  return addComment(data)
}

// 修改评论回复
export function updateCommentReply(data) {
  return updateComment(data)
}

// 删除评论回复
export function delCommentReply(id) {
  return delComment(id)
}

// 导出评论回复
export function exportCommentReply(query) {
  return exportComment(query)
}