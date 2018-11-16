# Nebulas Blockchain Explorer
A Java web app for users to explore and analyze the nebulas blockchain.

Its frontend is a single page application using vuejs and its backend uses Spring.

Please visit https://explorer.nebulas.io/ to view all data in Nebulas.

## Design Overview
![Nebulas Explorer Design Overview](https://i.imgur.com/vEdztR4.jpg)

### Explorer Frontend
It's a web app using [vuejs](https://vuejs.org/) serves as the presentation layer for the nebulas blockchain explorer.

More details at https://github.com/nebulasio/explorer/blob/develop/explorer-front/readme.md.

### Explorer Backend
1. Data access REST APIs

Exposes the REST APIs for frontend to get blockchain data.

2. Data Loader

Loads the blockchain data from Nebulas Node and transform and store the data in a way the frontend can consume.

### MySQL
Data Storage for the loaded and transformed data.

### Nebulas Node
The explorer make RPCs to [Nebulas nodes remote endpoints](https://github.com/nebulasio/wiki/blob/master/rpc.md) to load the blockchain data.

# How to Contribute
## Decide what to do
As a beginner, you may want to pick an issue from issues with **help wanted** or **good first issue** tag and make a pull request for your changes.

After being more familiar with the explorer and the code, you can submit improvement ideas and work on those ideas.

## Git workflow
### Step 1: Clone git repo to your local
You can clone https://github.com/nebulasio/explorer.git and commit to it if you are a key contributor.

```shell
git clone https://github.com/nebulasio/explorer.git
```

Or you can fork git clone https://github.com/nebulasio/explorer.git and clone your forked repo.

### Step 2: Make some changes
1. Create a new branch for your change, use prefix "hotfix/" for bug fix, "feature/" for feature.
2. Make the change and commit with good commit message

### Step 3: Get the change merged
1. Push your local changes to remote repo
2. Create the pull request if you are using forked repo
3. Address review feedback and get the change merged

# Environment Setup
## Run the explorer frontend locally
### Step 1: Install Node.js
1. Install lastest node.js so you can execute 'npm' command by either downloading zip or installer from https://nodejs.org/en/download/ or via [package manager](https://nodejs.org/en/download/package-manager/)

### Step 2: Build and Run
```bash
# install dependency
cd explorer/explorer-front
npm i

# run webpack dev build on localhost
npm run dev

# open http://localhost:8080/ in browser
# config port number in explorer-front/config/index.js!dev.port

# run webpack production build, results will output to 'dist' folder
npm run build
```

## Run explorer backend locally
### Step 1: Install JDK 8, MySQL and Redis
* JDK 8: http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
* MySQL ：https://www.mysql.com/
* Redis ：https://redis.io/

### Step 2: Build, Run and Stop
```
cd explorer/explorer-backend
source build-expl.sh
source start-expl.sh
source stop-expl.sh
```

# License
The Nebulas explorer uses [LGPL license](https://github.com/nebulasio/explorer/blob/develop/LICENSE)
