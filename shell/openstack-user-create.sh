# 1.加载令牌
source /root/keystonerc_admin
if [ $? -ne 0 ]; then
  echo "1:令牌加载失败"
  exit
fi

user_name=$(echo "$1")
user_pwd=$(echo "$2")
user_email=$(echo "$3")
user_enable=$(echo "$4")

# 2.判定用户是否已经存在
openstack user show ${user_name} &> /dev/null
if [ $? -eq 0 ]; then
  echo "2:用户已存在"
  exit
fi

# 3.创建用户
res=""
if [ "$user_enable" == "1" ]; then
  res=$(openstack user create --password ${user_pwd} --email ${user_email} --enable --format value -c id ${user_name})
else
  res=$(openstack user create --password ${user_pwd} --email ${user_email} --disable --format value -c id ${user_name})
fi
if [ $? -ne 0 ]; then
  echo "3:用户创建失败:${res}"
  exit
fi

echo "0:用户创建成功:${res}"
