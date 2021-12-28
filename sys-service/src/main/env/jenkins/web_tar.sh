#!/bin/bash
echo "start ..."
zip_dir="/usr/app/$1"
dist_dir="/usr/app/$1/dist"
dist_dist_dir="/usr/app/$1/dist/dist"

cd ${zip_dir}
sudo rm -rf dist
sudo unzip -o dist.zip

#如果解压后有重复的dist文件夹，移除多余的一层dist
if [ ! -d ${dist_dist_dir} ]; then
  mv -f ${dist_dist_dir} ${dist_dir}
fi

echo "end ..."
