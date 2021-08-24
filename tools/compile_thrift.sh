#!/bin/sh

DIR=`cd \`dirname ${BASH_SOURCE[0]}\`/.. && pwd`
#echo ${DIR}/sb-rpc/src/main/java/com/pg633/thrift
rm -fr ${DIR}/sb-rpc/src/main/java/com/pg633/thrift
thrift --gen java -out ${DIR}/sb-rpc/src/main/java/ ${DIR}/sb-rpc/src/main/resources/thrift/EchoService.thrift
