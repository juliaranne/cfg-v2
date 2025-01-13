# Fitness App ECS Deployment with Terraform

This project uses Terraform to deploy an application on AWS ECS (Elastic Container Service) with Fargate. The application is composed of several services, including a MongoDB database, activity tracking, analytics, AI service, auth service, and frontend.

## Prerequisites

Before you can use this Terraform configuration, ensure you have the following:

- **Terraform** installed on your local machine.
- **AWS CLI** configured with appropriate access to create resources in AWS.
- **Docker** (for local development and testing).

## (Local) Setup

1. Clone this repository:

```bash
git clone https://github.com/yourusername/fitness-app-terraform.git
cd terraform
```

2. Configure AWS credentials:
Ensure your AWS credentials are set up correctly, either by using AWS CLI (aws configure) or environment variables.

3. Initialise Terraform:
Ensure you have Terraform installed on your machine, if not follow online documentation.
Run the following command to initialise Terraform and download the necessary providers:

```bash
terraform init
```

4. Apply Terraform configuration:
To create the resources on AWS, run:

```bash
terraform apply
```

Terraform will ask for confirmation before creating the resources. Type yes to proceed.

5. Access your deployed services:
Once Terraform completes the deployment, your services should be running on ECS with Fargate. You can access the frontend service via the public IP of the ECS tasks or use the relevant ports exposed for each service.

# Directory Structure

```plaintext
├── ecs/
│   ├── main.tf             # The main Terraform configuration file
│   ├── variables.tf        # Variable definitions
│   ├── outputs.tf          # Outputs for the deployed resources
│   └── README.md           # This file
└── README.md               # Project documentation
```

## Services Deployed
- Frontend: A React app running on ECS Fargate.
- Activity Tracking: Tracks user activity data.
- Analytics: Handles data analytics.
- AI Service: Provides machine learning models.
- Auth Service: Manages authentication with Spring Boot and MongoDB.
- MongoDB: A MongoDB database used by the services.

## Security Considerations
- Ensure the MongoDB credentials (root and cfgmla23) are changed before deploying to production.
- Review the security group settings to ensure that only necessary services can access MongoDB.


## Cleanup
To destroy all resources created by this Terraform configuration, run:

```bash
terraform destroy
```
This will remove all the resources created by Terraform in your AWS account.
