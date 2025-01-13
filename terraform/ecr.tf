# This Terraform configuration creates ECR repositories, attaches IAM policies to allow ECS tasks access, and outputs the repository URLs.

# Variable defining the list of ECR repository names
variable "ecr_repos" {
  description = "List of ECR repository names"
  type        = list(string)
  default = [
    "activity-tracking",
    "analytics_v2",
    "authservice",
    "frontend",
    "ai_service",
  ]
}

# Create the ECR repositories with encryption and mutable tags
resource "aws_ecr_repository" "image_repos" {
  for_each = toset(var.ecr_repos)

  name                 = each.key
  image_tag_mutability = "MUTABLE"

  encryption_configuration {
    encryption_type = "AES256"
  }
}

# Define the IAM policy document for each ECR repository allowing ECS tasks to access them
data "aws_iam_policy_document" "image_repo_policy" {
  for_each = toset(var.ecr_repos)

  statement {
    sid    = "AllowECRAccessForEcsTasks"
    effect = "Allow"

    actions = [
      "ecr:GetAuthorizationToken",
      "ecr:BatchGetImage",
      "ecr:GetDownloadUrlForLayer",
      "ecr:BatchCheckLayerAvailability",
    ]

    principals {
      type        = "Service"
      identifiers = ["ecs-tasks.amazonaws.com"]
    }
  }
}

# Attach the generated IAM policy to each ECR repository
resource "aws_ecr_repository_policy" "image_repo_policies" {
  for_each = toset(var.ecr_repos)

  repository = aws_ecr_repository.image_repos[each.key].name
  policy     = data.aws_iam_policy_document.image_repo_policy[each.key].json
}

# Output the URLs of the ECR repositories
output "ecr_repositories" {
  value = [for repo in aws_ecr_repository.image_repos : repo.repository_url]
}
