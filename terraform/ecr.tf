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

# Create the ECR repositories
resource "aws_ecr_repository" "image_repos" {
  for_each = toset(var.ecr_repos)

  name                 = each.key
  image_tag_mutability = "MUTABLE"

  encryption_configuration {
    encryption_type = "AES256"
  }
}

# Define the IAM policy document for each ECR repository
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

# Attach the policy document to each ECR repository
resource "aws_ecr_repository_policy" "image_repo_policies" {
  for_each = toset(var.ecr_repos)

  repository = aws_ecr_repository.image_repos[each.key].name
  policy     = data.aws_iam_policy_document.image_repo_policy[each.key].json
}

output "ecr_repositories" {
  value = [for repo in aws_ecr_repository.image_repos : repo.repository_url]
}
