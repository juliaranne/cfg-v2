# Analytics V2 
A rewrite of the existing analytics service. 

```mermaid
classDiagram
    class Statistics {
        - userId: UUID
        - List~Exercise~
    }

    class User 

    class Exercise {
        - exercise_id: UUID
        - exercise_details: ExerciseDetails
    }
    
    class ExerciseDetails 


    Statistics --> User : 
    Statistics --> Exercise : 
    Exercise --> ExerciseDetails : contains 

    %% Key for DDD Components
    note for User "Entity: \n Unique identity (username)"
    note for Exercise "Entity: \n Unique identity (exercise_id)"
    note for ExerciseDetails "Value Object: \n Immutable and no identity"
    note for Statistics "Aggregate: \n Collating a User and Exercises"

```

[//]: # (To render the above, install a mermaid plugin)

## How to run: 

<details>
    <summary>As an independent service </summary>

- Navigate to app.py 
- Run this file
</details>

<details>
    <summary>Using docker </summary>

- `docker-compose build analytics_v2`
- `docker-compose up analytics_v2 -d`
</details>

You may then access at http://localhost:5051/ 

## Development Notes 
* This app will be running on 5051, so take note to specify this during development 

[//]: # (Todo add these instructions  )