FROM python:3.11-slim

RUN pip install --no-cache-dir --upgrade pip setuptools wheel # above is needd to ensure latest versions of key tools and improve the package installation.

WORKDIR /app

COPY . .

RUN pip install --only-binary=:all: -r requirements.txt
RUN python -m spacy download en_core_web_sm


EXPOSE 6000

CMD ["gunicorn", "-b", "0.0.0.0:5052", "app:app"]
