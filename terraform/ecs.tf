resource "aws_ecs_cluster" "app_cluster" {
  name = "fitness-tracker-app"
}

output "ecs_cluster_id" {
  value = aws_ecs_cluster.app_cluster.id
}
