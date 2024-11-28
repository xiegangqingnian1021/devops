
# 1.加载令牌
source /root/keystonerc_admin
if [ $? -ne 0 ]; then
  echo "1:令牌加载错误"
  exit
fi

# 位置参数
network_id=$(echo "$1")
subnet_name=$(echo "$2")
project_id=$(echo "$3")
subnet_range=$(echo "$4")
gateway=$(echo "$5")

# 2.确定网络是否存在
openstack network show ${network_id} &> /dev/null
if [ $? -ne 0 ]; then
  echo "2:网络不存在"
  exit
fi

# 3.确定子网是否重名
openstack subnet show ${subnet_name} &> /dev/null
if [ $? -eq 0 ]; then
  echo "3:子网重名"
  exit
fi

# 4.创建子网
res=$(openstack subnet create --project ${project_id} --subnet-range ${subnet_range} --dhcp --gateway ${gateway} --network ${network_id} --dns-nameserver 114.114.114.114  --format value --column id ${subnet_name})
if [ $? -ne 0 ]; then
  echo "4:创建失败:${res}"
  exit
fi

echo "0:创建成功:${res}"

