APPNAME=management
PORT=8085
docker build -t $APPNAME .
docker run -d --name $APPNAME -p $PORT:$PORT $APPNAME
