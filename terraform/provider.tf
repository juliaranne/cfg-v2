# Specify the required providers for the project
terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"  # The AWS provider from HashiCorp
      version = "~> 5.82.2"      # The required version of the AWS provider
    }
  }

  required_version = ">= 1.10.3"  # The minimum Terraform version required
}

# Configure the AWS provider to use the 'eu-west-2' region
provider "aws" {
  region = "eu-west-2"  # Specify the AWS region for resources
}
