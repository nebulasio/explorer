# Nebulas Explorer

How to Build

```
source build-expl.sh
```

How to Run

```
source start-expl.sh
```

How to Stop

```
source stop-expl.sh
```

### Docker

How to Build

```
gradle build buildDocker
```

How to Run

```
docker-compose up
```

### Coding

Enable Annotation Processing

### GRPC problem

Since the version of grpc is too low, need to set GRPC_PROXY_EXP to walk around

```
export GRPC_PROXY_EXP="http://127.0.0.1:6152"
```

see [google issues](https://github.com/GoogleCloudPlatform/java-docs-samples/issues/833)
