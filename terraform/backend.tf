# Terraform Configuration Block
terraform {
  # Backend configuration tells Terraform where to store its state
  backend "s3" {
    # The name of the S3 bucket where the Terraform state will be stored
    bucket = "mla-fitness-app-tf-state"

    key = "env:/dev/dev/terraform.tfstate"

    # The AWS region where the S3 bucket is located
    region = "eu-west-2"
  }
}
