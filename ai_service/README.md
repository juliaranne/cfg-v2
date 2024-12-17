# AI service
A python app to utilise AI, such as LLMs, to assist in the frontend. 

## How to run: 

<details>
    <summary>As an independent service </summary>

- Navigate to app.py 
- Run this file
</details>

<details>
    <summary>Using docker </summary>

- `docker-compose build ai_service`
- `docker-compose up ai_service -d`
</details>

You may then access at http://localhost:5052/ 

## Development Notes 
* This app will be running on 5052, so take note to specify this during development 

