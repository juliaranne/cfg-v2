FROM python:3.9.7-slim

WORKDIR /app

COPY . .

RUN pip install --no-cache-dir -r requirements.txt
RUN python -m spacy download en_core_web_sm


EXPOSE 6000

CMD ["gunicorn", "-b", "0.0.0.0:5052", "app:app"]
