namespace java com.pg633.thrift

service EchoServiceThrift {
    string echo(1: string message);
}