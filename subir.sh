mvn clean package
eval $(minikube docker-env)
docker build --no-cache -t vam-backend:1.0.2 .
eval $(minikube docker-env -u)
kubectl apply -f backend-deployment.yaml
kubectl apply -f backend-service.yaml
