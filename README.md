# explorer
Block Explorer of Nebulas

nebulas explorer is a single page application, the front of explorer use vue framework to render page and backend of exploer provide restful api interface.

## 一、Environment Set Up：
### 1、The front of explorer
npm is shipped with node.js, here are two methods, you can choose either one

#### 1.1 download zip or installer
https://nodejs.org/en/download/
#### 1.2 via package manager
https://nodejs.org/en/download/package-manager/


### 2、The backend of explorer
#### 2.1 JDK
explorer-backend rely on jdk1.8

JDK download：http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
#### 2.2 Mysql
Mysql ：https://www.mysql.com/
#### 2.3 Redis
Redis ：https://redis.io/
 

## 二、Compile and Run 

### 1、DownLoad Source Code
```
git clone https://github.com/nebulasio/explorer.git
git checkout master
```

### 2、Compile and Run

### 2.1 Compile explorer-backend
```
git clone https://github.com/nebulasio/explorer.git
cd explorer/explorer-backend
source build-expl.sh
```
### 2.2 Run explorer-backend
```
source start-expl.sh
```
### 2.3 Stop explorer-backend
```
source stop-expl.sh
```
### 2.4 Install explorer-front
```
cd explorer/explorer-front
npm i
```
### 2.5 Run explorer-front
```
npm run dev
```
note: after run success, you can visit website through http://localhost:8080 






