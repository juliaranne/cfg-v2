resource "aws_cloudwatch_log_group" "frontend_log_group" {
  name              = "/ecs/fitness-app-frontend"
  retention_in_days = 1
}

resource "aws_cloudwatch_log_group" "activity_tracking_log_group" {
  name              = "/ecs/fitness-app-activity-tracking"
  retention_in_days = 1
}

resource "aws_cloudwatch_log_group" "analytics_v2_log_group" {
  name              = "/ecs/fitness-app-analytics_v2"
  retention_in_days = 1
}

resource "aws_cloudwatch_log_group" "authservice_log_group" {
  name              = "/ecs/fitness-app-authservice"
  retention_in_days = 1
}

resource "aws_cloudwatch_log_group" "ai_service_log_group" {
  name              = "/ecs/fitness-app-ai-service"
  retention_in_days = 1
}

resource "aws_cloudwatch_log_group" "mongodb_log_group" {
  name              = "/ecs/fitness-app-mongodb-service"
  retention_in_days = 1
}
