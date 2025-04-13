# Shipment Service Microservice

This repository hosts the code and CI/CD pipeline for the Shipment Service microservice. It leverages industry-standard tools and processes to ensure automated, efficient, and reliable software delivery.

---

## Table of Contents

- [Overview](#overview)
- [Version Control](#version-control)
- [Build Process](#build-process)
- [Automated Testing & Code Coverage](#automated-testing--code-coverage)
- [Containerization and Deployment](#containerization-and-deployment)
- [Pipeline Evaluation & Reflection](#pipeline-evaluation--reflection)
- [References](#references)

---

## Overview

The Shipment Service microservice is designed to handle shipment-related functionalities in a cloud-native environment. The project employs Git for version control, Jenkins for continuous integration and deployment, Maven for build automation, and Docker with Ansible for containerization and deployment. The pipeline is enhanced through rigorous testing practices and continuous code quality analysis using SonarQube.

---

## Version Control

- **Repository:** [Shipment-service](https://github.com/SagarBarate/Shipment-service)
- **Tools Used:** Git, GitHub  
- **Branching Strategy:**
  - **main:** Production-ready branch containing stable, tested code.
  - **development:** Integration branch for new features; merged only after successful testing.
  - **feature/notification, feature/order, feature/shipment:** Isolated feature branches to develop new functionalities without impacting the main codebase.
  - **staging:** Pre-production branch used for final tests before merging into main.

---

## Build Process

The project uses Maven in combination with Jenkins to automate the build and packaging process, producing a deployable JAR artifact.

- **Jenkins Integration:**
  - Pulls the latest code from GitHub using webhooks and the Git plugin.
  - Polls the repository (e.g., every 5 minutes) or reacts to push events.
- **Maven Commands:**
  - Configured with goals: `clean install package` to compile, test, and package the service.
- **Artifact:**
  - Generated JAR file is located at `target/shipment-service.jar`.
- **Build Verification:**
  - Jenkins displays the successful build status, archives the artifact, and passes it along for testing and deployment.

---

## Automated Testing & Code Coverage

Quality assurance is achieved through a multi-layered testing strategy integrated into the Jenkins pipeline.

- **Unit Tests:**
  - Developed using JUnit 5 and Mockito.
  - Focus on the business logic (e.g., dispatching shipments, input validations).
- **Integration & System Tests:**
  - Integration tests validate service interactions using mocks.
  - Postman and manual testing help verify API endpoints.
- **Static Code Analysis:**
  - SonarQube continuously inspects the code quality.
  - Achieved over 95% code coverage with JaCoCo reports.
- **Sample Test Cases Include:**
  - `testDispatchShipment_Success()`
  - `testDispatchShipment_Failed_InvalidAddress()`
  - `testDispatchShipment_NullShipment()`
  - `testDispatchShipment_EmptyTrackingNumber()`
  - `testDispatchShipment_AlreadyDispatched()`

---

## Containerization and Deployment

To ensure consistency across different environments, the microservice is containerized using Docker and deployed via Ansible, orchestrated by Jenkins.

- **Docker:**
  - A Dockerfile in the project root builds the container image.
  - The image is pushed to Docker Hub for version tracking.
- **Ansible Deployment:**
  - An Ansible playbook automates container deployment to the designated environment.
  - **Environment Variables:**
    - `ANSIBLE_INVENTORY`: Path to the inventory file defining the deployment target.
    - `ANSIBLE_PLAYBOOK`: Name of the deployment playbook (e.g., `deployment.yml`).
- **Jenkins Deployment Stage:**
  - Post successful build and tests, Jenkins triggers the Ansible playbook.
  - The microservice becomes accessible at [http://localhost:8080](http://localhost:8080).

---

## Pipeline Evaluation & Reflection

The CI/CD pipeline is designed for speed and reliability, automating the entire software delivery lifecycle.

- **Performance Metrics:**
  - **Build Time:** ~45 seconds
  - **Unit Testing & Coverage:** ~30 seconds
  - **SonarQube Analysis:** ~20 seconds
  - **Containerization & Deployment:** ~20 seconds
- **Automation Highlights:**
  - 100% automation from code commit to deployment.
  - Zero manual intervention once a commit is pushed to main or development branches.
- **Continuous Improvement:**
  - Refactoring based on SonarQube insights.
  - Enhanced testing coverage.
  - Usage of Docker volumes to preserve logs and artifacts during container rebuilds.
- **Tool Justification:**
  - **Jenkins:** Provides flexible and scriptable CI/CD orchestration.
  - **Maven:** Streamlines dependency management and build processes.
  - **SonarQube & JaCoCo:** Ensure high code quality and adequate test coverage.
  - **Docker & Ansible:** Facilitate scalable and consistent deployment across environments.

---

## References

1. [Spring Microservices](https://spring.io/microservices)
2. [Mastering Microservices with Spring Boot and Spring Cloud](https://medium.com/@belemgnegreetienne/mastering-microservices-with-spring-boot-and-spring-cloud-a-comprehensive-guide-c6bcc908f8e6)
3. [Spring Cloud](https://spring.io/projects/spring-cloud)
4. [YouTube - Microservices Introduction](https://www.youtube.com/watch?v=HFl2dzhVuUo)
5. [Eraser Workspace](https://app.eraser.io/workspace/GXeour09tezNwRRdgdMq)
6. [Docker Hub - SagarBarate](https://hub.docker.com/repositories/sagarbarate)
7. [Jenkins Official Site](https://www.jenkins.io/)

---
