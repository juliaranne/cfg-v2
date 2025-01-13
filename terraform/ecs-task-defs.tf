# Data source to fetch the AWS caller identity (typically used to get the current account details)
data "aws_caller_identity" "current" {}

# Task definition for the activity tracking service on ECS
resource "aws_ecs_task_definition" "activity_tracking" {
  family                   = "activity-tracking-task"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn
  task_role_arn            = aws_iam_role.ecs_task_execution_role.arn

  container_definitions = jsonencode([{
    name      = "activity-tracking"
    image     = "${aws_ecr_repository.image_repos["activity-tracking"].repository_url}"
    essential = true

    environment = [
      {
        name  = "MONGO_URI"
        value = "mongodb://root:cfgmla23@10.0.3.28:27017"                # The TF project will have been destroyed so exposed IP is of no risk..  
      },
      {
        name  = "MONGO_DB"
        value = "activity"
      }
    ]

    portMappings = [{
      containerPort = 5300
      hostPort      = 5300
      protocol      = "tcp"
    }]

    logConfiguration = {
      logDriver = "awslogs"
      options = {
        "awslogs-group"         = "${aws_cloudwatch_log_group.activity_tracking_log_group.name}"
        "awslogs-region"        = "eu-west-2"
        "awslogs-stream-prefix" = "activity-tracking"
      }
    }
  }])
}

# Task definition for the analytics v2 service on ECS
resource "aws_ecs_task_definition" "analytics_v2" {
  family                   = "analytics-v2-task"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn
  task_role_arn            = aws_iam_role.ecs_task_execution_role.arn

  container_definitions = jsonencode([{
    name      = "analytics-v2"
    image     = "${aws_ecr_repository.image_repos["analytics_v2"].repository_url}"
    essential = true

    environment = [
      {
        name  = "MONGO_URI"
        value = "mongodb://root:cfgmla23@10.0.3.28:27017"                # The TF project will have been destroyed so exposed IP is of no risk.
      },
      {
        name  = "MONGO_DB"
        value = "activity"
      }
    ]

    portMappings = [{
      containerPort = 5051
      hostPort      = 5051
      protocol      = "tcp"
    }]

    logConfiguration = {
      logDriver = "awslogs"
      options = {
        "awslogs-group"         = "${aws_cloudwatch_log_group.analytics_v2_log_group.name}"
        "awslogs-region"        = "eu-west-2"
        "awslogs-stream-prefix" = "analytics-v2"
      }
    }
  }])
}

# Task definition for the AI service on ECS
resource "aws_ecs_task_definition" "ai_service" {
  family                   = "ai-service-task"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn
  task_role_arn            = aws_iam_role.ecs_task_execution_role.arn

  container_definitions = jsonencode([{
    name      = "ai-service"
    image     = "${aws_ecr_repository.image_repos["ai_service"].repository_url}"
    essential = true

    environment = [
      {
        name  = "MONGO_URI"
        value = "mongodb://root:cfgmla23@10.0.3.28:27017"                # The TF project will have been destroyed so exposed IP is of no risk.
      }
    ]

    portMappings = [{
      containerPort = 5052
      hostPort      = 5052
      protocol      = "tcp"
    }]

    logConfiguration = {
      logDriver = "awslogs"
      options = {
        "awslogs-group"         = "${aws_cloudwatch_log_group.ai_service_log_group.name}"
        "awslogs-region"        = "eu-west-2"
        "awslogs-stream-prefix" = "ai-service"
      }
    }
  }])
}

# Task definition for the auth service on ECS
resource "aws_ecs_task_definition" "authservice" {
  family                   = "authservice-task"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn
  task_role_arn            = aws_iam_role.ecs_task_execution_role.arn

  container_definitions = jsonencode([{
    name      = "authservice"
    image     = "${aws_ecr_repository.image_repos["authservice"].repository_url}"
    essential = true

    environment = [
      {
        name  = "SPRING_DATA_MONGODB_DATABASE"
        value = "authservice"
      },
      {
        name  = "SPRING_DATA_MONGODB_URI"
        value = "mongodb://root:cfgmla23@10.0.3.28:27017"             # The TF project will have been destroyed so exposed IP is of no risk..  
      }
    ]

    portMappings = [{
      containerPort = 8080
      hostPort      = 8080
      protocol      = "tcp"
    }]

    logConfiguration = {
      logDriver = "awslogs"
      options = {
        "awslogs-group"         = "${aws_cloudwatch_log_group.authservice_log_group.name}"
        "awslogs-region"        = "eu-west-2"
        "awslogs-stream-prefix" = "authservice"
      }
    }
  }])
}

# Task definition for the frontend service on ECS
resource "aws_ecs_task_definition" "frontend" {
  family                   = "frontend-task"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn
  task_role_arn            = aws_iam_role.ecs_task_execution_role.arn

  container_definitions = jsonencode([{
    name      = "frontend"
    image     = "${aws_ecr_repository.image_repos["frontend"].repository_url}"
    essential = true

    portMappings = [{
      containerPort = 80
      hostPort      = 80
      protocol      = "tcp"
    }]

    logConfiguration = {
      logDriver = "awslogs"
      options = {
        "awslogs-group"         = "${aws_cloudwatch_log_group.frontend_log_group.name}"
        "awslogs-region"        = "eu-west-2"
        "awslogs-stream-prefix" = "frontend"
      }
    }
  }])
}

# Task definition for the MongoDB container on ECS
resource "aws_ecs_task_definition" "mongodb_task" {
  family                   = "mongodb-task"
  network_mode             = "awsvpc"
  requires_compatibilities = ["FARGATE"]
  cpu                      = "256"
  memory                   = "512"
  execution_role_arn       = aws_iam_role.ecs_task_execution_role.arn
  task_role_arn            = aws_iam_role.ecs_task_execution_role.arn

  container_definitions = jsonencode([{
    name      = "mongodb"
    image     = "mongo:latest"
    essential = true

    environment = [
      {
        name  = "MONGO_INITDB_ROOT_USERNAME"
        value = "root"
      },
      {
        name  = "MONGO_INITDB_ROOT_PASSWORD"
        value = "cfgmla23"
      }
    ]

    portMappings = [{
      containerPort = 27017
      hostPort      = 27017
      protocol      = "tcp"
    }]

    logConfiguration = {
      logDriver = "awslogs"
      options = {
        "awslogs-group"         = "${aws_cloudwatch_log_group.mongodb_log_group.name}"
        "awslogs-region"        = "eu-west-2"
        "awslogs-stream-prefix" = "mongodb"
      }
    }
  }])
}
