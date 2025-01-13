// Application Load Balancer
resource "aws_lb" "app_lb" {
  name               = "app-lb"
  internal           = false
  load_balancer_type = "application"
  security_groups    = [aws_security_group.lb_sg.id]
  subnets            = module.vpc.public_subnets
}

// Load Balancer Listener
resource "aws_lb_listener" "frontend" {
  load_balancer_arn = aws_lb.app_lb.arn
  port              = "80"
  default_action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.frontend_target_group.arn
  }
}

// Load Balancer Target Groups
resource "aws_lb_target_group" "frontend_target_group" {
  name        = "frontend-target-group"
  port        = 80
  protocol    = "HTTP"
  vpc_id      = module.vpc.vpc_id
  target_type = "ip"

  depends_on = [aws_lb.app_lb]
}

resource "aws_lb_target_group" "activity_tracking_target_group" {
  name        = "activity-tracking-target-group"
  port        = 5300
  protocol    = "HTTP"
  vpc_id      = module.vpc.vpc_id
  target_type = "ip"

  depends_on = [aws_lb.app_lb]
}

resource "aws_lb_target_group" "analytics_v2_target_group" {
  name        = "analytics-v2-target-group"
  port        = 5051
  protocol    = "HTTP"
  vpc_id      = module.vpc.vpc_id
  target_type = "ip"

  depends_on = [aws_lb.app_lb]
}

resource "aws_lb_target_group" "ai_service_target_group" {
  name        = "ai-service-target-group"
  port        = 5052
  protocol    = "HTTP"
  vpc_id      = module.vpc.vpc_id
  target_type = "ip"

  depends_on = [aws_lb.app_lb]
}

resource "aws_lb_target_group" "auth_service_target_group" {
  name        = "auth-service-target-group"
  port        = 8080
  protocol    = "HTTP"
  vpc_id      = module.vpc.vpc_id
  target_type = "ip"
}

// Load Balancer Listener Rules

resource "aws_lb_listener_rule" "frontend" {
  listener_arn = aws_lb_listener.frontend.arn
  priority     = 1

  action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.frontend_target_group.arn
  }

  condition {
    path_pattern {
      values = ["/", "/home", "/dashboard"]
    }
  }
}

resource "aws_lb_listener_rule" "auth" {
  listener_arn = aws_lb_listener.frontend.arn
  priority     = 2

  action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.auth_service_target_group.arn
  }

  condition {
    path_pattern {
      values = ["/signup", "/login"]
    }
  }
}

resource "aws_lb_listener_rule" "activity_tracking" {
  listener_arn = aws_lb_listener.frontend.arn
  priority     = 3

  action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.activity_tracking_target_group.arn
  }

  condition {
    path_pattern {
      values = ["/trackExercise", "/exercises"]
    }
  }
}

resource "aws_lb_listener_rule" "analytics_v2" {
  listener_arn = aws_lb_listener.frontend.arn
  priority     = 4

  action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.analytics_v2_target_group.arn
  }

  condition {
    path_pattern {
      values = ["/statistics"]
    }
  }
}

resource "aws_lb_listener_rule" "ai_service" {
  listener_arn = aws_lb_listener.frontend.arn
  priority     = 5

  action {
    type             = "forward"
    target_group_arn = aws_lb_target_group.ai_service_target_group.arn
  }

  condition {
    path_pattern {
      values = ["/get_sentiment_message"]
    }
  }
}




output "alb_url" {
  value       = aws_lb.app_lb.dns_name
  description = "The URL of the frontend ALB"
}