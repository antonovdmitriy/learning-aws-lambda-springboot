[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=antonovdmitriy_learning-aws-lambda-springboot&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=antonovdmitriy_learning-aws-lambda-springboot)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=antonovdmitriy_learning-aws-lambda-springboot&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=antonovdmitriy_learning-aws-lambda-springboot)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=antonovdmitriy_learning-aws-lambda-springboot&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=antonovdmitriy_learning-aws-lambda-springboot)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=antonovdmitriy_learning-aws-lambda-springboot&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=antonovdmitriy_learning-aws-lambda-springboot)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=antonovdmitriy_learning-aws-lambda-springboot&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=antonovdmitriy_learning-aws-lambda-springboot)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=antonovdmitriy_learning-aws-lambda-springboot&metric=coverage)](https://sonarcloud.io/summary/new_code?id=antonovdmitriy_learning-aws-lambda-springboot)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=antonovdmitriy_learning-aws-lambda-springboot&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=antonovdmitriy_learning-aws-lambda-springboot)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=antonovdmitriy_learning-aws-lambda-springboot&metric=bugs)](https://sonarcloud.io/summary/new_code?id=antonovdmitriy_learning-aws-lambda-springboot)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=antonovdmitriy_learning-aws-lambda-springboot&metric=vulnerabilities)](https://sonarcloud.io/summary/new_code?id=antonovdmitriy_learning-aws-lambda-springboot)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=antonovdmitriy_learning-aws-lambda-springboot&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=antonovdmitriy_learning-aws-lambda-springboot)


# Description

The goal of the project is to demonstrate the use of spring boot + Spring Cloud Function to write a lambda 
for aws cloud provider. 

An interesting part in my opinion are examples of writing unit tests for lambdas.

While experimenting I writes notes about interesting features [here](https://github.com/antonovdmitriy/it-notes/blob/master/aws/AWS.md#spring-cloud-functions) 

See official documentation about [Spring Cloud Function](https://docs.spring.io/spring-cloud-function/docs/current/reference/html/aws.html#_introduction)

# To build project

```shell
mvn clean package
```

# To deploy

## With SAM
- How to install AWS CLI [see](https://github.com/antonovdmitriy/it-notes/blob/master/aws/AWS.md#aws-sam-cli)
- How to install SAM  [see](https://github.com/antonovdmitriy/it-notes/blob/master/aws/AWS.md#aws-sam-cli)
- create s3 bucket to deploy code 

```shell
aws s3 mb s3://some_unique_name
```

```shell
 sam deploy \
--s3-bucket s3_name \
--stack-name stack-name \
--capabilities CAPABILITY_IAM
```

## With Github actions pipeline

[see this](.github/workflows/build-and-deploy-to-aws.yml)

# To invoke

## Via AWS CLI

[See this](https://github.com/antonovdmitriy/it-notes/blob/master/aws/AWS.md#invoke-lambda-via-aws-cli)

# To clear

```bash
aws cloudformation delete-stack --stack-name stack-name
```


