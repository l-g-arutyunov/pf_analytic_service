#!/bin/bash

cat ./k8s/deployment.yaml |
sed -r "s/ENV_JOB_BASE_NAME/$1/g" |
sed -r "s/ENV_VERSION/$2/g;" |
sed -r "s/ENV_BUILD_NUMBER/$3/g;" |
sed -r "s/ENV_DATASOURCE_HOST/$4/g;" |
sed -r "s/ENV_DATASOURCE_PORT/\"$5\"/g;" |
sed -r "s/ENV_SERVICE_NAME/$6/g;" |
sed -r "s/ENV_SERVICE_PORT/\"$7\"/g;" |
kubectl apply -f -
