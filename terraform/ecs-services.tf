resource "aws_ecs_service" "activity_tracking_service" {
  name            = "activity-tracking-service"
  cluster         = aws_ecs_cluster.app_cluster.id
  task_definition = aws_ecs_task_definition.activity_tracking.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets          = module.vpc.public_subnets
    security_groups  = [aws_security_group.ecs_sg.id]
    assign_public_ip = true
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.activity_tracking_target_group.arn
    container_name   = "activity-tracking"
    container_port   = 5300
  }

  depends_on = [aws_lb.app_lb, aws_lb_target_group.activity_tracking_target_group]
}

resource "aws_ecs_service" "analytics_v2_service" {
  name            = "analytics-v2-service"
  cluster         = aws_ecs_cluster.app_cluster.id
  task_definition = aws_ecs_task_definition.analytics_v2.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets          = module.vpc.public_subnets
    security_groups  = [aws_security_group.ecs_sg.id]
    assign_public_ip = true
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.analytics_v2_target_group.arn
    container_name   = "analytics-v2"
    container_port   = 5051
  }

  depends_on = [aws_lb.app_lb, aws_lb_target_group.analytics_v2_target_group]
}

resource "aws_ecs_service" "ai_service" {
  name            = "ai-service"
  cluster         = aws_ecs_cluster.app_cluster.id
  task_definition = aws_ecs_task_definition.ai_service.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets          = module.vpc.public_subnets
    security_groups  = [aws_security_group.ecs_sg.id]
    assign_public_ip = true
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.ai_service_target_group.arn
    container_name   = "ai-service"
    container_port   = 5052
  }

  depends_on = [aws_lb.app_lb, aws_lb_target_group.ai_service_target_group]
}

resource "aws_ecs_service" "auth_service" {
  name            = "auth-service"
  cluster         = aws_ecs_cluster.app_cluster.id
  task_definition = aws_ecs_task_definition.authservice.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets          = module.vpc.public_subnets
    security_groups  = [aws_security_group.ecs_sg.id]
    assign_public_ip = true
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.auth_service_target_group.arn
    container_name   = "authservice"
    container_port   = 8080
  }

  depends_on = [aws_lb.app_lb, aws_lb_target_group.auth_service_target_group]
}

resource "aws_ecs_service" "frontend_service" {
  name            = "frontend-service"
  cluster         = aws_ecs_cluster.app_cluster.id
  task_definition = aws_ecs_task_definition.frontend.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets          = module.vpc.public_subnets
    security_groups  = [aws_security_group.ecs_sg.id]
    assign_public_ip = true
  }

  load_balancer {
    target_group_arn = aws_lb_target_group.frontend_target_group.arn
    container_name   = "frontend"
    container_port   = 80
  }

  depends_on = [aws_lb.app_lb, aws_lb_target_group.frontend_target_group]
}

resource "aws_ecs_service" "mongodb_service" {
  name            = "mongodb-service"
  cluster         = aws_ecs_cluster.app_cluster.id
  task_definition = aws_ecs_task_definition.mongodb_task.arn
  desired_count   = 1
  launch_type     = "FARGATE"

  network_configuration {
    subnets          = module.vpc.public_subnets
    security_groups  = [aws_security_group.mongodb_sg.id]
    assign_public_ip = true
  }
}
