
# lock controller
## add
curl 'localhost:8080/lock/add'

## desc

curl 'localhost:8080/lock/desc'


#post

curl --location 'localhost:8080/app/e1' \
--header 'Content-Type: application/json' \
--data '{"id":1,"name":"20230512"}'
