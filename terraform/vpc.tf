module "vpc" {
  source = "terraform-aws-modules/vpc/aws"
  name   = "fitness-app-vpc"
  cidr   = "10.0.0.0/16"

  enable_dns_support   = true
  enable_dns_hostnames = true
  azs                  = ["eu-west-2a", "eu-west-2b", "eu-west-2c"]

  public_subnets  = ["10.0.1.0/24", "10.0.2.0/24", "10.0.3.0/24"]
  private_subnets = [] # Empty since we're using public subnets for simplicity

  enable_nat_gateway = false # No NAT Gateway as everything will be in public subnets
  enable_vpn_gateway = false
}
