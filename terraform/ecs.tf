# Define an ECS cluster for the fitness tracker application
resource "aws_ecs_cluster" "app_cluster" {
  name = "fitness-tracker-app"
}

# Output the ECS cluster ID for reference in other resources
output "ecs_cluster_id" {
  value = aws_ecs_cluster.app_cluster.id
}
