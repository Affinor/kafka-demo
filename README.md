ngx_kafka_module+Kafka搭建步骤
##一、安装nginx
##二、安装zookeeper

~~~
tar -zxf zookeeper-3.4.14.tar.gz -C /opt cd /opt/zookeeper-3.4.14/conf 
# 复制zoo_sample.cfg命名为zoo.cfg 
cp zoo_sample.cfg zoo.cfg 
# 编辑zoo.cfg文件 
vim zoo.cfg
zkServer.sh start 
~~~

##三、安装kafka

~~~
tar -zxf kafka_2.12-1.0.2.tgz -C /opt
vim /etc/profile
配置/opt/kafka_2.12-1.0.2/config中的server.properties文件
sh kafka-server-start.sh -daemon ../config/server.properties 
~~~

##四、安装ngx_kafka_module
1、安装 librdkafka

~~~
git clone https://github.com/edenhill/librdkafka
cd librdkafka
./configure
make
sudo make install
~~~

2、添加ngx_kafka_module

~~~
git clone https://github.com/brg-liuwei/ngx_kafka_module

# cd /path/to/nginx
./configure --add-module=/path/to/ngx_kafka_module

make

sudo make install
~~~

3、配置nginx

~~~
server {
        listen       80;
        server_name  localhost;
        kafka;
        location = /log {
            
            add_header 'Access-Control-Allow-Origin' $http_origin;
            add_header 'Access-Control-Allow-Credentials' 'true';
            add_header 'Access-Control-Allow-Methods' 'GET, POST, OPTIONS';
            add_header 'Access-Control-Allow-Headers' 'DNT,web-token,app-token,Authorization,Accept,Origin,Keep-Alive,User-Agent,X-Mx-ReqToken,X-Data-Type,X-Auth-Token,X-Re
quested-With,If-Modified-Since,Cache-Control,Content-Type,Range';
            add_header 'Access-Control-Expose-Headers' 'Content-Length,Content-Range';
            if ($request_method = 'OPTIONS') {
                add_header 'Access-Control-Max-Age' 1728000;
                add_header 'Content-Type' 'text/plain; charset=utf-8';
                add_header 'Content-Length' 0;
                return 204;
            }
            kafka_topic   log_topic;
        }

~~~
