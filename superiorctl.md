```shell


sudo nano /etc/supervisor/conf.d/security-app.conf



[program:security-app]
command="java -jar /root/mini/1.jar"
directory=/root/pddapi
autostart=true
autorestart=true
startretries=5
startsecs=10
user=root
redirect_stderr=true
stdout_logfile=/var/log/supervisor/java-app.log
environment=HOME="/root",PATH="/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin"



sudo supervisorctl reread
sudo supervisorctl update

sudo supervisorctl restart security-app

```