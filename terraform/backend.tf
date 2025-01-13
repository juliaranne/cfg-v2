terraform {
  backend "s3" {
    bucket = "mla-fitness-app-tf-state"
    region = "eu-west-2"
  }
}
