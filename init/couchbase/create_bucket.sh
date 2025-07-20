#!/bin/bash

# Esperar a que Couchbase esté listo, dar permisos si es necesario
# Sino crear el usuario y el bucket correpondiente de forma manual
sleep 20

# Crear el bucket
curl -u Servico_Franquicias:iaq!pQZ!2p5dCNh \
  -X POST http://localhost:8091/pools/default/buckets \
  -d name=Franquicia_Img \
  -d ramQuotaMB=100 \
  -d authType=sasl \
  -d replicaNumber=1

echo "✅ Bucket creado: Franquicia_Img"
