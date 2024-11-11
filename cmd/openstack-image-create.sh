# 1.加载令牌
source /root/keystonerc_admin
if [ $? -ne 0 ]; then
  echo "1:令牌加载失败"
  exit
fi

# 接收参数
project_id=$(echo "$1")
image_name=$(echo "$2")
disk_format=$(echo "$3")
container_format=$(echo "$4")
min_ram=$(echo "$5")
min_disk=$(echo "$6")
is_private=$(echo $7)
is_protected=$(echo $8)
image_path=$(echo "$9")

# 2.验证镜像是否已经存在
openstack image show ${image_name} &> /dev/null
if [ $? -eq 0 ]; then
  echo "2:镜像已经存在"
  exit
fi

# 3.创建镜像
public_flag="--public"
if [ $is_private -eq 1 ]; then
  public_flag="--private"
fi

protect_flag="--unprotected"
if [ $is_protected -eq 1 ]; then
  protect_flag="--protected"
fi

res=$(openstack image create --container-format ${container_format} --disk-format ${disk_format} ${protect_flag} ${public_flag} --project ${project_id} --min-disk ${min_disk} --min-ram ${min_ram} --file ${image_path} --format value --column id ${image_name})
if [ $? -ne 0 ]; then
  echo "3:镜像创建失败"
  exit
fi

echo "0:镜像创建成功:$res"

