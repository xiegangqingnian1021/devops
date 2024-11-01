
# 准备参数
user_id=$(echo $1)

# 1.获取管理员令牌
source /root/keystonerc_admin &> /dev/null
if [ $? -ne 0 ]; then
  echo "1:加载令牌失败"
  exit
fi

# 2.判断用户是否存在
openstack user show $user_id &> /dev/null
if [ $? -ne 0 ]; then
  echo "2:用户不存在"
  exit
fi

# 3.用openstack执行用户删除
res=$(openstack user delete $user_id)
if [ $? -ne 0 ]; then
  echo "3:$res"
  exit
fi
echo "0:删除成功"
