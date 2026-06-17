# ersin-isik-challenge
# Camunda Sales Engineer - Technical Challenge Solution

This repository contains the complete solution for the Camunda Sales Engineer Technical Challenge. It includes a Java-based REST Client that interacts with a paginated user API, handles data structure mapping and error scenarios, exports data to a standardized CSV output, and provides local containerization for frictionless testing.

---

## 🛠️ Project Structure

Following the official submission guidelines, the project is organized into a single archive with the exact requested directory layout:

```text
ersin-isik-challenge/
├── README.md               # Setup instructions and architectural notes (This file)
├── ANSWERS.md              # Written answers to the four reflection questions
├── Dockerfile              # Multi-stage Docker build for the Java application
├── docker-compose.yml      # Local orchestration and volume mapping
├── src/                    # Full Java source code and build/dependency configuration (pom.xml)
├── process/
│   └── your-process.bpmn   # BPMN 2.0 business process diagram file
└── output/
    └── users.csv           # Example CSV output generated from a successful execution

Quick Start (Run in Under 2 Minutes)
To satisfy the requirement of executing the application in under five minutes without needing a local Java development kit or Maven installed, the entire workflow has been containerized using Docker and Docker Compose.

Prerequisites
Docker installed and running on your machine.

Docker Compose installed.

Execution Steps
Open your terminal and navigate to the project's root directory:

Bash
cd ersin-isik-challenge
Run the following command to automatically pull the base images, compile the Java source code, run the entry point, and generate the report:

Bash
docker compose up --build
Once the client finishes fetching all pages and logs the results to the console, the container will gracefully exit.

You can immediately inspect the exported dataset locally on your host machine at:

Plaintext
./output/users.csv
(Alternative Local Execution: If you prefer to run it outside of Docker, navigate to the src/ directory, run mvn clean package, and execute java -jar target/challenge.jar.)

📝 Technical Implementation Details
Part 1: The REST Client & Core Logic
Data Structure Representation: Implemented a robust data type (User) to cleanly encapsulate the mandatory fields: id, email, firstName, and lastName.

Pagination Architecture: The client component dynamically discovers and iterates through all sequential pages from the https://regres.in/api/users endpoint until it processes the complete dataset.

Resiliency & Error Handling: Explicit try-catch blocks capture potential network latencies, API drops, or non-200 HTTP statuses. Instead of crashing, the client logs a meaningful fallback error description.

Unit Testing: Includes automated unit tests explicitly verifying the pagination loops and asserting expected application behaviors during simulated API downtime.

Part 2: Containerization & Architecture Decisions
Multi-Stage Build (Dockerfile): To optimize footprint and security, a multi-stage Dockerfile is utilized. Stage 1 (Builder) uses a Maven/JDK image to build the production-ready challenge.jar. Stage 2 (Runtime) extracts only the compiled jar into a lightweight, secure Eclipse Temurin JRE image.

Docker Compose Justification: > "I chose a docker compose.yml configuration over a Kubernetes Deployment manifest to reduce friction for the technical evaluator. In a technical presales cycle, demonstrating value quickly with a low-overhead, single-command startup (docker compose up) provides an optimal, highly reproducible discovery experience for a prospective client or reviewer."

Part 3: The Business Process (BPMN)
Value Alignment: To show how technical REST clients drive real business outcomes, this step is embedded into a high-level orchestration flow.

Design Standards: The process includes logical start and end events, contextual upstream/downstream milestones, and models the automated data fetch specifically as a Camunda Service Task.

Location: The corresponding artifact can be reviewed or deployed directly inside the process/ folder.
