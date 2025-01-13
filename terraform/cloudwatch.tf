# CloudWatch Log Group for the Frontend service
resource "aws_cloudwatch_log_group" "frontend_log_group" {
  # The name of the log group, which will be used to store logs for the frontend ECS service
  name              = "/ecs/fitness-app-frontend"
  
  # Retention period in days, logs will be deleted after this period
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
