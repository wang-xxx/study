# 基础镜像
FROM openjdk:17

#声明一个挂载点，容器内此路径会对应宿主机的某个文件夹
VOLUME /tmp

#应用构建成功后的jar文件被复制到镜像内，名字也改成了*.jar
ADD target/springboot-docker-1.0-SNAPSHOT.jar /temp/springboot-docker.jar

#执行
ENTRYPOINT ["java", "-jar", "/temp/springboot-docker.jar"]

#暴露端口
EXPOSE 8082