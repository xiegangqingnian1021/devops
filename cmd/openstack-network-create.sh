
# 1.加载令牌
source /root/keystonerc_admin
if [ $? -ne 0 ]; then
  echo "1:令牌加载错误"
  exit
fi

# 接收位置参数
network_name=$(echo "$1")
project_id=$(echo "$2")
is_internal=$(echo $3) # 0 表示外部网络 1 表示内部网络

# 2.检测是否有重名网络
openstack network show ${network_name} &> /dev/null
if [ $? -eq 0 ]; then
  echo "2:网络重名"
  exit
fi

# 3.创建网络获取生成的网络ID
res=""
if [ ${is_internal} -eq 1 ]; then
  # 内部网络创建
  res=$(openstack network create --project ${project_id} --internal --format value --column id ${network_name})
else
  # 外部网络创建
  res=$(openstack network create --project ${project_id} --external --provider-network-type flat --provider-physical-network physnet1 --format value --column id ${network_name})
fi
if [ $? -ne 0 ]; then
  # 网络创建失败
  echo "3:网络创建失败:${res}"
  exit
fi
echo "0:网络创建成功:${res}"

